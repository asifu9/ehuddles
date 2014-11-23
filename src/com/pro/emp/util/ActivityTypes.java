package com.pro.emp.util;

public enum ActivityTypes {

	/**
	 * 	public static int ACT_GROUP_CREATE=1;
	public static int ACT_GROUP_PHOTO_UPDATE=2;
	public static int ACT_GROUP_POST_ADD=3;
	public static int ACT_GROUP_POST_COMMENT=4;
	public static int ACT_GROUP_JOINED=5;
	public static int ACT_GROUP_LIKE=6;
	 * 
	 */
	
	ACT_GROUP_CREATE(1),ACT_GROUP_PHOTO_UPDATE(2),ACT_GROUP_POST_ADD(3),ACT_GROUP_POST_COMMENT(4),
	ACT_GROUP_JOINED(5),ACT_GROUP_LIKE(6);
	private int i=0;
	
	ActivityTypes(int ii){
		i=ii;
	}
	
	public int getValue(){
		return i;
	}
}
