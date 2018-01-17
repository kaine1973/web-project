package org.util;

import com.google.gson.Gson;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public final class StringUtil {

	/**
	 * 判断字符串是否为null
	 *   "" --- false 
	 *   null ---> false
	 *   " abf" --> ture
	 *   
	 * @param str
	 * @return
	 */
	public static boolean isNullorEmpty(final String str) {

		if (null == str) {
			return true;
		}else {
			if ("".equals(str.trim())) {
				return true;
			} else {
				return false;
			}
		}
	}
	public static String firstChar2Upper(String str){
		return str.toUpperCase().charAt(0)+str.substring(1);
	}
	/**
	 * md5加密
	 * @param str
	 * @return
	 */
	public static String encypt(String str) {
		String s = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(str.getBytes());
			s = bytesToHex(digest);

		} catch (NoSuchAlgorithmException e) {
			System.out.println("加密字符异常"+e.getMessage()+e.getStackTrace());
		}
		return s;
	}

	public static String toJson(List list){
		String json = "";
		Gson gson = new Gson();
		json = gson.toJson(list);
		return json;
	}
	public static String bytesToHex(byte[] bytes) {
		StringBuffer hexStr = new StringBuffer();
		int num;
		for (int i = 0; i < bytes.length; i++) {
			num = bytes[i];
			if(num < 0) {
				num += 256;
			}
			if(num < 16){
				hexStr.append("0");
			}
			hexStr.append(Integer.toHexString(num));
		}
		return hexStr.toString().toUpperCase();
	}
}
