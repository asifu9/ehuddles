package com.pro.emp.domain;

import java.util.ArrayList;
import java.util.List;

public class DomainMix {

	List<PhotoAlbumInfo> photo=new ArrayList<PhotoAlbumInfo>();
	List<PublicChat> chat=new ArrayList<PublicChat>();
	List<EmpInfo> emp=new ArrayList<EmpInfo>();
	List<TickerInfoDomain> ticker=new ArrayList<TickerInfoDomain>();
	
	
	
	public List<TickerInfoDomain> getTicker() {
		return ticker;
	}
	public void setTicker(List<TickerInfoDomain> ticker) {
		this.ticker = ticker;
	}
	public List<PhotoAlbumInfo> getPhoto() {
		return photo;
	}
	public void setPhoto(List<PhotoAlbumInfo> photo) {
		this.photo = photo;
	}
	public List<PublicChat> getChat() {
		return chat;
	}
	public void setChat(List<PublicChat> chat) {
		this.chat = chat;
	}
	public List<EmpInfo> getEmp() {
		return emp;
	}
	public void setEmp(List<EmpInfo> emp) {
		this.emp = emp;
	}
	
}
