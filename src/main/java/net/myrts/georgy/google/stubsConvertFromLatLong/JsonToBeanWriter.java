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

    private static final Logger LOG = LoggerFactory.getLogger(JsonToBeanWriter.class);

    /**
     * to write json to GoogleAddress
     *
     * @param addressGoogle AddressGoogle
     * @param addressSettings Map<String, String>
     * @param keys ArrayList<String>
     */
    public static void jsonToGoogleAddress(AddressGoogle addressGoogle,
                                       Map<String, String> addressSettings,
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

}
