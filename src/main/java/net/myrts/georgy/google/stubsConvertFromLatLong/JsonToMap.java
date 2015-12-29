package net.myrts.georgy.google.stubsConvertFromLatLong;

import net.myrts.georgy.google.GoogleAddressProvider;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * To write JSON to Map
 *
 * @Created by Oleksandr Pavlov avpavlov108@gmail.com on 29.12.15.
 */
public class JsonToMap {

    private static final Logger LOG = LoggerFactory.getLogger(JsonToMap.class);

    /**
     * To write JSON to Map
     *
     * @param response JSONObject
     * @param addressSettings Map<String, String>
     */
    public static void jsonParseToMap(JSONObject response,
                                      Map<String, String> addressSettings) {
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
    }

}
