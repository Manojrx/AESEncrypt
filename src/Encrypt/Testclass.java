package Encrypt;

import java.io.File;

public class Testclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String key = "lB@1c20c684ngkii";
	        File orgin = new File("D:\\Sample File for UPload\\sampledocs-50mb-xls-file.csv");
	        File encryptedFile = new File("document.encrypted");
	        File decryptedFile = new File("document.xls");
	         
	        try {
	        	EncryptLogic encryptLogic = new EncryptLogic();
				encryptLogic.encrypt(key, orgin, encryptedFile);
	        	EncryptLogic.doCrypto(key, encryptedFile, decryptedFile);
	        } catch (EncryptException ex) {
	            System.out.println(ex.getMessage());
	            ex.printStackTrace();
	        }
	}

}
