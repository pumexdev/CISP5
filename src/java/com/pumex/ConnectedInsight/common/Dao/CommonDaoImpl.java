package com.pumex.ConnectedInsight.common.Dao;

import com.pumex.ConnectedInsight.dashboard.DAO.ChartsDaoInterface;
import com.pumex.ConnectedInsight.dashboard.beans.ChartDataBean;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vishnu
 */
@Repository
public class CommonDaoImpl implements CommonDaoInterface {

    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ChartsDaoInterface chartsDaoInterface;

    @Override
    public Map queryForMap(String query, Object... objects) {
        try {
            Map resultMap = new LinkedHashMap();
            resultMap = (Map) jdbcTemplate.query(query, new ResultSetExtractor() {
                @Override
                public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                    Map map = new LinkedHashMap();
                    while (rs.next()) {
                        map.put(rs.getString(1), rs.getString(2));
                    }
                    return map;
                }
            }, objects);
            return resultMap;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new HashMap();
        }
    }

    @Override
    public Map queryForMap(String query) {
        try {
            Map resultMap = new LinkedHashMap();
            resultMap = (Map) jdbcTemplate.query(query, new ResultSetExtractor() {
                @Override
                public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                    Map map = new LinkedHashMap();
                    while (rs.next()) {
                        map.put(rs.getString(1), rs.getString(2));
                    }
                    return map;
                }
            });
            return resultMap;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new HashMap();
        }
    }

    @Override
    public Object queryForObject(String query, Object... objects) {
        return jdbcTemplate.queryForObject(query, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getObject(1);
            }
        }, objects);
    }

    @Override
    public Object queryForObject(String query) {
        return jdbcTemplate.queryForObject(query, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getObject(1);
            }
        });
    }

    @Override
    public Map queryForDates(String query, Object... objects) {
        try {
            Map resultMap = new LinkedHashMap();
            resultMap = (Map) jdbcTemplate.query(query, new ResultSetExtractor() {
                @Override
                public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                    Map map = new LinkedHashMap();
                    while (rs.next()) {
                        map.put(rs.getString(1), rs.getString(1));
                    }
                    return map;
                }
            }, objects);
            return resultMap;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new HashMap();
        }
    }

    @Override
    public Map queryForDates(String query) {
        try {
            Map resultMap = new LinkedHashMap();
            resultMap = (Map) jdbcTemplate.query(query, new ResultSetExtractor() {
                @Override
                public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                    Map map = new LinkedHashMap();
                    while (rs.next()) {
                        map.put(rs.getString(1), rs.getString(1));
                    }
                    return map;
                }
            });
            return resultMap;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new HashMap();
        }
    }

    @Override
    public Map sortMapByValue(Map map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    @Override
    public Map sortMapByKey(Map map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getKey()).compareTo(((Map.Entry) (o2)).getKey());
            }
        });

        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    @Override
    public Map sortMapByValue(Map map, final Integer sortorder) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (new Integer(1).equals(sortorder))//ascending
                {
                    return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
                } else//descending
                {
                    return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
                }
            }
        });

        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    @Override
    public Map sortMapByKey(Map map, final Integer sortorder) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (new Integer(1).equals(sortorder)) {
                    return ((Comparable) ((Map.Entry) (o1)).getKey()).compareTo(((Map.Entry) (o2)).getKey());
                } else//descending
                {
                    return ((Comparable) ((Map.Entry) (o2)).getKey()).compareTo(((Map.Entry) (o1)).getKey());
                }
            }
        });

        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    @Override
    public Map sortHashMapByKey(Map map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Entry key1 = (Map.Entry) (o1);
                Integer key11 = Integer.parseInt(String.valueOf(key1.getKey()));
                Entry key2 = (Map.Entry) (o2);
                Integer key21 = Integer.parseInt(String.valueOf(key2.getKey()));
                return key11.compareTo(key21);
                //return ((Comparable) ((Map.Entry) (o1)).getKey()).compareTo(((Map.Entry) (o2)).getKey());
            }
        });
        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    @Override
    public Map<Comparable, List<ChartDataBean>> getChartMap(HttpServletRequest request) {
        Map<Comparable, List<ChartDataBean>> chartMap = new LinkedHashMap<Comparable, List<ChartDataBean>>();
        String graphNo = request.getParameter("graphNo");
        String queryFromDB = SQLSelector.getQuery("queryFromDB");
        String query = (String) queryForObject(queryFromDB, graphNo);
        List<String> parameterList = new ArrayList<String>();
        String getParams = SQLSelector.getQuery("getQueryParams");
        parameterList = jdbcTemplate.query(getParams, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString("paramname");
            }
        }, graphNo);
        Object[] params = null;
        List paramList = new ArrayList();
        if (parameterList != null && !parameterList.isEmpty()) {
            Map requestparams = request.getParameterMap();
            String[] dates = (String[]) requestparams.get("date[]");
            if (dates != null && dates.length > 0) {
                for (Integer i = 0; i < dates.length; ++i) {
                    Iterator iterator = parameterList.iterator();
                    paramList = new ArrayList();
                    while (iterator.hasNext()) {
                        String attributeName = String.valueOf(iterator.next());
                        Object object = request.getParameter(attributeName);
                        if (attributeName.equals("date[]")) {
                            paramList.add(dates[i]);
                        } else if (object != null) {
                            paramList.add(object);
                        }
                    }
                    params = paramList.toArray();
                    List<ChartDataBean> chartData = new ArrayList<ChartDataBean>();
                    chartData = chartsDaoInterface.getChartsData(query, params);
                    chartMap.put(dates[i], chartData);
                }
            }
        }
        return chartMap;
    }

    @Override
    public void clearBean(Object source) {
        if (source != null) {
            try {
                Map attribute = BeanUtils.describe(source);
                Set set = attribute.entrySet();
                Iterator setIterator = set.iterator();
                while (setIterator.hasNext()) {
                    Entry entry = (Map.Entry) setIterator.next();
                    String key = String.valueOf(entry.getKey());
                    BeanUtils.setProperty(source, key, null);
                }
            } catch (Exception ex) {

            }

        }
    }
}
