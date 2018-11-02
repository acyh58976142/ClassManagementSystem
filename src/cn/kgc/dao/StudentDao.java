package cn.kgc.dao;

import java.util.List;

import cn.kgc.entity.Page;
import cn.kgc.entity.Score;
import cn.kgc.entity.Student;
import cn.kgc.entity.User;

public interface StudentDao {
	
	/*
	 * ��ҳ��������ѯ����
	 */
	Page<Student> page = new Page<Student>();
	public Page<Student> findStudent(Page<Student> page,String studentName,String studentNumber);
	
	/*
	 * ���ӷ���
	 */
	public int saveStudent(Student student);
	
	/*
	 * ��ѯһ���ķ���(�б���ɾ�����޸���)
	 */
	Student getStudentById(String studentId);
	
	/*
	 * ֻɾ��student���ɾ������
	 */
	public int delStudent(String studentId);
	

	/*
	 * ��������ѧ������������£�ɾ������
	 */
	public int delStudentAndScore(String studentId);

	/*
	 * �޸�ѧ���ķ���
	 */
	int updateStudent(Student student);
	
	/*
	 * ��������ѧ������������£��޸ķ���
	 */
	public int updateStudentAndScore(Student student);
	
	/*
	 * ��ѯ����
	 */
	public List<Student> queryListStudentName();
	

	
	public List queryListStudent();
}
