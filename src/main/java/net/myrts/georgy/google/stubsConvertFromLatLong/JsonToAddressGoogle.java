package net.myrts.georgy.google.stubsConvertFromLatLong;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Maps;
import net.myrts.georgy.api.GeorgyException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static net.myrts.georgy.google.stubsConvertFromLatLong.JsonToBean.jsonToGoogleAddress;
import static net.myrts.georgy.google.stubsConvertFromLatLong.JsonToMap.jsonParseToMap;
import static net.myrts.georgy.google.stubsConvertFromLatLong.UrlEncoder.encodeParams;

/**
 * To write JSON to AdressGoogle
 *
 * @author <a href="mailto:avpavlov108@gmail.com">Oleksandr Pavlov</a>
 */
public class JsonToAddressGoogle {

    private static final Logger LOG = LoggerFactory.getLogger(JsonToAddressGoogle.class);

    public static AddressGoogle getAddressGoogle(double dLat, double dLon, String lang, String baseUrl) throws GeorgyException {
        if (!(dLat > -90 && dLat <= 90)) {
            LOG.error("Latitude should be in -90..90 range");
            throw new GeorgyException("Failed to get response " +
                    "Latitude should be in -90..90 range");
        }
        {
            if (!(dLon > -180 && dLon <= 180)) {
                LOG.error("Longitude should be in -180..180 range");
                throw new GeorgyException("Failed to get response " +
                        "Longitude should be in -180..180 range");
            } else {
                final String latLongString = String.valueOf(dLat) + "," + String.valueOf(dLon);
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

                        Map<String, String> addressSettings = Maps.newHashMap();
                        jsonParseToMap(response, addressSettings);

                        Set<String> set = addressSettings.keySet();
                        LOG.debug("addressSettings.keySet(); " + set);

                        ArrayList<String> keys = new ArrayList<>(set);
                        LOG.debug("keys " + keys);

                        Function<String, String> rotateHashMap = Functions.forMap(addressSettings);
                        ArrayList<String> values = new ArrayList<>(Collections2.transform(keys, rotateHashMap));

                        keys.forEach((Object key) -> LOG.debug(key + " " + addressSettings.get(key)));

                        addressGoogle.setAddressKeys(keys);
                        addressGoogle.setAddressValues(values);
                        addressGoogle.setAddressSettingsMap(addressSettings);

                        jsonToGoogleAddress(addressGoogle, addressSettings, keys);

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
}
