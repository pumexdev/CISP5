/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author user
 */
public class BasicExcelOperation {
    
    public static Cell getExcelCell(String cellValue,Sheet workSheet){
        Cell cell=null;
        Iterator rowIterator=workSheet.rowIterator();
        while(rowIterator.hasNext()){
            Row wsRow=(Row)rowIterator.next();
            Iterator cellIterator=wsRow.cellIterator();
            while(cellIterator.hasNext()){
                Cell insideCell=(Cell)cellIterator.next();
                String val="";
                insideCell.setCellType(Cell.CELL_TYPE_STRING);
                val=insideCell.getStringCellValue();
                if(val.equals(cellValue))                {
                    cell=insideCell;
                    break;
                }
            }
        }
        return cell;
    }
    
    public static List<String> getDataFromRow(Row row){
        List<String> resultList=new ArrayList<String>();
        if(row!=null){
            Iterator cellIterator=row.cellIterator();
            while(cellIterator.hasNext()){
                Cell cell=(Cell)cellIterator.next();
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String value=cell.getStringCellValue().trim();
                if(!value.equals("")){
                    resultList.add(value);
                }
            }
        }
        return resultList;
    }
    
    public static List<String> getDataFromColum(Sheet sheet,Integer rowNo,Integer colNo){
        List<String> resultList=new ArrayList<String>();
        Integer lastIndex=sheet.getLastRowNum();
        for(int i=rowNo;i<=lastIndex;i++){
            Row row=sheet.getRow(i);
            if(row!=null){
                Cell cell=row.getCell(colNo);
                if(cell!=null){
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String value=cell.getStringCellValue().trim();
                    if(!value.equals("")){
                        resultList.add(value);
                    }
                }
            }
        }
        return resultList;
    }
    
    public static List<String> getUniqueList(List<String> inputList){
        List<String> resultList=new ArrayList<String>();
        String listContent="";
        Iterator inputListIt=inputList.iterator();
        while(inputListIt.hasNext()){
            String loopValue=(String) inputListIt.next();
            if(listContent.equals("")){
                resultList.add(loopValue);
                listContent=loopValue;
            }else if(!resultList.contains(loopValue)){
                listContent=loopValue;
                resultList.add(loopValue);
            }
        }
        return resultList;
    }
    
    public static Map<Integer,String> getColumNumber(List<String> inputList,Sheet sheet){
        Map<Integer,String> resultMap=new HashMap<Integer, String>();
        Iterator inputListIt=inputList.iterator();
        while(inputListIt.hasNext()){
            String listValue=(String)inputListIt.next();
            Cell cell=getExcelCell(listValue, sheet);
            resultMap.put(cell.getColumnIndex() ,listValue);
        }
        return resultMap;
    }
    
    public static Map<Integer,String> getRowNumber(List<String> inputList,Sheet sheet){
        Map<Integer,String> resultMap=new HashMap<Integer,String>();
        Iterator inputListIt=inputList.iterator();
        while(inputListIt.hasNext()){
            String listValue=(String)inputListIt.next();
            Cell cell=getExcelCell(listValue, sheet);
            resultMap.put(cell.getRowIndex(),listValue);
        }
        return resultMap;
    }
    
    public static Cell getExcelCell(Sheet sheet,Integer rowNo,Integer colNo,String Type){
        Row row=sheet.getRow(rowNo);
        if(row==null){
            row=sheet.createRow(rowNo);
        }
        Cell cell=row.getCell(colNo);
        if(cell==null){
            cell=row.createCell(colNo);
        }
        if(Type.equals("String")){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }else if(Type.equals("Double")){
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        }else if(Type.equals("Boolean")){
            cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
        }
        
        return cell;
    }
    
    public static Row getExcelRow(Sheet sheet,Integer rowIndex){
        Row row=null;
        Iterator rowIterator=sheet.rowIterator();
        while(rowIterator.hasNext()){
            Row loopRow=(Row)rowIterator.next();
            Integer rowNo=loopRow.getRowNum();
            if(rowNo==rowIndex){
                row=loopRow;
                break;
            }
        }
        return row;
    }
}
