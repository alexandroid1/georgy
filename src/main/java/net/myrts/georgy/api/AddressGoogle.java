package net.myrts.georgy.api;

import com.google.api.client.repackaged.com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by georgy on 15.12.15.
 */
public class AddressGoogle {

    private ArrayList<String> addressKeys;
    private ArrayList<String> addressValues;
    private HashMap<String, String> addressSettingsMap;

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

    public HashMap<String, String> getAddressSettingsMap() {
        return addressSettingsMap;
    }

    public void setAddressSettingsMap(HashMap<String, String> addressSettingsMap) {
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

    @Override
    public String toString() {
        return Joiner.on(" | ").
                withKeyValueSeparator(" -> ")
                .useForNull("no such value").
                join(addressSettingsMap);
    }

}
