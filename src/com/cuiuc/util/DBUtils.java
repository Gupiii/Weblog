package com.cuiuc.util;
/*
 * JDBC工具类
 * 1、连接数据库
 * 2、关闭资源
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtils {
	static String driverName="com.mysql.jdbc.Driver";
	static String url="jdbc:mysql://localhost:3306/db_blog3?useUnicode=true&characterEncoding=utf8";
	static String userName="root";
	static String password="root";
	//连接数据库
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName(driverName);
		 conn=DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	//关闭资源
	public static void myClose(Connection conn,PreparedStatement pst) {
		try {
			if(pst!=null) {pst.close();}
			if(conn!=null) {conn.close();}
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	public static void myClose(Connection conn,PreparedStatement pst,ResultSet rs) {
		try {
			if(rs!=null) {rs.close();}
			if(pst!=null) {pst.close();}
			if(conn!=null) {conn.close();}
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
}
