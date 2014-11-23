var protocal='http';

$(document).ready(function(){	

	var obj = document.getElementById("links");
	if(obj!=null){
		var posX = obj.offsetLeft;
		var posY = obj.offsetTop;
		while(obj.offsetParent){
			posX=posX+obj.offsetParent.offsetLeft;
			posY=posY+obj.offsetParent.offsetTop;
			if(obj==document.getElementsByTagName('body')[0]){break;}
			else{obj=obj.offsetParent;}
		}
		posY=posY+20;
		//alert(" here i am " + posX  + " : " + posY);
		/*	$('#showLinks').css('position','absolute');
	$('#showLinks').css('box-shadow','0px 2px 2px 0px black');
	$('#showLinks').css('left',posX-50);
	$('#showLinks').css('top',posY+12);*/

	//	$('#manageLinks').css('position','absolute');
	//	$('#manageLinks').css('box-shadow','0px 2px 2px 0px black');
	////	$('#manageLinks').css('left',posX-450);
	////	$('#manageLinks').css('top',posY+12);
	//	$('#manageLinks').css('width',600);
	//	$('#manageLinks').css('height',500);
	//	$('#manageLinks').css('z-index',500);

		
		var objLike1=document.getElementById("Likedialog");
		var objLike2=document.getElementById("commentdailog");
		if(objLike2!=null)
			objLike2.style.display='none';

		if(objLike1!=null)
			objLike1.style.display='none';

	}

	registerMyChatTextBox();
	$('#addDynamicNotify').slimScroll({
		height: '200px',
		alwaysVisible: false,
		size: '8px',
		color: '#555555'
	});

	$('#addDynamicCommentNotify').slimScroll({
		height: '200px',
		alwaysVisible: false,
		size: '8px',
		color: '#555555'
	});

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
	var feedcontainer=document.getElementById("feedControl");
	var feedurl="http://www.espncricinfo.com/rss/content/story/feeds/586733.xml";
	var feedlimit=6;
	var rssoutput="<h2></h2><ul>";
	function rssfeedsetup(){
		try{
		var feedpointer=new google.feeds.Feed(feedurl);
		feedpointer.setNumEntries(feedlimit);
		feedpointer.load(displayfeed);
		}catch(e){}
	}
	function displayfeed(result){
		if (!result.error){
			var thefeeds=result.feed.entries;
			for (var i=0; i<thefeeds.length; i++)
				rssoutput+="<li style='font-size:13px;'><a href='" + thefeeds[i].link + "'>" + thefeeds[i].title + "</a><br>";
			rssoutput+=  "</li>";
			rssoutput+="</ul>";
			try{
				console.log(thefeeds[i].publishedDate);
			}catch(e){}
			feedcontainer.innerHTML=rssoutput;
		}
		else
			alert("Error fetching feeds!");
	}
	rssfeedsetup();
	//alert(" loading ");
	$(".myCommentStyle").keyup(function(event){
		if(event.keyCode == 13){
			var keyFull=$(this).attr('id');
			var key = keyFull.replace('txtCommentForPost_','');
			var ddd=keyFull;
			var commentDesc = document.getElementById(ddd).value;
			var data = document.getElementById("txtNewPost").value;
			var id = document.getElementById("txtEmpId").value;
			var imagePath = document.getElementById("txtPhotoPath").value;
			if(commentDesc=="" ||commentDesc==null)
				return;

			$.getJSON("CreateCommentForPost", {'commentedById' :id,'commentDesc':commentDesc,'postId':key,'flow':1}, function(datas) { // Do an AJAX call
				var photo = datas.commentUserPhoto;
				var userName = datas.commentUserName;
				var comment = datas.commentDesc;
				var commentDate=datas.commentDate;
				var commentPostId = datas.postId;
				var key = datas.key;
				if(photo==null||photo=='')
					photo='images/person_icon.jpg';
				var str="";
				str+='<div  id="deleteCom_'+key+'" class="commentStyle" onmouseover="showCommentDeleteButton(\''+key+'\')\" onmouseout="hideCommentDeleteButton(\''+key+'\')\" >';
				str+='<div class="commentPhotoStyle">';
				str+='<div class="photoSpaceStyle"><img src="'+photo+'" width="50px" '+
				'height="50px">';
				str+='</div></div>';

				str+='<div class="commentPhotoStyle1" width="800px"><span width="100%" class="commentedUserNameStyle" ><a  onclick="callme(\''+id+'\')"  id="'+key+'_'+id+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+key+'\',\''+id+'\')">'+userName+ '</a></span>: <span width="100%" class="commentedDescriptionStyle" > '+comment+'</span><br><span class="commentTimeStyle">1 Minute ago</span> </div>';
				str+='<div><div id="deleteComment_'+key+'" class="deleteCommentStyle"><span class="commentDeleteTextStyle" onclick="deleteComment(\''+key+'\',\''+commentDate+'\')\" >x</span> </div></div>';
				str+='</div>';
				var dd = '#comment_'+commentPostId;
				var ssss= 'appendKey_'+key;

				$(dd).append(str);
			});

			document.getElementById(ddd).value='';
		}
	});




});


function registerMyChatTextBox(){
	$("#chatId").keyup(function(event){
		if(event.keyCode == 13){
			var commentDesc = document.getElementById("chatId").value;
			var id = document.getElementById("txtEmpId").value;
			var p = document.getElementById("txtPhotoPath").value;
			var name=document.getElementById("txtEmpName").value;
			var basePath =document.getElementById("basePathOfPhoto").value;
			if(commentDesc=="" ||commentDesc==null)
				return;
			p=basePath+p.replace("a_","g_");
			$.getJSON("CreatePublicChat", {'userId' :id,'commentDesc':commentDesc}, function(datas) { // Do an AJAX call
				var str='';
				str+='<div class="borderForChat"><table cellpadding="0" cellspacing="0"><tr><td  style="padding-right:5px;padding-top:3px;vertical-align:top;padding-left:3px"><img src="'+p+'" width="30px" height="30px" ></td><td><table cellpadding="0" cellspacing="0" border="0">'+
				'<tr><td><span class="chatterName" style="vertical-align:middle;" id="'+commentDesc+'_'+id+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+commentDesc+'\',\''+id+'\')">'+name+'</span></td></tr>'+
				'<tr><td style="vertical-align:top;"><p class="chatContent">'+commentDesc+'</p></td></tr></table></td></tr></table>'+
				'</div>';
				$("#bulletinColumn").prepend(str);
			});

			document.getElementById("chatId").value='';
		}
	});

}
function addForumNotificationText(text){
	$("#forumNotificationCount").html(' ('+text+')');
}

function focusCommentBox(key){
	var s='txtCommentForPost_'+key;
	document.getElementById(s).focus();

}
function focusCommentBoxp(key){
	var s='txtCommentForPostp_'+key;
	document.getElementById(s).focus();

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

function deleteComment(key,commentDate){
	var k='deleteCom_'+key;
	$.getJSON("DeletePostComment", {'key' :key,'commentDate':commentDate}, function(datas) { // Do an AJAX call
		if(datas.result=='SUCCESS')
			document.getElementById(k).style.display='none';
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

function deletePost(key,date){

	$.getJSON("DeletePost", {'key' :key,'deleteDate':date}, function(datas) { // Do an AJAX call
		if(datas.result=='SUCCESS')
			document.getElementById(key).style.display='none';
	});
}


function addPost(){
	var data = document.getElementById("txtNewPost").value;
	if(data=="" || data==null || data=='what\'s on your mind?')
		return;
	var id = document.getElementById("txtEmpId").value;
	var targetId= document.getElementById("txtEmpIdTarget").value;
	var imagePath = document.getElementById("txtPhotoPath").value;
	var name= document.getElementById("txtEmpName").value;
	name = name.replace(/"/g, "");
	var flow =document.getElementById("flow").value;
	var qStringIs= 'postDesc='+data +
	'&userId='+id+''; 

	$.getJSON("CreateNewPost", {'postDesc' :data,'userId':id,'targetId':targetId,'privacy':0,'flow':flow}, function(datas) { // Do an AJAX call
		var postedById=datas.postedById;
		var postedDesc1=datas.postedDesc;
		var postedPhotoId= datas.postedPhotoId;
		var postedVideoId =datas.postedVideoId;
		var postedTime = datas.postedTime;
		var postedByPhotoInfo =datas.postedByPhotoInfo;
		var key = datas.key;
		if(imagePath!=null && !(imagePath=='null'))
		{
			imagePath=document.getElementById("basePathOfPhoto").value+imagePath.replace("a_","g_");
		}else{
			imagePath="images/person_icon.jpg";
		}
		createPostDiv(key,postedDesc1,imagePath,postedTime,name,postedById);

	});

	document.getElementById("txtNewPost").value='';

}


function addPost1(){
	var data = document.getElementById("txtNewPost").value;
	if(data=="" || data==null || data=='what\'s on your mind?')
		return;
	var id = document.getElementById("txtEmpId").value;
	var targetId= document.getElementById("txtEmpIdTarget").value;
	var imagePath = document.getElementById("txtPhotoPath").value;
	var name= document.getElementById("txtEmpName").value;

	var qStringIs= 'postDesc='+data +
	'&userId='+id+''; 

	$.getJSON("CreateNewPost", {'postDesc' :data,'userId':id,'targetId':targetId,'privacy':1,'flow':0}, function(datas) { // Do an AJAX call
		var postedById=datas.postedById;
		var postedDesc1=datas.postedDesc;
		var postedPhotoId= datas.postedPhotoId;
		var postedVideoId =datas.postedVideoId;
		var postedTime = datas.postedTime;
		var postedByPhotoInfo =datas.postedByPhotoInfo;
		var key = datas.key;
		imagePath=document.getElementById("basePathOfPhoto").value+imagePath.replace("a_","g_");
		createPostDiv(key,postedDesc1,imagePath,postedTime,name,postedById);

	});

	document.getElementById("txtNewPost").value='';


}


function addPost3(){
	var data = document.getElementById("txtNewPost").value;
	if(data=="" || data==null || data=='what\'s on your mind?')
		return;
	var id = document.getElementById("txtEmpId").value;
	var imagePath = document.getElementById("txtPhotoPath").value;
	var name= document.getElementById("txtEmpName").value;
	var flow= document.getElementById("flow").value;
	var qStringIs= 'postDesc='+data +
	'&userId='+id+''; 

	$.getJSON("CreateNewPost", {'postDesc' :data,'userId':id,'targetId':null,'privacy':0,'flow':flow}, function(datas) { // Do an AJAX call
		var postedById=datas.postedById;
		var postedDesc1=datas.postedDesc;
		var postedPhotoId= datas.postedPhotoId;
		var postedVideoId =datas.postedVideoId;
		var postedTime = datas.postedTime;
		var postedByPhotoInfo =datas.postedByPhotoInfo;
		var key = datas.key;
		imagePath=document.getElementById("basePathOfPhoto").value+imagePath.replace("a_","g_");
		createPostDiv(key,postedDesc1,imagePath,postedTime,name,postedById);

	});

	document.getElementById("txtNewPost").value='';


}

function shareme(){

	alert(" developmenet in progress..");

}
function addPostComment(key){
	var ddd = "txtCommentForPost_ key";
	var commentDesc = document.getElementById(ddd).value;

	var data = document.getElementById("txtNewPost").value;
	var id = document.getElementById("txtEmpId").value;
	var imagePath = document.getElementById("txtPhotoPath").value;
	var flow = document.getElementById("flow").value;
	imagePath=document.getElementById("basePathOfPhoto").value+imagePath.replace("a_","g_");

	$.getJSON("CreateCommentForPost", {'commentedById' :id,'commentDesc':commentDesc,'postId':key,'flow':flow}, function(datas) { // Do an AJAX call
		var photo = datas.commentUserPhoto;
		var userName = datas.commentUserName;
		var comment = datas.commentDesc;
		var commentDate=datas.commentDate;
		var commentPostId = datas.postId;
		var key = datas.key;
		var str="";
		str+='<div  id="deleteCom_'+key+'" class="commentStyle" onmouseover="showCommentDeleteButton(\''+key+'\')\" onmouseout="hideCommentDeleteButton(\''+key+'\')\" >';
		str+='<div class="commentPhotoStyle">';
		str+='<div class="photoSpaceStyle"><img src="'+photo+'" width="50px" '+
		'height="50px">';
		str+='</div></div>';

		str+='<div class="commentPhotoStyle1" width="800px"><span width="100%" class="commentedUserNameStyle" id="'+key+'_'+id+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+key+'\',\''+id+'\')">'+userName+ '</span>: <span width="100%" class="commentedDescriptionStyle" > '+comment+'</span><br><span class="commentTimeStyle">1 Minute ago</span> </div>';
		str+='<div><div id="deleteComment_'+key+'" class="deleteCommentStyle"><span class="commentDeleteTextStyle" onclick="deleteComment(\''+key+'\',\''+commentDate+'\')\" >x</span> </div></div>';
		str+='</div>';
		var dd = '#comment_'+commentPostId;
		var ssss= 'appendKey_'+key;

		$(dd).append(str);
	});

	document.getElementById(ddd).value='';

}

function createPostDiv(key,desc,photoPath,time,name,postedById){
	var object= document.getElementById("insertPost");

	console.log(" name " + name + " desc " + desc);
	var div='<div id="'+key+'" onmouseOut="hideDeleteButton(\''+key+'\')" onmouseOver="showDeleteButton(\''+key+'\')" class="postSection" width="100%">';
	div=div+'<div class="postSectionInside">';
	div+='<div><div id="delete_'+key+'" class="deletePostStyle" onclick="deletePost(\''+key+'\')"> <img  src=\"img/deletePost.png\"/></div>';

	div+='<div class="imageSection"> <img src="'+photoPath+'" width=\'50px\' height=\'50px\'></div>';
	div+='<div class="descSection"> <span id="'+key+'_'+postedById+'" class="postedUserNameStyle" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+key+'\',\''+postedById+'\')"><a class="removeDecoration"  onclick="callme(\''+postedById+'\')" >'+name +'</a></span>:<span "postDescriptionStyle">' + desc+'</span>';
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

	$('#insertPost').prepend(div);
	$(".linkStyleHref").click(function(){
		var status=$(this).html();
		var keyFull=$(this).attr('id');
		var spanText = ".span_ keyFull";
		var currentValue=$('#'+keyFull+ '').html();
		var key = keyFull.replace("span_Like_","");
		var userId = document.getElementById("hiddenCurrentUser").value;
		var flow=document.getElementById("flow").value;
		var data = trim(currentValue);
		if(data=="Like"){
			$.getJSON("CreateLikeForPost", {'userId' :userId,'postId':key,'flow':flow}, function(datas) { // Do an AJAX call
				$('#'+keyFull+ '').text("Unlike");
				var sss=	'#likeMessage_'+key;

				//
				var str="<span class=\"myNameStyle\">You";
				var count=0;
				var i=0;
				var countFlag=0;
				$.each(datas, function(k,item){

					var user=item.userName;
					if(userId==item.likedUserId){i=i+1;}else{
						if(count<=1){
							if(str=="")
								str=user;
							else
								str=str+ ", <a href=\"#\" class=\"likeNameStyleLink\" id=\""+item.key+"_"+item.likedUserId+"\" onmouseout=\"hideUserInfo()\" onmouseover=\"getUserInfoDisplay('"+item.key+"','"+item.likedUserId+"')\">"+user+"</a>";
						}else{
							countFlag=1;
						}
						i=i+1;
						count=count+1;
					}
				});

				if(countFlag==1){
					if((i-3)==0){}else{
						str=str+ " and "+(i-3) + "<a href=\"#\" class=\"likeNameStyleLink\"> other</a> like this.</span>";
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
							str="<span class=\"myNameStyle\" > <a href=\"#\" class=\"likeNameStyleLink\" id=\""+item.key+"_"+item.likedUserId+"\" onmouseout=\"hideUserInfo()\" onmouseover=\"getUserInfoDisplay('"+item.key+"','"+item.likedUserId+"')\">"+user+"</a>";
						else
							str=str+ ", <a href=\"#\" class=\"likeNameStyleLink\" id=\""+item.key+"_"+item.likedUserId+"\" onmouseout=\"hideUserInfo()\" onmouseover=\"getUserInfoDisplay('"+item.key+"','"+item.likedUserId+"')\">"+user+"</a>";
					}else{
						countFlag=1;
					}
					i=i+1;
					count=count+1;

				});

				if(countFlag==1){
					if((i-3)==0){}else{
						str=str+ " and "+(i-3) + "<a href=\"#\" class=\"likeNameStyleLink\">  other</a> like this.</span>";
					}
				}
				$('#'+keyFull+ '').text("Like");

				var sss=	'#likeMessage_'+key;

				//
				$(sss).html(str);

			});
		}

	});



	$(".myCommentStyle").keyup(function(event){
		if(event.keyCode == 13){

			var keyFull=$(this).attr('id');
			var ddd=keyFull;
			var key = keyFull.replace('txtCommentForPost_','');

			var commentDesc = document.getElementById(ddd).value;
			var data = document.getElementById("txtNewPost").value;
			var id = document.getElementById("txtEmpId").value;
			var imagePath = document.getElementById("txtPhotoPath").value;
			var basePath=document.getElementById("basePathOfPhoto").value;
			if(commentDesc=="" ||commentDesc==null)
				return;

			$.getJSON("CreateCommentForPost", {'commentedById' :id,'commentDesc':commentDesc,'postId':key,'flow':1}, function(datas) { // Do an AJAX call
				var photo = basePath+datas.commentUserPhoto;
				var userName = datas.commentUserName;
				var comment = datas.commentDesc;
				var commentDate=datas.commentDate;
				var commentPostId = datas.postId;
				var key = datas.key;
				var str="";
				str+='<div  id="deleteCom_'+key+'" class="commentStyle" onmouseover="showCommentDeleteButton(\''+key+'\')\" onmouseout="hideCommentDeleteButton(\''+key+'\')\" >';
				str+='<div class="commentPhotoStyle">';
				str+='<div class="photoSpaceStyle"><img src="'+photo+'" width="50px" '+
				'height="50px">';
				str+='</div></div>';

				str+='<div class="commentPhotoStyle1" width="800px"><span width="100%" class="commentedUserNameStyle" id="'+key+'_'+id+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+key+'\',\''+id+'\')">'+userName+ '</span>: <span width="100%" class="commentedDescriptionStyle" > '+comment+'</span><br><span class="commentTimeStyle">1 Minute ago</span> </div>';
				str+='<div><div id="deleteComment_'+key+'" class="deleteCommentStyle"><span class="commentDeleteTextStyle" onclick="deleteComment(\''+key+'\',\''+commentDate+'\')\" >x</span> </div></div>';
				str+='</div>';
				var dd = '#comment_'+commentPostId;
				var ssss= 'appendKey_'+key;

				$(dd).append(str);
			});

			document.getElementById(ddd).value='';
		}
	});
}
function addMyRecentlyUploadedPhotoAlbums(datas)
{


	var imagePath = document.getElementById("basePathOfPhoto").value;
	var idPhotoAlbum =datas.idPhotoAlbum;
	var name =datas.name;
	var coverScreenPath= datas.coverScreenPath;
	if(coverScreenPath!=null)
		coverScreenPath=coverScreenPath.replace("a_","g_");
	var CreatedOn =datas.CreatedOn;
	var updatedOn = datas.updatedOn;
	var userName=datas.userName;
	var userId=datas.userId;
	var str="";
	str+='<div  align="left" class="recentlyUploadedAlbums">';
	str+='<table border="0"><tr align="left"><td ><a onclick="photoUploadAID(\''+idPhotoAlbum+'\')" href ="#"><img src="'+imagePath+'/'+coverScreenPath+'" width="30px" height="30px"></a></span></td><td><a style="text-decoration:none;" onclick="photoUploadAID(\''+idPhotoAlbum+'\')" href ="#"><span class="AlbumUserNameStyle"  >'+name+ '</span></a><br> <span style="color:gray;font-size:10px;font-weight:bold;padding-right:5px;">By</span ><a style="text-decoration:none;" onclick="callme(\''+userId+'\')" ><span class="AlbumUserNameStyle" id="'+idPhotoAlbum+'_'+userId+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+idPhotoAlbum+'\',\''+userId+'\')">'+userName+'</span></a></td></tr></table>';
	str+='</div>';
	var dd = '#myDiv';

	$(dd).append(str);

}
function photoUploadAID(aId){
	$.getJSON("Uploader", {'albumId' :aId}, function(datas) { // Do an AJAX call
		window.location.href="photo_all.jsp";
	});
}
function getAllPost(){






}

function showAllComments(id){
	$(id).slideToggle(500);
}
function  registerEvent(){


	$(".hidingCommentPanel").hide();
	//toggle the componenet with class msg_body
//	$(".himeCommentsStyle").click(function()
//	{

//	var keyFull=$(this).attr('id');
//	var pbk = keyFull.replace('linkComment_','#hide_');

//	// jQuery(this).next(".content").slideToggle(500);
//	});




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
			var data = document.getElementById("txtNewPost").value;
			var id = document.getElementById("txtEmpId").value;
			var bPath=document.getElementById("basePathOfPhoto").value;
			var imagePath = document.getElementById("txtPhotoPath").value;
			if(commentDesc=="" ||commentDesc==null)
				return;

			$.getJSON("CreateCommentForPost", {'commentedById' :id,'commentDesc':commentDesc,'postId':key,'flow':1}, function(datas) { // Do an AJAX call
				var photo = bPath+datas.commentUserPhoto;
				var userName = datas.commentUserName;
				var comment = datas.commentDesc;
				var commentDate=datas.commentDate;
				var commentPostId = datas.postId;
				var key = datas.key;
				var str="";
				str+='<div  id="deleteCom_'+key+'" class="commentStyle" onmouseover="showCommentDeleteButton(\''+key+'\')\" onmouseout="hideCommentDeleteButton(\''+key+'\')\" >';
				str+='<div class="commentPhotoStyle">';
				str+='<div class="photoSpaceStyle"><img src="'+imagePath+'" width="50px" '+
				'height="50px">';
				str+='</div></div>';

				str+='<div class="commentPhotoStyle1" width="800px"><span width="100%" class="commentedUserNameStyle" id="'+key+'_'+id+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+key+'\',\''+id+'\')">'+userName+ '</span>: <span width="100%" class="commentedDescriptionStyle" > '+comment+'</span><br><span class="commentTimeStyle">1 Minute ago</span> </div>';
				str+='<div><div id="deleteComment_'+key+'" class="deleteCommentStyle"><span class="commentDeleteTextStyle" onclick="deleteComment(\''+key+'\',\''+commentDate+'\')\" >x</span> </div></div>';
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
		var userId = document.getElementById("hiddenCurrentUser").value;
		var flow=document.getElementById("flow").value;
		var data = trim(currentValue);
		var makeUp='';
		var popup='';
		if(data=="Like"){
			$.getJSON("CreateLikeForPost", {'userId' :userId,'postId':key,'flow':flow}, function(datas) { // Do an AJAX call
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


function popupLikesUser(key){
	hideAllLIkes();
	var k = '#displayLike_'+key;
	var alink='#hrefLink_'+key;
	var alink1='hrefLink_'+key;

	var obj = document.getElementById(alink1);
	var posX = obj.offsetLeft;
	var posY = obj.offsetTop;
	while(obj.offsetParent){
		posX=posX+obj.offsetParent.offsetLeft;
		posY=posY+obj.offsetParent.offsetTop;
		if(obj==document.getElementsByTagName('body')[0]){break;}
		else{obj=obj.offsetParent;}
	}
	$(k).css('box-shadow','0px 2px 2px 0px black');
	$(k).css('position','absolute');
	$(k).css('z-index',20);
	$(k).css('left',posX);

	$(k).css('top',posY);
	$(k).css('display','block');
	//end

}
function addMyPostDynamicallyInUserFeed(item,fffflow){

	var empId= document.getElementById("txtEmpId").value;
	var imagePPPPath = document.getElementById("txtPhotoPath").value;

	var paaaaath = document.getElementById("basePathOfPhoto").value;
	var videoPaths= document.getElementById("videoPath").value;
	var makeUp='';
	var str='';
	var likedUserNames=new Array();
	var likemessage='';
	var likedCount=-1;


	var likeStatus='';	
	var key=item.key;

	var desc=item.postedDesc;
	var postedById= item.postedById;
	var postedPhotoId= item.postedPhotoId;
	var postedVideoId= item.postedVideoId;
	var postedToId= item.postedToId;
	var timeToDelete=item.dateToDelete;
	var postedTimeStr= item.postedTimeStr;
	likeStatus='';
	var popup='';
	console.log(" item.postType " + item.postType);
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
				likedUserNames[likedCount]=new Array();
				likedUserNames[likedCount][0]=userName;
				likedUserNames[likedCount][2]=likedUserId;
				likedUserNames[likedCount][4]=key;
				likedUserNames[likedCount][5]=likedDate;


				if(userPhotoPath!=null)
					likedUserNames[likedCount][1]=userPhotoPath.replace("a_","g_");
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
				likemessage+=', <a  onclick="callme(\''+likedUserNames[y][2]+'\')"  class="likeNameStyleLink">'+likedUserNames[y][0]+'</a>';
			}else{
				popup+='<div class="blockUpoupStyle"><table><tr><td><img src="'+likedUserNames[y][1]+'" width="40px" height="40px"><td></td><td class="alignNameStle"><a onclick="callme(\''+likedUserNames[y][2]+'\')">'+likedUserNames[y][0]+'</a></td></tr></table></div>';
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
					likemessage+='<span class="myNameStyle" > <a onclick="callme(\''+likedUserNames[y][2]+'\')"  class="likeNameStyleLink" id="'+likedUserNames[y][4]+'_'+likedUserNames[y][2]+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+likedUserNames[y][4]+'\',\''+likedUserNames[y][2]+'\')">'+likedUserNames[y][0]+'</a>';
				}else{
					likemessage+=',  <a onclick="callme(\''+likedUserNames[y][2]+'\')"  class="likeNameStyleLink" id="'+likedUserNames[y][4]+'_'+likedUserNames[y][2]+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+likedUserNames[y][4]+'\',\''+likedUserNames[y][2]+'\')">'+likedUserNames[y][0]+'</a>';
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
		str+='<div id="delete_'+key+'" class="deletePostStyle" onclick="deletePost(\''+key+'\',\''+timeToDelete+'\')" ><img  src="img/deletePost.png"/></div>';
	}
	var aaaaa='images/person_icon.jpg';

	if(item.postedByUserInfo!=null && item.postedByUserInfo.imagePath!=null)
		aaaaa=paaaaath+item.postedByUserInfo.imagePath.photoPath.replace("a_","g_");
	str+='<div class="imageSection"> <img src="'+aaaaa+'" width="50px" height="50px"></div> ';
	if(postedById==postedToId)
		str+='<div class="descSection"> <span  class="postedUserNameStyle" id="'+key+'_'+postedById+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+key+'\',\''+postedById+'\')"><a class="linkStyleForPostNames" onclick="callme(\''+postedById+'\')" >'+ item.postedByUserInfo.empName +'</a></span>:<br><span "postDescriptionStyle">';
	else
	{
		if(item.postedToUserInfo!=null)
			str+='<div class="descSection"> <span  class="postedUserNameStyle" id="'+key+'_'+postedById+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+key+'\',\''+postedById+'\')"><a class="linkStyleForPostNames" onclick="callme(\''+postedById+'\')" >'+ item.postedByUserInfo.empName +'</a> &nbsp; <img src="img/arrow.jpg" width="10px" style="line-height:100px;" height="10px"/>&nbsp;<a class="linkStyleForPostNames" onclick="callme(\''+postedToId+'\')" >'+ item.postedToUserInfo.empName +'</a></span>:<br><span "postDescriptionStyle">';
		else
			str+='<div class="descSection"> <span  class="postedUserNameStyle" id="'+key+'_'+postedById+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+key+'\',\''+postedById+'\')"><a class="linkStyleForPostNames" onclick="callme(\''+postedById+'\')" >'+ item.postedByUserInfo.empName +'</a></span>:<br><span "postDescriptionStyle">';
	}
	if(desc==null)
		str+='';
	else{
		if(item.postType==2 || item.postType==3 ){
			str+='<span class="photoAlbumDescInPost">'+ desc+' </span>';
		}else{
			str+=desc;
		}
	}	
	str+='</span>';

	var isPhoto=0;
	console.log(" item.postedByPhotoInfo " + item.postedByPhotoInfo + " : " + item.postType);
	if(item.postType==2 ){

		var isFine=0;
		if(item.photoCount>=4)
			isFine=1;
		var sep=0;
		str+='<br><table><tr>';
		$.each(item.photoList,function(j,p){
			if(j>3){}else{
				if(isFine==1){
					var ss=p.photoPath;
					if(ss!=null)
						ss=ss.replace("/a_","/f_");
					if(sep==2){
						sep=0;
						str+='</tr><tr>';
					}
					if(p.width < 200 && p.height < 200 ){
						str+='<td class="linkOnPhoto"><a  onclick="updateHiddenBox(\''+p.idPhotoAlbum+'\',\''+p.ownerId+'\',\''+p.photoPath+'\',\'1\',\''+p.width+'\',\''+p.height+'\')" > <img class="lazy" src="'+paaaaath+''+ss+'" /></a></td>';
					}else{
						str+='<td class="linkOnPhoto"><a  onclick="updateHiddenBox(\''+p.idPhotoAlbum+'\',\''+p.ownerId+'\',\''+p.photoPath+'\',\'1\',\''+p.width+'\',\''+p.height+'\')" > <img class="lazy" src="'+paaaaath+''+ss+'" width="200px" height="200px" /></a></td>';
					}
					sep+=1;


				}else{
					if(isPhoto==0){
						var ss=p.photoPath;
						if(ss!=null)
							ss=ss.replace("/a_","/f_");
						if(sep==2){
							sep=0;
							str+='</tr><tr>';
						}
						str+='<td ><a  onclick="updateHiddenBox(\''+p.idPhotoAlbum+'\',\''+p.ownerId+'\',\''+p.photoPath+'\',\'1\',\''+p.width+'\',\''+p.height+'\')" > <img  class="lazy" src="'+paaaaath+''+ss+'" /></a></td>';
						sep+=1;
					}
				}
			}
			isPhoto=1;



		});
		str+='</table></div>';
		/*
		 * asif posting on his feed - "postType":1 - normal post on 'what's on your mind'
0 - from my profile feed
2 - upload images
3- share post
4- video

		 */
	}else if(item.postType==4 ){
		console.log(" path ius  " + videoPaths + item.videoInfo.videoPath);
		var isFine=0;
		var sep=0;
		str+='<br><table><tr>';
		str+='<td><div>';



		if (navigator.appVersion.indexOf("Mac")!=-1 ||navigator.appVersion.indexOf("X11")!=-1 ||navigator.appVersion.indexOf("Linux")!=-1){
			str+='<embed type="application/x-vlc-plugin"';

			str+='         pluginspage="http://www.videolan.org" version="VideoLAN.VLCPlugin.2"';

			str+='        id="video1"';

			str+='        autoplay="yes" loop="yes" width="400" height="300"';

			str+='         src="'+videoPaths+item.videoInfo.videoPath+'">';

			str+='</embed>';
		}else{
			str+='<video width="320" height="240" controls true>';
			//str+='  <source src="'+videoPaths+item.videoInfo.videoPath+'" type="video/ogg">';
			str+='  <source src="'+videoPaths+item.videoInfo.videoPath+'" type="video/mp4">';
			//str+='  <source src="C:\Documents and Settings\Administrator\Desktop\20130811_144656.mp4" type="video/mp4">';
			//str+='  <source src="'+videoPaths+item.videoInfo.videoPath+'" type="video/webm">';
			str+='  <object data="'+videoPaths+item.videoInfo.videoPath+'" width="320" height="240">';
			//str+='  <object data="C:\Documents and Settings\Administrator\Desktop\20130811_144656.mp4" width="320" height="240">';
			str+='    <embed width="320" height="240" >';
			str+='  </object>';
			str+='</video>';


		}
		/*str+='<embed src="'+videoPaths+item.videoInfo.videoPath+'" height="200" width="200">';*/

		/* str+='<embed type="application/x-mplayer2" '+
       	'pluginspage="http://www.microsoft.com/Windows/MediaPlayer/"'+ 
       	'name="mediaplayer1" '+
       	'ShowStatusBar="true" '+
       	'EnableContextMenu="false"'+ 
       	'autostart="false" '+
       	'width="420" '+
       	'height="340" '+
       	'loop="false" '+
       	'src="'+videoPaths+item.videoInfo.videoPath+'" />'; */
		str+='</div></td></tr>';
		str+='</table></div>';

	}else if(item.postedByPhotoInfo!=null ){

		console.log(" ------------------------------------------------- ")
		isPhoto=1;
		var ss=item.postedByPhotoInfo.photoPath;

		if(item.postedByPhotoInfo.width> 251){
			if(ss!=null)
				ss=ss.replace("/a_","/c_");
			str+='<br><table><tr><td class="linkOnPhoto"><a  onclick="updateHiddenBox(\''+item.postedByPhotoInfo.idPhotoAlbum+'\',\''+item.postedByPhotoInfo.ownerId+'\',\''+item.postedByPhotoInfo.photoPath+'\',\'1\',\''+item.postedByPhotoInfo.width+'\',\''+item.postedByPhotoInfo.height+'\')" > <img  class="lazy" src="'+paaaaath+''+ss+'" width="300px"/></a></td></tr></table>';

		}else{
			if(ss!=null)
				ss=ss.replace("/a_","/e_");
			str+='<br><table><tr><td class="linkOnPhoto"><a  onclick="updateHiddenBox(\''+item.postedByPhotoInfo.idPhotoAlbum+'\',\''+item.postedByPhotoInfo.ownerId+'\',\''+item.postedByPhotoInfo.photoPath+'\',\'1\',\''+item.postedByPhotoInfo.width+'\',\''+item.postedByPhotoInfo.height+'\')" > <img  class="lazy" src="'+paaaaath+''+ss+'" /></a></td></tr></table>';
		}
		str+='</div>';

	}
	if(isPhoto==0)
		str+='</div>';
	var count =0;
	if(item.comments!=null){
		var count = item.comments.length;
	}
	//	alert("count " + count);
	str=str+'<div style="padding-left:60px;">';
	str+='<div class="LikeSection"><div class="commentPhotoStyleComment" width="100%\><a  class="linkStyleHref" alt="aabcd"  id="Like_'+key+'"><span class="linkStyleHref" id="span_Like_'+key+'">' +likeStatus+'</span> </a>.<a class="linkStyleHref" onclick="focusCommentBox(\''+key+'\')"> Comment</a> &nbsp;.&nbsp;<span class="commentTimeStyle"> '+postedTimeStr+'</span> </div></div>';
	str+='<div class="borderForLike"><div><table><tr><td class=""><img src="img/icon_like.png" height="20px" /></td><td><span id="likeMessage_'+key+'" class="likeTextStyle">'+likemessage+'</span></td></tr></table></div></div>';
	if(count>2){
		str+='<div class="borderForLike1"><div><table><tr><td class="textAlign"><a  id="linkComment_'+key+'" onclick="showAllComments(\'#hide_'+key+'\')" class="himeCommentsStyle"><img src="img/comments.png" height="20px" />&nbsp;&nbsp;View other '+(count-3)+' comments</a></td></tr></table></div></div>';
		showAllComments('#hide_'+key);
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
			var commentDate=comm.timeInStrToDelete;
			var commmentUserId=comm.commmentUserId;
			var commentDesc=comm.commentDesc;
			var commentUserName=comm.commentUserName;
			var commentUserPhoto=comm.commentUserPhoto;
			var timeInStr = comm.timeInStr;
			var commentkey = comm.key;
			console.log(" commentUserPhoto " + commentUserPhoto);
			if(commentUserPhoto!=null ){
				commentUserPhoto= paaaaath+commentUserPhoto.replace("a_","g_");
			}else{
				commentUserPhoto='images/person_icon.jpg';
			}

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

			str+='<div class="commentPhotoStyle1" width="800px"><span width="100%" class="commentedUserNameStyle" id="'+commentkey+'_'+commmentUserId+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+commentkey+'\',\''+commmentUserId+'\')"><a class="linksToOTherPages" onclick="callme(\''+commmentUserId+'\')" > '+commentUserName+'</a></span>: <span width="100%" class="commentedDescriptionStyle" > '+commentDesc+'</span> <br><span class="commentTimeStyle"> ' + timeInStr+'</span></div>';

			if(commmentUserId==empId){
				str+='<div><div id="deleteComment_'+commentkey+'" class="deleteCommentStyle"><span class="commentDeleteTextStyle" onclick="deleteComment(\''+commentkey+'\',\''+commentDate+'\')" >x</span> </div></div>';
			}

			str+="</div>";

		});
	}
	str=str+'</div>';
	str+='<div id="appendKey_'+key+'" class="commentStyle1111">';
	console.log(" imagePPPPath " + imagePPPPath);
	var sss='';
	if(imagePPPPath=='null')
		sss='images/person_icon.jpg';
	else
		sss= paaaaath+imagePPPPath.replace("a_","g_");
	str+='<div class="commentPhotoStyle" width="10px"><div class="photoSpaceStyle"><img src="'+sss+'" width="50px" height="50px"></div></div>';
	str+='<div class="commentPhotoStyle2" width="100%"> <input type="text" class="form-control" placeholder="Comment here..."  id="txtCommentForPost_'+key+'" > </div>';
	str+='</div>';

	str+='</div>';
	str+='</div>';
	str+=makeUp+'</div>';
	var ssof=document.getElementById("lastTime");
	if(ssof!=null)
		document.getElementById("lastTime").value=item.postedTimeStr2;
	if(document.getElementById(item.key)==null)
		$("#insertPost").append(str);

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

	$(".myCommentStyle").keyup(function(event){
		if(event.keyCode == 13){
			var keyFull=$(this).attr('id');
			var key = keyFull.replace('txtCommentForPost_','');
			var ddd=keyFull;
			var commentDesc = document.getElementById(ddd).value;
			var data = document.getElementById("txtNewPost").value;
			var id = document.getElementById("txtEmpId").value;
			var imagePath = document.getElementById("txtPhotoPath").value;
			if(commentDesc=="" ||commentDesc==null)
				return;

			$.getJSON("CreateCommentForPost", {'commentedById' :id,'commentDesc':commentDesc,'postId':key,'flow':1}, function(datas) { // Do an AJAX call
				var photo = datas.commentUserPhoto;
				var userName = datas.commentUserName;
				var comment = datas.commentDesc;
				var commentDate=datas.commentDate;
				var commentPostId = datas.postId;
				var key = datas.key;
				var str="";
				str+='<div  id="deleteCom_'+key+'" class="commentStyle" onmouseover="showCommentDeleteButton(\''+key+'\')\" onmouseout="hideCommentDeleteButton(\''+key+'\')\" >';
				str+='<div class="commentPhotoStyle">';
				str+='<div class="photoSpaceStyle"><img src="'+photo+'" width="50px" '+
				'height="50px">';
				str+='</div></div>';

				str+='<div class="commentPhotoStyle1" width="100%"><span width="100%" class="commentedUserNameStyle" ><a  onclick="callme(\''+id+'\')"  id="'+key+'_'+id+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+key+'\',\''+id+'\')">'+userName+ '</a></span>: <span width="100%" class="commentedDescriptionStyle" > '+comment+'</span><br><span class="commentTimeStyle">1 Minute ago</span> </div>';
				str+='<div><div id="deleteComment_'+key+'" class="deleteCommentStyle"><span class="commentDeleteTextStyle" onclick="deleteComment(\''+key+'\',\''+commentDate+'\')\" >x</span> </div></div>';
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
		var userId = document.getElementById("hiddenCurrentUser").value;
		var flow=document.getElementById("flow").value;
		var data = trim(currentValue);
		var makeUp='';
		var popup='';
		if(data=="Like"){
			$.getJSON("CreateLikeForPost", {'userId' :userId,'postId':key,'flow':flow}, function(datas) { // Do an AJAX call
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
function addMyPostDynamically (item,fffflow){
	var empId= document.getElementById("txtEmpId").value;
	var imagePPPPath = document.getElementById("txtPhotoPath").value;

	var paaaaath = document.getElementById("basePathOfPhoto").value;
	var videoPaths= document.getElementById("videoPath").value;
	var makeUp='';
	var str='';
	var likedUserNames=new Array();
	var likemessage='';
	var likedCount=-1;
	var count =0;

	var likeStatus='';	
	var key=item.key;

	var desc=item.postedDesc;
	var postedById= item.postedById;
	var postedPhotoId= item.postedPhotoId;
	var postedVideoId= item.postedVideoId;
	var postedToId= item.postedToId;
	var timeToDelete=item.dateToDelete;
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
				likedUserNames[likedCount]=new Array();
				likedUserNames[likedCount][0]=userName;
				likedUserNames[likedCount][2]=likedUserId;
				likedUserNames[likedCount][4]=key;
				likedUserNames[likedCount][5]=likedDate;


				if(!(userPhotoPath=='' || userPhotoPath=='null' || userPhotoPath==null))
					likedUserNames[likedCount][1]=userPhotoPath.replace("a_","g_");
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
				likemessage+=', <a  onclick="callme(\''+likedUserNames[y][2]+'\')"  class="likeNameStyleLink">'+likedUserNames[y][0]+'</a>';
			}else{
				popup+='<div class="blockUpoupStyle"><table><tr><td><img src="'+likedUserNames[y][1]+'" width="40px" height="40px"><td></td><td class="alignNameStle"><a onclick="callme(\''+likedUserNames[y][2]+'\')">'+likedUserNames[y][0]+'</a></td></tr></table></div>';
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
					likemessage+='<span class="myNameStyle" > <a onclick="callme(\''+likedUserNames[y][2]+'\')"  class="likeNameStyleLink" id="'+likedUserNames[y][4]+'_'+likedUserNames[y][2]+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+likedUserNames[y][4]+'\',\''+likedUserNames[y][2]+'\')">'+likedUserNames[y][0]+'</a>';
				}else{
					likemessage+=',  <a onclick="callme(\''+likedUserNames[y][2]+'\')"  class="likeNameStyleLink" id="'+likedUserNames[y][4]+'_'+likedUserNames[y][2]+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+likedUserNames[y][4]+'\',\''+likedUserNames[y][2]+'\')">'+likedUserNames[y][0]+'</a>';
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
		str+='<div id="delete_'+key+'" class="deletePostStyle" onclick="deletePost(\''+key+'\',\''+timeToDelete+'\')" ><img  src="img/deletePost.png"/></div>';
	}
	var aaaaa='images/person_icon.jpg';

	if(item.postedByUserInfo!=null && (item.postedByUserInfo.imagePath!=null && !(item.postedByUserInfo.imagePath=='null')))

		aaaaa=paaaaath+item.postedByUserInfo.imagePath.photoPath.replace("a_","g_");
	str+='<div class="imageSection"> <img src="'+aaaaa+'" width="50px" height="50px"></div> ';
	if(postedById==postedToId)
		str+='<div class="descSection"> <span  class="postedUserNameStyle" id="'+key+'_'+postedById+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+key+'\',\''+postedById+'\')"><a class="linkStyleForPostNames" onclick="callme(\''+postedById+'\')" >'+ item.postedByUserInfo.empName +'</a></span>:<br><span "postDescriptionStyle">';
	else {
		if(item.postedToUserInfo!=null)
			str+='<div class="descSection"> <span  class="postedUserNameStyle" id="'+key+'_'+postedById+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+key+'\',\''+postedById+'\')"><a class="linkStyleForPostNames" onclick="callme(\''+postedById+'\')" >'+ item.postedByUserInfo.empName +'</a> &nbsp; <img src="img/arrow.jpg" width="10px" style="line-height:100px;" height="10px"/>&nbsp;<a class="linkStyleForPostNames" onclick="callme(\''+postedToId+'\')" >'+ item.postedToUserInfo.empName +'</a></span>:<br><span "postDescriptionStyle">';
		else
			str+='<div class="descSection"> <span  class="postedUserNameStyle" id="'+key+'_'+postedById+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+key+'\',\''+postedById+'\')"><a class="linkStyleForPostNames" onclick="callme(\''+postedById+'\')" >'+ item.postedByUserInfo.empName +'</a></span>:<br><span "postDescriptionStyle">';
	}
	if(desc==null)
		str+='';
	else{
		if(item.postType==2 || item.postType==3 ){
			str+='<span class="photoAlbumDescInPost">'+ desc+' </span>';
		}else{
			str+=desc;
		}
	}	
	str+='</span>';

	var isPhoto=0;
	console.log(" item.postedByPhotoInfo " + item.postedByPhotoInfo + " : " + item.postType);
	if(item.postType==2 ){

		var isFine=0;
		if(item.photoCount>=4)
			isFine=1;
		var sep=0;
		str+='<br><table><tr>';
		console.log(" item " + item.photoList);
		if(item.photoList!=null){
		$.each(item.photoList,function(j,p){
			if(j>3){}else{
				if(isFine==1){
					var ss=p.photoPath;
					if(ss!=null)
						ss=ss.replace("/a_","/f_");
					if(sep==2){
						sep=0;
						str+='</tr><tr>';
					}
					if(p.width < 200 && p.height < 200 ){
						str+='<td class="linkOnPhoto"><a  onclick="updateHiddenBox(\''+p.idPhotoAlbum+'\',\''+p.ownerId+'\',\''+p.photoPath+'\',\'1\',\''+p.width+'\',\''+p.height+'\')" > <img class="lazy" src="'+paaaaath+''+ss+'" /></a></td>';
					}else{
						str+='<td class="linkOnPhoto"><a  onclick="updateHiddenBox(\''+p.idPhotoAlbum+'\',\''+p.ownerId+'\',\''+p.photoPath+'\',\'1\',\''+p.width+'\',\''+p.height+'\')" > <img class="lazy" src="'+paaaaath+''+ss+'" width="200px" height="200px" /></a></td>';
					}
					sep+=1;


				}else{
					if(isPhoto==0){
						var ss=p.photoPath;
						if(ss!=null)
							ss=ss.replace("/a_","/f_");
						if(sep==2){
							sep=0;
							str+='</tr><tr>';
						}
						str+='<td ><a  onclick="updateHiddenBox(\''+p.idPhotoAlbum+'\',\''+p.ownerId+'\',\''+p.photoPath+'\',\'1\',\''+p.width+'\',\''+p.height+'\')" > <img  class="lazy" src="'+paaaaath+''+ss+'" /></a></td>';
						sep+=1;
					}
				}
			}
			isPhoto=1;



		});
	}
		str+='</table></div>';
		/*
		 * asif posting on his feed - "postType":1 - normal post on 'what's on your mind'
0 - from my profile feed
2 - upload images
3- share post
4- video

		 */
	}else if(item.postType==4 ){
		console.log(" path ius  " + videoPaths + item.videoInfo.videoPath);
		var isFine=0;
		var sep=0;
		str+='<br><table><tr>';
		str+='<td><div>';



		if (navigator.appVersion.indexOf("Mac")!=-1 ||navigator.appVersion.indexOf("X11")!=-1 ||navigator.appVersion.indexOf("Linux")!=-1){
			str+='<embed type="application/x-vlc-plugin"';

			str+='         pluginspage="http://www.videolan.org" version="VideoLAN.VLCPlugin.2"';

			str+='        id="video1"';

			str+='        autoplay="yes" loop="yes" width="400" height="300"';

			str+='         src="'+videoPaths+item.videoInfo.videoPath+'">';

			str+='</embed>';
		}else{
			str+='<video width="320" height="240" controls true>';
			//str+='  <source src="'+videoPaths+item.videoInfo.videoPath+'" type="video/ogg">';
			str+='  <source src="'+videoPaths+item.videoInfo.videoPath+'" type="video/mp4">';
			//str+='  <source src="C:\Documents and Settings\Administrator\Desktop\20130811_144656.mp4" type="video/mp4">';
			//str+='  <source src="'+videoPaths+item.videoInfo.videoPath+'" type="video/webm">';
			str+='  <object data="'+videoPaths+item.videoInfo.videoPath+'" width="320" height="240">';
			//str+='  <object data="C:\Documents and Settings\Administrator\Desktop\20130811_144656.mp4" width="320" height="240">';
			str+='    <embed width="320" height="240" >';
			str+='  </object>';
			str+='</video>';


		}
		/*str+='<embed src="'+videoPaths+item.videoInfo.videoPath+'" height="200" width="200">';*/

		/* str+='<embed type="application/x-mplayer2" '+
        	'pluginspage="http://www.microsoft.com/Windows/MediaPlayer/"'+ 
        	'name="mediaplayer1" '+
        	'ShowStatusBar="true" '+
        	'EnableContextMenu="false"'+ 
        	'autostart="false" '+
        	'width="420" '+
        	'height="340" '+
        	'loop="false" '+
        	'src="'+videoPaths+item.videoInfo.videoPath+'" />'; */
		str+='</div></td></tr>';
		str+='</table></div>';

	}else if(item.postedByPhotoInfo!=null ){

		console.log(" ------------------------------------------------- ")
		isPhoto=1;
		var ss=item.postedByPhotoInfo.photoPath;

		if(item.postedByPhotoInfo.width> 251){
			if(ss!=null)
				ss=ss.replace("/a_","/c_");
			str+='<br><table><tr><td class="linkOnPhoto"><a  onclick="updateHiddenBox(\''+item.postedByPhotoInfo.idPhotoAlbum+'\',\''+item.postedByPhotoInfo.ownerId+'\',\''+item.postedByPhotoInfo.photoPath+'\',\'1\',\''+item.postedByPhotoInfo.width+'\',\''+item.postedByPhotoInfo.height+'\')" > <img  class="lazy" src="'+paaaaath+''+ss+'" width="300px"/></a></td></tr></table>';

		}else{
			if(ss!=null)
				ss=ss.replace("/a_","/e_");
			str+='<br><table><tr><td class="linkOnPhoto"><a  onclick="updateHiddenBox(\''+item.postedByPhotoInfo.idPhotoAlbum+'\',\''+item.postedByPhotoInfo.ownerId+'\',\''+item.postedByPhotoInfo.photoPath+'\',\'1\',\''+item.postedByPhotoInfo.width+'\',\''+item.postedByPhotoInfo.height+'\')" > <img  class="lazy" src="'+paaaaath+''+ss+'" /></a></td></tr></table>';
		}
		str+='</div>';

	}
	if(isPhoto==0)
		str+='</div>';

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
			var commentDate=comm.timeInStrToDelete;
			var commmentUserId=comm.commmentUserId;
			var commentDesc=comm.commentDesc;
			var commentUserName=comm.commentUserName;
			var commentUserPhoto=comm.commentUserPhoto;
			var timeInStr = comm.timeInStr;
			var commentkey = comm.key;
			console.log(" commentUserPhoto " + commentUserPhoto);
			if(commentUserPhoto!=null ){
				commentUserPhoto= paaaaath+commentUserPhoto.replace("a_","g_");
			}else{
				commentUserPhoto='images/person_icon.jpg';
			}

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

			str+='<div class="commentPhotoStyle1" width="800px"><span width="100%" class="commentedUserNameStyle" id="'+commentkey+'_'+commmentUserId+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+commentkey+'\',\''+commmentUserId+'\')"><a class="linksToOTherPages" onclick="callme(\''+commmentUserId+'\')" > '+commentUserName+'</a></span>: <span width="100%" class="commentedDescriptionStyle" > '+commentDesc+'</span> <br><span class="commentTimeStyle"> ' + timeInStr+'</span></div>';

			if(commmentUserId==empId){
				str+='<div><div id="deleteComment_'+commentkey+'" class="deleteCommentStyle"><span class="commentDeleteTextStyle" onclick="deleteComment(\''+commentkey+'\',\''+commentDate+'\')" >x</span> </div></div>';
			}

			str+="</div>";

		});
	}
	str=str+'</div>';
	str+='<div id="appendKey_'+key+'" class="commentStyle1111">';
	console.log(" imagePPPPath " + imagePPPPath);
	var sss='';
	if(imagePPPPath=='null')
		sss='images/person_icon.jpg';
	else
		sss= paaaaath+imagePPPPath.replace("a_","g_");
	str+='<div class="commentPhotoStyle" width="10px"><div class="photoSpaceStyle"><img src="'+sss+'" width="50px" height="50px"></div></div>';
	str+='<div class="commentPhotoStyle2" width="100%"><input type="text" class="form-control" placeholder="Comment here.." id="txtCommentForPost_'+key+'"> </div>'; //<input class="myCommentStyle"  style="width:300px;height:18px;" type="text" value="Write a comment"  id="txtCommentForPost_'+key+'" />
	str+='</div>';

	str+='</div>';
	str+='</div>';
	str+=makeUp+'</div>';
	var ssof=document.getElementById("lastTime");
	if(ssof!=null)
		document.getElementById("lastTime").value=item.postedTimeStr2;
	if(document.getElementById(item.key)==null){
		if(fffflow==5)
			$("#insertPost").prepend(str);
		else
			$("#insertPost").append(str);
}
	if(count>2)
		showAllComments('#hide_'+key);
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

	$(".form-control").keyup(function(event){
		if(event.keyCode == 13){
			var keyFull=$(this).attr('id');
			var key = keyFull.replace('txtCommentForPost_','');
			var ddd=keyFull;
			var commentDesc = document.getElementById(ddd).value;
			var data = document.getElementById("txtNewPost").value;
			var id = document.getElementById("txtEmpId").value;
			var imagePath = document.getElementById("txtPhotoPath").value;
			if(commentDesc=="" ||commentDesc==null)
				return;

			$.getJSON("CreateCommentForPost", {'commentedById' :id,'commentDesc':commentDesc,'postId':key,'flow':1}, function(datas) { // Do an AJAX call
				var photo = datas.commentUserPhoto;
				var userName = datas.commentUserName;
				var comment = datas.commentDesc;
				var commentDate=datas.commentDate;
				var commentPostId = datas.postId;
				var key = datas.key;
				if(photo==null ||photo=='')
					photo='images/person_icon.jpg'
						var str="";
				if(document.getElementById('deleteCom_'+key)!=null)
					return;
				str+='<div  id="deleteCom_'+key+'" class="commentStyle" onmouseover="showCommentDeleteButton(\''+key+'\')\" onmouseout="hideCommentDeleteButton(\''+key+'\')\" >';
				str+='<div class="commentPhotoStyle">';
				str+='<div class="photoSpaceStyle"><img src="'+photo+'" width="50px" '+
				'height="50px">';
				str+='</div></div>';

				str+='<div class="commentPhotoStyle1" width="100%"><span width="100%" class="commentedUserNameStyle" ><a  onclick="callme(\''+id+'\')"  id="'+key+'_'+id+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+key+'\',\''+id+'\')">'+userName+ '</a></span>: <span width="100%" class="commentedDescriptionStyle" > '+comment+'</span><br><span class="commentTimeStyle">1 Minute ago</span> </div>';
				str+='<div><div id="deleteComment_'+key+'" class="deleteCommentStyle"><span class="commentDeleteTextStyle" onclick="deleteComment(\''+key+'\',\''+commentDate+'\')\" >x</span> </div></div>';
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
		var userId = document.getElementById("hiddenCurrentUser").value;
		var flow=document.getElementById("flow").value;
		var data = trim(currentValue);
		var makeUp='';
		var popup='';
		if(data=="Like"){
			$.getJSON("CreateLikeForPost", {'userId' :userId,'postId':key,'flow':flow}, function(datas) { // Do an AJAX call
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

function showmorepost(){
	var lastTime = document.getElementById("lastTime").value;
	$("#progressMore").css({'display':'block'});
	$.getJSON("GetPost", {'time' :lastTime}, function(datas) { // Do an AJAX call
		var lengh = datas.length;
		$.each(datas ,function(j,d){
			console.log(" j = " + j);
			addMyPostDynamically(d,1);
			if(j==lengh-1){
				$("#progressMore").css({'display':'none'});
				$('.linkStyleHref').unbind('click');
				$('.form-control').unbind('keyup');
				registerEvent();
			}
		});

		if(lengh==0){
			$("#progressMore").css({'display':'none'});
		}
	});
}
function hideUserInfo(key){

	$('#userInfoPopup').css('display','none');
	$('#userArrow').css('display','none');
	$('#manageLinks').css('display','none');
	$('#tickerArrow').css('display','none');
}
function hideAllLIkes(){

	$('.likesPopup').css('display','none');
}

/** For basic information dispay */


function addDynamicBasicInfo(item,followers){

	var empId= document.getElementById("txtEmpId").value;
	var imagePPPPath = document.getElementById("txtPhotoPath").value;
	var paaaaath = document.getElementById("basePathOfPhoto").value;
	var makeUp='';
	var str='';
	var key = item.key;
	var path='images/person_icon.jpg';
	var cover='images/cover.jpg';
	if(item.empInfo!=null){
		var coverH=0;
		var coverW=0;
		var isHReq=false;
		if(item.empInfo.image!=null)
			path=paaaaath+item.empInfo.image.photoPath.replace('a_','e_');
		if(item.coverPage!=null){
			if(item.coverPage.photoPath!=null)
				cover=paaaaath+item.coverPage.photoPath.replace('a_','d_');
			coverH=item.coverPage.height;
			coverW=item.coverPage.width;
			if(coverH>coverW)
				isHReq=true;
		}
		//alert(" cover "+cover);
		var ss='';
		ss='<div width="100%" id="'+item.key+'" class="CoverPage" >';
		ss+='<div >';
		if(isHReq==false){
			ss+='<table width="100%" border="0"  cellpadding="0" cellspacing="0"><tr><td align="center"   class="photoDisplayBasic">';
			ss+='<img src="'+''+cover+'" class="imageWithHeight" width="150px"/>';
		}else{
			ss+='<table width="100%" border="0"  cellpadding="0" cellspacing="0"><tr><td align="center"   class="photoDisplayBasic">';
			ss+='<img src="'+''+cover+'" class="imageWithHeightH" width="150px" />';
		}
		ss+='</td></tr>';
		ss+='<tr><td width="50%">';
		ss+='<table border="0" width="60%" class="bottomTableStyle"><tr><td class="adjustPosition">';
		ss+='<table border="0" width="60%"><tr><td class="imageCorner"><table border="0"><tr><td class="photoWrapper"><img id="mainIMagePath" src="'+path+'"  width="200px"/></td></tr></table></td><td class="adjustOtherInfo" width="50%" align="left"><table border="0" width="300px"><tr><td><span class="empNameDIvStyle"> ' + item.empInfo.empName + ' </span></td>';
		var followed=false;
		var followedKey="";
		if(!(empId==item.empInfo.id)){
			$.each(item.followers, function( i,item){
				if(item.followBy==empId){
					followed=true;
					followedKey=item.key;
				}
			});

			ss+='<td><input type="button" id="followId" name="followId" class="frindButton" value="Follow" onclick="followUser(\''+empId+'\',\''+item.empInfo.id+'\')"><input type="button" style="" id="unfollowId" name="unfollowId" class="frindButton" value="Unfollow" onclick="unfollowUser(\''+empId+'\',\''+item.empInfo.id+'\',\''+followedKey+'\')"></td>';
			//else
			//	ss+='<td></td>';

		}

		ss+='</tr></table></td></tr></table></td></tr></table>';



		ss+='<table border="0" cellpadding="0" cellspacing="0" class="backgroundColortablge"><tr><td class="sectionONe">';
		ss+='<table cellpadding="0" cellspacing="0">';
		if(item.empInfo.strDepartment!=null)
			str+='<tr><td><img src="images/department3.png" width="15px" height="15px"> <span class="rightHeaderUserINfo">'+ item.empInfo.strDepartment+'</span></td></tr>';
		if(item.empInfo.strDesignation!=null)
			ss+='<tr><td><img src="images/designation.png" width="15px" height="15px"> <span class="rightHeaderUserINfo">'+item.empInfo.strDesignation +'</span></td></tr>';
		if(item.empInfo.emailid!=null)
			ss+='<tr><td><img src="images/emailId.jpg" width="15px" height="15px"><span class="rightHeaderUserINfo">'+item.empInfo.emailid +'</span></td></tr>';
		ss+='</table>';

		ss+='</td><td align="right" style="padding-right:5px;"><table cellpadding="0" cellspacing="0"><tr>';

		$.each(item.photoAlbumns ,function(j,dddd){
			if(j<3){
				var ccc='images/camera.jpg';
				ss+='<td>';
				if(dddd.coverScreenPath!=null)
					ccc=paaaaath+dddd.coverScreenPath.replace('a_','g_');
				ss+='<table  cellpadding="0" cellspacing="0" style="padding-bottom:5px;"><tr><td class="borderGapeForPhotos"><a onclick="photoUploadAID(\''+dddd.idPhotoAlbum+'\')" href="#"><img src="'+ccc+'" ></a></td></tr></table>';
				ss+='</td>';
			}
		});
		ss+='</tr></table></td></tr></table>';
		ss+='<td>';
		ss+='</td></tr></table></table>';
		ss+='';
		//ss+='</td></tr></table>';
		//ss+='</td></tr></table>';
		ss+='</div>';
		ss+='</div>';
		$("#addAllInfo").append(ss);
		var sss='';
		sss='<div><table><tr><td><input type="button" style="width:150px;"  class="bttn" value="Photo" onclick="callPhotoDynamically(\''+empId+'\')"></td><td><input class="bttn" style="width:150px;" type="button" value="Groups" onclick="callGroupDynamically(\''+empId+'\')"></td></tr></table></div>';
		$("#basicInfo").append(sss);
		if(followed==true){
			$("#unfollowId").css('display','block');
			$("#followId").css('display','none');
		}
		else{
			$("#followId").css('display','block');
			$("#unfollowId").css('display','none');
		}
	}


}
function unfollowUser(followBy,followTo,followKey){

	$.getJSON("UnFollowUser", {'followBy' :followBy,'followTo':followTo,'followKey':followKey}, function(datas) { // Do an AJAX call
		if(datas.result=="SUCCESS"){
			$("#unfollowId").css('display','none');
			$("#followId").css('display','block');
		}
	});
}
function followUser(followBy,followTo){

	$.getJSON("FollowUser", {'followBy' :followBy,'followTo':followTo}, function(datas) { // Do an AJAX call
		if(datas.result=="SUCCESS"){
			$("#unfollowId").css('display','block');
			$("#followId").css('display','none');

			$("#unfollowId")[0].onclick = null;
			$("#unfollowId").attr('onclick','unfollowUser(\''+followBy+'\',\''+followTo+'\',\''+datas.key+'\')');
		}
	});
}

function addDynamicBasicInfoOthers(item){

	var empId= document.getElementById("txtEmpId").value;
	var imagePPPPath = document.getElementById("txtPhotoPath").value;
	var paaaaath = document.getElementById("basePathOfPhoto").value;
	var makeUp='';
	var str='';
	var key = item.key;
	var path='images/person_icon.jpg';
	var cover='images/cover.jpg';
	if(item.empInfo!=null){
		var coverH=0;
		var coverW=0;
		var isHReq=false;
		if(item.empInfo.image!=null)
			path=paaaaath+item.empInfo.image.photoPath.replace('a_','e_');
		if(item.coverPage!=null){
			if(item.coverPage.photoPath!=null)
				cover=paaaaath+item.coverPage.photoPath.replace('a_','d_');
			coverH=item.coverPage.height;
			coverW=item.coverPage.width;
			if(coverH>coverW)
				isHReq=true;
		}
		//alert(" cover "+cover);
		var ss='';
		ss='<div width="100%" id="'+item.key+'" class="CoverPage" >';
		ss+='<div >';
		if(isHReq==false){
			ss+='<table width="100%" border="0"  cellpadding="0" cellspacing="0"><tr><td align="center"   class="photoDisplayBasic">';
			ss+='<img src="'+''+cover+'" class="imageWithHeight" width="150px"/>';
		}else{
			ss+='<table width="100%" border="0"  cellpadding="0" cellspacing="0"><tr><td align="center"   class="photoDisplayBasic">';
			ss+='<img src="'+''+cover+'" class="imageWithHeightH" width="150px" />';
		}
		ss+='</td></tr>';
		ss+='<tr><td width="50%">';
		ss+='<table border="0" width="60%" class="bottomTableStyle"><tr><td class="adjustPosition">';
		ss+='<table border="0" width="60%"><tr><td class="imageCorner"><table border="0"><tr><td class="photoWrapper"><img id="mainIMagePath" src="'+path+'"  width="200px"/></td></tr></table></td><td class="adjustOtherInfo" width="50%" align="left"><table border="0" width="300px"><tr><td><span class="empNameDIvStyle"> ' + item.empInfo.empName + ' </span></td>';

		//	ss+='<td><input type="button" class="frindButton" value="Send Friend Request" onclick="createFriendRequest(\''+empId+'\',\''+item.empInfo.id+'\')"></td>';


		ss+='</tr></table></td></tr></table></td></tr></table>';



		ss+='<table border="0" cellpadding="0" cellspacing="0" class="backgroundColortablge"><tr><td class="sectionONe">';
		ss+='<table cellpadding="0" cellspacing="0">';
		if(item.empInfo.strDepartment!=null)
			str+='<tr><td><img src="images/department3.png" width="15px" height="15px"> <span class="rightHeaderUserINfo">'+ item.empInfo.strDepartment+'</span></td></tr>';
		if(item.empInfo.strDesignation!=null)
			ss+='<tr><td><img src="images/designation.png" width="15px" height="15px"> <span class="rightHeaderUserINfo">'+item.empInfo.strDesignation +'</span></td></tr>';
		if(item.empInfo.emailid!=null)
			ss+='<tr><td><img src="images/emailId.jpg" width="15px" height="15px"><span class="rightHeaderUserINfo">'+item.empInfo.emailid +'</span></td></tr>';
		ss+='</table>';

		ss+='</td><td align="right" style="padding-right:5px;"><table cellpadding="0" cellspacing="0"><tr>';

		$.each(item.photoAlbumns ,function(j,dddd){
			if(j<3){
				var ccc='images/camera.jpg';
				ss+='<td>';
				if(dddd.coverScreenPath!=null)
					ccc=paaaaath+dddd.coverScreenPath.replace('a_','g_');
				ss+='<table  cellpadding="0" cellspacing="0" style="padding-bottom:5px;"><tr><td class="borderGapeForPhotos"><a href="#" onclick="photoUploadAID(\''+dddd.idPhotoAlbum+'\')"><img src="'+ccc+'" ></a></td></tr></table>';
				ss+='</td>';
			}
		});
		ss+='</tr></table></td></tr></table>';
		ss+='<td>';
		ss+='</td></tr></table></table>';
		ss+='';
		//ss+='</td></tr></table>';
		//ss+='</td></tr></table>';
		ss+='</div>';
		ss+='</div>';
		$("#addAllInfo").append(ss);
		var sss='';
		var ssss= document.getElementById("txtEmpIdTarget").value;
		sss='<div><table><tr><td><input type="button" style="width:150px;"  class="bttn" value="Photo" onclick="callPhotoDynamically(\''+ssss+'\')"></td><td><input class="bttn" style="width:150px;" type="button" value="Groups" onclick="callGroupDynamically(\''+ssss+'\')"></td></tr></table></div>';
		$("#insertTickers1").append(sss);
	}


}
function callGroupDynamically(userId){
	var str='';
	var basePath=document.getElementById("basePathOfPhoto").value;
	$("#txtNewPost").css('display','none');
	$("#enlargeMe").css('display','none');
	$.getJSON("GroupShow", {'userId':userId}, function(datas) { // Do an AJAX call
		$.each(datas,function(l,li){


			var photo='images/group.png';
			if(li.photoIdInfo!=null)
				photo=basePath+ li.photoIdInfo.photoPath.replace("a_","f_");
			var desc='';
			if(li.description!=null)
				desc=li.description;
			str+='<div>';
			str+='<table>';
			str+='<tr><td><a href="GroupFeeds?groupId='+li.key+'">';
			str+='<img src="'+photo+'" width="200px" height= "200px">';
			str+='</a></td>';
			str+='<td style="vertical-align: top;">';
			str+='<table>';
			str+='<tr><td style="vertical-align: top;">';
			str+='<a href="GroupFeeds?groupId='+li.key+'" class="groupNameUFI">'+li.name+'</a>';
			str+='</td></tr>';
			str+='<tr><td style="vertical-align: top;" class="groupDescUFI">';
			str+=desc;
			str+='</td></tr>';
			str+='<tr><td style="vertical-align: top;" class="groupDescUFI">';
			str+=li.memberCount + ' members';

			str+='</td></tr>';
			str+='<tr><td style="vertical-align: top;" class="groupDateUFI">';
			str+=li.createdOnStr;
			str+='</td></tr>';
			str+='</table>';
			str+='</td>';
			str+='</tr>';
			str+='</table>';
			str+='';
			str+='';
			str+='';
			str+='';
			str+='';
			str+='';
			str+='';
			str+='';
			str+='';
			str+='';

			str+='';
		});
		$("#insertPost").html(str);
	});
}
function callPhotoDynamically(userId){
	var str='';
	var basePath=document.getElementById("basePathOfPhoto").value;
	$("#txtNewPost").css('display','none');
	$("#enlargeMe").css('display','none');
	//$.getJSON("GroupShow", {'userId':userId}, function(datas) { // Do an AJAX call
	$.getJSON("PhotoShow", {'userId':userId}, function(datas) { // Do an AJAX call

		$.each(datas,function(l,li){
			var photo=li.coverScreenPath;
			var desc='';
			if(li.desc!=null)
				desc=li.desc;
			if(photo!=null)
				photo=basePath+photo.replace("a_","f_");
			else
				photo='images/camera.jpg';
			str+='<div>';
			str+='<table>';
			str+='<tr><td><a  onclick="photoUploadAID(\''+li.idPhotoAlbum+'\')" href="#">';
			str+='<img src="'+photo+'" width="200px" height= "200px">';
			str+='</a></td>';
			str+='<td style="vertical-align: top;">';
			str+='<table>';
			str+='<tr><td style="vertical-align: top;">';
			str+='<a href="#" onclick="photoUploadAID(\''+li.idPhotoAlbum+'\')" class="groupNameUFI">'+li.name+'</a>';
			str+='</td></tr>';
			str+='<tr><td style="vertical-align: top;"  class="groupDescUFI">';
			str+=desc;
			str+='</td></tr>';
			str+='<tr><td style="vertical-align: top;"  class="groupDescUFI">';
			str+=li.count + ' Photos';

			str+='</td></tr>';
			str+='<tr><td style="vertical-align: top;" class="groupDateUFI">';
			str+=li.strCreatedOn;
			str+='</td></tr>';
			str+='</table>';
			str+='</td>';
			str+='</tr>';
			str+='</table>';
			str+='';
			str+='';
			str+='';
			str+='';
			str+='';
			str+='';
			str+='';
			str+='';
			str+='';
			str+='';

			str+='';

		});
		$("#insertPost").html(str);
	});
}
function createFriendRequest(from, to){

	$.getJSON("CreateFriendRequest", {'fromId' :from,'toId':to}, function(datas) { // Do an AJAX call

	});
}
function addPhotoShowForUser(text){
	$("#insertTickers1").append(text);
}
function slideToPost(key){
	var obj=document.getElementById(key);
	if(obj!=null){
		var kk = '#'+key;
		$("html, body").animate({scrollTop: $(kk).offset().top},600);
	}
}

function addLikeNotificationDynamically(item){


	var str='';
	//str+='<div id="likeNotificationsStart" align="left">';
	if(item.likeId=='tag'){
		clearAllLikeNotificationForId(item.key);
		str+='<div id="'+item.key+'" class="likeNoificationBlock" onclick="updateHiddenBox(\''+item.photoInfo.idPhotoAlbum+'\',\''+item.photoInfo.ownerId+'\',\''+item.photoInfo.photoPath+'\',\'1\',\''+item.photoInfo.width+'\',\''+item.photoInfo.height+'\')" style="background-color: white;"  align="left">';
		///updateHiddenBox('a6f7e346-660e-43f7-a78c-c21644f05606','04fc42f2-18c9-48dc-a16d-3602df78db66','04fc42f2-18c9-48dc-a16d-3602df78db66/a6f7e346-660e-43f7-a78c-c21644f05606/a_20171345061402916554114.jpg','1','666','500')
		//<a  onclick="updateHiddenBox(\''+p.idPhotoAlbum+'\',\''+p.ownerId+'\',\''+p.photoPath+'\',\'1\',\''+p.width+'\',\''+p.height+'\')
	}else{
		str+='<div id="'+item.key+'" class="likeNoificationBlock" onclick="showMyPostDynamicallyLike(\''+item.postId.key+'\',\''+item.key+'\')" style="background-color: white;"  align="left">';
	}
	str+='<div style="background-color: white;">';
	//str+'<div >';
	var path111=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
	var p=null;
	if(item.postLikedByUserId.imagePath!=null)
		p=path111+item.postLikedByUserId.imagePath.replace("a_","g_");
	if(p==null)
		p='images/person_icon.jpg';
	var des="";
	if(item.likeId=='tag'){
		str+='<table class="mynotifStyle" align="left"><tr><td><img src="'+p+'" width="30px" height="30px"></td>';
	}else{
		if(item.postId.postedDesc!=null)
			des=item.postId.postedDesc;

		if(des.length>70)
			des=des.substring(0,70);
		des+='....';

		var ph='';
		if(item.postId.postedToId!=null)
			//ph='Photo';
			if(item.status==1){
				str+='<table class="mynotifStyle" align="left"><tr><td><img src="'+p+'" width="30px" height="30px"></td>';
			}else{
				str+='<table class="mynotifStyle1" align="left"><tr><td><img src="'+p+'" width="30px" height="30px"></td>';
			}
	}


	if(!(item.message==null || item.message=='' || item == 'null')){
		str+='<td>'+item.message+'</td></tr>';
	}else{
		str+='<td><span class="notificationNameStyle"> '+item.postLikedByUserId.empName+'</span><span class="notificationCOntStyle"> likes your wall post '+ph+'</span> :<br><span> '+des+'</span></td></tr>';
	}
	str+='<tr><td colspan="2"><table><tr><td><span class="notifiyTime">'+item.likedTimeStr+'</span></td><td><img src="img/progressbar.gif" width="30px" height="10px" id="'+item.key+'_progress" class="likeHideProgress"></td></tr></table></td><td><span class="clickViewPost" id="'+item.key+'" "></span></td></tr></table>';
	str+='</div>';

	str+='</div>';

	$("#addDynamicNotify").prepend(str);
	var str1='<div><table><tr><td><img src="img/progressbar.gif" width="50px" height="20px" id="likeProgress" class="likeHideProgress"></td><td><span id="getMoreLikesId" class="linkMoreStyle" onclick="getMoreLikes(\''+item.likedTimeStr2+'\')">more</span></td></tr></table></div>';
	$("#showmoreOption").html(str1);


}

/**
 * 
 * 
 * THis method will popup a post informaion 
 * @param key
 */
function showMyPostDynamicallyLike(key1,viewKey){
	
	//var ss='#'+viewKey;
	var gggggg="#"+key1;
	$(window).scrollTop($(gggggg).offset().top);
	return;
	var str='';
	var proKey = '#'+viewKey+'_progress';
	console.log(" key1 " + key1);
	console.log(" viewKey " + viewKey);
	clearAllLikeNotificationForId(viewKey);
	$(proKey).css({'display':'block'});
	var paaaaath = document.getElementById("basePathOfPhoto").value;
	var empId= document.getElementById("txtEmpId").value;
	var currentImagePath=document.getElementById("txtPhotoPath").value;
	$.getJSON("GetPostInfo", {'key' :key1}, function(datas) { // Do an AJAX call

		var postedTimeStr= datas.postedTimeStr;
		var likedUserNames=new Array();
		var likemessage='';
		var likedCount=-1;
		var popup='';
		// get like information first
		if(datas.likes!=null){
			likeStatus='';
			likemessage='';
			$.each(datas.likes,function(l,li){
				var postId= li.postId;
				var likedUserId= li.likedUserId;
				var userPhotoPath= li.userPhotoPath;
				var likedDate= li.likedDate;
				var keyl= li.key;
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

		// displaying proper liked user infomraiton

		if(likeStatus=='Unlike'){
			likemessage='<span class="myNameStyle" > You';
			var i=1;
			var y=0;
			var tempCount=likedCount+1;
			while(y<=likedCount){

				if(i<=2){
					likemessage+=', <a  onclick="callme(\''+likedUserNames[y][2]+'\')"  class="likeNameStyleLink">'+likedUserNames[y][0]+'</a>';
				}else{
					popup+='<div class="blockUpoupStyle"><table><tr><td><img src="'+likedUserNames[y][1]+'" width="40px" height="40px"><td></td><td class="alignNameStle"><a onclick="callme(\''+likedUserNames[y][2]+'\')" >'+likedUserNames[y][0]+'</a></td></tr></table></div>';
				}
				y=y+1;
				i=i+1;
			}
			if(tempCount>2){
				popup+='<div class="blockUpoupStyle" align="right"><input type="button" class="buttonStyle" onclick="hideAllLIkes()" value="close"></div>';
				makeUp = '<div id="displayLike_'+key1+'" class="likesPopup" style="display:none;position:absolute;" >'+popup+'</div>';
				likemessage+= ' and '+(tempCount-2)+ ' <a id="hrefLink_'+key1+'" class="likeNameStyleLink"  onclick="popupLikesUser(\''+key1+'\')">other</a> like this.</span>';
			}
		}else{
			likeStatus='Like';
			var i=0;
			var y=0;
			var tempCount=likedCount;
			while(y<=likedCount){

				if(i<=2){
					if(likemessage==''){
						likemessage+='<span class="myNameStyle" > <a onclick="callme(\''+likedUserNames[y][2]+'\')"   class="likeNameStyleLink">'+likedUserNames[y][0]+'</a>';
					}else{
						likemessage+=',  <a onclick="callme(\''+likedUserNames[y][2]+'\')"   class="likeNameStyleLink">'+likedUserNames[y][0]+'</a>';
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
					makeUp = '<div id="displayLike_'+keyl+'" class="likesPopup" style="display:none;position:absolute;" >'+popup+'</div>';
					likemessage+= ' and '+(tempCount-2)+ ' <a id="hrefLink_'+keyl+'" class="likeNameStyleLink" onclick="popupLikesUser(\''+keyl+'\')">other</a> like this.</span>';
				}

			}
		}
		//end of display proper liked user information

		var count =0;
		if(datas.comments!=null){
			count = datas.comments.length;
		}

		if(datas.postedByUserInfo==null){
			alert(" This post has been removed");
			$(proKey).css({'display':'none'});
		}

		str+='<div id="myPostIdPopUp" class="onTopClass">';
		str+='<div class="popupTableStyle">';
		str+='<div class="popuptableRow">';
		str+='<div class="popuTableCell">';
		//str+='<table width="100%" class="notificationHeader"><tr><td align="left"><span class="notificationHeaderStyle">Like Notification details</span></td><td align="left"></td></tr></table>';
		str+='<table width="100%" class="notificationHeader"><tr><td align="left" width="100%"><span class="notificationHeaderStyle" >Like Notification details</span></td><td align="right" width="50%"><span class="notificationClose" onclick="coloseMeagain()">X</span></td></tr></table>';

		str+='<br><br><table border="0" style=" vertical-align: top;"><tr><td style=" vertical-align: top;">';
		var p='images/person_icon.jpg';
		var path=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
		if(datas.postedByUserInfo.imagePath!=null)
			p=path+datas.postedByUserInfo.imagePath.photoPath.replace("a_","g_");
		//alert(" p " + p);
		str+='<table><tr><td><img src="'+p+'" width="50px" height="50px"></td></tr></table>';
		str+='</td><td>';
		str+='<table><tr><td> <a onclick="callme(\''+datas.postedByUserInfo.id+'\')"  class="likeNameStyleLink5" ><span >'+datas.postedByUserInfo.empName+'</span></a>';
		str+='</td></td></tr><tr><td>';
		if(datas.postedDesc!=null && datas.postedDesc!='null')
			str+='<span>'+datas.postedDesc+'</span>';
		else
			str+='<span></span>';
		str+='</td></tr><tr><td>';
		var isPhoto=0;

		if(datas.postType==1 ){
			var isFine=0;
			var isFine=0;
			if(datas.photoCount>=4)
				isFine=1;
			var sep=0;
			str+='<br><table><tr>';
			if(datas.photoList!=null){
				$.each(datas.photoList,function(j,p){
					if(j>3){}else{
						if(isFine==1){
							var ss=p.photoPath;
							if(ss!=null)
								ss=ss.replace("/a_","/g_");
							if(sep==2){
								sep=0;
								str+='</tr><tr>';
							}
							str+='<td class="linkOnPhoto"><a  onclick="updateHiddenBox(\''+p.idPhotoAlbum+'\',\''+p.ownerId+'\',\''+p.photoPath+'\',\'1\',\''+p.width+'\',\''+p.height+'\')" > <img src="'+paaaaath+''+ss+'" /></a></td>';
							sep+=1;


						}else{
							if(isPhoto==0){
								var ss=p.photoPath;
								if(ss!=null)
									ss=ss.replace("/a_","/e_");
								if(sep==2){
									sep=0;
									str+='</tr><tr>';
								}
								str+='<td class="linkOnPhotoOne"><a  onclick="updateHiddenBox(\''+p.idPhotoAlbum+'\',\''+p.ownerId+'\',\''+p.photoPath+'\',\'1\',\''+p.width+'\',\''+p.height+'\')" > <img src="'+paaaaath+''+ss+'" /></a></td>';
								sep+=1;
							}
						}
					}
					isPhoto=1;



				});
			}
			str+='</table></div>';


		}else if(datas.postedPhotoId!=null){
			str+='<img src="'+paaaaath+datas.postedByPhotoInfo.photoPath.replace("/a_","/e_")+'"/>';
		}

		str+='</td></tr><tr><td>';
		str+='<div class="LikeSection"><div class="commentPhotoStyleComment" width="100%\><a  class="linkStyleHref3" alt="aabcd"  id="Likepop_'+key1+'"><span class="linkStyleHref3" id="span_Likepop_'+key1+'">' +likeStatus+'</span> </a>.<a class="linkStyleHref" onclick="focusCommentBoxp(\''+key1+'\')"> Comment</a> &nbsp;.&nbsp;<span class="commentTimeStyle"> '+postedTimeStr+'</span> </div></div>';
		str+='<div class="borderForLike"><div><table><tr><td class=""><img src="img/icon_like.png" height="20px" /></td><td><span id="likeMessagepop_'+key1+'" class="likeTextStyle">'+likemessage+'</span></td></tr></table></div></div>';
		if(count>2){
			//str+='<div class="borderForLike1"><div><table><tr><td class="textAlign"><a  id="linkCommentpop_'+key1+'" onclick="showAllComments(\'#hide_'+key1+'\')" class="himeCommentsStyle"><img src="img/comments.png" height="20px" />&nbsp;&nbsp;View other '+(count-3)+' comments</a></td></tr></table></div></div>';
		}
		str+='<div id="commentp_'+key1+'" class="CurrentUserCommentSection">';

		if(datas.comments!=null){
			count = datas.comments.length;
			var hideCount=0;
			if(!(count<=2))
				hideCount=count-2;
			var flagHide=0;
			if(hideCount>0){
				flagHide=1;
				str+='<div class="hidingCommentPanel" id="hide_'+key1+'" >';
			}
			$.each(datas.comments,function(j,comm){
				console.log(' comments index ' + j);
				var postId=comm.postId;
				var commentDate=comm.timeInStrToDelete;
				var commmentUserId=comm.commmentUserId;
				var commentDesc=comm.commentDesc;
				var commentUserName=comm.commentUserName;
				var commentUserPhoto=comm.commentUserPhoto;
				var timeInStr = comm.timeInStr;
				var commentkey = comm.key;
				var path=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
				if(commentUserPhoto!=null)
					commentUserPhoto=path+commentUserPhoto.replace("a_","g_");
				if(hideCount>=0){
					hideCount=hideCount-1;
					if(hideCount==0){
						if(flagHide=1){
							str=str+'</div>';
						}
					}
				}
				str+='<div id="deleteCom_'+commentkey+'" class="commentStyle" onmouseover="showCommentDeleteButton(\''+commentkey+'\')" onmouseout="hideCommentDeleteButton(\''+commentkey+'\')" >';


				str+='<div class="commentPhotoStyle" width="100%">';

				str+='<div class="photoSpaceStyle"><img src="'+commentUserPhoto+'" width="50px" height="50px">';
				str+='</div></div>';

				str+='<div class="commentPhotoStyle10" width="100%"><span width="100%" class="commentedUserNameStyle" ><a class="linksToOTherPages" onclick="callme(\''+commmentUserId+'\')" >'+commentUserName+'</a></span>: <span width="100%" class="commentedDescriptionStyle" > '+commentDesc+'</span> <br><span class="commentTimeStyle"> ' + timeInStr+'</span></div>';

				if(commmentUserId==empId){
					str+='<div><div id="deleteComment_'+commentkey+'" class="deleteCommentStyle"><span class="commentDeleteTextStyle" onclick="deleteComment(\''+commentkey+'\',\''+commentDate+'\')" >x</span> </div></div>';
				}
				str+="</div>";


			});
			str+='</div>';
			str+='<div id="appendKey_'+key1+'" class="commentStyle1111">';
			if(currentImagePath!=null)
				currentImagePath= currentImagePath.replace("a_","g_");
			str+='<div class="commentPhotoStyle" width="10px"><div class="photoSpaceStyle"><img src="'+paaaaath+currentImagePath+'" width="50px" height="50px"></div></div>';
			str+='<div class="commentPhotoStyle2" width="100%"> <input class="myCommentStyle2"  style="width:300px;height:18px;" type="text" value="Write a comment"  id="txtCommentForPostp_'+key1+'" /></div>';

		}

		str+='</td></tr></table>';
		str+='</td></tr></table>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='</div>';


		document.getElementById("myPostPopUp").innerHTML=str;

		var obj=document.getElementById(key1);
		if(obj==null)
			obj=document.getElementById("myLNotification");
		// var obj = document.getElementById(keyUser);
		var posX = obj.offsetLeft;
		var posY = obj.offsetTop;
		//$("#popupMainDiv").css('left',offset.left);    
		//$("#popupMainDiv").css('top',offset.top);
		//
		while(obj.offsetParent){
			posX=posX+obj.offsetParent.offsetLeft;
			posY=posY+obj.offsetParent.offsetTop;
			if(obj==document.getElementsByTagName('body')[0]){break;}
			else{obj=obj.offsetParent;}
		}


		//TODO
		$('#myPostPopUp').css('box-shadow','0px 2px 2px 0px black');
		$("#myPostPopUp").css('position','absolute');
		$("#myPostPopUp").css('display','block');
		//$("#myPostPopUp").css('width','400');
		//$("#myPostPopUp").css('height','400');
		$("#myPostPopUp").css('z-index','60');
		$("#myPostPopUp").css('top',posY+28);
		$("#myPostPopUp").css('left',posX+310);
		$("#myPostPopUp").css('background-color','#5E93C4');
		$('#myPostIdPopUp').slimScroll({
			height: '500px',
			alwaysVisible: false,
			size: '5px',
			color: '#555555'
		});

		$(".myCommentStyle2").blur(function() {
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

		$(".linkStyleHref3").click(function(){
			var status=$(this).html();
			var keyFull=$(this).attr('id');
			var spanText = ".spanpop_"+keyFull + " span";
			var currentValue=$('#'+keyFull+ '').html();
			var key = keyFull.replace("span_Likepop_","");
			var keyParent = keyFull.replace("span_Like_","");
			keyParent='span_Like_'+key1;
			var userId = document.getElementById("hiddenCurrentUser").value;
			var flow=document.getElementById("flow").value;
			var data = trim(currentValue);
			if(data=="Like"){
				$.getJSON("CreateLikeForPost", {'userId' :userId,'postId':key,'flow':flow}, function(datas) { // Do an AJAX call
					$('#'+keyFull+ '').text("Unlike");
					$('#'+keyParent+ '').text("Unlike");

					var sss=	'#likeMessage_'+key;
					var ssss= '#likeMessagepop_'+key;

					//
					var str="<span class=\"myNameStyle\">You";
					var count=0;
					var i=0;
					var countFlag=0;
					$.each(datas, function(k,item){

						var user=item.userName;
						if(userId==item.likedUserId){i=i+1;}else{
							if(count<=1){
								if(str=="")
									str=user;
								else
									str=str+ ", <a href=\"#\" class=\"likeNameStyleLink\" >"+user+"</a>";
							}else{
								countFlag=1;
							}
							i=i+1;
							count=count+1;
						}
					});

					if(countFlag==1){
						if((i-3)==0){}else{
							str=str+ " and "+(i-3) + "<a href=\"#\" class=\"likeNameStyleLink\"> other</a> like this.</span>";
						}
					}



					$(sss).html(str);
					$(ssss).html(str);

				});
			}else{
				var id = keyFull.replace('span_Likepop_','');
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
						}
						i=i+1;
						count=count+1;

					});

					if(countFlag==1){
						if((i-3)==0){}else{
							str=str+ " and "+(i-3) + "<a href=\"#\" class=\"likeNameStyleLink\">  other</a> like this.</span>";
						}
					}
					$('#'+keyFull+ '').text("Like");
					$('#'+keyParent+ '').text("Like");
					var sss=	'#likeMessage_'+key;

					//
					$(sss).html(str);
					var ssssss=	'#likeMessagepop_'+key;

					//
					$(ssssss).html(str);

				});
			}

		});

		// comment
		$(".myCommentStyle2").keyup(function(event){
			if(event.keyCode == 13){
				var keyFull=$(this).attr('id');
				var key = keyFull.replace('txtCommentForPostp_','');
				var ddd=keyFull;
				var commentDesc = document.getElementById(ddd).value;
				var data = document.getElementById("txtNewPost").value;
				var id = document.getElementById("txtEmpId").value;
				var imagePath = document.getElementById("txtPhotoPath").value;
				var bPath=document.getElementById("basePathOfPhoto").value;
				if(commentDesc=="" ||commentDesc==null)
					return;

				$.getJSON("CreateCommentForPost", {'commentedById' :id,'commentDesc':commentDesc,'postId':key,'flow':1}, function(datas) { // Do an AJAX call
					var photo = bPath+datas.commentUserPhoto;
					var userName = datas.commentUserName;
					var comment = datas.commentDesc;
					var commentDate = datas.commentDate;
					var commentPostId = datas.postId;
					var key = datas.key;
					var str="";
					str+='<div  id="deleteCom_'+key+'" class="commentStyle" onmouseover="showCommentDeleteButton(\''+key+'\')\" onmouseout="hideCommentDeleteButton(\''+key+'\')\" >';
					str+='<div class="commentPhotoStyle">';
					str+='<div class="photoSpaceStyle"><img src="'+photo+'" width="50px" '+
					'height="50px">';
					str+='</div></div>';

					str+='<div class="commentPhotoStyle1" width="800px"><span width="100%" class="commentedUserNameStyle" ><a onclick="callme(\''+id+'\')">'+userName+ '</a></span>: <span width="100%" class="commentedDescriptionStyle" > '+comment+'</span><br><span class="commentTimeStyle">1 Minute ago</span> </div>';
					str+='<div><div id="deleteComment_'+key+'" class="deleteCommentStyle"><span class="commentDeleteTextStyle" onclick="deleteComment(\''+key+'\',\''+commentDate+'\')\" >x</span> </div></div>';
					str+='</div>';
					var dd = '#comment_'+commentPostId;
					var ssss= 'appendKey_'+key;
					var dds = '#commentp_'+commentPostId;
					$(dd).append(str);
					$(dds).append(str);
				});

				document.getElementById(ddd).value='';
			}
		});

		$(proKey).css({'display':'none'});
	});


}


/**
 * 
 * 
 * THis method will popup a post informaion 
 * @param key
 */
function showMyPostDynamicallyComment(key1,viewKey,currentUserId){

	var ss='#'+viewKey;
	var gggggg="#"+key1;
	$(window).scrollTop($(gggggg).offset().top);
	 //$.scrollTo($(ss), 1000);
	 return;
	
	console.log(" key1 " + key1);
	console.log(" viewKey " + viewKey);
	console.log(" currentUserId " + currentUserId);
	clearAllCommentNotificationForId(viewKey,currentUserId);
	$(ss).css({'background-color':'#343434'});
	var proKey = '#'+viewKey+'_progress';
	$(proKey).css({'display':'block'});
	var str='';
	var paaaaath = document.getElementById("basePathOfPhoto").value;
	var empId= document.getElementById("txtEmpId").value;
	var currentImagePath=document.getElementById("txtPhotoPath").value;
	$.getJSON("GetPostInfo", {'key' :key1}, function(datas) { // Do an AJAX call

		var postedTimeStr= datas.postedTimeStr;
		var likedUserNames=new Array();
		var likemessage='';
		var likedCount=-1;
		var popup='';
		// get like information first
		if(datas.likes!=null){
			likeStatus='';
			likemessage='';
			$.each(datas.likes,function(l,li){
				var postId= li.postId;
				var likedUserId= li.likedUserId;
				var userPhotoPath= li.userPhotoPath;
				var likedDate= li.likedDate;
				var keyl= li.key;
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

		// displaying proper liked user infomraiton

		if(likeStatus=='Unlike'){
			likemessage='<span class="myNameStyle" > You';
			var i=1;
			var y=0;
			var tempCount=likedCount+1;
			while(y<=likedCount){

				if(i<=2){
					likemessage+=', <a  onclick="callme(\''+likedUserNames[y][2]+'\')"   class="likeNameStyleLink">'+likedUserNames[y][0]+'</a>';
				}else{
					popup+='<div class="blockUpoupStyle"><table><tr><td><img src="'+likedUserNames[y][1]+'" width="40px" height="40px"><td></td><td class="alignNameStle"><a  onclick="callme(\''+likedUserNames[y][2]+'\')">'+likedUserNames[y][0]+'</a></td></tr></table></div>';
				}
				y=y+1;
				i=i+1;
			}
			if(tempCount>2){
				popup+='<div class="blockUpoupStyle" align="right"><input type="button" class="buttonStyle" onclick="hideAllLIkes()" value="close"></div>';
				makeUp = '<div id="displayLike_'+key1+'" class="likesPopup" style="display:none;position:absolute;" >'+popup+'</div>';
				likemessage+= ' and '+(tempCount-2)+ ' <a id="hrefLink_'+key1+'" class="likeNameStyleLink"  onclick="popupLikesUser(\''+key1+'\')">other</a> like this.</span>';
			}
		}else{
			likeStatus='Like';
			var i=0;
			var y=0;
			var tempCount=likedCount;
			while(y<=likedCount){

				if(i<=2){
					if(likemessage==''){
						likemessage+='<span class="myNameStyle" > <a  onclick="callme(\''+likedUserNames[y][2]+'\')"  class="likeNameStyleLink">'+likedUserNames[y][0]+'</a>';
					}else{
						likemessage+=',  <a  onclick="callme(\''+likedUserNames[y][2]+'\')"  class="likeNameStyleLink">'+likedUserNames[y][0]+'</a>';
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
					makeUp = '<div id="displayLike_'+key1+'" class="likesPopup" style="display:none;position:absolute;" >'+popup+'</div>';
					likemessage+= ' and '+(tempCount-2)+ ' <a id="hrefLink_'+key1+'" class="likeNameStyleLink" onclick="popupLikesUser(\''+key1+'\')">other</a> like this.</span>';
				}

			}
		}
		//end of display proper liked user information

		var count =0;
		if(datas.comments!=null){
			count = datas.comments.length;
		}

		if(datas.postedByUserInfo==null){
			alert(" This comment has been removed");
			return;
		}

		str+='<div id="myPostIdPopUp" class="onTopClass" >';
		str+='<div class="popupTableStyle" ';
		str+='<div class="popuptableRow" >';
		str+='<div class="popuTableCell">';
		str+='<table width="100%" class="notificationHeader"><tr><td align="left" width="100%"><span class="notificationHeaderStyle" >Comment Notification details</span></td><td align="right" width="50%"><span class="notificationClose" onclick="coloseMeagain()">X</span></td></tr></table>';
		str+='<br><br><table border="0" style=" vertical-align: top;"><tr><td style=" vertical-align: top;">';
		var p='images/person_icon.jpg';
		var path=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
		if(datas.postedByUserInfo.imagePath!=null)
			p=path+datas.postedByUserInfo.imagePath.photoPath.replace("a_","g_");
		str+='<table><tr><td><img src="'+p+'" width="50px" height="50px"></td></tr></table>';
		str+='</td><td>';
		str+='<table width="100%"><tr><td> <a onclick="callme(\''+datas.postedByUserInfo.id+'\')"  class="likeNameStyleLink5" ><span >'+datas.postedByUserInfo.empName+'</span></a>';
		str+='</td></tr><tr><td>';
		if(datas.postedDesc!=null && datas.postedDesc!='null')
			str+='<span>'+datas.postedDesc+'</span>';
		else
			str+='<span></span>';
		str+='</td></tr><tr><td>';

		var isPhoto=0;

		if(datas.postType==1 ){
			var isFine=0;
			if(datas.photoCount>=4)
				isFine=1;
			var sep=0;
			str+='<br><table><tr>';
			if(datas.photoList!=null){
				$.each(datas.photoList,function(j,p){
					if(j>3){}else{
						if(isFine==1){
							var ss=p.photoPath;
							if(ss!=null)
								ss=ss.replace("/a_","/g_");
							if(sep==2){
								sep=0;
								str+='</tr><tr>';
							}
							str+='<td ><a  onclick="updateHiddenBox(\''+p.idPhotoAlbum+'\',\''+p.ownerId+'\',\''+p.photoPath+'\',\'1\',\''+p.width+'\',\''+p.height+'\')" > <img class="linkOnPhoto" src="'+paaaaath+''+ss+'" /></a></td>';
							sep+=1;


						}else{
							if(isPhoto==0){
								var ss=p.photoPath;
								if(ss!=null)
									ss=ss.replace("/a_","/e_");
								if(sep==2){
									sep=0;
									str+='</tr><tr>';
								}
								str+='<td class="linkOnPhotoOne"><a  onclick="updateHiddenBox(\''+p.idPhotoAlbum+'\',\''+p.ownerId+'\',\''+p.photoPath+'\',\'1\',\''+p.width+'\',\''+p.height+'\')" > <img src="'+paaaaath+''+ss+'" /></a></td>';
								sep+=1;
							}
						}
					}
					isPhoto=1;



				});
			}
			str+='</table></div>';


		}else
			if(datas.postedPhotoId!=null){
				str+='<img src="'+paaaaath+datas.postedByPhotoInfo.photoPath.replace("/a_","/e_")+'"/>';
			}

		str+='</td></tr><tr><td>';
		str+='<div class="LikeSection"><div class="commentPhotoStyleComment" width="100%\><a  class="linkStyleHref3" alt="aabcd"  id="Likepop_'+key1+'"><span class="linkStyleHref3" id="span_Likepop_'+key1+'">' +likeStatus+'</span> </a>.<a class="linkStyleHref" onclick="focusCommentBoxp(\''+key1+'\')"> Comment</a> &nbsp;.&nbsp;<span class="commentTimeStyle"> '+postedTimeStr+'</span> </div></div>';
		str+='<div class="borderForLike"><div><table><tr><td class=""><img src="img/icon_like.png" height="20px" /></td><td><span id="likeMessagepop_'+key1+'" class="likeTextStyle">'+likemessage+'</span></td></tr></table></div></div>';
		if(count>2){
			//str+='<div class="borderForLike1"><div><table><tr><td class="textAlign"><a  id="linkCommentpop_'+key1+'" onclick="showAllComments(\'#hide_'+key1+'\')" class="himeCommentsStyle"><img src="img/comments.png" height="20px" />&nbsp;&nbsp;View other '+(count-3)+' comments</a></td></tr></table></div></div>';
		}
		str+='<div id="commentp_'+key1+'" class="CurrentUserCommentSection">';

		if(datas.comments!=null){
			count = datas.comments.length;
			var hideCount=0;
			if(!(count<=2))
				hideCount=count-2;
			var flagHide=0;
			if(hideCount>0){
				flagHide=1;
				str+='<div class="hidingCommentPanel" id="hide_'+key1+'" >';
			}
			$.each(datas.comments,function(j,comm){
				console.log(' comments index ' + j);
				var postId=comm.postId;
				var commentDate=comm.timeInStrToDelete;
				var commmentUserId=comm.commmentUserId;
				var commentDesc=comm.commentDesc;
				var commentUserName=comm.commentUserName;
				var commentUserPhoto=comm.commentUserPhoto;
				var timeInStr = comm.timeInStr;
				var commentkey = comm.key;
				var path=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
				if(commentUserPhoto!=null)
					commentUserPhoto=path+commentUserPhoto.replace("a_","g_");
				if(hideCount>=0){
					hideCount=hideCount-1;
					if(hideCount==0){
						if(flagHide=1){
							str=str+'</div>';
						}
					}
				}
				str+='<div id="deleteCom_'+commentkey+'" class="commentStyle" onmouseover="showCommentDeleteButton(\''+commentkey+'\')" onmouseout="hideCommentDeleteButton(\''+commentkey+'\')" >';


				str+='<div class="commentPhotoStyle" width="100%">';

				str+='<div class="photoSpaceStyle"><img src="'+commentUserPhoto+'" width="50px" height="50px">';
				str+='</div></div>';

				str+='<div class="commentPhotoStyle10" width="100%"><span width="100%" class="commentedUserNameStyle" ><a class="linksToOTherPages" onclick="callme(\''+commmentUserId+'\')">'+commentUserName+'</a></span>: <span width="100%" class="commentedDescriptionStyle" > '+commentDesc+'</span> <br><span class="commentTimeStyle"> ' + timeInStr+'</span></div>';

				if(commmentUserId==empId){
					str+='<div><div id="deleteComment_'+commentkey+'" class="deleteCommentStyle"><span class="commentDeleteTextStyle" onclick="deleteComment(\''+commentkey+'\',\''+commentDate+'\')" >x</span> </div></div>';
				}
				str+="</div>";


			});
			str+='</div>';
			str+='<div id="appendKey_'+key1+'" class="commentStyle1111">';
			if(currentImagePath!=null)
				currentImagePath=currentImagePath.replace("a_","g_");
			str+='<div class="commentPhotoStyle" width="10px"><div class="photoSpaceStyle"><img src="'+paaaaath+currentImagePath+'" width="50px" height="50px"></div></div>';
			str+='<div class="commentPhotoStyle2" width="100%"> <input class="myCommentStyle2"  style="width:300px;height:18px;" type="text" value="Write a comment"  id="txtCommentForPostp_'+key1+'" /></div>';

		}

		str+='</td></tr></table>';
		str+='</td></tr></table>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='</div>';


		document.getElementById("myPostPopUp").innerHTML=str;

		//TODO
		console.log(" key1 " + key1);
		var ssssssssss="comment_"+viewKey;
		console.log(" view id = " +ssssssssss );
		var	obj=document.getElementById(ssssssssss);
		//comment_610c0843-0f87-4d9e-bd9d-96cf1e1446eb
		// var obj = document.getElementById(keyUser);
		var posX = obj.offsetLeft;
		var posY = obj.offsetTop;
		//$("#popupMainDiv").css('left',offset.left);    
		//$("#popupMainDiv").css('top',offset.top);
		//
		while(obj.offsetParent){
			posX=posX+obj.offsetParent.offsetLeft;
			posY=posY+obj.offsetParent.offsetTop;
			if(obj==document.getElementsByTagName('body')[0]){break;}
			else{obj=obj.offsetParent;}
		}

		$("#myPostPopUp").css('position','absolute');
		$("#myPostPopUp").css('display','block');
		$('#myPostPopUp').css('box-shadow','0px 2px 2px 0px black');
		//$("#myPostPopUp").css('width','400');
		//$("#myPostPopUp").css('height','400');
		$("#myPostPopUp").css('z-index','60');
		$("#myPostPopUp").css('top',posY-100);
		$("#myPostPopUp").css('left',posX+280);
		$("#myPostPopUp").css('background-color','#5E93C4');



		$('#myPostIdPopUp').slimScroll({
			height: '500px',
			alwaysVisible: false,
			size: '5px',
			color: '#555555'
		});

		$(".myCommentStyle2").blur(function() {
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

		$(".linkStyleHref3").click(function(){
			var status=$(this).html();
			var keyFull=$(this).attr('id');
			var spanText = ".spanpop_"+keyFull + " span";
			var currentValue=$('#'+keyFull+ '').html();
			var key = keyFull.replace("span_Likepop_","");
			var keyParent = keyFull.replace("span_Like_","");
			keyParent='span_Like_'+key1;
			var userId = document.getElementById("hiddenCurrentUser").value;
			var flow=document.getElementById("flow").value;
			var data = trim(currentValue);
			if(data=="Like"){
				$.getJSON("CreateLikeForPost", {'userId' :userId,'postId':key,'flow':flow}, function(datas) { // Do an AJAX call
					$('#'+keyFull+ '').text("Unlike");
					$('#'+keyParent+ '').text("Unlike");

					var sss=	'#likeMessage_'+key;
					var ssss= '#likeMessagepop_'+key;

					//
					var str="<span class=\"myNameStyle\">You";
					var count=0;
					var i=0;
					var countFlag=0;
					$.each(datas, function(k,item){

						var user=item.userName;
						if(userId==item.likedUserId){i=i+1;}else{
							if(count<=1){
								if(str=="")
									str=user;
								else
									str=str+ ", <a href=\"#\"  class=\"likeNameStyleLink\" >"+user+"</a>";
							}else{
								countFlag=1;
							}
							i=i+1;
							count=count+1;
						}
					});

					if(countFlag==1){
						if((i-3)==0){}else{
							str=str+ " and "+(i-3) + "<a href=\"#\" class=\"likeNameStyleLink\"> other</a> like this.</span>";
						}
					}



					$(sss).html(str);
					$(ssss).html(str);

				});
			}else{
				var id = keyFull.replace('span_Likepop_','');
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
						}
						i=i+1;
						count=count+1;

					});

					if(countFlag==1){
						if((i-3)==0){}else{
							str=str+ " and "+(i-3) + "<a href=\"#\" class=\"likeNameStyleLink\">  other</a> like this.</span>";
						}
					}
					$('#'+keyFull+ '').text("Like");
					$('#'+keyParent+ '').text("Like");
					var sss=	'#likeMessage_'+key;

					//
					$(sss).html(str);
					var ssssss=	'#likeMessagepop_'+key;

					//
					$(ssssss).html(str);

				});
			}

		});

		// comment
		$(".myCommentStyle2").keyup(function(event){
			if(event.keyCode == 13){
				var keyFull=$(this).attr('id');
				var key = keyFull.replace('txtCommentForPostp_','');
				var ddd=keyFull;
				var commentDesc = document.getElementById(ddd).value;
				var data = document.getElementById("txtNewPost").value;
				var id = document.getElementById("txtEmpId").value;
				var imagePath = document.getElementById("txtPhotoPath").value;
				var bPath = document.getElementById("basePathOfPhoto").value;
				if(commentDesc=="" ||commentDesc==null)
					return;

				$.getJSON("CreateCommentForPost", {'commentedById' :id,'commentDesc':commentDesc,'postId':key,'flow':1}, function(datas) { // Do an AJAX call
					var photo = bPath+datas.commentUserPhoto;
					var userName = datas.commentUserName;
					var commentDate=datas.commentDate;
					var comment = datas.commentDesc;
					var commentPostId = datas.postId;
					var key = datas.key;
					var str="";
					str+='<div  id="deleteCom_'+key+'" class="commentStyle" onmouseover="showCommentDeleteButton(\''+key+'\')\" onmouseout="hideCommentDeleteButton(\''+key+'\')\" >';
					str+='<div class="commentPhotoStyle">';
					str+='<div class="photoSpaceStyle"><img src="'+photo+'" width="50px" '+
					'height="50px">';
					str+='</div></div>';

					str+='<div class="commentPhotoStyle1" width="800px" ><span width="100%" id="'+key+'_'+id+'" class="commentedUserNameStyle" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+key+'\',\''+id+'\')" ><a  onclick="callme(\''+id+'\')">'+userName+ '</a></span>: <span width="100%" class="commentedDescriptionStyle" > '+comment+'</span><br><span class="commentTimeStyle">1 Minute ago</span> </div>';
					str+='<div><div id="deleteComment_'+key+'" class="deleteCommentStyle"><span  class="commentDeleteTextStyle" onclick="deleteComment(\''+key+'\',\''+commentDate+'\')\" >x</span> </div></div>';
					str+='</div>';
					var dd = '#comment_'+commentPostId;
					var ssss= 'appendKey_'+key;
					var dds = '#commentp_'+commentPostId;
					$(dd).append(str);
					$(dds).append(str);
				});

				document.getElementById(ddd).value='';
			}
		});
		$(proKey).css({'display':'none'});

	});


}
function coloseMeagain(){

	$("#myPostPopUp").css('display','none');
}

function coloseAllAlret(){
	$("#commentdailog").css('display','none');
	$("#commentArrow").css('display','none');
	$("#likeArrow").css('display','none');
	$("#Likedialog").css('display','none');
	$("#myPostPopUp").css('display','none');
	//$("#LikeInfoNotification").css('display','none');

}
function showLikeNoticiation(iiiid){
	$("#addDynamicNotify").empty();
	closeCNotification();
	var obj=document.getElementById("myLNotification");
	// var obj = document.getElementById(keyUser);
	var posX = obj.offsetLeft;
	var posY = obj.offsetTop;


	//
	while(obj.offsetParent){
		posX=posX+obj.offsetParent.offsetLeft;
		posY=posY+obj.offsetParent.offsetTop;
		if(obj==document.getElementsByTagName('body')[0]){break;}
		else{obj=obj.offsetParent;}
	}

	var currentId=document.getElementById("hiddenCurrentUser").value;

	posY=posY+30;
	//$('#LikeInfoNotification').css('box-shadow','0px 2px 2px 2px black');
	//console.log(" X = " + posX + " y " + posY);
	//$('#LikeInfoNotification').css('position','absolute');
	$('#Likedialog').css('left',posX-710);
	$('#Likedialog').css('width',300);
	$('#Likedialog').css('top',posY-30);
	/*$('#LikeInfoNotification').css('display','block');
	$('#LikeInfoNotification').css('backgroundColor','white');
	$('#LikeInfoNotification').css('z-index',53);

	$('#likeArrow').css('position','absolute');
	$('#likeArrow').css('left',posX-30);
	$('#likeArrow').css('top',posY+13);
	$('#likeArrow').css('display','block');
	$('#likeArrow').css('backgroundColor','gray');
	$('#likeArrow').css('z-index',52);
*/

	$('#myLikeCount').css('display','none');
	$('#addDynamicNotify').slimScroll({
		height: '200px',
		alwaysVisible: false,
		size: '8px',
		color: '#555555'
	});
	$.getJSON("GetLikeNotificationJson", {'empId' :iiiid}, function(datas) { // Do an AJAX call
		$.each(datas,function(j,item){
			addLikeNotificationDynamically(item);
		});

	});

}

function closeCNotification(){
	$('#commentdailog').css('display','none');
}
function closeNotification(){

	$('#Likedialog').css('display','none');
}
function clearAllLikeNotificationForId(id){
	$.getJSON("UpdateLikeNotification", {'key' :id}, function(datas) { // Do an AJAX call

	});

}
function addCommentNotificationDyna(item,iiiid){

	var str='';
	var path=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
	//str+='<div id="likeNotificationsStart" align="left">';
	str+='<div id="comment_'+item.key+'" onclick="showMyPostDynamicallyComment(\''+item.postId.key+'\',\''+item.key+'\',\''+iiiid+'\')"  class="likeNoificationBlock" style="background-color: #FFFFFF;"  align="left">';

	str+'<div class="likeNoificationBlock">';
	var p=null;
	if(item.postCommentedByUserId.imagePath!=null)
		p=path+item.postCommentedByUserId.imagePath.replace("a_","g_");
	if(p==null)
		p='images/person_icon.jpg';
	var pp='';
	if(item.postId.postedPhotoId!=null)
		pp='Photo';
	var va='';
	if(item.postId.postedDesc!=null)
		va=item.postId.postedDesc;
	if(va.length>50)
		va=va.substring(0,50);
	var ddd='';
	if(item.commentId!=null && item.commentId!='')
		ddd=item.commentId.commentDesc;
	var leng=ddd.length;
	if(leng>25)
		ddd=ddd.substring(0,20);
	ddd+='...';
	if(item.status==1){
		str+='<table class="mynotifStyle1"  align="left"><tr><td><img src="'+p+'" width="30px" height="30px"></td>';
	}else{
		str+='<table class="mynotifStyle1"  align="left"><tr><td><img src="'+p+'" width="30px" height="30px"></td>';
	}
	//str+='<table class="mynotifStyle" align="left"><tr><td><img src="'+p+'" width="30px" height="30px"></td>';
	if(item.message==null || item.message=="" || item.message == 'null')
		str+='<td><span class="notificationNameStyle"> '+item.postCommentedByUserId.empName+'</span><span class="notificationCOntStyle"> commented your wall post '+pp+'</span> - <span> '+va+' : <span>'+ddd+' </span></span></td></tr>';
	else
		str+='<td>'+item.message+'</td></tr>';
	str+='<tr><td colspan="2"><table><tr><td><span class="notifiyTime">'+item.commetnTimeStr+'</span></td><td><img src="img/progressbar.gif" width="30px" height="10px" id="'+item.key+'_progress" class="commentHideProgress"></td></tr></table></td><td></td></tr></table>';
	str+='</div>';
	str+='</div>';
	$("#addDynamicCommentNotify").append(str);
	//var str='<div><span class="linkMoreStyle" onclick="getMoreComments(\''+item.commentTimeStr2+'\')">more</span></div>';

	var str1='<div><table><tr><td><img src="img/progressbar.gif" width="50px" height="20px" id="commentProgress" class="commentHideProgress"></td><td><span class="linkMoreStyle" id="getMoreCommentId" onclick="getMoreComments(\''+item.commentTimeStr2+'\')">more</span></td></tr></table></div>';
	$("#showmoreOptionComment").html(str1);
}

function showCommentNoticiation(iiiid){
	closeNotification();

	$("#addDynamicCommentNotify").empty();
	var obj=document.getElementById("myCNotification");
	// var obj = document.getElementById(keyUser);
	var posX = obj.offsetLeft;
	var posY = obj.offsetTop;
//	$("#popupMainDiv").css('left',offset.left);    
//	$("#popupMainDiv").css('top',offset.top);

	while(obj.offsetParent){
		posX=posX+obj.offsetParent.offsetLeft;
		posY=posY+obj.offsetParent.offsetTop;
		if(obj==document.getElementsByTagName('body')[0]){break;}
		else{obj=obj.offsetParent;}
	}

	var currentId=document.getElementById("hiddenCurrentUser").value;

	posY=posY+30;
	//$('#CommentInfoNotification').css('box-shadow','0px 2px 2px 2px black');
	$('#commentdailog').css('left',posX-755);
	$('#commentdailog').css('width',300);
	$('#commentdailog').css('top',posY-30);
	/*$('#CommentInfoNotification').css('display','block');
	$('#CommentInfoNotification').css('position','absolute');*/
	$('#CommentInfoNotification').css('backgroundColor','white');
	$('#CommentInfoNotification').css('z-index',70);

	/*$("#commentArrow").css('position','absolute');
	$("#commentArrow").css('display','block');
	$("#commentArrow").css('z-index','61');
	$("#commentArrow").css('top',posY+15);
	$("#commentArrow").css('left',posX-10);
	$("#commentArrow").css('background-color','gray');
*/
	//$('#myCLikeCount').css('display','none');
	$('#addDynamicCommentNotify').slimScroll({
		height: '200px',
		alwaysVisible: false,
		size: '8px',
		color: '#555555'
	});

	$.getJSON("GetCommentNotificationJson", {'empId' :iiiid}, function(datas) { // Do an AJAX call
		$.each(datas,function(j,item){
			addCommentNotificationDyna(item,iiiid);
		});

	});

}
function clearAllCommentNotificationForId(id,userId){
	$.getJSON("UpdateCommentNotification", {'key' :id,'userId':userId}, function(datas) { // Do an AJAX call

	});

}
function addLikeNofiCount(item){
	var currentId=document.getElementById("hiddenCurrentUser").value;
	if(item==0 ||item=='0'){
		$("#likeNotifyIcon").css('display','block');
		$("#likeNotifyIcon").append('<span class="likeNotifyIconStyleLike" id="myLikeCount" onclick="showLikeNoticiation(\''+currentId+'\')" ></span>' );
		var str1='<div><table><tr><td><img src="img/progressbar.gif" width="50px" height="20px" id="likeProgress" class="likeHideProgress"></td><td><span class="linkMoreStyle" id="getMoreLikesId" onclick="getMoreLikes(\'null\')">more</span></td></tr></table></div>';
		$("#showmoreOption").html(str1);
	}else{
		$("#likeNotifyIcon").append('<span class="likeNotifyIconStyleLike" id="myLikeCount" >'+item+'</span>' ); //onclick="showLikeNoticiation(\''+currentId+'\')" 
	}

}
function addCommentNotiCount(item){
	var currentId=document.getElementById("hiddenCurrentUser").value;
	if(item==0 ||item=='0'){
		$("#commentNotificationIcon").css('display','block');
		document.getElementById("commentNotificationIcon").style.display='block';
		$("#commentNotificationIcon").append('<span class="likeNotifyIconStyleComm" id="myCLikeCount" onclick="showCommentNoticiation(\''+currentId+'\')" ></span>' );
		var str1='<table><tr><td><img src="img/progressbar.gif" width="50px" height="20px" id="commentProgress" class="commentHideProgress"></td><td><span class="linkMoreStyle" id="getMoreCommentId" onclick="getMoreComments(\'null\')">more</span></td></tr></table>';
		$("#showmoreOptionComment").html(str1);
		
	}else{
		$("#commentNotificationIcon").css('display','block');
		$("#commentNotificationIcon").append('<span class="likeNotifyIconStyleComm" id="myCLikeCount" onclick="showCommentNoticiation(\''+currentId+'\')" >'+item+'</span>' );
	}
}
function addMessageCount(coun){
	if(coun==0 || coun=='0' || coun==''){
		$("#messageCount").html("");
	}else
		$("#messageCount").html("("+coun+")");

}
function addFollowCount(count){
	$("#followCount").html(" "+count);
}
function getMoreLikes(time){
	$("#getMoreLikesId").css({'display':'none'});
	$("#likeProgress").css({'display':'block'});
	var tttt='';
	var path=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
	var empId= document.getElementById("txtEmpId").value;
	var str='';
	$.getJSON("GetOldLikesNotificaiton", {'userId' :empId,'time':time}, function(datas) { // Do an AJAX call
		$.each(datas,function(j,item){
			var p=null;
			if(item.postLikedByUserId.imagePath!=null)
				p=path+ item.postLikedByUserId.imagePath.replace("a_","g_")
				if(p==null)
					p='images/person_icon.jpg';
			var des="";
			if(item.postId.postedDesc!=null)
				des=item.postId.postedDesc;
			if(des.length>70)
				des=des.substring(0,70);
			des+='....';
			var ph='';
			if(item.postId.postedPhotoId!=null)
				ph='Photo';

			str='<div class="addNewCmmentDynamPost" id="'+item.key+'" onclick="showMyPostDynamicallyLike(\''+item.postId.key+'\',\''+item.key+'\')">';
			str+='<div class="myNewStyleCommentLikeOk"><table id="'+item.key+'_table" class="mynotifStyle1" align="left"><tr><td><img src="'+p+'" width="30px" height="30px"></td>';

			if(item.postId.postedByUserInfo.id==empId)
				str+='<td><span class="notificationNameStyle"> '+item.postLikedByUserId.empName+'</span><span class="notificationCOntStyle"> likes your wall post '+ph+'</span> :<br><span> '+des+'</span></td></tr>';
			else
				str+='<td><span class="notificationNameStyle"> '+item.postLikedByUserId.empName+'</span><span class="notificationCOntStyle"> likes '+item.postId.postedByUserInfo.empName+' wall post '+ph+'</span> :<br><span> '+des+'</span></td></tr>';


			str+='<tr><td colspan="2"><table><tr><td><span class="notifiyTime">'+item.likedTimeStr+'</span></td><td><img src="img/progressbar.gif" class="hideMeFirst" width="30px" height="10px" id="'+item.key+'_progress" ></td></tr></table></td><td><span class="clickViewPost" id="getMoreLikesId" id="'+item.key+'" ></span></td></tr></table>';
			str+='</div></div>';
			tttt= item.likedTimeStr2;
			if(document.getElementById(item.key)==null)
				$("#addDynamicNotify").append(str);
			var str1='<div><table><tr><td><img src="img/progressbar.gif" width="50px" height="20px" id="likeProgress" class="likeHideProgress"></td><td><span class="linkMoreStyle" id="getMoreLikesId" onclick="getMoreLikes(\''+tttt+'\')">more</span></td></tr></table></div>';
			$("#showmoreOption").html(str1);


		});
		$("#likeProgress").css({'display':'none'});
		$("#getMoreLikesId").css({'display':'block'});
		$('.myNewStyleCommentLikeOk').parent().bind('mouseenter', function () {
			var keyFull=$(this).attr('id');
			var k = '#'+keyFull+'_table';

			$(k).css('z-index', 100);
			$(k).css('background-color', '#E1F0F5');
		});
		$('.myNewStyleCommentLikeOk').parent().bind('mouseleave', function () {
			var keyFull=$(this).attr('id');
			var k = '#'+keyFull+'_table';

			$(k).css('z-index', 100);
			$(k).css('background-color', 'white');
		});

	});

}
function getMoreComments(time){
	$("#getMoreCommentId").css({'display':'none'});
	$("#commentProgress").css({'display':'block'});
	getMoreC(time);

}

function getMoreC(time){

	var tttt='';
	var empId= document.getElementById("txtEmpId").value;
	var str='';
	$.getJSON("GetOldCommentNotificaiton", {'userId' :empId,'time':time}, function(datas) { // Do an AJAX call
		$.each(datas,function(j,item){
			var path111=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
			var p=null;
			if(item.postCommentedByUserId.imagePath!=null)
				p=path111+item.postCommentedByUserId.imagePath.replace("a_","g_");
			if(p==null)
				p='images/person_icon.jpg';
			var des="";
			if(item.postId.postedDesc!=null)
				des=item.postId.postedDesc;
			if(des.length>70){
				des=des.substring(0,70);
				des+='....';
			}

			var ph='';
			if(item.postId.postedPhotoId!=null)
				ph='Photo';
			var commDeee='';
			if(item.commentId.commentDesc!=null){
				if(item.commentId.commentDesc.length>70){
					commDeee=item.commentId.commentDesc.substring(0,70);
				}else{
					commDeee=item.commentId.commentDesc;
				}
			}

			str='<div onmouseover="hightBox(\''+item.key+'\')" class="addNewCmmentDynamPost" id="'+item.key+'" onclick="showMyPostDynamicallyComment(\''+item.postId.key+'\',\''+item.key+'\')">';
			str+='<div class="myNewStyleCommentLikeOkComment" ><table id="'+item.key+'_table" class="mynotifStyle1" align="left"><tr><td><img src="'+p+'" width="30px" height="30px"></td>';

			if(item.message==null || item.message=="" || item.message == 'null'){
				str+='<td><span class="notificationNameStyle"> '+item.postCommentedByUserId.empName+'</span><span class="notificationCOntStyle"> commented on your wall post '+ph+'</span> :<br><span> '+des+' : '+commDeee+'</span></td></tr>';
			}else{
				str+='<td><span class="notificationNameStyle"> '+item.message+'</span></td></tr>';
			}


			str+='<tr><td colspan="2"><table><tr><td><span class="notifiyTime">'+item.commetnTimeStr+'</span></td><td><img src="img/progressbar.gif" class="hideMeFirst" width="30px" height="10px" id="'+item.key+'_progress" ></td></tr></table></td><td><span class="clickViewPost" id="'+item.key+'" ></span></td></tr></table>';
			str+='</div></div>';
			tttt= item.commentTimeStr2;
//			var str1='<div><span class="linkMoreStyle" onclick="getMoreComments(\''+tttt+'\')">more</span></div>';
			var str1='<div><table><tr><td><img src="img/progressbar.gif" width="50px" height="20px" id="commentProgress" class="commentHideProgress"></td><td><span class="linkMoreStyle" id="getMoreCommentId" onclick="getMoreComments(\''+tttt+'\')">more</span></td></tr></table></div>';
			$("#showmoreOptionComment").html(str1);
			$("#addDynamicCommentNotify").append(str);
		});

		$('.myNewStyleCommentLikeOkComment').parent().bind('mouseenter', function () {
			var keyFull=$(this).attr('id');
			var k = '#'+keyFull+'_table';
			$(k).css('z-index', 100);
			$(k).css('background-color', '#E1F0F5');
		});
		$('.myNewStyleCommentLikeOkComment').parent().bind('mouseleave', function () {
			var keyFull=$(this).attr('id');
			var k = '#'+keyFull+'_table';

			$(k).css('z-index', 100);
			$(k).css('background-color', 'white');
		});
		$("#commentProgress").css({'display':'none'});
		$("#getMoreCommentId").css({'display':'block'});
	});


}
function hightBox(id){
	var k = '#'+id;
	$(k).css({'background-color':'#EEEEEE'});
}
function closeMySession(){
	$.getJSON("CloseMySession", {'key' :id}, function(datas) { // Do an AJAX call

	});

}

function addMessageNotification(text){
	if(text!=null && text!='' && text!='0'){
		$("#messageCountId").html(' ('+text+')');
	}
}





function getUserInfoDisplay(key,postedId){

	// alert(" user id "+id);
	var div='';
	$.getJSON("GetUserInfo", {'userId' :postedId}, function(datas) { // Do an AJAX call
		var name=datas.empName;
		var imagePath=datas.imagePath;

		if(imagePath=='' || imagePath==null)
			imagePath='images/person_icon.jpg';
		div='<div class="popupMainDiv2">';
		div=div+'<div class="popupSecondDiv" align="left">';
		div=div+'<table width="100%"><tr><td  valign="top" width="60px"><img src="'+imagePath+'" width="50px" height="50px"/> </td>';
		div=div+'<td valign="top" align="left"><table width="100%" align="left"><tr><td align="left" class="nameInPopupStyle" onclick="callme(\''+postedId+'\')"> '+name+'</td><td align="right" onclick="function(){ $(\'#userInfoPopup\').css(\'display\',\'none\');}"><span onclick="function(){ $(\'#userInfoPopup\').css(\'display\',\'none\');}">X</span></td></tr><tr><td class="otherInPopupStyle">'+datas.department+' | '+datas.designation+' </td></tr></table></td></tr><table></div></div>';

		var keyUser =key + '_'+ postedId;
		var obj = document.getElementById(keyUser);
		var posX = obj.offsetLeft;
		var posY = obj.offsetTop;
		while(obj.offsetParent){
			posX=posX+obj.offsetParent.offsetLeft;
			posY=posY+obj.offsetParent.offsetTop;
			if(obj==document.getElementsByTagName('body')[0]){break;}
			else{obj=obj.offsetParent;}
		}
		posY=posY+20;
		//alert(" here i am " + posX  + " : " + posY);
		$('#userInfoPopup').css('position','absolute');
		$('#userInfoPopup').css('box-shadow','0px 2px 2px 0px black');
		$('#userInfoPopup').css('left',posX-20);
		$('#userInfoPopup').css('top',posY);
		$('#userInfoPopup').css('display','block');
		$('#userInfoPopup').css('z-index','46');

		$('#userArrow').css('position','absolute');
		$('#userArrow').css('left',posX);
		$('#userArrow').css('top',posY-2);
		$('#userArrow').css('display','block');
		$('#userArrow').css('z-index','45');

		var userDiv= document.getElementById('userInfoPopup');
		userDiv.innerHTML=div;
		$("#userInfoPopup").children().bind('mouseleave', function () {
			$('#userInfoPopup').css('display','none');
			$('#userArrow').css('display','none');
			// getTickerDisplay(keyFull);

		});	
	});

}





function hideUserInfo(key){ 

	$('#userInfo').css('display','none'); 
	$('#userInfoPopup').css('display','none'); 
	$('#userArrow').css('display','none');
} 

//SETTING UP OUR POPUP
//0 means disabled; 1 means enabled;
var popupStatus = 0; 

//loading popup with jQuery magic!
function loadPopup(albumId,ownerId,path,width,height){ 
	//loads popup only if it is disabled

	if(popupStatus==0){+
		$("#backgroundPopup").css({
			"opacity": "0.8"

		}); 

	//alert(  here data   + albumId +   :   + ownerId);
	document.getElementById("myIFrame").src="" ; 
	var windowWidth2 = document.documentElement.clientWidth; 
	var windowHeight2 = $(window).height();  
	windowHeight2 = windowHeight2+$(document).height()-$(window).scrollTop() ;   
	var urrl='photoList2.jsp?albumId='+albumId+'&ownerId='+ownerId+'&path='+path+'&windowWidth2='+windowWidth2+'&windowHeight2='+windowHeight2+'&acutalWidth='+width+'&actualHeight='+height; 

	document.getElementById("myIFrame").src=urrl;  

	//document.getElementById( myIFrame ).contentDocument.location.reload(true);
	$("#backgroundPopup").fadeIn("slow"); 
	$("#popupContact").fadeIn("slow"); 
	popupStatus = 1; 
	} 
} 

//disabling popup with jQuery magic!
function disablePopup(){ 
	//disables popup only if it is enabled
	if(popupStatus==1){ 
		$("#backgroundPopup").fadeOut("slow"); 
		$("#popupContact").fadeOut("slow"); 
		popupStatus = 0; 
	} 
} 

//centering popup
function centerPopup(){ 


	var windowWidth = document.documentElement.clientWidth; 
	var windowHeight = document.documentElement.clientHeight;
	var popupHeight = $("#popupContact").height()+20; 
	var popupWidth = $("#popupContact").width(); 
	//centering
	$("#popupContact").css({ 
		"position" : "absolute", 
		" top" : (parseInt(windowHeight)/2)-(parseInt(popupHeight)/2), 
		" left" : windowWidth/2-popupWidth/2 
	}); 
	//only need force for IE6

	$("#backgroundPopup").css({ 
		"height" : windowHeight
	}); 

} 

$(document).ready(function(){ 


	$(".SendStyle").click(function(){ 

	}); 

	$("#popupContactClose").click(function(){ 
		disablePopup(); 

	}); +

	$("#backgroundPopup" ).click(function(){ 
		disablePopup(); 
	}); +
	//Press Escape event!
	$(document).keypress(function(e){ 
		if(e.keyCode==27 && popupStatus==1){ 
			disablePopup(); 
		} 
	}); 

}); 

function updateHiddenBox (albumId,ownerId,path,pcount,width,height) { 
	$.getJSON("IsPhotoExists" , {'albumId' :albumId,'path':path}, function(datass) {  
		if(datass.result=='SUCCESS'){  

			$('#userInfo').css('display','none'); 
			centerPopup(); 
//			load popup
			loadPopup(albumId,ownerId,path,width,height); 

		}else{disablePopup();return;};  
	}); 



} 

function callme(id){
	$.getJSON("SetOtherId", {'id' :id}, function(datas) { // Do an AJAX call
		window.location.href="userfeed.jsp";
	});

}

function showLinks(){
	
	showManageLinks();
}
function showManageLinks(){


	var str='';
	alert("ssssss");
	str+='<div style="background-color: #9ECEDE;z-index:501;">';
	str+='<table  cellpadding="0" cellspacing="0">';
	str+='<tr>';
	str+='<td align="left">';
	/*	str+='	<Table width="100%"  cellpadding="0" cellspacing="0">';
	str+='	<tr>';
	str+='		<td align="left">';
	str+='			<h3 style="padding-left:5px;">Manage Links<h3>';
	str+='		</td>';
	str+='<td align="right" style="color:#153540;font-size:15px;font-family:calibri;padding-right:5px;">';
	str+='<span id="addShow" ><h3>Create</h3></span>';
	str+='</td>';
	str+='</tr>';
	str+='</Table>';*/

	str+='</td>';
	str+='</tr>';
	str+='<tr>';
	str+='<Td>';
	str+='<div id="manageDivLinks" class="mytoggle">';
	str+='<table>';
	str+='<tr>';
	str+='<td class="columnHeaderStyle">';

	str+='Name';
	str+='</td>';
	str+='<td class="columnHeaderStyle">';

	str+='URL';
	str+='</td>';
	str+='<td class="columnHeaderStyle">';
	str+='User Name';
	str+='</td>';
	str+='</tr>';
	str+='<tr>';
	str+='<td class="columnHeaderStyle">';

	str+='<input type="text" name="linkName" id="linkName" >';
	str+='</td>';
	str+='<td class="columnHeaderStyle">';

	str+='<input type="text" name="linkURL" id="linkURL" >';
	str+='</td>';
	str+='<td class="columnHeaderStyle">';
	str+='<input type="text" name="userName" id="userName" >';
	str+='</td>';
	str+='</tr>';
	str+='<tr>';
	str+='<td class="columnHeaderStyle">';
	str+='Password';
	str+='</td>';
	str+='<td class="columnHeaderStyle">';
	str+='Order';
	str+='</td>';
	str+='<td class="columnHeaderStyle">';

	str+='</td>';
	str+='</tr>';
	str+='<tr>';
	str+='<td class="columnHeaderStyle">';
	str+='<input type="password" name="passwordU" id="passwordU" >';
	str+='</td>';
	str+='<td class="columnHeaderStyle">';
	str+='<input type="text" name="orderNum" id="orderNum" >';
	str+='</td>';
	str+='<td class="columnHeaderStyle">';
	str+='<input type="button" value="Add" onclick="addLink()">';
	str+='</td>';
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	str+='<div>';
	str+='<table id="insertLink">';
	str+='<tr >';
	str+='<td class="columnHeaderStyleColumns1">Name</td>';
	str+='<td class="columnHeaderStyleColumns2">URL</td>';
	str+='<td class="columnHeaderStyleColumns1">User Name</td>';
	str+='<td class="columnHeaderStyleColumns3">Order</td>';
	str+='<td class="columnHeaderStyleColumns1"></td>';
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	//str+='<div id="insertLink"></div>';
	str+='</Td>';
	str+='</tr>';
	str+='	</table>';
	str+='</div>';

	//$('#showLinks').empty();
	$('#showLinks').css('display','block');
	//$('#showLinks').css('width','300px');
	alert(" str " + str);
	$('#showLinks').append(str);
	alert(" hi ");
	showLinksData();
	console.log(" t");
	
	
}

//-------------------------------------------------------------------

//function arrived(id,type,text) {
////"{alert('hi'); 
//var newJ= $.parseJSON(text); 
//if(type=='post'){ 
//addMyPostDynamically(newJ,0);} 
//else if(type=='likeNotification'){ 
//addLikeNotificationDynamically(newJ);} 
//else if(type=='commentNotification'){ 
//addCommentNotificationDyna(newJ);} 
//else if(type=='likeNotificationCount'){ 
//addLikeNofiCount(text);} 
//else if(type=='CommentNotificationCount'){ 
//addCommentNotiCount(text);} 
//else if(type=='messageNofitication'){ 
//addMessageNotification(text);} 
//else if(type=='forumNotification'){ 
//addForumNotificationText(text);} 
//else if(type=='ticker'){ 
//addMyTickerDynamically(newJ);} 
//else if(type=='birth'){ 
//addMyDynamicBirthEvents(newJ);} 
//else if(type=='public'){ 
//addPublicChats(newJ);} 
//else if(type=='album'){ 
//addMyRecentlyUploadedPhotoAlbums(newJ);} 
//}



//function load() { 
//var feed ="http://www.vcricket.com/rss/Bangalore/index.xml"; 
//new GFdynamicFeedControl(feed, "feedControl"); 

//} 
//google.load(\"feeds\", \"1\"); 
//google.setOnLoadCallback(load); 
//function registerMyEvents(){registerEvents();}
//writer.write("<script type=\"text/javascript\">function arrivedTicker(id,text) {alert('hi');var newT= $.parseJSON(text); addMyTickerDynamically( albumPath+",newT);var b=document.getElementById(id); }</script>");

function insertNOtifyLike(likes){
	var id='#likeMessage_'+likes.postId;
	var m = $(id);

    // set text before displaying message
    console.log(" like names " +   m.children("span").text());
}
function insertNotifyComment(comm){
	 if(document.getElementById(comm.postId)!=null){
		 if(document.getElementById("comment_"+comm.postId)!=null){

			 if(document.getElementById('deleteCom_'+comm.postId)==null){
				var postId=comm.postId;
				var commentDate=comm.timeInStrToDelete;
				var commmentUserId=comm.commmentUserId;
				var commentDesc=comm.commentDesc;
				var commentUserName=comm.commentUserName;
				var commentUserPhoto=comm.commentUserPhoto;
				var timeInStr = comm.timeInStr;
				var commentkey = comm.key;
				console.log(" commentUserPhoto " + commentUserPhoto);
				if(commentUserPhoto!=null ){
					commentUserPhoto= paaaaath+commentUserPhoto.replace("a_","g_");
				}else{
					commentUserPhoto='images/person_icon.jpg';
				}

				
				var str='<div id="deleteCom_'+commentkey+'" class="commentStyle" onmouseover="showCommentDeleteButton(\''+commentkey+'\')" onmouseout="hideCommentDeleteButton(\''+commentkey+'\')" >';


				str+='<div class="commentPhotoStyle" >';

				str+='<div class="photoSpaceStyle"><img src="'+commentUserPhoto+'" width="50px" height="50px">';
				str+='</div></div>';

				str+='<div class="commentPhotoStyle1" width="800px"><span width="100%" class="commentedUserNameStyle" id="'+commentkey+'_'+commmentUserId+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+commentkey+'\',\''+commmentUserId+'\')"><a class="linksToOTherPages" onclick="callme(\''+commmentUserId+'\')" > '+commentUserName+'</a></span>: <span width="100%" class="commentedDescriptionStyle" > '+commentDesc+'</span> <br><span class="commentTimeStyle"> ' + timeInStr+'</span></div>';

				if(commmentUserId==empId){
					str+='<div><div id="deleteComment_'+commentkey+'" class="deleteCommentStyle"><span class="commentDeleteTextStyle" onclick="deleteComment(\''+commentkey+'\',\''+commentDate+'\')" >x</span> </div></div>';
				}

				str+="</div>";
				var objjj=document.getElementById("comment_"+comm.postId);
				objjj.innerHTML = objjj.innerHTML + str;
				var sss=document.getElementById("myCLikeCount").innerHTML;
				console.log("ssss " + sss);
				/*if(sss=='' ||sss==null){
					document.getElementById("myCLikeCount").innerHTML=1;
				}else{
					var count = parseInt(sss)+1;
					document.getElementById("myCLikeCount").innerHTML=count;
				}*/
				 
				/*   $.getJSON("GetNotificationData",{},function(data){
				    	var likeCount=data.likeCount;
				    	var commentCount=data.commCount;
				    	var followCount =  data.followCount;
				    	var messageCount = data.messageCount;
				    	addLikeNofiCount(likeCount);
				    	addCommentNotiCount(commentCount);
				    	addFollowCount(followCount);
				    	addMessageCount(messageCount);
				    	//followCount
				    }); */
				//objjj.append(str);
			 }
		 }
	 }
}