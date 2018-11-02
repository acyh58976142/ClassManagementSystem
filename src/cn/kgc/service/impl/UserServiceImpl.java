package cn.kgc.service.impl;
/*
 * �����
 * 2018-08-01
 * Userģ��Serviceʵ�ֲ�
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
	 * ��ҳ��ѯ����
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
     * ��½
     * @see cn.kgc.service.UserService#login(cn.kgc.entity.User)
     */
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		
		return userDao.queryUser(user);
	}
	
    
	/*
	 * ��ѯȫ��
	 * @see cn.kgc.service.UserService#queryAllUser()
	 */
	@Override
	public List<User> queryAllUser() {
		// TODO Auto-generated method stub
		return userDao.queryListUser();
	}


	/*
	 * �����û�����
	 */
	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		return userDao.saveUser(user);
	}


	/*
	 * ɾ������
	 */
	@Override
	public int delUser(String userId) {
		// TODO Auto-generated method stub
		return userDao.delUser(userId);
	}


	/*
	 * ��ѯһ���ķ���
	 */
	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return userDao.getUserById(userId);
	}


	/*
	 * �޸��û��ķ���
	 */
	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}
    
	
	/*
	 * �����û�����ѯ�û�Ȩ��
	 */
	public User getUserRole(String userName){
		
		return userDao.getUserRole(userName);
		
	}
}
