package com.pro.emp.util;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.*;
public class SquareThumb {
	 private JPanel getContent(BufferedImage image) {
	        JPanel panel = new JPanel(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(5,5,5,5);
	        gbc.weightx = 1.0;
	     //   BufferedImage bufferedImage= ImageIcon(image)), gbc);
	        BufferedImage bufferedImage=getCropThumb(image);
	      //  panel.add(new JLabel(new ImageIcon(getCropThumb(image))), gbc);
	        Graphics g = bufferedImage.createGraphics();
	        g.drawImage(image, 0, 0, null);
	        g.dispose();

	        try {
				ImageIO.write(bufferedImage, "png", new File("c:\\a3.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return panel;
	    }
	 
	    public static BufferedImage getFillThumb(BufferedImage src,int w,int h) throws IOException {
	        int type = BufferedImage.TYPE_INT_RGB;
	                   // src.getType();
	        BufferedImage dest = new BufferedImage(w, h, type);
	        Graphics2D g2 = dest.createGraphics();
	        int iw = src.getWidth();
	        int ih = src.getHeight();
	        double xScale = (double)w/iw;
	        double yScale = (double)h/ih;
	        double scale = Math.max(xScale, yScale);
	        double x = (w - scale*iw)/2;
	        double y = (h - scale*ih)/2;
	        AffineTransform at = AffineTransform.getTranslateInstance(x, 0);
	        at.scale(scale, scale);
	        g2.drawRenderedImage(src, at);
//	        ImageIO.write(dest, "png", new File("c:\\sssssssss.png"));
//	        g2.dispose();
	        return dest;
	    }
	 
	    public static BufferedImage getCropThumb(BufferedImage src) {
	        int w = 200;
	        int h = 200;
	        int type = BufferedImage.TYPE_INT_RGB;
	                   // src.getType();
	        BufferedImage dest = new BufferedImage(w, h, type);
	        Graphics2D g2 = dest.createGraphics();
	        int iw = src.getWidth();
	        int ih = src.getHeight();
	        System.out.println(" iw = " +iw+ " :  ih = " + ih);
	        double x = (iw - w)/2.0;
	        double y = (ih - h)/2.0;
	        System.out.println(" x = " + x + " :  y = " + y);
	        AffineTransform at = AffineTransform.getTranslateInstance(-x, -y);
	        g2.drawRenderedImage(src, at);
	        g2.dispose();
	        return dest;
	    }
	 
	    public static void createSquareImage(File input,String outputs,int w,int h,String domain) throws IOException {
	        BufferedImage image = ImageIO.read(input);
	        BufferedImage bufferedImage=getFillThumb(image,w,h);
		      //  panel.add(new JLabel(new ImageIcon(getCropThumb(image))), gbc);
//		        Graphics g = bufferedImage.createGraphics();
//		        g.drawImage(image, w,h, null);
//		      
//		        g.dispose();
	        ImageWriter writer = null;
	        FileImageOutputStream output = null;
	        try {
	            writer = ImageIO.getImageWritersByFormatName("jpeg").next();
	            ImageWriteParam param = writer.getDefaultWriteParam();
	            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	            param.setCompressionQuality(1f);
	            output = new FileImageOutputStream(new File(outputs));
	            writer.setOutput(output);
	            IIOImage iioImage = new IIOImage(bufferedImage, null, null);
	            writer.write(null, iioImage, param);
	        } catch (IOException ex) {
	            throw ex;
	        } finally {
	            if (writer != null) writer.dispose();
	            if (output != null) output.close();
	        }
		        
//		        Graphics2D g2 = bufferedImage.createGraphics();
//		        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//		        g2.drawImage(bufferedImage, 200, 200, w, h, null);
//		        g2.dispose();
		        
//
//		        try {
//					//ImageIO.write(bufferedImage, domain, new File(output));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
	    }
	    
	   public static void main(String arg[]){
		   try {
			createSquareImage(new File("c:\\dddd.jpg"),"c:\\tttt3.png",200,200,"jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
}
