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
 * ��������·����������
 * @author 59112
 *
 */
public class URLPathUtil {
	/*** ��¼·��*/
	public static final String LOGIN="login";
	/*** ע��·��*/
	public static final String REGISTER="register";
	/***��ѯ����·��*/
	public static final String FIND_ALL_CAR="findallcar";
	/***��ѯ���޼�¼·��*/
	public static final String FIND_ALL_RECORD="findallrecord";
	/***��ѯƷ��·��*/
	public static final String FIND_ALL_BRAND="findallbrand";
	/***����Ʒ�Ʊ�Ų�ѯ·��*/
	public static final String FIND_ONE_BRAND="findonebrand";
	/***��ѯ����·��*/
	public static final String FIND_ALL_CATEGORY="findallcategory";
	/***�������ͱ�Ų�ѯ·��*/
	public static final String FIND_ONE_CATEGORY="findonecategory";
	/***�⳵·��*/
	public static final String USER_LEASE_CAR="userleasecar";
	/***����·��*/
	public static final String USER_RETURN_CAR="userreturncar";
	/***�������·��*/
	public static final String ADD_CAR="addcar";
	/***�޸�����·��*/
	public static final String UPDATE_CAR="updatecar";
	/***��ѯָ������·��*/
	public static final String FIND_ONE_CAR="findonecar";
	/***��ѯָ���û����޼�¼*/
	public static final String FIND_ONE_USER="findoneuser";
	/***����*/
	public static final String SORT="sort";
	
	/**
	 * json����
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
	 * ���ݿ�����·��
	 */
	private static String driver;
	private static String url;
	private static String username;
	private static String pwd;
	static{
		InputStream in = URLPathUtil.class.getClassLoader().getResourceAsStream("db.properties");
		Properties prop = new Properties();
		//���������ļ�
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
	 * ��ȡ����
	 */
	public static Connection getCon(){
		Connection con =null;
		try {//ע������
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
	 * �ر��ͷ���Դ
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
