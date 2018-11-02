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
	
	
	//此构造方法是给子类调用的
		public BeanDao(){
			//UserDao extrands BaseDao<User>
			//type 就是我们子类的类型，我们只需要找到具体的泛型的类型个给type赋值
			//得到带泛型的类型
			//Type genericSuperclass = this.getClass().getGenericSuperclass();
			ParameterizedType pt  =  (ParameterizedType) this.getClass().getGenericSuperclass();
			//获取具体的泛型类型
			//因为我们的泛型就只有一个，所以我们回去数组的第一个也是唯一的一个
			Type[] types = pt.getActualTypeArguments();
			this.type=(Class<T>) types[0];
		}
	
	/*
	 * 增删改方法
	 */
    public int update(String sql,Object...params){
    	 int count = 0;
    	//连接数据库
    	Connection conn = jdbcUtils.connJDBC();
    	
    	//利用个工具类操作增删改
    	
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
     * 通用的查询一条数据的方法
     */
	 public  T getQuery(String sql,Object...params){
		 
		 T t = null;
		 
		 //连接数据库
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
	     * 通用的查询多条数据的方法
	     */
	 public List<T> getListBean(String sql,Object...params){
    	 List list = null;
    	
    	//连接数据库
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
	     * 通用的查询多条数据的方法
	     */
	
	public Object[] getArrayHandler(String sql,Object...params){
 	 Object[] list = null;
 	
 	//连接数据库
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
	     * 通用的查询多条数据的方法
	     */
	 public Map<String,Object> getMapHandler(String sql,Object...params){
 	 Map<String, Object> list = null;
 	
 	//连接数据库
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
	     * 通用的查询数据库函数
	     */
	 public List<Integer> getColumnList(String sql,Object...params){
 	 List list = null;
 	
 	//连接数据库
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
	     * 通用的两表联查--查询多条数据的方法
	     */
	 public List<Map<String,Object>> getListMapBean(String sql,Object...params){
		 List<Map<String, Object>> list = null;
 	
 	//连接数据库
 	 Connection conn = jdbcUtils.connJDBC();
 	 
 	 try {
 		
 		 list=runner.query(conn,sql, new MapListHandler(),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtils.close(conn);
		}
 	 //查询结果的每一行构成一个Map集合，Map的key为查询的字段名（即列名）
 	  return list;
  }
	 
	 /*
	     * 通用的两表联查--查询多条数据的方法
	     */
	 public boolean getListMapBean2(String sql,Object...params){
		 List<Map<String, Object>> list = null;
	
	//连接数据库
	 Connection conn = jdbcUtils.connJDBC();
	 
	 try {
		
		 list=runner.query(conn,sql, new MapListHandler(),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtils.close(conn);
		}
	 //查询结果的每一行构成一个Map集合，Map的key为查询的字段名（即列名）
	 if (list==null) {
		 return false;
	}
	 return true;
}

	 

		/*
		 * 返回一个值的方法
		 */
		public Object getSinleValue(String sql,Object...params){
			
			Object obj =null;
			//连接数据库
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
		 * 章金邦的方法
		 */
		public  Object getQuerys(String sql,Object...params){
			 
			 Object t = null;
				 
				 //连接数据库
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
		
		
		/* 用于将rs查询结果封装为List<Map<String, Object>>对象
	     * 
	     * @param rs数据库查询结果
	     * @return 返回list map封装后的结果
	     */
	    public static List<Map<String, Object>> convertList(ResultSet rs) {
	        // 新建一个map list集合用于存放多条查询记录
	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        try {
	            ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();// 结果集(rs)的结构信息，比如字段数、字段名等。
	            int columnCount = md.getColumnCount();// 取得查询出来的字段个数
	            while (rs.next()) {// 迭代rs
	                // 新建一个map集合 将查询出内容按照字段名：值 的键值对形式存储在map集合中
	                Map<String, Object> rowData = new HashMap<String, Object>();
	                for (int i = 1; i <= columnCount; i++) {// 循环所有查询出字段
	                    rowData.put(md.getColumnName(i), rs.getObject(i));
	                    // getColumnName(i) 获取第i个列名
	                    // getObject(i) 获取第i个对象的值
	                }
	                list.add(rowData);// 将map放入list集合中
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {// 关闭连接
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
	     * 用于将rs查询结果封装为Map<String, Object>对象（适合于只有一条查询记录）
	     * 
	     * @param rs数据库查询结果
	     * @return 返回map封装后 字段名：值 的键值对结果
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
