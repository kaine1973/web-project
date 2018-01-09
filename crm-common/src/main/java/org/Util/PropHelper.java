package org.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropHelper {
	private static Properties prop = null;
	static {
		try {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(DBConstant.DB_FILE_LOCATION);
		prop = new Properties();
		prop.load(is);
		} catch (IOException e) {
			System.out.println("配置文件异常"+e.getMessage()+e.getStackTrace());
		}
	}
	public static String getValue(String key) {
		if(!StringUtil.isNotEmptyOrNull(key)) {
			throw new RuntimeException("获取 配置文件中的值时，key 不能为 null  key：" + key);
		}else {
			return prop.getProperty(key);
		}
	}
}
