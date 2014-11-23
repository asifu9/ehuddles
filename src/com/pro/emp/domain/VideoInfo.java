package com.pro.emp.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


public class VideoInfo {

	private String idVideoInfo;
	
	private String videoPath;
	private String description;
	private Timestamp creaedOn;
	private List<LikeTable> likeInfo;
	private String ownerId;

	
	public String getIdVideoInfo() {
		return idVideoInfo;
	}
	public void setIdVideoInfo(String idVideoInfo) {
		this.idVideoInfo = idVideoInfo;
	}
	/**
	 * @return the videoPath
	 */
	public String getVideoPath() {
		return videoPath;
	}
	/**
	 * @param videoPath the videoPath to set
	 */
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
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
	/**
	 * @return the likeInfo
	 */
	public List<LikeTable> getLikeInfo() {
		return likeInfo;
	}
	/**
	 * @param likeInfo the likeInfo to set
	 */
	public void setLikeInfo(List<LikeTable> likeInfo) {
		this.likeInfo = likeInfo;
	}
	/**
	 * @return the ownerId
	 */
	public String getOwnerId() {
		return ownerId;
	}
	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	
}
