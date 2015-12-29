package net.myrts.georgy.google.stubsConvertFromLatLong;

import net.myrts.georgy.api.GeorgyException;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Writes data from JSON to AddressGoogle
 *
 * @author <a href="mailto:avpavlov108@gmail.com">Oleksandr Pavlov</a>
 */
public class JsonToBean {

    private static final Logger LOG = LoggerFactory.getLogger(JsonToBean.class);

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

        for(String key : keys){
            if (addressSettings.containsKey(key)) {
                try {
                    BeanUtils.setProperty(addressGoogle, key, addressSettings.get(key));
                } catch (IllegalAccessException e) {
                    LOG.debug("IllegalAccessException ", e);
                    throw new GeorgyException("IllegalAccessException ", e);
                } catch (InvocationTargetException e) {
                    LOG.debug("InvocationTargetException ", e);
                    throw new GeorgyException("InvocationTargetException ", e);
                }
            }
        }

    }

}
