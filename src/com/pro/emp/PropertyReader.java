package com.pro.emp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PropertyReader {

	
	public static String getValue(String att,HttpServletRequest req){
		// Read properties file.
		Properties properties = new Properties();
		try {
		    properties.load(new FileInputStream("c:\\config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(att.equalsIgnoreCase("photoAlbumPath")){
			System.out.println("ssssssssss");
			System.out.println(req.getRequestURI());
			System.out.println( "URL is here : "+ req.getRequestURL());
			System.out.println(" req.getProtocol() "+ req.getProtocol());
			System.out.println("path of the server " + req.getPathInfo() + " : "+ req.getServerName() + " : " +req.getServerPort());
			//return "http://"+req.getServerName()+":"+req.getServerPort()+"/PhotoStatic/userUploads/";
				return "http://"+req.getServerName()+":"+req.getServerPort()+"/PhotoStatic/userUploads/";
			//return "http://172.16.136.16/PhotoStatic/userUploads/";
			
		}
		return properties.getProperty(att);
	}
	public static String getValue(String att){
		// Read properties file.
		Properties properties = new Properties();
		try {
		    properties.load(new FileInputStream("c:\\config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return properties.getProperty(att);
	}
}
