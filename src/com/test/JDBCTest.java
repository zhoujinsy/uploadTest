package com.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JDBCTest {
	public static final String PROPERTYNAME="jdbc";
	public static final String DRIVERCLASSNAME;
	public static final String URL;
	public static final String USER;
	public static final String PASSWORD;
	
	//1、加载配置文件（驱动DRIVERCLASSNAME，URL,USER,PASSWORD	）
	static{
		//1、加载属性配置文件jdbc.properties
		ResourceBundle rb=ResourceBundle.getBundle(PROPERTYNAME);
		//2、属性名称初始化
		DRIVERCLASSNAME=rb.getString("DRIVERCLASSNAME").trim();
		URL=rb.getString("URL").trim();
		USER=rb.getString("USER").trim();
		PASSWORD=rb.getString("PASSWORD").trim();
		
	}
	static{
		//2 加载驱动
		try {
			Class.forName(DRIVERCLASSNAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		//3、建立连接
		Connection con= DriverManager.getConnection(URL, USER, PASSWORD);
		//4、获取statement
	/*	Statement st = con.createStatement();
		//5、拼sql
		String querySql="select * from user";
		//6、执行sql查询，获取返回结果
		ResultSet rs = st.executeQuery(querySql);
		//7、关闭资源
*/		
		PreparedStatement ps = con.prepareStatement("select * from user where sex=?");
		ps.setString(1, "男");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			System.out.println("ID="+rs.getString("id")+","+
				"EMAIL="+rs.getString("email")+","+
				"USERNAME="+rs.getString("username")+","+
				"PASSWORD="+rs.getString("password")+","+
				"SEX="+rs.getString("sex")
					);
		}
		
		rs.close();
		ps.close();
		con.close();
	}

}
