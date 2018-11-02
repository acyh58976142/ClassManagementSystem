package cn.kgc.dao;

import cn.kgc.entity.Page;
import cn.kgc.entity.studentInfo;
import cn.kgc.entity.teahcerInfo;

public interface teacherinfo_now {
	teahcerInfo teahcer_Info(teahcerInfo teacher);
	int student_Info(studentInfo student);
	
	
	/*
	 * 注册教师的信息
	 * 
	 */
	 int updateTeacherInfo(teahcerInfo teacherinfo);
	

	
	 /*
	  * 删除指定的教师信息
	  */
	 int deleteTeacherInfo(teahcerInfo teacherinfo);
	/*
	 *修改指定的教师信息 
	 */
	 int updateTeacherInfo_one(teahcerInfo teacherinfo);
	teahcerInfo teacherAttendance(teahcerInfo teacherinfo);
	/*修改出勤率*/
	int teacherAttendance_update(teahcerInfo teacherinfo);
	 //每页显示的数据条数
	 Page<teahcerInfo> pageSize(Page<teahcerInfo> pages);
}
