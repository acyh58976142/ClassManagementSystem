package cn.kgc.service;

import java.util.List;

import cn.kgc.entity.Homework;
import cn.kgc.entity.HomeworkPage;

public interface HomeworkService {
	
	/**
	 * ��ѯ����ѧ����ҳ�ķ���
	 */
	HomeworkPage<Homework> findHomework(String pageNum,int pageSize);

	
	public HomeworkPage<Homework> lookingforHomework(String pageNum, int pageSize,String studentName,String HomeworkDate);
	/**
	 * ����ͼ��ķ���
	 */
	int saveHomework(Homework homework);
	
	
	/**
	 * ɾ������
	 */
	int delHomework(String id);
	
	/**
	 * �޸�ͼ��ķ���
	 */
	int updateHomework(Homework homework);
	
	/**
	 * ��ѯһ���ķ���
	 */
	Homework getHomeworkById(String id);
	
	/**
	 * ��ѯ���ѧ����ҵ�ķ���
	 */
	List<Homework> getHomeworkList(String studentName);
    
	/**
	 * ��ѯ����ѧ���ķ���
	 */
	List<Homework> getHomeworkList();
		
	/*
	 *����������ѧ�� 
	 */
	
	List<Homework> getHomeworkListByStudentName(String studentName);

}


