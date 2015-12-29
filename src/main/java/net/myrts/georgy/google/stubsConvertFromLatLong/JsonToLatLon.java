package net.myrts.georgy.google.stubsConvertFromLatLong;

import net.myrts.georgy.api.GeoLocation;
import net.myrts.georgy.api.GeorgyException;
import net.myrts.georgy.google.stubsConvertToLatLong.GoogleResponse;
import net.myrts.georgy.google.stubsConvertToLatLong.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * To receive lat,lon from JSON
 *
 * @author <a href="mailto:avpavlov108@gmail.com">Oleksandr Pavlov</a>
 */
public class JsonToLatLon {

    private static final Logger LOG = LoggerFactory.getLogger(JsonToLatLon.class);

    public static GeoLocation getGeoLocation(GoogleResponse response) throws GeorgyException {
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
        return null;
    }
}
