package cn.kgc.dao;

import java.util.List;
import java.util.Map;

import cn.kgc.entity.Page;
import cn.kgc.entity.Teacher;

public interface TeacherDao {
	
	
	Teacher teahcerInfo(Teacher teacher);
	
	
	/*
	 * ��ҳ��������ѯ����
	 */
	Page<Teacher> page = new Page<Teacher>();
	public Page<Teacher> findTeacher(Page<Teacher> page,String teacherName,String jobNumber);
	
	 /*
     * ��ҳ��ѯ���з���
     */
	Page<Teacher> findAll(Page<Teacher> page);
	
	  /*
	   * ���ӽ�ʦ��Ϣ�ķ��� ok
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
	 * ��ѯһ���ķ���(�б���ɾ�����޸���)
	 */
	Teacher getTeacherById(String TeacherId);
	
	/*
	 * ��ѯ�����ʦ��Ϣ�ķ��� ok
	 */
	List<Teacher> getTeacherList();
	
	/*
	 * ����ͼƬ�ķ���
	 */
	
	/*
	 * �޸�ͼƬ�ķ���
	 */
	
}

