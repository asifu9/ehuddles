 
 function addScroll(){
	 $('#userList').slimScroll({
         height: '427px',
         alwaysVisible: false,
         size: '12px',
         color: '#555555'
         });
$('#selectedUsersScroll').slimScroll({
    height: '427px',
    alwaysVisible: false,
    size: '12px',
    color: '#555555'
    });

$("#tagMe").bind('mouseenter', function () {
	   var kf='#tagMe';
	   $(kf).css({'background-color':'#A7BAD1'});
	   $("#cancelMe").css({'background-color':'#CDD8E5'});

});
$("#tagMe").bind('mouseleave', function () {
	   var k = '#tagMe';
	   $(k).css({'background-color':'#CDD8E5'});

});	
$("#cancelMe").bind('mouseenter', function () {
	   var kf='#cancelMe';
	   $(kf).css({'background-color':'#A7BAD1'});
	   $("#tagMe").css({'background-color':'#CDD8E5'});

});
$("#cancelMe").bind('mouseleave', function () {
	   var k = '#cancelMe';
	   $(k).css({'background-color':'#CDD8E5'});

});	
 }
 function changeColorTag(){
	 console.log(" hee he he");
	 $("#tagMe").css({'background-color':'#A7BAD1'});
 }
function tagPhoto(photoPath,photoId,basepath){
	var str='';
	var count=0;
	
	
	
	  var obj = document.getElementById("myTagPoint");
		  var posX = obj.offsetLeft;
		  var posY = obj.offsetTop;

while(obj.offsetParent){
	  posX=posX+obj.offsetParent.offsetLeft;
	  posY=posY+obj.offsetParent.offsetTop;
	  if(obj==document.getElementsByTagName('body')[0]){break;}
	  else{obj=obj.offsetParent;}
	  }
	  $('#tagSection').css('box-shadow','0px 4px 4px 0px black');
	  $('#tagSection').css('position','absolute');
$('#tagSection').css('left',posX-650);
$('#tagSection').css('top',posY+30);
 $('#tagSection').css('display','block');
 $('#tagSection').css('z-index','102');

 $('#userTagArrow').css('position','absolute');
 $('#userTagArrow').css('left',posX-20);
 $('#userTagArrow').css('top',posY+25);
 $('#userTagArrow').css('display','block');
 $('#userTagArrow').css('z-index','101');
	
	$.getJSON("GetAllEmp", {}, function(datas) { // Do an AJAX call
		$('#userList').empty();
		$('#selectedUsers').empty();
		var reminder=parseInt( datas.length)%3;
		var total= parseInt( datas.length)-1;;
		$.each(datas, function(l,items){
			if(count==3 || count==0){
				str='<table cellpadding="0" cellspacing="0" border="0"><tr>';
			count=count+1;
			var imgPath='';
			
			if(items.image!=null){
				imgPath= basepath+items.image.photoPath;
			}else{
				imgPath='images/person_icon.jpg';
			}
			str+='<td class="eachCellStyle"><div id="'+items.id+'" onclick="addMetoList(\''+items.id+'\',\''+imgPath+'\',\''+items.empName+'\')">';
			str+='<table cellpadding="0" cellspacing="0"><tr><td><img src="'+imgPath+'" width="50px" height="50px" ></td><td>';
			str+='<table cellpadding="0" cellspacing="0"><tr><td><table><tr><td><span>'+items.empName+'</span></td></tr></table>';
			str+='</td></tr></table></div></td></tr></table>';
			
			if(count==3 || total==l){
				var i=1;
				while(i<=reminder){
					str+='<td></td>';
					i+=1;
				}
				str+='</tr></table>';
				console.log("insrting " + str);
				$('#userList').append(str);
				count=0;
			}
			}
			else{
				count=count+1;
				var imgPath='';
				if(items.image!=null){
					imgPath= basepath+items.image.photoPath;
				}else{
					imgPath='images/person_icon.jpg';
				}
				str+='<td class="eachCellStyle"><div id="'+items.id+'" onclick="addMetoList(\''+items.id+'\',\''+imgPath+'\',\''+items.empName+'\')">';
				str+='<table cellpadding="0" cellspacing="0"> <tr><td><img src="'+imgPath+'" width="50px" height="50px" ></td><td>';
				str+='<table cellpadding="0" cellspacing="0"><tr><td><table><tr><td><span>'+items.empName+'</span></td></tr></table>';
				str+='</td></tr></table></div></td></tr></table>';

				if(count==3 || total==l){
					var i=1;
					while(i<=reminder){
						str+='<td></td>';
						i+=1;
					}
					str+='</tr></table>';
					$('#userList').append(str);
					count=0;
				}
			}
		});
			
		$.each(datas, function(l,item){
			var ssssss='#'+item.id;
		$(ssssss).children().bind('mouseenter', function () {
			   var keyFull=$(this).parent().attr('id');
			   var kf='#'+keyFull;
			   $(kf).css({'background-color':'#B6B6B6'});
		   
		});
		$(ssssss).children().bind('mouseleave', function () {
			   var keyFull=$(this).parent().attr('id');
			   var k = '#'+keyFull;
			   $(k).css({'background-color':'#EAEAEA'});
			  // getTickerDisplay(keyFull);
		   
		});	
		});
	});

	
}
function addMyTag(photoId){
	var str='';
	$("div[id^='selected_']").each(function() {

			str=this.id+","+str;
			console.log(" str " + str);
		});
	
	$.getJSON("TagPhotos", {'userList':str,'photoId':photoId}, function(datas) { // Do an AJAX call
		var stt='';
		var count=parseInt(datas.length);
		var i=0;
		$.each(datas, function(l,item){
			i=i+1;
			if(count==i){
				console.log(" clllllllllllllllllllllllllllllosing");
				commentsForPhoto(photoId);
				closeMeTag();
				 $('#showTaggerUsers').css('display','block');
			        
			     	$('#taggedUserList').css('display','block');
			     	$('#arrowtagUserPermanent').css('display','block');
			}
			
		});
		
	});
}
function closeMeTag(){
	 $('#tagSection').css('display','none');
	 $('#userTagArrow').css('display','none');
}
function addMetoList(id,path,name){
	var ss='';
	var iiid='selected_'+id;
	var iiiidd="#"+iiid;
	if(document.getElementById(iiid)==null){
	ss+='<div class="eachCellStyleUserSelected" id="'+iiid+'"><table border="0" aligb="left" width="100%" ><tr><td class="selectedUserSSS" align="left"><img src="'+path+'" width="50px" height="50px"><span>'+name+'</span></td><td width="5px" align="right" style="vertical-align: top;"><span style="color:red;display:none;" id="delete'+iiid +'" onclick="removeUser(\''+iiid+'\')">x</span></td></tr></table></div>';
	
		
		$("#selectedUsers").append(ss);
	}
	$(iiiidd).children().bind('mouseenter', function () {
		   var keyFull=$(this).parent().attr('id');
		   var kf='#'+keyFull;
		   $("#delete"+keyFull).css('display','block');
		   $(kf).css({'background-color':'#B6B6B6'});
	   
	});
	$(iiiidd).children().bind('mouseleave', function () {
		   var keyFull=$(this).parent().attr('id');
		   var k = '#'+keyFull;
		   $("#delete"+keyFull).css('display','none');
		   $(k).css({'background-color':'#FFFFFF'});
		  // getTickerDisplay(keyFull);
	   
	});	
}

function removeUser(id){
	$("#"+id).remove();
}