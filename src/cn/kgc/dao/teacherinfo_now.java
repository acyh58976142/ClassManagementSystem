package cn.kgc.dao;

import cn.kgc.entity.Page;
import cn.kgc.entity.studentInfo;
import cn.kgc.entity.teahcerInfo;

public interface teacherinfo_now {
	teahcerInfo teahcer_Info(teahcerInfo teacher);
	int student_Info(studentInfo student);
	
	
	/*
	 * ע���ʦ����Ϣ
	 * 
	 */
	 int updateTeacherInfo(teahcerInfo teacherinfo);
	

	
	 /*
	  * ɾ��ָ���Ľ�ʦ��Ϣ
	  */
	 int deleteTeacherInfo(teahcerInfo teacherinfo);
	/*
	 *�޸�ָ���Ľ�ʦ��Ϣ 
	 */
	 int updateTeacherInfo_one(teahcerInfo teacherinfo);
	teahcerInfo teacherAttendance(teahcerInfo teacherinfo);
	/*�޸ĳ�����*/
	int teacherAttendance_update(teahcerInfo teacherinfo);
	 //ÿҳ��ʾ����������
	 Page<teahcerInfo> pageSize(Page<teahcerInfo> pages);
}
