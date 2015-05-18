/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.DAO;

import java.util.List;
import com.pumex.ConnectedInsight.Business.beans.*;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vishnu
 */
@Repository
public interface LocationDaoInterface
{

    public List searchlocation(LocationFormBean lLocationFormBean);

    public void saveLocation(LocationFormBean locationFormBean,UserBean userBean);

    public void updateLocation(LocationFormBean locationFormBean,UserBean userBean);

    public LocationFormBean getLocation(String lid);
    
    public Map getBusinessCenter(Integer organizationid);
    
    public Boolean checkLocationName(String locationName);
    
    public Map getBusinessUnitByLocation(Integer location);
    
    public Map getCenterlist();
    
}
