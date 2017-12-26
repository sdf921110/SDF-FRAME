package com.sdf.codeGeneration.code.tool.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbConn {

	// 定义一个连接对象
	private Connection conn = null;

	// oracle
	// private String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	// private String driver = "oracle.jdbc.driver.OracleDriver";
	// private String userName = "test";
	// private String passWord = "test";

	// mysql
//	private String url = "jdbc:mysql://139.196.36.206:3306/test?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
	private String url = "jdbc:mysql://localhost:3306/seashop?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String passWord = "root";

	// 加载数据库连接驱动
	public Connection getConnection() {
		try {
			Class.forName(driver);

			Properties props = new Properties();
			props.put("remarksReporting", "true");// 数据字段的注释支持
			props.put("user", userName);
			props.put("password", passWord);

			conn = DriverManager.getConnection(url, props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭数据库
	public void closeALL(Connection conn, Statement st, ResultSet rs, PreparedStatement pst) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (pst != null){
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws  Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Properties props = new Properties();
		// 数据字段的注释支持
		props.put("remarksReporting", "true");
		props.put("user", "root");
		props.put("password", "root");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rap_db?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull", props);

		System.out.println(conn);


		String sql = "insert into students (Name,Sex,Age) values(?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	} 

}
