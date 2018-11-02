package cn.kgc.dao;

import java.util.List;

import cn.kgc.entity.Page;
import cn.kgc.entity.User;

public interface UserDao {
	
	/*
	 * 分页带索引查询方法
	 */
	Page<User> page = new Page<User>();
	public Page<User> findBook(Page<User> page,String userName);
	
	/*
	 * 增加方法
	 */
	public int saveUser(User user);
	
	/*
	 * 查询一个方法(登陆用)
	 */
	public User queryUser(User user);
	
	/*
	 * 查询一个的方法(列表中删除，修改用)
	 */
	User getUserById(String userId);
	
    /*
     * 查询多个方法
     */
	public List<User> queryListUser();
	   
	/*
	 * 删除方法
	 */
	public int delUser(String userId);
	
	/*
	 * 修改用户的方法
	 */
	int updateUser(User user);
	
	/*
	 * 根据姓名查询权限
	 */
	public User getUserRole(String userName);
	
}
