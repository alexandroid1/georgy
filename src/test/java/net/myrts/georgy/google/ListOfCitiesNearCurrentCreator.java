package net.myrts.georgy.google;

import net.myrts.georgy.api.Address;
import net.myrts.georgy.api.AddressLocation;
import net.myrts.georgy.api.GeoLocation;
import net.myrts.georgy.api.GeorgyException;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oleksandr on 03.01.2017.
 * @author <a href="mailto:avpavlov108@gmail.com">Oleksandr Pavlov</a>
 */
public class ListOfCitiesNearCurrentCreator {

    @Test
    public void listOfCities() throws UnknownHostException, GeorgyException {

        final String sourceAddress = "Arnhem, Netherlands";
        final GeoLocation geoLocation = new GoogleAddressProvider().convertToLatLong(sourceAddress);

        double dLat = geoLocation.getLatitude();
        double dLon = geoLocation.getLongitude();

        double radius = 1.00000000d;
        double delta = 0.10000d;

        double searchPointLat = dLat - radius;
        double searchPointLon = dLon - radius;

        GoogleAddressProvider googleAddressProvider = new GoogleAddressProvider();
        AddressLocation addressLocation = null;

        HashSet<String> citiesHashSet = new HashSet<>();
        HashSet<String> subdivisionHashSet = new HashSet<>();

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

                // rate-limit for this API
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Address destinationAddress = addressLocation.getAddress();

                subdivisionHashSet.add(destinationAddress.getSubdivision());
                //System.out.println("Subdivision = " + address.getSubdivision());

                citiesHashSet.add(destinationAddress.getCity());
                //System.out.println("City = " + address.getCity());
            }
        }

        // Subdivision
        List sortedSubdivisionList = new ArrayList(subdivisionHashSet);
        Collections.sort(sortedSubdivisionList);
        System.out.println(sortedSubdivisionList);
        assertEquals("Subdivision does not match " + sortedSubdivisionList, "[Limburg, " +
                        "Nordrhein-Westfalen, Vlaanderen]"
                , sortedSubdivisionList.toString());

        // Cities
        List sortedCitiesList = new ArrayList(citiesHashSet);
        Collections.sort(sortedCitiesList);
        System.out.println(sortedCitiesList);
        assertEquals("City does not match " + sortedCitiesList, "[Beringen, Burscheid, Dormagen, " +
                "Echt, Erkelenz, Grevenbroich, Ham, Heinsberg, Houthalen-Helchteren, Hückelhoven, Jüchen, Laakdal, " +
                "Leverkusen, Maaseik, Meeuwen-Gruitrode, Monheim am Rhein, " +
                "Rommerskirchen, Waldfeucht]"
                , sortedCitiesList.toString());

    }
}
