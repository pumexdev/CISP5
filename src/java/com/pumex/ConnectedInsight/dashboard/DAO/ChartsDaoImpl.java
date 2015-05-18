package com.pumex.ConnectedInsight.dashboard.DAO;

import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.dashboard.beans.BenchMarkDataBean;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**Class for querying and creating the data for plotting the graphs
 * @author mjons
 */
@Repository
public class ChartsDaoImpl implements ChartsDaoInterface
{
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CommonDaoInterface commonDaoInterface;
    
    /**Returns a list containing the data for plotting the Google charts
     * @param query query String
     * @param params Conditional parameters used in the query
     * @return List<ChartDataBean>
     */
    @Override
    public List<ChartDataBean> getChartsData(String query,Object...params)
    {
        List<ChartDataBean>chartData=new ArrayList<ChartDataBean>();
        chartData=jdbcTemplate.query(query, params, new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException
            {
                ChartDataBean chartDataBean=new ChartDataBean();
                chartDataBean.setyValue(rs.getDouble(1));
                try
                {
                    chartDataBean.setxValue(rs.getString(2));
                }
                catch(Exception ex)
                {
                    chartDataBean.setxValue("");
                }
                return chartDataBean;
            }
        });
        return chartData;
    }
    
    /**Not used currently
     * @param query
     * @param params
     * @return 
     */
    @Override
    public List<BenchMarkDataBean> getBenchMarkData(String query,Object...params)
    {
        List<BenchMarkDataBean>benchMarkData=new ArrayList<BenchMarkDataBean>();
        benchMarkData=jdbcTemplate.query(query, params, new RowMapper()
        {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException
            {
                BenchMarkDataBean benchMarkDataBean=new BenchMarkDataBean();
                benchMarkDataBean.setxBenchMarkValue(rs.getString(2));
                benchMarkDataBean.setyBenchMarkValue(rs.getDouble(1));
                return benchMarkDataBean;
            }
        });
        return benchMarkData;
    }
    
    
}
