package cn.kgc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class JDBCUtils {
	
	/*
	 * �ⲿ�ļ�jdbc.properties����jdbc��ʽ
	 */     
	        
			public  Connection connJDBC(){
					
					//1.����properties��
					Properties pros = new Properties();
					Connection conn = null;
					try {
						//2.��ȡ�ⲿ�ļ���·��(�����ⲿ�ļ�)
						pros.load(this.getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
						//3.�������ⲿ�ļ��󣬻�ȡ����ĸ������Ե�ֵ�������kv��ֵ�Ե���ʽ�������ǿ���ͨ��key�ķ�ʽ��ȡ·��
						String driverClass = pros.getProperty("driverClass");
						String url = pros.getProperty("url");
						String user = pros.getProperty("user");
						String password = pros.getProperty("password");
						
						//��������
						Class.forName(driverClass);
						//��ȡ����
						 conn = DriverManager.getConnection(url,user,password);
						System.out.println(conn);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //��ǰ������������ļ�
					
					return  conn;
					
					
				}
		
		/*
		 * �ر���	
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
