/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.HRData.beans;

import com.pumex.ConnectedInsight.Excel.Bean.EmployeeDetailsBean;

/**
 *
 * @author mjons
 */
public class HRDataBean
{
    EmployeeDetailsBean employeeDetailsBean[];

    public EmployeeDetailsBean[] getEmployeeDetailsBean() {
        return employeeDetailsBean;
    }

    public void setEmployeeDetailsBean(EmployeeDetailsBean[] employeeDetailsBean) {
        this.employeeDetailsBean = employeeDetailsBean;
    }

    
}
