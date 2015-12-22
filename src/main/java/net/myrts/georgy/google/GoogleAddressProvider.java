package net.myrts.georgy.google;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.api.client.util.Joiner;
import com.google.common.base.Functions;
import com.google.common.collect.*;
import net.myrts.georgy.api.*;
import net.myrts.georgy.google.stubsConvertFromLatLong.JsonReader;
import net.myrts.georgy.google.stubsConvertToLatLong.GoogleResponse;
import net.myrts.georgy.google.stubsConvertToLatLong.Result;
import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * Created by Oleksandr Pavlov avpavlov108@gmail.com on 15.11.15.
 *
 * @author <a href="mailto:avpavlov108@gmail.com">Oleksandr Pavlov</a>
 */

/**
 * Geocode request URL. Here see we are passing "json" it means we will get
 * the output in JSON format. You can also pass "xml" instead of "json" for
 * XML output. For XML output URL will be
 * "http://maps.googleapis.com/maps/api/geocode/xml";
 */
public class GoogleAddressProvider implements GeoProviderLatLon {

    /**
     * Here the fullAddress String
     */
    private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";

    private static final Logger LOG = LoggerFactory.getLogger(GoogleAddressProvider.class);

    /**
     * Create an java.net.URL object by passing the request URL in
     * constructor.
     *
     * @param fullAddress String
     * @return geoLocation GeoLocation
     */
    @Override
    public GeoLocation convertToLatLong(String fullAddress) throws GeorgyException {

        try {
            URL url = new URL(URL + "?address="
                    + URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=false");
            LOG.debug("URL ", URL);

            // Open the Connection
            URLConnection conn = url.openConnection();

            GoogleResponse response;

            try (InputStream in = conn.getInputStream()) {

                ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

                // IMPORTANT
                // without this option set adding new fields breaks old code
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                response = mapper.readValue(in, GoogleResponse.class);

                if ("OK".equals(response.getStatus())) {
                    if (response.getResults().isEmpty()) {
                        throw new GeorgyException("Response result is empty " + response);
                    }
                    for (Result result : response.getResults()) {
                        GeoLocation geoLocation = new GeoLocation(
                                result.getGeometry().getLocation().getLat(),
                                result.getGeometry().getLocation().getLng()
                        );

                        LOG.debug("Latitude ", result.getGeometry().getLocation().getLat());
                        LOG.debug("Longitude ", result.getGeometry().getLocation().getLng());
                        LOG.debug("Location type ", result.getGeometry().getLocationType());
                        return geoLocation;
                    }
                } else {
                    LOG.debug(response.getStatus());
                    throw new GeorgyException("Failed to get response " + response);
                }
            }

        } catch (MalformedURLException e) {
            LOG.error("MalformedURLException ", e);
            throw new GeorgyException(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            LOG.error("UnsupportedEncodingException ", e);
            throw new GeorgyException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            LOG.error("JsonMappingException ", e);
            throw new GeorgyException(e.getMessage(), e);
        } catch (JsonParseException e) {
            LOG.error("JsonParseException ", e);
            throw new GeorgyException(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error("IOException ", e);
            throw new GeorgyException(e.getMessage(), e);
        }

        return null;
    }

    /**
     * convertFromLatLong
     *
     * @param dLat double
     * @param dLon double
     * @param lang String
     *
     * @return addressGoogle AddressGoogle
     */
    @Override
    public AddressGoogle convertFromLatLong(double dLat, double dLon, String lang) throws GeorgyException {
        if (!(dLat > -90 && dLat <= 90)) {
            LOG.error("Latitude should be in -90..90 range");
            throw new GeorgyException("Failed to get response " +
                    "Latitude should be in -90..90 range");
        } else {
            if (!(dLon > -180 && dLon <= 180)) {
                LOG.error("Longitude should be in -180..180 range");
                throw new GeorgyException("Failed to get response " +
                        "Longitude should be in -180..180 range");
            } else {

                final String latLongString = String.valueOf(dLat) + "," + String.valueOf(dLon);
                final String baseUrl = URL;
                final Map<String, String> params = Maps.newHashMap();
                params.put("language", lang);
                params.put("sensor", "false");
                params.put("latlng", latLongString);
                final String url = baseUrl + '?' + encodeParams(params);
                LOG.debug("url ", url);
                AddressGoogle addressGoogle = new AddressGoogle();
                try {
                    final JSONObject response = JsonReader.read(url);

                    if ("OK".equals(response.getString("status"))) {

                        HashMap<String, String> addressSettings = Maps.newHashMap();

                        for (int j = 0; j < response.getJSONArray("results").length(); j++) {

                            final JSONObject location = response.getJSONArray("results").getJSONObject(j);
                            final JSONArray addressComponents = location.getJSONArray("address_components");
                            LOG.debug("addressComponents " + addressComponents);

                            for (int i = 0; i < addressComponents.length(); i++) {
                                String longName = addressComponents.getJSONObject(i).getString("long_name");
                                JSONArray types = addressComponents.getJSONObject(i).getJSONArray("types");
                                String type = (String) types.get(0);
                                addressSettings.putIfAbsent(type, longName);
                            }
                        }
                        Set set = addressSettings.keySet();
                        LOG.debug("addressSettings.keySet(); " + set);

                        ArrayList<String> keys = new ArrayList<>(set);
                        LOG.debug("keys " + keys);

                        Function<String, String> rotateHashMap = Functions.forMap(addressSettings);
                        ArrayList<String> values = new ArrayList<>(Collections2.transform(keys, rotateHashMap));

                        keys.forEach((Object key) -> LOG.debug(key + " " + addressSettings.get(key)));

                        addressGoogle.setAddressKeys(keys);
                        addressGoogle.setAddressValues(values);
                        addressGoogle.setAddressSettingsMap(addressSettings);

                        keys.forEach((Object key) -> {
                            if (addressSettings.containsKey(key)) {
                                try {
                                    BeanUtils.setProperty(addressGoogle,
                                            (String) key,
                                            addressSettings.get(key));
                                } catch (IllegalAccessException e) {
                                    LOG.error("IllegalAccessException ", e);
                                    try {
                                        throw new GeorgyException(e.getMessage(), e);
                                    } catch (GeorgyException e1) {
                                        e1.printStackTrace();
                                    }
                                } catch (InvocationTargetException e) {
                                    LOG.error("IllegalAccessException ", e);
                                    try {
                                        throw new GeorgyException(e.getMessage(), e);
                                    } catch (GeorgyException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            }
                        });

                    } else {
                        LOG.debug(response.getString("status"));
                        throw new GeorgyException("Failed to get response " + response);
                    }

                } catch (MalformedURLException e) {
                    LOG.error("MalformedURLException ", e);
                    throw new GeorgyException(e.getMessage(), e);
                } catch (UnsupportedEncodingException e) {
                    LOG.error("UnsupportedEncodingException ", e);
                    throw new GeorgyException(e.getMessage(), e);
                } catch (IOException e) {
                    LOG.error("IOException ", e);
                    throw new GeorgyException(e.getMessage(), e);
                }

                return addressGoogle;

            }
        }
    }

    private static String encodeParams(final Map<String, String> params) {
        final String paramsUrl = Joiner.on('&').join(
                Iterables.transform(params.entrySet(), new Function<Map.Entry<String, String>, String>() {

                    @Nullable
                    @Override
                    public String apply(@Nullable Map.Entry<String, String> input) {
                        try {
                            final StringBuffer buffer = new StringBuffer();
                            buffer.append(input.getKey());
                            buffer.append('=');
                            buffer.append(URLEncoder.encode(input.getValue(), "utf-8"));
                            return buffer.toString();
                        } catch (final UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));

        return paramsUrl;
    }

}