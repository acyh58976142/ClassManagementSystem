package cn.kgc.service;

import java.util.List;

import cn.kgc.entity.Page;
import cn.kgc.entity.User;

public interface UserService {
	
	
	/*
	 * ��ҳ��ѯ����
	 */
	Page<User> findBook(String pageNum,int pageSize,String userName);
	
    /*
     * ��½
     */
	public User login(User user);
	
	/*
	 * ���ӷ���
	 */
	public int saveUser(User user);
   
	/*
	 * ɾ������
	 */
	public int delUser(String userId);
	
	/*
	 * ��ѯ�����˺�
	 */
	public List<User> queryAllUser();
	
	/*
	 * ��ѯһ���ķ���
	 */
	public User getUserById(String userId);
	
	/*
	 * �޸��û��ķ���
	 */
	public int updateUser(User user);
	
	/*
	 * �����û���(�û���Ψһ)��ѯȨ��
	 */
	public User getUserRole(String userName);
}
