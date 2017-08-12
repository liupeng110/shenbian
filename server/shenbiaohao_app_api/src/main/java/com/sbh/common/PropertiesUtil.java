package com.sbh.common;
import java.io.IOException;
import java.util.Properties;


public class PropertiesUtil {
	
	private Properties properties = new Properties();
	/**
	 * 资源文件构造函数,1个参数
	 * @param prop
	 */
	public PropertiesUtil(String prop) {
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(prop));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 资源文件构造函数,2个参数
	 * @param prop
	 */
	public PropertiesUtil(String path, String prop) {
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(prop));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据key，获取value
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * @return 返回数据库名称
	 */
	public static String getDataBaseName(){
		PropertiesUtil propertiesUtil=new PropertiesUtil("/hibernate.properties");
		return propertiesUtil.getProperty("hibernate.datatabase_name");
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
