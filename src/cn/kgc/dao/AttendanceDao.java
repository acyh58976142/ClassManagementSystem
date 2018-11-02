package cn.kgc.dao;

import cn.kgc.entity.Attendance;
import cn.kgc.entity.Page;


public interface AttendanceDao {
	
	/*
	 * ��ҳ��������ѯ����
	 */
	Page<Attendance> page = new Page<Attendance>();
	public Page<Attendance> findAttendance(Page<Attendance> page,String studentName,String attendanceDate);
	
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
