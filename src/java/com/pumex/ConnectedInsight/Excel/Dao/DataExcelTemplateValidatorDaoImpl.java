/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import com.pumex.ConnectedInsight.Excel.Bean.EmployeeBUBean;
import com.pumex.ConnectedInsight.Excel.Bean.EmployeeBean;
import com.pumex.ConnectedInsight.Excel.Bean.EntryBean;
import com.pumex.ConnectedInsight.Excel.Bean.ExcelProcessingBean;
import com.pumex.ConnectedInsight.Property.DateConvertion;
import com.pumex.ConnectedInsight.Property.PropertyFile;
import com.pumex.ConnectedInsight.login.beans.UserBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vishnu
 */
@Repository
public class DataExcelTemplateValidatorDaoImpl implements IDataExcelTemplateValidatorDao {

    @Autowired
    ExcelMasterDao excelMasterDao;
    @Autowired
    ExcelOperationDao excelOperationDao;

    @Override
    public ExcelProcessingBean validateBusinessCenter(Workbook wb, ExcelProcessingBean excelProcessingBean) {
        Sheet sheet = wb.getSheetAt(1);
        Cell businessCenterCell = BasicExcelOperation.getExcelCell("Regional Business Center", sheet);
        if (businessCenterCell != null) {
            Integer rowIndex = businessCenterCell.getRowIndex();
            Integer columnIndex = businessCenterCell.getColumnIndex();
            if (rowIndex == 4 && columnIndex == 1) {
                Cell businessCenterContentCell = BasicExcelOperation.getExcelCell(sheet, 4, 5, "String");
                if (businessCenterContentCell != null) {
                    String cellContent = businessCenterContentCell.getStringCellValue();
                    if (!cellContent.equals("")) {
                        excelProcessingBean.setStatus("Success");
                    } else {
                        excelProcessingBean.setError_message("Regional Business Center value does not exist in the uploaded Excel or may be misplaced. Please verify with standard Excel template or contact administrator.");
                        excelProcessingBean.setStatus("Failed");
                    }
                } else {
                    excelProcessingBean.setError_message("Regional Business Center value does not exist in the uploaded Excel or may be misplaced. Please verify with standard Excel template or contact administrator.");
                    excelProcessingBean.setStatus("Failed");
                }
            } else {
                excelProcessingBean.setError_message("Regional Business Center cell misplaced in the uploaded Excel. Please verify with standard Excel template or contact administrator.");
                excelProcessingBean.setStatus("Failed");
            }
        } else {
            excelProcessingBean.setError_message("Regional Business Center cell not in the uploaded Excel. Please verify with standard Excel template or contact administrator.");
            excelProcessingBean.setStatus("Failed");
        }
        return excelProcessingBean;
    }

    @Override
    public ExcelProcessingBean validateStatementDate(Workbook wb, ExcelProcessingBean excelProcessingBean) {
        Sheet sheet = wb.getSheetAt(1);
        Cell statementDateCell = BasicExcelOperation.getExcelCell("Data as of :", sheet);
        if (statementDateCell != null) {
            Integer rowIndex = statementDateCell.getRowIndex();
            Integer columnIndex = statementDateCell.getColumnIndex();
            if (rowIndex == 5 && columnIndex == 1) {
                try {
                    //String cellContent = DateConvertion.convertToMysqlFormat(excelOperationDao.validateDate(wb, PropertyFile.excelInt("resource.input.date.rowIndex"), PropertyFile.excelInt("resource.input.date.columIndex")));
                    
                      //  if (!cellContent.equals("")) {
                            excelProcessingBean.setStatus("Success");
                        //} else {
                          //  excelProcessingBean.setError_message("Statement date value not in your Excel template or misplaced. Please check with Excel template or Contact Admin!!!");
                            //excelProcessingBean.setStatus("Failed");
                        //}
                } catch (Exception ex) {
                    Logger.getLogger(DataExcelTemplateValidatorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
            } else {
                excelProcessingBean.setError_message("Date As of cell misplaced in the uploaded Excel. Please verify with standard Excel template or contact administrator.");
                excelProcessingBean.setStatus("Failed");
            }
        } else {
            excelProcessingBean.setError_message("Date As of cell does not exist in the uploaded Excel. Please verify with standard Excel template or contact administrator.");
            excelProcessingBean.setStatus("Failed");
        }
        return excelProcessingBean;
    }

    @Override
    public ExcelProcessingBean validateExcelHeader(Workbook wb, ExcelProcessingBean excelProcessingBean) {
        Sheet sheet = wb.getSheetAt(1);
        Cell nameCell = BasicExcelOperation.getExcelCell("Name", sheet);
        if (nameCell != null) {
            Integer rowNo = nameCell.getRowIndex();
            Integer templateRowNo = PropertyFile.excelInt("resource.input.header.starting.rowIndex");
            if (rowNo == templateRowNo) {
                Row row = BasicExcelOperation.getExcelRow(sheet, rowNo);
                Iterator cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell insideCell = (Cell) cellIterator.next();

                    if (insideCell != null) {
                        Integer cellNo = insideCell.getColumnIndex();
                        String content = insideCell.getStringCellValue();
                        if (cellNo == 1) {
                            if (content.equals("Name")) {
                                excelProcessingBean.setStatus("Success");
                            } else {
                                excelProcessingBean.setError_message("Name header does not exist in the uploaded Excel or may be misplaced. Please verify with standard Excel template or contact administrator.");
                                excelProcessingBean.setStatus("Failed");
                            }
                        } else if (cellNo == 2) {
                            if (content.equals("Cost Center")) {
                                excelProcessingBean.setStatus("Success");
                            } else {
                                excelProcessingBean.setError_message("Cost Center header does not exist in the uploaded Excel or may be misplaced. Please verify with standard Excel template or contact administrator.");
                                excelProcessingBean.setStatus("Failed");
                            }
                        } else if (cellNo == 3) {
                            if (content.equals("Type")) {
                                excelProcessingBean.setStatus("Success");
                            } else {
                                excelProcessingBean.setError_message("Type header does not exist in the uploaded Excel or may be misplaced. Please verify with standard Excel template or contact administrator.");
                                excelProcessingBean.setStatus("Failed");
                            }
                        } else if (cellNo == 4) {
                            if (content.equals("Grade")) {
                                excelProcessingBean.setStatus("Success");
                            } else {
                                excelProcessingBean.setError_message("Grade header does not exist in the uploaded Excel or may be misplaced. Please verify with standard Excel template or contact administrator.");
                                excelProcessingBean.setStatus("Failed");
                            }
                        } else if (cellNo == 5) {
                            if (content.equals("Tier")) {
                                excelProcessingBean.setStatus("Success");
                            } else {
                                excelProcessingBean.setError_message("Tier header does not exist in the uploaded Excel or may be misplaced. Please verify with standard Excel template or contact administrator.");
                                excelProcessingBean.setStatus("Failed");
                            }
                        } else if (cellNo == 6) {
                            if (content.equals("Comp")) {
                                excelProcessingBean.setStatus("Success");
                            } else {
                                excelProcessingBean.setError_message("Comp header does not exist in the uploaded Excel or may be misplaced. Please verify with standard Excel template or contact administrator.");
                                excelProcessingBean.setStatus("Failed");
                            }
                        } else if (cellNo == 7) {
                            if (content.equals("Process")) {
                                excelProcessingBean.setStatus("Success");
                            } else {
                                excelProcessingBean.setError_message("Process header does not exist in the uploaded Excel or may be misplaced. Please verify with standard Excel template or contact administrator.");
                                excelProcessingBean.setStatus("Failed");
                            }
                        } else if (cellNo == 8) {
                            if (content.equals("Sub Process")) {
                                excelProcessingBean.setStatus("Success");
                            } else {
                                excelProcessingBean.setError_message("Sub process header does not exist in the uploaded Excel or may be misplaced. Please verify with standard Excel template or contact administrator.");
                                excelProcessingBean.setStatus("Failed");
                            }
                        }
                    }
                }
            } else {
                excelProcessingBean.setError_message("");
                excelProcessingBean.setStatus("Failed");
            }
        } else {
            excelProcessingBean.setError_message("");
            excelProcessingBean.setStatus("Failed");
        }
        return excelProcessingBean;
    }

    @Override
    public ExcelProcessingBean validateTotalLabel(Workbook wb, ExcelProcessingBean excelProcessingBean) {
        Sheet sheet = wb.getSheetAt(1);
        Cell totalCell = BasicExcelOperation.getExcelCell("$Total", sheet);
        if (totalCell != null) {
            excelProcessingBean.setStatus("Success");
        } else {
            excelProcessingBean.setError_message("$Total does not exist in the uploaded Excel. Please verify with standard Excel template or contact administrator.");
            excelProcessingBean.setStatus("Failed");
        }
        return excelProcessingBean;
    }

    @Override
    public Map<String, List<String>> validateExcelData(Workbook wb, ExcelProcessingBean excelProcessingBean, UserBean user) {
        Map<String, List<String>> resultMap = new LinkedHashMap<String, List<String>>();
        try {
            EntryBean entry_Bean = new EntryBean();

            entry_Bean.setBusiness_center_id(excelProcessingBean.getBusiness_center_id());
            entry_Bean.setBusiness_id(excelProcessingBean.getBusiness_id());
            entry_Bean.setEntry_date(excelProcessingBean.getDate());
            entry_Bean.setOrganization_id(user.getOrganizationid());
            Map<Integer, String> headerMap = excelOperationDao.validateHeader(wb, PropertyFile.excelInt("resource.input.header.starting.rowIndex"), PropertyFile.excelInt("resource.input.header.starting.columIndex"));
            Map<Integer, String> subMap = excelOperationDao.validateSubBU(wb, PropertyFile.excelInt("resource.input.subheader.starting.rowIndex"), PropertyFile.excelInt("resource.input.subheader.starting.columIndex"));
            Map<String, Integer> BUmap = excelMasterDao.getBusinesMap(entry_Bean.getBusiness_center_id(), entry_Bean.getBusiness_id());
            Sheet sheet = wb.getSheetAt(PropertyFile.excelInt("resource.input.resourcesheet.no"));
            Integer startIndex = PropertyFile.excelInt("resource.input.employee.rowIndex");
            Integer endIndex = excelProcessingBean.getTotalEntries();
            List<EmployeeBean> employeeBeans = new ArrayList<EmployeeBean>();
            Integer limit = endIndex + startIndex;
            for (int i = startIndex; i < limit; i++) {

                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                } else {
                    entry_Bean.setEmployeeBean(getHRemployeeDetails(row, headerMap, subMap, BUmap, user));
                    entry_Bean.getEmployeeBean().setXl_rowno(i);
                    employeeBeans.add(entry_Bean.getEmployeeBean());
                }
            }
            if (!employeeBeans.isEmpty()) {
                resultMap = validateRowField(employeeBeans);
            }

        } catch (IOException ex) {
            Logger.getLogger(DataExcelTemplateValidatorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(DataExcelTemplateValidatorDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultMap;
    }

    public EmployeeBean getHRemployeeDetails(Row row, Map<Integer, String> headerMap, Map<Integer, String> subMap, Map<String, Integer> BUmap, UserBean user) {
        EmployeeBean employeeBean = new EmployeeBean();
        Map<String, Integer> empMap = excelMasterDao.getEmployeeTypeMap();
        Map<String, Integer> proMap = excelMasterDao.getProcessMap(user);
        Integer columCount = PropertyFile.excelInt("resource.input.employee.columIndex");
        for (int j = columCount; j < columCount + 8; j++) {
            Cell cell = row.getCell(j);
            if (headerMap.get(j).equals("Name")) {
                employeeBean.setEmployee_name(getCellStringValue(cell));
            } else if (headerMap.get(j).equals("Type")) {
                employeeBean.setEmployee_type(empMap.get(getCellStringValue(cell)));
            } else if (headerMap.get(j).equals("Grade")) {
                employeeBean.setGrade(getCellIntegerValue(cell));
            } else if (headerMap.get(j).equals("Tier")) {
                employeeBean.setTier(getCellIntegerValue(cell));
            } else if (headerMap.get(j).equals("Comp")) {
                employeeBean.setTotal_cost(getCellDoubleValue(cell));
            } else if (headerMap.get(j).equals("Process")) {
                employeeBean.setProcess(getCellStringValue(cell));
                if (cell != null) {
                    employeeBean.setProcess_id(proMap.get(cell.getStringCellValue()));
                }
            } else if (headerMap.get(j).equals("Sub Process")) {
                employeeBean.setSub_process(getCellStringValue(cell));
                if (cell != null) {
                    Map<String, Integer> subprocessMap = excelMasterDao.getSubProcessMap(employeeBean.getProcess_id());
                    employeeBean.setSub_process_id(subprocessMap.get(cell.getStringCellValue().trim()));
                }
            } else if (headerMap.get(j).equals("Cost Center")) {
                employeeBean.setCost_Center(getCellStringValue(cell));
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
            if (subMap.get(i).equals("Total")) {
                employeeBean.setTotal_fte(cell.getNumericCellValue());
            } else {
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    if (cell.getNumericCellValue() != 0) {
                        EmployeeBUBean employeeBUBean = new EmployeeBUBean();
                        employeeBUBean.setFte(getCellDoubleValue(cell));
                        String business_unit_name = subMap.get(i);
                        employeeBUBean.setBusiness_unit(subMap.get(i));
                        employeeBUBean.setBusiness_unit_id(buMap.get(business_unit_name));
                        employeeBUBean.setCost((employeeBean.getTotal_cost() * employeeBUBean.getFte()));
                        employeeBUBeanList.add(employeeBUBean);
                    }
                }
            }
        }
        employeeBean.setEmployee_bu_bean(employeeBUBeanList);
        return employeeBean;
    }

    public String getCellStringValue(Cell cell) {
        String result = "";
        cell.setCellType(Cell.CELL_TYPE_STRING);
        if (cell != null) {
            result = cell.getStringCellValue();
        }
        if (result.equals("")) {
            result = "-1";
        }
        return result;
    }

    public Integer getCellIntegerValue(Cell cell) {
        Integer result = 0;
        if (cell != null) {
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            result = (int) cell.getNumericCellValue();
        }
        if (result == 0) {
            result = -1;
        }
        return result;
    }

    public Double getCellDoubleValue(Cell cell) {
        Double result = 0.0;
        if (cell != null) {
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            result = cell.getNumericCellValue();
        }
        if (result == 0.0) {
            result = -1.0;
        }
        return result;
    }

    public Map<String, List<String>> validateRowField(List<EmployeeBean> employeeBeans) {
        Map<String, List<String>> resultMap = new LinkedHashMap<String, List<String>>();
        List<String> trueList = new ArrayList<String>();
        List<String> falseList = new ArrayList<String>();
        Iterator employeeIterator = employeeBeans.iterator();
        while (employeeIterator.hasNext()) {
            EmployeeBean employeeBean = (EmployeeBean) employeeIterator.next();
            if (validEmployee(employeeBean)) {
                trueList.add(employeeBean.getXl_rowno().toString());
            } else {
                falseList.add(employeeBean.getXl_rowno().toString());
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

    public Boolean validEmployee(EmployeeBean employeeBean) {
        Boolean flag = false;
        if (!employeeBean.getEmployee_name().equals("-1")) {
            if (!employeeBean.getCost_Center().equals("-1")) {
                if (employeeBean.getEmployee_type() != -1) {
                    if (employeeBean.getGrade() != -1) {
                        if (employeeBean.getTier() != -1) {
                            if (!employeeBean.getProcess().equals("-1")) {
                                if (!employeeBean.getSub_process().equals("-1")) {
                                    if (employeeBean.getTotal_cost() != -1.0) {
                                        List<EmployeeBUBean> bUBeans = employeeBean.getEmployee_bu_bean();
                                        Iterator bIt = bUBeans.iterator();

                                        while (bIt.hasNext()) {
                                            EmployeeBUBean employeeBUBean = (EmployeeBUBean) bIt.next();
                                            if (validateEBU(employeeBUBean)) {
                                                flag = true;
                                            } else {
                                                flag = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    public Boolean validateEBU(EmployeeBUBean employeeBUBean) {
        Boolean flag = false;
        if (employeeBUBean.getFte() != -1.0) {
            flag = true;
        }
        return flag;
    }
}
