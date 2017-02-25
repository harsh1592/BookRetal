/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculationWS;

import java.util.ArrayList;
import java.util.HashMap;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author patil
 */
@WebService(serviceName = "CalWS")
@HandlerChain(file = "CalWS_handler.xml")
public class CalWS {
    public HashMap<String,String> sessionList;
    
    /**
     * This is a sample web service operation
     */
    public CalWS(){
        sessionList = new HashMap<>();
    }
    @WebMethod(operationName="getInfoByYear")
    public ArrayList<BookInfo> getInfoByYear(@WebParam(name="year")int year,
            @WebParam(name="username")String username,
            @WebParam(name="sessionId")String sessionId){
        ArrayList<BookInfo> output = new ArrayList<>();
        
        sessionList.put(sessionId, username);
        
        DataHandler dh = new DataHandler();
        dh.INSERT(username);
        output = dh.GET(year);
        for(BookInfo p: output)
            System.out.println(p.title);
        return output;
    }  
    
    @WebMethod(operationName="rentBook")
    public String rentBook(@WebParam(name="id")int id,
            @WebParam(name="username")String username,
            @WebParam(name="sessionId")String sessionId){
        
        if(sessionList.containsKey(sessionId)){
            DataHandler dh = new DataHandler();
            boolean insertFlag = dh.UPDATE_USER(username);
            if(insertFlag){
                dh.UPDATE(id);
                return "0";
            }else               
                return "1";
        }else
            return "2";
    }  
}
