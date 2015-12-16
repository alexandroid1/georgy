package net.myrts.georgy.api;

import com.google.api.client.repackaged.com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by georgy on 15.12.15.
 */
public class AddressGoogle {

    private ArrayList<String> addressKeys;
    private ArrayList<String> addressValues;
    private HashMap<String, String> addressSettingsMap;

    private String country;
    private String postalCode;
    private String countryIsoCode;
    private String city;
    private String subdivision;
    private String subdivisionIsoCode;
    private String streetNumber;
    private String route;
    private String locality;
    private String sublocalityLevel1;
    private String sublocalityLevel2;
    private String sublocalityLevel3;
    private String administrativeAreaLevel_3;
    private String administrativeAreaLevel_2;
    private String administrativeAreaLevel_1;

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
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

    public String getSublocalityLevel3() {
        return sublocalityLevel3;
    }

    public void setSublocalityLevel3(String sublocalityLevel3) {
        this.sublocalityLevel3 = sublocalityLevel3;
    }

    public String getSublocalityLevel2() {
        return sublocalityLevel2;
    }

    public void setSublocalityLevel2(String sublocalityLevel2) {
        this.sublocalityLevel2 = sublocalityLevel2;
    }

    public String getSublocalityLevel1() {
        return sublocalityLevel1;
    }

    public void setSublocalityLevel1(String sublocalityLevel1) {
        this.sublocalityLevel1 = sublocalityLevel1;
    }

    public String getAdministrativeAreaLevel_3() {
        return administrativeAreaLevel_3;
    }

    public void setAdministrativeAreaLevel_3(String administrativeAreaLevel_3) {
        this.administrativeAreaLevel_3 = administrativeAreaLevel_3;
    }

    public String getAdministrativeAreaLevel_2() {
        return administrativeAreaLevel_2;
    }

    public void setAdministrativeAreaLevel_2(String administrativeAreaLevel_2) {
        this.administrativeAreaLevel_2 = administrativeAreaLevel_2;
    }

    public String getAdministrativeAreaLevel_1() {
        return administrativeAreaLevel_1;
    }

    public void setAdministrativeAreaLevel_1(String administrativeAreaLevel_1) {
        this.administrativeAreaLevel_1 = administrativeAreaLevel_1;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    @Override
    public String toString() {
        return Joiner.on("|").skipNulls().withKeyValueSeparator(" -> ").join(addressSettingsMap);
    }

}
