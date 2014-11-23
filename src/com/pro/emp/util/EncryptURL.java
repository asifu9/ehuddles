package com.pro.emp.util;

/**
 * Using JCE (Java Cryptography Entension) which is integerated
 * with the SDK since (J2SE) version 1.4.0.
 *
 * This is an example of encryption and decryption of a string
 * using symmetric keys. In this case, the same key is used
 * for both encryption and decryption.
 *
 * The algorithm used to generate the key is a variant of DES
 * (The Data Encryption Standard). DES is a popular symmetric
 * block algorithm for data encryption, but because the the key
 * space is relatively small, brute force attacks are possible.
 *
 * Since the DES algorithm is out of date, DESede, a triple DES
 * variant, can be used which increases the key space and helps
 * prevent brute force attacks.
 *
 * Note: AES (The Advanced Encryption Standard) has now replaced
 * DES.   The National Institute of Standards and Technology (NIST)
 * chose "Rijndael" as AES's implementing algorithm. AES well be
 * seen later when examining another "provider".
 */

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.*;
public class EncryptURL
{

    private static final String ALGO = "AES";
   private static final byte[] keyValue = 
       new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't',
'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
   private static Key key=null;
   private EncryptURL obj=null;
   /*
    * Lots of exceptions can be thrown here.  I've chosen
    * not to deal with them and just let the application
    * fail.  These exceptions are:
    *                              javax.crypto.IllegalBlockException
    *                              javax.crypto.BadPaddingException
    *                              java.securty.InvalidKeyException
    */
   private EncryptURL() throws Exception{
	   
	  key = new SecretKeySpec(keyValue, ALGO);
      
   }
   
   public EncryptURL getInstance(){
	   if(obj==null){
		   try {
			obj=new EncryptURL();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   return obj;
   }
   public static void main(String[] args) throws Exception
   {
      // This statement is not needed since the SunJCE is
      // (statically) installed as of SDK 1.4.  Do this to
      // dynamically install another provider.

     

      // Generate a secret key for a symmetric algorithm and
      // create a Cipher instance. DESede key size is always
      // 168 bits. Other algorithms, like "blowfish", allow
      // for variable lenght keys.

      

      // Store the string as an array of bytes.  You should
      // specify the encoding method for consistent encoding
      // and decoding across different platforms.

      

      // Reinitialize the cipher an decrypt the byte array

    

   }
   
   public static String encrypt(String Data) throws Exception {
       Cipher c = Cipher.getInstance(ALGO);
       c.init(Cipher.ENCRYPT_MODE, key);
       byte[] encVal = c.doFinal(Data.getBytes());
       String encryptedValue = new BASE64Encoder().encode(encVal);
       return encryptedValue;
   }
   public static String decrypt(String encryptedData) throws Exception {
       Cipher c = Cipher.getInstance(ALGO);
       c.init(Cipher.DECRYPT_MODE, key);
       byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
       byte[] decValue = c.doFinal(decordedValue);
       String decryptedValue = new String(decValue);
       return decryptedValue;
   }
   
 
}
/*
Before encryption: I sure love working with the JCE.
After encryption: ?¶b??§?a?P?^2'???U-Y?k?Xis?M5E?????1f?8?
After decryption: I sure love working with the JCE.
*/