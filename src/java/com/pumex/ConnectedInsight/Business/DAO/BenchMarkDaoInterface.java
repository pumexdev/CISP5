/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.DAO;

import com.pumex.ConnectedInsight.Business.beans.BenchmarkFormBean;
import com.pumex.ConnectedInsight.Business.beans.VolumeFormBean;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vishnu
 */
@Repository
public interface BenchMarkDaoInterface
{

    public Boolean saveBenchmark(BenchmarkFormBean benchmarkFormBean);

    public BenchmarkFormBean getBenchmarkData(final BenchmarkFormBean benchmarkFormBean,String bid);

    public void updateBenchmark(BenchmarkFormBean benchmarkFormBean,String processid);    

    public void deleteBenchmark(String BmId);

    public List getBenchMarkList();

    public List getBenchMarkEditList(BenchmarkFormBean benchmarkFormBean);
    
    public Map getBecnchmarkNameList();
    
    public Map getLocationlist();
    
    public Map getYear();
    
    public void addVolumedescription(VolumeFormBean volumeFormBean);
    
    public Map getVlolumedescriptionlist();
    
    public List getDescriptionlist(String descriptionid);
    
    public void editVolumedescription(VolumeFormBean volumeFormBean);
}