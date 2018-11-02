package cn.kgc.service.impl;
/*
 * 陈雨豪
 * 2018-08-01
 * User模块Service实现层
 */

import java.util.List;

import cn.kgc.dao.UserDao;
import cn.kgc.dao.impl.UserDaoImpl;
import cn.kgc.entity.Page;
import cn.kgc.entity.User;
import cn.kgc.service.UserService;

public class UserServiceImpl implements UserService{

	
	
	UserDao userDao = new UserDaoImpl();
	
	/*
	 * 分页查询方法
	 */
	@Override
	public Page<User> findBook(String pageNum, int pageSize,String userName ) {
		int pn=1;
		try {
			pn=Integer.parseInt(pageNum);
		} catch (Exception e) {
		}
				
		Page<User> page = new Page<User>();
		
		page.setPageNum(pn);
		page.setPageSize(pageSize);
		
		return userDao.findBook(page,userName);
	}
	
    /*
     * 登陆
     * @see cn.kgc.service.UserService#login(cn.kgc.entity.User)
     */
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		
		return userDao.queryUser(user);
	}
	
    
	/*
	 * 查询全部
	 * @see cn.kgc.service.UserService#queryAllUser()
	 */
	@Override
	public List<User> queryAllUser() {
		// TODO Auto-generated method stub
		return userDao.queryListUser();
	}


	/*
	 * 增加用户方法
	 */
	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		return userDao.saveUser(user);
	}


	/*
	 * 删除方法
	 */
	@Override
	public int delUser(String userId) {
		// TODO Auto-generated method stub
		return userDao.delUser(userId);
	}


	/*
	 * 查询一个的方法
	 */
	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return userDao.getUserById(userId);
	}


	/*
	 * 修改用户的方法
	 */
	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}
    
	
	/*
	 * 更具用户名查询用户权限
	 */
	public User getUserRole(String userName){
		
		return userDao.getUserRole(userName);
		
	}
}
