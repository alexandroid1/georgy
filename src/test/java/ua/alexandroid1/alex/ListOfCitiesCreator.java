package ua.alexandroid1.alex;

import net.myrts.georgy.BaseProviderTest;
import net.myrts.georgy.api.Address;
import net.myrts.georgy.api.AddressLocation;
import net.myrts.georgy.api.GeorgyException;
import net.myrts.georgy.google.GoogleAddressProvider;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oleksandr on 03.01.2017.
 */
public class ListOfCitiesCreator extends BaseProviderTest {

    @Test
    public void listOfCities() throws UnknownHostException, GeorgyException {

        double dLat = 50.115977d;
        double dLon = 8.690928d;

        double radius = 1.00000000d;
        double delta = 0.2000000d;

        double searchPointLat = dLat - radius;
        double searchPointLon = dLon - radius;

        GoogleAddressProvider googleAddressProvider = new GoogleAddressProvider();
        AddressLocation addressLocation = null;

        while (((dLat - radius - delta) <= searchPointLat)
                && (searchPointLat <= (dLat + radius + delta) )) {
            searchPointLat = searchPointLat + delta;
            while (
                    (dLon - radius - delta) <= searchPointLon
                            && searchPointLon <= (dLon + radius + delta)
                    ) {
                searchPointLon = searchPointLon + delta;

                try {
                    addressLocation = googleAddressProvider.convertFromLatLong(searchPointLat, searchPointLon, "en");
                } catch (GeorgyException e) {
                    e.printStackTrace();
                }
                Address address = addressLocation.getAddress();

                System.out.println("Subdivision = " + address.getSubdivision() );
                System.out.println("City = " + address.getCity());

            }
        }

    }

}
