/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import com.pumex.ConnectedInsight.Excel.Bean.BUActualBean;
import com.pumex.ConnectedInsight.Excel.Bean.ExcelProcessingBean;
import com.pumex.ConnectedInsight.Excel.Bean.VolumeAndRevBean;
import com.pumex.ConnectedInsight.Property.DateConvertion;
import com.pumex.ConnectedInsight.Property.PropertyFile;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.common.Dao.SQLSelector;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public class ExcelVolAndRevDaoImpl implements IExcelVolAndRevDao {

    @Autowired
    CommonDaoInterface commonDaoInterface;
    @Autowired
    ExcelMasterDao excelMaster;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    ExcelOperationDao excelOperationDao;

    @Override
    public void testVolume(Workbook wb) {
        Sheet sheet = wb.getSheetAt(2);
        Row row = sheet.getRow(16);
        Cell cell = row.getCell(4);
        String cellValue = cell.getStringCellValue();
        Integer lastRowCount = sheet.getLastRowNum();
        Integer firstRowCount = sheet.getFirstRowNum();

        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ExcelProcessingBean validateVolAndRevSheet(ExcelProcessingBean excelProcessingBean, Workbook wb, UserBean user) {
        Integer volumeSheetno=PropertyFile.excelInt("volumerevenue.sheetno");
        if (checkDate(excelProcessingBean.getDate(), excelProcessingBean.getBusiness_center_id()) != false) {
            Cell bc_cell = BasicExcelOperation.getExcelCell(wb.getSheetAt(volumeSheetno), 5, 4, "String");
            Cell date_cell = BasicExcelOperation.getExcelCell(wb.getSheetAt(volumeSheetno), 6, 4, "Double");
            String bc_center = bc_cell.getStringCellValue();
            String dateValue = DateConvertion.convertToMysqlFormat(date_cell.getDateCellValue());
            List<String> bu = BasicExcelOperation.getDataFromRow(wb.getSheetAt(volumeSheetno).getRow(11));
            bu.remove("Center Total");
            /*Validating Client unique list*/
            Map volAndRevClientUniqueMap=ExcelValidator.validateUniqueEntry(bu);
            Cell cell = BasicExcelOperation.getExcelCell("Sub-Process (s)", wb.getSheetAt(volumeSheetno));
            Integer subprRowNo = cell.getRowIndex();
            Integer subprCellNo = cell.getColumnIndex();
            List<String> subProc = BasicExcelOperation.getDataFromColum(wb.getSheetAt(volumeSheetno), subprRowNo + 1, subprCellNo);
            Map subprocValidMap = ExcelValidator.validateList(subProc, commonDaoInterface.queryForMap("SELECT `SubProcessId`,`SubProcessName` FROM `SubProcess`"));
            cell = BasicExcelOperation.getExcelCell("Volume Description", wb.getSheetAt(volumeSheetno));
            Integer volDescRowNo = cell.getRowIndex();
            Integer volDescCellNo = cell.getColumnIndex();
            List<String> volDesList = BasicExcelOperation.getDataFromColum(wb.getSheetAt(volumeSheetno), volDescRowNo + 1, volDescCellNo);
            volDesList = BasicExcelOperation.getUniqueList(volDesList);
            Map volDesdcMap = ExcelValidator.validateList(volDesList, commonDaoInterface.queryForMap("SELECT `VolumeDescId`,`VolumeDescription` FROM `VolumeDescription`"));
            Map clientMap = ExcelValidator.validateList(bu, commonDaoInterface.queryForMap("SELECT `BusinessUnit`.`BusinessUnitId`,`BusinessUnit`.`BusinessUnitName` FROM `BusinessUnit`,`BusinessCenterBusinessUnit`,`BusinessCenter` WHERE `BusinessUnit`.`BusinessUnitId`=`BusinessCenterBusinessUnit`.`BusinessUnitId` AND `BusinessCenterBusinessUnit`.`BusinessCenterId`=`BusinessCenter`.`BusinessCenterId` AND `BusinessCenter`.`BusinessCenterId`=?", new Object[]{excelProcessingBean.getBusiness_center_id()}));
            Map colNumBUMap = BasicExcelOperation.getColumNumber(bu, wb.getSheetAt(volumeSheetno));
            Map rowSubProcess = BasicExcelOperation.getRowNumber(subProc, wb.getSheetAt(volumeSheetno));

            //Revenue validation
            cell = BasicExcelOperation.getExcelCell("Annual Revenue", wb.getSheetAt(volumeSheetno));
            Integer arRowNo = cell.getRowIndex();
            Integer arCellNo = cell.getColumnIndex();
            Map paramMap = excelMaster.interChangeKV(commonDaoInterface.queryForMap("SELECT ParamTypecode,Description FROM ParamType"));
            VolumeAndRevBean volumeAndRevBean = getAnnualRevenue(excelProcessingBean, wb.getSheetAt(volumeSheetno), arRowNo, arCellNo + 1, user, colNumBUMap, Integer.parseInt((String) paramMap.get("Revenue")));
            Map revenueValidMap = validateBussinessUnit(volumeAndRevBean.getBussiness_actualBean_list());
            //End Revenue validation

            //Volume validation
            Map rowNoSubProcess = BasicExcelOperation.getRowNumber(subProc, wb.getSheetAt(volumeSheetno));
            List<VolumeAndRevBean> volumeAndRevBeans = getVolumePerSubProccess(wb.getSheetAt(volumeSheetno), subprRowNo + 1, volDescCellNo + 1, excelProcessingBean, volDescCellNo, rowNoSubProcess, user, Integer.parseInt((String) paramMap.get("Volume")), colNumBUMap);
            volumeAndRevBeans = removeNullBeanFromList(volumeAndRevBeans);
            Map<String, Map<String, List<String>>> validatedVolumeMap = validateVolumeData(volumeAndRevBeans);
            validatedVolumeMap = sortVolumeDataByFalseList(validatedVolumeMap);

            //Volume validation ends.
            if (!bc_center.equals(excelProcessingBean.getBussiness_center())) {
                excelProcessingBean.setStatus("Failed");
                String message = "Business Center in Volume and Revenue worksheet:" + bc_center + "does not match with Resource data input Business Center:" + excelProcessingBean.getBussiness_center();
                excelProcessingBean.setError_message(message);
            } else if (!dateValue.equals(excelProcessingBean.getDate())) {
                excelProcessingBean.setStatus("Failed");
                String message = "Date as of in Volume and Revenue worksheet:" + dateValue + "does not match with Resource data input Date as of:" + excelProcessingBean.getDate();
                excelProcessingBean.setError_message(message);
            } else if (clientMap.containsKey("false")) {
                excelProcessingBean.setStatus("Failed");
                List<String> falseList = (List<String>) clientMap.get("false");
                String message = ExcelValidator.getErrorList(falseList, "Client used in Volume to be defined in Settings:");
                excelProcessingBean.setError_message(message);
            } else if (volDesdcMap.containsKey("false")) {
                excelProcessingBean.setStatus("Failed");
                List<String> falseList = (List<String>) volDesdcMap.get("false");
                String message = ExcelValidator.getErrorList(falseList, "Volume Description to be defined in Settings:");
                excelProcessingBean.setError_message(message);
            } else if (subprocValidMap.containsKey("false")) {
                excelProcessingBean.setStatus("Failed");
                List<String> falseList = (List<String>) subprocValidMap.get("false");
                String message = ExcelValidator.getErrorList(falseList, "Subprocess used in Volume to be defined in Settings:");
                excelProcessingBean.setError_message(message);
            } else if (revenueValidMap.containsKey("false")) {
                excelProcessingBean.setStatus("Failed");
                List<String> falseList = (List<String>) revenueValidMap.get("false");
                String message = "Revenue for clients " + ExcelValidator.getErrorList(falseList, "") + " missing.";
                excelProcessingBean.setError_message(message);
            } else if (!validatedVolumeMap.isEmpty()) {
                excelProcessingBean.setStatus("Failed");
                excelProcessingBean.setError_message(getVolumedataErrorMessage(validatedVolumeMap, "Volume data for clients "));
            }else if (volAndRevClientUniqueMap.containsKey("false")) {
                excelProcessingBean.setStatus("Failed");
                List<String> falseList = (List<String>) volAndRevClientUniqueMap.get("false");
                String message = ExcelValidator.getErrorList(falseList, "Client used in Volume worksheet is repeated.");
                excelProcessingBean.setError_message(message);
            }  
            else {
                excelProcessingBean.setStatus("Success");
                excelProcessingBean.setAnnual_revenue(volumeAndRevBean);
                excelProcessingBean.setVolume_list(volumeAndRevBeans);
            }

        } else {
            excelProcessingBean.setStatus("Failed");
            excelProcessingBean.setError_message(excelProcessingBean.getDate() + " Data exists in database");
            excelProcessingBean.setDate_flag(false);
        }

        return excelProcessingBean;
    }

    @Override
    public VolumeAndRevBean getAnnualRevenue(ExcelProcessingBean excelProcessingBean, Sheet sheet, Integer rowNo, Integer colNo, UserBean user, Map<Integer, String> clientColumMap, Integer paramType) {
        VolumeAndRevBean volumeAndRevBean = new VolumeAndRevBean();
        volumeAndRevBean.setBusCernter_id(excelProcessingBean.getBusiness_center_id());
        volumeAndRevBean.setOrag_id(user.getOrganizationid());
        volumeAndRevBean.setParameter_type(paramType);
        volumeAndRevBean.setStatement_date(excelProcessingBean.getDate());
        volumeAndRevBean.setSubProcess_id(0);
        volumeAndRevBean.setVolume_desc_id(0);
        Row row = sheet.getRow(rowNo);
        Iterator cellIt = row.cellIterator();
        List<BUActualBean> bUActualBeans = new ArrayList<BUActualBean>();
        while (cellIt.hasNext()) {
            Cell cell = (Cell) cellIt.next();
            Integer cellColNo = cell.getColumnIndex();
            if (cellColNo >= colNo) {
                if (clientColumMap.containsKey(cellColNo)) {
                    BUActualBean bUActualBean = new BUActualBean();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (cell.getStringCellValue().equals("")) {
                        bUActualBean.setActual_value(Double.parseDouble("-1"));
                    } else {
                        bUActualBean.setActual_value(Double.parseDouble(cell.getStringCellValue()));
                    }
                    bUActualBean.setBisiness_unit_name(clientColumMap.get(cellColNo));
                    bUActualBeans.add(bUActualBean);
                }
            }
        }
        volumeAndRevBean.setBussiness_actualBean_list(bUActualBeans);
        return volumeAndRevBean;
    }

    @Override
    public Map<String, List<String>> validateBussinessUnit(List<BUActualBean> bu_actual_list) {
        Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
        Iterator bUActualListIt = bu_actual_list.iterator();
        List<String> falseList = new ArrayList<String>();
        List<String> trueList = new ArrayList<String>();
        while (bUActualListIt.hasNext()) {
            BUActualBean bUActualBean = (BUActualBean) bUActualListIt.next();
            if (bUActualBean.getActual_value() == Double.parseDouble("-1")) {
                falseList.add(bUActualBean.getBisiness_unit_name());
            } else {
                trueList.add(bUActualBean.getBisiness_unit_name());
            }

        }
        if (!falseList.isEmpty()) {
            resultMap.put("false", falseList);
        }
        if (!trueList.isEmpty()) {
            resultMap.put("true", trueList);
        }
        return resultMap;
    }

    @Override
    public List<VolumeAndRevBean> getVolumePerSubProccess(Sheet sheet, Integer rowNo, Integer colNo, ExcelProcessingBean excelProcessingBean, Integer volDescColNo, Map<Integer, String> subProcessRowMap, UserBean user, Integer paramType, Map<Integer, String> clientColumMap) {
        List<VolumeAndRevBean> volAndRevBeanList = new ArrayList<VolumeAndRevBean>();
        Iterator rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = (Row) rowIterator.next();
            Integer rowIndex = row.getRowNum();
            if (rowIndex >= rowNo) {
                VolumeAndRevBean volumeAndRevBean = new VolumeAndRevBean();
                volumeAndRevBean.setSubProcess_name(subProcessRowMap.get(rowIndex));
                volumeAndRevBean.setBusCernter_id(excelProcessingBean.getBusiness_center_id());
                volumeAndRevBean.setOrag_id(user.getOrganizationid());
                volumeAndRevBean.setParameter_type(paramType);
                volumeAndRevBean.setStatement_date(excelProcessingBean.getDate());
                Iterator cellIterator = row.cellIterator();
                List<BUActualBean> bUActualBeans = new ArrayList<BUActualBean>();
                while (cellIterator.hasNext()) {
                    Cell cell = (Cell) cellIterator.next();
                    Integer cellIndex = cell.getColumnIndex();
                    if (cellIndex == volDescColNo) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String volDescName = cell.getStringCellValue();
                        if (volDescName.equals("") || volDescName == null) {
                            continue;
                        }
                        volumeAndRevBean.setVolume_desc(cell.getStringCellValue());
                    }
                    if (cellIndex >= colNo) {
                        if (clientColumMap.containsKey(cellIndex)) {
                            BUActualBean bUActualBean = new BUActualBean();
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            if (cell.getStringCellValue().equals("")) {
                                bUActualBean.setActual_value(Double.parseDouble("-1"));
                            } else {
                                bUActualBean.setActual_value(Double.parseDouble(cell.getStringCellValue()));
                            }
                            bUActualBean.setBisiness_unit_name(clientColumMap.get(cellIndex));
                            bUActualBeans.add(bUActualBean);
                        }
                    }
                }
                if (!bUActualBeans.isEmpty()) {
                    volumeAndRevBean.setBussiness_actualBean_list(bUActualBeans);
                }
                volAndRevBeanList.add(volumeAndRevBean);
            }
        }
        return volAndRevBeanList;
    }

    @Override
    public List<VolumeAndRevBean> removeNullBeanFromList(List<VolumeAndRevBean> volumeAndRevBeans) {
        List<VolumeAndRevBean> resultList = new ArrayList<VolumeAndRevBean>();
        Iterator inputListIt = volumeAndRevBeans.iterator();
        while (inputListIt.hasNext()) {
            VolumeAndRevBean volumeAndRevBean = (VolumeAndRevBean) inputListIt.next();
            if (volumeAndRevBean.getSubProcess_name() != null && volumeAndRevBean.getVolume_desc() != null && volumeAndRevBean.getBussiness_actualBean_list() != null) {
                resultList.add(volumeAndRevBean);
            }
        }
        return resultList;
    }

    @Override
    public Map<String, Map<String, List<String>>> validateVolumeData(List<VolumeAndRevBean> volumeAndRevBeans) {
        Map<String, Map<String, List<String>>> resultMap = new HashMap<String, Map<String, List<String>>>();
        Iterator listIt = volumeAndRevBeans.iterator();
        while (listIt.hasNext()) {
            VolumeAndRevBean volumeAndRevBean = (VolumeAndRevBean) listIt.next();
            resultMap.put(volumeAndRevBean.getSubProcess_name(), validateBussinessUnit(volumeAndRevBean.getBussiness_actualBean_list()));
        }
        return resultMap;
    }

    @Override
    public Map<String, Map<String, List<String>>> sortVolumeDataByFalseList(Map<String, Map<String, List<String>>> inputMap) {
        Map<String, Map<String, List<String>>> resultMap = new HashMap<String, Map<String, List<String>>>();
        Iterator mapIt = inputMap.entrySet().iterator();
        while (mapIt.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) mapIt.next();
            Map<String, List<String>> insideMap = (Map<String, List<String>>) mapEntry.getValue();
            if (insideMap.containsKey("false")) {
                resultMap.put(mapEntry.getKey().toString(), insideMap);
            }
        }
        return resultMap;
    }

    @Override
    public String getVolumedataErrorMessage(Map<String, Map<String, List<String>>> inputMap, String message) {
        String resultMessage = "";
        Iterator mapIt = inputMap.entrySet().iterator();
        while (mapIt.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) mapIt.next();
            Map<String, List<String>> insideMap = (Map<String, List<String>>) mapEntry.getValue();
            if (insideMap.containsKey("false")) {
                resultMessage += message + ExcelValidator.getErrorList((List<String>) insideMap.get("false"), "") + " for " + mapEntry.getKey().toString() + " are missing.";
            }
        }
        return resultMessage;
    }

    @Override
    public void deleteVolumeAndRevenue(String date, String bcid) {
        jdbcTemplate.update("DELETE FROM `ActualReference` WHERE `BusinessCenterId`=? AND `StatementDate`=?", new Object[]{bcid, date});
    }

    public Boolean checkDate(String date, Integer bcid) {
        Boolean flag = true;
        try {
            String recieve_date = jdbcTemplate.queryForObject("SELECT DISTINCT `StatementDate` FROM `ActualReference` WHERE `BusinessCenterId`=? AND `StatementDate`=?", new Object[]{bcid,date}, String.class);
            if (recieve_date != null) {
                flag = false;
            }
        } catch (Exception ex) {
            return true;
        }
        return flag;
    }

    @Override
    public void saveVolumeBean(VolumeAndRevBean volumeAndRevBean) {
        try {
            List<BUActualBean> bUActualBeans = volumeAndRevBean.getBussiness_actualBean_list();
            Integer status = Integer.parseInt(SQLSelector.getQuery("active.status"));
            Iterator listIt = bUActualBeans.iterator();
            while (listIt.hasNext()) {
                BUActualBean bUActualBean = (BUActualBean) listIt.next();
                jdbcTemplate.update(SQLSelector.getQuery("actualreferencetemp.insert"), new Object[]{
                            /*OrganizationId*/volumeAndRevBean.getOrag_id(),
                            /*BusinessUnitId*/ bUActualBean.getBusiness_unit_id(),
                            /*BusinessCenterId*/ volumeAndRevBean.getBusCernter_id(),
                            /*SubProcessId*/ volumeAndRevBean.getSubProcess_id(),
                            /*StatementDate*/ volumeAndRevBean.getStatement_date(),
                            /*ParamTypeCode*/ volumeAndRevBean.getParameter_type(),
                            /*ActualValue*/ bUActualBean.getActual_value(),
                            /*Status*/ status,
                            /*VolumeDescId*/ volumeAndRevBean.getVolume_desc_id()
                        });

            }
        } catch (Exception ex) {
        }
    }

    public VolumeAndRevBean setSubProcessId(VolumeAndRevBean volumeAndRevBean, Map subProcessMap) {
        Integer id = Integer.parseInt(subProcessMap.get(volumeAndRevBean.getSubProcess_name()).toString());
        volumeAndRevBean.setSubProcess_id(id);
        return volumeAndRevBean;
    }

    public VolumeAndRevBean setVolumeDescriptionId(VolumeAndRevBean volumeAndRevBean, Map volDescriptionMap) {
        Integer id = Integer.parseInt(volDescriptionMap.get(volumeAndRevBean.getVolume_desc()).toString());
        volumeAndRevBean.setVolume_desc_id(id);
        return volumeAndRevBean;
    }

    public BUActualBean setBussinessUnitId(BUActualBean bUActualBean, Map busUnitMap) {
        Integer id = Integer.parseInt(busUnitMap.get(bUActualBean.getBisiness_unit_name()).toString());
        bUActualBean.setBusiness_unit_id(id);
        return bUActualBean;
    }

    @Override
    public ExcelProcessingBean setVandRId(ExcelProcessingBean excelProcessingBean) {
        Map bus_unit_map = commonDaoInterface.queryForMap("SELECT `BusinessUnit`.`BusinessUnitName`,`BusinessUnit`.`BusinessUnitId` FROM `BusinessUnit`,`BusinessCenterBusinessUnit`,`BusinessCenter` WHERE `BusinessUnit`.`BusinessUnitId`=`BusinessCenterBusinessUnit`.`BusinessUnitId` AND `BusinessCenterBusinessUnit`.`BusinessCenterId`=`BusinessCenter`.`BusinessCenterId` AND `BusinessCenter`.`BusinessCenterId`=?", new Object[]{excelProcessingBean.getBusiness_center_id()});
        Map vol_desc_map = commonDaoInterface.queryForMap("SELECT `VolumeDescription`,`VolumeDescId` FROM `VolumeDescription`", new Object[]{});
        Map sub_process_map = commonDaoInterface.queryForMap("SELECT `SubProcessName`,`SubProcessId` FROM `SubProcess`", new Object[]{});
        VolumeAndRevBean revenueBean = excelProcessingBean.getAnnual_revenue();

        List<BUActualBean> bUActualBeans = revenueBean.getBussiness_actualBean_list();
        List<BUActualBean> bUActualBeans1 = new ArrayList<BUActualBean>();
        Iterator it = bUActualBeans.iterator();
        while (it.hasNext()) {
            BUActualBean bUActualBean = (BUActualBean) it.next();
            bUActualBean = setBussinessUnitId(bUActualBean, bus_unit_map);
            bUActualBeans1.add(bUActualBean);
        }
        revenueBean.setBussiness_actualBean_list(bUActualBeans1);
        excelProcessingBean.setAnnual_revenue(revenueBean);
        List<VolumeAndRevBean> volumeAndRevBeans = new ArrayList<VolumeAndRevBean>();
        it = excelProcessingBean.getVolume_list().iterator();
        while (it.hasNext()) {
            VolumeAndRevBean volumeAndRevBean = (VolumeAndRevBean) it.next();
            volumeAndRevBean = setSubProcessId(volumeAndRevBean, sub_process_map);
            volumeAndRevBean = setVolumeDescriptionId(volumeAndRevBean, vol_desc_map);
            List<BUActualBean> iBUActualBeans = volumeAndRevBean.getBussiness_actualBean_list();
            List<BUActualBean> iBUActualBeans1 = new ArrayList<BUActualBean>();
            Iterator it1 = iBUActualBeans.iterator();
            while (it1.hasNext()) {
                BUActualBean bUActualBean = (BUActualBean) it1.next();
                bUActualBean = setBussinessUnitId(bUActualBean, bus_unit_map);
                iBUActualBeans1.add(bUActualBean);
            }
            volumeAndRevBeans.add(volumeAndRevBean);

        }
        excelProcessingBean.setVolume_list(volumeAndRevBeans);
        return excelProcessingBean;
    }

    @Override
    public void saveToPermenent(ExcelProcessingBean excelProcessingBean) {
        try {
            jdbcTemplate.update("INSERT INTO ActualReference (OrganizationId,BusinessUnitId,BusinessCenterId,SubProcessId,StatementDate,ParamTypeCode,ActualValue,Status,VolumeDescId) (SELECT OrganizationId,BusinessUnitId,BusinessCenterId,SubProcessId,StatementDate,ParamTypeCode,ActualValue,STATUS,VolumeDescId FROM `TempActualReference` WHERE BusinessCenterId=? AND StatementDate=?)", new Object[]{excelProcessingBean.getBusiness_center_id(), excelProcessingBean.getDate()});
        } catch (Exception ex) {
        }
    }

    @Override
    public void saveToTemperory(ExcelProcessingBean excelProcessingBean) {
        jdbcTemplate.update("DELETE FROM `TempActualReference` WHERE `BusinessCenterId`=? AND `StatementDate`=?", new Object[]{excelProcessingBean.getBusiness_center_id(), excelProcessingBean.getDate()});
        saveVolumeBean(excelProcessingBean.getAnnual_revenue());
        List<VolumeAndRevBean> volumeAndRevBeans = excelProcessingBean.getVolume_list();
        Iterator it = volumeAndRevBeans.iterator();
        while (it.hasNext()) {
            VolumeAndRevBean volumeAndRevBean = (VolumeAndRevBean) it.next();
            saveVolumeBean(volumeAndRevBean);
        }
    }

    @Override
    public ExcelProcessingBean getExcelDetails(Workbook wb, ExcelProcessingBean excelProcessingBean, UserBean user) {
        try {
            Integer volumeSheetno=PropertyFile.excelInt("volumerevenue.sheetno");
            excelProcessingBean.setError_message("");
            excelProcessingBean.setBussiness_center(excelOperationDao.readCell(wb, 5, 4, volumeSheetno));
            excelProcessingBean.setLocation_cord(excelOperationDao.readCell(wb, 7, 4, volumeSheetno));
            excelProcessingBean.setDate(DateConvertion.convertToMysqlFormat(excelOperationDao.validateDate(wb, volumeSheetno, 6, 4)));
            excelProcessingBean.setBusiness_center_id(excelOperationDao.checkBusinessCenter(excelProcessingBean.getBussiness_center(), user));
            if (excelProcessingBean.getBusiness_center_id() != null) {
                excelProcessingBean.setBusinessc_flag(Boolean.TRUE);
            } else {
                excelProcessingBean.setBusinessc_flag(Boolean.FALSE);
            }

        } catch (Exception ex) {
        }
        return excelProcessingBean;
    }
}
