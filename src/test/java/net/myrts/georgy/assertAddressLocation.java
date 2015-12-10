package net.myrts.georgy;

import net.myrts.georgy.api.AddressLocation;
import net.myrts.georgy.api.GeoLocation;

/**
 * Created by georgy on 08.12.15.
 */
public interface assertAddressLocation {
    void assertLocation(Double latitude, Double longitude, AddressLocation addressLocation);
}
