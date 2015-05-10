

package com.tcs.ilp.telecomstore.module3.controller;

import java.util.Scanner;

/**
 * @author 225068
 * 
 */

/*
   Modified by : Arnabi Das and Sravanti Adepu
   Reason : To implement password encription
   Date : 7 March,2012
 */
public class CryptoUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i=0; i<5; i++) {
			System.out.print("Provide Password: ");
			Scanner scanner = new Scanner(System.in);
			String password = scanner.next();
			
			try {
				System.out.println("Encrypted Password: " + encryptPassword(password));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

	public static String encryptPassword(String password) throws Exception {
		String encryptedPassword = byteArrayToHexString(CryptoUtils.computeHash(password));
		return encryptedPassword;
	}
	
	
	public static byte[] computeHash(String x) throws Exception {
		java.security.MessageDigest d = null;
		d = java.security.MessageDigest.getInstance("SHA-1");
		d.reset();
		d.update(x.getBytes());
		return d.digest();
	}

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

}

