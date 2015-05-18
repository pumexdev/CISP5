package com.pumex.ConnectedInsight.Business.beans;

/**
 *
 * @author user
 */
public class BusinessFormBean {
    /*Business and Common*/

    String name;
    String shortName;
    Integer organization;
    Integer status;
    
    /*Business and Common*/
    /*Business Unit*/
    Integer business;
    Integer[] locations;
    Integer location;
    /*Business Unit*/
    
    /*Business SubUnit*/
    Integer businessunit;
    /*Business SubUnit*/

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    
    public Integer[] getLocations() {
        return locations;
    }

    public void setLocations(Integer[] locations) {
        this.locations = locations;
    }
        
    public Integer getBusiness() {
        return business;
    }

    public void setBusiness(Integer business) {
        this.business = business;
    }

    public Integer getBusinessunit() {
        return businessunit;
    }

    public void setBusinessunit(Integer businessunit) {
        this.businessunit = businessunit;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
