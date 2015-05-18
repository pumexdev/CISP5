/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import com.pumex.ConnectedInsight.Excel.Bean.ExcelExportBean;
import com.pumex.ConnectedInsight.Excel.Bean.ExportEmployeeBUBean;
import com.pumex.ConnectedInsight.Excel.Bean.ExportEmployeeFormBean;
import com.pumex.ConnectedInsight.Excel.Bean.ExportEntryFormBean;
import com.pumex.ConnectedInsight.Excel.Bean.MasterDataBean;
import com.pumex.ConnectedInsight.Property.PropertyFile;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.common.bean.ListBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.sql.DataSource;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vishhhnu
 */
@Repository
public class ExcelExporterDaoImpl implements ExcelExporterDao {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CommonDaoInterface commondaointerface;
    private Map buMap;

    @Override
    public Workbook writeToExcel(Workbook wb, String date, String date1, String sql_query, Integer colum_start, String xtitle, Object[] obj) {
        sql_query = sql_query.replaceAll("date1", date);
        sql_query = sql_query.replaceAll("date2", date1);
        List<ExcelExportBean> excel_Export_list = getDataTwoDate(sql_query, obj);
        Sheet sheet = wb.getSheetAt(1);
        Row row = sheet.getRow(0);
        if (row == null) {
            sheet.createRow(0);
        }
        Cell cell = row.getCell(colum_start);
        if (cell == null) {
            cell = row.createCell(colum_start);
        }
        cell.setCellValue(xtitle);
        cell = row.getCell(colum_start + 1);
        if (cell == null) {
            cell = row.createCell(colum_start + 1);
        }
        cell.setCellValue(date);
        cell = row.getCell(colum_start + 2);
        if (cell == null) {
            cell = row.createCell(colum_start + 2);
        }
        cell.setCellValue(date1);
        Iterator it = excel_Export_list.iterator();
        int i = 1;
        while (it.hasNext()) {
            ExcelExportBean excelExportBean = (ExcelExportBean) it.next();
            row = sheet.getRow(i);
            if (row == null) {
                row = sheet.createRow(i);
            }
            cell = row.getCell(colum_start);
            if (cell == null) {
                cell = row.createCell(colum_start);
            }
            cell.setCellValue(excelExportBean.getX());
            cell = row.getCell(colum_start + 1);
            if (cell == null) {
                cell = row.createCell(colum_start + 1);
            }
            cell.setCellValue(excelExportBean.getY());
            cell = row.getCell(colum_start + 2);
            if (cell == null) {
                cell = row.createCell(colum_start + 2);
            }
            cell.setCellValue(excelExportBean.getY1());
            i++;
        }
        return wb;
    }

    public List<ExcelExportBean> getDataTwoDate(String sql_query, Object[] obj) {
        List<ExcelExportBean> excel_export_list = jdbcTemplate.query(sql_query, new RowMapper() {
            @Override
            public ExcelExportBean mapRow(ResultSet rs, int i) throws SQLException {
                ExcelExportBean excelExportBean = new ExcelExportBean();
                excelExportBean.setX(rs.getString(1));
                excelExportBean.setY(rs.getDouble(2));
                excelExportBean.setY1(rs.getDouble(3));
                return excelExportBean;
            }
        }, obj);
        return excel_export_list;
    }

    @Override
    public Workbook writeToExcel(Workbook wb, String date, String sql_query, Integer colum_start, String xtitle, Object[] obj) {
        List<ExcelExportBean> excel_Export_list = getDataTwoDate(sql_query, date, obj);
        Sheet sheet = wb.getSheetAt(1);
        Row row = sheet.getRow(0);
        if (row == null) {
            sheet.createRow(0);
        }
        Cell cell = row.getCell(colum_start);
        if (cell == null) {
            cell = row.createCell(colum_start);
        }
        cell.setCellValue(xtitle);
        cell = row.getCell(colum_start + 1);
        if (cell == null) {
            cell = row.createCell(colum_start + 1);
        }
        cell.setCellValue(date);
        Iterator it = excel_Export_list.iterator();
        int i = 1;
        while (it.hasNext()) {
            ExcelExportBean excelExportBean = (ExcelExportBean) it.next();
            row = sheet.getRow(i);
            if (row == null) {
                row = sheet.createRow(i);
            }
            cell = row.getCell(colum_start);
            if (cell == null) {
                cell = row.createCell(colum_start);
            }
            cell.setCellValue(excelExportBean.getX());
            cell = row.getCell(colum_start + 1);
            if (cell == null) {
                cell = row.createCell(colum_start + 1);
            }
            cell.setCellValue(excelExportBean.getY());
            i++;
        }
        return wb;
    }

    public List<ExcelExportBean> getDataTwoDate(String sql_query, String date, Object[] obj) {
        List<ExcelExportBean> excel_export_list = jdbcTemplate.query(sql_query, new RowMapper() {
            @Override
            public ExcelExportBean mapRow(ResultSet rs, int i) throws SQLException {
                ExcelExportBean excelExportBean = new ExcelExportBean();
                excelExportBean.setX(rs.getString(1));
                excelExportBean.setY(rs.getDouble(2));
                return excelExportBean;
            }
        }, obj);
        return excel_export_list;
    }

    @Override
    public Workbook setExcelSheet(Workbook wb, String bc_id, String date) {
        ExportEntryFormBean exportEntryFormBean = setEntryFormBean(bc_id, date);
        wb = writeEntryFormBean(exportEntryFormBean, wb);
        List buList = getBUList(bc_id);
        wb = writeBusinessUnit(wb, buList);
        //List employee_List=setEmpoyeeFormBean(exportEntryFormBean.getEntry_id());
        List employee_List = setEmpoyeeFormBean(exportEntryFormBean.getEntry_id(), bc_id);
        wb = writeEmployeedata(wb, employee_List);
        Double total = getTotal(employee_List);
        wb = setTotal(wb, total);
        wb = setProcess(wb);
        wb = setSubProcess(wb);
        return wb;
    }

    @Override
    public ExportEntryFormBean setEntryFormBean(String bc_id, String date) {
        ExportEntryFormBean exportEntryFormBean = new ExportEntryFormBean();
        List l = (List) jdbcTemplate.query(SQLSelector.getQuery("excelexport.hrdataentry.select"), new RowMapper() {
            @Override
            public ExportEntryFormBean mapRow(ResultSet rs, int i) throws SQLException {

                ExportEntryFormBean exportEntryFormBean = new ExportEntryFormBean();
                exportEntryFormBean.setEntry_id(rs.getString(1));
                exportEntryFormBean.setBusiness_center(rs.getString(2));
                exportEntryFormBean.setEntry_date(rs.getString(3));
                return exportEntryFormBean;


            }
        }, new Object[]{bc_id, date});
        if (l.size() == 1) {
            exportEntryFormBean = (ExportEntryFormBean) l.get(0);
        }
        return exportEntryFormBean;
    }

    @Override
    public Workbook writeEntryFormBean(ExportEntryFormBean exportEntryBean, Workbook wb) {
        wb = writeStringData(wb, PropertyFile.excelInt("resource.input.businesscenter.rowIndex"), PropertyFile.excelInt("resource.input.businesscenter.columIndex"), exportEntryBean.getBusiness_center());
        wb = writeStringData(wb, PropertyFile.excelInt("resource.input.date.rowIndex"), PropertyFile.excelInt("resource.input.date.columIndex"), exportEntryBean.getEntry_date());
        return wb;
    }

    @Override
    public Workbook writeStringData(Workbook wb, Integer row, Integer column, String data) {
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("exportexcel.sheetno"));
        Row row_data = sheet.getRow(row);
        Cell cell = row_data.getCell(column);
        cell.setCellValue(data);
        return wb;
    }

    @Override
    public Workbook writeDoubleData(Workbook wb, Integer row, Integer column, Double data) {
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("exportexcel.sheetno"));
        Row row_data = sheet.getRow(row);
        Cell cell = row_data.getCell(column);
        cell.setCellValue(data);
        return wb;
    }

    @Override
    public Workbook writeDateData(Workbook wb, Integer row, Integer column, Date date) {
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("exportexcel.sheetno"));
        Row row_data = sheet.getRow(row);
        Cell cell = row_data.getCell(column);
        cell.setCellValue(date);
        return wb;
    }

    @Override
    public Workbook writeEmployeedata(Workbook wb, List<ExportEmployeeFormBean> exportEmpFormBeanList) {
        Integer starting_row = PropertyFile.excelInt("resource.input.employee.rowIndex");
        Iterator it = exportEmpFormBeanList.iterator();
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("exportexcel.sheetno"));
        while (it.hasNext()) {
            ExportEmployeeFormBean exportEmployeeBean = (ExportEmployeeFormBean) it.next();
            Integer colum = PropertyFile.excelInt("resource.input.employee.columIndex");
            Row row = sheet.createRow(starting_row);
            Cell cell = row.createCell(colum);
            cell.setCellValue(exportEmployeeBean.getEmployee_name());
            colum++;

            cell = row.createCell(colum);
            cell.setCellValue(exportEmployeeBean.getCost_center());
            colum++;

            cell = row.createCell(colum);
            cell.setCellValue(exportEmployeeBean.getEmployee_type());
            colum++;

            cell = row.createCell(colum);
            cell.setCellValue(exportEmployeeBean.getGrade());
            colum++;

            cell = row.createCell(colum);
            cell.setCellValue(exportEmployeeBean.getTier());
            colum++;

//            cell=row.createCell(colum);
//            cell.setCellValue(exportEmployeeBean.getTotal_fte());
//            colum++;

            cell = row.createCell(colum);
            cell.setCellValue(exportEmployeeBean.getTotal_compansation());
            colum++;

            cell = row.createCell(colum);
            cell.setCellValue(exportEmployeeBean.getProcess_name());
            colum++;

            cell = row.createCell(colum);
            cell.setCellValue(exportEmployeeBean.getSub_process_name());
            colum++;

            Iterator bu_it = exportEmployeeBean.getExportEmployeeBUList().iterator();
            while (bu_it.hasNext()) {
                ExportEmployeeBUBean exportEmployeeBUBean = (ExportEmployeeBUBean) bu_it.next();
                Integer colum_number = (Integer) buMap.get(exportEmployeeBUBean.getBu_id());
                cell = row.createCell(colum_number);
                cell.setCellValue(exportEmployeeBUBean.getFte());
                //cell=row.createCell(colum_number+1);
                //cell.setCellValue(exportEmployeeBUBean.getCompansation());
            }
            Integer total_colum_no = (Integer) buMap.get("total");
            cell = row.createCell(total_colum_no);
            cell.setCellValue(exportEmployeeBean.getTotal_fte());
            //cell=row.createCell(total_colum_no+1);
            //cell.setCellValue(exportEmployeeBean.getTotal_compansation());
            starting_row++;
        }

        Row tot_row = sheet.createRow(starting_row + 2);
        Cell tot_cell = tot_row.createCell(PropertyFile.excelInt("resource.input.employee.columIndex"));
        tot_cell.setCellValue(PropertyFile.excel("resource.input.employee.ending.label"));
        return wb;
    }

    @Override
    public List<ExportEmployeeFormBean> setEmpoyeeFormBean(String entry_id) {
        List<ExportEmployeeFormBean> exportEmployeeFormList = (List) jdbcTemplate.query(SQLSelector.getQuery("excelexport.hremployee.select"), new RowMapper() {
            @Override
            public ExportEmployeeFormBean mapRow(ResultSet rs, int i) throws SQLException {

                ExportEmployeeFormBean exportEmployeeFormBean = new ExportEmployeeFormBean();
                exportEmployeeFormBean.setEmployee_id(rs.getString(1));
                exportEmployeeFormBean.setEmployee_name(rs.getString(2));
                exportEmployeeFormBean.setCost_center(rs.getString(3));
                exportEmployeeFormBean.setEmployee_type(rs.getString(4));
                exportEmployeeFormBean.setProcess_name(rs.getString(5));
                exportEmployeeFormBean.setSub_process_name(rs.getString(6));
                exportEmployeeFormBean.setGrade(rs.getDouble(7));
                exportEmployeeFormBean.setTier(rs.getDouble(8));
                exportEmployeeFormBean.setTotal_fte(rs.getDouble(9));
                exportEmployeeFormBean.setTotal_compansation(rs.getDouble(10));
                exportEmployeeFormBean.setExportEmployeeBUList(setExportBUBean(rs.getString(1)));



                return exportEmployeeFormBean;
            }
        }, new Object[]{entry_id});
        return exportEmployeeFormList;
    }

    @Override
    public Workbook writeBusinessUnit(Workbook wb, List businessList) {
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("exportexcel.sheetno"));
        Integer row_number = PropertyFile.excelInt("resource.input.subheader.starting.rowIndex");
        Integer column_number = PropertyFile.excelInt("resource.input.subheader.starting.columIndex");
        buMap = new HashMap();
        Iterator it = businessList.iterator();
        Row row = sheet.getRow(row_number);
        if (row == null) {
            row = sheet.createRow(row_number);
        }
        Row row1 = sheet.getRow(row_number + 1);
        if (row1 == null) {
            row1 = sheet.createRow(row_number + 1);
        }
        while (it.hasNext()) {
            ListBean listBean = (ListBean) it.next();
            Cell cell = row.getCell(column_number);
            if (cell == null) {
                cell = row.createCell(column_number);
            }
            cell.setCellValue(listBean.getName());
            buMap.put(listBean.getId(), column_number);
            cell = row1.getCell(column_number);
            if (cell == null) {
                cell = row1.createCell(column_number);
            }
            cell.setCellValue("FTE");
            //cell=row1.getCell(column_number+1);
            //if(cell==null){cell=row1.createCell(column_number+1);}
            //cell.setCellValue("$'s");
            column_number = column_number + 1;
        }
        Cell cell = row.getCell(column_number);
        if (cell == null) {
            cell = row.createCell(column_number);
        }
        buMap.put("total", column_number);
        cell.setCellValue("Total");
        cell = row1.getCell(column_number);
        if (cell == null) {
            cell = row1.createCell(column_number);
        }
        cell.setCellValue("FTE");
        //cell=row1.getCell(column_number+1);
        //if(cell==null){
        //    cell=row1.createCell(column_number+1);
        //}
        //cell.setCellValue("$'s");
        return wb;
    }

    @Override
    public List<ListBean> getBUList(String bc_id) {
        List buList = jdbcTemplate.query(SQLSelector.getQuery("excelexport.getbusinessmap"), new RowMapper() {
            @Override
            public ListBean mapRow(ResultSet rs, int i) throws SQLException {

                ListBean listBean = new ListBean();
                listBean.setId(rs.getString(1));
                listBean.setName(rs.getString(2));
                return listBean;
            }
        }, new Object[]{bc_id});
        return buList;
    }

    @Override
    public List<ExportEmployeeBUBean> setExportBUBean(String employee_id) {
        List<ExportEmployeeBUBean> exportBUList = jdbcTemplate.query(SQLSelector.getQuery("excelexport.hrempdetails.select"), new RowMapper() {
            @Override
            public ExportEmployeeBUBean mapRow(ResultSet rs, int i) throws SQLException {


                ExportEmployeeBUBean exportBuBean = new ExportEmployeeBUBean();
                exportBuBean.setBu_id(rs.getString(1));
                exportBuBean.setFte(rs.getDouble(2));
                exportBuBean.setCompansation(rs.getDouble(3));


                return exportBuBean;
            }
        }, new Object[]{employee_id});
        return exportBUList;
    }

    @Override
    public Double getTotal(List<ExportEmployeeFormBean> exportEmployeeBeanList) {
        Iterator it = exportEmployeeBeanList.iterator();
        Double total = 0.0;
        while (it.hasNext()) {
            ExportEmployeeFormBean exportEmployeeBean = (ExportEmployeeFormBean) it.next();
            total += exportEmployeeBean.getTotal_compansation();
        }
        return total;
    }

    @Override
    public Workbook setTotal(Workbook wb, Double total) {
        Integer sheet_no = PropertyFile.excelInt("exportexcel.sheetno");
        Sheet sheet = wb.getSheetAt(sheet_no);
        Integer row_index = PropertyFile.excelInt("resource.input.employee.rowIndex");
        Integer colum_index = PropertyFile.excelInt("resource.input.employee.columIndex");
        String ending_label = PropertyFile.excel("resource.input.employee.ending.label");
        while (true) {

            Row row = sheet.getRow(row_index);
            if (row == null) {
                row = sheet.createRow(row_index);
            }
            Cell cell = row.getCell(colum_index);
            if (cell == null) {
                cell = row.createCell(colum_index);
            }
            String cell_value = cell.getStringCellValue();

            if (cell_value.equals(ending_label)) {
                Cell cell1 = row.createCell(colum_index + 1);
                cell1.setCellValue(total);
                break;
            }
            row_index++;
        }
        return wb;
    }

    @Override
    public Workbook setProcess(Workbook wb) {
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("exportexcel.lookupsheetno"));
        String query_for_Map = SQLSelector.getQuery("excelexport.process.map.select");
        List processList = jdbcTemplate.query(query_for_Map, new RowMapper() {
            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(2);
            }
        }, new Object[]{1});
        Integer row_count = PropertyFile.excelInt("exportexcel.lookup.process.row");
        Iterator it = processList.iterator();
        while (it.hasNext()) {
            Row row = sheet.getRow(row_count);
            if (row == null) {
                row = sheet.createRow(row_count);
            }
            Cell cell = row.createCell(PropertyFile.excelInt("exportexcel.lookup.process.column"));
            cell.setCellValue((String) it.next());
            row_count++;
        }
        return wb;
    }

    @Override
    public Workbook setSubProcess(Workbook wb) {
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("exportexcel.lookupsheetno"));
        String query_for_Map = SQLSelector.getQuery("excelexport.subprocess.map.select");
        List processList = jdbcTemplate.query(query_for_Map, new RowMapper() {
            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(2);
            }
        }, new Object[]{1});
        Integer row_count = PropertyFile.excelInt("exportexcel.lookup.process.row");
        Iterator it = processList.iterator();
        while (it.hasNext()) {
            Row row = sheet.getRow(row_count);
            if (row == null) {
                row = sheet.createRow(row_count);
            }
            Cell cell = row.createCell(PropertyFile.excelInt("exportexcel.lookup.subprocess.column"));
            cell.setCellValue((String) it.next());
            row_count++;
        }
        return wb;
    }

    @Override
    public List<ExportEntryFormBean> setEntryBeanList(String entry_date) {
        List<ExportEntryFormBean> result_list = jdbcTemplate.query(SQLSelector.getQuery("excelexportall.gethrdataentry.select"), new RowMapper() {
            @Override
            public ExportEntryFormBean mapRow(ResultSet rs, int i) throws SQLException {
                ExportEntryFormBean export_entry_bean = new ExportEntryFormBean();
                export_entry_bean.setEntry_id(rs.getString(1));
                export_entry_bean.setEntry_date(rs.getString(3));
                export_entry_bean.setBusiness_center(rs.getString(2));
                return export_entry_bean;
            }
        }, new Object[]{entry_date});
        return result_list;
    }

    @Override
    public Workbook setAllDataToExcel(Workbook wb, String entry_date) {
        List<ExportEntryFormBean> export_entry_beanList = setEntryBeanList(entry_date);
        Iterator it = export_entry_beanList.iterator();
        Integer row_count = 1;
        Sheet sheet = wb.getSheetAt(0);
        while (it.hasNext()) {
            ExportEntryFormBean export_entry_bean = (ExportEntryFormBean) it.next();
            List<ExportEmployeeFormBean> employee_beanList = setEmpoyeeFormBean(export_entry_bean.getEntry_id());
            Iterator employeeIt = employee_beanList.iterator();
            while (employeeIt.hasNext()) {
                ExportEmployeeFormBean exportEmployeeBean = (ExportEmployeeFormBean) employeeIt.next();
                List<ExportEmployeeBUBean> export_BUList = exportEmployeeBean.getExportEmployeeBUList();
                Iterator bu_Iterator = export_BUList.iterator();
                while (bu_Iterator.hasNext()) {
                    Row row = sheet.createRow(row_count);
                    ExportEmployeeBUBean export_BU_Bean = (ExportEmployeeBUBean) bu_Iterator.next();
                    Cell cell = row.createCell(0);
                    cell.setCellValue(export_entry_bean.getEntry_date());
                    cell = row.createCell(1);
                    cell.setCellValue(export_entry_bean.getBusiness_center());
                    cell = row.createCell(2);
                    cell.setCellValue(exportEmployeeBean.getEmployee_name());
                    cell = row.createCell(3);
                    cell.setCellValue(exportEmployeeBean.getCost_center());
                    cell = row.createCell(4);
                    cell.setCellValue(exportEmployeeBean.getEmployee_type());
                    cell = row.createCell(5);
                    cell.setCellValue(exportEmployeeBean.getTier());
                    cell = row.createCell(6);
                    cell.setCellValue(exportEmployeeBean.getGrade());
                    cell = row.createCell(7);
                    cell.setCellValue(exportEmployeeBean.getProcess_name());
                    cell = row.createCell(8);
                    cell.setCellValue(exportEmployeeBean.getSub_process_name());

                    cell = row.createCell(9);
                    cell.setCellValue(getClientName(export_BU_Bean.getBu_id()));
                    cell = row.createCell(10);
                    cell.setCellValue(export_BU_Bean.getFte());
                    cell = row.createCell(11);
                    cell.setCellValue(export_BU_Bean.getCompansation());

                    row_count++;
                }
            }
        }
        return wb;
    }

    @Override
    public String getClientName(String id) {
        String clinet_name = (String) jdbcTemplate.queryForObject(SQLSelector.getQuery("excelexportall.getclientname.select"), new RowMapper() {
            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(1);
            }
        }, new Object[]{id});
        return clinet_name;
    }

    @Override
    public Workbook setProcess(Workbook wb, Integer sheetno, Integer colum_count, Integer row_count) {
        Sheet sheet = wb.getSheetAt(sheetno);
        String query_for_Map = SQLSelector.getQuery("excelexport.process.map.select");
        List processList = jdbcTemplate.query(query_for_Map, new RowMapper() {
            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(2);
            }
        }, new Object[]{1});

        Iterator it = processList.iterator();
        while (it.hasNext()) {
            Row row = sheet.getRow(row_count);
            if (row == null) {
                row = sheet.createRow(row_count);
            }
            Cell cell = row.createCell(colum_count);
            cell.setCellValue((String) it.next());
            row_count++;
        }
        return wb;
    }

    @Override
    public Workbook setSubProcess(Workbook wb, Integer sheetno, Integer colum_count, Integer row_count) {
        Sheet sheet = wb.getSheetAt(sheetno);
        String query_for_Map = SQLSelector.getQuery("excelexport.subprocess.map.select");
        List processList = jdbcTemplate.query(query_for_Map, new RowMapper() {
            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(2);
            }
        }, new Object[]{1});

        Iterator it = processList.iterator();
        while (it.hasNext()) {
            Row row = sheet.getRow(row_count);
            if (row == null) {
                row = sheet.createRow(row_count);
            }
            Cell cell = row.createCell(colum_count);
            cell.setCellValue((String) it.next());
            row_count++;
        }
        return wb;
    }

    @Override
    public Workbook getGlobalWorkBook(Workbook wb) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Workbook getLocationWorkBook(Workbook wb) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Workbook getProcessWorkBook(Workbook wb) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Workbook getSubProcessWorkBook(Workbook wb) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer hasEntry(String date, String buc_center) {
        Integer count = 0;
        try {
            count = (Integer) jdbcTemplate.queryForObject(SQLSelector.getQuery("hrdataentry.hasentry.param2"), new RowMapper() {
                Integer i = 0;

                @Override
                public Integer mapRow(ResultSet rs, int i) throws SQLException {
                    i++;
                    return i;
                }
            }, new Object[]{date, buc_center});

        } catch (Exception ex) {
            count = 0;
            return count;
        }
        //Integer entry_id=jdbcTemplate.query("SELECT EntryId FROM HRDataEntry WHERE `HRDataEntry`.`EntryDate`=? AND `HRDataEntry`.`BusinessCenterId`=?", new Object[]{date,buc_center});
        return count;
    }

    @Override
    public Integer hasEntry(String date) {
        Integer count = 0;
        try {
            count = (Integer) jdbcTemplate.queryForObject(SQLSelector.getQuery("hrdataentry.hasentry.param1"), new RowMapper() {
                Integer i = 0;

                @Override
                public Integer mapRow(ResultSet rs, int i) throws SQLException {
                    i++;
                    return i;
                }
            }, new Object[]{date});

        } catch (Exception ex) {
            count = 0;
            return count;
        }
        //Integer entry_id=jdbcTemplate.query("SELECT EntryId FROM HRDataEntry WHERE `HRDataEntry`.`EntryDate`=? AND `HRDataEntry`.`BusinessCenterId`=?", new Object[]{date,buc_center});
        return count;
    }

    @Override
    public List<ExportEmployeeFormBean> setEmpoyeeFormBean(String entry_id, final String bcid) {
        List<ExportEmployeeFormBean> exportEmployeeFormList = (List) jdbcTemplate.query(SQLSelector.getQuery("excelexport.hremployee.select"), new RowMapper() {
            @Override
            public ExportEmployeeFormBean mapRow(ResultSet rs, int i) throws SQLException {

                ExportEmployeeFormBean exportEmployeeFormBean = new ExportEmployeeFormBean();
                exportEmployeeFormBean.setEmployee_id(rs.getString(1));
                exportEmployeeFormBean.setEmployee_name(rs.getString(2));
                exportEmployeeFormBean.setCost_center(rs.getString(3));
                exportEmployeeFormBean.setEmployee_type(rs.getString(4));
                exportEmployeeFormBean.setProcess_name(rs.getString(5));
                exportEmployeeFormBean.setSub_process_name(rs.getString(6));
                exportEmployeeFormBean.setGrade(rs.getDouble(7));
                exportEmployeeFormBean.setTier(rs.getDouble(8));
                exportEmployeeFormBean.setTotal_fte(rs.getDouble(9));
                exportEmployeeFormBean.setTotal_compansation(rs.getDouble(10));
                exportEmployeeFormBean.setExportEmployeeBUList(setExportBUBean(rs.getString(1), bcid));



                return exportEmployeeFormBean;
            }
        }, new Object[]{entry_id});
        return exportEmployeeFormList;
    }

    @Override
    public List<ExportEmployeeBUBean> setExportBUBean(String employee_id, String bcid) {
        List<ExportEmployeeBUBean> exportBUList = jdbcTemplate.query(SQLSelector.getQuery("excelexport.hrempdetails.select1"), new RowMapper() {
            @Override
            public ExportEmployeeBUBean mapRow(ResultSet rs, int i) throws SQLException {


                ExportEmployeeBUBean exportBuBean = new ExportEmployeeBUBean();
                exportBuBean.setBu_id(rs.getString(1));
                exportBuBean.setFte(rs.getDouble(2));
                exportBuBean.setCompansation(rs.getDouble(3));


                return exportBuBean;
            }
        }, new Object[]{bcid, employee_id});
        return exportBUList;
    }

    @Override
    public Workbook getBusinessUnit(Workbook wb, Map<String, List<String>> dataMap) {
        Integer rowIndex = 1;
        Integer firstColumIndex = 0;
        Integer secondColumIndex = 1;
        Sheet sheet = wb.getSheetAt(0);
        Iterator dataMapit = dataMap.entrySet().iterator();
        while (dataMapit.hasNext()) {
            Entry dataMapEntry = (Entry) dataMapit.next();
            List<MasterDataBean> buList = (List<MasterDataBean>) dataMapEntry.getValue();
            Row row = sheet.createRow(rowIndex);
            Cell cell = row.createCell(firstColumIndex);
            cell.setCellValue(dataMapEntry.getKey().toString());
            Iterator buListIt = buList.iterator();
            while (buListIt.hasNext()) {
                row = sheet.getRow(rowIndex);
                if (row == null) {
                    row = sheet.createRow(rowIndex);
                }
                cell = row.createCell(secondColumIndex);
                cell.setCellValue((String) buListIt.next());
                rowIndex++;
            }
        }
        return wb;
    }

    @Override
    public Map<String, List<String>> getBusinessUnitData() {
        Map<String, List<String>> resultMap = new LinkedHashMap<String, List<String>>();
        List<MasterDataBean> businessCenterList = jdbcTemplate.query("SELECT BusinessCenterId,BusinessCenterName FROM BusinessCenter ORDER BY BusinessCenterName", new RowMapper() {
            @Override
            public MasterDataBean mapRow(ResultSet rs, int i) throws SQLException {
                MasterDataBean masterDataBean = new MasterDataBean();
                masterDataBean.setId(rs.getString(1));
                masterDataBean.setName(rs.getString(2));
                return masterDataBean;
            }
        }, new Object[]{});
        Iterator businessCenterIt = businessCenterList.iterator();
        while (businessCenterIt.hasNext()) {
            MasterDataBean masterDataBean = (MasterDataBean) businessCenterIt.next();
            List<String> businessUnitList = jdbcTemplate.query("SELECT BusinessUnitName FROM BusinessUnit,BusinessCenterBusinessUnit WHERE BusinessUnit.BusinessUnitId=BusinessCenterBusinessUnit.BusinessUnitId AND BusinessCenterBusinessUnit.BusinessCenterId=? ORDER BY BusinessUnitName", new RowMapper() {
                @Override
                public String mapRow(ResultSet rs, int i) throws SQLException {
                    return rs.getString(1);
                }
            }, new Object[]{masterDataBean.getId()});
            resultMap.put(masterDataBean.getName(), businessUnitList);
        }
        return resultMap;
    }

    @Override
    public Workbook setAction(Workbook wb, Integer sheetno, Integer colum_count, Integer row_count) {
        Sheet sheet = wb.getSheetAt(sheetno);
        String query_for_Map = SQLSelector.getQuery("excelexport.action.map.select");
        List processList = jdbcTemplate.query(query_for_Map, new RowMapper() {
            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(2);
            }
        }, new Object[]{1});

        Iterator it = processList.iterator();
        while (it.hasNext()) {
            Row row = sheet.getRow(row_count);
            if (row == null) {
                row = sheet.createRow(row_count);
            }
            Cell cell = row.createCell(colum_count);
            cell.setCellValue((String) it.next());
            row_count++;
        }
        return wb;
    }
}
