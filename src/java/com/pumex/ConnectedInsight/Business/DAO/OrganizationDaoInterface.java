/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.DAO;

import org.springframework.stereotype.Repository;
import com.pumex.ConnectedInsight.Business.beans.*;
import java.util.List;
/**
 *
 * @author Vishnu
 */
@Repository
public interface OrganizationDaoInterface {
    
     public void saveOrganization(OrganizationFormBean organizationFormBean);
     
     public OrganizationFormBean getOrganization(Integer oid,OrganizationFormBean organizationFormBean);
     
     public void updateOrganization(OrganizationFormBean organizationFormBean);
     
     public List searchorg(SearchOrganizationFormBean searchOrganzsationFormBean);    
    
    
}
