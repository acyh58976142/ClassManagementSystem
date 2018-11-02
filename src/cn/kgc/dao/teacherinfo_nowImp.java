package cn.kgc.dao;

import java.util.List;

import cn.kgc.entity.Page;
import cn.kgc.entity.studentInfo;
import cn.kgc.entity.teahcerInfo;

public class teacherinfo_nowImp extends BeanDao<teahcerInfo> implements teacherinfo_now {

	@Override
	public teahcerInfo teahcer_Info(teahcerInfo teacher) {
		// TODO Auto-generated method stub
	String sql="SELECT id,`teather_name`,job_number,position,birth_date,ismarry,teacher_mobilephone FROM teacher WHERE teather_name=?  AND job_number=?";
		teacher = this.getQuery(sql, teacher.getTeather_name(),teacher.getJob_number());
	 	 return teacher;
	
	//return  this.getQuery(sql, teacher.getTeacher_name(),teacher.getJob_number());
	
}

	@Override
	public int student_Info(studentInfo student) {
		// TODO Auto-generated method stub
		String sql="insert into student(id,student_name,student_id,team,birth_date,graduation_study,profession,graduation_time,student_mobilephone,student_email,img_path,create_time) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		return this.update(sql, student.getId(),student.getStudent_name(),student.getStudent_id(),student.getTeam(),student.getBirth_date(),student.getGraduation_study(),student.getProfession(),student.getGraduation_time(),student.getStudent_mobilephone(),student.getStudent_email(),student.getImg_path(),student.getCreate_time());
	}
	@Override
	public teahcerInfo teacherAttendance(teahcerInfo teacherinfo) {
		// TODO Auto-generated method stub
		String sql="SELECT teacher.`id`,`teather_name`,teacher.`job_number`,teacher.`position`,teacher.`teacher_mobilephone`,attendance_date,`isabsence` FROM teacher,attendance WHERE teacher.`teather_name`=attendance.`attendance_name` AND teacher.`teather_name`=?  AND attendance.attendance_date=?";
		
		return  this.getQuery(sql, teacherinfo.getTeather_name(),teacherinfo.getAttendance_date());
	}

	@Override
	public int teacherAttendance_update(teahcerInfo teacherinfo) {
			String sql="UPDATE attendance,teacher SET isabsence=?,attendance_date=? WHERE attendance.`attendance_name`=? AND teacher.`job_number`=?";
		return this.update(sql, teacherinfo.getIsabsence(),teacherinfo.getAttendance_date(),teacherinfo.getTeather_name(),teacherinfo.getJob_number());
	}
	/*
	 * (non-Javadoc)
	 * @see cn.kgc.dao.UserDao#updateTeacherInfo(cn.kgc.entity.teahcerInfo)
	 * 插入教师的信息
	 */
	@Override
	public int updateTeacherInfo(teahcerInfo teacherinfo) {
		// TODO Auto-generated method stub
		
		String sql="INSERT INTO teacher(id,teather_name,job_number,`position`,birth_date,ismarry,teacher_mobilephone,teacher_email) VALUES (?,?,?,?,?,?,?,?)";
		String sql2="insert into attendance(attendance_name) values (?)";
		int update = this.update(sql,teacherinfo.getId(),teacherinfo.getTeather_name(),teacherinfo.getJob_number(),teacherinfo.getPosition(),teacherinfo.getBirth_date(),teacherinfo.getIsmarry(),teacherinfo.getTeacher_mobilephone(),teacherinfo.getTeacher_eamil());
		int update2 = this.update(sql2,teacherinfo.getTeather_name());
		return update+update2;
	}

	@Override
	public int deleteTeacherInfo(teahcerInfo teacherinfo) {
		// TODO Auto-generated method stub
	String sql="DELETE FROM attendance WHERE attendance_name=?";
	int del1 = this.update(sql, teacherinfo.getTeather_name());
	String sql2="DELETE FROM teacher WHERE teather_name=?";
	int del2 = this.update(sql2, teacherinfo.getTeather_name());
		return del1+del2;
	}

	@Override
	public int updateTeacherInfo_one(teahcerInfo teacherinfo) {
		String sql="UPDATE	teacher SET job_number=?,`position`=?,birth_date=?,ismarry=?,teacher_mobilephone=? WHERE teather_name=?";
		return this.update(sql,teacherinfo.getJob_number(),teacherinfo.getPosition(),teacherinfo.getBirth_date(),teacherinfo.getIsmarry(),teacherinfo.getTeacher_mobilephone(),teacherinfo.getTeather_name());
	}

	public Page<teahcerInfo> pageSize(Page<teahcerInfo> page) {
		String sql="SELECT COUNT(1) FROM teacher";
		Long querys =(Long) this.getQuerys(sql);
		long a=(long)querys;
		page.setTotalRecode((int)a);
		sql="SELECT id,teather_name,job_number,`position`,birth_date,ismarry,teacher_mobilephone FROM teacher  LIMIT ?,?";
	    List<teahcerInfo> date = this.getListBean(sql,page.getIndex(),page.getPageSize());
		page.setDate(date);
		return page;
	}

}
