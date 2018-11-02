package cn.kgc.service;

import java.util.List;

import cn.kgc.entity.Homework;
import cn.kgc.entity.HomeworkPage;

public interface HomeworkService {
	
	/**
	 * 查询所有学生分页的方法
	 */
	HomeworkPage<Homework> findHomework(String pageNum,int pageSize);

	
	public HomeworkPage<Homework> lookingforHomework(String pageNum, int pageSize,String studentName,String HomeworkDate);
	/**
	 * 增加图书的方法
	 */
	int saveHomework(Homework homework);
	
	
	/**
	 * 删除方法
	 */
	int delHomework(String id);
	
	/**
	 * 修改图书的方法
	 */
	int updateHomework(Homework homework);
	
	/**
	 * 查询一个的方法
	 */
	Homework getHomeworkById(String id);
	
	/**
	 * 查询多个学生作业的方法
	 */
	List<Homework> getHomeworkList(String studentName);
    
	/**
	 * 查询所有学生的方法
	 */
	List<Homework> getHomeworkList();
		
	/*
	 *按姓名查找学生 
	 */
	
	List<Homework> getHomeworkListByStudentName(String studentName);

}


