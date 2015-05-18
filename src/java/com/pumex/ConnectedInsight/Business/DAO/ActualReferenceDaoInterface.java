/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.DAO;

import com.pumex.ConnectedInsight.Business.beans.BenchmarkFormBean;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface  ActualReferenceDaoInterface 
{  
    public void addActualReference(BenchmarkFormBean benchmarkFormBean,UserBean userBean);
    
    public BenchmarkFormBean getActualReferencerDetails(final BenchmarkFormBean benchmarkFormBean,Integer parameterId);
    
    public BenchmarkFormBean getSearchresult(BenchmarkFormBean benchmarkFormBean);
    
    public Map getYear();
    
    public Map getdatelist();
    
    public Map getActalValueList(BenchmarkFormBean benchmarkFormBean);
    
    public List setVolumeDescription(String center,String date,String parameter,String status);
    
    public Map getRevenueList(BenchmarkFormBean benchmarkFormBean);
    
    public Map getVolumeList(BenchmarkFormBean benchmarkFormBean);
}
