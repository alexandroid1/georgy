package net.myrts.georgy.google;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import net.myrts.georgy.api.*;
import net.myrts.georgy.google.stubsConvertFromLatLong.AddressGoogle;
import net.myrts.georgy.google.stubsConvertToLatLong.GoogleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.myrts.georgy.google.stubsConvertFromLatLong.JsonToAddressGoogle.getAddressGoogle;
import static net.myrts.georgy.google.stubsConvertFromLatLong.JsonToLatLon.getGeoLocation;

/**
 * @author <a href="mailto:avpavlov108@gmail.com">Oleksandr Pavlov</a>
 *         <p>
 *         * Geocode request URL. Here see we are passing "json" it means we will get
 *         the output in JSON format. You can also pass "xml" instead of "json" for
 *         XML output. For XML output URL will be
 *         "http://maps.googleapis.com/maps/api/geocode/xml";
 */

public class GoogleAddressProvider implements GeoProviderLatLon {

    /**
     * Here the fullAddress String
     */
    private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";

    private static final Logger LOG = LoggerFactory.getLogger(GoogleAddressProvider.class);

    /**
     * convert To Lat,Long
     *
     * @param fullAddress String
     * @return geoLocation GeoLocation
     */
    @Override
    public GeoLocation convertToLatLong(String fullAddress) throws GeorgyException {

        try {
            URL url = new URL(URL + "?address=" + URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=false");
         //   LOG.debug("URL ", URL);

            // Open the Connection
            URLConnection conn = url.openConnection();
            GoogleResponse response;

            try (InputStream in = conn.getInputStream()) {
                ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

                // IMPORTANT
                // without this option set adding new fields breaks old code
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                response = mapper.readValue(in, GoogleResponse.class);
                GeoLocation geoLocation = getGeoLocation(response);
                if (geoLocation != null) return geoLocation;
            }
        } catch (MalformedURLException e) {
            LOG.error("MalformedURLException ", e);
            throw new GeorgyException(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            LOG.error("UnsupportedEncodingException ", e);
            throw new GeorgyException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            LOG.error("JsonMappingException ", e);
            throw new GeorgyException(e.getMessage(), e);
        } catch (JsonParseException e) {
            LOG.error("JsonParseException ", e);
            throw new GeorgyException(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error("IOException ", e);
            throw new GeorgyException(e.getMessage(), e);
        }

        return null;
    }


    /**
     * convert From Lat,Long
     *
     * @param dLat double
     * @param dLon double
     * @param locale String
     * @return addressLocation AddressLocation
     */
    @Override
    public AddressLocation convertFromLatLong(double dLat, double dLon, String locale) throws GeorgyException {
        final AddressGoogle addressGoogle = getAddressGoogle(dLat, dLon, locale, URL);

        final AddressLocation addressLocation = new AddressLocation();
        final Address address = new Address();

        address.setCountry(addressGoogle.getCountry());
        address.setSubdivision(addressGoogle.getAdministrative_area_level_1());
        address.setCity(addressGoogle.getLocality());
        address.setCountryIsoCode(addressGoogle.getCountry_short());
        address.setSubdivisionIsoCode(addressGoogle.getAdministrative_area_level_1_short());
        address.setPostalCode(addressGoogle.getPostal_code());
        addressLocation.setAddress(address);
        addressLocation.setLocation(new GeoLocation(dLat, dLon));

        return addressLocation;

    }

}