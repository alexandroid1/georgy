package net.myrts.georgy.google;

import net.myrts.georgy.BaseProviderTest;
import net.myrts.georgy.api.Address;
import net.myrts.georgy.api.AddressGoogle;
import net.myrts.georgy.api.GeoLocation;
import org.junit.Test;

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
public class GoogleGeoProviderTest extends BaseProviderTest {

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
        final AddressGoogle addressGoogle = new GoogleAddressProvider().convertFromLatLong(strLat + "," + strLon);
        assertEquals(addressCompare, addressGoogle.toString());
    }

    @Test
    public void shouldConvertLatLongToAddressByString() throws UnknownHostException, GeorgyException {


       // final String strLat ="48.033342";
       // final String strLon ="37.790217";

        // вул. Челюскінців, 265, Донецьк, Донецька область, Украина
        //48.033475, 37.790281
        //  http://maps.googleapis.com/maps/api/geocode/json?latlng=48.033475, 37.790281&sensor=false

        //Donbass Arena
        //  http://maps.googleapis.com/maps/api/geocode/json?latlng=48.021238, 37.810244&sensor=false
        final String strLat ="48.021238";
        final String strLon ="37.810244";
        final String addressCompare = "vul. Chelyuskіntsіv 263 Donetsk Ukraine";
       // 48.021238, 37.810244

        // http://maps.googleapis.com/maps/api/geocode/json?latlng=18.92038860,72.83013059999999&sensor=false
        final AddressGoogle addressGoogle = new GoogleAddressProvider().convertFromLatLong(strLat+","+strLon);
        assertEquals(addressCompare, addressGoogle.toString());
    }

}
