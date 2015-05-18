package com.pumex.ConnectedInsight.Excel.Bean;

/**
 *
 * @author Vishhhnu
 */
public class ExportEntryFormBean {
    
    private String entry_id;
    private String entry_date;
    private String business_center;

    public String getBusiness_center() {
        return business_center;
    }

    public void setBusiness_center(String business_center) {
        this.business_center = business_center;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    public String getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(String entry_id) {
        this.entry_id = entry_id;
    }
    
}
