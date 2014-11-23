var protocal='http';
function addForumNotificationDynamically(datas){
	var str='';
	var p='images/person.jpg';
	$.each(datas, function(k,item){
		var sub=item.forumIdInfo.subject;
		if(item.forumIdInfo.subject!=null && item.forumIdInfo.subject.lenght>100){
			sub=sub.substring(0,100)+'....';
		}
		if(item.postedByUserIdInfo.imagePath!=null)
			p=item.postedByUserIdInfo.imagePath.photoPath.replace("0_","7_");
		str='<div class="notifictionBlockStyle" onclick="showForum(\''+item.forumId+'\')">';
		str+='<table class="notifictionBlockStyle" style="width:100%;"><tr><td style="vertical-align: top;"><img src="'+p+'" width="30px" height="30px"></td><td style="vertical-align: top;"> ' + item.postedByUserIdInfo.empName+' replied for question "'+sub+'" </td></tr></table>';
		str+='</div>';
	});
	$("#insertNotifications").append(str);
}
function showForum(key){
	document.location.href="ShowForum?forumId="+key;
}
function addForumDynamically(item){
	
	var base = document.getElementById("basePathOfPhoto").value;
	var str='';
	var s=item.subject;
	if(s!=null && s.length>100){
		s=s.substring(0,100)+'....';
	}
	var obj=document.getElementById(item.key);
	if(obj==null){
			  		if(item.key!=null){
			  		 var p='images/person_icon.jpg';
			  			if(item.ownerIdInfo.imagePath!=null)
			  				p=base+item.ownerIdInfo.imagePath.photoPath.replace("0_","7_");
			  				str+='<div id="'+item.key+'" onclick="callMe(\''+item.key+'\')"  class="birthdaySection"  ><div id="Abc" style="display:table-row;" ><div style="display:table-cell;padding-left:3px;vertical-align: top;">';
				  			str+='<img src="'+p+'" width="30px" height="30px" ></div><div style="display:table-cell;vertical-align: top;padding-left:5px;"><a href="UserOtherFeedInfo?u='+item.ownerIdInfo.id+' "><span class="birthdayNameStyle" style="vertical-align: top;">';
				  			str+=item.ownerIdInfo.empName+' ' + '</span></a><br>&nbsp;<a class="forumSubjectNameStyle" style="text-decoration:none;"   href="ShowForum?forumId='+item.key+'">'+s+' </a></div>';
				  			str+='</div></div>';
			  			
			  		}
				 $("#insertForums").append(str);
		 
	}
	
}
function callMe(key){
	document.location.href="ShowForum?forumId="+key;
}
function deleteThread(key){
	$.getJSON("DeleteMyThread", {'thread' :key}, function(datas) { // Do an AJAX call
		location.reload();
	});
}
function addForumThreadToBody(item){
	var base = document.getElementById("basePathOfPhoto").value;
	var str='';
	var userId= document.getElementById("hiddneUserId").value;
	var sss='';
	if(userId==item.replierIdInfo.id)
		sss='<a class="closeButtonStyle" onclick="deleteThread(\''+item.key+'\')">x</a>';
	
			  		if(item.key!=null){
			  		 var p='images/person_icon.jpg';
			  			if(item.replierIdInfo.image!=null)
			  				p=base+item.replierIdInfo.image.photoPath.replace("0_","7_");
			  			str+='<div style="padding-top:5px"></div>';
			  				str+='<div class="topDiv">';
			  				str+='<div class="subjectStyleReplied"><table width="100%" class="borderForThreadHeader"><tr><td> replied on '+item.timeStr+'</td><td>'+sss+'</td></tr></table></div>';
			  				str+='<table style="totalWidth" border="0"><tr><td class="tableRowStyle" >';
				  			str+='<img src="'+p+'" width="30px" height="30px" ><br><a href="UserOtherFeedInfo?u='+item.replierIdInfo.id+' ">'+item.replierIdInfo.empName;
				  			str+='</td><td class="bodyStyle">';
				  			str+='<div><span>'+item.body+'<span> </div>';
				  			str+='</div>';
			  		}
				 $("#displayThreadBody").append(str);
			//	 var sss='<table align="right" class="replyStyle"><tr><td align="right" style="padding-right: 15px;"><input type="button" class="bttn" value="Reply" onclick="showResponseSection()"></td></tr></table>';
			//	$("#responseSection").css("display","none");
			//	$("#replySection").append(sss);
		 
	 
}
function showNewThread(){
	window.location.href="newForum.jsp";
}
function addForumTopBody(item){
	
	var base = document.getElementById("basePathOfPhoto").value;
	var str='';
	var sss='<table align="right" class="replyStyle"><tr><td align="right" style="padding-right: 0px;"><input type="button" style="font-size:12px;" class="bttn" value="Reply" onclick="showResponseSection()"></td></tr></table>';
			  		if(item.key!=null){
			  		 var p='images/person_icon.jpg';
			  			if(item.ownerIdInfo.imagePath!=null)
			  				p=base+item.ownerIdInfo.imagePath.photoPath.replace("0_","7_");
			  		
			  			str+='<title>'+item.subject+'</title><div class="subjectStyle"><table style="width:610px;" ><tr><td style="width:550px;overflow:hidden;"> '+item.subject+'</td><td align="right" width="50px" >'+sss+'</td></tr></table></div>';
			  				str+='<div style="padding-top:5px;"></div><div  class="topDiv">';
			  				str+='<div class="subjectStyleReplied"><table width="100%" class="borderForThreadHeader"><tr><td> Posted on '+item.timeStr+'</td></tr></table></div>';
			  				str+='<table  style="totalWidth" border="0"><tr><td class="tableRowStyle" >';
				  			str+='<img src="'+p+'" width="30px" height="30px" ><br><a href="UserOtherFeedInfo?u='+item.ownerIdInfo.id+' ">'+item.ownerIdInfo.empName;
				  			str+='</td><td class="bodyStyle">';
				  			str+='<div><span>'+item.body+'<span> </div></div>';
			  		}
				 $("#displayTopBody").append(str);
				$("#responseSection").css("display","none");
		 
	 
	
}
function showResponseSection(){
	var str='';
	str='';
	
	$("#responseSection").css("display","block");

	$("#idResponseText").focus();
	document.getElementById("responseSection").scrollIntoView();
	
}

function cancelPost(){
	$("#responseSection").css("display","none");
}

function postReply(){
	var forumId= document.getElementById("hiddenForumId").value;
	var userId= document.getElementById("hiddneUserId").value;
	var desc = document.getElementById("idResponseText").value;
	$.getJSON("ForumReply", {'userId' :userId,'forumId':forumId,'desc':desc}, function(datas) { // Do an AJAX call
		document.location.href="ShowForum?forumId="+forumId;
	});
}
