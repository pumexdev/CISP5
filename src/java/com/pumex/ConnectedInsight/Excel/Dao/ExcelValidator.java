/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.Excel.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ExcelValidator {
    
    public static Map<String,List<String>> validateList(List<String> contentList,Map contentMap){
        Map<String,List<String>> resultMap=new HashMap<String, List<String>>();
        List trueList=new ArrayList<String>();
        List falseList=new ArrayList<String>();
        Iterator it=contentList.iterator();
        while(it.hasNext()){
            String content=(String)it.next();
            if(contentMap.containsValue(content)){
                trueList.add(content);
            }else{
                falseList.add(content);
            }
        }
        if(!trueList.isEmpty()){
            resultMap.put("true", trueList);
        }
        if(!falseList.isEmpty()){
            resultMap.put("false", falseList);
        }
        return resultMap;
    }
    
    public static String getErrorList(List<String> falseList,String messageType){
    
        String errorMessage=messageType+"";
        Iterator falseListIt=falseList.iterator();
        while(falseListIt.hasNext()){
            String falseListValue=(String) falseListIt.next();
            errorMessage+=falseListValue+",";
        }
        errorMessage=errorMessage.substring(0, errorMessage.lastIndexOf(","));
        return errorMessage;
    } 
    
    public static Map<String,List<String>> validateUniqueEntry(List<String> contentList){
        Map<String,List<String>> resultMap=new HashMap<String, List<String>>();
        List trueList=new ArrayList<String>();
        List falseList=new ArrayList<String>();
        String listContent="";
        Iterator it=contentList.iterator();
        while(it.hasNext()){
            String loopValue=(String) it.next();
            if(listContent.equals("")){
                trueList.add(loopValue);
                listContent=loopValue;
            }else if(!trueList.contains(loopValue)){
                listContent=loopValue;
                trueList.add(loopValue);
            }else if(trueList.contains(loopValue)){
                 listContent=loopValue;
                falseList.add(loopValue);
            }
        }
         if(!trueList.isEmpty()){
            resultMap.put("true", trueList);
        }
        if(!falseList.isEmpty()){
            resultMap.put("false", falseList);
        }
        return resultMap;
    }
}
