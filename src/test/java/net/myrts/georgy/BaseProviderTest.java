package net.myrts.georgy;

import net.myrts.georgy.api.AddressLocation;
import net.myrts.georgy.api.GeoLocation;

import static org.junit.Assert.assertEquals;
/**
 * Created by Oleksandr Pavlov avpavlov108@gmail.com on 15.12.15.
 */

public class BaseProviderTest {

    public void assertLocation(Double latitude, Double longitude, GeoLocation geoLocation) {
        assertEquals(latitude, geoLocation.getLatitude(), 0.00001);
        assertEquals(longitude, geoLocation.getLongitude(), 0.00001);
    }

    public void assertLocation(Double latitude, Double longitude, AddressLocation addressLocation) {
        final GeoLocation geoLocation = addressLocation.getLocation();
        assertEquals("Latitude does not match " + addressLocation, latitude, geoLocation.getLatitude(), 0.00001);
        assertEquals("Longitude does not match for " + addressLocation, longitude, geoLocation.getLongitude(), 0.00001);
    }
}
