/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Bean;

/**
 *
 * @author user
 */
public class ExportEmployeeBUBean {
    
    private String detail_id;
    private String bu_id;
    private double fte;
    private double compansation;

    public String getBu_id() {
        return bu_id;
    }

    public void setBu_id(String bu_id) {
        this.bu_id = bu_id;
    }

    public double getCompansation() {
        return compansation;
    }

    public void setCompansation(double compansation) {
        this.compansation = compansation;
    }

    public String getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(String detail_id) {
        this.detail_id = detail_id;
    }

    public double getFte() {
        return fte;
    }

    public void setFte(double fte) {
        this.fte = fte;
    }
    
    
}
