package com.pro.emp;

import java.util.Comparator;
import com.pro.emp.domain.VideoInfo;

public class SortVideos implements Comparator<Object>{
private String type="";
	
	public SortVideos(String typ){
		this.type=typ;
	}
	
	@Override
	public int compare(Object arg0, Object arg1) {
		int result=0;
		if(type.equalsIgnoreCase("VideoInfo")){
			VideoInfo p = (VideoInfo)arg0;
			VideoInfo p2= (VideoInfo)arg1;
			result=p.getIdVideoInfo().compareTo(p2.getIdVideoInfo());
		}
		return result;
	}
}
