package com.gy.Utill;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * JDBC访问MySQL数据库步骤:
 *   1.加载驱动（注册驱动是自动的，不需要程序员自己注册驱动）  Class.forName("com.mysql.jdbc.Driver");
 *   			    前提：是否已经添加驱动jar包
 *              mysql驱动jar:mysql-connector-java-XX.XX.XX
 *              oracle的驱动jar包:ojdbcXX.jar
 *   2.获取数据库连接           DriverManager.getConnection(url, username, password);
 *   3.创建语句
 *   4.执行sql语句
 *   5.处理结果集
 *   6.关闭资源
 */
public class JdbcUtil 
{
	private static Properties properties;
	//   将Connection 放入到ThreadLocal中 THreadLocal 线程的副本:保证一个事务中用同一个连接
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	private static DataSource bds = null;
	static{
		try {
			// 静态代码块，类加载完后就执行
			properties = new Properties();
			// 获取文件流
			InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("datasource.properties");
			// 加载配置文件
			properties.load(in);
			// 推荐配置方式实现连接池,  便于维护
			// 根据配置信息,从工厂中 直接获得数据源对象

			//apache 提供的dbcp连接池
			bds = BasicDataSourceFactory.createDataSource(properties);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			 // 当在静态初始化块中出现了异常的时候
			throw new ExceptionInInitializerError("初始化datasource.properties异常!");
		}
	}
	

	/**
	 * 获取数据库连接(推荐方式，便于维护)
	 * @return
	 * @throws Exception 
	 */
	public static Connection getConn() throws SQLException 
	{
		Connection conn  =  local.get();
		if(conn == null)
		{
			conn = bds.getConnection();
			local.set(conn);
		}
		return conn;
	}

	/**
	 * 封装PreparedStatement
	 * @param sql
	 * @return
	 */
	public static PreparedStatement getPreparedStatement(String sql) throws Exception
	{
		PreparedStatement ps = null;;
		try {
			ps = getConn().prepareStatement(sql);
		} catch (Exception e) {
			throw new Exception("获取PreparedStatement异常");
		}
		return ps;
	}
	
	/**
	 * 封装关闭资源方法
	 * 主要关闭顺序
	 */
    public static void  closeAll(Connection conn, Statement stmt, ResultSet rs)
    {
    	 
    	try 
		{
			if (rs != null)
			{
				rs.close();
			}
			
			rs = null;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
    	

    	try 
		{
			if (stmt != null)
			{
				stmt.close();
			}
			
			stmt = null;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
    	try 
		{
			if (conn != null)
			{
				conn.close();
				// 注意当conn关闭时，将local中移除变量
				local.remove();
			}			
			conn = null;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
    }

    
    /**
     * 单独关闭conn
     * 关闭conn统一在service层关闭
     */
    public static void close(){
		Connection con = local.get();
		if (con != null) {
			try {
				con.close();
				local.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    
    /**
     * 开启事务
     * @throws SQLException 
     */
    public static void beginTransiaction() throws SQLException{
    	getConn().setAutoCommit(false);
    }
    /**
     * 事务提交
     * @throws SQLException
     */
    public static void commit() throws SQLException{
    	Connection conn = getConn();
    	conn.commit();
    	// 增删改查操作一定要关闭连接
    	close();
    	
    }
    
    /**
     * 事务回滚
     * @throws SQLException
     */
    public static void rollback() throws SQLException{
    	Connection conn = getConn();
    	conn.rollback();
    	// 增删改查操作一定要关闭连接
    	close();
    	
    }

	public static ResultSet getResuluset(String sql) throws Exception
	{
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			ps = getConn().prepareStatement(sql);
			rs=ps.executeQuery();
		} catch (Exception e) {
			throw new Exception("获取PreparedStatement异常");
		}
		return rs;
	}
    
    
    
}
