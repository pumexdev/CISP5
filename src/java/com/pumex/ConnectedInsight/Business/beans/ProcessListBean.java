/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Business.beans;

import java.util.List;

/**
 *
 * @author mjons
 */
public class ProcessListBean
{
    Integer processid;
    String process;
    List subProcess;

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public Integer getProcessid() {
        return processid;
    }

    public void setProcessid(Integer processid) {
        this.processid = processid;
    }

    public List getSubProcess() {
        return subProcess;
    }

    public void setSubProcess(List subProcess) {
        this.subProcess = subProcess;
    }
}
