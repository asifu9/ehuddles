package com.pro.emp.notification;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
 
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
 
@ServerEndpoint(value = "/wschat", encoders = { DomainNotifyEncoder.class }, decoders = { DomainNotifyDecoder.class })
public class WsChatServlet{

	 private static ArrayList<Session> sessionList =new ArrayList<Session>();
			 
	 
	
    @OnOpen
    public void onOpen(Session session){
        try{
            sessionList.add(session);
            System.out.println(" session id "+ session.getId());
            Map<String,String> list=session.getPathParameters();
            System.out.println(" map data " + list.toString());
            System.out.println(" map keys " + list.keySet().toString());
            System.out.println(" query name " + session.getQueryString()); 
            NotificationGroupList.getInstance().addToList(session,session.getQueryString());
            //asynchronous communication
          //  session.getBasicRemote().sendText("Hello!");
        }catch(Exception e){}
    }
    
    @OnClose
    public void onClose(Session session){
        sessionList.remove(session);
    }
    
    @OnMessage
    public void onMessage(DomainNotify message, Session sessions){
        try{
            for(Session session : sessionList){
                //asynchronous communication
            	System.out.println(" session " + session.getQueryString());
                try {
					session.getBasicRemote().sendObject("hi");
				} catch (EncodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }catch(IOException e){}
    }
}