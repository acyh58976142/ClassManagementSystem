package cn.kgc.dao;

import java.util.List;

import cn.kgc.entity.Homework;
import cn.kgc.entity.HomeworkPage;

public interface HomeworkDao {
	
	HomeworkPage<Homework> findHomework(HomeworkPage<Homework> page);
	public HomeworkPage<Homework> lookingforHomework(HomeworkPage<Homework> page,String studentName,String homeworkDate);
	

	/**
	 * ������ҵ����ķ���
	 */
	int saveHomework(Homework homework);
	
	
	/**
	 * ɾ������
	 */
	int delHomework(String id);
	
	/**
	 * �޸���ҵ����ķ���
	 */
	int updateHomework(Homework homework);
	
	/**
	 * ��ѯһ��ѧ���ķ���
	 */
	Homework getHomeworkById(String id);
	
	/**
	 * ��ѯ���ѧ���ķ���
	 */
	List<Homework> getHomeworkListByStudentName(String studentName);
	
	/**
	 * ��ѯ����ѧ���ķ���
	 */
	List<Homework> getHomeworkListByStudentName();

}
