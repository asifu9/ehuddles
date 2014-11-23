package com.pro.emp;

import java.util.Calendar;
import java.util.Comparator;

import com.pro.emp.domain.PhotoInfo;

public class SortPhotos implements Comparator<Object>{

	private String type="";
	
	public SortPhotos(String typ){
		this.type=typ;
	}
	
	@Override
	public int compare(Object arg0, Object arg1) {
		int result=0;
		if(type.equalsIgnoreCase("PhotoAlbum")){
			PhotoInfo p = (PhotoInfo)arg0;
			PhotoInfo p2= (PhotoInfo)arg1;
			if(p!=null && p2!=null && p.getCreaedOn()!=null && p2.getCreaedOn()!=null){
			Calendar c1= Calendar.getInstance();
			c1.setTimeInMillis(p.getCreaedOn().getTime());
			
			Calendar c2= Calendar.getInstance();
			c2.setTimeInMillis(p2.getCreaedOn().getTime());
			result=c1.compareTo(c2);
			}
			else{
				return 0;
			}
		}
		return result;
	}

}
