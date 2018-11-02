package cn.kgc.service;

import java.util.List;

import cn.kgc.entity.Page;
import cn.kgc.entity.Teacher;

public interface TeacherService {
	
	
	Teacher teahcerInfo(Teacher teacher);
	
	
	Page<Teacher> findAll(String pageNum,int pageSize);
	
	/*
	 * 分页带索引查询方法
	 */
	Page<Teacher> page = new Page<Teacher>();
	public Page<Teacher> findTeacher(String pageNum, int pageSize,String teacherName,String jobNumber);
	
	
	/*
	   * 增加教师信息的方法
	   */
	int saveTeacher(Teacher teacher);
	
	/*
	 * 删除教师信息的方法
	 */
	int delTeacher(String teacherId);
	
	/*
	 * 修改教师信息的方法
	 */
	int updateTeacher(Teacher teacher);
	
	/*
	 * 查询一个的方法
	 */
	Teacher getTeacherById(String TeacherId);
	
	/*
	 * 查询多个教师信息的方法
	 */
	List<Teacher> getTeacherList();
}
