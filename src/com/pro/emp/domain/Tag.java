package com.pro.emp.domain;

import java.util.Date;

public class Tag {

	  private String key;
		        private String    photoId;
		        private String    ownerId ;
		        private String    taggedUserId;
		        private String    desc;
		        private Date    tagTime;
		        private int    follow;
		        
		        private EmpBasicInfo taggerUserIdInfo;
		        private EmpBasicInfo ownerIdInfo;
		        private PhotoInfo photoIdInfo;
				public String getKey() {
					return key;
				}
				public void setKey(String key) {
					this.key = key;
				}
				public String getPhotoId() {
					return photoId;
				}
				public void setPhotoId(String photoId) {
					this.photoId = photoId;
				}
				public String getOwnerId() {
					return ownerId;
				}
				public void setOwnerId(String ownerId) {
					this.ownerId = ownerId;
				}
				public String getTaggedUserId() {
					return taggedUserId;
				}
				public void setTaggedUserId(String taggedUserId) {
					this.taggedUserId = taggedUserId;
				}
				public String getDesc() {
					return desc;
				}
				public void setDesc(String desc) {
					this.desc = desc;
				}
				public Date getTagTime() {
					return tagTime;
				}
				public void setTagTime(Date tagTime) {
					this.tagTime = tagTime;
				}
				public int getFollow() {
					return follow;
				}
				public void setFollow(int follow) {
					this.follow = follow;
				}
				public EmpBasicInfo getTaggerUserIdInfo() {
					return taggerUserIdInfo;
				}
				public void setTaggerUserIdInfo(EmpBasicInfo taggerUserIdInfo) {
					this.taggerUserIdInfo = taggerUserIdInfo;
				}
				public EmpBasicInfo getOwnerIdInfo() {
					return ownerIdInfo;
				}
				public void setOwnerIdInfo(EmpBasicInfo ownerIdInfo) {
					this.ownerIdInfo = ownerIdInfo;
				}
				public PhotoInfo getPhotoIdInfo() {
					return photoIdInfo;
				}
				public void setPhotoIdInfo(PhotoInfo photoIdInfo) {
					this.photoIdInfo = photoIdInfo;
				}

		        
}
