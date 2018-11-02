package cn.kgc.service;

import java.util.List;

import cn.kgc.entity.Page;
import cn.kgc.entity.Student;

public interface StudentService {
	

	/*
	 * ��ҳ��������ѯ����
	 */
	Page<Student> page = new Page<Student>();
	public Page<Student> findStudent(String pageNum, int pageSize, String studentName,String studentNumber);
	
	/*
	 * ���ӷ���
	 */
	public int saveStudent(Student student);
	
	/*
	 * ��ѯһ���ķ���(�б���ɾ�����޸���)
	 */
	Student getStudentById(String studentId);
	
	/*
	 * ɾ������
	 */
	public int delStudent(String studentId);
	

	/*
	 * �޸�ѧ���ķ���
	 */
	int updateStudent(Student student);
	
	/*
	 * ��ѯ����
	 */
	public List<Student> queryListStudentName();

}
