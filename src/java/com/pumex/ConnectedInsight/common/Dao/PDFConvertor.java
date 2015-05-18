/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.common.Dao;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author mjons
 */
public class PDFConvertor
{
    public void convertExcelFromPdf(String url,HttpServletResponse response) throws IOException, DocumentException
    {
        Document document = new Document();
        response.setContentType("application/pdf");
        response.setHeader("content-disposition", "attachment; filename=\"Reports.pdf\"");
        OutputStream fos = response.getOutputStream();
        PdfWriter.getInstance(document, fos);
        document.open();
        InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("/draw.htm>d1=2012-07-12&d2=2012-07-04");
        
    }
    
    public void pdf() throws FileNotFoundException, MalformedURLException, IOException, DocumentException, InvalidFormatException
    {
        Document document = new Document();
        //FileInputStream fis=new FileInputStream("/home/mjons/Downloads/Reports(1).xlsx");
        URL url=new URL("http://10.0.0.8:8084/ConnectedInsight/draw.htm?d1=2012-08-14&d2=2012-07-12");
        InputStream is=new FileInputStream("/home/mjons/Desktop/Downloads/Reports(1).xlsx");
        FileOutputStream fos=new FileOutputStream("/home/mjons/Desktop/excel.pdf");
        PdfWriter.getInstance(document, fos);
        document.open();
        String content="";
        int ch;
        while((ch=is.read())!=-1)
        {
            content+=(char)ch;
        }
        fos.write(content.getBytes());
        //document.add((Element)WorkbookFactory.create(is));
        document.close();
    }
    
    public void convert() throws Exception
    {
        Document document = new Document();
        URL url=new URL("http://10.0.0.8:8084/ConnectedInsight/draw.htm?d1=2012-08-14&d2=2012-07-12");
        InputStream is=url.openStream();
        Workbook wb=WorkbookFactory.create(is);
        FileOutputStream fos=new FileOutputStream("/home/mjons/Desktop/excel.pdf");
        
        POIFSFileSystem fs=new POIFSFileSystem(is);
        
        PdfWriter.getInstance(document, fos);
    }
    
    public static void main(String[] args) throws Exception
    {
        new PDFConvertor().pdf();
    }
}
