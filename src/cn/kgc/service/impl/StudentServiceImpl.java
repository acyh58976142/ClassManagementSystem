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
	 * ��ҳ��������ѯ����
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
	 * ���ӷ���
	 */
	@Override
	public int saveStudent(Student student) {
		
		return studentDao.saveStudent(student);
	}
    
	/*
	 * ��ѯһ���ķ���(�б���ɾ�����޸���)
	 */
	@Override
	public Student getStudentById(String studentId) {
		
		return studentDao.getStudentById(studentId);
	}
    
	/*
	 * ɾ������
	 */
	@Override
	public int delStudent(String studentId) {
		
		//���ͨ�� getListMapBean2������ѯ��score�����и�ѧ���������򷵻�true������delStudentAndScore����������ɾ
		if (scoreDao.queryScoreStudentNameById(studentId)) {
			
			return studentDao.delStudentAndScore(studentId);
		}
		
		
		//��֮��score����û�и�ѧ�������򣬽���delStudent����ɾ��
		return studentDao.delStudent(studentId);
	}
    
	/*
	 * �޸�ѧ���ķ���
	 */
	@Override
	public int updateStudent(Student student) {
		//���ͨ�� getListMapBean2������ѯ��score�����и�ѧ���������򷵻�true������delStudentAndScore����������ɾ
				if (scoreDao.queryScoreStudentNameById(student.getId())) {
					
					return studentDao.updateStudentAndScore(student);
				}
				
				
				//��֮��score����û�и�ѧ�������򣬽���updateStudent����ɾ��
				
		
		return studentDao.updateStudent(student);
	}
    
	/*
	 * ��ѯѧ������
	 * @see cn.kgc.service.StudentService#queryListStudentName()
	 */
	@Override
	public List<Student> queryListStudentName() {
				
		return studentDao.queryListStudentName();
	}

}
