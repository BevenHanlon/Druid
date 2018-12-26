package com.bodao.ehrms.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * 所有请求路径处理工具类
 * @author 59112
 *
 */
public class URLPathUtil {
	/*** 登录路径*/
	public static final String LOGIN="login";
	/*** 注册路径*/
	public static final String REGISTER="register";
	/***查询汽车路径*/
	public static final String FIND_ALL_CAR="findallcar";
	/***查询租赁记录路径*/
	public static final String FIND_ALL_RECORD="findallrecord";
	/***查询品牌路径*/
	public static final String FIND_ALL_BRAND="findallbrand";
	/***根据品牌编号查询路径*/
	public static final String FIND_ONE_BRAND="findonebrand";
	/***查询类型路径*/
	public static final String FIND_ALL_CATEGORY="findallcategory";
	/***根据类型编号查询路径*/
	public static final String FIND_ONE_CATEGORY="findonecategory";
	/***租车路径*/
	public static final String USER_LEASE_CAR="userleasecar";
	/***还车路径*/
	public static final String USER_RETURN_CAR="userreturncar";
	/***添加汽车路径*/
	public static final String ADD_CAR="addcar";
	/***修改汽车路径*/
	public static final String UPDATE_CAR="updatecar";
	/***查询指定汽车路径*/
	public static final String FIND_ONE_CAR="findonecar";
	/***查询指定用户租赁记录*/
	public static final String FIND_ONE_USER="findoneuser";
	/***排序*/
	public static final String SORT="sort";
	
	/**
	 * json解析
	 * @param url
	 * @return
	 */
	public static String getPath(String url){
		String[] paths = url.split("\\?");
		return paths[0];	
	}
	public static Map<String,String> getParams(String url){
		Map<String,String> map = new HashMap<String,String>();
		String[]  paths = url.split("\\?");
		if (paths.length==1) {
			return null;
		}else{
			String params = paths[1];
			String[] ps = params.split("&");
			
			for (String string : ps) {
				String[] kv =string.split("=");
				map.put(kv[0],kv[1]);
			}
			return map;
		}
	}
	/**
	 * 数据库连接路径
	 */
	private static String driver;
	private static String url;
	private static String username;
	private static String pwd;
	static{
		InputStream in = URLPathUtil.class.getClassLoader().getResourceAsStream("db.properties");
		Properties prop = new Properties();
		//加载配置文件
		try {
			prop.load(in);
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			pwd = prop.getProperty("pwd");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 获取连接
	 */
	public static Connection getCon(){
		Connection con =null;
		try {//注册驱动
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * 关闭释放资源
	 */
	public static void close(Connection con,PreparedStatement ps,ResultSet rs){
		try {
			if (con!=null) {
				con.close();
			}
			if (ps!=null) {
				ps.close();
			}
			if (rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
