var protocal='http'; 
 $(document).ready(function () {
	 $('#tickerDiv').slimScroll({
         height: '220px',
         alwaysVisible: false,
         size: '12px',
         color: '#555555'
         });
	 $('#insertTickers').slimScroll({
         height: '220px',
         alwaysVisible: false,
         size: '12px',
         color: '#555555'
         });
	
	 $('#birdthDayDiv').slimScroll({
         height: '242px',
         alwaysVisible: false,
         size: '12px',
         color: '#555555'
         });
	 
	 $('#bulletinColumn').slimScroll({
         height: '242px',
         alwaysVisible: false,
         size: '12px',
         color: '#555555'
         });
	 
	 $('#myDiv').slimScroll({
         height: '220px',
         alwaysVisible: false,
         size: '12px',
         color: '#555555'
         });
	 $('#bullentinDiv').slimScroll({
         height: '220px',
         alwaysVisible: false,
         size: '12px',
         color: '#555555'
         });
//	 $('#feedControl').slimScroll({
//         height: '200px',
//         alwaysVisible: false,
//         size: '8px',
//         color: '#555555'
//         });
 });
 function hideUserInfo(key){
	  
	  $('#userInfo').css('display','none');
	  $('#manageLinks').css('display','none');
	  $('#arrowDiv').css('display','none');
	  $('#tickerArrow').css('display','none');
	  if(!(key=='ticker'))
	  $('#tickerdailog').css('display','none');
	  
	}

 function addPublicChats(item){
	var bPath=document.getElementById("basePathOfPhoto").value;
	 var str='';
	 var p='images/person_icon.jpg';
	 if(item !=null && item.key!=null){
		 if(item.info!=null && item.info.imagePath!=null)
			 p=bPath+item.info.imagePath.photoPath.replace("a_","g_");
		 str+='<div class="borderForChat"><table cellpadding="0" cellspacing="0"><tr><td style="padding-right:5px;padding-top:3px;vertical-align:top;padding-left:3px;"><img src="'+p+'" width="30px" height="30px" ></td><td><table cellpadding="0" cellspacing="0" border="0">'+
		 '<tr><td><span class="chatterName" style="vertical-align:middle;" ><a style="text-decoration:none;color:black;"  onclick="callme(\''+item.info.id+'\')"  id="'+item.key+'_'+item.info.id+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+item.key+'\',\''+item.info.id+'\')">'+item.info.empName+'</a></span></td></tr>'+
		 '<tr><td style="vertical-align:top;"><p class="chatContent" style="vertical-align:top;">'+item.postedInfo+'</p></td></tr></table></td></tr></table>'+
		 '</div>';
		 $("#bulletinColumn").append(str);
	 }
 }
 function addPublicChatsInTop(item){
		var bPath=document.getElementById("basePathOfPhoto").value;
		 var str='';
		 var p='images/person_icon.jpg';
		 if(item !=null && item.key!=null){
			 if(item.info!=null && item.info.imagePath!=null)
				 p=bPath+item.info.imagePath.photoPath.replace("a_","g_");
			 str+='<div class="borderForChat"><table cellpadding="0" cellspacing="0"><tr><td style="padding-right:5px;padding-top:3px;vertical-align:top;padding-left:3px;"><img src="'+p+'" width="30px" height="30px" ></td><td><table cellpadding="0" cellspacing="0" border="0">'+
			 '<tr><td><span class="chatterName" style="vertical-align:middle;" ><a style="text-decoration:none;color:black;"  onclick="callme(\''+item.info.id+'\')"  id="'+item.key+'_'+item.info.id+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+item.key+'\',\''+item.info.id+'\')">'+item.info.empName+'</a></span></td></tr>'+
			 '<tr><td style="vertical-align:top;"><p class="chatContent" style="vertical-align:top;">'+item.postedInfo+'</p></td></tr></table></td></tr></table>'+
			 '</div>';
			 $("#bulletinColumn").prepend(str);
		 }
	 }
 function addMyDynamicBirthEvents(item){
	 
	  var str='';
		  		if(item.id!=null){
		  		 var p='images/person_icon.jpg';
		  			if(item.imagePath!=null)
		  				p=item.imagePath;
		  			if(item.todayBirth==true){
		  				str+='<div id="'+item.id+'"  class="birthdaySectionToday"  ><div id="Abc" style="display:table-row;" ><div style="display:table-cell;padding-left:3px;vertical-align: top;">';
			  			str+='<img src="'+p+'" width="30px" height="30px" ></div><div style="display:table-cell;vertical-align: top;padding-left:5px;"><a  onclick="callme(\''+item.id+'\')" ><span class="birthdayNameStyle" style="vertical-align: top;" id="'+item.id+'_'+item.id+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+item.id+'\',\''+item.id+'\')">';
			  			str+=item.empName+' ' + '</span></a> <span class="tickerMessageStyle" style="vertical-align: top;">'+item.dobStr+' </span>&nbsp;<img src="images/dob.png" width="30px" height="30px"></div>';
			  			str+='</div></div>';
		  			}else{
		  			str+='<div id="'+item.id+'"  class="birthdaySection"  ><div id="Abc" style="display:table-row;" ><div style="display:table-cell;padding-left:5px;vertical-align: top;">';
		  			str+='<img src="'+p+'" width="30px" height="30px" ></div><div style="display:table-cell;vertical-align: top;padding-left:5px;"><a class="birthdayNameStyle"  onclick="callme(\''+item.id+'\')" ><span class="birthdayNameStyle" id="'+item.id+'_'+item.id+'" onmouseout="hideUserInfo()" onmouseover="getUserInfoDisplay(\''+item.id+'\',\''+item.id+'\')">';
		  			str+=item.empName+' ' + '</span></a> <span class="tickerMessageStyle">'+item.dobStr+' </span>&nbsp;<img src="images/dob.png" width="30px" height="30px"></div>';
		  			str+='</div></div>';
		  			}
		  		}
			 $("#birdthDayDiv").append(str);
	 
 }
 var latestTickerDate=null;
 
 function addMyTickerDynamically(item){
	 var path=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
	// alert(" itcker greate ");
	var str='';
				var key = item.key;//+'~~~'+item.dateTime;
				var message = item.message;
	 	  		if(item.userId!=null){
	 	  		 var p='images/person_icon.jpg';
	 	  			if(item.targetUserId.imagePath!=null)
	 	  				p=path+item.targetUserId.imagePath.photoPath.replace("a_","g_");
	 	  			//if(latestTickerDate==null)
	 	  				latestTickerDate = item.dateTime;
	 	  			console.log(" item.date ticker " + item.dateTime);
	 	  			str+='<div id="'+key+'"  class="tickerSection" style="height:50px;"  ><input type="hidden" id="date_'+key+'" name="date_'+key+'" value="'+item.dateTime+'"/> <div id="Abc" style="display:table-row;" ><div class="tickerSection1"  style="display:table-cell;padding-left:3px;">';
	 	  			str+='<img src="img/progressbar.gif" class="hideTickerProgress" width="30px" id="'+key+'_progress" height="20px"><img src="'+p+'" id="'+key+'_photo" width="30px" height="30px" ></div><div style="display:table-cell;vertical-align: top;padding-left:5px;width:100%;"><span class="tickerUserNameStyle">';
	 	  			str+=item.targetUserId.empName+' ' + '</span> <span class="tickerMessageStyle">'+message+' </span></div>';
	 	  			str+='</div><span style="color:gray;font-size:10px;">'+item.time+'<span></div>';
	 	  			
	 	  			/*str+='<div id="tickerdailog" class="popover">';
 	  				str+='<div class="arrow"></div>';
  					str+='<h3 class="popover-title">CCC</h3>';
					str+='<div class="popover-content" style="margin-top: 0px;">';
					str+='	<div id="appendTickerData"></div>';
					str+='</div>';
					str+='</div>';*/
				
	 	  		}
	 	  	var ss=document.getElementById("tickerDate");
	 	  	if(ss!=null)
	 	  		ss.value=latestTickerDate;
			var sss= document.getElementById(key);
			
			if(sss==null){
				$("#insertTickers").prepend(str);
				console.log(str);
				var ssssss='#'+key;
				$(ssssss).children().bind('click', function () {
					   var keyFull=$(this).parent().attr('id');
					   //alert(" keyfull " + keyFull);
					   var dates = document.getElementById('date_'+key);
					  // alert(" spit " + splipt[1]);
					   var kf='#'+keyFull;
					   $(kf).css({'background-color':'#EEEEEE'});
					   getTickerDisplay(keyFull);
					   
					   $('#tickerdailog').css('display','none');	
					   $('#tickerdailog').css('z-index','100');	
					   try{
						  console.log(" kf " + kf);
						  
					   /*$('#tickerdailog').modalPopover({
							target : '#tarrgetTicker',
							placement : 'right'
						});*/
					   $('#tickerdailog').modalPopover('show');
					   $('#tickerdailog').css('display','none');
					   }
					   catch(e){}
				});
				$(ssssss).children().bind('mouseleave', function () {
					   var keyFull=$(this).parent().attr('id');
					   var k = '#'+keyFull;
					   $(k).css({'background-color':'#DFF0F7'});
					  // getTickerDisplay(keyFull);
					
				});	
				console.log(" tcker i d " + ssssss);
			  	
				
			}
			
		
 }
 function registerEvents(){

	 
	 function showTick(){
		 
	 }
	 function hideTick(){
		 //var keyFull=$(this).parent().attr('id');
		 var keyFull=$(this).attr('id');
		   var k = '#'+keyFull;
		   //$(k).css({'background-color':'#DFF0F7'});
		   $(k).css({'background-color':'greeb'});
		  // getTickerDisplay(keyFull);
	 }
	 function registerTickEvent(){

		 $('.tickerSection').live('mouseover',function(){
			 var keyFull=$(this).parent().attr('id');
			 
			   var k = '#'+keyFull;
			   $(k).css({'background-color':'#EEEEEE'});
			   var kk=k+'_progress';
			   var kkk=k+'_photo';
			   $(kkk).css({'display':'none'});
			   $(kk).css({'display':'block'});
		 });
		  // getTickerDisplay(key);
	 


	//$('.tickerSection1').children().bind('mouseout', function () {
//		   var keyFull=$(this).parent().attr('id');
//		   var k = '#'+keyFull;
//		   alert("key " + k);
//		   $(k).css({'background-color':'#DFF0F7'});
//		  // getTickerDisplay(keyFull);
//		   hideUserInfo('dfd');
	//});
		 
	 }
	
 }
 function getTickerDisplay(key){
	 	var skls="#"+key;
	 	$("#tickerdailog").css({'display':'none'});
	 	$("#appendTickerData").css({'display':'none'});
	 	$('#tickerdailog').modalPopover({
				target : skls,
				placement : 'left'
			});
	 	$("#tickerdailog").css({'display':'none'});
	 	$("#appendTickerData").css({'display':'none'});
		var currentUserId= document.getElementById("hiddenCurrentUser").value;
	  	 var userDiv= document.getElementById('userInfo');
	  	var currentuserName=document.getElementById("txtEmpName").value;
	  	var currentPhoto = document.getElementById("txtPhotoPath").value;
	  	if(currentPhoto!=null)
	  		currentPhoto=currentPhoto.replace("a_","g_");
	  	var path=protocal+'://'+window.location.host+'/PhotoStatic/userUploads/';
	  	//var path='userUploads/';
	  	var str='';
	  	  $.getJSON("GetTickerInfo", {'key' :key}, function(datas) { // Do an AJAX call
	  			var likeText='';
	  			var str1='';
	  		  if(datas.photo!=null){
	  			  var pp='images/person_icon.jpg';
	  			  if(datas.userId.image!=null)
	  				  pp=path+datas.userId.image.photoPath.replace("a_","g_");
	  			  str='<div id="'+key+'_myTickerPop" class="giveBorder"><input type="hidden" value="'+datas.photo.idPhotoInfo+'" id="myPhotoId" > <div align="center" class="adjustPhoto">'+
	  			  '<a class="myCLiCKStyle" onclick="updateHiddenBox(\''+datas.photo.idPhotoAlbum+'\',\''+datas.photo.ownerId+'\',\''+datas.photo.photoPath+'\',\'1\',\''+datas.photo.width+'\',\''+datas.photo.height+'\')" > <img src="'+path+datas.photo.photoPath.replace('a_','e_')+'" ><div style="padding-bottom:10px;"></div></a></div>';
	  			  str+='<div style="display:table-row;background-color:#E1F0F5;width:100%;" align="left"><div  align="left" style="display:table-cell;padding-top:10px;padding-bottom:10px;width:5%;"><img src="'+pp+'" width="30px" height="30px"></div>'+
	  			  '<div  align="left" style="display:table-cell;vertical-align: top;padding-top:10px;padding-bottom:10px;width:95%;"><span class="tickerMainNameStyle"><a  onclick="callme(\''+datas.userId.id+'\')"  class="likeNameStyleLink">'+datas.userId.empName+'</a></span></div>'+
	  			  
	  			  '</div>';
	  			//  if(datas.tickerType=='like'){
	  				  str1+='<div class="likeStyleInTicker"> <img src="img/icon_like.png"><span class="myCommentInTicker">';
	  			
	  				  var count = datas.photo.likeInfo.length;
	  				  var i=0;
	  				  var likeKeyValue="";
	  				  var flag=0;
	  				$.each(datas.photo.likeInfo,function(j,l){
	  					if(l.likedUserId==currentUserId){
	  						str1+='you</span>';
	  						likeKeyValue=l.id;
	  						flag=1;
	  					}
	  				});
	  				if(flag==1){
	  				//	likeText='<a href="#" onclick="updateLike" >Unlike</a>';
	  				}
	  				 $.each(datas.photo.likeInfo,function(j,l){
	  					if(i<3)	{
	  					
	  						
	  						console.log(' i.likedUserId ' + l.likedUserId + ' : datas.userId.id ' + datas.userId.id);
	  						if(l.likedUserId==currentUserId){
	  							//str+='you';
	  							i=i+1;
	  						}else{
	  							if(flag==0){
	  								str1+='<span class="OtherLIkedStyleTicker"><a  onclick="callme(\''+l.likedUserId+'\')"    class="likeNameStyleLink"> '+l.userName+'</a></span>';
	  								
	  							}else{
	  							str1+=', <span class="OtherLIkedStyleTicker1"><a onclick="callme(\''+l.likedUserId+'\')"   class="likeNameStyleLink"> '+l.userName+'</a></span>';
	  							}
	  							i=i+1;
	  							}
	  						}
	  						
	  				 });
	  					 if(count>3){
	  						 str1+='<span class="OtherLIkedStyleTicker1"> and '+(count-3)+' other likes this.</span>';
	  					 }
	  					 
	  				
	  				 str1+='</div>';
	  				 if(flag==1){
	  					 str=str+'<a class="tickerLikeUnlikeStyle" onclick="UnlikePhoto(\''+datas.photo.idPhotoInfo+'\',\''+likeKeyValue+'\',\''+key+'\')">Unlike</a>&nbsp;.&nbsp;<a class="tickerLikeUnlikeStyle" onclick="focusComment()">Comment</a>';
	  				 }else{
	  					 str=str+'<a class="tickerLikeUnlikeStyle" onclick="likePhoto(\''+datas.photo.idPhotoInfo+'\',\''+key+'\')">Like</a>&nbsp;.&nbsp;<a class="tickerLikeUnlikeStyle" onclick="focusComment()">Comment</a>';
	  				 }
	  				 str=str+likeText+str1;
	  			 // }else{
	  				 var ppp='images/person_icon.jpg';
	  				 var cou=0;
	  			  if(datas.photoComm!=null){
		  				  str+='<div id="myTickerSectionId" class="tickerCommentSection"><input type="hidden" value="'+datas.photo.idPhotoInfo+'" id="myPhotoId" >';
		  				 $.each(datas.photoComm,function(j,comm){
		  					 var p='images/person_icon.jpg';
		  					 if(comm.commentUserPhoto!=null)
		  						 p=comm.commentUserPhoto;
		  					 	 if(p!=null)
		  					 		 p=path+p.replace("a_","g_");
		  						 cou=cou+1;
		  					 str+='<div style="display:table-row;width:100%;vertical-align: top;" class="borderForSec2">'+
		  					 		'<div class="borderForSec" style="width:100%;">'+
		  					 			'<div style="display:table-cell;padding-top:5px;vertical-align: top;width:10%;">'+
		  					 				'<img src="'+p+'" width="30px" height="30px">'+
		  					 			'</div>'+
		  					 			'<div style="display:table-cell;padding-top:5px;vertical-align: top;width:90%;">'+
		  					 				'<span class="tickerCOmmentUserNameStyle"><a onclick="callme(\''+comm.commmentUserId+'\')"  class="likeNameStyleLink">'+comm.commentUserName+'</a></span>&nbsp;&nbsp;<span class="tickerCOmmentsStyle">'+comm.commentDesc+'</span>'+
		  					 			'</div>'+
		  					 		'</div>'+
		  					 	'</div>';
		  				 });
//		  				 if(cou>0)
//		  					str+='<div style="display:table-row;width:100%" class="borderForSec22">'+
//		  							'<div class="borderForSec21" style="width:100%;vertical-align: top;">'+
//		  								'<div style="display:table-cell;padding-top:5px;vertical-align: top;width:10%;">'+
//		  								'</div>'+
//		  								'<div style="display:table-cell;padding-top:5px;vertical-align: top;width:90%;">'+
//		  									'<span class="tickerCOmmentUserNameStyle"></span><br><span class="tickerCOmmentsStyle"></span>'+
//		  								'</div>'+
//		  							'</div>'+
//		  						
//		  							'</div>'+
//		  						'</div>';
		  				str+='<div style="display:table-row;width:100%" class="borderForSec2">'+
		  						'<div class="borderForSec1" style="width:100%;">'+
		  							'<div style="display:table-cell;padding-top:5px;vertical-align: middle;width:10%;">'+
		  								'<img src="'+path+currentPhoto+'" width="30px" height="30px">'+
		  							'</div>'+
		  							'<div style="display:table-cell;padding-top:5px;vertical-align: top;width:90%;">'+
		  								'<span class="tickerCOmmentsStyle"><input type="text" id="commentTextInTicker" class="myCommentStyle1" size="35px"> </span>'+
		  							'</div>'+
		  						'</div>'+
		  					'</div>';
		  				str+='<input type="hidden" id="postInTickerId" value="'+key+'">';
		  				 str+='</div>';
		  				registerEventTicker();
		  				
		  				
		  				
		  			  }
	  			  }
	  			  //str+='</div>';
	  			 var keyUser = key;
	  	   		 //var offset = $(keyUser).offset();
	  	   		 // alert(" hi "+keyUser);
	  			 console.log(" keyUser " + keyUser);
	  			
	  	   		  var obj = document.getElementById(keyUser);
	  	   		var position = document.getElementById("insertTickers").scrollTop;
	  	   		  var posX = obj.offsetLeft;
	  	   		  var posY = obj.offsetTop;
	  	   		  var posssX=posX;
	  	   while(obj.offsetParent){
	  	   	  posX=posX+obj.offsetParent.offsetLeft;
	  	   	  posY=posY+obj.offsetParent.offsetTop;
	  	   	  if(obj==document.getElementsByTagName('body')[0]){break;}
	  	   	  else{obj=obj.offsetParent;}
	  	   	  }
	//  			  $('#userInfo').css('box-shadow','0px 2px 2px 0px black');
	 // 		  $('#userInfo').css('left',posX-300);
	  		 posY=posY-position;
	  	/*	  $('#userInfo').css('top',posY);
	  		   $('#userInfo').css('display','block');
	  		 $('#userInfo').css('z-index','80');*/
	  		
	  		/* $('#tickerArrow').css('position','absolute');
	  		  $('#tickerArrow').css('left',posX-15);
	  		  $('#tickerArrow').css('top',posY+10);
	  		   $('#tickerArrow').css('display','block');
	  		 $('#tickerArrow').css('backgroundColor','#CCCCCC');*/
	  		 console.log(" x = " + posX);
	  		$("#tickerdailog").css({'display':'none'});
	  		$('#tickerdailog').css('left',posssX-255);
	  		$('#tickerdailog').css('width',300);
	  		$('#tickerdailog').css('top',posY-70);
	  		
	  		  // userDiv.innerHTML=str;
	  		    document.getElementById("appendTickerData").innerHTML=str;
	  		    console.log(" here i am " + str);
	  		 $('#appendTickerData').slimScroll({
			         height: '300px',
			         alwaysVisible: false,
			         size: '8px',
			         color: '#555555'
			         });
	  		$("#tickerdailog").css({'display':'block'});
	  		$("#appendTickerData").css({'display':'block'});
			 //  $(kk).css({'display':'none'});//image
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
		    			   currentUserName=currentUserName.replace(/"/g, "");
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
		str+='<table><tr><td><img src="'+path+datas.photoPath.replace('a_','e_')+'" width="100px" height="100px"></td></tr></table>';
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