/**
 * 
 */
/*

* Author:      Marco Kuiper (http://www.marcofolio.net/)

*/


//google.load("jquery", "1.3.1");

google.setOnLoadCallback(function()

{

	// Safely inject CSS3 and give the search results a shadow

	var cssObj = { 'box-shadow' : '#888 5px 10px 10px', // Added when CSS3 is standard

		'-webkit-box-shadow' : '#888 5px 10px 10px', // Safari

		'-moz-box-shadow' : '#888 5px 10px 10px'}; // Firefox 3.5+

	$("#suggestions").css(cssObj);
	
	// Fade out the suggestions box when not active
	 $("input").blur(function(){
	 	$('#suggestions').fadeOut();
	 	
	 });
	// Fade out the suggestions box when not active
	 $("select").blur(function(){
	 	$('#suggestions').fadeOut();
	 });
	 //$("p").height()

});

function lookup(inputString,searchType) {

	if(inputString.length == 0) {
		$('#suggestions').fadeOut(); // Hide the suggestions box
	} else {
		$("#suggestions").height("auto");
		$.post("SearchUsers", {queryString: ""+inputString+"~"+searchType+""}, function(data) { // Do an AJAX call
			//alert("Dddd");
			///$('#suggestions').fadeIn(); // Show the suggestions box
			//alert(" data is  " + $("#searchresults").height());
			///$('#suggestions').html(data); // Fill the suggestions box
			
			//.css("display", "inline")
			//alert(" data is before  " + $("#searchresults").height());
			//$("#searchresults").height(10);
			///$("#searchresults").css("overflow", "auto");
			//$("#suggestions").css('display','inline');
			//var divTag=document.myForm.getElementById("suggestions");
			//divTag.style.height='10px';
			//divTag.style.display='inline';
			//divTag.style.overflow='auto';
			//alert(" data is after  " + $("#searchresults").height());
			//alert(" height " +  $("#suggestions").height() + " width is " + $("#suggestions").width());
		});
	}
}

function arrived(data){
	alert(data);
	$('#suggestions').html(data);
}