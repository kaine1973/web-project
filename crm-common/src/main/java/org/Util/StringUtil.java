package org.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	public static boolean isNotEmptyOrNull(final String str) {
		
		if (str != null && !"".equals(str)) {
			return true;
		}
		
		return false;
	}
	/**
	 * md5加密
	 * @param str
	 * @return
	 */
	public static String encypt(String str) {
		String md5 = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(str.getBytes());
			md5 = new String(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return md5;
	}
}
