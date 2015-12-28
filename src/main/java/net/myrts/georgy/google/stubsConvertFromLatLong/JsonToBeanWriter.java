package net.myrts.georgy.google.stubsConvertFromLatLong;

import com.google.api.client.util.Joiner;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import net.myrts.georgy.api.AddressGoogle;
import net.myrts.georgy.api.GeorgyException;
import net.myrts.georgy.google.GoogleAddressProvider;
import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Writes data from JSON to AddressGoogle
 *
 * @Created by Oleksandr Pavlov avpavlov108@gmail.com on 28.12.15.
 */
public class JsonToBeanWriter {

    private static final Logger LOG = LoggerFactory.getLogger(GoogleAddressProvider.class);

    protected void jsonToGoogleAddress(AddressGoogle addressGoogle,
                                       HashMap<String, String> addressSettings,
                                       ArrayList<String> keys)
            throws GeorgyException {

        keys.forEach((Object key) -> {
            if (addressSettings.containsKey(key)) {
                try {
                    BeanUtils.setProperty(addressGoogle,
                            (String) key,
                            addressSettings.get(key));
                } catch (IllegalAccessException e) {
                    LOG.debug("IllegalAccessException ", e);
                    try {
                        throw new GeorgyException("IllegalAccessException ", e);
                    } catch (GeorgyException e1) {
                        e1.printStackTrace();
                    }

                } catch (InvocationTargetException e) {
                    LOG.debug("InvocationTargetException ", e);
                    try {
                        throw new GeorgyException("InvocationTargetException ", e);
                    } catch (GeorgyException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    protected static String encodeParams(final Map<String, String> params) {
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

    protected void jsonParseToMap(JSONObject response,
                                  HashMap<String,
                                          String> addressSettings) {
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
