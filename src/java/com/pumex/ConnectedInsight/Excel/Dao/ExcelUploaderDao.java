/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import com.pumex.ConnectedInsight.common.bean.FileUploadBean;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vishnu AU
 * Created on:10 July 2012
 * Updated On:10 July 2012
 * Updated by: Vishnu AU
 */
@Repository
public interface ExcelUploaderDao {
    public InputStream uploadFile(FileUploadBean fileUploadBean) throws FileNotFoundException,IOException;
    
    public Boolean checkStatementDate(String date,String date1);
    
}
