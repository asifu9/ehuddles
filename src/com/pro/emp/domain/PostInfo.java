package com.pro.emp.domain;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class PostInfo {

	private String key;
	private String postedById;
	private String postedDesc;
	private String postedPhotoId;
	private String postedVideoId;
	private String postedToId;
	private Timestamp postedTime;
	private String postedTimeStr;
	private int flow;
	private EmpBasicInfo postedByUserInfo;
	private EmpBasicInfo postedToUserInfo;
	public EmpBasicInfo getPostedToUserInfo() {
		return postedToUserInfo;
	}
	public void setPostedToUserInfo(EmpBasicInfo postedToUserInfo) {
		this.postedToUserInfo = postedToUserInfo;
	}
	private PhotoInfo postedByPhotoInfo;
	private int privatestatus;
	private List<PostLikeTable> likes;
	private List<PostComments> comments;
	private List<PhotoInfo> photoList;
	private int postType;
	private int photoCount;
	private String postedTimeStr2;
	private VideoInfo videoInfo;
	private String dateToDelete;
	
	
	
	public String getDateToDelete() {
		return dateToDelete;
	}
	public void setDateToDelete(String dateToDelete) {
		this.dateToDelete = dateToDelete;
	}
	public int getFlow() {
		return flow;
	}
	public void setFlow(int flow) {
		this.flow = flow;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getPostedById() {
		return postedById;
	}
	public void setPostedById(String postedById) {
		this.postedById = postedById;
	}
	public String getPostedDesc() {
		return postedDesc;
	}
	public void setPostedDesc(String postedDesc) {
		this.postedDesc = postedDesc;
	}
	public String getPostedPhotoId() {
		return postedPhotoId;
	}
	public void setPostedPhotoId(String postedPhotoId) {
		this.postedPhotoId = postedPhotoId;
	}
	public String getPostedVideoId() {
		return postedVideoId;
	}
	public void setPostedVideoId(String postedVideoId) {
		this.postedVideoId = postedVideoId;
	}
	public String getPostedToId() {
		return postedToId;
	}
	public void setPostedToId(String postedToId) {
		this.postedToId = postedToId;
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
	public EmpBasicInfo getPostedByUserInfo() {
		return postedByUserInfo;
	}
	public void setPostedByUserInfo(EmpBasicInfo postedByUserInfo) {
		this.postedByUserInfo = postedByUserInfo;
	}
	public PhotoInfo getPostedByPhotoInfo() {
		return postedByPhotoInfo;
	}
	public void setPostedByPhotoInfo(PhotoInfo postedByPhotoInfo) {
		this.postedByPhotoInfo = postedByPhotoInfo;
	}
	public int getPrivatestatus() {
		return privatestatus;
	}
	public void setPrivatestatus(int privatestatus) {
		this.privatestatus = privatestatus;
	}
	public List<PostLikeTable> getLikes() {
		return likes;
	}
	public void setLikes(List<PostLikeTable> likes) {
		this.likes = likes;
	}
	public List<PostComments> getComments() {
		return comments;
	}
	public void setComments(List<PostComments> comments) {
		this.comments = comments;
	}
	public List<PhotoInfo> getPhotoList() {
		return photoList;
	}
	public void setPhotoList(List<PhotoInfo> photoList) {
		this.photoList = photoList;
	}
	public int getPostType() {
		return postType;
	}
	public void setPostType(int postType) {
		this.postType = postType;
	}
	public int getPhotoCount() {
		return photoCount;
	}
	public void setPhotoCount(int photoCount) {
		this.photoCount = photoCount;
	}
	public String getPostedTimeStr2() {
		return postedTimeStr2;
	}
	public void setPostedTimeStr2(String postedTimeStr2) {
		this.postedTimeStr2 = postedTimeStr2;
	}
	public VideoInfo getVideoInfo() {
		return videoInfo;
	}
	public void setVideoInfo(VideoInfo videoInfo) {
		this.videoInfo = videoInfo;
	}
	
	
	
	
	
}
