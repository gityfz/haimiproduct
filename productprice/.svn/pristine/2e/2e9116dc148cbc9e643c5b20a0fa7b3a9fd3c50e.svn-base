package com.intelligence.autodev.dao.impl;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;

public class DBConnection{

		private static final String USER="root";
		private static final String PASSWORD="111111";
		private static final String URL="jdbc:mysql://127.0.0.1:3306/devsys?&useUnicode=true&zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8";

		static {
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("加载数据库驱动失败");
				e.printStackTrace();
			}
		}
		
		public static Connection getConnection() throws SQLException{
				System.out.println("连接数据库开始");
				return DriverManager.getConnection(URL,USER,PASSWORD);

}
			
		public static void close(Connection conn,PreparedStatement ps,ResultSet rs) throws SQLException{
			
				if(rs!=null){
					rs.close();
				}if(ps!=null){
					ps.close();
				}if(conn!=null){
					conn.close();
				}
		}
		
		public static void main(String[] args){
			
			try {
				DBConnection.getConnection();
				System.out.println("连接数据库");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

}