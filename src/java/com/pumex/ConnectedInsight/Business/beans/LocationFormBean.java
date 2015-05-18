package com.pumex.ConnectedInsight.Business.beans;

/**
 *
 * @author user
 */
public class LocationFormBean {

    String name;
    String shortName;
    Integer country;
    String city;
    Integer organization;
    Integer locationid;
    Integer[] businesses;
    Integer[] client;

    public Integer[] getClient() {
        return client;
    }

    public void setClient(Integer[] client) {
        this.client = client;
    }
    
    public Integer[] getBusinesses() {
        return businesses;
    }

    public void setBusinesses(Integer[] businesses) {
        this.businesses = businesses;
    }

    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrganization() {
        return organization;
    }

    public void setOrganization(Integer organization) {
        this.organization = organization;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
