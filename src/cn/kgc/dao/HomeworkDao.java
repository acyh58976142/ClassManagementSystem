package cn.kgc.dao;

import java.util.List;

import cn.kgc.entity.Homework;
import cn.kgc.entity.HomeworkPage;

public interface HomeworkDao {
	
	HomeworkPage<Homework> findHomework(HomeworkPage<Homework> page);
	public HomeworkPage<Homework> lookingforHomework(HomeworkPage<Homework> page,String studentName,String homeworkDate);
	

	/**
	 * 增加作业情况的方法
	 */
	int saveHomework(Homework homework);
	
	
	/**
	 * 删除方法
	 */
	int delHomework(String id);
	
	/**
	 * 修改作业情况的方法
	 */
	int updateHomework(Homework homework);
	
	/**
	 * 查询一名学生的方法
	 */
	Homework getHomeworkById(String id);
	
	/**
	 * 查询多个学生的方法
	 */
	List<Homework> getHomeworkListByStudentName(String studentName);
	
	/**
	 * 查询所有学生的方法
	 */
	List<Homework> getHomeworkListByStudentName();

}
