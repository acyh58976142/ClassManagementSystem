package cn.kgc.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.kgc.entity.Plan;
import cn.kgc.util.JDBCUtils;



public class Plan_BaseDao<T> {
	QueryRunner runner = new QueryRunner();
//	Plan_JdbcUtils jdbcUtils= new Plan_JdbcUtils();
	JDBCUtils jdbcUtils = new JDBCUtils();
	Class<T> type ;
	
	
	//姝ゆ瀯閫犳柟娉曟槸缁欏瓙绫昏皟鐢ㄧ殑
		public Plan_BaseDao(){
			//UserDao extrands BaseDao<User>
			//type 灏辨槸鎴戜滑瀛愮被鐨勭被鍨嬶紝鎴戜滑鍙渶瑕佹壘鍒板叿浣撶殑娉涘瀷鐨勭被鍨嬩釜缁檛ype璧嬪�
			//寰楀埌甯︽硾鍨嬬殑绫诲瀷
			//Type genericSuperclass = this.getClass().getGenericSuperclass();
			ParameterizedType pt  =  (ParameterizedType) this.getClass().getGenericSuperclass();
			//鑾峰彇鍏蜂綋鐨勬硾鍨嬬被鍨�
			//鍥犱负鎴戜滑鐨勬硾鍨嬪氨鍙湁涓�釜锛屾墍浠ユ垜浠洖鍘绘暟缁勭殑绗竴涓篃鏄敮涓�殑涓�釜
			Type[] types = pt.getActualTypeArguments();
			this.type=(Class<T>) types[0];
		}
	
	/*
	 * 澧炲垹鏀规柟娉�
	 */
    public int plan_update(String sql,Object...params){
    	 int count = 0;
    	//杩炴帴鏁版嵁搴�
    	Connection conn = jdbcUtils.connJDBC();
    	
    	//鍒╃敤涓伐鍏风被鎿嶄綔澧炲垹鏀�
    	
    	try {
    		count= runner.update(conn, sql, params);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			jdbcUtils.close(conn);
		}
    	
    	return count;
    }
    
    /*
     * 閫氱敤鐨勬煡璇竴鏉℃暟鎹殑鏂规硶
     */
	 public  T plan_getQuery(String sql,Object...params){
		 
		 T t = null;
		 
		 //杩炴帴鏁版嵁搴�
    	 Connection conn = jdbcUtils.connJDBC();
    	 
    	
			try {
				t = runner.query(conn, sql,new BeanHandler<T>(type)  ,params);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jdbcUtils.close(conn);
			}
		   
		 
		 return t;
	 }
	 
	 
	 /*
	     * 閫氱敤鐨勬煡璇㈠鏉℃暟鎹殑鏂规硶
	     */
	 public List<T> plan_getListBean(String sql,Object...params){
    	 List<T> list = null;
    	
    	//杩炴帴鏁版嵁搴�
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

	public int plan_zsg(Plan plan) {
		// TODO Auto-generated method stub
		return 0;
	}
	//鍒嗛〉杩斿洖涓�釜鏁版嵁鐨勬柟娉�
	public Object getSinvelue(String sql,Object...parames){
		 Object obj=null;
		 Connection con=jdbcUtils.connJDBC();
		 try {
			 obj = runner.query(con, sql, new ScalarHandler(),parames );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtils.close(con);
		}
		 return obj;
		 
	 }
	public int plan_drop(String sql){
		 int count = 0;
	    	//杩炴帴鏁版嵁搴�
	    	Connection conn = jdbcUtils.connJDBC();
	    	
	    	//鍒╃敤涓伐鍏风被鎿嶄綔澧炲垹鏀�
	    	
	    	try {
	    		count= runner.update(conn, sql);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				jdbcUtils.close(conn);
			}
	    	
	    	return count;
	}
}
