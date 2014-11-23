/*
 * CacheRecords.java
 *
 * Copyright (c) by Velocity Systems and Solutions, LCC.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Velocity System and Solutions, LCC Inc. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license
 * agreement you entered into with Velocity Systems Inc.
 *
 * Author : Asif U
 * Creation : March 9 2012
 */

package com.pro.cache;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.core.Session;
import com.pro.emp.CassandraDB;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.domain.EmpBasicInfo;

/**
 * Class to define singleton Cache implementation which store key
 * along with its data(value) in map. (Cache data)
 * @author Asif
 * @version 1.0
 * 
 */
public class CacheRecords {

	private static LRUCache<String, CacheData> cache=null;
	private static CacheRecords ob=null;
	/**----------------------------------------------------------------------*/
	/**
	 * Private Constructor to make this class singleton
	 */
	private CacheRecords(){
		cache=new LRUCache<String, CacheData>(300);
	}
	
	/**----------------------------------------------------------------------*/
	/**
	 * Singleton method to get the CacheRecords instance
	 * @return CacheRecords
	 */ 
	public static CacheRecords getInstance(){
		if(ob==null){
			ob=new CacheRecords();
		}
		return ob;
	}
	

	/**----------------------------------------------------------------------*/	
	/**
	 * Method to get the cached data for given command
	 * If returned Object is null then cache may be not having data or cache time expired.
	 * else it will return the data stored in cache
	 * @param key - command ( nmcAccountNumber + StartDate + EndDate + ReportType )
	 * @return Object
	 */
	public EmpBasicInfo getCacheData(String key){
		
		EmpBasicInfo obj=null;
		if(cache.containsKey(key)) {
			// cache contains data for the given command, now check for time 
			int minutes = (int) ((int)((new Date().getTime()) - cache.get(key).getTime())/((double)1000*60));
			
			// if time exceeds more than the given time (configuration in minutes)
			// then remove data from cache
			// else return the data stored in cache
			if(minutes < 10){
				obj= cache.get(key).getData();
			}
			else{ 
				// lets remove the cache
				clearCache(key);
				Session con = CassandraDB.getCassConnection();
				try {
					if(key==null){}else{
				obj = EmpInfoDAO.getBasicEmpById_(con, key);
				setCacheData(key, obj);
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				finally{
					//.CassandraDB.freeConnection(con);
				}
			}
			
		}else{
			Session con = CassandraDB.getCassConnection();
			try {
			obj = EmpInfoDAO.getBasicEmpById_(con, key);
			setCacheData(key, obj);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				//CassandraDB.freeConnection(con);
			}
		}
		
		return obj;
	}
	
	/**----------------------------------------------------------------------*/	
	/**
	 * Method to set the data in cache along with its command
	 * @param key
	 * @param data
	 */
	public void setCacheData(String key, EmpBasicInfo data){
		
		try {
			
			//System.out.println(" cache data to be storing is " + key);
			// remove the old entry if exist and 				
			// create new CacheData object and store in cache map
			CacheData cdata=new CacheData();
			cdata.setData(data);
			cdata.setTime(new Date().getTime());
			synchronized(cache){
				cache.remove(key);
				cache.put(key, cdata);
			}
		}
		catch(Exception e){
		}
		
	}
	
	/**----------------------------------------------------------------------*/
	/**
	 * Method to remove data belongs to particular command
	 * from the cache
	 * @param key
	 */
	public void clearCache(String key){
		
		try {
			if(cache.containsKey(key)){
				
				synchronized(cache){
					cache.remove(key);
				}
			}
		}
		catch(Exception e){
		}

	}
	
	
	/**----------------------------------------------------------------------*/	
}
