package com.pro.emp;

import java.util.Comparator;

import com.pro.emp.domain.EmpInfo;


public class EmpSortByEmpID implements Comparator<EmpInfo>{

    public int compare(EmpInfo o1, EmpInfo o2) {
    
        if( o2.getEmpName().compareTo(o1.getEmpName())>1)
        	return -1;
        else if(o2.getEmpName().compareTo(o1.getEmpName())<1)
        	return 1;
        else 
        	return 0;
    }
}
