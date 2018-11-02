package cn.kgc.service;

import cn.kgc.entity.Attendance;
import cn.kgc.entity.Page;

public interface AttendanceService {
	
	/*
	 * 分页带索引查询方法
	 */
	Page<Attendance> page = new Page<Attendance>();
	public Page<Attendance> findAttendance(String pageNum, int pageSize,String studentName,String attendanceDate);
	
	/*
	 * 增加学生考勤方法
	 */
	public int saveAttendance(Attendance attendance);
	
	/*
	 * 查询一个学生考勤的方法(列表中删除，修改用)
	 */
	Attendance getAttendanceById(String attendanceId);
	
	/*
	 * 删除方法
	 */
	public int delAttendance(String attendanceId);
	

	/*
	 * 修改学生考勤的方法
	 */
	int updateAttendance(Attendance attendance);

}
