package com.pro.post.domain;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.pro.emp.dao.PhotoAlbumDAO;
import com.pro.emp.domain.Domain;
import com.pro.emp.domain.EmpActivityInfo;
import com.pro.emp.domain.EmpAdditionalInfo;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpCompanyInfo;
import com.pro.emp.domain.EmpIdproofInfo;
import com.pro.emp.domain.EmpInfo;
import com.pro.emp.domain.Follow;
import com.pro.emp.domain.Group;
import com.pro.emp.domain.PhotoAlbum;
import com.pro.emp.domain.PhotoAlbumInfo;
import com.pro.emp.domain.PhotoInfo;
import com.pro.friends.domain.Friends;

public class UserBasicInfo extends Domain{

	private String key;
	private EmpInfo empInfo;
	private EmpAdditionalInfo addInfo;
	private List<PhotoAlbum> photoAlbumns; 
	private List<EmpBasicInfo> colleagues;
	private PhotoInfo coverPage;
	private List<Follow> followers;
	
	
	
	public List<Follow> getFollowers() {
		return followers;
	}
	public void setFollowers(List<Follow> followers) {
		this.followers = followers;
	}
	public PhotoInfo getCoverPage() {
		return coverPage;
	}
	public void setCoverPage(PhotoInfo coverPage) {
		this.coverPage = coverPage;
	}
	public List<EmpBasicInfo> getColleagues() {
		return colleagues;
	}
	public void setColleagues(List<EmpBasicInfo> colleagues) {
		this.colleagues = colleagues;
	}
	public List<PhotoAlbum> getPhotoAlbumns() {
		return photoAlbumns;
	}
	public void setPhotoAlbumns(List<PhotoAlbum> photoAlbumns) {
		this.photoAlbumns = photoAlbumns;
	}
	public String getKey() {
		
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public EmpInfo getEmpInfo() {
		return empInfo;
	}
	public void setEmpInfo(EmpInfo empInfo) {
		this.empInfo = empInfo;
	}
	public EmpAdditionalInfo getAddInfo() {
		return addInfo;
	}
	public void setAddInfo(EmpAdditionalInfo addInfo) {
		this.addInfo = addInfo;
	}
	
}
