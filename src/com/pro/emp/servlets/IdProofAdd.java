package com.pro.emp.servlets;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



import com.pro.emp.CassandraDB;
import com.pro.emp.ConnectionPool;
import com.pro.emp.PropertyReader;
import com.pro.emp.dao.EmpIdProofDAO;
import com.pro.emp.domain.EmpIdproofInfo;

/**
 * Servlet implementation class IdProofAdd
 */
public class IdProofAdd extends HttpServlet {/*
	private static final long serialVersionUID = 1L;
    public static String flow="";
    public static String actualpath="";
    public static String destPath="";
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public IdProofAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		EmpIdproofInfo proof=new EmpIdproofInfo();
		proof = uploadPhoto(request);
		String urlApp ="";
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Connection con =CassandraDB.getCassConnection();
		int res =EmpIdProofDAO.createEmpIdproofInfo(proof, proof.getEmpId(), con);
		
		if(res==1){
			urlApp= "&message1=&message2=Successfully saved..";
		}else if(res==2){
			urlApp = "&message1=&message2=This file already uploaded, upload other file..";
		}else if(res==3){
			urlApp ="&message1=&message2= Exception while saving to DB..";
		}
		CassandraDB.freeConnection(con);
		
		// create thumb nail
		//com.sanah.emp.Thumbnail tn=new com.sanah.emp.Thumbnail();
		//String ress=tn.createThumbnail(actualpath, destPath, Integer.parseInt(PropertyReader.getValue("thumbSize")));
		//System.out.println(" result " + ress);
		//System.out.println("{\"error\":\""+res+"\",\"msg\":\""+res+"\"}");
		out.print("{\"error\":"+res+",\"msg\":"+res+"}");
		//
		//echo "{";
		//echo				"error: '" . $error . "',\n";
		//echo				"msg: '" . $msg . "'\n";
		//echo "}";
		//
		//out.flush();
		
//		String url="/editOtherInfo.jsp?empId="+proof.getEmpId()+urlApp;
//		if(flow.equals("creation")){
//			url="/createOtherInfo.jsp?empid="+proof.getEmpId()+urlApp;
//		}
//		RequestDispatcher rd =request.getRequestDispatcher(url);
//		rd.forward(request, response);
		
	}
	

	public EmpIdproofInfo uploadPhoto(HttpServletRequest request){
		EmpIdproofInfo proof=new EmpIdproofInfo();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		@SuppressWarnings("rawtypes")
		List items = null;
		String filepath="";
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		@SuppressWarnings("rawtypes")
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			if (item.isFormField()){
			
				String name = item.getFieldName();
				if(name.equalsIgnoreCase("description")){
					proof.setDescription(item.getString());
				}else if(name.equalsIgnoreCase("documentType")){
					proof.setType(item.getString());
				}else if(name.equalsIgnoreCase("empid")){
					proof.setEmpId(item.getString());
				}else if(name.equalsIgnoreCase("flow")){
					flow = item.getString();
				}

			} else {
				try {
					String itemName = item.getName();
					actualpath="";
					destPath="";
					String reg = "[.*]";
					String replacingtext = "";
					Pattern pattern = Pattern.compile(reg);
					Matcher matcher = pattern.matcher(itemName);
					StringBuffer buffer = new StringBuffer();

					while (matcher.find()) {
						matcher.appendReplacement(buffer, replacingtext);
					}
					int IndexOf = itemName.indexOf("."); 
					String domainName = itemName.substring(IndexOf);


					String finalimage = buffer.toString()+"_"+domainName;
					String finalimageThum = buffer.toString()+"thumb_"+domainName;
					File savedFile = new File(PropertyReader.getValue("proofSavepath",request)+"Docs\\"+finalimage);
					
					//Image inImage = Toolkit.getDefaultToolkit().getImage(item.getFieldName());
					if(inImage.getWidth(null) == -1 || inImage.getHeight(null) == -1)
					{
						return "Error loading file: \"" + inFilename + "\"";
					}
					filepath =PropertyReader.getValue("proofDisplayPath",request)+ finalimage;
					//String thumnaiPath= "C:/Documents and Settings/Asif/workspace/sanahempinfo/WebContent/"+"Docs\\"+finalimageThum;
					String thumnaiPath= PropertyReader.getValue("proofSavepath",request)+"Docs\\"+finalimageThum;
					
					proof.setAttachment(filepath);
					proof.setAttachmentThumbnail(PropertyReader.getValue("proofDisplayPath",request)+finalimageThum);
					item.write(savedFile);
					actualpath=PropertyReader.getValue("proofSavepath",request)+"Docs/"+finalimage;
					destPath = thumnaiPath;
					CreateThumbNail(savedFile,thumnaiPath);
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		return proof;
	}
	
	
	   public static byte[] createThumbnail( byte[] orig, int maxDim) {

		    try {
		        ImageIcon imageIcon = new ImageIcon(orig);
		         Image inImage = imageIcon.getImage();
		        double scale = (double) maxDim / (double) inImage.getWidth(null);

		        int scaledW = (int) (scale * inImage.getWidth(null));
		        int scaledH = (int) (scale * inImage.getHeight(null));

		        BufferedImage outImage = new BufferedImage(scaledW, scaledH, BufferedImage.TYPE_INT_RGB);
		        AffineTransform tx = new AffineTransform();

		        if (scale < 1.0d) {
		            tx.scale(scale, scale);
		        }

		        Graphics2D g2d = outImage.createGraphics();
		        g2d.drawImage(inImage, tx, null);
		        g2d.dispose();  


		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        ImageIO.write(outImage, "JPG", baos);
		        byte[] bytesOut = baos.toByteArray();

		        return bytesOut;
		    } catch (IOException e) {
		        System.out.println("Erro: " + e.getMessage());
		        e.printStackTrace();
		    }
		    return null;

		}
	   
	   
	   public static void CreateThumbNail(File file,String thumbUrl) {
	        try{
	    BufferedImage originalImage = ImageIO.read(file);
	    int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
	          int  IMG_HEIGHT = 200;
	          int IMG_WIDTH =  200;
	    BufferedImage resizeImageJpg = resizeImage(originalImage, type, IMG_HEIGHT,IMG_WIDTH);
	    ImageIO.write(resizeImageJpg, "jpg", new File(thumbUrl));
	            originalImage.flush();
	            resizeImageJpg.flush();
	            System.gc();

	}catch(IOException e){
	    System.out.println(e.getMessage());
	            System.out.println("Not Created:"+thumbUrl);
	}
	    }
	private static BufferedImage resizeImage(BufferedImage originalImage, int type,int IMG_HEIGHT,int IMG_WIDTH){
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();
	    System.gc();
	return resizedImage;
	}

*/}
