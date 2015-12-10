package net.myrts.georgy;

import net.myrts.georgy.api.GeoLocation;

/**
 * Created by georgy on 10.12.15.
 */
public interface assertGeoLocation {
    void assertLocation(Double latitude, Double longitude, GeoLocation geoLocation);
}
