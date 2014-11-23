function showDivision(){
	jQuery.getJSON("ManageDepartment", {'type' :'department'}, function(datas) { // Do an AJAX call
		 $.each(datas, function(l,items){
		addDepartmentToList(items);
		 });
	});
	jQuery.getJSON("ManageDepartment", {'type' :'designation'}, function(datas) { // Do an AJAX call
		 $.each(datas, function(l,items){
			 addDesignationToList(items);
		 });
	});
}
function deleteDep(id){
	jQuery.getJSON("DeleteDivisions", {'key' :id,'type':'department'}, function(datas) { // Do an AJAX call
		$.each(datas, function(l,items){
		if(items.result=='SUCCESS'){
			var keys="#"+id;
			$(keys).css('display','none');
		}
		});
	});
}
//DeleteLink
function deleteDes(id){
	jQuery.getJSON("DeleteDivisions", {'key' :id,'type':'designation'}, function(datas) { // Do an AJAX call
		$.each(datas, function(l,items){
		if(items.result=='SUCCESS'){
			var keys="#"+id;
			$(keys).css('display','none');
		}
		});
	});
}
function addDepartment(){
	var depName = document.getElementById("depName").value;
	var depOrder = document.getElementById("depOrder").value;
	jQuery.getJSON("CreateDepartment", {'depName' :depName,'orderDep':depOrder}, function(datas) { // Do an AJAX call
		addDepartmentToList(datas);
		document.getElementById("depName").value="";
		document.getElementById("depOrder").value="";
	});
	
}
function addDesignation(){
	var depName = document.getElementById("desName").value;
	var depOrder = document.getElementById("desOrder").value;
	jQuery.getJSON("CreateDesignation", {'desName' :depName,'orderNum':depOrder}, function(datas) { // Do an AJAX call
		addDesignationToList(datas);
		document.getElementById("desName").value="";
		document.getElementById("desOrder").value="";
	});
}
function addDepartmentToList(item){
	var str="";
	str+="<table id='"+item.key+"'>";
	str+="<tr>";
	str+="<td class=\"columnHeaderStyleColumns\">"+item.name+"</td>";
	str+="<td class=\"columnHeaderStyleColumns\">"+item.orderDep+"</td>";
	str+="<td class=\"columnHeaderStyleColumns\"> <input type='button' value='delete' onclick=\"deleteDep('"+item.key+"')\"></td></tr></table>";
	if(document.getElementById(item.key)==null){
		$("#insertDepDiv").append(str);
	}
}

function addDesignationToList(item){
	var str="";
	str+="<table id='"+item.key+"'>";
	str+="<tr>";
	str+="<td class=\"columnHeaderStyleColumns\">"+item.name+"</td>";
	str+="<td class=\"columnHeaderStyleColumns\">"+item.orderNum+"</td>";
	str+="<td class=\"columnHeaderStyleColumns\"> <input type='button' value='delete' onclick=\"deleteDes('"+item.key+"')\"></td></tr></table>";
	if(document.getElementById(item.key)==null){
		$("#insertDesDiv").append(str);
	}
}


function addLink(){
	alert(" sss ");
	var linkName = document.getElementById("linkName").value;
	var linkURL = document.getElementById("linkURL").value;
	var userName = document.getElementById("userName").value;
	var passwordU = document.getElementById("passwordU").value;
	var orderNum = document.getElementById("orderNum").value;
	jQuery.getJSON("CreateLink", {'linkName' :linkName,'linkURL':linkURL,'userName':userName,'passwordU':passwordU,'orderNum':orderNum}, function(datas) { // Do an AJAX call
		addLinktoPage(datas);
	});
	
}
/**
 * <td class="columnHeaderStyleColumns1">Name</td>
								<td class="columnHeaderStyleColumns2">URL</td>
								<td class="columnHeaderStyleColumns1">User Name</td>
								<td class="columnHeaderStyleColumns3">Order</td>
								<td class="columnHeaderStyleColumns1"></td>
 * @param item
 */
function addLinktoPage(item){
	var str="";
	str+="<tr>";
	str+="<td class=\"columnHeaderStyleColumns1\">"+item.name+"</td>";
	str+="<td class=\"columnHeaderStyleColumns2\">"+item.url+"</td>";
	str+="<td class=\"columnHeaderStyleColumns1\">"+item.userName+"</td>";
	str+="<td class=\"columnHeaderStyleColumns3\">"+item.orderNum+"</td>";
	str+="<td class=\"columnHeaderStyleColumns1\"> <input type='button' value='delete' onclick=\"deleteLinks('"+item.key+"')\"></td></tr>";
	alert(" sss " + str);
	if(document.getElementById(item.key)==null){
		$("#insertLink").append(str);
	}
}
function showLinksData(){
	
	$("#insertLink").empty();
	jQuery.getJSON("GetLinks", {}, function(datas) { // Do an AJAX call
		 $.each(datas, function(l,items){
			 addLinktoPage(items);
		 });
	});
}
//DeleteLink
function deleteLinks(id){
	jQuery.getJSON("DeleteLink", {'key' :id,}, function(datas) { // Do an AJAX call
		$.each(datas, function(l,items){
		if(items.result=='SUCCESS'){
			var keys="#"+id;
			$(keys).css('display','none');
		}
		});
	});
}