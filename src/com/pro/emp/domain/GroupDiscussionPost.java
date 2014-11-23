package com.pro.emp.domain;

import java.sql.Timestamp;
import java.util.List;

public class GroupDiscussionPost {

	private String key;
	private String groupId;
	private String postedById;
	private EmpBasicInfo postedByIdInfo;
	private String desc;
	private String postedPhotoId;
	private PhotoInfo postedPhotoIdInfo;
	private String postedVideoId;
	private Timestamp postedTime;
	private String postedTimeStr;
	private String postedTimeStr2;
	private List<PostGroupLikeTable> likes;
	private List<PostGroupComments> comments;

	
	public List<PostGroupLikeTable> getLikes() {
		return likes;
	}
	public void setLikes(List<PostGroupLikeTable> likes) {
		this.likes = likes;
	}
	public List<PostGroupComments> getComments() {
		return comments;
	}
	public void setComments(List<PostGroupComments> comments) {
		this.comments = comments;
	}
	public String getPostedTimeStr2() {
		return postedTimeStr2;
	}
	public void setPostedTimeStr2(String postedTimeStr2) {
		this.postedTimeStr2 = postedTimeStr2;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getPostedById() {
		return postedById;
	}
	public void setPostedById(String postedById) {
		this.postedById = postedById;
	}
	public EmpBasicInfo getPostedByIdInfo() {
		return postedByIdInfo;
	}
	public void setPostedByIdInfo(EmpBasicInfo postedByIdInfo) {
		this.postedByIdInfo = postedByIdInfo;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPostedPhotoId() {
		return postedPhotoId;
	}
	public void setPostedPhotoId(String postedPhotoId) {
		this.postedPhotoId = postedPhotoId;
	}
	public PhotoInfo getPostedPhotoIdInfo() {
		return postedPhotoIdInfo;
	}
	public void setPostedPhotoIdInfo(PhotoInfo postedPhotoIdInfo) {
		this.postedPhotoIdInfo = postedPhotoIdInfo;
	}
	public String getPostedVideoId() {
		return postedVideoId;
	}
	public void setPostedVideoId(String postedVideoId) {
		this.postedVideoId = postedVideoId;
	}
	public Timestamp getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(Timestamp postedTime) {
		this.postedTime = postedTime;
	}
	public String getPostedTimeStr() {
		return postedTimeStr;
	}
	public void setPostedTimeStr(String postedTimeStr) {
		this.postedTimeStr = postedTimeStr;
	}
	
	
	
}
