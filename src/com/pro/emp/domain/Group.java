package com.pro.emp.domain;

import java.sql.Timestamp;

public class Group {

	/**
	 * "CREATE TABLE group ("+
	    		   "KEY text PRIMARY KEY,"+
	    		   "name text,"+
	    		   "ownerId text,description text,"+
	    		   "indicator int,createdOn timestamp) ";
	 */
	
	private String key;
	private String name;
	private String ownerId;
	private String description;
	private Timestamp createdOn;
	private String createdOnStr;
	private EmpBasicInfo ownerIdInfo;
	private String photoId;
	private PhotoInfo photoIdInfo;
	private int memberCount;
	private int isMember;
	
	
	public int getIsMember() {
		return isMember;
	}
	public void setIsMember(int isMember) {
		this.isMember = isMember;
	}
	public int getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	public PhotoInfo getPhotoIdInfo() {
		return photoIdInfo;
	}
	public void setPhotoIdInfo(PhotoInfo photoIdInfo) {
		this.photoIdInfo = photoIdInfo;
	}
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedOnStr() {
		return createdOnStr;
	}
	public void setCreatedOnStr(String createdOnStr) {
		this.createdOnStr = createdOnStr;
	}
	public EmpBasicInfo getOwnerIdInfo() {
		return ownerIdInfo;
	}
	public void setOwnerIdInfo(EmpBasicInfo ownerIdInfo) {
		this.ownerIdInfo = ownerIdInfo;
	}
	
	
}
