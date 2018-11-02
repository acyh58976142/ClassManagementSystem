package cn.kgc.service;

import java.util.List;

import cn.kgc.entity.Page;
import cn.kgc.entity.Student;

public interface StudentService {
	

	/*
	 * 分页带索引查询方法
	 */
	Page<Student> page = new Page<Student>();
	public Page<Student> findStudent(String pageNum, int pageSize, String studentName,String studentNumber);
	
	/*
	 * 增加方法
	 */
	public int saveStudent(Student student);
	
	/*
	 * 查询一个的方法(列表中删除，修改用)
	 */
	Student getStudentById(String studentId);
	
	/*
	 * 删除方法
	 */
	public int delStudent(String studentId);
	

	/*
	 * 修改学生的方法
	 */
	int updateStudent(Student student);
	
	/*
	 * 查询姓名
	 */
	public List<Student> queryListStudentName();

}
