package com.caps;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
public class JavaMD5Hash {
	private static Scanner in;
	public static void main(String[] args) {
		System.out.println("enter your text");
		in = new Scanner(System.in);
		String text=in.nextLine();
		System.out.println(md5(text));
		
		
	}
	public static String md5(String input) {
		String md5 = null;
		if(null == input) return null;
		try {
			//Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");
			//Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());
			//Converts message digest value in base 16 (hex) 
			md5 = new BigInteger(1, digest.digest()).toString(16);
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}
}