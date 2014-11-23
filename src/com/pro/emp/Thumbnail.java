package com.pro.emp;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author anand
 */
public class Thumbnail {

   /**
    * @param args
    */
   public static void createThumb(File file,String output,int withd,int height) {
      
    // Specify the input image source file location.
      try {
         FileInputStream fis = new FileInputStream(file);
         InputStream bis = new BufferedInputStream(fis);
         FileOutputStream fos = null;
         Image image = (Image) ImageIO.read(bis);
         int thumbWidth = withd;// Specify image width in px
         int thumbHeight = height;// Specify image height in px
         
         int imageWidth = image.getWidth(null);// get image Width
         int imageHeight = image.getHeight(null);// get image Height
         
         double thumbRatio = (double) thumbWidth / (double) thumbHeight;
         double imageRatio = (double) imageWidth / (double) imageHeight;

         // This calculation is used to convert the image size according to the pixels mentioned above
         if (thumbRatio < imageRatio) {
            thumbHeight = (int) (thumbWidth / imageRatio);
         } else {
            thumbWidth = (int) (thumbHeight * imageRatio);
         }

         BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);

         Graphics2D graphics = thumbImage.createGraphics();
         graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
         graphics.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);

         ByteArrayOutputStream out = new ByteArrayOutputStream();
         JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
         System.out.println("Encoder" + encoder);
         JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);

         int quality = 300;
         quality = Math.max(0, Math.min(quality, 500));
         param.setQuality(0.99f, false);

         // output image type.
         String format = "jpg";

         encoder.setJPEGEncodeParam(param);
         encoder.encode(thumbImage);
         ImageIO.write(thumbImage, format, new       File(output));
      } catch (IOException ioExcep) {
         ioExcep.printStackTrace();
      } catch (Exception excep) {
         excep.printStackTrace();
      }
   }
   
   public static void main(String af[]){
	   String path = "c:\\photo for testing\\dddd.jpg";
	   String out =  "c:\\result4.jpg";
	
   }

}
