/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.common.bean;


import java.util.Date;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Vishnu AU
 * Created on:06 July 2012
 * Updated On:06 July 2012
 * Updated by: Vishnu AU
 */
public class FileUploadBean {

    private CommonsMultipartFile file;
    private String statement_date;

    public String getStatement_date() {
        return statement_date;
    }

    public void setStatement_date(String statement_date) {
        this.statement_date = statement_date;
    }

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }
}
