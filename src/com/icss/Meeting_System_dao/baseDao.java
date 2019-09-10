package com.icss.Meeting_System_dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*****************************
*@类名     baseDao.java
*@作者      沐沐
*@日期    2018年7月17日-上午9:32:35
*@版本    V1.0
*@描述    创建数据库连接包
*             如果在数据库中插入数据出现问号，在连接数据库的时候插入“useUnicode=true&characterEncoding=utf-8”
******************************/
public class baseDao {
	private static String DRIVER;
	private static String URL;
	private static String USER;
	private static String PASSWORD;
	protected static Connection connection;
	static{
		Properties prop = new Properties();
		InputStream is = baseDao.class.getResourceAsStream("jdbcinfor.properties");
			try {
				prop.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			DRIVER = prop.getProperty("mysql.driver");
			URL = prop.getProperty("mysql.url");
			USER = prop.getProperty("mysql.user");
			PASSWORD = prop.getProperty("mysql.password");
	}
	/**
	 *@作者  沐沐
	 *@日期  2018年7月17日-上午10:07:18
	 *@描述
	 */
	public static Connection GetConnection(){
		try {
			Class.forName(DRIVER);
			try {
				connection=DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("连接成功");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public void closeConnection() {
		try {
			if(connection != null) {
			connection.close();
			connection = null;//将连接置空
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 *@作者  沐沐
	 *@日期  2018年7月17日-上午10:07:41
	 *@描述  开启事务
	 */
    public void beginTrans() {
    connection=baseDao.GetConnection();
    try {
		connection.setAutoCommit(false);
	} catch (SQLException e) {
		e.printStackTrace();
	}
    }
    /**
     *@作者  沐沐
     *@日期  2018年7月17日-上午10:07:30
     *@描述  事务提交
     */
	public void commit() {
		if(connection != null) {
			try {
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月17日-上午10:07:10
	 *@描述  事务回滚
	 */
	public void rollBack() {
		if(connection != null) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
