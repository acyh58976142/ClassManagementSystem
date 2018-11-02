package cn.kgc.dao;

import java.util.List;
import java.util.Map;

import cn.kgc.entity.Page;
import cn.kgc.entity.Teacher;

public interface TeacherDao {
	
	
	Teacher teahcerInfo(Teacher teacher);
	
	
	/*
	 * 分页带索引查询方法
	 */
	Page<Teacher> page = new Page<Teacher>();
	public Page<Teacher> findTeacher(Page<Teacher> page,String teacherName,String jobNumber);
	
	 /*
     * 分页查询所有方法
     */
	Page<Teacher> findAll(Page<Teacher> page);
	
	  /*
	   * 增加教师信息的方法 ok
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
	 * 查询一个的方法(列表中删除，修改用)
	 */
	Teacher getTeacherById(String TeacherId);
	
	/*
	 * 查询多个教师信息的方法 ok
	 */
	List<Teacher> getTeacherList();
	
	/*
	 * 新增图片的方法
	 */
	
	/*
	 * 修改图片的方法
	 */
	
}

