package net.myrts.georgy.api;

/**
 * Geo provider Lat Lon base interface.
 *
 * @author <a href="mailto:avpavlov108@gmail.com">Oleksandr Pavlov</a>
 */
public interface GeoProviderLatLon {
    GeoLocation convertToLatLong(String fullAddress) throws GeorgyException;

    AddressGoogle convertFromLatLong(double dLat, double dLon, String lang) throws GeorgyException;
}
