package cn.kgc.service;

import java.util.List;

import cn.kgc.entity.Page;
import cn.kgc.entity.User;

public interface UserService {
	
	
	/*
	 * 分页查询方法
	 */
	Page<User> findBook(String pageNum,int pageSize,String userName);
	
    /*
     * 登陆
     */
	public User login(User user);
	
	/*
	 * 增加方法
	 */
	public int saveUser(User user);
   
	/*
	 * 删除方法
	 */
	public int delUser(String userId);
	
	/*
	 * 查询所有账号
	 */
	public List<User> queryAllUser();
	
	/*
	 * 查询一个的方法
	 */
	public User getUserById(String userId);
	
	/*
	 * 修改用户的方法
	 */
	public int updateUser(User user);
	
	/*
	 * 根据用户名(用户名唯一)查询权限
	 */
	public User getUserRole(String userName);
}
