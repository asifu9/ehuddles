package com.pro.emp.domain;

import java.util.List;
import java.util.UUID;



public class TickerInfoAll {


	private String tickerType;
	 private String key;
	 private EmpInfo userId;
	 private EmpInfo targetUserId;
	 private PhotoInfo photo;
	 private List<PhotoComments> photoComm;
	 private Follow follow;
	 
	 
	 
	 
	public Follow getFollow() {
		return follow;
	}
	public void setFollow(Follow follow) {
		this.follow = follow;
	}
	public String getTickerType() {
		return tickerType;
	}
	public void setTickerType(String tickerType) {
		this.tickerType = tickerType;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public EmpInfo getUserId() {
		return userId;
	}
	public void setUserId(EmpInfo userId) {
		this.userId = userId;
	}
	public EmpInfo getTargetUserId() {
		return targetUserId;
	}
	public void setTargetUserId(EmpInfo targetUserId) {
		this.targetUserId = targetUserId;
	}
	public PhotoInfo getPhoto() {
		return photo;
	}
	public void setPhoto(PhotoInfo photo) {
		this.photo = photo;
	}
	public List<PhotoComments> getPhotoComm() {
		return photoComm;
	}
	public void setPhotoComm(List<PhotoComments> photoComm) {
		this.photoComm = photoComm;
	}

	 
	 
	 
}
