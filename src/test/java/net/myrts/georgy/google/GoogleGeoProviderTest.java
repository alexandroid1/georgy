package net.myrts.georgy.google;

import net.myrts.georgy.api.Address;
import net.myrts.georgy.api.GeoLocation;
import net.myrts.georgy.assertGeoLocation;
import net.myrts.georgy.google.stubs.GoogleResponse;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import net.myrts.georgy.api.GeorgyException;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Created by Oleksandr Pavlov avpavlov108@gmail.com on 15.11.15.
 */
public class GoogleGeoProviderTest implements assertGeoLocation {

    @Override
    public void assertLocation(Double latitude, Double longitude, GeoLocation geoLocation) {
        assertEquals(latitude, geoLocation.getLatitude(), 0.00001);
        assertEquals(longitude, geoLocation.getLongitude(), 0.00001);
    }

    @Test
    public void shouldConvertAddressToLatLong() throws UnknownHostException, GeorgyException {
        final String address = "Apollo Bunder, Mumbai, Maharashtra, India";
        final double dLat =18.9203886d;
        final double dLon =72.8301305d;

        final GeoLocation geoLocation = new GoogleAddressProvider().convertToLatLong(address);
        assertLocation(dLat, dLon, geoLocation);
    }

    @Test
    public void shouldConvertLatLongToAddress() throws UnknownHostException, GeorgyException {
        final String strLat ="18.92038860";
        final String strLon ="72.83013059999999";
        final String addressCompare = "1218, Shahid Bhagat Singh Marg, Apollo Bandar, Colaba, Mumbai, Maharashtra 400001, India";

        // http://maps.googleapis.com/maps/api/geocode/json?latlng=18.92038860,72.83013059999999&sensor=false
        final Address addressGoogle = new GoogleAddressProvider().convertFromLatLong(strLat+","+strLon);
        assertEquals(addressCompare, addressGoogle.toStringGoogle());
    }

}
