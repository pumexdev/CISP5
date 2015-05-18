/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import com.pumex.ConnectedInsight.Excel.Bean.BUActualBean;
import com.pumex.ConnectedInsight.Excel.Bean.CenterAvgBean;
import com.pumex.ConnectedInsight.Excel.Bean.ExcelProcessingBean;
import com.pumex.ConnectedInsight.Excel.Bean.KPICenterAvgBean;
import com.pumex.ConnectedInsight.Excel.Bean.KPIScoreBean;
import com.pumex.ConnectedInsight.Property.DateConvertion;
import com.pumex.ConnectedInsight.Property.PropertyFile;
import com.pumex.ConnectedInsight.common.Dao.CommonDaoInterface;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vishnu
 */
@Repository
public class KPIDaoImpl implements IKPIDao {

    @Autowired
    CommonDaoInterface commonDaoInterface;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    IExcelVolAndRevDao iExcelVolAndRevDao;
    @Autowired
    ExcelOperationDao excelOperationDao;

    @Override
    public ExcelProcessingBean validateKPIWSheet(Workbook wb, ExcelProcessingBean excelProcessingBean, UserBean user) {
        Integer kpiSheetno=PropertyFile.excelInt("kpi.sheetno");
        excelProcessingBean = validateKPITemplate(wb, excelProcessingBean);
        if (!excelProcessingBean.getStatus().equals("Failed")) {
            if (checkDate(excelProcessingBean.getDate(), excelProcessingBean.getBusiness_center_id()) != false) {
                Sheet sheet = wb.getSheetAt(kpiSheetno);
                Cell bc_cell = BasicExcelOperation.getExcelCell(sheet, 5, 2, "String");
                Cell date_cell = BasicExcelOperation.getExcelCell(sheet, 6, 2, "Double");
                String bc_center = bc_cell.getStringCellValue();
                String dateValue = DateConvertion.convertToMysqlFormat(date_cell.getDateCellValue());
                List<String> bu = BasicExcelOperation.getDataFromRow(sheet.getRow(10));

                Cell cell = BasicExcelOperation.getExcelCell("Sub-Process", sheet);
                Integer subprRowNo = cell.getRowIndex();
                Integer subprCellNo = cell.getColumnIndex();
                List<String> subProc = BasicExcelOperation.getDataFromColum(sheet, subprRowNo + 1, subprCellNo);
                Map subprocValidMap = ExcelValidator.validateList(subProc, commonDaoInterface.queryForMap("SELECT `SubProcessId`,`SubProcessName` FROM `SubProcess`"));
                cell = BasicExcelOperation.getExcelCell("KPI", sheet);
                Integer kpiRowNo = cell.getRowIndex();
                Integer kpiCellNo = cell.getColumnIndex();
                List<String> kpiList = BasicExcelOperation.getDataFromColum(sheet, kpiRowNo + 1, kpiCellNo);

                Map kpiMap = ExcelValidator.validateList(kpiList, commonDaoInterface.queryForMap("SELECT KPIId,KPIDescription FROM KPI"));
                List<String> bu1 = bu;
                bu1.remove("Center Average");
                /*Validating Client in kpi sheet*/
                Map kpiBuUniqueValidMap=ExcelValidator.validateUniqueEntry(bu1);
                Map clientMap = ExcelValidator.validateList(bu1, commonDaoInterface.queryForMap("SELECT `BusinessUnit`.`BusinessUnitId`,`BusinessUnit`.`BusinessUnitName` FROM `BusinessUnit`,`BusinessCenterBusinessUnit`,`BusinessCenter` WHERE `BusinessUnit`.`BusinessUnitId`=`BusinessCenterBusinessUnit`.`BusinessUnitId` AND `BusinessCenterBusinessUnit`.`BusinessCenterId`=`BusinessCenter`.`BusinessCenterId` AND `BusinessCenter`.`BusinessCenterId`=?", new Object[]{excelProcessingBean.getBusiness_center_id()}));

                Map excelKPISubprocessMap = getSubProceAndKPI(sheet);
                Map kpisubprocessMap = getValidateKpiAndSubproce(excelKPISubprocessMap, commonDaoInterface.queryForMap("SELECT `KPIDescription`,`SubProcessId` FROM `KPI`"), commonDaoInterface.queryForMap("SELECT `SubProcessName`,`SubProcessId` FROM `SubProcess`"));

                /*Unique list*/
                kpiList.remove("");
                Map kpiUniqueMap = getValidateKPI(kpiList);

                Map rowKPIMap = BasicExcelOperation.getRowNumber((List<String>) kpiUniqueMap.get("true"), sheet);
                Map colNumBUMap = BasicExcelOperation.getColumNumber(bu1, sheet);
//                Map colNumBUMap = BasicExcelOperation.getColumNumber(bu, wb.getSheetAt(0));
//                Map rowSubProcess = BasicExcelOperation.getRowNumber(subProc, wb.getSheetAt(0));

                //Read Costcenter
                cell = BasicExcelOperation.getExcelCell("Center Average", sheet);
                Integer costRowNo = cell.getRowIndex();
                Integer costCellNo = cell.getColumnIndex();
                KPICenterAvgBean kPICenterAvgBean = getCenterAverage(excelProcessingBean, user, sheet, subprRowNo, rowKPIMap, subprCellNo, kpiCellNo, costCellNo);
                kPICenterAvgBean.setCenterAverageBeans(removeNullBeanFromList(kPICenterAvgBean.getCenterAverageBeans()));
                Map kpiCenterValidMap = validateKPICenterAverage(kPICenterAvgBean.getCenterAverageBeans());

                //Read KPIScore
                List<KPIScoreBean> kPIScoreBeans = getKPIScore(excelProcessingBean, user, sheet, subprCellNo, rowKPIMap, subprCellNo, kpiCellNo, costCellNo, colNumBUMap);
                Map<String, Map<String, List<String>>> validatedKPIScoreMap = validateVolumeData(kPIScoreBeans);
                validatedKPIScoreMap = iExcelVolAndRevDao.sortVolumeDataByFalseList(validatedKPIScoreMap);


                if (!bc_center.equals(excelProcessingBean.getBussiness_center())) {
                    excelProcessingBean.setStatus("Failed");
                    String message = "Business Center in Others KPI's:" + bc_center + " does not match with Resource data input Business Center:" + excelProcessingBean.getBussiness_center();
                    excelProcessingBean.setError_message(message);
                } /*else if (!dateValue.equals(excelProcessingBean.getDate())) {
                    excelProcessingBean.setStatus("Failed");
                    String message = "Others KPI'S Date as of:" + dateValue + " not matching with Resource data Input Date as of:" + excelProcessingBean.getDate();
                    excelProcessingBean.setError_message(message);
                }*/ else if (clientMap.containsKey("false")) {
                    excelProcessingBean.setStatus("Failed");
                    List<String> falseList = (List<String>) clientMap.get("false");
                    String message = ExcelValidator.getErrorList(falseList, "Client used in KPI's to be defined in Settings:");
                    excelProcessingBean.setError_message(message);
                } else if (kpiMap.containsKey("false")) {
                    excelProcessingBean.setStatus("Failed");
                    List<String> falseList = (List<String>) kpiMap.get("false");
                    String message = ExcelValidator.getErrorList(falseList, "KPI to be defined in Settings:");
                    excelProcessingBean.setError_message(message);
                } else if (subprocValidMap.containsKey("false")) {
                    excelProcessingBean.setStatus("Failed");
                    List<String> falseList = (List<String>) subprocValidMap.get("false");
                    String message = ExcelValidator.getErrorList(falseList, "Subprocess used in KPI to be defined in Settings:");
                    excelProcessingBean.setError_message(message);
                } else if (kpisubprocessMap.containsKey("false")) {
                    excelProcessingBean.setStatus("Failed");
                    List<String> falseList = (List<String>) kpisubprocessMap.get("false");
                    String message = ExcelValidator.getErrorList(falseList, "Subprocess and KPI used in KPI to be defined in Settings:");
                    excelProcessingBean.setError_message(message);
                } else if (kpiUniqueMap.containsKey("false")) {
                    excelProcessingBean.setStatus("Failed");
                    List<String> falseList = (List<String>) kpisubprocessMap.get("false");
                    String message = ExcelValidator.getErrorList(falseList, "KPI used in KPI worksheet is repeated.");
                    excelProcessingBean.setError_message(message);
                } else if (kpiCenterValidMap.containsKey("false")) {
                    excelProcessingBean.setStatus("Failed");
                    List<String> falseList = (List<String>) kpiCenterValidMap.get("false");
                    String message = "Center Averge for KPI " + ExcelValidator.getErrorList(falseList, "") + " is missing";
                    excelProcessingBean.setError_message(message);
                }else if (!validatedKPIScoreMap.isEmpty()) {
                    excelProcessingBean.setStatus("Failed");
                    excelProcessingBean.setError_message(iExcelVolAndRevDao.getVolumedataErrorMessage(validatedKPIScoreMap,"KPI Score data for clients "));
                }else if (kpiBuUniqueValidMap.containsKey("false")) {
                    excelProcessingBean.setStatus("Failed");
                    List<String> falseList = (List<String>) kpiBuUniqueValidMap.get("false");
                    String message = ExcelValidator.getErrorList(falseList, "Client used in KPI worksheet is repeated.");
                    excelProcessingBean.setError_message(message);
                }
                else {
                    excelProcessingBean.setStatus("Success");
                    excelProcessingBean.setkPICenterAvgBean(kPICenterAvgBean);
                    excelProcessingBean.setkPIScoreBeans(kPIScoreBeans);
                }

            } else {
                excelProcessingBean.setStatus("Failed");
                excelProcessingBean.setError_message(excelProcessingBean.getDate() + " Data exists in database");
                excelProcessingBean.setDate_flag(false);
            }
        }
        return excelProcessingBean;
    }

    public Boolean checkDate(String date, Integer bcid) {
        Boolean flag = true;
        try {
            String recieve_date = jdbcTemplate.queryForObject("SELECT DISTINCT StatementDate FROM KPIScore WHERE BusinessCenterId=? AND StatementDate=?", new Object[]{bcid, date}, String.class);
            if (recieve_date != null) {
                flag = false;
            }
        } catch (Exception ex) {
            return true;
        }
        return flag;
    }

    @Override
    public ExcelProcessingBean validateKPITemplate(Workbook wb, ExcelProcessingBean excelProcessingBean) {
        Boolean flag = false;
        Integer kpiSheetno=PropertyFile.excelInt("kpi.sheetno");
        try{
        Sheet sheet = wb.getSheetAt(kpiSheetno);
        if (validateCenterCell(sheet)) {
            if (validateCenterValue(sheet, excelProcessingBean.getBussiness_center())) {
                if (validateDateCell(sheet)) {
                    //if (validateDateValue(wb, excelProcessingBean.getDate())) {
                        if (validateSubprocessCell(sheet)) {
                            if (validateKPICell(sheet)) {
                                if (validateCenterAvgCell(sheet)) {
                                    excelProcessingBean.setStatus("");
                                } else {
                                    excelProcessingBean.setStatus("Failed");
                                    excelProcessingBean.setError_message("Invalid KPI Template: Center Average misplaced.");
                                }
                            } else {
                                excelProcessingBean.setStatus("Failed");
                                excelProcessingBean.setError_message("Invalid KPI Template: KPI misplaced.");
                            }
                        } else {
                            excelProcessingBean.setStatus("Failed");
                            excelProcessingBean.setError_message("Invalid KPI Template: Sub-process misplaced.");
                        }
                    /*} else {
                        excelProcessingBean.setStatus("Failed");
                        excelProcessingBean.setError_message("Mismatch date values in VolumeRevenueSheet and KPI Sheet ");
                    }*/
                } else {
                    excelProcessingBean.setStatus("Failed");
                    excelProcessingBean.setError_message("Invalid KPI Template: Date misplaced");
                }
            } else {
                excelProcessingBean.setStatus("Failed");
                excelProcessingBean.setError_message("Mismatch in Volume/Revenue sheet center values and KPI sheet ");
            }
        } else {
            excelProcessingBean.setStatus("Failed");
            excelProcessingBean.setError_message("Invalid KPI template: Center misplaced");
        }
        }catch(Exception ex){}
        return excelProcessingBean;
    }

    public Boolean validateCenterCell(Sheet sheet) {
        Boolean flag = false;
        Cell centerCell = BasicExcelOperation.getExcelCell("Center", sheet);
        Integer cellRowIndex = centerCell.getRowIndex();
        Integer cellColumIndex = centerCell.getColumnIndex();
        if (cellRowIndex == 5 && cellColumIndex == 1) {
            Cell centerValueCell = BasicExcelOperation.getExcelCell(sheet, 5, 2, "String");
            String cellValue = centerValueCell.getStringCellValue();
            if (!cellValue.equals("")) {
                flag = true;
            }
        }
        return true;
    }

    public Boolean validateCenterValue(Sheet sheet, String value) {
        Boolean flag = false;
        Cell centerValueCell = BasicExcelOperation.getExcelCell(sheet, 5, 2, "String");
        String cellValue = centerValueCell.getStringCellValue();
        if (cellValue.equals(value)) {
            flag = true;
        }
        return flag;
    }

    public Boolean validateDateCell(Sheet sheet) {
        Boolean flag = false;
        Cell dateCell = BasicExcelOperation.getExcelCell("Date", sheet);
        Integer cellRowIndex = dateCell.getRowIndex();
        Integer cellColumIndex = dateCell.getColumnIndex();
        if (cellRowIndex == 6 && cellColumIndex == 1) {
            Cell dateValueCell = BasicExcelOperation.getExcelCell(sheet, 6, 2, "String");
            String cellValue = dateValueCell.getStringCellValue();
            if (!cellValue.equals("")) {
                flag = true;
            }
        }
        return flag;
    }

    public Boolean validateDateValue(Sheet sheet, String value) {
        Boolean flag = false;
        Cell centerValueCell = BasicExcelOperation.getExcelCell(sheet, 6, 2, "Double");
        String dateValue = DateConvertion.convertToMysqlFormat(centerValueCell.getDateCellValue());
        if (dateValue.equals(value)) {
            flag = true;
        }
        return flag;
    }
    
    public Boolean validateDateValue(Workbook wb, String value) throws IOException, InvalidFormatException {
        Boolean flag = false;
        Integer kpiSheetno=PropertyFile.excelInt("kpi.sheetno");
        Cell DateCell=BasicExcelOperation.getExcelCell(wb.getSheetAt(kpiSheetno), 6, 2, "String");
        Date date=DateCell.getDateCellValue();
        String dateValue = DateConvertion.convertToMysqlFormat(excelOperationDao.validateDate(wb, kpiSheetno, 6, 2));
        if (dateValue.equals(value)) {
            flag = true;
        }
        return flag;
    }

    public Boolean validateSubprocessCell(Sheet sheet) {
        Boolean flag = false;
        Cell subProcessCell = BasicExcelOperation.getExcelCell("Sub-Process", sheet);
        Integer cellRowIndex = subProcessCell.getRowIndex();
        Integer cellColumIndex = subProcessCell.getColumnIndex();
        if (cellRowIndex == 11 && cellColumIndex == 1) {
            flag = true;
        }
        return flag;
    }

    public Boolean validateKPICell(Sheet sheet) {
        Boolean flag = false;
        Cell kpiCell = BasicExcelOperation.getExcelCell("KPI", sheet);
        Integer cellRowIndex = kpiCell.getRowIndex();
        Integer cellColumIndex = kpiCell.getColumnIndex();
        if (cellRowIndex == 11 && cellColumIndex == 3) {
            flag = true;
        }
        return flag;
    }

    public Boolean validateCenterAvgCell(Sheet sheet) {
        Boolean flag = false;
        Cell caCell = BasicExcelOperation.getExcelCell("Center Average", sheet);
        Integer cellRowIndex = caCell.getRowIndex();
        Integer cellColumIndex = caCell.getColumnIndex();
        if (cellRowIndex == 10 && cellColumIndex == 4) {
            flag = true;
        }
        return flag;
    }

    public Map getSubProceAndKPI(Sheet sheet) {
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        Cell subprocessCell = BasicExcelOperation.getExcelCell("Sub-Process", sheet);
        Integer subProcessRowIndex = subprocessCell.getRowIndex();
        Integer subProcessColIndex = subprocessCell.getColumnIndex();
        Cell kpiCell = BasicExcelOperation.getExcelCell("KPI", sheet);
        Integer kpiCellRowIndex = kpiCell.getRowIndex();
        Integer kpiCellColIndex = kpiCell.getColumnIndex();
        Iterator rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row insideRow = (Row) rowIterator.next();
            if (insideRow.getRowNum() > subProcessRowIndex) {
                Iterator cellIterator = insideRow.cellIterator();
                String sprocessvalue = "";
                String kpiValue = "";
                while (cellIterator.hasNext()) {
                    Cell insideCell = (Cell) cellIterator.next();
                    if (insideCell.getColumnIndex() == subProcessColIndex) {
                        insideCell.setCellType(Cell.CELL_TYPE_STRING);
                        sprocessvalue = insideCell.getStringCellValue();
                    }
                    if (insideCell.getColumnIndex() == kpiCellColIndex) {
                        insideCell.setCellType(Cell.CELL_TYPE_STRING);
                        kpiValue = insideCell.getStringCellValue();
                    }
                }
                if (!sprocessvalue.equals("") && !kpiValue.equals("")) {
                    resultMap.put(sprocessvalue, kpiValue);
                }
            }
        }
        return resultMap;
    }

    public Map<String, List<String>> getValidateKpiAndSubproce(Map excelKPISubMap, Map KPIMap, Map subprocessMap) {
        Iterator mapIt = excelKPISubMap.entrySet().iterator();
        Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
        List trueList = new ArrayList<String>();
        List falseList = new ArrayList<String>();
        while (mapIt.hasNext()) {
            Map.Entry entry = (Map.Entry) mapIt.next();
            String kpirelatedSpId = KPIMap.get(entry.getValue().toString()).toString();
            String excelSPId = subprocessMap.get(entry.getKey().toString()).toString();
            if (excelSPId.equals(kpirelatedSpId)) {
                trueList.add(entry.getKey().toString());
            } else {
                falseList.add(entry.getKey().toString());
            }
        }
        if (!trueList.isEmpty()) {
            resultMap.put("true", trueList);
        }
        if (!falseList.isEmpty()) {
            resultMap.put("false", falseList);
        }
        return resultMap;
    }

    public Map<String, List<String>> getValidateKPI(List<String> kpiLIst) {
        Map<String, List<String>> resultMap = new LinkedHashMap<String, List<String>>();
        List<String> trueList = new ArrayList<String>();
        List<String> falseList = new ArrayList<String>();
        Iterator listIt = kpiLIst.iterator();
        while (listIt.hasNext()) {
            String value=(String)listIt.next();
            if (trueList.isEmpty()) {
                trueList.add(value);
            } else {
                if (trueList.contains(value)) {
                    falseList.add(value);
                } else {
                    trueList.add(value);
                }
            }
        }
        if (!trueList.isEmpty()) {
            resultMap.put("true", trueList);
        }
        if (!falseList.isEmpty()) {
            resultMap.put("false", falseList);
        }
        return resultMap;
    }

    public KPICenterAvgBean getCenterAverage(ExcelProcessingBean excelProcessingBean, UserBean user, Sheet sheet, Integer rowNoSubprocess, Map<Integer, String> kpiMap, Integer colNOSubprocess, Integer colNoKpi, Integer colNoCostCenter) {
        KPICenterAvgBean kPICenterAvgBean = new KPICenterAvgBean();
        kPICenterAvgBean.setBusCernter_id(excelProcessingBean.getBusiness_center_id());
        kPICenterAvgBean.setOrag_id(user.getOrganizationid());
        kPICenterAvgBean.setStatement_date(excelProcessingBean.getDate());
        Iterator rowIterator = sheet.rowIterator();
        List<CenterAvgBean> centerAvgBeanList = new ArrayList<CenterAvgBean>();
        while (rowIterator.hasNext()) {
            Row row = (Row) rowIterator.next();
            if (row.getRowNum() > rowNoSubprocess) {
                Iterator cellIterator = row.cellIterator();
                CenterAvgBean centerAvgBean = new CenterAvgBean();
                while (cellIterator.hasNext()) {
                    Cell cell = (Cell) cellIterator.next();
                    Integer cellNo = cell.getColumnIndex();
                    if (kpiMap.containsKey(row.getRowNum())) {
                        
                        if (cellNo == colNOSubprocess) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            String subprocess = cell.getStringCellValue();
                            if (subprocess.equals("") || subprocess == null) {
                                continue;
                            }
                            centerAvgBean.setSubProcess_name(subprocess);
                        }
                        if (cellNo == colNoKpi) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            String kpi = cell.getStringCellValue();
                            if (kpi.equals("") || kpi == null) {
                                continue;
                            }
                            centerAvgBean.setKpi_desc(kpiMap.get(row.getRowNum()).toString());
                        }
                        if (cellNo == colNoCostCenter) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            if (cell.getStringCellValue().equals("")) {
                                centerAvgBean.setCenter_average(Double.parseDouble("-1"));
                            } else {
                                centerAvgBean.setCenter_average(Double.parseDouble(cell.getStringCellValue()));
                            }
                        }
                        
                    }
                }
                centerAvgBeanList.add(centerAvgBean);
            }
        }
        kPICenterAvgBean.setCenterAverageBeans(centerAvgBeanList);
        return kPICenterAvgBean;
    }

    public List<KPIScoreBean> getKPIScore(ExcelProcessingBean excelProcessingBean, UserBean user, Sheet sheet, Integer rowNoSubprocess, Map<Integer, String> kpiMap, Integer colNOSubprocess, Integer colNoKpi, Integer colNoCostCenter, Map<Integer, String> buMap) {
        List<KPIScoreBean> kpiScoreList = new ArrayList<KPIScoreBean>();
        Iterator rowIt = sheet.rowIterator();
        while (rowIt.hasNext()) {
            Row row = (Row) rowIt.next();
            Integer rowNo = row.getRowNum();
            if (rowNo > rowNoSubprocess) {
                Iterator cellIterator = row.cellIterator();
                if (kpiMap.containsKey(rowNo)) {
                    KPIScoreBean kPIScoreBean = new KPIScoreBean();
                    kPIScoreBean.setBusCernter_id(excelProcessingBean.getBusiness_center_id());
                    kPIScoreBean.setKpi_desc(kpiMap.get(rowNo).toString());
                    kPIScoreBean.setOrag_id(user.getOrganizationid());
                    kPIScoreBean.setStatement_date(excelProcessingBean.getDate());
                    List<BUActualBean> bUActualBeans = new ArrayList<BUActualBean>();
                    while (cellIterator.hasNext()) {


                        Cell cell = (Cell) cellIterator.next();
                        Integer cellNo = cell.getColumnIndex();

                        if (cellNo == colNOSubprocess) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            String subprocess = cell.getStringCellValue();
                            if (subprocess.equals("") || subprocess == null) {
                                continue;
                            }
                            kPIScoreBean.setSubProcess_name(subprocess);
                        }
                        if (cellNo > colNoCostCenter) {
                            BUActualBean actualBean = new BUActualBean();
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            if (cell.getStringCellValue().equals("")) {
                                actualBean.setActual_value(Double.parseDouble("-1"));
                            } else {
                                actualBean.setActual_value(Double.parseDouble(cell.getStringCellValue()));
                            }
                            actualBean.setBisiness_unit_name(buMap.get(cellNo));
                            bUActualBeans.add(actualBean);
                        }

                    }
                    kPIScoreBean.setBussiness_actualBean_list(bUActualBeans);
                    kpiScoreList.add(kPIScoreBean);
                }
            }
        }
        return kpiScoreList;
    }

    @Override
    public void saveKPICenterAvgTemp(KPICenterAvgBean kPICenterAvgBean,UserBean user) {
        Iterator listIt = kPICenterAvgBean.getCenterAverageBeans().iterator();
        jdbcTemplate.update("DELETE FROM `TempKPICenterAverage` WHERE `BusinessCenterId`=? AND `StatementDate`=? AND `UserId`=?",new Object[]{
            /*BusinessCenterId*/ kPICenterAvgBean.getBusCernter_id(),
            /*StatementDate*/ kPICenterAvgBean.getStatement_date(),
            /*UserId*/user.getUserid()
        });
        while (listIt.hasNext()) {
            CenterAvgBean centerAvgBean = (CenterAvgBean) listIt.next();
            jdbcTemplate.update("INSERT INTO `TempKPICenterAverage` (`OrganizationId`, `BusinessCenterId`, `SubProcessId`, `KPIId`, `StatementDate`, `CenterAverage`,`UserId`) VALUES (?, ?, ?, ?, ?, ?, ?)", new Object[]{
                        /*OrganizationId*/kPICenterAvgBean.getOrag_id(),
                        /*BusinessCenterId*/ kPICenterAvgBean.getBusCernter_id(),
                        /*SubProcessId*/ centerAvgBean.getSubProcess_id(),
                        /*KPIId*/ centerAvgBean.getKpi_Id(),
                        /*StatementDate*/ kPICenterAvgBean.getStatement_date(),
                        /*CenterAverage*/ centerAvgBean.getCenter_average(),
                        /*UserId*/ user.getUserid()
                    });
        }

    }

    @Override
    public void saveKPIScoreTemp(List<KPIScoreBean> kPIScoreBeans,UserBean user) {
        Iterator kpiScoreBeansIt = kPIScoreBeans.listIterator();
        jdbcTemplate.update("DELETE FROM `TempKPIScore` WHERE `UserId`=?",new Object[]{
            /*UserId*/user.getUserid()
        });
        while (kpiScoreBeansIt.hasNext()) {
            KPIScoreBean kPIScoreBean = (KPIScoreBean) kpiScoreBeansIt.next();
            List buList = kPIScoreBean.getBussiness_actualBean_list();
            Iterator buListIt = buList.iterator();
            while (buListIt.hasNext()) {
                BUActualBean bUActualBean = (BUActualBean) buListIt.next();
                jdbcTemplate.update("INSERT INTO `TempKPIScore` (`OrganizationId`, `BusinessUnitId`, `BusinessCenterId`, `SubProcessId`, `KPIId`, `StatementDate`, `KPIScore`,`UserId`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)", new Object[]{
                            /*OrganizationId*/kPIScoreBean.getOrag_id(),
                            /*BusinessUnitId*/ bUActualBean.getBusiness_unit_id(),
                            /*BusinessCenterId*/ kPIScoreBean.getBusCernter_id(),
                            /*SubProcessId*/ kPIScoreBean.getSubProcess_id(),
                            /*KPIId*/ kPIScoreBean.getKpi_Id(),
                            /*StatementDate*/ kPIScoreBean.getStatement_date(),
                            /*KPIScore*/ bUActualBean.getActual_value(),
                            /*UserId*/ user.getUserid()
                        });
            }
        }
    }

    @Override
    public void saveToPermenentTable(ExcelProcessingBean excelProcessingBean,UserBean user) {
        jdbcTemplate.update("INSERT INTO `KPICenterAverage` (`OrganizationId`, `BusinessCenterId`, `SubProcessId`, `KPIId`, `StatementDate`, `CenterAverage`) (SELECT 	`OrganizationId`, `BusinessCenterId`, `SubProcessId`, `KPIId`, `StatementDate`, `CenterAverage` FROM `TempKPICenterAverage` WHERE `BusinessCenterId`=? AND `StatementDate`=? AND `UserId`=?)", new Object[]{excelProcessingBean.getBusiness_center_id(), excelProcessingBean.getDate(),user.getUserid()});
        jdbcTemplate.update("INSERT INTO `KPIScore` (`OrganizationId`, `BusinessUnitId`, `BusinessCenterId`, `SubProcessId`, `KPIId`, `StatementDate`, `KPIScore`) (SELECT 	`OrganizationId`, `BusinessUnitId`, `BusinessCenterId`, `SubProcessId`, `KPIId`, `StatementDate`, `KPIScore` FROM `TempKPIScore` WHERE `BusinessCenterId`=? AND `StatementDate`=? AND `UserId`=?)", new Object[]{excelProcessingBean.getBusiness_center_id(), excelProcessingBean.getDate(),user.getUserid()});

    }

    public Map<String, Map<String, List<String>>> validateVolumeData(List<KPIScoreBean> kPIScoreBeans) {
        Map<String, Map<String, List<String>>> resultMap = new HashMap<String, Map<String, List<String>>>();
        Iterator listIt = kPIScoreBeans.iterator();
        while (listIt.hasNext()) {
            KPIScoreBean kPIScoreBean = (KPIScoreBean) listIt.next();
            resultMap.put(kPIScoreBean.getKpi_desc(), iExcelVolAndRevDao.validateBussinessUnit(kPIScoreBean.getBussiness_actualBean_list()));
        }
        return resultMap;
    }

    public Map<String, List<String>> validateKPICenterAverage(List<CenterAvgBean> centerAvgBeans) {
        Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
        Iterator centerAvgBeansIt = centerAvgBeans.iterator();
        List<String> falseList = new ArrayList<String>();
        List<String> trueList = new ArrayList<String>();
        while (centerAvgBeansIt.hasNext()) {
            CenterAvgBean centerAvgBean = (CenterAvgBean) centerAvgBeansIt.next();
            if (centerAvgBean.getCenter_average() == Double.parseDouble("-1")) {
                falseList.add(centerAvgBean.getKpi_desc());
            } else {
                trueList.add(centerAvgBean.getKpi_desc());
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
    
    public List<CenterAvgBean> removeNullBeanFromList(List<CenterAvgBean> centerAvgBeans) {
        List<CenterAvgBean> resultList = new ArrayList<CenterAvgBean>();
        Iterator inputListIt = centerAvgBeans.iterator();
        while (inputListIt.hasNext()) {
            CenterAvgBean centerAvgBean = (CenterAvgBean) inputListIt.next();
            if (centerAvgBean.getSubProcess_name() != null && centerAvgBean.getKpi_desc() != null) {
                resultList.add(centerAvgBean);
            }
        }
        return resultList;
    }

    @Override
    public ExcelProcessingBean setID(ExcelProcessingBean excelProcessingBean) {
        KPICenterAvgBean kPICenterAvgBean=excelProcessingBean.getkPICenterAvgBean();
        List<CenterAvgBean> centerAvgBeans=(List<CenterAvgBean>)kPICenterAvgBean.getCenterAverageBeans();
        Iterator centerAvgBeanIt=centerAvgBeans.iterator();
        Map bus_unit_map = commonDaoInterface.queryForMap("SELECT `BusinessUnit`.`BusinessUnitName`,`BusinessUnit`.`BusinessUnitId` FROM `BusinessUnit`,`BusinessCenterBusinessUnit`,`BusinessCenter` WHERE `BusinessUnit`.`BusinessUnitId`=`BusinessCenterBusinessUnit`.`BusinessUnitId` AND `BusinessCenterBusinessUnit`.`BusinessCenterId`=`BusinessCenter`.`BusinessCenterId` AND `BusinessCenter`.`BusinessCenterId`=?", new Object[]{excelProcessingBean.getBusiness_center_id()});
        Map kpi_map = commonDaoInterface.queryForMap("SELECT KPIDescription,KPIId FROM KPI", new Object[]{});
        Map sub_process_map = commonDaoInterface.queryForMap("SELECT `SubProcessName`,`SubProcessId` FROM `SubProcess`", new Object[]{});
        List<CenterAvgBean> centerAvgBeans1=new ArrayList<CenterAvgBean>();
        while(centerAvgBeanIt.hasNext()){
            CenterAvgBean centerAvgBean=(CenterAvgBean)centerAvgBeanIt.next();
            Integer kpi_ID=Integer.parseInt(kpi_map.get(centerAvgBean.getKpi_desc()).toString());
            centerAvgBean.setKpi_Id(kpi_ID);
            Integer subprocess_ID=Integer.parseInt(sub_process_map.get(centerAvgBean.getSubProcess_name()).toString());
            centerAvgBean.setSubProcess_id(subprocess_ID);
            centerAvgBeans1.add(centerAvgBean);
        }
        kPICenterAvgBean.setCenterAverageBeans(centerAvgBeans1);
        excelProcessingBean.setkPICenterAvgBean(kPICenterAvgBean);
        List<KPIScoreBean> kPIScoreBeans=(List<KPIScoreBean>)excelProcessingBean.getkPIScoreBeans();
        Iterator kpiScoreIt=kPIScoreBeans.iterator();
        List<KPIScoreBean> kPIScoreBeans1=new ArrayList<KPIScoreBean>();
        while(kpiScoreIt.hasNext()){
            KPIScoreBean kPIScoreBean=(KPIScoreBean)kpiScoreIt.next();
            Integer kpi_ID=Integer.parseInt(kpi_map.get(kPIScoreBean.getKpi_desc()).toString());
            kPIScoreBean.setKpi_Id(kpi_ID);
            Integer subprocess_ID=Integer.parseInt(sub_process_map.get(kPIScoreBean.getSubProcess_name()).toString());
            kPIScoreBean.setSubProcess_id(subprocess_ID);
            List<BUActualBean> bUActualBeans=kPIScoreBean.getBussiness_actualBean_list();
            Iterator buIt=bUActualBeans.iterator();
            List<BUActualBean> bUActualBeans1=new ArrayList<BUActualBean>();
            while(buIt.hasNext()){
                BUActualBean bUActualBean=(BUActualBean)buIt.next();
                Integer client_ID=Integer.parseInt(bus_unit_map.get(bUActualBean.getBisiness_unit_name()).toString());
                bUActualBean.setBusiness_unit_id(client_ID);
                bUActualBeans1.add(bUActualBean);
            }
            kPIScoreBean.setBussiness_actualBean_list(bUActualBeans1);
            kPIScoreBeans1.add(kPIScoreBean);
        }
        excelProcessingBean.setkPIScoreBeans(kPIScoreBeans1);
        return excelProcessingBean;
    }
    
    public void deleteKPI(String date, String bcid) {
        jdbcTemplate.update("DELETE FROM KPICenterAverage WHERE BusinessCenterId=? AND StatementDate=?", new Object[]{bcid, date});
        jdbcTemplate.update("DELETE FROM KPIScore WHERE BusinessCenterId=? AND StatementDate=?", new Object[]{bcid, date});
    }
}
