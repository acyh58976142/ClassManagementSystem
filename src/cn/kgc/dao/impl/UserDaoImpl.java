package cn.kgc.dao.impl;
/*
 * �����
 * 2018-08-01
 * UserDaoImpl�㣬�������ݿ�
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
	 * ��ҳ��������ѯ����
	 */
	@Override
	public Page<User> findBook(Page<User> page,String userName) {
		/*
		 * pageNum pageSize ǰ̨��ȡ
		 * 
		 * totalPage totalRecode date index 
		 */
		/*String sql ="select count(*) from user";
		
		User user = new User();
		
		//ͨ�÷����������������д���
		Long a = (Long)this.getSinleValue(sql);
		long totalRecode=(long)a;
		
		//��page���е�totalRecode  ������ֵ
		page.setTotalRecode((int)totalRecode);*/
		
		if (userName==null) {
			String sql ="select count(*) from user";
			
			User user = new User();
			
			//ͨ�÷����������������д���
			Long a = (Long)this.getSinleValue(sql);
			long totalRecode=(long)a;
			
			//��page���е�totalRecode  ������ֵ
			page.setTotalRecode((int)totalRecode);
			//������ѯ
			sql="select id,username,password,mobilePhone,email,role,joinTime,isUsing  from user limit ?,?";
			List<User> date =this.getListBean(sql, page.getIndex(),page.getPageSize());
			page.setDate(date);
			
			return page;
		}else{
			String sql ="select count(*) from user where username like ?";
			
			User user = new User();
			
			//ͨ�÷����������������д���
			Long a = (Long)this.getSinleValue(sql,"%"+userName+"%");
			long totalRecode=(long)a;
			
			//��page���е�totalRecode  ������ֵ
			page.setTotalRecode((int)totalRecode);
			//������ѯ
			sql="select id,username,password,mobilePhone,email,role,joinTime,isUsing  from user where username like ? limit ?,?";
			List<User> date =this.getListBean(sql,"%"+userName+"%", page.getIndex(),page.getPageSize());
			page.setDate(date);
			
			return page;
		}
		
		
	}


	
	/*
	 * ��ѯһ������(��½��)
	 */
	@Override
	public User queryUser(User user) {
		// TODO Auto-generated method stub
        String sql ="select id,username,password,mobilePhone,email,role,joinTime,isUsing from user where username=? and password=?";
		
		return this.getQuery(sql, user.getUserName(),user.getPassword());
	}
    
	/*
	 * ���ӷ���
	 */
	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
        String sql ="insert into user(id,username,password,mobilePhone,email,role,joinTime,isUsing) value(uuid(),?,?,?,?,?,now(),?)";
		
        return  this.update(sql, user.getUserName(),user.getPassword(),user.getMobilePhone(),user.getEmail(),user.getRole(), 
        		 user.getIsUsing());
		
	}
    
	/*
     * ��ѯ�������
     */
	@Override
	public List<User> queryListUser() {
		// TODO Auto-generated method stub
		String sql = "select id,username,password,mobilePhone,email,role,joinTime,isUsing from user";
		return getListBean(sql);
	}
    
	/*
	 * ��ѯһ���ķ���(�б���ɾ�����޸���)
	 */
	@Override
	public User getUserById(String userId) {
		String sql="select id,username,password,mobilePhone,email,role,joinTime,isUsing from user where id=?";
		return getQuery(sql,userId);
	}
	
	/*
	 * ɾ������
	 */
	@Override
	public int delUser(String userId) {
		String sql="delete from user where id=?";
		return update(sql, userId);
	}
	
	/*
	 * �޸��û��ķ���
	 */
	@Override
	public int updateUser(User user) {
		String sql="update user set username=?,password=?,mobilePhone=?,email=?,role=?,isUsing=? where id=?";
		return this.update(sql, user.getUserName(),user.getPassword(),user.getMobilePhone(),user.getEmail(),user.getRole(),
				user.getIsUsing(),user.getId());
	}		
		         
	/*
	 * �����û�����ѯ�û�Ȩ��
	 * @see cn.kgc.dao.UserDao#getUserRole(java.lang.String)
	 */
	public User getUserRole(String userName){
		String sql="select id,username,password,mobilePhone,email,role,joinTime,isUsing from user where username=?";
		return getQuery(sql,userName);
	}
}
