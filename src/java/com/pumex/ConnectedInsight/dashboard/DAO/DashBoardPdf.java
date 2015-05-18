/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.dashboard.DAO;

import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import java.io.OutputStream;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**Class for creating the pdf exported from the graphs
 * @author mjons
 */
public class DashBoardPdf
{
    /**Generates the Global Dashboard Pdf and write it to the responae object
     * @param request request containing the parameters
     * @param response response on which the image content is written
     * @param urllist Image urllists which are to be exported into the pdf
     * @throws Exception 
     */
   public void generateGlobalDashboard(HttpServletRequest request,HttpServletResponse response,String[] urllist) throws Exception
   {
       Document document = new Document();
       response.setContentType("application/pdf");
       response.setHeader("content-disposition", "attachment; filename=\"GlobalDashboard.pdf\"");
       OutputStream fos = response.getOutputStream();
       PdfWriter.getInstance(document, fos);
       /*Setting the document header and footer starts*/
       HeaderFooter  header = new HeaderFooter(new Phrase("Global Dashboard", FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD)),false);
       HeaderFooter footer = new HeaderFooter(new Phrase("-"),new Phrase("-"));
       
       header.setBorder(Rectangle.NO_BORDER);
       header.setAlignment(Rectangle.ALIGN_CENTER);
       footer.setBorder(Rectangle.NO_BORDER);
       footer.setAlignment(Rectangle.ALIGN_CENTER);
       
       document.setHeader(header);
       document.setFooter(footer);
       /*Setting the document header and footer ends*/
       
       /*Different Chapters for the Global dashboards according to the CIS Dashboard Library*/
       Paragraph paragraph1 = new Paragraph("Profile",FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD));
       Paragraph paragraph2 = new Paragraph("Efficiency",FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD));
       Paragraph paragraph3 = new Paragraph("Resources & Cost",FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD));
       /*Different Chapters for the Global dashboards according to the CIS Dashboard Library*/
       
       Chapter chapter1 = new Chapter(paragraph1, 1);
       Chapter chapter2 = new Chapter(paragraph2, 2);
       Chapter chapter3 = new Chapter(paragraph3, 3);
       
       /*First Chapter starts here*/
       document.open();
       document.add(chapter1);
       Paragraph titleParagraph = null;
       titleParagraph = new Paragraph("\nUnderstand the profile of your global organization");
       titleParagraph.setFont(new Font(Font.UNDERLINE, Font.BOLD));
       titleParagraph.setSpacingAfter(25);
       titleParagraph.setSpacingBefore(25);
       titleParagraph.setAlignment(Element.ALIGN_LEFT);
       document.add(titleParagraph);
       
       for(Integer i=0;i<5;++i)
       {
           Image image = Image.getInstance(new URL(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")+1)+urllist[i]));
           image.scaleAbsolute(550, 450);
           image.setBorder(10);
           Paragraph imageParagraph = new Paragraph();
           imageParagraph.add(image);
           imageParagraph.setSpacingAfter(5);
           document.add(imageParagraph);
           document.newPage();
       }
       /*First Chapter ends here*/
       
       /*Second Chapter starts here*/
       document.add(chapter2);
       titleParagraph = new Paragraph("\nUnderstand the efficiency of your organization structure");
       titleParagraph.setFont(new Font(Font.UNDERLINE, Font.BOLD));
       titleParagraph.setSpacingAfter(25);
       titleParagraph.setSpacingBefore(25);
       titleParagraph.setAlignment(Element.ALIGN_LEFT);
       document.add(titleParagraph);
       
       for(Integer i=5;i<10;++i)
       {
           Image image = Image.getInstance(new URL(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")+1)+urllist[i]));
           image.scaleAbsolute(550, 450);
           image.setBorder(10);
           Paragraph imageParagraph = new Paragraph();
           imageParagraph.add(image);
           imageParagraph.setSpacingAfter(5);
           document.add(imageParagraph);
           document.newPage();
       }
       /*Second Chapter ends here*/
       
       
       /*Third Chapter starts here*/
       document.add(chapter3);
       titleParagraph = new Paragraph("\nUnderstand resources and cost of your processes");
       titleParagraph.setFont(new Font(Font.UNDERLINE, Font.BOLD));
       titleParagraph.setSpacingAfter(25);
       titleParagraph.setSpacingBefore(25);
       titleParagraph.setAlignment(Element.ALIGN_LEFT);
       document.add(titleParagraph);
       
       for(Integer i=10;i<13;++i)
       {
           Image image = Image.getInstance(new URL(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")+1)+urllist[i]));
           image.scaleAbsolute(550, 450);
           image.setBorder(10);
           Paragraph imageParagraph = new Paragraph();
           imageParagraph.add(image);
           imageParagraph.setSpacingAfter(5);
           document.add(imageParagraph);
           document.newPage();
       }
       /*Third Chapter ends here*/
       
       document.close();
       fos.close();
   }
   
   /**Generates the Location Dashboard Pdf and write it to the responae object
     * @param request request containing the parameters
     * @param response response on which the image content is written
     * @param urllist Image urllists which are to be exported into the pdf
     * @throws Exception 
     */
   public void generateLocationDashboard(HttpServletRequest request,HttpServletResponse response,String[] urllist) throws Exception
   {
       Document document = new Document();
       response.setContentType("application/pdf");
       response.setHeader("content-disposition", "attachment; filename=\"LocationDashboard.pdf\"");
       OutputStream fos = response.getOutputStream();
       PdfWriter.getInstance(document, fos);
       /*Setting the document header and footer starts*/
       HeaderFooter  header = new HeaderFooter(new Phrase("Location Dashboard", FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD)),false);
       HeaderFooter footer = new HeaderFooter(new Phrase("-"),new Phrase("-"));
       
       header.setBorder(Rectangle.NO_BORDER);
       header.setAlignment(Rectangle.ALIGN_CENTER);
       footer.setBorder(Rectangle.NO_BORDER);
       footer.setAlignment(Rectangle.ALIGN_CENTER);
       
       document.setHeader(header);
       document.setFooter(footer);
       /*Setting the document header and footer ends*/
       
       /*Different Chapters for the Location dashboards according to the CIS Dashboard Library*/
       Paragraph paragraph1 = new Paragraph("Profile",FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD));
       Paragraph paragraph2 = new Paragraph("Efficiency by Organization Structure",FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD));
       Paragraph paragraph3 = new Paragraph("Efficiency by Sub-Process",FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD));
       Paragraph paragraph4 = new Paragraph("Efficiency by Client",FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD));
       /*Different Chapters for the Location dashboards according to the CIS Dashboard Library*/
       
       Chapter chapter1 = new Chapter(paragraph1, 1);
       Chapter chapter2 = new Chapter(paragraph2, 2);
       Chapter chapter3 = new Chapter(paragraph3, 3);
       Chapter chapter4 = new Chapter(paragraph4, 4);
       
       /*First Chapter starts here*/
       document.open();
       document.add(chapter1);
       Paragraph titleParagraph = new Paragraph("\nUnderstand the profile of your organization");
       titleParagraph.setSpacingAfter(25);
       titleParagraph.setSpacingBefore(25);
       titleParagraph.setFont(new Font(Font.UNDERLINE, Font.BOLD));
       titleParagraph.setAlignment(Element.ALIGN_LEFT);
       document.add(titleParagraph);
       
       for(Integer i=0;i<8;++i)
       {
           Image image = Image.getInstance(new URL(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")+1)+urllist[i]));
           image.scaleAbsolute(550, 450);
           Paragraph imageParagraph = new Paragraph();
           imageParagraph.add(image);
           imageParagraph.setSpacingAfter(5);
           document.add(imageParagraph);
           document.newPage();
       }
       /*First chapter ends here*/
       
       /*Second chapter starts here*/
       
       document.add(chapter2);
       titleParagraph = new Paragraph("\nUnderstand the efficiency of your organization structure");
       titleParagraph.setSpacingAfter(25);
       titleParagraph.setSpacingBefore(25);
       titleParagraph.setFont(new Font(Font.UNDERLINE, Font.BOLD));
       titleParagraph.setAlignment(Element.ALIGN_LEFT);
       document.add(titleParagraph);
       
       for(Integer i=8;i<13;++i)
       {
           Image image = Image.getInstance(new URL(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")+1)+urllist[i]));
           image.scaleAbsolute(550, 450);
           Paragraph imageParagraph = new Paragraph();
           imageParagraph.add(image);
           imageParagraph.setSpacingAfter(5);
           document.add(imageParagraph);
           document.newPage();
       }
       /*Second chapter ends here*/
       
       /*Third chapter starts here*/
       document.add(chapter3);
       titleParagraph = new Paragraph("\nUnderstand efficiency by Sub-Process");
       titleParagraph.setSpacingAfter(25);
       titleParagraph.setSpacingBefore(25);
       titleParagraph.setFont(new Font(Font.UNDERLINE, Font.BOLD));
       titleParagraph.setAlignment(Element.ALIGN_LEFT);
       document.add(titleParagraph);
       
       for(Integer i=13;i<17;++i)
       {
           Image image = Image.getInstance(new URL(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")+1)+urllist[i]));
           image.scaleAbsolute(550, 450);
           Paragraph imageParagraph = new Paragraph();
           imageParagraph.add(image);
           imageParagraph.setSpacingAfter(5);
           document.add(imageParagraph);
           document.newPage();
       }
       /*Third chapter ends here*/
       
       /*Forth chapter starts here*/
       document.add(chapter4);
       titleParagraph = new Paragraph("\nUnderstand efficiency by Client");
       titleParagraph.setSpacingAfter(25);
       titleParagraph.setSpacingBefore(25);
       titleParagraph.setFont(new Font(Font.UNDERLINE, Font.BOLD));
       titleParagraph.setAlignment(Element.ALIGN_LEFT);
       document.add(titleParagraph);
       
       for(Integer i=17;i<21;++i)
       {
           Image image = Image.getInstance(new URL(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")+1)+urllist[i]));
           image.scaleAbsolute(550, 450);
           Paragraph imageParagraph = new Paragraph();
           imageParagraph.add(image);
           imageParagraph.setSpacingAfter(5);
           document.add(imageParagraph);
           document.newPage();
       }
       /*Forth chapter ends here*/
       document.close();
       fos.close();
   }
   
   /**Generates the Process Dashboard Pdf and write it to the responae object
     * @param request request containing the parameters
     * @param response response on which the image content is written
     * @param urllist Image urllists which are to be exported into the pdf
     * @throws Exception 
     */
   public void generateProcessDashboard(HttpServletRequest request,HttpServletResponse response,String[] urllist) throws Exception
   {
       Document document = new Document();
       response.setContentType("application/pdf");
       response.setHeader("content-disposition", "attachment; filename=\"ProcessDashboard.pdf\"");
       OutputStream fos = response.getOutputStream();
       PdfWriter.getInstance(document, fos);
       /*Setting the document header and footer starts*/
       HeaderFooter  header = new HeaderFooter(new Phrase("Process Dashboard", FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD)),false);
       HeaderFooter footer = new HeaderFooter(new Phrase("-"),new Phrase("-"));
       
       header.setBorder(Rectangle.NO_BORDER);
       header.setAlignment(Rectangle.ALIGN_CENTER);
       footer.setBorder(Rectangle.NO_BORDER);
       footer.setAlignment(Rectangle.ALIGN_CENTER);
       
       document.setHeader(header);
       document.setFooter(footer);
       /*Setting the document header and footer ends*/
       
       /*Different Chapters for the Process dashboards according to the CIS Dashboard Library*/
       Paragraph paragraph1 = new Paragraph("Profile",FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD));
       Paragraph paragraph2 = new Paragraph("Efficiency",FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD));
       /*Different Chapters for the Process dashboards according to the CIS Dashboard Library*/
       
       Chapter chapter1 = new Chapter(paragraph1, 1);
       Chapter chapter2 = new Chapter(paragraph2, 2);
       document.open();
       
       /*First Chapter starts here*/
       document.add(chapter1);
       Paragraph titleParagraph = new Paragraph("\nUnderstand the profile of your PROCESS organization");
       titleParagraph.setSpacingAfter(25);
       titleParagraph.setSpacingBefore(25);
       titleParagraph.setFont(new Font(Font.UNDERLINE, Font.BOLD));
       titleParagraph.setAlignment(Element.ALIGN_LEFT);
       document.add(titleParagraph);
       
       for(Integer i=0;i<5;++i)
       {
           Image image = Image.getInstance(new URL(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")+1)+urllist[i]));
           Paragraph imageParagraph = new Paragraph();
           imageParagraph.add(image);
           imageParagraph.setSpacingAfter(5);
           document.add(imageParagraph);
           document.newPage();
       }
       /*First chapter ends here*/
       
       /*Second chapter starts here*/
       document.add(chapter2);
       titleParagraph = new Paragraph("\nUnderstand the efficiency of your PROCESS organization");
       titleParagraph.setSpacingAfter(25);
       titleParagraph.setSpacingBefore(25);
       titleParagraph.setFont(new Font(Font.UNDERLINE, Font.BOLD));
       titleParagraph.setAlignment(Element.ALIGN_LEFT);
       document.add(titleParagraph);
       
       for(Integer i=5;i<10;++i)
       {
           Image image = Image.getInstance(new URL(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")+1)+urllist[i]));
           image.scaleAbsolute(550, 450);
           Paragraph imageParagraph = new Paragraph();
           imageParagraph.add(image);
           imageParagraph.setSpacingAfter(5);
           document.add(imageParagraph);
           document.newPage();
       }
       /*Second chapter ends here*/
       document.close();
       fos.close();
   }
   
   /**Generates the Subprocess Dashboard Pdf and write it to the responae object
     * @param request request containing the parameters
     * @param response response on which the image content is written
     * @param urllist Image urllists which are to be exported into the pdf
     * @throws Exception 
     */
   public void generateSubProcessDashboard(HttpServletRequest request,HttpServletResponse response,String[] urllist) throws Exception
   {
       Document document = new Document();
       response.setContentType("application/pdf");
       response.setHeader("content-disposition", "attachment; filename=\"SubProcessDashboard.pdf\"");
       OutputStream fos = response.getOutputStream();
       PdfWriter.getInstance(document, fos);
       /*Setting the document header and footer starts*/
       HeaderFooter  header = new HeaderFooter(new Phrase("Sub-Process Dashboard", FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD)),false);
       HeaderFooter footer = new HeaderFooter(new Phrase("-"),new Phrase("-"));
       
       header.setBorder(Rectangle.NO_BORDER);
       header.setAlignment(Rectangle.ALIGN_CENTER);
       footer.setBorder(Rectangle.NO_BORDER);
       footer.setAlignment(Rectangle.ALIGN_CENTER);
       
       document.setHeader(header);
       document.setFooter(footer);
       /*Setting the document header and footer ends*/
       
       /*Different Chapters for the Subprocess dashboards according to the CIS Dashboard Library*/
       Paragraph paragraph1 = new Paragraph("Profile",FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD));
       Paragraph paragraph2 = new Paragraph("Efficiency by Organization Structure",FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD));
       Paragraph paragraph3 = new Paragraph("Efficiency by Sub-Process",FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD));
       /*Different Chapters for the Subprocess dashboards according to the CIS Dashboard Library*/
       
       Chapter chapter1 = new Chapter(paragraph1, 1);
       Chapter chapter2 = new Chapter(paragraph2, 2);
       Chapter chapter3 = new Chapter(paragraph3, 3);
       
       /*First Chapter starts here*/
       document.open();
       document.add(chapter1);
       Paragraph titleParagraph = new Paragraph("\nUnderstand the profile of your SUB-PROCESS organization");
       titleParagraph.setSpacingAfter(25);
       titleParagraph.setSpacingBefore(25);
       titleParagraph.setFont(new Font(Font.UNDERLINE, Font.BOLD));
       titleParagraph.setAlignment(Element.ALIGN_LEFT);
       document.add(titleParagraph);
       
       for(Integer i=0;i<5;++i)
       {
           Image image = Image.getInstance(new URL(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")+1)+urllist[i]));
           image.scaleAbsolute(550, 450);
           Paragraph imageParagraph = new Paragraph();
           imageParagraph.add(image);
           imageParagraph.setSpacingAfter(5);
           document.add(imageParagraph);
           document.newPage();
       }
       /*First chapter ends here*/
       
       /*Second chapter starts here*/
       document.add(chapter2);
       titleParagraph = new Paragraph("\nUnderstand the efficiency of your SUB-PROCESS organization structure");
       titleParagraph.setSpacingAfter(25);
       titleParagraph.setSpacingBefore(25);
       titleParagraph.setFont(new Font(Font.UNDERLINE, Font.BOLD));
       titleParagraph.setAlignment(Element.ALIGN_LEFT);
       document.add(titleParagraph);
       
       for(Integer i=5;i<10;++i)
       {
           Image image = Image.getInstance(new URL(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")+1)+urllist[i]));
           image.scaleAbsolute(550, 450);
           Paragraph imageParagraph = new Paragraph();
           imageParagraph.add(image);
           imageParagraph.setSpacingAfter(5);
           document.add(imageParagraph);
           document.newPage();
       }
       /*Second chapter ends here*/
       
       /*Third chapter starts here*/
       document.add(chapter3);
       titleParagraph = new Paragraph("\nUnderstand the efficiency of your Sub-process");
       titleParagraph.setSpacingAfter(25);
       titleParagraph.setSpacingBefore(25);
       titleParagraph.setFont(new Font(Font.UNDERLINE, Font.BOLD));
       titleParagraph.setAlignment(Element.ALIGN_LEFT);
       document.add(titleParagraph);
       
       for(Integer i=10;i<14;++i)
       {
           Image image = Image.getInstance(new URL(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")+1)+urllist[i]));
           image.scaleAbsolute(550, 450);
           Paragraph imageParagraph = new Paragraph();
           imageParagraph.add(image);
           imageParagraph.setSpacingAfter(5);
           document.add(imageParagraph);
           document.newPage();
       }
       /*Third chapter ends here*/
       document.close();
       fos.close();
   }
   
   /**Generates the Target Dashboard Pdf and write it to the responae object
     * @param request request containing the parameters
     * @param response response on which the image content is written
     * @param urllist Image urllists which are to be exported into the pdf
     * @throws Exception 
     */
   public void generateTargetDashboard(HttpServletRequest request,HttpServletResponse response,String[] urllist) throws Exception
   {
       Document document = new Document();
       response.setContentType("application/pdf");
       response.setHeader("content-disposition", "attachment; filename=\"SubProcessDashboard.pdf\"");
       OutputStream fos = response.getOutputStream();
       PdfWriter.getInstance(document, fos);
       /*Setting the document header and footer starts*/
       HeaderFooter  header = new HeaderFooter(new Phrase("Sub-Process Dashboard", FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD)),false);
       HeaderFooter footer = new HeaderFooter(new Phrase("-"),new Phrase("-"));
       
       header.setBorder(Rectangle.NO_BORDER);
       header.setAlignment(Rectangle.ALIGN_CENTER);
       footer.setBorder(Rectangle.NO_BORDER);
       footer.setAlignment(Rectangle.ALIGN_CENTER);
       
       document.setHeader(header);
       document.setFooter(footer);
       /*Setting the document header and footer ends*/
       
       /*Different Chapters for the Subprocess dashboards according to the CIS Dashboard Library*/
       Paragraph paragraph1 = new Paragraph("Profile",FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD));
       Paragraph paragraph2 = new Paragraph("Efficiency by Organization Structure",FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD));
       Paragraph paragraph3 = new Paragraph("Efficiency by Sub-Process",FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD));
       /*Different Chapters for the Subprocess dashboards according to the CIS Dashboard Library*/
       
       Chapter chapter1 = new Chapter(paragraph1, 1);
       Chapter chapter2 = new Chapter(paragraph2, 2);
       Chapter chapter3 = new Chapter(paragraph3, 3);
       
       /*First Chapter starts here*/
       document.open();
       document.add(chapter1);
       Paragraph titleParagraph = new Paragraph("\nUnderstand the profile of your SUB-PROCESS organization");
       titleParagraph.setSpacingAfter(25);
       titleParagraph.setSpacingBefore(25);
       titleParagraph.setFont(new Font(Font.UNDERLINE, Font.BOLD));
       titleParagraph.setAlignment(Element.ALIGN_LEFT);
       document.add(titleParagraph);
       
//       for(Integer i=0;i<5;++i)
//       {
//           Image image = Image.getInstance(new URL(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")+1)+urllist[i]));
//           image.scaleAbsolute(550, 450);
//           Paragraph imageParagraph = new Paragraph();
//           imageParagraph.add(image);
//           imageParagraph.setSpacingAfter(5);
//           document.add(imageParagraph);
//           document.newPage();
//       }
       /*First chapter ends here*/
       
       /*Second chapter starts here*/
       document.add(chapter2);
       titleParagraph = new Paragraph("\nUnderstand the efficiency of your SUB-PROCESS organization structure");
       titleParagraph.setSpacingAfter(25);
       titleParagraph.setSpacingBefore(25);
       titleParagraph.setFont(new Font(Font.UNDERLINE, Font.BOLD));
       titleParagraph.setAlignment(Element.ALIGN_LEFT);
       document.add(titleParagraph);
       
//       for(Integer i=5;i<10;++i)
//       {
//           Image image = Image.getInstance(new URL(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")+1)+urllist[i]));
//           image.scaleAbsolute(550, 450);
//           Paragraph imageParagraph = new Paragraph();
//           imageParagraph.add(image);
//           imageParagraph.setSpacingAfter(5);
//           document.add(imageParagraph);
//           document.newPage();
//       }
       /*Second chapter ends here*/
       
       /*Third chapter starts here*/
       document.add(chapter3);
       titleParagraph = new Paragraph("\nUnderstand the efficiency of your Sub-process");
       titleParagraph.setSpacingAfter(25);
       titleParagraph.setSpacingBefore(25);
       titleParagraph.setFont(new Font(Font.UNDERLINE, Font.BOLD));
       titleParagraph.setAlignment(Element.ALIGN_LEFT);
       document.add(titleParagraph);
       
       for(Integer i=10;i<14;++i)
       {
           Image image = Image.getInstance(new URL(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")+1)+urllist[i]));
           image.scaleAbsolute(550, 450);
           Paragraph imageParagraph = new Paragraph();
           imageParagraph.add(image);
           imageParagraph.setSpacingAfter(5);
           document.add(imageParagraph);
           document.newPage();
       }
       /*Third chapter ends here*/
       document.close();
       fos.close();
   }
}
