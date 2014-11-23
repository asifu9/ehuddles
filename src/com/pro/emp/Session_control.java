package com.pro.emp;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.Session;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.domain.EmpInfo;


/**
 *
 * @@author Asif
 */
public class Session_control {

	
    public static void setSession_(HttpServletRequest request,String userId)
    {
        HttpSession session = request.getSession(true);
       try
       {
    	  // System.out.println(" user id in set session " + userId);
           //ConnectionPool con=ConnectionPool.getInstance();

       EmpInfo emp = EmpInfoDAO.getEmpById( userId);
       if(emp!=null)
       {
           session.setAttribute("user", emp);
      
    
           //System.out.println("session has been setted");
       }else{
    	   //System.out.println(" here we are ");
       }


       }
       catch(Exception exp)
       {
       }
    
    }

//    public static boolean getProfileUpdate(HttpServletRequest request){
//    	 HttpSession session = request.getSession(true);
//         boolean result=true;
//        // System.out.println(" session.getAttribute(profile) "+ session.getAttribute("profile"));
//           if(session.getAttribute("profile") != null)
//        {
//        	   
//            if(session.getAttribute("profile").equals("false"))
//            	result=false;
//        }
//           return result;
//    }
//    public static void setProfileUpdate(HttpServletRequest request){
//   	 HttpSession session = request.getSession(true);
//        session.setAttribute("profile", true);
//   }
    public static EmpInfo getSession(HttpServletRequest request)
    {
         HttpSession session = request.getSession(true);
         EmpInfo obbb=null;
           if(session.getAttribute("user") != null)
        {
            EmpInfo  ob_= (EmpInfo)session.getAttribute("user");
            obbb=ob_;
        }
        
         return obbb;
    }
    public static EmpInfo getOtherInfo(HttpServletRequest request)
    {
         HttpSession session = request.getSession(true);
         EmpInfo obbb=null;
           if(session.getAttribute("otherUser") != null)
        {
            EmpInfo  ob_= (EmpInfo)session.getAttribute("otherUser");
            obbb=ob_;
        }
        
         return obbb;
    }
    public static void setOtherUserInfo(HttpServletRequest request,String userId){
    	 HttpSession session = request.getSession(true);
         try
         {
      	  // System.out.println(" user id in set session " + userId);
             //ConnectionPool con=ConnectionPool.getInstance();
         EmpInfo emp = EmpInfoDAO.getEmpById( userId);
         if(emp!=null)
         {
             session.setAttribute("otherUser", emp);
        
      
             //System.out.println("session has been setted");
         }else{
      	   //System.out.println(" here we are ");
         }


         }
         catch(Exception exp)
         {
         }
    }
    



}

