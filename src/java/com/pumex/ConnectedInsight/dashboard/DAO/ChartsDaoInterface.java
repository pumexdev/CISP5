/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.dashboard.DAO;

import com.pumex.ConnectedInsight.dashboard.beans.BenchMarkDataBean;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mjons
 */
public interface ChartsDaoInterface
{
    public List<ChartDataBean> getChartsData(String query,Object...params);
    
    public List<BenchMarkDataBean> getBenchMarkData(String query,Object...params);
}
