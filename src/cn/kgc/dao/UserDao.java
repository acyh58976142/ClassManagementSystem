package cn.kgc.dao;

import java.util.List;

import cn.kgc.entity.Page;
import cn.kgc.entity.User;

public interface UserDao {
	
	/*
	 * ��ҳ��������ѯ����
	 */
	Page<User> page = new Page<User>();
	public Page<User> findBook(Page<User> page,String userName);
	
	/*
	 * ���ӷ���
	 */
	public int saveUser(User user);
	
	/*
	 * ��ѯһ������(��½��)
	 */
	public User queryUser(User user);
	
	/*
	 * ��ѯһ���ķ���(�б���ɾ�����޸���)
	 */
	User getUserById(String userId);
	
    /*
     * ��ѯ�������
     */
	public List<User> queryListUser();
	   
	/*
	 * ɾ������
	 */
	public int delUser(String userId);
	
	/*
	 * �޸��û��ķ���
	 */
	int updateUser(User user);
	
	/*
	 * ����������ѯȨ��
	 */
	public User getUserRole(String userName);
	
}
