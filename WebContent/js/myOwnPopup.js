
//SETTING UP OUR POPUP
//0 means disabled; 1 means enabled;
var popupStatus = 0;
var popupStatus1 = 0;
var popupStatusCover=0;
function loadPopupCover(){
	//loads popup only if it is disabled
	if(popupStatusCover==0){
		$("#backgroundPopupCover").css({
			"opacity": "0.7"
		});
		//var fra=document.getElementById("myIFrameUpload").src="photoUpload.jsp";
		document.getElementById('myIFrameUploadCover').src = document.getElementById('myIFrameUploadCover').src;
		document.getElementById("myIFrameUploadCover").contentDocument.location.reload(true);
		$("#backgroundPopupCover").fadeIn("slow");
		$("#popupContactCover").fadeIn("slow");
		popupStatusCover = 1;
	}
}
//loading popup with jQuery magic!
function loadPopup(){
	//loads popup only if it is disabled
	if(popupStatus==0){
		$("#backgroundPopup").css({
			"opacity": "0.7"
		});
		//var fra=document.getElementById("myIFrameUpload").src="photoUpload.jsp";
		document.getElementById('myIFrameUpload').src = document.getElementById('myIFrameUpload').src;
		document.getElementById("myIFrameUpload").contentDocument.location.reload(true);
		$("#backgroundPopup").fadeIn("slow");
		$("#popupContact").fadeIn("slow");
		popupStatus = 1;
	}
}
function loadPopup1(){
	//loads popup only if it is disabled
	if(popupStatus1==0){
		$("#backgroundPopup1").css({
			"opacity": "0.7"
		});
		var fra=document.getElementById("myIFrameCrop").src="ImageCrop.jsp";
		
		document.getElementById("myIFrameCrop").contentDocument.location.reload(true);
		$("#backgroundPopup1").fadeIn("slow");
		$("#popupContact1").fadeIn("slow");
		popupStatus1 = 1;
	}
}
//disabling popup with jQuery magic!
function disablePopup(){
	//disables popup only if it is enabled
	if(popupStatus==1){
		$("#backgroundPopup").fadeOut("slow");
		$("#popupContact").fadeOut("slow");
		popupStatus = 0;
		window.location.reload();
	}
}
//disabling popup with jQuery magic!
function disablePopupCover(){
	//disables popup only if it is enabled
	if(popupStatusCover==1){
		$("#backgroundPopupCover").fadeOut("slow");
		$("#popupContactCover").fadeOut("slow");
		popupStatusCover = 0;
		window.location.reload();
	}
}

//disabling popup with jQuery magic!
function disablePopup1(){
	//disables popup only if it is enabled
	if(popupStatus1==1){
		$("#backgroundPopup1").fadeOut("slow");
		$("#popupContact1").fadeOut("slow");
		popupStatus1 = 0;
		window.location.reload();
	}
}


//centering popup
function centerPopup(){
	//request data for centering

	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight;
	var popupHeight = $("#popupContact").height();
	var popupWidth = $("#popupContact").width();
	//centering
	$("#popupContact").css({
		"position": "absolute",
		"top": windowHeight/2-popupHeight/2,
		"left": windowWidth/2-popupWidth/2
	});
	//only need force for IE6
	
	$("#backgroundPopup").css({
		"height": windowHeight
	});
	
}
//document.addProof.reset();
function callDivPopup(){
	
	  // This AJAX call will save the Navigator's state to session.
	  // We don't need a callback function because nothing happens
	  // once said state is saved.
	  var url = "ManageCalSession?empid="+document.myForm.empid.value;
	  try {
	  req = new ActiveXObject("Microsoft.XMLHTTP");
	  req.open("POST", url, true);				
	  req.send();

	  }catch(e){
		 req= new window.XMLHttpRequest();
		 req.open("POST", url, false);				
		  req.send(null);

	  }
	//centering with css
	centerPopup();
	//load popup
	loadPopup();
}
function callDivPopupCover(){
	
	  // This AJAX call will save the Navigator's state to session.
	  // We don't need a callback function because nothing happens
	  // once said state is saved.
	  var url = "ManageCalSession?empid="+document.myFormCover.empidCover.value;
	  try {
	  req = new ActiveXObject("Microsoft.XMLHTTP");
	  req.open("POST", url, true);				
	  req.send();

	  }catch(e){
		 req= new window.XMLHttpRequest();
		 req.open("POST", url, false);				
		  req.send(null);

	  }
	//centering with css
	centerPopupCover();
	//load popup
	loadPopupCover();
}
$(document).ready(function(){

	//LOADING POPUP
	//Click the button event!
	$(".SendStyle").click(function(){

	});
				
	//CLOSING POPUP
	//Click the x event!
	$("#popupContactClose").click(function(){
		disablePopup();
	});
	//Click out event!
	$("#backgroundPopup").click(function(){
	//	disablePopup();
	});
	//Press Escape event!
	$(document).keypress(function(e){
		if(e.keyCode==27 && popupStatus==1){
			//disablePopup();
		}
	});

});

$(document).ready(function(){

	//LOADING POPUP
	//Click the button event!
	$(".CropImage").click(function(){

	});
				
	//CLOSING POPUP
	//Click the x event!
	$("#popupContactClose1").click(function(){
		disablePopup1();
	});
	//Click out event!
	$("#backgroundPopup1").click(function(){
	//	disablePopup1();
	});
	//Press Escape event!
	$(document).keypress(function(e){
		if(e.keyCode==27 && popupStatus==1){
			//disablePopup1();
		}
	});

});


//centering popup
function centerPopup1(){
	//request data for centering

	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight;
	var popupHeight = $("#popupContact1").height();
	var popupWidth = $("#popupContact1").width();
	//centering
	$("#popupContact1").css({
		"position": "absolute",
		"top": windowHeight/2-popupHeight/2,
		"left": windowWidth/2-popupWidth/2
	});
	//only need force for IE6
	
	$("#backgroundPopup1").css({
		"height": windowHeight+200,
		"width": windowWidth+100
	});
	
}

function centerPopupCover(){
	//request data for centering

	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight;
	var popupHeight = $("#popupContactCover").height();
	var popupWidth = $("#popupContactCover").width();
	//centering
	$("#popupContactCover").css({
		"position": "absolute",
		"top": windowHeight/2-popupHeight/2,
		"left": windowWidth/2-popupWidth/2
	});
	//only need force for IE6
	
	$("#backgroundPopupCover").css({
		"height": windowHeight+200,
		"width": windowWidth+100
	});
	
}

