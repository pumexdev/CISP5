/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.DAO;

import java.sql.ResultSet;
import com.pumex.ConnectedInsight.Business.beans.BenchmarkFormBean;
import com.pumex.ConnectedInsight.Business.beans.VolumeFormBean;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vishnu
 */
@Repository
public class BenchMarkDaoImpl implements BenchMarkDaoInterface
{
    @Autowired
    DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CommonDaoInterface commonDaoInterface;

    @Override
    public Boolean saveBenchmark(BenchmarkFormBean benchmarkFormBean)
    {
        Boolean flag = true;
        try
        {
            String query = SQLSelector.getQuery("benchmark.insert");
            jdbcTemplate.update(query, new Object[]{benchmarkFormBean.getBusiness(), benchmarkFormBean.getBusinessunit(), benchmarkFormBean.getProcess(), benchmarkFormBean.getSubprocess(), benchmarkFormBean.getBusinesscenter(), benchmarkFormBean.getFromdate(), benchmarkFormBean.getTodate(), benchmarkFormBean.getParamtypecode(), benchmarkFormBean.getTop(), benchmarkFormBean.getMedian(), benchmarkFormBean.getLow(), benchmarkFormBean.getStatus()});
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public BenchmarkFormBean getBenchmarkData(final BenchmarkFormBean benchmarkFormBean, String benchMarkId)
    {
        String query = SQLSelector.getQuery("benchmark.getbenchmarkdata");
        return (BenchmarkFormBean) jdbcTemplate.queryForObject(query, new Object[]{benchMarkId}, new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException
            {
                benchmarkFormBean.setParamtypecode(rs.getInt("ParamTypeCode"));
                benchmarkFormBean.setBusiness(rs.getInt("BusinessId"));
                benchmarkFormBean.setBusinessunit(rs.getInt("BusinessUnitId"));
                benchmarkFormBean.setBusinesscenter(rs.getInt("BusinessCenterId"));
                benchmarkFormBean.setProcess(rs.getInt("ProcessId"));
                benchmarkFormBean.setSubprocess(rs.getInt("SubProcessId"));
                benchmarkFormBean.setFromdate(rs.getString("FromDate"));
                benchmarkFormBean.setTodate(rs.getString("ToDate"));
                benchmarkFormBean.setTop(rs.getDouble("TopValue"));
                benchmarkFormBean.setMedian(rs.getDouble("MedianValue"));
                benchmarkFormBean.setLow(rs.getDouble("LowValue"));
                benchmarkFormBean.setStatus(rs.getInt("Status"));
                return benchmarkFormBean;
            }
        });
    }

    public void updateBenchmark(BenchmarkFormBean benchmarkFormBean, String benchMarkId)
    {
        try
        {
            String query = SQLSelector.getQuery("benchmark.edit");
            jdbcTemplate.update(query, new Object[]{benchmarkFormBean.getBusiness(), benchmarkFormBean.getBusinessunit(), benchmarkFormBean.getBusinesscenter(), benchmarkFormBean.getProcess(), benchmarkFormBean.getSubprocess(), benchmarkFormBean.getParamtypecode(), benchmarkFormBean.getTop(), benchmarkFormBean.getMedian(), benchmarkFormBean.getLow(), benchmarkFormBean.getStatus(), benchMarkId});
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void deleteBenchmark(String benchMarkId)
    {
        try
        {
            jdbcTemplate.update(SQLSelector.getQuery("benchmark.delete"), new Object[]{benchMarkId});
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public List getBenchMarkList()
    {
        List benchmarkList = new ArrayList();
        try
        {
            String query = SQLSelector.getQuery("benchmark.search");
            benchmarkList = jdbcTemplate.queryForList("");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return benchmarkList;
    }

    public List getBenchMarkEditList(BenchmarkFormBean benchmarkFormBean)
    {
        List benchmarkList = new ArrayList();
        String date;
        date = benchmarkFormBean.getFromdate();
        date += benchmarkFormBean.getTodate();
        benchmarkList = jdbcTemplate.queryForList(SQLSelector.getQuery("benchmark.getbenchmarkList"), new Object[]{benchmarkFormBean.getParamtypecode(), benchmarkFormBean.getBusiness(), benchmarkFormBean.getBusinessunit(), benchmarkFormBean.getBusinesscenter(), date});
        return benchmarkList;
    }

    public Map getBecnchmarkNameList()
    {
        String query = SQLSelector.getQuery("paramtype.getname");
        return commonDaoInterface.sortMapByKey(commonDaoInterface.queryForMap(query));
    }

    public Map getLocationlist()
    {
        String query = SQLSelector.getQuery("location.getlocations");
        return commonDaoInterface.queryForMap(query);
    }

    public Map getYear()
    {
        String query = SQLSelector.getQuery("benchmark.getyear");
        return commonDaoInterface.queryForDates(query);
    }
    public void addVolumedescription(VolumeFormBean volumeFormBean)
    {
        String query=SQLSelector.getQuery("volumedescription.insert");
        jdbcTemplate.update(query,new Object[]{volumeFormBean.getSubprocess(),volumeFormBean.getDescription(),volumeFormBean.getStatus()});
    }
    public Map getVlolumedescriptionlist()
    {
        String query=SQLSelector.getQuery("volumedescription.getlist");
        return commonDaoInterface.sortMapByValue(commonDaoInterface.queryForMap(query));
       
    }
    public List getDescriptionlist(String descriptionid)
    {
        String query=SQLSelector.getQuery("volumedescription.getdata");
        return jdbcTemplate.queryForList(query,new Object[]{descriptionid});
    }
    public void editVolumedescription(VolumeFormBean volumeFormBean)
    {
        String query=SQLSelector.getQuery("volumedescription.edit");
        jdbcTemplate.update(query,new Object[]{volumeFormBean.getSubprocess(),volumeFormBean.getDescription(),volumeFormBean.getStatus(),volumeFormBean.getDescriptionid()});
    }
}
