package com.sbh.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class ConfigUtil {
	
	static Map<String,Object> map = new HashMap<String,Object>();
	
	private ConfigUtil(){
		map= readProperties();
	}
	

	/**
	 * 内部类
	 * @author msh
	 *
	 */
	private static class ConfigUtilHandler{
		
		private static ConfigUtil  config = new ConfigUtil();
	}
	
	public static ConfigUtil getConfigInstance(){
		
		return ConfigUtilHandler.config;
		
	}
	
	@SuppressWarnings("unused")
	private static Map<String,Object> readProperties(){
		Properties p = new Properties();
		String path =  ConfigUtil.class.getClassLoader().getResource("config/config.properties").getPath();
        try {
    		InputStream in = new BufferedInputStream(new FileInputStream(path));
    		

        	p.load(in);

        	Enumeration e = p.propertyNames();
		   while(e.hasMoreElements()){
			   String key = (String) e.nextElement();
			   String value= p.getProperty(key);
			   map.put(key, value);
		   }
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
        return map;
	}
	
	

	public static Map<String, Object> getMap() {
		return map;
	}

	public static void setMap(Map<String, Object> map) {
		ConfigUtil.map = map;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
      ConfigUtil u =  ConfigUtil.getConfigInstance();
     String value = (String) u.map.get("imgUrl");
     System.out.println(value);
     
     
     
     Map<String,Object> map = new HashMap<String,Object>();
 	ConfigUtil config = ConfigUtil.getConfigInstance();
 	Map<String,Object> configMap = config.getMap();
 	String url = (String) configMap.get("imgUrl");
 	String img = (String) configMap.get("imgNames");
 	String [] imgInfos = new String(img.getBytes("ISO8859-1"),"UTF-8").split("\\|");
 	//List<ImgInfo> listArr = new ArrayList<ImgInfo>();
 	
 	for(int i =0;i<imgInfos.length;i++){
 	     String info =imgInfos[i];
 	/*	if(i==0){
 			info=imgInfos[0].substring(1);	
 		}
 		if(i==imgInfos.length-1){
 			info = info.substring(0, info.length()-1);
 		}*/
 		String [] imgInfo = info.split(",");
 		
  		String imgUrl="http://"+url+"/"+imgInfo[0];
 		String imgText=imgInfo[1];
 		System.out.println(imgUrl);
 		System.out.println(imgText);
 	   // ImgInfo ii = new ImgInfo();
 	  //  ii.text =imgText;
 	   // ii.url = imgUrl;
 	    //listArr.add(ii);
 	}
	}

}
