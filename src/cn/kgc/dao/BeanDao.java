package cn.kgc.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v1.util.ArrayUtils;
import com.mysql.jdbc.ResultSetMetaData;

import cn.kgc.entity.User;
import cn.kgc.util.JDBCUtils;

public class BeanDao<T> {
	
	QueryRunner runner = new QueryRunner();
	JDBCUtils jdbcUtils = new JDBCUtils();
	Class<T> type ;
	
	
	//�˹��췽���Ǹ�������õ�
		public BeanDao(){
			//UserDao extrands BaseDao<User>
			//type ����������������ͣ�����ֻ��Ҫ�ҵ�����ķ��͵����͸���type��ֵ
			//�õ������͵�����
			//Type genericSuperclass = this.getClass().getGenericSuperclass();
			ParameterizedType pt  =  (ParameterizedType) this.getClass().getGenericSuperclass();
			//��ȡ����ķ�������
			//��Ϊ���ǵķ��;�ֻ��һ�����������ǻ�ȥ����ĵ�һ��Ҳ��Ψһ��һ��
			Type[] types = pt.getActualTypeArguments();
			this.type=(Class<T>) types[0];
		}
	
	/*
	 * ��ɾ�ķ���
	 */
    public int update(String sql,Object...params){
    	 int count = 0;
    	//�������ݿ�
    	Connection conn = jdbcUtils.connJDBC();
    	
    	//���ø������������ɾ��
    	
    	try {
    		count= runner.update(conn, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtils.close(conn);
		}
    	
    	return count;
    }
    
    /*
     * ͨ�õĲ�ѯһ�����ݵķ���
     */
	 public  T getQuery(String sql,Object...params){
		 
		 T t = null;
		 
		 //�������ݿ�
    	 Connection conn = jdbcUtils.connJDBC();
    	 
    	 try {
			t = runner.query(conn, sql,new BeanHandler<T>(type)  ,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return t;
	 }
	 
	 
	 /*
	     * ͨ�õĲ�ѯ�������ݵķ���
	     */
	 public List<T> getListBean(String sql,Object...params){
    	 List list = null;
    	
    	//�������ݿ�
    	 Connection conn = jdbcUtils.connJDBC();
    	 
    	 try {
    		 list=runner.query(conn, sql,new BeanListHandler<T>(type), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtils.close(conn);
		}
    	 
    	 return list;
     }
	 
	 
	 /*
	     * ͨ�õĲ�ѯ�������ݵķ���
	     */
	
	public Object[] getArrayHandler(String sql,Object...params){
 	 Object[] list = null;
 	
 	//�������ݿ�
 	 Connection conn = jdbcUtils.connJDBC();
 	 
 	 try {
 		 list=runner.query(conn, sql,new  ArrayHandler(), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtils.close(conn);
		}
 	 
 	 return list;
  }
	 
	 /*
	     * ͨ�õĲ�ѯ�������ݵķ���
	     */
	 public Map<String,Object> getMapHandler(String sql,Object...params){
 	 Map<String, Object> list = null;
 	
 	//�������ݿ�
 	 Connection conn = jdbcUtils.connJDBC();
 	 
 	 try {
 		 list=runner.query(conn, sql,new MapHandler(), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtils.close(conn);
		}
 	 
 	 return list;
  }
	 

	 
	 
	     /*
	     * ͨ�õĲ�ѯ���ݿ⺯��
	     */
	 public List<Integer> getColumnList(String sql,Object...params){
 	 List list = null;
 	
 	//�������ݿ�
 	 Connection conn = jdbcUtils.connJDBC();
 	
 	 try {
 		 list=runner.query(conn, sql,new  ColumnListHandler(), params);
 		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtils.close(conn);
		}
 	 
 	 return list;
  }
	 
	 /*
	     * ͨ�õ���������--��ѯ�������ݵķ���
	     */
	 public List<Map<String,Object>> getListMapBean(String sql,Object...params){
		 List<Map<String, Object>> list = null;
 	
 	//�������ݿ�
 	 Connection conn = jdbcUtils.connJDBC();
 	 
 	 try {
 		
 		 list=runner.query(conn,sql, new MapListHandler(),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtils.close(conn);
		}
 	 //��ѯ�����ÿһ�й���һ��Map���ϣ�Map��keyΪ��ѯ���ֶ�������������
 	  return list;
  }
	 
	 /*
	     * ͨ�õ���������--��ѯ�������ݵķ���
	     */
	 public boolean getListMapBean2(String sql,Object...params){
		 List<Map<String, Object>> list = null;
	
	//�������ݿ�
	 Connection conn = jdbcUtils.connJDBC();
	 
	 try {
		
		 list=runner.query(conn,sql, new MapListHandler(),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtils.close(conn);
		}
	 //��ѯ�����ÿһ�й���һ��Map���ϣ�Map��keyΪ��ѯ���ֶ�������������
	 if (list==null) {
		 return false;
	}
	 return true;
}

	 

		/*
		 * ����һ��ֵ�ķ���
		 */
		public Object getSinleValue(String sql,Object...params){
			
			Object obj =null;
			//�������ݿ�
	   	    Connection conn = jdbcUtils.connJDBC();
			
	   	 
	   	 try {
	   		    obj=runner.query(conn, sql,new  ScalarHandler(), params);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jdbcUtils.close(conn);
			}
			
	   	   return obj;
		}
		
		
		
		
		
		/*
		 * 
		 * �½��ķ���
		 */
		public  Object getQuerys(String sql,Object...params){
			 
			 Object t = null;
				 
				 //�������ݿ�
		    	 Connection conn = jdbcUtils.connJDBC();
		    	 
		    	 try {
					t = runner.query(conn, sql, new ScalarHandler(), params);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					jdbcUtils.close(conn);
				}
				 
				 return t;
			 }
		
		
		/* ���ڽ�rs��ѯ�����װΪList<Map<String, Object>>����
	     * 
	     * @param rs���ݿ��ѯ���
	     * @return ����list map��װ��Ľ��
	     */
	    public static List<Map<String, Object>> convertList(ResultSet rs) {
	        // �½�һ��map list�������ڴ�Ŷ�����ѯ��¼
	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        try {
	            ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();// �����(rs)�Ľṹ��Ϣ�������ֶ������ֶ����ȡ�
	            int columnCount = md.getColumnCount();// ȡ�ò�ѯ�������ֶθ���
	            while (rs.next()) {// ����rs
	                // �½�һ��map���� ����ѯ�����ݰ����ֶ�����ֵ �ļ�ֵ����ʽ�洢��map������
	                Map<String, Object> rowData = new HashMap<String, Object>();
	                for (int i = 1; i <= columnCount; i++) {// ѭ�����в�ѯ���ֶ�
	                    rowData.put(md.getColumnName(i), rs.getObject(i));
	                    // getColumnName(i) ��ȡ��i������
	                    // getObject(i) ��ȡ��i�������ֵ
	                }
	                list.add(rowData);// ��map����list������
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {// �ر�����
	            try {
	                if (rs != null)
	                    rs.close();
	                rs = null;
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return list;
	    }



	    /*
	     * ���ڽ�rs��ѯ�����װΪMap<String, Object>�����ʺ���ֻ��һ����ѯ��¼��
	     * 
	     * @param rs���ݿ��ѯ���
	     * @return ����map��װ�� �ֶ�����ֵ �ļ�ֵ�Խ��
	     */
	    public static Map<String, Object> convertMap(ResultSet rs) {
	        Map<String, Object> map = new TreeMap<String, Object>();
	        try {
	            ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
	            int columnCount = md.getColumnCount();
	            while (rs.next()) {
	                for (int i = 1; i <= columnCount; i++) {
	                    map.put(md.getColumnName(i), rs.getObject(i));
	                }
	            }
	            return map;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null)
	                    rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return null;
	    }
}
