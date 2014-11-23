package com.pro.emp.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

public class PhotoAlbum {

	private String idPhotoAlbum;
	private String name;
	private String desc;
	private String coverScreenPath;
	private Timestamp createdOn;
	private Timestamp updatedOn;
	private String  userId;
	private int privacy;
	private String strCreatedOn;
	private int height;
	private int width;
	private int albumType;
	private int count;
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getAlbumType() {
		return albumType;
	}
	public void setAlbumType(int albumType) {
		this.albumType = albumType;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the strCreatedOn
	 */
	public String getStrCreatedOn() {
		return strCreatedOn;
	}
	/**
	 * @param strCreatedOn the strCreatedOn to set
	 */
	public void setStrCreatedOn(String strCreatedOn) {
		this.strCreatedOn = strCreatedOn;
	}
	/**
	 * @return the privacy
	 */
	public int getPrivacy() {
		return privacy;
	}
	/**
	 * @param privacy the privacy to set
	 */
	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}
	/**
	 * @return the idPhotoAlbum
	 */

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the coverScreenPath
	 */
	public String getCoverScreenPath() {
		return coverScreenPath;
	}
	/**
	 * @param coverScreenPath the coverScreenPath to set
	 */
	public void setCoverScreenPath(String coverScreenPath) {
		this.coverScreenPath = coverScreenPath;
	}
	/**
	 * @return the createdOn
	 */
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * @return the updatedOn
	 */
	public Timestamp getUpdatedOn() {
		return updatedOn;
	}
	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getIdPhotoAlbum() {
		return idPhotoAlbum;
	}
	public void setIdPhotoAlbum(String idPhotoAlbum) {
		this.idPhotoAlbum = idPhotoAlbum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
}
