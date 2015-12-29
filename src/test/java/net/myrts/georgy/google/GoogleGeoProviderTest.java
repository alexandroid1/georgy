package net.myrts.georgy.google;
import net.myrts.georgy.BaseProviderTest;
import net.myrts.georgy.api.Address;
import net.myrts.georgy.api.AddressLocation;
import net.myrts.georgy.api.GeoLocation;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import net.myrts.georgy.api.GeorgyException;
import java.net.UnknownHostException;

/**
 * Created by Oleksandr Pavlov avpavlov108@gmail.com on 15.11.15.
 * @author <a href="mailto:avpavlov108@gmail.com">Oleksandr Pavlov</a>
 */
public class GoogleGeoProviderTest extends BaseProviderTest {
    @Test
    public void shouldConvertAddressToLatLongIndia() throws UnknownHostException, GeorgyException {
        final String address = "Apollo Bunder, Mumbai, Maharashtra, India";
        final double dLat =18.9203886d;
        final double dLon =72.8301305d;

        final GeoLocation geoLocation = new GoogleAddressProvider().convertToLatLong(address);
        assertLocation(dLat, dLon, geoLocation);
    }

    @Test
    public void shouldConvertLatLongToAddressIndia() throws UnknownHostException, GeorgyException {

        final double dLat =18.92038860d;
        final double dLon =72.83013059d;

        // http://maps.googleapis.com/maps/api/geocode/json?latlng=18.92038860,72.83013059999999&sensor=false
        final GoogleAddressProvider googleAddressProvider = new GoogleAddressProvider();
        final AddressLocation addressLocation = googleAddressProvider.convertFromLatLong(dLat, dLon, "en");
        final Address address = addressLocation.getAddress();

        assertEquals("Country iso code does not match " + addressLocation, "IN", address.getCountryIsoCode());
        assertEquals("Country does not match " + addressLocation, "India", address.getCountry());

        assertEquals("State iso code does not match " + addressLocation, "MH", address.getSubdivisionIsoCode());
        assertEquals("State name does not match " + addressLocation, "Maharashtra", address.getSubdivision());

        assertEquals("City does not match " + addressLocation, "Mumbai", address.getCity());

        assertEquals("Postal code does not match " + addressLocation, "400001", address.getPostalCode());
        assertLocation(18.92038860d, 72.83013059d, addressLocation);


    }

    @Test
    public void shouldConvertLatLongToAddressUkraine() throws UnknownHostException, GeorgyException {

        final double dLat =48.021238d;
        final double dLon =37.810244d;

        //  http://maps.googleapis.com/maps/api/geocode/json?latlng=48.021238, 37.810244&sensor=false
        final GoogleAddressProvider googleAddressProvider = new GoogleAddressProvider();
        final AddressLocation addressLocation = googleAddressProvider.convertFromLatLong(dLat, dLon, "en");
        final Address address = addressLocation.getAddress();

        assertEquals("Country iso code does not match " + addressLocation, "UA", address.getCountryIsoCode());
        assertEquals("Country does not match " + addressLocation, "Ukraine", address.getCountry());

        assertEquals("State iso code does not match " + addressLocation, "Donetsk Oblast", address.getSubdivisionIsoCode());
        assertEquals("State name does not match " + addressLocation, "Donetsk Oblast", address.getSubdivision());

        assertEquals("City does not match " + addressLocation, "Donetsk", address.getCity());

        assertEquals("Postal code does not match " + addressLocation, "83000", address.getPostalCode());
        assertLocation(48.021238d, 37.810244d, addressLocation);

    }
}
