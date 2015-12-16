package net.myrts.georgy.google;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;


import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;


/**
 * Created by Oleksandr Pavlov avpavlov108@gmail.com on 15.11.15.
 */

/**
 * Geocode request URL. Here see we are passing "json" it means we will get
 * the output in JSON format. You can also pass "xml" instead of "json" for
 * XML output. For XML output URL will be
 * "http://maps.googleapis.com/maps/api/geocode/xml";
 */
public class GoogleAddressProvider implements GeoProviderLatLon {

    /**
     * Here the fullAddress String is in format like
     * "address,city,state,zipcode". Here address means "street number + route"
     */
    private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";

    private static final Logger LOG = LoggerFactory.getLogger(GoogleAddressProvider.class);

    /**
     * Create an java.net.URL object by passing the request URL in
     * constructor. Here you can see I am converting the fullAddress String
     * in UTF-8 format. You will get Exception if you don't convert your
     * address in UTF-8 format. Perhaps google loves UTF-8 format. :) In
     * parameter we also need to pass "sensor" parameter. sensor (required
     * parameter) — Indicates whether or not the geocoding request comes
     * from a device with a location sensor. This value must be either true
     * or false.
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
     * @param latLongString String
     */
    @Override
    public AddressGoogle convertFromLatLong(String latLongString) throws GeorgyException {

        final String baseUrl = URL;
        final Map<String, String> params = Maps.newHashMap();

        // @todo func input param lang
        params.put("language", "ru");
        params.put("sensor", "false");
        params.put("latlng", latLongString);
        final String url = baseUrl + '?' + encodeParams(params);
        LOG.debug("url ", url);
        AddressGoogle addressGoogle = new AddressGoogle();
        try {
            final JSONObject response = JsonReader.read(url);

            //@todo for () getJSONObject(0); -> length
            final JSONObject location = response.getJSONArray("results").getJSONObject(0);
            final JSONArray addressComponents = location.getJSONArray("address_components");
            LOG.debug("addressComponents " + addressComponents);

            HashMap<String, String> addressSettings = Maps.newHashMap();
            for (int i = 0; i < addressComponents.length(); i++) {
                String longName = addressComponents.getJSONObject(i).getString("long_name");
                JSONArray types = addressComponents.getJSONObject(i).getJSONArray("types");
                String type = (String) types.get(0);
                addressSettings.put(type, longName);
            }

            Set set = addressSettings.keySet();
            LOG.debug("addressSettings.keySet(); " + set);

            ArrayList<String> keys = new ArrayList<>(set);
            LOG.debug("keys " + keys);

            Function<String, String> rotateHashMap = Functions.forMap(addressSettings);
            ArrayList<String> values = new ArrayList<>(Collections2.transform(keys, rotateHashMap));

            for (Object key : keys) {
                LOG.debug(key + " " + addressSettings.get(key));
            }

            addressGoogle.setAddressKeys(keys);
            addressGoogle.setAddressValues(values);
            addressGoogle.setAddressSettingsMap(addressSettings);

            // Київський район
            // Colaba
            if (addressSettings.containsKey("sublocality_level_1")) {
                addressGoogle.setSublocalityLevel1(addressSettings.get("sublocality_level_1"));
            }

            // null
            // Apollo Bandar
            if (addressSettings.containsKey("sublocality_level_2")) {
                addressGoogle.setSublocalityLevel1(addressSettings.get("sublocality_level_2"));
            }

            // null
            // Cusrow Baug Colony
            if (addressSettings.containsKey("sublocality_level_3")) {
                addressGoogle.setSublocalityLevel3(addressSettings.get("sublocality_level_3"));
            }

            //Украина
            // Индия
            if (addressSettings.containsKey("country")) {
                addressGoogle.setCountry(addressSettings.get("country"));
            }

            //вулиця Челюскінців
            // null
            if (addressSettings.containsKey("route")) {
                addressGoogle.setRoute(addressSettings.get("route"));

            }

            //Донецька область
            //Maharashtra
            if (addressSettings.containsKey("administrative_area_level_1")) {
                addressGoogle.setAdministrativeAreaLevel_1(addressSettings.get("administrative_area_level_1"));
            }

            // null
            // Mumbai
            if (addressSettings.containsKey("administrative_area_level_2")) {
                addressGoogle.setAdministrativeAreaLevel_2(addressSettings.get("administrative_area_level_2"));
            }

            //Донецька міськрада
            // Cusrow Baug Colony
            if (addressSettings.containsKey("administrative_area_level_3")) {
                addressGoogle.setAdministrativeAreaLevel_3(addressSettings.get("administrative_area_level_3"));
            }

            //189
            if (addressSettings.containsKey("street_number")) {
                addressGoogle.setStreetNumber(addressSettings.get("street_number"));
            }

            //Донецьк
            //Mumbai
            if (addressSettings.containsKey("locality")) {
                addressGoogle.setStreetNumber(addressSettings.get("locality"));
            }

            //@todo add postal_code

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