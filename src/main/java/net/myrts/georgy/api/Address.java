package net.myrts.georgy.api;

/**
 * Container for address.
 *
 * @author <a href="mailto:vzhovtiuk@gmail.com">Vitaliy Zhovtyuk</a>
 *         Date: 10/19/15
 *         Time: 7:38 PM
 */
public class Address {
    // for all providers
    private String country;
    private String postalCode;

    //  for MaxMind provider
    private String countryIsoCode;
    private String city;
    private String subdivision;
    private String subdivisionIsoCode;

    // for Google provider
    private String streetNumber;
    private String route;
    private String locality;
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

    @Override
    public String toString() {
        return "AddressLocation{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", countryIsoCode='" + countryIsoCode + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", subdivision='" + subdivision + '\'' +
                ", subdivisionIsoCode='" + subdivisionIsoCode + '\'' +
                '}';
    }

    public String toStringGoogle() {
        return  streetNumber + ", " +
                route + ", " +
                locality + ", " +
                administrativeAreaLevel_2 + ", " +
                administrativeAreaLevel_1 + ", " +
                country + ", " +
                postalCode;
    }
}
