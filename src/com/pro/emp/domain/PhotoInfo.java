package com.pro.emp.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


public class PhotoInfo {
	private String idPhotoInfo;
	private String idPhotoAlbum;
	private String photoPath;
	private String description;
	private Timestamp creaedOn;
	private List<LikeTable> likeInfo;
	private int width;
	private int height;
	private String ownerId;
	private String photoCreationDate;
	
	
	public String getPhotoCreationDate() {
		return photoCreationDate;
	}
	public void setPhotoCreationDate(String photoCreationDate) {
		this.photoCreationDate = photoCreationDate;
	}

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
	public List<LikeTable> getLikeInfo() {
		return likeInfo;
	}
	public void setLikeInfo(List<LikeTable> likeInfo) {
		this.likeInfo = likeInfo;
	}

	
	public String getIdPhotoInfo() {
		return idPhotoInfo;
	}
	public void setIdPhotoInfo(String idPhotoInfo) {
		this.idPhotoInfo = idPhotoInfo;
	}
	public String getIdPhotoAlbum() {
		return idPhotoAlbum;
	}
	public void setIdPhotoAlbum(String idPhotoAlbum) {
		this.idPhotoAlbum = idPhotoAlbum;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * @return the photoPath
	 */
	public String getPhotoPath() {
		return photoPath;
	}
	/**
	 * @param photoPath the photoPath to set
	 */
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the creaedOn
	 */
	public Timestamp getCreaedOn() {
		return creaedOn;
	}
	/**
	 * @param creaedOn the creaedOn to set
	 */
	public void setCreaedOn(Timestamp creaedOn) {
		this.creaedOn = creaedOn;
	}
	
	
}
