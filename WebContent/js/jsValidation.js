

function isEmpty(str){
	 if (!str || 0 === str.length || /^\s*$/.test(str))
		 return true;
	 
	 return false;
}


function isEmptyPassword(str){
	 if (!str || 0 === str.length)
		 return true;
	 
	 return false;
}

function isPasswordMatch(str1,str2){
	if(str1==str2)
		return false;
	
	return true;
}

function isValideEmailID(str){
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
     if(reg.test(str) == false) 
	      return true;
     
     return false;
}


