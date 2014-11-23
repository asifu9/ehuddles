package com.pro.emp.domain;

import java.util.Date;
import java.util.UUID;

public class LikeTable {

	private String id;
	private String itemId;
	private String likedUserId;
	private String userPhotoPath;
	private Date likedDate;
	private String userName;
	
	
	
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
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the likedUserId
	 */
	public String getLikedUserId() {
		return likedUserId;
	}
	/**
	 * @param likedUserId the likedUserId to set
	 */
	public void setLikedUserId(String likedUserId) {
		this.likedUserId = likedUserId;
	}
	/**
	 * @return the userPhotoPath
	 */
	public String getUserPhotoPath() {
		return userPhotoPath;
	}
	/**
	 * @param userPhotoPath the userPhotoPath to set
	 */
	public void setUserPhotoPath(String userPhotoPath) {
		this.userPhotoPath = userPhotoPath;
	}
	/**
	 * @return the likedDate
	 */
	public Date getLikedDate() {
		return likedDate;
	}
	/**
	 * @param likedDate the likedDate to set
	 */
	public void setLikedDate(Date likedDate) {
		this.likedDate = likedDate;
	}
	
	
}
