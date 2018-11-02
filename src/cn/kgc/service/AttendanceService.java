package cn.kgc.service;

import cn.kgc.entity.Attendance;
import cn.kgc.entity.Page;

public interface AttendanceService {
	
	/*
	 * ��ҳ��������ѯ����
	 */
	Page<Attendance> page = new Page<Attendance>();
	public Page<Attendance> findAttendance(String pageNum, int pageSize,String studentName,String attendanceDate);
	
	/*
	 * ����ѧ�����ڷ���
	 */
	public int saveAttendance(Attendance attendance);
	
	/*
	 * ��ѯһ��ѧ�����ڵķ���(�б���ɾ�����޸���)
	 */
	Attendance getAttendanceById(String attendanceId);
	
	/*
	 * ɾ������
	 */
	public int delAttendance(String attendanceId);
	

	/*
	 * �޸�ѧ�����ڵķ���
	 */
	int updateAttendance(Attendance attendance);

}
