package net.myrts.georgy.api;

import net.myrts.georgy.google.stubsConvertFromLatLong.AddressGoogle;

/**
 * Geo provider Lat Lon base interface.
 *
 * @author <a href="mailto:avpavlov108@gmail.com">Oleksandr Pavlov</a>
 */
public interface GeoProviderLatLon {
    GeoLocation convertToLatLong(String fullAddress) throws GeorgyException;

    AddressLocation convertFromLatLong(double dLat, double dLon, String lang) throws GeorgyException;
}
