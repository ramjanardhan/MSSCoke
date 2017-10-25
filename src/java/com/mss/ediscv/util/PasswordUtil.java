/*******************************************************************************
 *
 * Project : Miracle Supply Chain Visibility portal v2.0
 *
 * Package : com.mss.mscvp.util
 *
 * Date    : Mar 11, 2013 1:43:29 PM
 *
 * Author  : Nagireddy seerapu <nseerapu@miraclesoft.com>
 *
 * File    : PasswordUtility.java
 *

 * *****************************************************************************
 */
package com.mss.ediscv.util;
/*
 * This class is used for password Encryption
 */
public class PasswordUtil {
	/** Creates a new instance of EncriptDecriptPwd */
	public PasswordUtil() {
	}

	//START: Password Encryption method 
	public static String encryptPwd(String srcPwd) {

		// Converting String to array
		char asciiArr[] = srcPwd.toCharArray();

		// Finding length and converting it into ascii
		int encryASCIIArr[] = new int[srcPwd.length()];

		for (int i = 0; i < encryASCIIArr.length; i++) {
			int asciiChar = (int) asciiArr[i] + 3;
			int asciiCharMulBy3 = asciiChar * 3;
			encryASCIIArr[i] = asciiCharMulBy3;
		}

		String encryPwd = "";
		for (int j = 0; j < encryASCIIArr.length; j++) {
			encryPwd = encryPwd + "#" + encryASCIIArr[j];
		}

		return encryPwd;
	}//END: Password Encryption method

	//START: Password Decryption method
	public static String decryptPwd(String encryPwd) {

		String encryPwdArr[] = encryPwd.split("#");
		String decryptedPwd = "";
		
		for (int lk = 0; lk < encryPwdArr.length; lk++) {
			if (!encryPwdArr[lk].equalsIgnoreCase("")) {
				int asciiChar = Integer.parseInt(encryPwdArr[lk]);
				int asciiCharDivBy3 = asciiChar / 3;
				int asciiCharSubBy3 = asciiCharDivBy3 - 3;
				decryptedPwd = decryptedPwd + (char) asciiCharSubBy3;
			}
		}
		return decryptedPwd;
	}//END: Password Decryption method

public static void main(String args[]){
    
    System.out.println("Encrypt password-->"+encryptPwd("123"));
     System.out.println(decryptPwd("#219#168#165#156#216#213#216#213#219#168#165#156#216#213#216#213"));
	
}
	
}
