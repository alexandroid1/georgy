package net.myrts.georgy.google.stubsConvertFromLatLong;

import com.google.api.client.repackaged.com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author <a href="mailto:avpavlov108@gmail.com">Oleksandr Pavlov</a>
 */
public class AddressGoogle {

    private ArrayList<String> addressKeys;
    private ArrayList<String> addressValues;
    private Map<String, String> addressSettingsMap;

    private String country;
    private String postal_code;
    private String countryIsoCode;
    private String city;
    private String subdivision;
    private String subdivisionIsoCode;
    private String street_number;
    private String route;
    private String locality;
    private String sublocality_level_1;
    private String sublocality_level_2;
    private String sublocality_level_3;
    private String administrative_area_level_3;
    private String administrative_area_level_2;
    private String administrative_area_level_1;
    private String point_of_interest;
    private String premise;

    private String country_short;
    private String postal_code_short;
    private String countryIsoCode_short;

    private String city_short;
    private String subdivision_short;
    private String subdivisionIsoCode_short;
    private String street_number_short;
    private String route_short;
    private String locality_short;
    private String sublocality_level_1_short;
    private String sublocality_level_2_short;
    private String sublocality_level_3_short;
    private String administrative_area_level_3_short;
    private String administrative_area_level_2_short;
    private String administrative_area_level_1_short;
    private String point_of_interest_short;
    private String premise_short;

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getSublocality_level_3() {
        return sublocality_level_3;
    }

    public void setSublocality_level_3(String sublocality_level_3) {
        this.sublocality_level_3 = sublocality_level_3;
    }

    public String getSublocality_level_2() {
        return sublocality_level_2;
    }

    public void setSublocality_level_2(String sublocality_level_2) {
        this.sublocality_level_2 = sublocality_level_2;
    }

    public String getSublocality_level_1() {
        return sublocality_level_1;
    }

    public void setSublocality_level_1(String sublocality_level_1) {
        this.sublocality_level_1 = sublocality_level_1;
    }

    public String getAdministrative_area_level_3() {
        return administrative_area_level_3;
    }

    public void setAdministrative_area_level_3(String administrative_area_level_3) {
        this.administrative_area_level_3 = administrative_area_level_3;
    }

    public String getAdministrative_area_level_2() {
        return administrative_area_level_2;
    }

    public void setAdministrative_area_level_2(String administrative_area_level_2) {
        this.administrative_area_level_2 = administrative_area_level_2;
    }

    public String getAdministrative_area_level_1() {
        return administrative_area_level_1;
    }

    public void setAdministrative_area_level_1(String administrative_area_level_1) {
        this.administrative_area_level_1 = administrative_area_level_1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getSubdivisionIsoCode() {
        return subdivisionIsoCode;
    }

    public void setSubdivisionIsoCode(String subdivisionIsoCode) {
        this.subdivisionIsoCode = subdivisionIsoCode;
    }

    public ArrayList<String> getAddressKeys() {
        return addressKeys;
    }

    public void setAddressKeys(ArrayList<String> addressKeys) {
        this.addressKeys = new ArrayList<>(addressKeys);
    }

    public ArrayList<String> getAddressValues() {
        return addressValues;
    }

    public void setAddressValues(ArrayList<String> addressValues) {
        this.addressValues = new ArrayList<>(addressValues);
    }

    public Map<String, String> getAddressSettingsMap() {
        return addressSettingsMap;
    }

    public void setAddressSettingsMap(Map<String, String> addressSettingsMap) {
        this.addressSettingsMap = addressSettingsMap;
    }

    public String getPoint_of_interest() {
        return point_of_interest;
    }

    public void setPoint_of_interest(String point_of_interest) {
        this.point_of_interest = point_of_interest;
    }

    public String getPremise() {
        return premise;
    }

    public void setPremise(String premise) {
        this.premise = premise;
    }

    public String getCountry_short() {
        return country_short;
    }

    public void setCountry_short(String country_short) {
        this.country_short = country_short;
    }

    public String getPostal_code_short() {
        return postal_code_short;
    }

    public void setPostal_code_short(String postal_code_short) {
        this.postal_code_short = postal_code_short;
    }

    public String getCountryIsoCode_short() {
        return countryIsoCode_short;
    }

    public void setCountryIsoCode_short(String countryIsoCode_short) {
        this.countryIsoCode_short = countryIsoCode_short;
    }

    public String getCity_short() {
        return city_short;
    }

    public void setCity_short(String city_short) {
        this.city_short = city_short;
    }

    public String getSubdivision_short() {
        return subdivision_short;
    }

    public void setSubdivision_short(String subdivision_short) {
        this.subdivision_short = subdivision_short;
    }

    public String getSubdivisionIsoCode_short() {
        return subdivisionIsoCode_short;
    }

    public void setSubdivisionIsoCode_short(String subdivisionIsoCode_short) {
        this.subdivisionIsoCode_short = subdivisionIsoCode_short;
    }

    public String getStreet_number_short() {
        return street_number_short;
    }

    public void setStreet_number_short(String street_number_short) {
        this.street_number_short = street_number_short;
    }

    public String getRoute_short() {
        return route_short;
    }

    public void setRoute_short(String route_short) {
        this.route_short = route_short;
    }

    public String getLocality_short() {
        return locality_short;
    }

    public void setLocality_short(String locality_short) {
        this.locality_short = locality_short;
    }

    public String getSublocality_level_1_short() {
        return sublocality_level_1_short;
    }

    public void setSublocality_level_1_short(String sublocality_level_1_short) {
        this.sublocality_level_1_short = sublocality_level_1_short;
    }

    public String getSublocality_level_2_short() {
        return sublocality_level_2_short;
    }

    public void setSublocality_level_2_short(String sublocality_level_2_short) {
        this.sublocality_level_2_short = sublocality_level_2_short;
    }

    public String getSublocality_level_3_short() {
        return sublocality_level_3_short;
    }

    public void setSublocality_level_3_short(String sublocality_level_3_short) {
        this.sublocality_level_3_short = sublocality_level_3_short;
    }

    public String getAdministrative_area_level_3_short() {
        return administrative_area_level_3_short;
    }

    public void setAdministrative_area_level_3_short(String administrative_area_level_3_short) {
        this.administrative_area_level_3_short = administrative_area_level_3_short;
    }

    public String getAdministrative_area_level_2_short() {
        return administrative_area_level_2_short;
    }

    public void setAdministrative_area_level_2_short(String administrative_area_level_2_short) {
        this.administrative_area_level_2_short = administrative_area_level_2_short;
    }

    public String getAdministrative_area_level_1_short() {
        return administrative_area_level_1_short;
    }

    public void setAdministrative_area_level_1_short(String administrative_area_level_1_short) {
        this.administrative_area_level_1_short = administrative_area_level_1_short;
    }

    public String getPoint_of_interest_short() {
        return point_of_interest_short;
    }

    public void setPoint_of_interest_short(String point_of_interest_short) {
        this.point_of_interest_short = point_of_interest_short;
    }

    public String getPremise_short() {
        return premise_short;
    }

    public void setPremise_short(String premise_short) {
        this.premise_short = premise_short;
    }

    @Override
    public String toString() {
        return Joiner.on(" | ").
                withKeyValueSeparator(" -> ")
                .useForNull("no such value").
                join(addressSettingsMap);
    }


    /* final String addressCompare = "sublocality_level_2 -> Apollo Bandar " +
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
                "| postal_code -> 400001";*/

    /*
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
                Joiner.on(", ").useForNull("No value").join(addressGoogle.getAddressValues()));*/


    /*-----------------------------------------*/

           /* final String addressCompare = "sublocality_level_1 -> Kyivs'kyi district " +
                "| country -> Ukraine " +
                "| route -> Chelyuskintsiv Street " +
                "| administrative_area_level_3 -> Donets'ka city council " +
                "| administrative_area_level_1 -> Donetsk Oblast " +
                "| street_number -> 189 " +
                "| locality -> Donetsk " +
                "| postal_code -> 83000";*/

     /*assertEquals(addressCompare, addressGoogle.toString());*/

        /*assertEquals(addressCompare,
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
                Joiner.on(", ").useForNull("No value").join(addressGoogle.getAddressValues()));*/

}
