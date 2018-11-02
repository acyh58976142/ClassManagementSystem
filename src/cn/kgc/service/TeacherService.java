package cn.kgc.service;

import java.util.List;

import cn.kgc.entity.Page;
import cn.kgc.entity.Teacher;

public interface TeacherService {
	
	
	Teacher teahcerInfo(Teacher teacher);
	
	
	Page<Teacher> findAll(String pageNum,int pageSize);
	
	/*
	 * ��ҳ��������ѯ����
	 */
	Page<Teacher> page = new Page<Teacher>();
	public Page<Teacher> findTeacher(String pageNum, int pageSize,String teacherName,String jobNumber);
	
	
	/*
	   * ���ӽ�ʦ��Ϣ�ķ���
	   */
	int saveTeacher(Teacher teacher);
	
	/*
	 * ɾ����ʦ��Ϣ�ķ���
	 */
	int delTeacher(String teacherId);
	
	/*
	 * �޸Ľ�ʦ��Ϣ�ķ���
	 */
	int updateTeacher(Teacher teacher);
	
	/*
	 * ��ѯһ���ķ���
	 */
	Teacher getTeacherById(String TeacherId);
	
	/*
	 * ��ѯ�����ʦ��Ϣ�ķ���
	 */
	List<Teacher> getTeacherList();
}
