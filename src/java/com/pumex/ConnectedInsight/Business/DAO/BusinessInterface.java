/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.DAO;

import com.pumex.ConnectedInsight.Business.beans.*;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface BusinessInterface
{
    public void saveBusiness(BusinessFormBean businessFormBean);
    
    public BusinessFormBean getBusinessData(Integer businessId, BusinessFormBean businessFormBean);

    public void editBusiness(BusinessFormBean businessFormBean);
    
    public void saveBusinessUnit(BusinessFormBean businessFormBean);
    
    public BusinessFormBean getBusinessUnitData(BusinessFormBean businessFormBean,Integer buid);

    public void editBusinessUnit(BusinessFormBean businessFormBean);

    public List searchBusiness(BusinessFormBean businessFormBean);

    public Map getBusiness();
    
    public Map getBusinessUnitsNames();

    public Map getBusinessUnits(Integer businessId);
    
    public Map getLocations(Integer businessid);
    
    public Boolean checkBusinessName(String businessName);
    
    public Boolean checkBusinessUnitName(String businessUnitName);
    
    public List getclientlist(BusinessFormBean businessFormBean);
    
    public Map getSubProcessList();
            
}
