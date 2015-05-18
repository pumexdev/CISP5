/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.GraphReports;

/**
 *
 * @author Vishhhnu
 */
public class ValueBean {
    private String xtitle;
    private String y1title;
    private String y2title;
    private String xvalue;
    private Double y1value;
    private Double y2value;

    public String getXtitle() {
        return xtitle;
    }

    public void setXtitle(String xtitle) {
        this.xtitle = xtitle;
    }

    public String getXvalue() {
        return xvalue;
    }

    public void setXvalue(String xvalue) {
        this.xvalue = xvalue;
    }

    public String getY1title() {
        return y1title;
    }

    public void setY1title(String y1title) {
        this.y1title = y1title;
    }

    public Double getY1value() {
        return y1value;
    }

    public void setY1value(Double y1value) {
        this.y1value = y1value;
    }

    public String getY2title() {
        return y2title;
    }

    public void setY2title(String y2title) {
        this.y2title = y2title;
    }

    public Double getY2value() {
        return y2value;
    }

    public void setY2value(Double y2value) {
        this.y2value = y2value;
    }
    
}
