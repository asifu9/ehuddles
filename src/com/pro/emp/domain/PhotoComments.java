package com.pro.emp.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PhotoComments {

	private String id;
	private String photoId;
	private String commmentUserId;
	private String commentUserName;
	private String commentUserPhoto;
	private String commentDesc;
	private Timestamp commentDate;
	private String timeInStr;
	private List<LikeTable> likes;
	
	
	
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
	 * @return the likes
	 */
	public List<LikeTable> getLikes() {
		return likes;
	}
	/**
	 * @param likes the likes to set
	 */
	public void setLikes(List<LikeTable> likes) {
		this.likes = likes;
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
	 * @return the photoId
	 */
	public String getPhotoId() {
		return photoId;
	}
	/**
	 * @param photoId the photoId to set
	 */
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	/**
	 * @return the commmentUserId
	 */
	public String getCommmentUserId() {
		return commmentUserId;
	}
	/**
	 * @param commmentUserId the commmentUserId to set
	 */
	public void setCommmentUserId(String commmentUserId) {
		this.commmentUserId = commmentUserId;
	}
	/**
	 * @return the commentUserName
	 */
	public String getCommentUserName() {
		return commentUserName;
	}
	/**
	 * @param commentUserName the commentUserName to set
	 */
	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}
	/**
	 * @return the commentUserPhoto
	 */
	public String getCommentUserPhoto() {
		return commentUserPhoto;
	}
	/**
	 * @param commentUserPhoto the commentUserPhoto to set
	 */
	public void setCommentUserPhoto(String commentUserPhoto) {
		this.commentUserPhoto = commentUserPhoto;
	}
	/**
	 * @return the commentDesc
	 */
	public String getCommentDesc() {
		return commentDesc;
	}
	/**
	 * @param commentDesc the commentDesc to set
	 */
	public void setCommentDesc(String commentDesc) {
		this.commentDesc = commentDesc;
	}
	/**
	 * @return the commentDate
	 */
	public Timestamp getCommentDate() {
		return commentDate;
	}
	/**
	 * @param commentDate the commentDate to set
	 */
	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}

	
	
	
	
	
}
