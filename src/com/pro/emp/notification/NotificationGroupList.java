package com.pro.emp.notification;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.Session;

public class NotificationGroupList {

    private static HashMap<String,Session> sessionList =null;// new ArrayList<Session>();
    private static NotificationGroupList obj=null;
    
    private NotificationGroupList(){
    	sessionList = new HashMap<String,Session>();
    }
    
    public static NotificationGroupList getInstance(){
    	if(obj==null)
    		obj=new NotificationGroupList();
    	return obj;
    }
    
    public HashMap<String,Session> getSession(){
    	return sessionList;
    }
    
    public void addToList(Session s,String userId){
    	sessionList.put(userId,s);
    	System.out.println(" size after add " + sessionList.size());
    }
    
    public void sendNotificationToUsers(Set<String> list,Object message){
    	for(String str:list){
    		try{
    			Session sess=sessionList.get(str);
    			if(sess!=null && sess.isOpen())
					try {
						sess.getBasicRemote().sendObject(message);
					} catch (EncodeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    		}catch(IOException ex){
    			ex.printStackTrace();
    		}
    	}
    }
    
    public void sendNotificationToAll(Object message){
    	System.out.println(" isze is " + sessionList.size());
    	for(String str:sessionList.keySet()){
    		try{
    			System.out.println(" key is " + str);
    			Session sess=sessionList.get(str);
    			if(sess!=null && sess.isOpen()){
					try {
						sess.getBasicRemote().sendObject(message);
					} catch (EncodeException e) {
						// TODO Auto-generated catch block
						System.out.println(" error in first exception ");
						e.printStackTrace();
					}
    			}
    		}catch(IOException ex){
    			System.out.println(" error in last exception ");
    			ex.printStackTrace();
    		}
    	}
    }
}
