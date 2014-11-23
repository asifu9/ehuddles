var protocal='http'; 
$(document).ready(function () {
		$('#addMails').slimScroll({
		     height: '350px',
		     alwaysVisible: false,
		     size: '8px',
		     color: '#555555'
		     });
	 
		$('#noReplay').slimScroll({
		     height: '350px',
		     alwaysVisible: false,
		     size: '8px',
		     color: '#555555'
		     });
		
		$(".mailSectionNew").click(function() {
			 
			 $("#addMails > div[id]").each(function(){
				    var context = $(this).attr('id');
				    var c='#'+context;
				    $(c).css({'background-color':'#EEF7FB'});
				  	
				});
			 
			var keyFull = $(this).attr('id');
			
			  var k='#'+keyFull;
			  $(k).css({'background-color':'#A2D5EA'});
		});
		$(".mailSection").click(function() {
			 
			 $("#addMails > div[id]").each(function(){
				    var context = $(this).attr('id');
				    var x=$(this).css("backgroundColor");
				    var c='#'+context;
				    if(x=='rgb(97, 183, 220)'){}else{
				    $(c).css({'background-color':'#EEF7FB'});
				    }
				    var $bac=$(this).css("background-color");
				    
				});
			
			var keyFull = $(this).attr('id');
			  var k='#'+keyFull;
			  var sssss= document.getElementById("txtEmpId").value;
			  udpateStatus(keyFull,sssss);
			  $(k).css({'background-color':'#A2D5EA'});
		});
	 
 });
 function hexc(colorval) {
	    var parts = colorval.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
	    delete(parts[0]);
	    for (var i = 1; i <= 3; ++i) {
	        parts[i] = parseInt(parts[i]).toString(16);
	        if (parts[i].length == 1) parts[i] = '0' + parts[i];
	    }
	    color = '#' + parts.join('');
	}
	
 function replyMessageMe(toUserId){
 	var usId= document.getElementById("txtEmpId").value;

// 	var mess=document.getElementById("mes").value;
 	var m=document.getElementById("replyMessage").value;
 	var path=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
 	$.getJSON("CreateMessage", {'idlist' :toUserId,'fromId':usId,'message':m}, function(datass) {
 		
 		$.each(datass, function(i,datas){
 		var p='images/person.jpg';
 		if(datas.fromIdInfo.imagePath!=null || datas.fromIdInfo.imagePath!='')
 			p=path+datas.fromIdInfo.imagePath.photoPath;
 		str='<div class="eachMailStyle" >';
 		str+='<table width="100%"><tr><td style="vertical-align: top;" width="30px"><img src="'+p+'" width="30px" height="30px" ></td>';
 		str+='<td class="adjustTable" style="vertical-align: top;"><table width="100%"><tr><td align="left" ><span class="userNameStleMail" >'+datas.fromIdInfo.empName+'</span><span class="justifyMessage">'+m+'</span></td></tr></table></td></tr></table>';
 		str+='<span class="userDateTimeMailStyle">1 secon ago</span>';
 		str+='</div>';
 		$("#replayToTHisThrewad").prepend(str);
 		});
 	});
 	document.getElementById("replyMessage").value="";
 }
 function displayMessage(userId){
		var usId=  document.getElementById("txtEmpId").value;
		$("#addUserMails").html("");
		$("#newMailSection").css('display','none');
		var str1='';
		var str='';
		str1+='<div class="replySectionOk" > <textarea rows="2" cols="48" id="replyMessage" name="replyMessage"></textarea><br><div align="right"><input class="buttonStyleInSend" type="button" onclick="replyMessageMe(\''+userId+'\')" value="Reply"></div></div><div id="replayToTHisThrewad"></div>';
		$("#addUserMails").append(str1);
		$.getJSON("GetMessagesByMail", {'userId' :usId,'mailedUserId':userId,'flow':'inbox'}, function(datas) { // Do an AJAX call
			$.each(datas, function(i,item){
				var st='';
				if(item.status=='0' && userId== item.fromIdInfo.id)
					st='style="background-color:#61B7DC;" ';
				
			str='<div class="eachMailStyle" '+st+' id="'+item.key+'">';
			var pp='images/person.jpg';
			var path=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
			if(item.fromIdInfo.imagePath!=null)
				pp=path+item.fromIdInfo.imagePath.photoPath.replace("0_","7_");
			str+='<table width="100%"><tr><td style="vertical-align: top;" width="30px"><img src="'+pp+'" width="30px" height="30px" ></td>';
			str+='<td class="adjustTable" style="vertical-align: top;"><table width="100%"><tr><td align="left" ><span class="userNameStleMail" >'+item.fromIdInfo.empName+'</span><span class="justifyMessage">'+item.mailIdInfo.message+'</span></td></tr></table></td></tr></table>';
			str+='<span class="userDateTimeMailStyle">'+item.mailTimeStr+'</span>';
			str+='</div>';
			$("#addUserMails").append(str);
		});
		});
		 $('#addUserMails').slimScroll({
		     height: '349px',
		     alwaysVisible: false,
		     size: '8px',
		     color: '#555555'
		     });
	}

 function displayMessageSent(userId){
 	var usId= document.getElementById("txtEmpId").value;
 	$("#addUserMails").html("");
 	$("#newMailSection").css('display','none');
 	var str1='';
 	var str='';
 	var path=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
 	//str1+='<div class="replySectionOk" > <textarea rows="2" cols="48" id="replyMessage" name="replyMessage"></textarea><br><div align="right"><input class="buttonStyleInSend" type="button" onclick="replyMessageMe(\''+userId+'\')" value="Reply"></div></div><div id="replayToTHisThrewad"></div>';
 	$("#addUserMails").append(str1);
 	$.getJSON("GetMessagesByMail", {'userId' :usId,'mailedUserId':userId,'flow':'sent'}, function(datas) { // Do an AJAX call
 		$.each(datas, function(i,item){
 			var st='';
 			if(item.status=='0' && userId== item.fromIdInfo.id)
 				st='style="background-color:#61B7DC;" ';
 		var pp='images/person.jpg';
 		if(item.fromIdInfo.imagePath!=null)
 			pp=path+item.fromIdInfo.imagePath.photoPath.replace("0_","7_");
 		str='<div class="eachMailStyle" '+st+' id="'+item.key+'">';
 		str+='<table width="100%"><tr><td style="vertical-align: top;" width="30px"><img src="'+pp+'" width="30px" height="30px" ></td>';
 		str+='<td class="adjustTable" style="vertical-align: top;"><table width="100%"><tr><td align="left" ><span class="userNameStleMail" >'+item.fromIdInfo.empName+'</span><span class="justifyMessage">'+item.mailIdInfo.message+'</span></td></tr></table></td></tr></table>';
 		str+='<span class="userDateTimeMailStyle">'+item.mailTimeStr+'</span>';
 		str+='</div>';
 		$("#addUserMails").append(str);
 	});
 	});
 	 $('#addUserMails').slimScroll({
 	     height: '349px',
 	     alwaysVisible: false,
 	     size: '8px',
 	     color: '#555555'
 	     });
 }
 function sendMessage(){
		var list='';
		$(".selectedContactDiv > div[id]").each(function(){
		    var context = $(this);
		    list+=this.id+'~~~';
		    
		});
		var mess=document.getElementById("mes").value;
		var fromId=document.getElementById("currentId").value;
		if(list=='' || mess==''){}else{
		$.getJSON("CreateMessage", {'idlist' :list,'fromId':fromId,'message':mess}, function(datas) {
			  document.getElementById('myText').innerHTML = 'Message Sent Successfully';

		
		});
		document.getElementById("mes").value="";
		}
	}

 function udpateStatus(context,usId){
		context = context.replace('_1','');
		$.getJSON("UpdateMailStatus", {'fromId' :context,'toId':usId}, function(ddatas) {
			
		});
	}
function showCommentDeleteButton(key){

	var k = 'deleteComment_'+key;
	var ss=document.getElementById(k);
	if(ss!=null)
		ss.style.display='block';
}
function hideCommentDeleteButton(key){
	var k = 'deleteComment_'+key;
	
	var ss=document.getElementById(k);
	if(ss!=null)
		ss.style.display='none';
}
function addInboxDynamically(item){
	

	var str='';
	var m=null;
	if(item.mailIdInfo!=null)
	m= item.mailIdInfo.message;

	if(m!=null && m.length>20)
		m=m.substring(0,20);
		var st='';
		var co=item.newMessageCount;
		var nameAndDes=item.fromIdInfo.empName;
		if(co!='0'){
			st='style="background-color:#61B7DC;" ';
			nameAndDes=	item.fromIdInfo.empName+'('+item.newMessageCount+')';
		}
		var path=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
				
		//if(item.status!='0')
			var pp ='images/person.jpg';
			if(item.fromIdInfo.imagePath!=null)
				pp=path+item.fromIdInfo.imagePath.photoPath.replace("0_","7_");
		str='<div class="mailSection" width="100%" '+st+' onclick="displayMessage(\''+item.fromIdInfo.id+'\')" id="'+item.fromIdInfo.id+'_1">';
		str+='<table style="width: 100%;" border="0"><tr><td width="40px"><img src="'+pp+'" width="30px" height="30px">';
		str+='</td><td><table><tr><td>'+nameAndDes+'</td></tr><tr><td>' +m+ '</td></tr></table></td></tr></table>';
		str+='</div>';
		if(document.getElementById(item.fromIdInfo.id+'_1')==null)
			$("#addMails").append(str);

	 
	
}
function showNewMessageSection(){
	 $("#addUserMails").html("");
	 $("#newMailSection").css('display','block');
	 
	 
}

function lookup(inputString,searchType) {
		if(inputString.length == 0) {
			$('#suggestions').fadeOut(); // Hide the suggestions box
		} else {
			$("#suggestions").height("auto");
			$.post("SearchUsers", {queryString: ""+inputString+"~"+searchType+""}, function(data) { // Do an AJAX call
				$('#suggestions').fadeIn(); // Show the suggestions box
				
				$('#suggestions').html(data); // Fill the suggestions box
				
				 $('#searchresultsMessage').slimScroll({
			         height: '225px',
			         alwaysVisible: false,
			         size: '8px',
			         color: '#555555'
			         });
			});
		}
	}

function addSentItemDynamically(item){
	
	var m=null;
	if(item.mailIdInfo!=null)
	m= item.mailIdInfo.message;
	var path=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
	if(m!=null && m.length>20)
		m=m.substring(0,20);
		var st='';
		var co=item.newMessageCount;
		var nameAndDes=item.toIdInfo.empName;
//		if(co!='0'){
//			st='style="background-color:#61B7DC;" ';
//			nameAndDes=	item.toIdInfo.empName+'('+item.newMessageCount+')';
//		}
		
				
		//if(item.status!='0')
		var pp='images/person.jpg';
		if(item.toIdInfo.imagePath!=null)
			pp=path+item.toIdInfo.imagePath.photoPath.replace("0_","7_");
		str='<div class="mailSection" width="250px" '+st+' onclick="displayMessageSent(\''+item.toIdInfo.id+'\')" id="'+item.toIdInfo.id+'_2">';
		str+='<table style="width: 100%;" border="0" ><tr><td width="40px" align="left"><img src="'+pp+'" width="30px" height="30px">';
		str+='<td class="adjustTable" style="vertical-align: top;"><table width="100%"><tr><td>'+nameAndDes+'</td></tr><tr><td >' +m+ '</td></tr><table style="width: 100%;" border="0" ><tr><td width="40px" align="left"><span class="userDateTimeMailStyle">'+item.mailTimeStr+'</span></td></tr></table></td></tr> </td></tr></table></table>';
		str+='</div>';
		//str+='<span class="userDateTimeMailStyle">'+item.mailTimeStr2+'</span>';
		str+='</div>';
		if(document.getElementById(item.fromIdInfo.id+'_2')==null)
			$("#noReplay").append(str);
	
}
function  registerEventGroup(){
	
	

		// $(".hidingCommentPanel").hide();
		  //toggle the componenet with class msg_body
//		  $(".himeCommentsStyle").click(function()
//		  {
//			  
//			  var keyFull=$(this).attr('id');
//			  var pbk = keyFull.replace('linkComment_','#hide_');
//			 
//		   // jQuery(this).next(".content").slideToggle(500);
//		  });
		
		
		
	
	 $(".myCommentStyle").blur(function() {
	        if ($(this).val() == "") {
	            $(this).val("Write a comment")
	                   .removeClass("comment_filled")
	                   .addClass("comment_empty");
	          
	        }
	    }).focus(function() {
	        if ($(this).val() == "Write a comment") {
	            $(this).val("")
	                   .removeClass("comment_empty")
	                   .addClass("comment_filled");
	           
	        }
	    });
	 $("#enlargeMe").hide();
	   $("textarea").blur(function() {
	        if ($(this).val() == "") {
	            $(this).val("what's on your mind?")
	                   .removeClass("comment_filled1")
	                   .addClass("comment_empty1");
	          $("#enlargeMe").hide();
	        }
	    }).focus(function() {
	        if ($(this).val() == "what's on your mind?") {
	            $(this).val("")
	                   .removeClass("comment_empty1")
	                   .addClass("comment_filled1");
	            $("#enlargeMe").show();
	        }
	    });

	$(".myCommentStyle").keyup(function(event){
	    if(event.keyCode == 13){
	        var keyFull=$(this).attr('id');
	        var key = keyFull.replace('txtCommentForPost_','');
	        var ddd=keyFull;
	        var commentDesc = document.getElementById(ddd).value;
	    	//var data = document.getElementById("txtNewPost").value;
	    	var id = document.getElementById("hiddenUserId").value;
	    	var imagePath = document.getElementById("txtPhotoPath").value;
	    	if(commentDesc=="" ||commentDesc==null)
	    		return;

	    	 $.getJSON("CreateCommentForGroupPost", {'commentedById' :id,'commentDesc':commentDesc,'postId':key,'flow':1}, function(datas) { // Do an AJAX call
	    		  	var photo = datas.commentUserPhoto;
	    		  	var userName = datas.commentUserName;
	    		  	var comment = datas.commentDesc;
	    		  	var commentPostId = datas.postId;
	    		  	var key = datas.key;
	    		  	var str="";
	    		  	str+='<div  id="deleteCom_'+key+'" class="commentStyle" onmouseover="showCommentDeleteButton(\''+key+'\')\" onmouseout="hideCommentDeleteButton(\''+key+'\')\" >';
	    			str+='<div class="commentPhotoStyle">';
	    			str+='<div class="photoSpaceStyle"><img src="'+photo+'" width="50px" '+
	    			'height="50px">';
	    			str+='</div></div>';
	    			
	    			str+='<div class="commentPhotoStyle1" width="800px"><span width="100%" class="commentedUserNameStyle" >'+userName+ '</span>: <span width="100%" class="commentedDescriptionStyle" > '+comment+'</span><br><span class="commentTimeStyle">1 Minute ago</span> </div>';
	    			str+='<div><div id="deleteComment_'+key+'" class="deleteCommentStyle"><span class="commentDeleteTextStyle" onclick="deleteComment(\''+key+'\')\" >x</span> </div></div>';
	    			str+='</div>';
	    			var dd = '#comment_'+commentPostId;
	    			var ssss= 'appendKey_'+key;
	    			
	    			 $(dd).append(str);
	    	  });
	    	 
	    	 document.getElementById(ddd).value='';
	    }
	});
	
	$(".linkStyleHref").click(function(){
		var status=$(this).html();
		var keyFull=$(this).attr('id');
		var spanText = ".span_"+keyFull + " span";
		var currentValue=$('#'+keyFull+ '').html();
		var key = keyFull.replace("span_Like_","");
		var userId = document.getElementById("hiddenUserId").value;
		var data = trim(currentValue);
		var makeUp='';
		var popup='';
		if(data=="Like"){
			  $.getJSON("CreateLikeForGroupPost", {'userId' :userId,'postId':key}, function(datas) { // Do an AJAX call
				  	$('#'+keyFull+ '').text("Unlike");
				  var sss=	'#likeMessage_'+key;
				 
				//
			      var str="<span class=\"myNameStyle\">You";
			      var count=0;
			      var i=0;
			      var countFlag=0;
			  	 $.each(datas, function(k,item){
			  		
			  		var user=item.userName;
			  		if(userId==item.likedUserId){
			  			i=i+1;
			  			
			  		}else{
			  			if(count<=1){
						  if(str=="")
							  str=user;
						  else
							  str=str+ ", <a href=\"#\" class=\"likeNameStyleLink\" >"+user+"</a>";
			  		}else{
			  			if(item.userPhotoPath!=null)
			  				popup+='<div class="blockUpoupStyle"><table><tr><td><img src="'+item.userPhotoPath+'" width="20px" height="20px"><td></td><td>'+item.userName+'</td></tr></table></div>';
			  			else 
			  				popup+='<div class="blockUpoupStyle"><table><tr><td><img src="images/person_icon.jpg" width="20px" height="20px"><td></td><td>'+item.userName+'</td></tr></table></div>';
			  			countFlag=1;
			  		}
			  		i=i+1;
					  count=count+1;
			  		}
			  	 });
			  	 
			  	 if(countFlag==1){
			  		 if((i-3)==0){}else{
			  			popup+='<div class="blockUpoupStyle" align="right"><input type="button" class="buttonStyle" onclick="hideAllLIkes()" value="close"></div>';
						 makeUp = '<div id="displayLike_'+key+'" class="likesPopup" style="display:none;position:absolute;" >'+popup+'</div>';
			  		 str=str+ ' and '+(i-3) + '<a id="hrefLink_'+key+'" class="likeNameStyleLink" onclick="popupLikesUser(\''+key+'\')"> other</a> like this.</span>'+makeUp;
			  		 }
			  	 }
				  
				  
				
					  $(sss).html(str);
				  
				  
			  });
		}else{
			var id = keyFull.replace('span_Like_','');
			  $.getJSON("DelelePostLike", {'postId' :id,'userId':userId}, function(datas) { // Do an AJAX call
				 
				      var str="";
				      var count=0;
				      var i=0;
				      var countFlag=0;
				  	 $.each(datas, function(k,item){
				  		
				  		var user=item.userName;
				  		if(count<=2){
						  if(str=="")
							  str="<span class=\"myNameStyle\" > <a href=\"#\" class=\"likeNameStyleLink\" >"+user+"</a>";
						  else
							  str=str+ ", <a href=\"#\" class=\"likeNameStyleLink\" >"+user+"</a>";
				  		}else{
				  			countFlag=1;
				  			if(item.userPhotoPath!=null)
				  				popup+='<div class="blockUpoupStyle"><table><tr><td><img src="'+item.userPhotoPath+'" width="20px" height="20px"><td></td><td>'+item.userName+'</td></tr></table></div>';
				  			else 
				  				popup+='<div class="blockUpoupStyle"><table><tr><td><img src="images/person_icon.jpg" width="20px" height="20px"><td></td><td>'+item.userName+'</td></tr></table></div>';
				  			
				  		}
				  		i=i+1;
						  count=count+1;
						  
				  	 });
				  	 
				  	 if(countFlag==1){
				  		 if((i-3)==0){}else{
				  			popup+='<div class="blockUpoupStyle" align="right"><input type="button" class="buttonStyle" onclick="hideAllLIkes()" value="close"></div>';
							 var makeUp = '<div id="displayLike_'+key+'" class="likesPopup" style="display:none;position:absolute;" >'+popup+'</div>';
				  		 
				  			str=str+ ' and '+(i-3) + '<a class="likeNameStyleLink" id="hrefLink_'+key+'" onclick="popupLikesUser(\''+key+'\')">  other</a> like this.</span>'+makeUp;
					  		 }
				  	 }
				  $('#'+keyFull+ '').text("Like");
				  
				  var sss=	'#likeMessage_'+key;
				  
				 	//
					  $(sss).html(str);
				  
			  });
		}
		
	});
	
}


function showDeleteButton(key){

	var k = 'delete_'+key;
	var obj=document.getElementById(k);
	if(obj!=null)
		obj.style.display='block';
}

function hideDeleteButton(key){
	var k = 'delete_'+key;
	var obj=document.getElementById(k);
	if(obj!=null)
		obj.style.display='none';
}
function removeMe(id){
	 $('#'+id).remove();
 }
 function addMetoList(id,photo,name){
	 var str='';
	 str='<div class="displayContacts" id="'+id+'"><table width="100%"><tr><td width="30px"><img src="'+photo+'" width="30px" height="30px"></td><td>'+name+'</td><td aligh="right" width="10px"><span class="closeOption" onclick="removeMe(\''+id+'\')">x</span></td></tr></table> </div>';
	 if(document.getElementById(id)==null)
 	 $("#addNamesDynamically").append(str);
	 $("#suggestions").html("");
  }
 function addMyGroupPostDynamically (item){
	 var empId= document.getElementById("hiddenUserId").value;
	    var imagePPPPath = document.getElementById("txtPhotoPath").value;
	    var paaaaath = document.getElementById("basePathOfPhoto").value;
	    var makeUp='';
	var str='';
	var likedUserNames=new Array();
	var likemessage='';
	var likedCount=-1;
	
	
	var likeStatus='';	
		var key=item.key;
		
	var desc=item.desc;
	var postedById= item.postedById;
	var postedPhotoId= item.postedPhotoId;
	var postedVideoId= item.postedVideoId;
	var postedToId= item.postedToId;
	var postedTimeStr= item.postedTimeStr;
	likeStatus='';
	var popup='';
	if(item.likes!=null){
		likeStatus='';
		likemessage='';
	  		$.each(item.likes,function(l,li){
				 
				var postId= li.postId;
				var likedUserId= li.likedUserId;
				var userPhotoPath= li.userPhotoPath;
				var likedDate= li.likedDate;
				var key= li.key;
				var userName= li.userName;

				if(likedUserId==empId){
					likeStatus='Unlike';
				}else{
					likedCount+=1;
					likedUserNames[likedCount]=new Array(3);
					likedUserNames[likedCount][0]=userName;
					likedUserNames[likedCount][2]=likedUserId;
					if(userPhotoPath!=null)
						likedUserNames[likedCount][1]=userPhotoPath;
					else
						likedUserNames[likedCount][1]='images/person_icon.jpg';
				}
			 });
		}
			if(likeStatus=='Unlike'){
			likemessage='<span class="myNameStyle" > You';
			var i=1;
			var y=0;
			var tempCount=likedCount+1;
			while(y<=likedCount){
				
				if(i<=2){
					likemessage+=', <a href="UserOtherFeedInfo?u='+likedUserNames[y][2]+'"  class="likeNameStyleLink">'+likedUserNames[y][0]+'</a>';
				}else{
					popup+='<div class="blockUpoupStyle"><table><tr><td><img src="'+likedUserNames[y][1]+'" width="40px" height="40px"><td></td><td class="alignNameStle"><a href="UserOtherFeedInfo?u='+likedUserNames[y][2]+'">'+likedUserNames[y][0]+'</a></td></tr></table></div>';
				}
				y=y+1;
				i=i+1;
			}
			if(tempCount>2){
				popup+='<div class="blockUpoupStyle" align="right"><input type="button" class="buttonStyle" onclick="hideAllLIkes()" value="close"></div>';
				 makeUp = '<div id="displayLike_'+key+'" class="likesPopup" style="display:none;position:absolute;" >'+popup+'</div>';
				likemessage+= ' and '+(tempCount-2)+ ' <a id="hrefLink_'+key+'" class="likeNameStyleLink"  onclick="popupLikesUser(\''+key+'\')">other</a> like this.</span>';
			}
		}else{
			likeStatus='Like';
			var i=0;
			var y=0;
			var tempCount=likedCount;
			while(y<=likedCount){
				
				if(i<=2){
					if(likemessage==''){
						likemessage+='<span class="myNameStyle" > <a href="UserOtherFeedInfo?u='+likedUserNames[y][2]+'"  class="likeNameStyleLink">'+likedUserNames[y][0]+'</a>';
					}else{
						likemessage+=',  <a href="UserOtherFeedInfo?u='+likedUserNames[y][2]+'"  class="likeNameStyleLink">'+likedUserNames[y][0]+'</a>';
					}
					
					i=i+1;
				}else{
					popup+='<div class="blockUpoupStyle"><table><tr><td><img src="'+likedUserNames[y][1]+'" width="20px" height="20px"><td></td><td>'+likedUserNames[y][0]+'</td></tr></table></div>';
				}
				y=y+1;
			}
			if(tempCount>2){
				if(!(tempCount-2==0)){
					popup+='<div class="blockUpoupStyle" align="right"><input type="button" class="buttonStyle" onclick="hideAllLIkes()" value="close"></div>';
					 makeUp = '<div id="displayLike_'+key+'" class="likesPopup" style="display:none;position:absolute;" >'+popup+'</div>';
					 likemessage+= ' and '+(tempCount-2)+ ' <a id="hrefLink_'+key+'" class="likeNameStyleLink" onclick="popupLikesUser(\''+key+'\')">other</a> like this.</span>';
				}
				
			}
		}
		

			str=str+'<div id="'+key+'"  onmouseOut="hideDeleteButton(\''+key+'\')\" onmouseOver="showDeleteButton(\''+key+'\')" class="postSection" width="100%">';
		str+='<div class="postSectionInside">';
		if(empId== postedById){
		str+='<div id="delete_'+key+'" class="deletePostStyle" onclick="deletePost(\''+key+'\')" ><img  src="img/deletePost.png"/></div>';
		}
		
		str+='<div class="imageSection"> <img src="'+item.postedByIdInfo.imagePath+'" width="50px" height="50px"></div> ';
		str+='<div class="descSection"> <span class="postedUserNameStyle"><a class="linkStyleForPostNames" href="UserOtherFeedInfo?u='+postedById+'">'+ item.postedByIdInfo.empName +'</a></span>:<br><span "postDescriptionStyle">';
		
		if(desc==null)
			str+='';
		else
			str+=desc;
		str+='</span>';
		
		
	var isPhoto=0;
//	if(item.postType==1 ){
//		var isFine=0;
//		if(item.photoCount>=4)
//			isFine=1;
//		var sep=0;
//		str+='<br><table><tr>';
//		$.each(item.photoList,function(j,p){
//				if(j>3){}else{
//				if(isFine==1){
//					var ss=p.photoPath;
//					if(ss!=null)
//						ss=ss.replace("/0_","/7_");
//					if(sep==2){
//						sep=0;
//						str+='</tr><tr>';
//					}
//					str+='<td class="linkOnPhoto"><a  onclick="updateHiddenBox(\''+p.idPhotoAlbum+'\',\''+p.ownerId+'\',\''+p.photoPath+'\',\'1\',\''+p.width+'\',\''+p.height+'\')" > <img src="'+paaaaath+''+ss+'" /></a></td>';
//					sep+=1;
//					
//					
//				}else{
//					if(isPhoto==0){
//					var ss=p.photoPath;
//					if(ss!=null)
//						ss=ss.replace("/0_","/5_");
//					if(sep==2){
//						sep=0;
//						str+='</tr><tr>';
//					}
//					str+='<td ><a  onclick="updateHiddenBox(\''+p.idPhotoAlbum+'\',\''+p.ownerId+'\',\''+p.photoPath+'\',\'1\',\''+p.width+'\',\''+p.height+'\')" > <img class="linkOnPhotoOne" src="'+paaaaath+''+ss+'" /></a></td>';
//					sep+=1;
//					}
//				}
//				}
//				isPhoto=1;
//				
//				
//			
//			});
//		str+='</table></div>';
//	
//	}else
	if(item.postedByPhotoInfo!=null ){
		
	
		isPhoto=1;
		var ss=item.postedByPhotoInfo.photoPath;
		
		if(item.postedByPhotoInfo.width> 251){
			if(ss!=null)
				ss=ss.replace("/0_","/3_");
			str+='<br><table><tr><td class="linkOnPhoto"><a  onclick="updateHiddenBox(\''+item.postedByPhotoInfo.idPhotoAlbum+'\',\''+item.postedByPhotoInfo.ownerId+'\',\''+item.postedByPhotoInfo.photoPath+'\',\'1\',\''+item.postedByPhotoInfo.width+'\',\''+item.postedByPhotoInfo.height+'\')" > <img src="'+paaaaath+''+ss+'" width="300px"/></a></td></tr></table>';
			
		}else{
			if(ss!=null)
				ss=ss.replace("/0_","/5_");
		str+='<br><table><tr><td class="linkOnPhoto"><a  onclick="updateHiddenBox(\''+item.postedByPhotoInfo.idPhotoAlbum+'\',\''+item.postedByPhotoInfo.ownerId+'\',\''+item.postedByPhotoInfo.photoPath+'\',\'1\',\''+item.postedByPhotoInfo.width+'\',\''+item.postedByPhotoInfo.height+'\')" > <img src="'+paaaaath+''+ss+'" /></a></td></tr></table>';
		}
		str+='</div>';
	
	}
	if(isPhoto==0)
		str+='</div>';
	var count =0;
	if(item.comments!=null){
		var count = item.comments.length;
	}
		
	str=str+'<div style="padding-left:60px;">';
	str+='<div class="LikeSection"><div class="commentPhotoStyleComment" width="100%\><a  class="linkStyleHref" alt="aabcd"  id="Like_'+key+'"><span class="linkStyleHref" id="span_Like_'+key+'">' +likeStatus+'</span> </a>.<a class="linkStyleHref" onclick="focusCommentBox(\''+key+'\')"> Comment</a> &nbsp;.&nbsp;<span class="commentTimeStyle"> '+postedTimeStr+'</span> </div></div>';
	str+='<div class="borderForLike"><div><table><tr><td class=""><img src="img/icon_like.png" height="20px" /></td><td><span id="likeMessage_'+key+'" class="likeTextStyle">'+likemessage+'</span></td></tr></table></div></div>';
	if(count>2){
	str+='<div class="borderForLike1"><div><table><tr><td class="textAlign"><a  id="linkComment_'+key+'" onclick="showAllComments(\'#hide_'+key+'\')" class="himeCommentsStyle"><img src="img/comments.png" height="20px" />&nbsp;&nbsp;View other '+(count-3)+' comments</a></td></tr></table></div></div>';
	}
	str+='<div id="comment_'+key+'" class="CurrentUserCommentSection">';
	if(item.comments!=null){
		 count = item.comments.length;
		var hideCount=0;
		if(!(count<=2))
			hideCount=count-2;
		var flagHide=0;
		if(hideCount>0){
			flagHide=1;
			str+='<div class="hidingCommentPanel" id="hide_'+key+'" >';
		}
	 $.each(item.comments,function(j,comm){
			 console.log(' comments index ' + j);
			 var postId=comm.postId;
			var commentDate=comm.commentDate;
			var commmentUserId=comm.commmentUserId;
			var commentDesc=comm.commentDesc;
			var commentUserName=comm.commentUserName;
			var commentUserPhoto=comm.commentUserPhoto;
			 var timeInStr = comm.timeInStr;
			 var commentkey = comm.key;
			 
			 if(hideCount>=0){
				 hideCount=hideCount-1;
				 if(hideCount==0){
					 if(flagHide=1){
						 str=str+'</div>';
					 }
				 }
			 }
			str+='<div id="deleteCom_'+commentkey+'" class="commentStyle" onmouseover="showCommentDeleteButton(\''+commentkey+'\')" onmouseout="hideCommentDeleteButton(\''+commentkey+'\')" >';
		
		
		str+='<div class="commentPhotoStyle" >';
		
		str+='<div class="photoSpaceStyle"><img src="'+commentUserPhoto+'" width="50px" height="50px">';
		str+='</div></div>';
		
		str+='<div class="commentPhotoStyle1" width="800px"><span width="100%" class="commentedUserNameStyle" ><a class="linksToOTherPages" href="UserOtherFeedInfo?u='+commmentUserId+ '">'+commentUserName+'</a></span>: <span width="100%" class="commentedDescriptionStyle" > '+commentDesc+'</span> <br><span class="commentTimeStyle"> ' + timeInStr+'</span></div>';
		
		if(commmentUserId==empId){
		str+='<div><div id="deleteComment_'+commentkey+'" class="deleteCommentStyle"><span class="commentDeleteTextStyle" onclick="deleteComment(\''+commentkey+'\')" >x</span> </div></div>';
		}
		
		str+="</div>";
			 
			 });
}
	str=str+'</div>';
	str+='<div id="appendKey_'+key+'" class="commentStyle1111">';
	str+='<div class="commentPhotoStyle" width="10px"><div class="photoSpaceStyle"><img src="'+imagePPPPath+'" width="50px" height="50px"></div></div>';
	str+='<div class="commentPhotoStyle2" width="100%"> <input class="myCommentStyle"  style="width:300px;height:18px;" type="text" value="Write a comment"  id="txtCommentForPost_'+key+'" /></div>';
	str+='</div>';
	
	str+='</div>';
	str+='</div>';
	str+=makeUp+'</div>';
	var ssof=document.getElementById("lastTime");
	if(ssof!=null)
		document.getElementById("lastTime").value=item.postedTimeStr2;
		$("#groupDiscussion").append(str);
	
	
		
		
		 }
function showCreatePopup(){
	$('#newGroup').css('display','block');
	  var obj = document.getElementById("createButton");
	  var posX = obj.offsetLeft;
	  var posY = obj.offsetTop;
	  $('#newGroup').css('left',posX-320);
	  $('#newGroup').css('top',posY);
	  $('#newGroup').css('z-index',10);
}
function populateData(){
	
	var userId= document.getElementById("hiddenUserId").value;
	 $.getJSON("GetGroups", {'userId' :userId,'flow':1}, function(datas) {
			 $.each(datas, function(k,item){
				 addGroupDivDynamically(item);
			 });
	  	  });
	 $.getJSON("GetGroups", {'userId' :userId,'flow':0}, function(datas) {
		 $.each(datas, function(k,item){
			 addOthersGroupDivDynamically(item);
		 });
  	  });
 
	 
	 
	
}
function submitGroupPost(){

	var data = document.getElementById("groupPost").value;
	if(data=="" || data==null)
		return;
	var id = document.getElementById("hiddenUserId").value;
	var imagePath = document.getElementById("txtPhotoPath").value;
	var name= document.getElementById("txtEmpName").value;
	var groupId= document.getElementById("hiddenGroupId").value;
	var qStringIs= 'postDesc='+data +
					'&userId='+id+''; 
	 $.getJSON("CreateNewGroupPost", {'desc' :data,'groupId':groupId,'postedById':id}, function(datas) { // Do an AJAX call
	  		var postedById=datas.postedById;
			  var postedDesc1=datas.desc;
			  var postedPhotoId= datas.postedPhotoId;
			  var postedVideoId =datas.postedVideoId;
			  var postedTime = datas.postedTime;
			  var postedByPhotoInfo =datas.postedByPhotoInfo;
			  var key = datas.key;
			 
			  createPostDiv(key,postedDesc1,imagePath,postedTime,name,postedById);
	
	  });
	 
	 document.getElementById("groupPost").value='';
	
	

	
	
}
function joinGroup(groupId,userId){
	
	$.getJSON("JoinGroup", {'userId' :userId,'groupId':groupId}, function(datas) {
		$('#joinIdForGroup').html('<span class="joinLinkClass" aling="left"><img src="images/right_mark.gif" class="memberImageStyle" >Member</span>');
 	  });

	
}
function addGroupDivDynamically(item){
	
	var str='';
	var p='images/person_icon.jpg';
	var name= item.name;
	var key = item.key;
	var desc= item.description;
	var members = item.memberCount;
	if(item.photoId!=null)
		p=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/'+item.photoIdInfo.photoPath;
	str+='<div class="GroupSection" id="'+key+'"><a href="GroupFeeds?groupId='+key+'"><table border="0" align="left"><tr><td><img src="'+p+'" width="100px" height="100px"></td>';
	str+='<td class="sectionColumnGroup"> <Table><tr><td><span  class="groupName"> '+name+'</span> </td></tr><tr><td><span class="groupDescStyle"> '+desc+'</span></td></tr><tr><td><span class="MemberCount"> Members '+members+' </span></td></tr></table>';
	str+='</td></tr></table></a></div>';
	$("#insertGroups").append(str);
	
}

function addOthersGroupDivDynamically(item){
	
	var str='';
	var p='images/person_icon.jpg';
	var name= item.name;
	var key = item.key;
	var desc= item.description;
	var memberString='';
	if(item.isMember==1){
		memberString='<span class="joinLinkClass" aling="left"><img src="images/right_mark.gif" class="memberImageStyle1" >Member</span>';
	}
		
	
	
	if(item.photoId!=null)
		p=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/'+item.photoIdInfo.photoPath;
	var members = item.memberCount;
	if(document.getElementById(key)==null){
		str+='<div class="GroupSectionOthers" id="'+key+'" align="left"><a href="GroupFeeds?groupId='+key+'"><table cellpadding="0" cellspacing="0" border="0" align="left"><tr><td style="padding-top:5px;vertical-align:top;"><img src="'+p+'" width="60px" height="60px"></td>';
		str+='<td class="sectionColumnGroup"> <Table cellpadding="0" cellspacing="0"><tr><td><span  class="groupName"> '+name+'</span> </td></tr><tr><td><span class="groupDescStyle"> '+desc+'</span></td></tr><tr><td><span class="MemberCount"> Members '+members+' </span></td></tr><tr><td><span class="UserInfoDispayBy">by</span><span class="postedUserNameStyle"><a class="removeDecoration" href="UserOtherFeedInfo?u='+item.ownerIdInfo.id+'">'+item.ownerIdInfo.empName +'</a></span></td></tr><tr><td>'+memberString+' </td></tr></table>';
		str+='</td></tr></table></a></div>';
	$("#insertOthersGroups").append(str);
	}
}
function hideCreateGroup(){
	$('#newGroup').css('display','none');
}
 function createGroup(){
		var userId= document.getElementById("hiddenUserId").value;
	  	 var name= document.getElementById("groupName").value;
	  	var desc=document.getElementById("description").value;
	  	
	  	if(name=="" || name==null)
	  		return;
	  	//var path='http://'+window.location.host+'/PhotoStatic/userUploads/';
	  	  $.getJSON("CreateGroup", {'userId' :userId,'name':name,'desc':desc}, function(datas) {
	  		
	  		addGroupDivDynamically(datas);
	  		$('#newGroup').css('display','none');
	  	  });
	  }
 
function likePhoto(photoId,key){
	 var currentUserId= document.getElementById("hiddenCurrentUser").value;
	 var queryString=photoId+'~@~'+currentUserId+'~@~'+'Like';
	 $.getJSON("createLike", {inputQueryString : queryString}, function(datass) { // Do an AJAX call
		 getTickerDisplay(key);
	 });
	
}
function focusComment (){
	document.getElementById("commentTextInTicker").focus();
}
 function UnlikePhoto(photoId,key,key2){
	 var currentUserId= document.getElementById("hiddenCurrentUser").value;
	 var queryString=photoId+'~@~'+currentUserId+'~@~'+'Unlike'+'~@~'+key;
	 $.getJSON("createLike", {inputQueryString : queryString}, function(datass) { // Do an AJAX call
			
		 getTickerDisplay(key2);
	 });
 }

 function test() {
	   
	   $.getJSON("GetTickersLatest", {'datee' :latestTickerDate}, function(datas) { // Do an AJAX call
		   var str='';
			$.each(datas, function(k,item){
				var key = item.key;
				
				var message = item.message;
	 	  		if(item.userId!=null){
	 	  		 var p='images/person_icon.jpg';
	 	  			if(item.targetUserId.imagePath!=null)
	 	  				p=item.targetUserId.imagePath;
	 	  		
	 	  				latestTickerDate = item.date;
	 	  			str+='<div id="'+key+'"  class="tickerSection"  ><div id="Abc" style="display:table-row;" ><div style="display:table-cell;">';
	 	  			str+='<img src="img/progressbar.gif" width="30px" id="'+key+'_progress" hieght="20px"><img src="'+p+'" id="'+key+'_photo" width="30px" height="30px" ></div><div style="display:table-cell;vertical-align: top;padding-left:5px;width:100%;"><span class="tickerUserNameStyle">';
	 	  			str+=item.targetUserId.empName+' ' + '</span> <span class="tickerMessageStyle">'+message+' </span></div>';
	 	  			str+='</div></div>';
	 	  			
	 	  		}
			});
			$("#insertTickers").prepend(str);
			
//			$('.tickerSection').children().bind('mouseenter', function () {
//				   var keyFull=$(this).parent().attr('id');
//				   var k = '#'+keyFull;
//				   var kk='#'+k+'_progress';
//				   var kkk='#'+k+'_photo';
//				   $(kkk).css({'display':'none'});
//				   alert("1");
//				   $(kk).css({'display':'block'});
//				   alert("2");
//				   $(k).css({'background-color':'#EEEEEE'});
//				  // getTickerDisplay(keyFull);
//			   
//			});
//			$('.tickerSection').children().bind('mouseleave', function () {
//				   var keyFull=$(this).parent().attr('id');
//				   var k = '#'+keyFull;
//				   $(k).css({'background-color':'#DFF0F7'});
//				  // getTickerDisplay(keyFull);
//			   
//			});
//			
	   
	   
 });
 }
 

 function  registerEventTicker(){
	 
	 $("input").live("keyup", function(event){
		 
		    if(event.keyCode == 13){

		    	var postId = document.getElementById("postInTickerId").value;
		    	var currentUserId= document.getElementById("hiddenCurrentUser").value;
		    	var commentDesc = document.getElementById("commentTextInTicker").value;
		    	var currentUserName=document.getElementById("txtEmpName").value;
		    	var photoId=document.getElementById("myPhotoId").value;
		    	if(commentDesc=="" ||commentDesc==null)
		    		return;
		    	var str='';
		    	 $.getJSON("CreateComment", {'photoId' :photoId,'commentUserId':currentUserId,'text':commentDesc}, function(datas) { // Do an AJAX call
		    		 // $.each(datas, function(l,items){
		    		// alert(" llooop " + datas.commentUserPhoto);
		    			  var ph=datas.commentUserPhoto;
		    			  if(ph=='')
		    				  ph='images/person_icon.jpg';
		    			  str+='<div style="display:table-row;width:100%" class="borderForSec2"><div class="borderForSec2" style="width:100%;"><div style="display:table-cell;padding-top:5px;vertical-align: top;width:10%;"><img src="'+ph+'" width="30px" height="30px"></div><div style="display:table-cell;padding-top:5px;vertical-align: top;width:90%;"><span class="tickerCOmmentUserNameStyle">'+currentUserName+'</span><br><span class="tickerCOmmentsStyle">'+commentDesc+'<span></div></div></div>';
		    			 // alert(" stt  ssssss " + str);
		    			  $("#myTickerSectionId").prepend(str);
		    	 });
		
		    	
//		    	 $.getJSON("CreateCommentForPost", {'commentedById' :currentUserId,'commentDesc':commentDesc,'postId':postId}, function(datas) { // Do an AJAX call
//		    		  //	alert(" created comment : " + datas);
//		    		  	var photo = datas.commentUserPhoto;
//		    		  	var userName = datas.commentUserName;
//		    		  	var comment = datas.commentDesc;
//		    		  	var commentPostId = datas.postId;
//		    		  	var key = datas.key;
//		    		  	var str="";
//		    		  	if(photo==null)
//		    		  		photo='images/person_icon.jpg';
//		    		  	str+='<div style="display:table-row;width:100%" class="borderForSec"><div class="borderForSec" style="width:100%;"><div style="display:table-cell;padding-top:5px;vertical-align: top;width:10%;"><img src="'+photo+'" width="30px" height="30px"></div><div style="display:table-cell;padding-top:5px;vertical-align: top;width:90%;"><span class="tickerCOmmentUserNameStyle">'+comm.commentUserName+'</span><br><span class="tickerCOmmentsStyle">'+comm.commentDesc+'<span></div></div></div>';
//		    		  	
//		    		  	$("#userDiv").append(str);
//		    	  });
		    	 
		    	 document.getElementById("commentTextInTicker").value='';
		    }
		});
	 
	 
 }
 
 
 function shareMyPhoto(key){
	 var str='';
	 var userDiv= document.getElementById('sharePopupHere');
	 var path=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
	 str+='<div class="sharePopupClassStyle" style="z-index:100;" align="left">';
	 
	 $.getJSON("GetPhotoInfo", {'photoId' :key}, function(datas) { // Do an AJAX call
		str+='<table class="tableBackGroundShare" width="100%"><tr><td><span class="shareHeadingStyle"> Share Photo </span> </td></tr></table>';
		 str+='<table><tr><td>';
		str+='<table><tr><td><img src="'+path+datas.photoPath.replace('0_','5_')+'" width="100px" height="100px"></td></tr></table>';
		str+='</td><td style="vertical-align:top;">';
		str+='<table><tr><td><input type="text" id="shareDesc" size="40px"></td></tr><tr><td><input type="button" class="shareButtonStyle" value="Share" onclick="shareMyPostInTicker(\''+key+'\')">&nbsp;&nbsp;<input type="button"  class="shareButtonStyle"  value="Cancle" onclick="closeSharePopup()"></td></tr></table>';
		str+='</td></tr></table>';
		 str+='';
		 str+='</div>';
		 
		 
	   		 //var offset = $(keyUser).offset();
	   		 // alert(" hi "+keyUser);
	   		  var obj = document.getElementById("sharePointOk");
	   		  var posX = obj.offsetLeft;
	   		  var posY = obj.offsetTop;
	  
	   while(obj.offsetParent){
	   	  posX=posX+obj.offsetParent.offsetLeft;
	   	  posY=posY+obj.offsetParent.offsetTop;
	   	  if(obj==document.getElementsByTagName('body')[0]){break;}
	   	  else{obj=obj.offsetParent;}
	   	  }
			  $('#sharePopupHere').css('box-shadow','0px 4px 4px 0px black');
		  $('#sharePopupHere').css('left',posX-310);
		  $('#sharePopupHere').css('top',posY-300);
		   $('#sharePopupHere').css('display','block');
		   
		   
		   userDiv.innerHTML=str;
	 });

	 
	
 }
 function trim(s)
 {

 	var l=0; var r=s.length -1;
 	while(l < s.length && s[l] == ' ')
 	{	l++; }
 	while(r > l && s[r] == ' ')
 	{	r-=1;	}
 	return s.substring(l, r+1);
 }

 function shareMyPostInTicker(photoId){
		
		var shareComment = document.getElementById("shareDesc").value;
		var userId = document.getElementById("hiddenCurrentUser").value;
		
		$.getJSON("CreateNewPost", {'photoId' :photoId,'userId':userId,'postDesc':shareComment,'targetId':null,'privacy':0}, function(datas) { // Do an AJAX call
				// alert(" data s" + datas);
				 // $.each(datas, function(l,items){
					 // parent.disablePopup();
			closeSharePopup();
				 });
		
	}
 function closeSharePopup(){
	 $('#sharePopupHere').css('display','none');
 }
 

 function createPostDiv(key,desc,photoPath,time,name,postedById){
 	var object= document.getElementById("insertPost");


 	var div='<div id="'+key+'" onmouseOut="hideDeleteButton(\''+key+'\')" onmouseOver="showDeleteButton(\''+key+'\')" class="postSection" width="100%">';
 	div=div+'<div class="postSectionInside">';
 	div+='<div><div id="delete_'+key+'" class="deletePostStyle" onclick="deletePost(\''+key+'\')"> <img  src=\"img/deletePost.png\"/></div>';
 	
 	div+='<div class="imageSection"> <img src="'+photoPath+'" width=\'50px\' height=\'50px\'></div>';
 	div+='<div class="descSection"> <span class="postedUserNameStyle"><a class="removeDecoration" href="UserOtherFeedInfo?u='+postedById+'">'+name +'</a></span>:<span "postDescriptionStyle">' + desc+'</span>';
 	div=div+'</div>';
 	div=div+'<div style="padding-left:60px;">';
 	div+='<div class="LikeSection"><div class="commentPhotoStyleComment" width="100%"><a class="linkStyleHref" href="#" id="Like_'+key+'" title=""><span class="linkStyleHref" id="span_Like_'+key+'">Like</span> </a>.<a class="linkStyleHref" href="#" > Comment</a> &nbsp;.&nbsp;<span class="commentTimeStyle"> 1 sec ago</span> </div></div>';
 	div+='<div class="borderForLike"><div><table><tr><td><img src="img/icon_like.png" height="20px" /></td><td><span id=\"likeMessage_'+key+'" class="likeTextStyle"></span></td></tr></table></div></div>';
 	div+='<div id="comment_'+key+'" class="CurrentUserCommentSection"></div>';
 	div+='<div class="commentStyle1111">';
 	div+='<div class="commentPhotoStyle" width="10px"><div class=\"photoSpaceStyle\"><img src="'+photoPath+'" width="50px" height="50px"></div></div>';
 	div+='<div class="commentPhotoStyle2"> <input class=\"myCommentStyle\" type=\"text\" size=\"50px\" id=\"txtCommentForPost_'+key+'" /></div>';
 	div+='</div>';
 	//div+='</div>';
 	
 	 $('#groupDiscussion').prepend(div);

 	$(".myCommentStyle").keyup(function(event){
 	    if(event.keyCode == 13){
 	        var keyFull=$(this).attr('id');
 	        var key = keyFull.replace('txtCommentForPost_','');
 	        var ddd=keyFull;
 	        var commentDesc = document.getElementById(ddd).value;
 	    	//var data = document.getElementById("txtNewPost").value;
 	    	var id = document.getElementById("hiddenUserId").value;
 	    	var imagePath = document.getElementById("txtPhotoPath").value;
 	    	if(commentDesc=="" ||commentDesc==null)
 	    		return;
 	    	 $.getJSON("CreateCommentForGroupPost", {'commentedById' :id,'commentDesc':commentDesc,'postId':key,'flow':1}, function(datas) { // Do an AJAX call
 	    		  	var photo = datas.commentUserPhoto;
 	    		  	var userName = datas.commentUserName;
 	    		  	var comment = datas.desc;
 	    		  	var commentPostId = datas.postId;
 	    		  	var key = datas.key;
 	    		  	var str="";
 	    		  	str+='<div  id="deleteCom_'+key+'" class="commentStyle" onmouseover="showCommentDeleteButton(\''+key+'\')\" onmouseout="hideCommentDeleteButton(\''+key+'\')\" >';
 	    			str+='<div class="commentPhotoStyle">';
 	    			str+='<div class="photoSpaceStyle"><img src="'+photo+'" width="50px" '+
 	    			'height="50px">';
 	    			str+='</div></div>';
 	    			
 	    			str+='<div class="commentPhotoStyle1" width="800px"><span width="100%" class="commentedUserNameStyle" >'+userName+ '</span>: <span width="100%" class="commentedDescriptionStyle" > '+comment+'</span><br><span class="commentTimeStyle">1 Minute ago</span> </div>';
 	    			str+='<div><div id="deleteComment_'+key+'" class="deleteCommentStyle"><span class="commentDeleteTextStyle" onclick="deleteComment(\''+key+'\')\" >x</span> </div></div>';
 	    			str+='</div>';
 	    			var dd = '#comment_'+commentPostId;
 	    			var ssss= 'appendKey_'+key;
 	    			
 	    			 $(dd).append(str);
 	    	  });
 	    	 
 	    	 document.getElementById(ddd).value='';
 	    }
 	});
 	
 	$(".linkStyleHref").click(function(){
 		var status=$(this).html();
 		var keyFull=$(this).attr('id');
 		var spanText = ".span_"+keyFull + " span";
 		var currentValue=$('#'+keyFull+ '').html();
 		var key = keyFull.replace("span_Like_","");
 		var userId = document.getElementById("hiddenUserId").value;
 		var data = trim(currentValue);
 		var makeUp='';
 		var popup='';
 		if(data=="Like"){
 			  $.getJSON("CreateLikeForGroupPost", {'userId' :userId,'postId':key}, function(datas) { // Do an AJAX call
 				  	$('#'+keyFull+ '').text("Unlike");
 				  var sss=	'#likeMessage_'+key;
 				 
 				//
 			      var str="<span class=\"myNameStyle\">You";
 			      var count=0;
 			      var i=0;
 			      var countFlag=0;
 			  	 $.each(datas, function(k,item){
 			  		
 			  		var user=item.userName;
 			  		if(userId==item.likedUserId){
 			  			i=i+1;
 			  			
 			  		}else{
 			  			if(count<=1){
 						  if(str=="")
 							  str=user;
 						  else
 							  str=str+ ", <a href=\"#\" class=\"likeNameStyleLink\" >"+user+"</a>";
 			  		}else{
 			  			if(item.userPhotoPath!=null)
 			  				popup+='<div class="blockUpoupStyle"><table><tr><td><img src="'+item.userPhotoPath+'" width="20px" height="20px"><td></td><td>'+item.userName+'</td></tr></table></div>';
 			  			else 
 			  				popup+='<div class="blockUpoupStyle"><table><tr><td><img src="images/person_icon.jpg" width="20px" height="20px"><td></td><td>'+item.userName+'</td></tr></table></div>';
 			  			countFlag=1;
 			  		}
 			  		i=i+1;
 					  count=count+1;
 			  		}
 			  	 });
 			  	 
 			  	 if(countFlag==1){
 			  		 if((i-3)==0){}else{
 			  			popup+='<div class="blockUpoupStyle" align="right"><input type="button" class="buttonStyle" onclick="hideAllLIkes()" value="close"></div>';
 						 makeUp = '<div id="displayLike_'+key+'" class="likesPopup" style="display:none;position:absolute;" >'+popup+'</div>';
 			  		 str=str+ ' and '+(i-3) + '<a id="hrefLink_'+key+'" class="likeNameStyleLink" onclick="popupLikesUser(\''+key+'\')"> other</a> like this.</span>'+makeUp;
 			  		 }
 			  	 }
 				  
 				  
 				
 					  $(sss).html(str);
 				  
 				  
 			  });
 		}else{
 			var id = keyFull.replace('span_Like_','');
 			  $.getJSON("DelelePostLike", {'postId' :id,'userId':userId}, function(datas) { // Do an AJAX call
 				 
 				      var str="";
 				      var count=0;
 				      var i=0;
 				      var countFlag=0;
 				  	 $.each(datas, function(k,item){
 				  		
 				  		var user=item.userName;
 				  		if(count<=2){
 						  if(str=="")
 							  str="<span class=\"myNameStyle\" > <a href=\"#\" class=\"likeNameStyleLink\" >"+user+"</a>";
 						  else
 							  str=str+ ", <a href=\"#\" class=\"likeNameStyleLink\" >"+user+"</a>";
 				  		}else{
 				  			countFlag=1;
 				  			if(item.userPhotoPath!=null)
 				  				popup+='<div class="blockUpoupStyle"><table><tr><td><img src="'+item.userPhotoPath+'" width="20px" height="20px"><td></td><td>'+item.userName+'</td></tr></table></div>';
 				  			else 
 				  				popup+='<div class="blockUpoupStyle"><table><tr><td><img src="images/person_icon.jpg" width="20px" height="20px"><td></td><td>'+item.userName+'</td></tr></table></div>';
 				  			
 				  		}
 				  		i=i+1;
 						  count=count+1;
 						  
 				  	 });
 				  	 
 				  	 if(countFlag==1){
 				  		 if((i-3)==0){}else{
 				  			popup+='<div class="blockUpoupStyle" align="right"><input type="button" class="buttonStyle" onclick="hideAllLIkes()" value="close"></div>';
 							 var makeUp = '<div id="displayLike_'+key+'" class="likesPopup" style="display:none;position:absolute;" >'+popup+'</div>';
 				  		 
 				  			str=str+ ' and '+(i-3) + '<a class="likeNameStyleLink" id="hrefLink_'+key+'" onclick="popupLikesUser(\''+key+'\')">  other</a> like this.</span>'+makeUp;
 					  		 }
 				  	 }
 				  $('#'+keyFull+ '').text("Like");
 				  
 				  var sss=	'#likeMessage_'+key;
 				  
 				 	//
 					  $(sss).html(str);
 				  
 			  });
 		}
 		
 	});
 	
 }