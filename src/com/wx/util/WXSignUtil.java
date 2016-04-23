package com.wx.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
/*
 *加密解密
 */
public class WXSignUtil {
	
	private static String token="ershoupiao";
	/**
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		
		String[] paramArr = new String[]{token,timestamp,nonce};
		Arrays.sort(paramArr);
	
		String content=paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
		
		String ciphertext= null;
		
		try{
			MessageDigest md=MessageDigest.getInstance("SHA-1");
			
			byte[] digest = md.digest(content.toString().getBytes());
			
			ciphertext= byteToStr(digest);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		
		
		return ciphertext != null?ciphertext.equals(signature.toUpperCase()):false;
	}
	
	private static String byteToStr(byte[] byteArray){
		String strDigest="";
		for(int i=0;i<byteArray.length;i++){
			strDigest +=byteToHexStr(byteArray[i]);
		}
		
		return strDigest;
	}
	private static String byteToHexStr(byte mByte){
		char [] Digit={'0','1','2','3','4','5','6','7','8','9',
				'A','B','C','D','E','F'};
		
		char[] tempArr = new char[2];
		tempArr[0]=Digit[(mByte>>>4) & 0x0F];
		tempArr[1]=Digit[mByte & 0x0F];
		
		String s= new String(tempArr);
		
		return s;
				
		
	}

}