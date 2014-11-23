<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page import="com.pro.emp.util.Constant"%>
<%@page import="com.pro.emp.EmployeeInfo"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.pro.emp.PropertyReader"%>
<%@page import="com.pro.emp.Util"%>
<%@page import="com.pro.emp.domain.PhotoInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.pro.emp.Session_control"%>
<%@page import="com.pro.emp.domain.EmpInfo"%>
<%

EmpInfo emp=null;
if(Session_control.getSession(request)!=null){
	emp= Session_control.getSession(request);
}
else {
	response.sendRedirect(request.getContextPath()+"/sessionover.jsp");
}

String albumId="";
if(request.getParameter("album")!=null){
	albumId=request.getParameter("album");
}


List<PhotoInfo> list= Util.getAllPhotoByAlbumId(albumId);
HashMap roleMap = EmployeeInfo.getRolesForEmpId(emp.getId());
if(!(roleMap.get(Constant.PHOTO_VIEW)==null?false:true)){
	response.sendRedirect(request.getContextPath()+"/listUsers.jsp");
}

%>




<html>
<head>
  <link rel="stylesheet" type="text/css" href="css/jquery.ad-gallery.css">
  <script type="text/javascript" src="js/jquery4.1.min.js"></script>
  <script type="text/javascript" src="js/jquery.ad-gallery.js"></script>
  <script type="text/javascript">
  $(function() {
    $('img.image1').data('ad-desc', 'Whoa! This description is set through elm.data("ad-desc") instead of using the longdesc attribute.<br>And it contains <strong>H</strong>ow <strong>T</strong>o <strong>M</strong>eet <strong>L</strong>adies... <em>What?</em> That aint what HTML stands for? Man...');
    $('img.image1').data('ad-title', 'Title through $.data');
    $('img.image4').data('ad-desc', 'This image is wider than the wrapper, so it has been scaled down');
    $('img.image5').data('ad-desc', 'This image is higher than the wrapper, so it has been scaled down');
    var galleries = $('.ad-gallery').adGallery();
    $('#switch-effect').change(
      function() {
        galleries[0].settings.effect = $(this).val();
        return false;
      }
    );
    $('#toggle-slideshow').click(
      function() {
        galleries[0].slideshow.toggle();
        return false;
      }
    );
    $('#toggle-description').click(
      function() {
        if(!galleries[0].settings.description_wrapper) {
          galleries[0].settings.description_wrapper = $('#descriptions');
        } else {
          galleries[0].settings.description_wrapper = false;
        }
        return false;
      }
    );
  });
  </script>

  <style type="text/css">
  * {
    font-family: "Lucida Grande", "Lucida Sans Unicode", "Lucida Sans", Verdana, Arial, sans-serif;
    color: #ccc;
    line-height: 140%;
  }
  select, input, textarea {
    font-size: 1em;
  }
  body {
    padding: 0px;
    font-size: 70%;
    width: 800px;
  }
  h2 {
    margin-top: 1.2em;
    margin-bottom: 0;
    padding: 0;
    border-bottom: 1px dotted #dedede;
  }
  h3 {
    margin-top: 1.2em;
    margin-bottom: 0;
    padding: 0;
  }
  .example {
    border: 1px solid #CCC;
    background: #f2f2f2;
    padding: 10px;
  }
  ul {
    list-style-image:url(../image/list-style.gif);
  }
  pre {
    font-family: "Lucida Console", "Courier New", Verdana;
    border: 1px solid #CCC;
    background: white; /*#f2f2f2;*/
    padding: 10px;
  }
  code {
    font-family: "Lucida Console", "Courier New", Verdana;
    margin: 0;
    padding: 0;
  }

  #gallery {
    padding: 20px;
    background: white; /*#e1eef5;*/
  }
  #descriptions {
    position: relative;
    height: 50px;
    background: white;/* #EEE; */
    margin-top: 10px;
    width: 740px;
    padding: 10px;
    overflow: hidden;
  }
    #descriptions .ad-image-description {
      position: absolute;
    }
      #descriptions .ad-image-description .ad-description-title {
        display: block;
      }
  </style>
  <style>
  .headerLink {
			text-decoration: none;
			font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
			font-stretch: expanded;
			font-size: 16px;
			font-style: normal;
			display: block;
			color:#696969;
			font-weight: bold;
	}
	
.headerLinkMO{
			text-decoration: none;
			font-family: "lucida grande", tahoma, verdana, arial, sans-serif;
			font-stretch: expanded;
			color:white;
			font-size: 16px;
			font-style: normal;
			font-weight: bold;
			display: block;
			

	}
.headerLinkMOBlock{
			background-color: #A2A2A2;
			padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
			color:white;
}
.headerLinkBlock{
			 background-color: white; 
			 padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
			color:balck;
}
.link {
	text-decoration: none;
	font-family: Tahoma;
	font-weight: bold;
	font-size: 16px;

}
.headerGap {
			padding-left: 5px;	
			padding-bottom: 5px;
			padding-right: 5px;
			padding-top: 5px;
			width: 70px;
			
	}
  </style>
  <title>Photo album</title>
</head>
<body marginheight="0" marginwidth="0" style="width: 100%">
<div align="center" >
<div style="width:80%;">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="30%" style="padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;">
					<img src="images/logo.jpg"/>
				</td>
				<td valign="bottom" width="70%" align="right" style="padding-bottom: 0px;border-bottom-style: groove ;border-bottom-width: 1px;border-bottom-color: #A2A2A2;">
					<table  cellpadding="0" cellspacing="0" align="right">
						<tr>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="home.jsp" >Home</a>
							</td>
							<% if(emp !=null && (roleMap.get(Constant.PROFILE_EDIT)==null?false:true)) {%>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="listUsers.jsp">Profile</a>
							</td>
							<% }else if(emp !=null && (roleMap.get(Constant.PROFILE_VIEW)==null?false:true)){ %>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="listUsers.jsp" >View</a>
							</td>
							<%} %>
							<% if(emp!=null && (roleMap.get(Constant.PHOTO_EDIT)==null?false:true)){ %>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="photo.jsp?userId=<%=emp.getId() %>" >Photo</a>
							</td>
							<%} else if(emp!=null && (roleMap.get(Constant.PHOTO_VIEW)==null?false:true)) {%>
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="photo.jsp?userId=<%=emp.getId() %>" >Photo</a>
							</td>
							<%} %>
							
							<td class="headerGap" onmouseover="this.className='headerLinkMOBlock'" onmouseout="this.className='headerLinkBlock'" align="center">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="logout.jsp" >Log out</a>
							</td>
							<!-- <td class="headerGap">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="services.jsp" target="contents">Services</a>
							</td>
							<td class="headerGap">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="aboutus.jsp" target="contents">About Us</a>
							</td>
							<td class="headerGap">
								<a class="headerLink" onmouseout="this.className='headerLink'"
								onmouseover="this.className='headerLinkMO'" href="contactus.jsp" target="contents">Contact Us</a>
							</td>
							 -->
						</tr>
					</table>
				</td>
			</tr>
		</table>
		
  <div id="container">
    <div id="gallery" class="ad-gallery">
      <div class="ad-image-wrapper">
      </div>
      <div class="ad-controls">
      </div>
      <div class="ad-nav">
        <div class="ad-thumbs">
          <ul class="ad-thumb-list">
          
          <%
          for(PhotoInfo info:list){
       	  %>
       	  <li>
              <a href="<%=PropertyReader.getValue("photoAlbumPath",request)%><%=info.getPhotoPath()%>">
                <img src="<%=PropertyReader.getValue("photoAlbumPath",request)%><%=info.getPhotoPath().replace("/","/thumbsmall_")%>" class="image0">
              </a>
            </li>
       	  
       	  <%
          }
          
          %>
          
          
          </ul>
        </div>
      </div>
    </div>

  </div>
  </div>
  </div>
</body>
</html>