package cn.kgc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class JDBCUtils {
	
	/*
	 * 外部文件jdbc.properties连接jdbc方式
	 */     
	        
			public  Connection connJDBC(){
					
					//1.创建properties类
					Properties pros = new Properties();
					Connection conn = null;
					try {
						//2.获取外部文件的路径(加载外部文件)
						pros.load(this.getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
						//3.加载完外部文件后，获取里面的各个属性的值。里面的kv键值对的形式，那我们可以通过key的方式获取路径
						String driverClass = pros.getProperty("driverClass");
						String url = pros.getProperty("url");
						String user = pros.getProperty("user");
						String password = pros.getProperty("password");
						
						//加载驱动
						Class.forName(driverClass);
						//获取链接
						 conn = DriverManager.getConnection(url,user,password);
						System.out.println(conn);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //当前类来加载这个文件
					
					return  conn;
					
					
				}
		
		/*
		 * 关闭流	
		 */
		public void close(Connection conn){
			
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
			
			
   
	
	 
	 
}
