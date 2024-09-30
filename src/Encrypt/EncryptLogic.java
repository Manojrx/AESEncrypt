package Encrypt;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptLogic {
	 private static final String ALGORITHM = "AES";
	    private static final String TRANSFORMATION = "AES";
	 
	    public  void encrypt(String key, File inputFile, File outputFile)
	            throws EncryptException {
	        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
	    }
	 
	    public static void doCrypto(String key, File inputFile, File outputFile)
	            throws EncryptException {
	    	doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
	    }
	 
	    private static  void doCrypto(int cipherMode, String key, File inputFile,
	            File outputFile) throws EncryptException {
	        try {
// old 
//	            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
//	            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//	            cipher.init(cipherMode, secretKey);	 
	        	
	        	
	        	 byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	             IvParameterSpec ivSpec = new IvParameterSpec(iv);
	             SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	             KeySpec spec = new PBEKeySpec("lB@1c20c684ngkii".toCharArray(), "XGEvvGLXntvugatfsn".getBytes(), 65536, 256);
	             SecretKey tmp = factory.generateSecret(spec);
	             SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	             Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	             cipher.init(cipherMode,secretKey,ivSpec);
	            
	    
	             System.out.println("Encrypt");
	             
	            FileInputStream inputStream = new FileInputStream(inputFile);
	            
	            byte[] inputBytes = new byte[(int) inputFile.length()];
	            inputStream.read(inputBytes);
	            
	            byte[] buffer = new byte[2097152];      //Splits Into 2 Mb
				int bytesRead;
	            
	            byte[] outputBytes = cipher.doFinal(inputBytes);
	             
	            
				InputStream targetStream = new ByteArrayInputStream(outputBytes);
	            FileOutputStream outputStream = new FileOutputStream(outputFile);

				// Converting Bytes into Chunks List for Upload
				while ((bytesRead = targetStream.read(buffer)) != -1) {
					byte[] chunk = new byte[bytesRead];
					System.arraycopy(buffer, 0, chunk, 0, bytesRead);
		            outputStream.write(chunk);
				}				
	            outputStream.close();

	           // outputStream.write(outputBytes);
	             
	            inputStream.close();
	            outputStream.close();
	             
	        }catch (Exception e) {
	        	 throw new EncryptException("Error encrypting/decrypting file", e);
			}
	    }

	    

//	    private static  void doCryptodecrypt(int cipherMode, String key, File inputFile,
//	            File outputFile) throws EncryptException {
//	        try {
////	        	byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
////
////	            IvParameterSpec ivSpec = new IvParameterSpec(new byte[128/8]);
////	            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
////	            //System.out.println("secretkey"+secretKey.getAlgorithm());
////	            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
////	            cipher.init(cipherMode, secretKey);
////	             System.out.println("DEcrypr");
////	            FileInputStream inputStream = new FileInputStream(inputFile);
////	            byte[] inputBytes = new byte[(int) inputFile.length()];
////	            inputStream.read(inputBytes);
//	            
//	       	 byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//             IvParameterSpec ivSpec = new IvParameterSpec(iv);
//             SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//             KeySpec spec = new PBEKeySpec("lB@1c20c684ngkii".toCharArray(), "XGEvvGLXntvugatfsn".getBytes(), 65536, 256);
//             SecretKey tmp = factory.generateSecret(spec);
//             SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
//             Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//             cipher.init(cipherMode,secretKey,ivSpec);
//	            FileInputStream inputStream = new FileInputStream(inputFile);
//
//             byte[] inputBytes = new byte[(int) inputFile.length()];
//	            inputStream.read(inputBytes);
//	            byte[] outputBytes = cipher.doFinal(inputBytes);
//	             
//	            FileOutputStream outputStream = new FileOutputStream(outputFile);
//	            outputStream.write(outputBytes);
//	             
//	            inputStream.close();
//	            outputStream.close();
//	             
//	        }catch (Exception e) {
//	        	 throw new EncryptException("Error encrypting/decrypting file", e);
//			}
//	    }
	    
}
