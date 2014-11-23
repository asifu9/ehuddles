package com.pro.emp.domain;


import com.pro.post.domain.UserBasicInfo;

public class DomainUserMix {

	com.pro.post.domain.UserBasicInfo info=new UserBasicInfo();

	public com.pro.post.domain.UserBasicInfo getInfo() {
		return info;
	}

	public void setInfo(com.pro.post.domain.UserBasicInfo info) {
		this.info = info;
	}
	
	
}
