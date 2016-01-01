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
 * @author <a href="mailto:avpavlov108@gmail.com">Oleksandr Pavlov</a>
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
                readsJsonToMap(addressSettings, addressComponents, i);
            }
        }
    }

    /**
     * To read Json To Map
     *
     * @param addressSettings Map<String, String>
     * @param addressComponents JSONArray
     * @param i int
     */
    private static void readsJsonToMap(Map<String, String> addressSettings, JSONArray addressComponents, int i) {
        String longName = addressComponents.getJSONObject(i).getString("long_name");
        String shortName = addressComponents.getJSONObject(i).getString("short_name");
        JSONArray types = addressComponents.getJSONObject(i).getJSONArray("types");
        String type = (String) types.get(0);
        addressSettings.putIfAbsent(type, longName);
        addressSettings.putIfAbsent(type+"_short", shortName);
    }

}
