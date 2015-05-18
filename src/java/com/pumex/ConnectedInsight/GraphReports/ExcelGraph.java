/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.GraphReports;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Vishnu AU
 * Created on:05 July 2012
 * Updated On:05 July 2012
 * Updated by: Vishnu AU
 */
public class ExcelGraph
{    
    public void setDataToExcelSheet(FileInputStream in,Integer sheetno,String[][] data,Integer rowIndex,String[] type) throws Exception
    {
        Integer rowCount=rowIndex;
        Workbook _excel_work_book=WorkbookFactory.create(in);
        Sheet _excel_sheet=_excel_work_book.getSheetAt(sheetno);
        for(int i=0;i<data.length;i++)
        {
            Row row=_excel_sheet.createRow(rowCount);
            for(int j=0;j<data[i].length;j++)
            {
                if(type[j].equals("String"))
                {
                    Cell cell=row.createCell(j);
                    cell.setCellValue(data[i][j]);
                }
                else if(type[j].equals("Double"))
                {
                    Cell cell=row.createCell(j);
                    double d= Double.valueOf(data[i][j].trim());
                    cell.setCellValue(d);
                }
            }
            rowCount++;
        }
        
         FileOutputStream fout=new FileOutputStream("src/jxlsgraphapps/chart_output11.xlsx");
         _excel_work_book.write(fout);
    } 
    public void dbToExcel(FileInputStream in,List<ValueBean> vallist) throws IOException, InvalidFormatException{
        Workbook wb=WorkbookFactory.create(in);
        Sheet sheet=wb.getSheetAt(1);
        int i=0;
        Iterator it=vallist.iterator();
        while(it.hasNext()){
            ValueBean valbean=(ValueBean)it.next();
            if(i==0){
                Row row=sheet.getRow(i);
                Cell cell=row.getCell(2);
                cell.setCellValue(valbean.getXtitle());
                cell=row.getCell(3);
                cell.setCellValue(valbean.getY1title());
                cell=row.getCell(4);
                cell.setCellValue(valbean.getY2title());
                row=sheet.getRow(i+1);
                cell=row.getCell(2);
                cell.setCellValue(valbean.getXvalue());
                cell=row.getCell(3);
                cell.setCellValue(valbean.getY1value());
                cell=row.getCell(4);
                cell.setCellValue(valbean.getY2value());
            }
            
        }
        FileOutputStream fout=new FileOutputStream("D:/ConnectedInsight/src/java/chart_output11.xlsx");
            wb.write(fout);
    }
}
