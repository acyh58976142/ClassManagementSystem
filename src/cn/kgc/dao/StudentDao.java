package cn.kgc.dao;

import java.util.List;

import cn.kgc.entity.Page;
import cn.kgc.entity.Score;
import cn.kgc.entity.Student;
import cn.kgc.entity.User;

public interface StudentDao {
	
	/*
	 * 分页带索引查询方法
	 */
	Page<Student> page = new Page<Student>();
	public Page<Student> findStudent(Page<Student> page,String studentName,String studentNumber);
	
	/*
	 * 增加方法
	 */
	public int saveStudent(Student student);
	
	/*
	 * 查询一个的方法(列表中删除，修改用)
	 */
	Student getStudentById(String studentId);
	
	/*
	 * 只删除student表的删除方法
	 */
	public int delStudent(String studentId);
	

	/*
	 * 分数表有学生姓名的情况下，删除方法
	 */
	public int delStudentAndScore(String studentId);

	/*
	 * 修改学生的方法
	 */
	int updateStudent(Student student);
	
	/*
	 * 分数表有学生姓名的情况下，修改方法
	 */
	public int updateStudentAndScore(Student student);
	
	/*
	 * 查询姓名
	 */
	public List<Student> queryListStudentName();
	

	
	public List queryListStudent();
}
