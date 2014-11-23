/* 
 * CacheData.java
 * 
 * Copyright (c) by Velocity Systems and Solutions, LCC.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Velocity System and Solutions, LCC Inc. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license
 * agreement you entered into with Velocity Systems Inc.
 * 
 * 
 */
package com.pro.cache;



import com.pro.emp.domain.EmpBasicInfo;


/**
 * Class to hold cached data for a specific time of interval
 * @author Asif
 * @version 1.0
 * 
 */
public class CacheData {

	private long time;
	private EmpBasicInfo data;
	
	/**----------------------------------------------------------------------*/
	/**
	 * Getter method for time
	 * @return
	 */
	public long getTime() {
		return time;
	}
	
	/**----------------------------------------------------------------------*/
	/**
	 * Setter method for time
	 * @param time
	 */
	public void setTime(long time) {
		this.time = time;
	}

	public  EmpBasicInfo getData() {
		return data;
	}

	public void setData( EmpBasicInfo data) {
		this.data = data;
	}
	
	
	
	/**----------------------------------------------------------------------*/
}
