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
    public void shouldConvertLatLongToAddressIndia() throws UnknownHostException, GeorgyException {

        double dLat = 50.115977d;
        double dLon = 8.690928d;

        double radius = 10.00000000d;


        DoublePoint doublePoint = new DoublePoint(dLat, dLon);

        while (
                (dLat - radius) < doublePoint.getdLat()
                        && doublePoint.getdLat() < (dLat + radius)
                ) {
            doublePoint.increasedLat();
            while (
                    (dLon - radius) < doublePoint.getdLon()
                            && doublePoint.getdLon() < (dLon + radius)
                    ) {
                doublePoint.increasedLon();
                final GoogleAddressProvider googleAddressProvider = new GoogleAddressProvider();
                final AddressLocation addressLocation = googleAddressProvider.convertFromLatLong(dLat, dLon, "en");
                final Address address = addressLocation.getAddress();
                System.out.println(address.getSubdivision() + address.getCity());
            }
        }
    }
}
