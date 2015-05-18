/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import com.pumex.ConnectedInsight.Excel.Bean.DataEntryBean;
import com.pumex.ConnectedInsight.Excel.Bean.EmployeeBUBean;
import com.pumex.ConnectedInsight.Excel.Bean.EmployeeBean;
import com.pumex.ConnectedInsight.Excel.Bean.EmployeeDetailsBean;
import com.pumex.ConnectedInsight.Excel.Bean.EntryBean;
import com.pumex.ConnectedInsight.Excel.Bean.ExcelProcessingBean;
import com.pumex.ConnectedInsight.Property.DateConvertion;
import com.pumex.ConnectedInsight.Property.PropertyFile;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.sql.DataSource;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
 * @author Vishnu AU
 * Created on:09 July 2012
 * Updated On:31 July 2012
 * Updated by: Vishnu AU
 */
@Repository
public class ExcelOperationDaoImpl implements ExcelOperationDao {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static String PROPERTY_NAME = "resource.input.headername.header";
    @Autowired
    ExcelMasterDao excelMasterDao;
    @Autowired
    IDataExcelTemplateValidatorDao iDataExcelTemplateValidatorDao;

    /**
     * 
     * @param wb
     * @return
     * @throws IOException
     * @throws InvalidFormatException 
     * Comment: This function is used for saving excel data to database
     */
    @Override
    public DataEntryBean excelPreprocessing(Workbook wb) throws IOException, InvalidFormatException {
        /*String bussinesss_Center = validateBussinessCenter(wb, 4, 5);
        //String date = validateDate(wb, 5, 5);
        Integer business_id = jdbcTemplate.queryForInt("SELECT BusinessId FROM Business WHERE BusinessName=?", new Object[]{validateBussiness(wb, 6, 12)});
        Integer business_center_id = jdbcTemplate.queryForInt("SELECT BusinessCenterId FROM BusinessCenter WHERE BusinessCenterName=?", new Object[]{bussinesss_Center});
        DataEntryBean dataEntryBean = readEmployeeDetails(wb, 10, 1, validateHeader(wb, 9, 1), validateSubBU(wb, 8, 10), business_id);
        dataEntryBean.setBusiness_center_id(business_center_id);
        dataEntryBean.setBusiness_id(business_id);
        jdbcTemplate.update("INSERT INTO HRDataEntry	(OrganizationId, BusinessCenterId, BusinessId, EntryDate) VALUES (?,?,?,?)", new Object[]{dataEntryBean.getOrganization_id(), dataEntryBean.getBusiness_center_id(), dataEntryBean.getBusiness_id(), dataEntryBean.getEntry_date()});
        Integer entry_id = jdbcTemplate.queryForInt("SELECT MAX(EntryId) FROM HRDataEntry");
        List<EmployeeDetailsBean> employeeList = dataEntryBean.getEmployeeDetailsBean();
        Iterator it = employeeList.iterator();
        while (it.hasNext()) {
            EmployeeDetailsBean employeeDetailBean = (EmployeeDetailsBean) it.next();
            jdbcTemplate.update("INSERT INTO HRDataEmployeeEntry (EntryId, EmployeeName, EmployeeType, ProcessId, SubProcessId, Grade, Tier, TotalFTE, TotalCompensation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", new Object[]{entry_id, employeeDetailBean.getEmployee_name(), employeeDetailBean.getEmployee_type(), employeeDetailBean.getProcess_id(), employeeDetailBean.getSub_process_id(), employeeDetailBean.getGrade(), employeeDetailBean.getTier(), employeeDetailBean.getTotal_fte(), employeeDetailBean.getTotal_cost()});
            Integer employee_id = jdbcTemplate.queryForInt("SELECT MAX(EmployeeEntryId) FROM HRDataEmployeeEntry");
            List<EmployeeBUBean> employeeBUList = employeeDetailBean.getEmployee_bu_bean();
            Iterator its = employeeBUList.iterator();
            while (its.hasNext()) {
                EmployeeBUBean employeeBUBean = (EmployeeBUBean) its.next();
                jdbcTemplate.update("INSERT INTO HRDataEmployeeDetail (EmployeeEntryId, BusinessUnitId, FTE, Cost) VALUES (?, ?, ?, ?)", new Object[]{employee_id, employeeBUBean.getBusiness_unit_id(), employeeBUBean.getFte(), employeeBUBean.getCost()});
            }
        }*/
        return null;
    }

    @Deprecated
    @Override
    public String validateBussinessCenter(Workbook wb, Integer rowCount, Integer columCount) throws IOException, InvalidFormatException {
        String location = null;
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("resource.input.resourcesheet.no"));
        Row row = sheet.getRow(rowCount);
        Cell cell = row.getCell(columCount);
        location = cell.getStringCellValue();
        return location;
    }

    @Override
    public Date validateDate(Workbook wb, Integer rowCount, Integer columCount) throws IOException, InvalidFormatException {
        Date date = null;
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("resource.input.resourcesheet.no"));
        Row row = sheet.getRow(rowCount);
        Cell cell = row.getCell(columCount);
        //cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        date = cell.getDateCellValue();
        cell.setCellValue(date);
        return date;
    }

    @Deprecated
    @Override
    public String validateLocCordinator(Workbook wb, Integer rowCount, Integer columCount) throws IOException, InvalidFormatException {
        String locationLeader = null;
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("resource.input.resourcesheet.no"));
        Row row = sheet.getRow(rowCount);
        Cell cell = row.getCell(columCount);
        locationLeader = cell.getStringCellValue();
        return locationLeader;
    }

    @Deprecated
    @Override
    public String validateBussiness(Workbook wb, Integer rowCount, Integer columCount) throws IOException, InvalidFormatException {
        String bussiness_name = null;
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("resource.input.resourcesheet.no"));
        Row row = sheet.getRow(rowCount);
        Cell cell = row.getCell(columCount);
        bussiness_name = cell.getStringCellValue();
        return bussiness_name;
    }

    @Override
    public Map<Integer, String> validateSubBU(Workbook wb, Integer rowCount, Integer columCount) throws IOException, InvalidFormatException {
        Map<Integer, String> map = new HashMap<Integer, String>();
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("resource.input.resourcesheet.no"));
        Row row = sheet.getRow(rowCount);
        for (int i = columCount; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell.getStringCellValue().equals("")) {
                continue;
            } else if (cell.getStringCellValue().equals(ResourceBundle.getBundle("excelconfig").getString("resource.input.businessheader.limit"))) {
                map.put(i, cell.getStringCellValue());
                break;
            } else {

                map.put(i, cell.getStringCellValue());

            }
        }
        return map;
    }

    @Override
    public Boolean checkSubBU(String subBUName) {
        Boolean check_Status = false;
        Integer businessId = 1;
        Integer business_centerId = 1;
        Object sub_B_Unit = jdbcTemplate.queryForObject(
                ResourceBundle.getBundle("sqlqueries").getString("exceloperation.validation.getBussinessUnit"),
                new Object[]{businessId, business_centerId, subBUName}, Object.class);
        if (sub_B_Unit != null) {
            check_Status = true;
        }
        return check_Status;
    }

    @Override
    public Map<Integer, String> validateHeader(Workbook wb, Integer rowCount, Integer columCount) throws IOException, InvalidFormatException {
        Map<Integer, String> map = new HashMap<Integer, String>();
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("resource.input.resourcesheet.no"));
        Row row = sheet.getRow(rowCount);
        String header_count_String = ResourceBundle.getBundle("excelconfig").getString("resource.input.header.count");
        Integer header_count = Integer.parseInt(header_count_String);
        for (int i = columCount; i < columCount + header_count; i++) {
            Cell cell = row.getCell(i);
            if (checkHeader(cell.getStringCellValue(), header_count)) {
                map.put(i, cell.getStringCellValue());
            } else {
            }
        }
        return map;
    }

    @Override
    public Boolean checkHeader(String headerName, Integer headerCount) {
        Boolean check_Status = false;
        for (Integer i = 0; i < headerCount; i++) {
            String header_name = ResourceBundle.getBundle("excelconfig").getString(PROPERTY_NAME + "" + i);
            if (header_name.equals(headerName)) {
                check_Status = true;
                break;
            }
        }
        return check_Status;

    }

    @Override
    public DataEntryBean readEmployeeDetails(Workbook wb, Integer rowCount, Integer columCount, Map<Integer, String> mapHeader, Map<Integer, String> mapSBU, Integer totalEntries,ExcelProcessingBean excelProcessingBean) throws IOException, InvalidFormatException {
        DataEntryBean dataEntryFormBean = new DataEntryBean();
        dataEntryFormBean.setBusiness_center_name(readCell(wb, PropertyFile.excelInt("resource.input.businesscenter.rowIndex"), PropertyFile.excelInt("resource.input.businesscenter.columIndex"), PropertyFile.excelInt("resource.input.resourcesheet.no")));
//        dataEntryFormBean.setBusiness_name(readCell(wb, PropertyFile.excelInt("resource.input.business.rowIndex"), PropertyFile.excelInt("resource.input.business.columIndex"), PropertyFile.excelInt("resource.input.resourcesheet.no")));
        dataEntryFormBean.setEmployeeDetailsBean(getEmployeeDetails(wb, rowCount, columCount, mapHeader, mapSBU, totalEntries));
        dataEntryFormBean.setEntry_date(excelProcessingBean.getDate());
        return dataEntryFormBean;
    }

    @Override
    public List<EmployeeDetailsBean> getEmployeeDetails(Workbook wb, Integer rowCount, Integer columCount, Map<Integer, String> headerMap, Map<Integer, String> subMap, Integer totalEntries) throws IOException, InvalidFormatException {
        List<EmployeeDetailsBean> employeeDetailBeanList = new ArrayList<EmployeeDetailsBean>();
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("resource.input.resourcesheet.no"));
        for (int i = rowCount; i < rowCount + totalEntries; i++) {
            EmployeeDetailsBean employeeDetailBean = new EmployeeDetailsBean();
            Row row = sheet.getRow(i);
            System.out.println("i:" + i);
            for (int j = columCount; j < columCount + 8; j++) {
                Cell cell = row.getCell(j);
                if (headerMap.get(j).equals("Name")) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    employeeDetailBean.setEmployee_name(cell.getStringCellValue());
                } else if (headerMap.get(j).equals("Type")) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    employeeDetailBean.setEmployee_type(1);
                } else if (headerMap.get(j).equals("Grade")) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    employeeDetailBean.setGrade((int) cell.getNumericCellValue());
                } else if (headerMap.get(j).equals("Tier")) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    employeeDetailBean.setTier((int) cell.getNumericCellValue());
                } else if (headerMap.get(j).equals("Comp")) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    employeeDetailBean.setTotal_cost(cell.getNumericCellValue());
                } else if (headerMap.get(j).equals("Process")) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    employeeDetailBean.setProcess(cell.getStringCellValue());
                    //employeeDetailBean.setProcess_id(jdbcTemplate.queryForInt("SELECT ProcessId FROM Process WHERE ProcessName=?", new Object[]{cell.getStringCellValue()}));
                } else if (headerMap.get(j).equals("Sub Process")) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    employeeDetailBean.setSub_process(cell.getStringCellValue());
                    //employeeDetailBean.setSub_process_id(jdbcTemplate.queryForInt("SELECT SubProcessId FROM SubProcess WHERE SubProcessName=? AND ProcessId=?", new Object[]{cell.getStringCellValue(), employeeDetailBean.getProcess_id()}));
                }
            }
            employeeDetailBean.setEmployee_bu_bean(getEmpBUDetails(row, subMap, rowCount));
            employeeDetailBeanList.add(employeeDetailBean);
        }
        return employeeDetailBeanList;
    }

    @Override
    public List<EmployeeBUBean> getEmpBUDetails(Row row, Map<Integer, String> subMap, Integer startColCount) throws IOException, InvalidFormatException {
        List<EmployeeBUBean> employeeBUBeanList = new ArrayList<EmployeeBUBean>();
        //Integer endColumCount = 10 + (subMap.size() * 2) - 4;
        Integer endColumCount = 10 + (subMap.size()) - 2;
        //for (int i = 10; i < endColumCount; i += 2) {
        for (int i = 10; i < endColumCount; i++) {

            Cell cell = row.getCell(i);
            if (cell.getNumericCellValue() != 0) {
                EmployeeBUBean employeeBean = new EmployeeBUBean();
                employeeBean.setFte(cell.getNumericCellValue());
                String business_unit_name = subMap.get(i);
                employeeBean.setBusiness_unit(business_unit_name);
//                cell = row.getCell(i + 1);
//                employeeBean.setCost(cell.getNumericCellValue());
                employeeBUBeanList.add(employeeBean);
            }

        }
        return employeeBUBeanList;
    }

    @Override
    public String readCell(Workbook wb, Integer rowCount, Integer columCount, Integer sheetno) throws IOException, InvalidFormatException {
        String cellContent = null;
        Sheet sheet = wb.getSheetAt(sheetno);
        Row row = sheet.getRow(rowCount);
        Cell cell = row.getCell(columCount);
        cellContent = cell.getStringCellValue();
        return cellContent;
    }

    @Override
    public Double readDoubleCell(Workbook wb, Integer rowIndex, Integer columIndex, Integer sheetno) throws IOException, InvalidFormatException {
        Double cellContent = null;
        Sheet sheet = wb.getSheetAt(sheetno);
        Row row = sheet.getRow(rowIndex);
        Cell cell = row.getCell(columIndex);
        cellContent = cell.getNumericCellValue();
        return cellContent;
    }

    @Override
    public Boolean checkBURowLimit(Workbook wb, Integer rowIndex, Integer columIndex) throws IOException, InvalidFormatException {
        Boolean checkStatus = false;
        Sheet sheet = wb.getSheetAt(1);
        Row row = sheet.getRow(rowIndex);
        for (Integer i = columIndex; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            String cell_content = cell.getStringCellValue();
            String excel_config_content = ResourceBundle.getBundle("excelconfig").getString("resource.input.businessheader.limit");
            if (excel_config_content.equals(cell_content)) {
                checkStatus = true;
                break;
            }
        }
        return checkStatus;
    }

    @Override
    public Integer checkBusinessCenter(String businessCenter_Name, UserBean user) throws IOException, InvalidFormatException {
        try {
            Integer business_center_id = jdbcTemplate.queryForInt(ResourceBundle.getBundle("sqlqueries").getString("exceloperation.validation.getBusinessCenter"),
                    new Object[]{user.getOrganizationid(), businessCenter_Name});
            return business_center_id;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Integer checkBusiness(String businessName, UserBean user) throws IOException, InvalidFormatException {

        try {

            Integer business_Id = jdbcTemplate.queryForInt(SQLSelector.getQuery("exceloperation.validation.getBusiness"),
                    new Object[]{user.getOrganizationid(), businessName});

            return business_Id;

        } catch (Exception ex) {

            return null;
        }
    }

    @Override
    public Integer[] getEmployeeEndingIndex(Workbook wb) {
        Integer sheet_no = PropertyFile.excelInt("resource.input.resourcesheet.no");
        Sheet sheet = wb.getSheetAt(sheet_no);
        Integer row_index = PropertyFile.excelInt("resource.input.employee.rowIndex");
        Integer colum_index = PropertyFile.excelInt("resource.input.employee.columIndex");
        String ending_label = PropertyFile.excel("resource.input.employee.ending.label");
        Integer[] result = new Integer[2];
        Integer entryNumber = row_index;
        Integer exit_condition = 0;
        while (true) {
            if (exit_condition == 100) {
                break;
            }

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
                result[0] = row_index - entryNumber - exit_condition;
                result[1] = row_index;
                break;
            } else if (cell_value.equals("")) {
                exit_condition++;
            }
            row_index++;
        }
        return result;
    }

    @Override
    public Double getTotalCompensation(List<EmployeeDetailsBean> empDetailedFormBean) {
        Double totalCost = 0.0;
        Iterator it = empDetailedFormBean.iterator();
        while (it.hasNext()) {
            EmployeeDetailsBean empFormBean = (EmployeeDetailsBean) it.next();
            totalCost += empFormBean.getTotal_cost();
        }
        return totalCost;
    }

    @Override
    public Boolean insertProcessToHashTab(Workbook wb, Integer totalEntries, UserBean user) {
        jdbcTemplate.update(SQLSelector.getQuery("exceloperation.process.temptable.delete"), new Object[]{user.getUserid()});
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("resource.input.resourcesheet.no"));
        Integer columIndex = PropertyFile.excelInt("resource.input.employee.columIndex") + PropertyFile.excelInt("resource.input.process.columfrom.index");
        Integer startRowIndex = PropertyFile.excelInt("resource.input.employee.rowIndex");
        Map<String, Integer> processMap = new HashMap<String, Integer>();
        for (Integer i = startRowIndex; i < startRowIndex + totalEntries; i++) {
            String current_process = readCell(sheet.getRow(i), columIndex);

            if (processMap.get(current_process) != null && processMap.get(current_process) == 1) {
                continue;
            } else {
                processMap.put(current_process, 1);
                String query_for_temp = SQLSelector.getQuery("exceloperation.process.temptable.insert");
                jdbcTemplate.update(query_for_temp, new Object[]{user.getUserid(), current_process});
            }

        }
        List l = (List) jdbcTemplate.query(SQLSelector.getQuery("exceloperation.process.validate.select"), new RowMapper() {

            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(1);
            }
        }, new Object[]{user.getOrganizationid(), user.getUserid()});
        if (l.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String readCell(Row row, Integer columIndex) {
        Cell cell = row.getCell(columIndex);
        String cell_value = cell.getStringCellValue();
        return cell_value;
    }

    @Override
    public Boolean insertSubProcessToHashTab(Workbook wb, Integer totalEntries, UserBean user, Map<String, Integer> processMap) {
        jdbcTemplate.update(SQLSelector.getQuery("exceloperation.subprocess.temptable.delete"), new Object[]{user.getUserid()});
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("resource.input.resourcesheet.no"));
        Integer columIndex = PropertyFile.excelInt("resource.input.employee.columIndex") + PropertyFile.excelInt("resource.input.subprocess.columfrom.index");
        Integer startRowIndex = PropertyFile.excelInt("resource.input.employee.rowIndex");
        Map<String, Integer> subProcessMap = new HashMap<String, Integer>();
        for (Integer i = startRowIndex; i < startRowIndex + totalEntries; i++) {
            String current_process = readCell(sheet.getRow(i), columIndex);

            if (subProcessMap.get(current_process) != null && subProcessMap.get(current_process) == 1) {
                continue;
            } else {
                subProcessMap.put(current_process, 1);
                String query_for_temp = SQLSelector.getQuery("exceloperation.subprocess.temptable.insert");
                Integer processId = processMap.get(readCell(sheet.getRow(i), columIndex - 1));
                jdbcTemplate.update(query_for_temp, new Object[]{user.getUserid(), current_process, processId});
            }

        }
        List l = (List) jdbcTemplate.query(SQLSelector.getQuery("exceloperation.subprocess.validate.select"), new RowMapper() {

            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(1);
            }
        }, new Object[]{user.getUserid()});
        if (l.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Boolean insertEmployeeTypeToHashTab(Workbook wb, Integer totalEntries, UserBean user) {
        jdbcTemplate.update(SQLSelector.getQuery("exceloperation.employeetype.temptable.delete"), new Object[]{user.getUserid()});
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("resource.input.resourcesheet.no"));
        Integer columIndex = PropertyFile.excelInt("resource.input.employee.columIndex") + PropertyFile.excelInt("resource.input.employeetype.columfrom.index");
        Integer startRowIndex = PropertyFile.excelInt("resource.input.employee.rowIndex");
        Map<String, Integer> empTypeMap = new HashMap<String, Integer>();
        for (Integer i = startRowIndex; i < startRowIndex + totalEntries; i++) {
            String current_process = readCell(sheet.getRow(i), columIndex);

            if (empTypeMap.get(current_process) != null && empTypeMap.get(current_process) == 1) {
                continue;
            } else {
                empTypeMap.put(current_process, 1);
                String query_for_temp = SQLSelector.getQuery("exceloperation.emptype.temptable.insert");
                jdbcTemplate.update(query_for_temp, new Object[]{user.getUserid(), current_process});
            }

        }
        List l = (List) jdbcTemplate.query(SQLSelector.getQuery("exceloperation.emptype.validate.select"), new RowMapper() {

            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(1);
            }
        }, new Object[]{user.getUserid()});
        if (l.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean insertBUToHashTab(Workbook wb, Integer busCenterId, Integer busId, UserBean user) {
        jdbcTemplate.update(SQLSelector.getQuery("exceloperation.bu.temptable.delete"), new Object[]{user.getUserid()});
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("resource.input.resourcesheet.no"));
        Integer columIndex = PropertyFile.excelInt("resource.input.subheader.starting.columIndex");
        Integer startRowIndex = PropertyFile.excelInt("resource.input.subheader.starting.rowIndex");
        Row row = sheet.getRow(startRowIndex);

        for (int i = columIndex; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell.getStringCellValue().equals("")) {
                continue;
            } else if (cell.getStringCellValue().equals(ResourceBundle.getBundle("excelconfig").getString("resource.input.businessheader.limit"))) {
                break;
            } else {
                jdbcTemplate.update(SQLSelector.getQuery("exceloperation.bu.temptable.insert"), new Object[]{user.getUserid(), cell.getStringCellValue()});
            }
        }
        List list = getJdbcList(SQLSelector.getQuery("exceloperation.bu.validate.select"), new Object[]{user.getUserid(), busCenterId});
        if (list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public List getJdbcList(String sqlQuery, Object[] obj) {
        List l = (List) jdbcTemplate.query(sqlQuery, new RowMapper() {

            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                String data = rs.getString(1);
                return data;
            }
        }, obj);
        return l;
    }

    @Override
    public void saveToHRdataTab(Workbook wb, UserBean user, ExcelProcessingBean excelProcessingBean) throws IOException, InvalidFormatException {
        EntryBean entry_Bean = new EntryBean();
        jdbcTemplate.update("DELETE FROM TempHRData WHERE UserId=?", new Object[]{user.getUserid()});
        entry_Bean.setBusiness_center_id(excelProcessingBean.getBusiness_center_id());
        entry_Bean.setBusiness_id(excelProcessingBean.getBusiness_id());
        entry_Bean.setEntry_date(excelProcessingBean.getDate());
        entry_Bean.setOrganization_id(user.getOrganizationid());
        Map<Integer, String> headerMap = validateHeader(wb, PropertyFile.excelInt("resource.input.header.starting.rowIndex"), PropertyFile.excelInt("resource.input.header.starting.columIndex"));
        Map<Integer, String> subMap = validateSubBU(wb, PropertyFile.excelInt("resource.input.subheader.starting.rowIndex"), PropertyFile.excelInt("resource.input.subheader.starting.columIndex"));
        Map<String, Integer> BUmap = excelMasterDao.getBusinesMap(entry_Bean.getBusiness_center_id(), entry_Bean.getBusiness_id());
        Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("resource.input.resourcesheet.no"));
        Integer startIndex = PropertyFile.excelInt("resource.input.employee.rowIndex");
        Integer endIndex = excelProcessingBean.getTotalEntries();
        Integer limit = endIndex + startIndex;
        for (int i = startIndex; i < limit; i++) {

            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            } else {
                entry_Bean.setEmployeeBean(getHRemployeeDetails(row, headerMap, subMap, BUmap, user));
                entry_Bean.getEmployeeBean().setXl_rowno(i);
                if (entry_Bean.getEmployeeBean() != null) {
                    saveToTempHRData(entry_Bean, user);
                }
            }
        }
    }

    public void saveToTempHRData(EntryBean entryBean, UserBean user) {
        EmployeeBean employeeBean = entryBean.getEmployeeBean();
        List<EmployeeBUBean> buList = employeeBean.getEmployee_bu_bean();
        Iterator it = buList.iterator();
        while (it.hasNext()) {
            EmployeeBUBean empBUBean = (EmployeeBUBean) it.next();
            jdbcTemplate.update("INSERT INTO TempHRData ( UserId, OrganizationId, BusinessCentreId, EntryDate, EmployeeName, EmployeeTypeId, ProcessId, SubProcessId, Grade, Tier, TotalFTE, TotalCompensation, BusinessUnitId, FTE, Cost, CostCenter, XLRowNumber) VALUES	( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", new Object[]{
                        /*UserId*/user.getUserid(),
                        /*OrganizationId*/ entryBean.getOrganization_id(),
                        /*BusinessCentreId*/ entryBean.getBusiness_center_id(),
                        /*EntryDate*/ entryBean.getEntry_date(),
                        /*EmployeeName*/ employeeBean.getEmployee_name(),
                        /*EmployeeTypeId*/ employeeBean.getEmployee_type(),
                        /*ProcessId*/ employeeBean.getProcess_id(),
                        /*SubProcessId*/ employeeBean.getSub_process_id(),
                        /*Grade*/ employeeBean.getGrade(),
                        /*Tier*/ employeeBean.getTier(),
                        /*TotalFTE*/ employeeBean.getTotal_fte(),
                        /*TotalCompensation*/ employeeBean.getTotal_cost(),
                        /*BusinessUnitId*/ empBUBean.getBusiness_unit_id(),
                        /*FTE*/ empBUBean.getFte(),
                        /*Cost*/ empBUBean.getCost(),
                        /*Cost Center*/ employeeBean.getCost_Center(),
                        /*XL Row count*/ employeeBean.getXl_rowno()
                    });
        }
    }

    public EmployeeBean getHRemployeeDetails(Row row, Map<Integer, String> headerMap, Map<Integer, String> subMap, Map<String, Integer> BUmap, UserBean user) {
        EmployeeBean employeeBean = new EmployeeBean();
        Map<String, Integer> empMap = excelMasterDao.getEmployeeTypeMap();
        Map<String, Integer> proMap = excelMasterDao.getProcessMap(user);
        Integer columCount = PropertyFile.excelInt("resource.input.employee.columIndex");
        for (int j = columCount; j < columCount + 8; j++) {
            Cell cell = row.getCell(j);
            if (cell == null) {
                return null;
            }
            if (headerMap.get(j).equals("Name")) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                employeeBean.setEmployee_name(cell.getStringCellValue());
            } else if (headerMap.get(j).equals("Type")) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                employeeBean.setEmployee_type(empMap.get(cell.getStringCellValue()));
            } else if (headerMap.get(j).equals("Grade")) {
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                employeeBean.setGrade((int) cell.getNumericCellValue());
            } else if (headerMap.get(j).equals("Tier")) {
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                employeeBean.setTier((int) cell.getNumericCellValue());
            } else if (headerMap.get(j).equals("Comp")) {
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                employeeBean.setTotal_cost(cell.getNumericCellValue());
            } else if (headerMap.get(j).equals("Process")) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                employeeBean.setProcess(cell.getStringCellValue());
                employeeBean.setProcess_id(proMap.get(cell.getStringCellValue()));
            } else if (headerMap.get(j).equals("Sub Process")) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                employeeBean.setSub_process(cell.getStringCellValue());
                Map<String, Integer> subprocessMap = excelMasterDao.getSubProcessMap(employeeBean.getProcess_id());
                employeeBean.setSub_process_id(subprocessMap.get(cell.getStringCellValue().trim()));
            } else if (headerMap.get(j).equals("Cost Center")) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                employeeBean.setCost_Center(cell.getStringCellValue().trim());
            }
        }
        employeeBean = getHrEmployeeBU(row, employeeBean, subMap, BUmap);
        return employeeBean;
    }

    public EmployeeBean getHrEmployeeBU(Row row, EmployeeBean employeeBean, Map<Integer, String> subMap, Map<String, Integer> buMap) {
        List<EmployeeBUBean> employeeBUBeanList = new ArrayList<EmployeeBUBean>();
        Integer endColumCount = PropertyFile.excelInt("resource.input.subheader.starting.columIndex") + (subMap.size());
        for (int i = PropertyFile.excelInt("resource.input.subheader.starting.columIndex"); i < endColumCount; i++) {

            Cell cell = row.getCell(i);
            if (cell.getNumericCellValue() != 0) {
                if (subMap.get(i).equals("Total")) {
                    employeeBean.setTotal_fte(cell.getNumericCellValue());
                } else {
                    EmployeeBUBean employeeBUBean = new EmployeeBUBean();
                    employeeBUBean.setFte(cell.getNumericCellValue());
                    String business_unit_name = subMap.get(i);
                    employeeBUBean.setBusiness_unit(subMap.get(i));
                    employeeBUBean.setBusiness_unit_id(buMap.get(business_unit_name));
                    //employeeBean.setBusiness_unit_id(jdbcTemplate.queryForInt("SELECT BusinessUnitId FROM BusinessUnit WHERE BusinessUnitName=? AND BusinessId=? AND BusinessCenterId=?", new Object[]{business_unit_name, business_id, business_center_id}));
                    //cell = row.getCell(i + 1);
                    /*One change from client. calculate cost from comp and fte in client. equation:comp*fte*/
                    // employeeBUBean.setCost(cell.getNumericCellValue());
                    employeeBUBean.setCost((employeeBean.getTotal_cost() * employeeBUBean.getFte()));
                    employeeBUBeanList.add(employeeBUBean);
                }

            }

        }
        employeeBean.setEmployee_bu_bean(employeeBUBeanList);
        return employeeBean;
    }

    @Override
    public void savePermenentTable(UserBean user, String entryDate, Integer bcid) {
        jdbcTemplate.update("INSERT INTO HRDataEntry(`OrganizationId`,`BusinessCenterId`,`EntryDate`) (SELECT DISTINCT `OrganizationId`,`BusinessCentreId`,`EntryDate` FROM `TempHRData` WHERE UserId=?)", new Object[]{user.getUserid()});
        List entryList = (List) jdbcTemplate.query("SELECT DISTINCT `EntryId` ,`OrganizationId`,`BusinessCenterId`  FROM HRDataEntry WHERE EntryDate=? and BusinessCenterId=?", new RowMapper() {

            @Override
            public DataEntryBean mapRow(ResultSet rs, int i) throws SQLException {
                DataEntryBean entryBean = new DataEntryBean();
                entryBean.setEntry_id(rs.getInt(1));
                entryBean.setOrganization_id(rs.getInt(2));
                entryBean.setBusiness_center_id(rs.getInt(3));
                //entryBean.setBusiness_id(rs.getInt(4));
                return entryBean;
            }
        }, new Object[]{entryDate, bcid});

        Iterator it = entryList.iterator();
        DataEntryBean entryBean = (DataEntryBean) it.next();
        jdbcTemplate.update("INSERT INTO `HRDataEmployeeEntry` (`EntryId`,`EmployeeName`,`EmployeeTypeId`,`ProcessId`,`SubProcessId`,`Grade`,`Tier`,`TotalFTE`,`TotalCompensation`,`CostCenter`,`XLRowNumber`)  (SELECT DISTINCT ?,`EmployeeName`,`EmployeeTypeId` ,`ProcessId`,`SubProcessId`,`Grade`,`Tier`,`TotalFTE`,`TotalCompensation`,`CostCenter`,`XLRowNumber` FROM `TempHRData` WHERE  `EntryDate`=? and `OrganizationId`=? and `BusinessCentreId`=?)",
                new Object[]{entryBean.getEntry_id(), entryDate, entryBean.getOrganization_id(), entryBean.getBusiness_center_id()});
        List empList = (List) jdbcTemplate.query("SELECT DISTINCT `EmployeeEntryId`,`EmployeeName`,`EmployeeTypeId`,`ProcessId`,`SubProcessId`,`Grade`,`Tier`,`XLRowNumber` FROM `HRDataEmployeeEntry` WHERE   `EntryId`=?", new RowMapper() {

            @Override
            public EmployeeDetailsBean mapRow(ResultSet rs, int i) throws SQLException {
                EmployeeDetailsBean employeeDetailsBean = new EmployeeDetailsBean();
                employeeDetailsBean.setEmployee_entry_id(rs.getInt(1));
                employeeDetailsBean.setEmployee_name(rs.getString(2));
                employeeDetailsBean.setEmployee_type(rs.getInt(3));
                employeeDetailsBean.setProcess_id(rs.getInt(4));
                employeeDetailsBean.setSub_process_id(rs.getInt(5));
                employeeDetailsBean.setGrade(rs.getInt(6));
                employeeDetailsBean.setTier(rs.getInt(7));
                employeeDetailsBean.setXl_row_number(rs.getString(8));
                return employeeDetailsBean;
            }
        }, new Object[]{entryBean.getEntry_id()});

        it = empList.iterator();
        while (it.hasNext()) {
            EmployeeDetailsBean employeeDetailsBean = (EmployeeDetailsBean) it.next();
            List buList = (List) jdbcTemplate.query("SELECT `BusinessUnitId`,`FTE`,`Cost` FROM `TempHRData` WHERE `EmployeeName`=? and`EmployeeTypeId`=? and `ProcessId`=? and `SubProcessId`=? and `Grade`=? and `Tier`=? and `XLRowNumber`=? and`EntryDate`= ?", new RowMapper() {

                @Override
                public EmployeeBUBean mapRow(ResultSet rs, int i) throws SQLException {
                    EmployeeBUBean employeeBUBean = new EmployeeBUBean();
                    employeeBUBean.setBusiness_unit_id(rs.getInt(1));
                    employeeBUBean.setFte(rs.getDouble(2));
                    employeeBUBean.setCost(rs.getDouble(3));
                    return employeeBUBean;

                }
            }, new Object[]{employeeDetailsBean.getEmployee_name(), employeeDetailsBean.getEmployee_type(), employeeDetailsBean.getProcess_id(), employeeDetailsBean.getSub_process_id(), employeeDetailsBean.getGrade(), employeeDetailsBean.getTier(), employeeDetailsBean.getXl_row_number(),entryDate});
            Iterator buIt = buList.iterator();
            while (buIt.hasNext()) {
                EmployeeBUBean employeeBuBean = (EmployeeBUBean) buIt.next();
                jdbcTemplate.update("INSERT INTO `HRDataEmployeeDetail` (`EmployeeEntryId`,`BusinessUnitId`,`FTE`,`Cost`) VALUES ( ?, ?, ?, ?)", new Object[]{employeeDetailsBean.getEmployee_entry_id(), employeeBuBean.getBusiness_unit_id(), employeeBuBean.getFte(), employeeBuBean.getCost()});
            }
        }

    }

    @Override
    public Boolean checkDate(String date, Integer bcid) {
        Boolean flag = true;
        try {
            String recieve_date = jdbcTemplate.queryForObject(SQLSelector.getQuery("excel.date.validation"), new Object[]{date, bcid}, String.class);
            if (recieve_date != null) {
                flag = false;
            }
        } catch (Exception ex) {
            return true;
        }
        return flag;
    }

    @Override
    public void deleteData(String date, String bcid) {
        try {
            Integer entry_id = jdbcTemplate.queryForInt("SELECT EntryId FROM HRDataEntry WHERE EntryDate=? AND BusinessCenterId=?", new Object[]{date, bcid});
            jdbcTemplate.update("DELETE FROM `HRDataEmployeeDetail` WHERE `EmployeeEntryId` IN (SELECT `EmployeeEntryId` FROM `HRDataEmployeeEntry` WHERE `EntryId` IN (SELECT `EntryId` FROM `HRDataEntry` WHERE `EntryId` = ?))", new Object[]{entry_id});
            jdbcTemplate.update("DELETE FROM `HRDataEmployeeEntry` WHERE `EntryId` IN (SELECT `EntryId` FROM `HRDataEntry` WHERE `EntryId` = ?)", new Object[]{entry_id});
            jdbcTemplate.update("DELETE FROM `HRDataEntry` WHERE `EntryId` = ?", new Object[]{entry_id});
        } catch (Exception ex) {
            System.out.println("Exception in delete data---->>>" + ex);
        }
    }

    @Override
    public List<EmployeeDetailsBean> setBusinessUnitCost(List<EmployeeDetailsBean> emplBeanList) {
//        Iterator it=emplBeanList.iterator();
//        while(it.hasNext()){
//            EmployeeDetailsBean employeeDetailsBean=(EmployeeDetailsBean)it.next();
//            Iterator itBU=employeeDetailsBean.getEmployee_bu_bean().iterator();
//            while(itBU.hasNext()){
//                EmployeeBUBean employeeBUBean=(EmployeeBUBean)itBU.next();
//                employeeDetailsBean.get
//            }
//        }
        return null;
    }

    @Override
    public Date validateDate(Workbook wb, Integer sheetNo, Integer rowCount, Integer columCount) throws IOException, InvalidFormatException {
        Date date = null;
        Sheet sheet = wb.getSheetAt(sheetNo);
        Row row = sheet.getRow(rowCount);
        Cell cell = row.getCell(columCount);
        date = cell.getDateCellValue();
        return date;
    }

    @Override
    public ExcelProcessingBean validateDataSheetTemplate(ExcelProcessingBean excelProcessingBean, Workbook wb) {
        excelProcessingBean = iDataExcelTemplateValidatorDao.validateBusinessCenter(wb, excelProcessingBean);
        if (!excelProcessingBean.getStatus().equals("Failed")) {
            excelProcessingBean = iDataExcelTemplateValidatorDao.validateStatementDate(wb, excelProcessingBean);
            if (!excelProcessingBean.getStatus().equals("Failed")) {
                excelProcessingBean = iDataExcelTemplateValidatorDao.validateExcelHeader(wb, excelProcessingBean);
                if (!excelProcessingBean.getStatus().equals("Failed")) {
                    excelProcessingBean = iDataExcelTemplateValidatorDao.validateTotalLabel(wb, excelProcessingBean);

                }
            }
        }
        return excelProcessingBean;
    }
}
