package com.pumex.ConnectedInsight.HRData.beans;

/**
 *
 * @author mjons
 */
public class StatementBean
{
    Integer statementStatus;
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public Integer getStatementStatus() {
        return statementStatus;
    }

    public void setStatementStatus(Integer statementStatus) {
        this.statementStatus = statementStatus;
    }

    public String getStatementdate() {
        return statementdate;
    }

    public void setStatementdate(String statementdate) {
        this.statementdate = statementdate;
    }
    String statementdate;
    String closedate;
    String opendate;

    public String getClosedate() {
        return closedate;
    }

    public void setClosedate(String closedate) {
        this.closedate = closedate;
    }

    public String getOpendate() {
        return opendate;
    }

    public void setOpendate(String opendate) {
        this.opendate = opendate;
    }
    
}
