package com.pro.emp.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

public class PhotoAlbumInfo extends Domain{

	private String idPhotoAlbum;
	private String name;
	private String coverScreenPath;
	private Timestamp createdOn;
	private Timestamp updatedOn;
	private String timeInStr;
	private String  userId;
	private int privacy;
	private String userName;
	private int count;
	private String desc;
	private int width;
	private int height;
	
	
	
	
	
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the timeInStr
	 */
	public String getTimeInStr() {
		return timeInStr;
	}
	/**
	 * @param timeInStr the timeInStr to set
	 */
	public void setTimeInStr(String timeInStr) {
		this.timeInStr = timeInStr;
	}
	/**
	 * @return the idPhotoAlbum
	 */
	public String getIdPhotoAlbum() {
		return idPhotoAlbum;
	}
	/**
	 * @param idPhotoAlbum the idPhotoAlbum to set
	 */
	public void setIdPhotoAlbum(String idPhotoAlbum) {
		this.idPhotoAlbum = idPhotoAlbum;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the coverScreenPath
	 */
	public String getCoverScreenPath() {
		return coverScreenPath;
	}
	/**
	 * @param coverScreenPath the coverScreenPath to set
	 */
	public void setCoverScreenPath(String coverScreenPath) {
		this.coverScreenPath = coverScreenPath;
	}
	/**
	 * @return the createdOn
	 */
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * @return the updatedOn
	 */
	public Timestamp getUpdatedOn() {
		return updatedOn;
	}
	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the privacy
	 */
	public int getPrivacy() {
		return privacy;
	}
	/**
	 * @param privacy the privacy to set
	 */
	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
