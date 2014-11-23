package com.pro.emp.servlets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.coobird.thumbnailator.Thumbnails;

import com.datastax.driver.core.Session;
import com.pro.cache.CacheRecords;
import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.PropertyReader;
import com.pro.emp.Session_control;
import com.pro.emp.Util;
import com.pro.emp.dao.EmpInfoDAO;
import com.pro.emp.domain.EmpBasicInfo;
import com.pro.emp.domain.EmpInfo;

/**
 * Servlet implementation class ImageCrop
 */
public class ImageCrop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageCrop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = req.getSession(true);
		String empid="";
		if(session.getAttribute("editUser")!=null)
			empid = session.getAttribute("editUser").toString();
		
		@SuppressWarnings("unused")
		String imageName= req.getParameter("imageName").toString();
		int t=Integer.parseInt(req.getParameter("t"));
		int l=Integer.parseInt(req.getParameter("l"));
		int w=Integer.parseInt(req.getParameter("w"));
		int h=Integer.parseInt(req.getParameter("h"));
		String acutlaImage=req.getParameter("i");
		String imagePath=getServletContext().getRealPath("/")+req.getParameter("i");
		
		BufferedImage outImage=ImageIO.read(new File(imagePath));
		BufferedImage cropped=outImage.getSubimage(l, t, w, h);
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		ImageIO.write(cropped,req.getParameter("f"), out);
		File nFile=new File(PropertyReader.getValue("photoSavepath1")+"Photo\\"+empid+"_thumbNail."+req.getParameter("f"));
		ImageIO.write(cropped,req.getParameter("f"), 
				nFile); // save the file with crop dimensions

		
		File SmallFile=new File(PropertyReader.getValue("photoSavepath1")+"Photo\\"+empid+"_thumbNail_Icon."+req.getParameter("f"));
		Thumbnails.of(nFile).size(50,50).toFile(SmallFile);
		
		String path =  empid + "_thumbNail."+req.getParameter("f");
		String iconPath = empid + "_thumbNail_icon."+req.getParameter("f");
		// save the path in db

		Session con = CassandraDB.getCassConnection();
		try {
	//	EmpInfo info = EmpInfoDAO.getEmpById(con, empid);
		//.info.setImagePath("Photo/"+path);
		System.out.println(" ok here i am see the difference ----------------------- " +"Photo/"+path);
		EmpInfo e=new EmpInfo();
		e.setId(empid);
		EmpBasicInfo info= CacheRecords.getInstance().getCacheData(empid);
		e.setCreationDate(Util.convertDateToTimestamp(info.getCreationdate()));
		e.setImagePath("Photo/"+path);
		EmpInfoDAO.updateImagePath(con, e);
		Session_control.setSession_(req,empid);
		//double[] dataa = Util.resize_image(nFile,20,20,400,400,200,200);
		//int wid =(int)(dataa[1]);
		//int hei = (int)(dataa[0]);
		//Util.CreateThumbNail(nFile, PropertyReader.getValue("photoSavepath")+"Photo\\"+iconPath,wid,hei);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			CassandraDB.freeConnection(con);
		}
		// end of save path in db
		//res.setContentType("image/jpg");
		ServletOutputStream wrt=res.getOutputStream();
		wrt.write(out.toByteArray());
		wrt.flush();
		wrt.close();
		
		String url="/ImageCrop.jsp?status=done";
	
	
	RequestDispatcher rd =req.getRequestDispatcher(url);
	rd.forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
