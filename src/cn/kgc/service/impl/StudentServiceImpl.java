package cn.kgc.service.impl;

import java.util.List;

import cn.kgc.dao.ScoreDao;
import cn.kgc.dao.StudentDao;
import cn.kgc.dao.impl.ScoreDaoImpl;
import cn.kgc.dao.impl.StudentDaoImpl;
import cn.kgc.entity.Page;
import cn.kgc.entity.Student;
import cn.kgc.service.StudentService;

public class StudentServiceImpl implements StudentService{
	
	StudentDao studentDao = new StudentDaoImpl();
	ScoreDao scoreDao = new ScoreDaoImpl();
	/*
	 * 分页带索引查询方法
	 */
	@Override
	public Page<Student> findStudent(String pageNum, int pageSize, String studentName,
			String studentNumber) {
		int pn=1;
		try {
			pn=Integer.parseInt(pageNum);
		} catch (Exception e) {
		}
				
		Page<Student> page = new Page<Student>();
		
		page.setPageNum(pn);
		page.setPageSize(pageSize);
		
		return studentDao.findStudent(page, studentName, studentNumber);
	}
    
	/*
	 * 增加方法
	 */
	@Override
	public int saveStudent(Student student) {
		
		return studentDao.saveStudent(student);
	}
    
	/*
	 * 查询一个的方法(列表中删除，修改用)
	 */
	@Override
	public Student getStudentById(String studentId) {
		
		return studentDao.getStudentById(studentId);
	}
    
	/*
	 * 删除方法
	 */
	@Override
	public int delStudent(String studentId) {
		
		//如果通过 getListMapBean2方法查询到score表里有该学生姓名，则返回true，进入delStudentAndScore方法两边联删
		if (scoreDao.queryScoreStudentNameById(studentId)) {
			
			return studentDao.delStudentAndScore(studentId);
		}
		
		
		//反之，score表里没有改学生姓名则，进入delStudent方法删除
		return studentDao.delStudent(studentId);
	}
    
	/*
	 * 修改学生的方法
	 */
	@Override
	public int updateStudent(Student student) {
		//如果通过 getListMapBean2方法查询到score表里有该学生姓名，则返回true，进入delStudentAndScore方法两边联删
				if (scoreDao.queryScoreStudentNameById(student.getId())) {
					
					return studentDao.updateStudentAndScore(student);
				}
				
				
				//反之，score表里没有改学生姓名则，进入updateStudent方法删除
				
		
		return studentDao.updateStudent(student);
	}
    
	/*
	 * 查询学生姓名
	 * @see cn.kgc.service.StudentService#queryListStudentName()
	 */
	@Override
	public List<Student> queryListStudentName() {
				
		return studentDao.queryListStudentName();
	}

}
