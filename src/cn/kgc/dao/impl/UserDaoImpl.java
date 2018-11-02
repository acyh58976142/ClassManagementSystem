package cn.kgc.dao.impl;
/*
 * 陈雨豪
 * 2018-08-01
 * UserDaoImpl层，操作数据库
 */

import java.util.List;
import java.util.UUID;

import cn.kgc.dao.BeanDao;
import cn.kgc.dao.UserDao;
import cn.kgc.entity.Page;
import cn.kgc.entity.User;
import cn.kgc.util.JDBCUtils;

public class UserDaoImpl extends BeanDao<User> implements UserDao{

	JDBCUtils jdbcUtils = new JDBCUtils();
	
	/*
	 * 分页带索引查询方法
	 */
	@Override
	public Page<User> findBook(Page<User> page,String userName) {
		/*
		 * pageNum pageSize 前台获取
		 * 
		 * totalPage totalRecode date index 
		 */
		/*String sql ="select count(*) from user";
		
		User user = new User();
		
		//通用方法对数据条数进行处理
		Long a = (Long)this.getSinleValue(sql);
		long totalRecode=(long)a;
		
		//给page类中的totalRecode  变量赋值
		page.setTotalRecode((int)totalRecode);*/
		
		if (userName==null) {
			String sql ="select count(*) from user";
			
			User user = new User();
			
			//通用方法对数据条数进行处理
			Long a = (Long)this.getSinleValue(sql);
			long totalRecode=(long)a;
			
			//给page类中的totalRecode  变量赋值
			page.setTotalRecode((int)totalRecode);
			//索引查询
			sql="select id,username,password,mobilePhone,email,role,joinTime,isUsing  from user limit ?,?";
			List<User> date =this.getListBean(sql, page.getIndex(),page.getPageSize());
			page.setDate(date);
			
			return page;
		}else{
			String sql ="select count(*) from user where username like ?";
			
			User user = new User();
			
			//通用方法对数据条数进行处理
			Long a = (Long)this.getSinleValue(sql,"%"+userName+"%");
			long totalRecode=(long)a;
			
			//给page类中的totalRecode  变量赋值
			page.setTotalRecode((int)totalRecode);
			//索引查询
			sql="select id,username,password,mobilePhone,email,role,joinTime,isUsing  from user where username like ? limit ?,?";
			List<User> date =this.getListBean(sql,"%"+userName+"%", page.getIndex(),page.getPageSize());
			page.setDate(date);
			
			return page;
		}
		
		
	}


	
	/*
	 * 查询一个方法(登陆用)
	 */
	@Override
	public User queryUser(User user) {
		// TODO Auto-generated method stub
        String sql ="select id,username,password,mobilePhone,email,role,joinTime,isUsing from user where username=? and password=?";
		
		return this.getQuery(sql, user.getUserName(),user.getPassword());
	}
    
	/*
	 * 增加方法
	 */
	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
        String sql ="insert into user(id,username,password,mobilePhone,email,role,joinTime,isUsing) value(uuid(),?,?,?,?,?,now(),?)";
		
        return  this.update(sql, user.getUserName(),user.getPassword(),user.getMobilePhone(),user.getEmail(),user.getRole(), 
        		 user.getIsUsing());
		
	}
    
	/*
     * 查询多个方法
     */
	@Override
	public List<User> queryListUser() {
		// TODO Auto-generated method stub
		String sql = "select id,username,password,mobilePhone,email,role,joinTime,isUsing from user";
		return getListBean(sql);
	}
    
	/*
	 * 查询一个的方法(列表中删除，修改用)
	 */
	@Override
	public User getUserById(String userId) {
		String sql="select id,username,password,mobilePhone,email,role,joinTime,isUsing from user where id=?";
		return getQuery(sql,userId);
	}
	
	/*
	 * 删除方法
	 */
	@Override
	public int delUser(String userId) {
		String sql="delete from user where id=?";
		return update(sql, userId);
	}
	
	/*
	 * 修改用户的方法
	 */
	@Override
	public int updateUser(User user) {
		String sql="update user set username=?,password=?,mobilePhone=?,email=?,role=?,isUsing=? where id=?";
		return this.update(sql, user.getUserName(),user.getPassword(),user.getMobilePhone(),user.getEmail(),user.getRole(),
				user.getIsUsing(),user.getId());
	}		
		         
	/*
	 * 根据用户名查询用户权限
	 * @see cn.kgc.dao.UserDao#getUserRole(java.lang.String)
	 */
	public User getUserRole(String userName){
		String sql="select id,username,password,mobilePhone,email,role,joinTime,isUsing from user where username=?";
		return getQuery(sql,userName);
	}
}
