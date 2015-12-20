package net.myrts.georgy.google;
import com.google.api.client.repackaged.com.google.common.base.Joiner;
import net.myrts.georgy.BaseProviderTest;
import net.myrts.georgy.api.AddressGoogle;
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

        final String addressCompare = "sublocality_level_2 -> Apollo Bandar " +
                "| sublocality_level_1 -> Colaba " +
                "| country -> India " +
                "| route -> Shahid Bhagat Singh Marg " +
                "| administrative_area_level_2 -> Mumbai " +
                "| premise -> B " +
                "| sublocality_level_3 -> Cusrow Baug Colony " +
                "| administrative_area_level_1 -> Maharashtra " +
                "| locality -> Mumbai " +
                "| street_number -> 1218 " +
                "| point_of_interest -> Colaba Depot " +
                "| postal_code -> 400001";

        // http://maps.googleapis.com/maps/api/geocode/json?latlng=18.92038860,72.83013059999999&sensor=false
        final AddressGoogle addressGoogle = new GoogleAddressProvider()
                .convertFromLatLong(dLat, dLon, "en");

        assertEquals(addressCompare, addressGoogle.toString());

        assertEquals(addressCompare,
                Joiner.on(" | ").withKeyValueSeparator(" -> ").useForNull("No such HashMap Element").
                join(addressGoogle.getAddressSettingsMap()));

        assertEquals("sublocality_level_2, " +
                "sublocality_level_1, " +
                "country, route, " +
                "administrative_area_level_2, " +
                "premise, sublocality_level_3, " +
                "administrative_area_level_1, " +
                "locality, street_number, " +
                "point_of_interest, " +
                "postal_code",
                Joiner.on(", ").useForNull("No key").join(addressGoogle.getAddressKeys()));

        assertEquals("Apollo Bandar, " +
                        "Colaba, " +
                        "India, " +
                        "Shahid Bhagat Singh Marg, " +
                        "Mumbai, " +
                        "B, " +
                        "Cusrow Baug Colony, " +
                        "Maharashtra, " +
                        "Mumbai, " +
                        "1218, " +
                        "Colaba " +
                        "Depot, " +
                        "400001",
                Joiner.on(", ").useForNull("No value").join(addressGoogle.getAddressValues()));

        assertEquals("Apollo Bandar", addressGoogle.getSublocalityLevel2());
        assertEquals("Colaba", addressGoogle.getSublocalityLevel1());
        assertEquals("India", addressGoogle.getCountry());
        assertEquals("Shahid Bhagat Singh Marg", addressGoogle.getRoute());
        assertEquals("Mumbai", addressGoogle.getAdministrativeAreaLevel_2());
        assertEquals("Cusrow Baug Colony", addressGoogle.getSublocalityLevel3());
        assertEquals("Maharashtra", addressGoogle.getAdministrativeAreaLevel_1());
        assertEquals("Mumbai", addressGoogle.getLocality());
        assertEquals("1218", addressGoogle.getStreetNumber());
        assertEquals("400001", addressGoogle.getPostalCode());
        assertEquals("Colaba Depot", addressGoogle.getPointOfInterest());
        assertEquals("B", addressGoogle.getPremise());
    }

    @Test
    public void shouldConvertLatLongToAddressUkraine() throws UnknownHostException, GeorgyException {
        //Donbass Arena Donetsk city
        //  http://maps.googleapis.com/maps/api/geocode/json?latlng=48.021238, 37.810244&sensor=false
        final double dLat =48.021238d;
        final double dLon =37.810244d;
        final String addressCompare = "sublocality_level_1 -> Kyivs'kyi district " +
                "| country -> Ukraine " +
                "| route -> Chelyuskintsiv Street " +
                "| administrative_area_level_3 -> Donets'ka city council " +
                "| administrative_area_level_1 -> Donetsk Oblast " +
                "| street_number -> 189 " +
                "| locality -> Donetsk " +
                "| postal_code -> 83000";

        final AddressGoogle addressGoogle = new GoogleAddressProvider()
                .convertFromLatLong(dLat, dLon, "en");

        assertEquals(addressCompare, addressGoogle.toString());

        assertEquals(addressCompare,
                Joiner.on(" | ").withKeyValueSeparator(" -> ").useForNull("No such HashMap Element").
                        join(addressGoogle.getAddressSettingsMap()));

        assertEquals("sublocality_level_1, " +
                        "country, " +
                        "route, " +
                        "administrative_area_level_3, " +
                        "administrative_area_level_1, " +
                        "street_number, " +
                        "locality, " +
                        "postal_code",
                Joiner.on(", ").useForNull("No key").join(addressGoogle.getAddressKeys()));

        assertEquals("Kyivs'kyi district, " +
                        "Ukraine, " +
                        "Chelyuskintsiv Street, " +
                        "Donets'ka city council, " +
                        "Donetsk Oblast, " +
                        "189, " +
                        "Donetsk, " +
                        "83000",
                Joiner.on(", ").useForNull("No value").join(addressGoogle.getAddressValues()));

        assertEquals("Kyivs'kyi district", addressGoogle.getSublocalityLevel1());
        assertEquals("Ukraine", addressGoogle.getCountry());
        assertEquals("Chelyuskintsiv Street", addressGoogle.getRoute());
        assertEquals("Donets'ka city council", addressGoogle.getAdministrativeAreaLevel_3());
        assertEquals("Donetsk Oblast", addressGoogle.getAdministrativeAreaLevel_1());
        assertEquals("189", addressGoogle.getStreetNumber());
        assertEquals("Donetsk", addressGoogle.getLocality());
        assertEquals("83000", addressGoogle.getPostalCode());
    }
}
