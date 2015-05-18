/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import com.pumex.ConnectedInsight.common.bean.FileUploadBean;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vishnu AU
 * Created on:10 July 2012
 * Updated On:10 July 2012
 * Updated by: Vishnu AU
 */
@Repository
public class ExcelUploaderDaoImpl implements ExcelUploaderDao{

    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public InputStream uploadFile(FileUploadBean fileUploadBean) throws FileNotFoundException,IOException{
        InputStream in=fileUploadBean.getFile().getInputStream();
        return in;
    }

    public Boolean checkStatementDate(String date,String date1) {
        if(date.equals(date1)){
            return true;
        }else{
            return false;
        }
    }
    
}
