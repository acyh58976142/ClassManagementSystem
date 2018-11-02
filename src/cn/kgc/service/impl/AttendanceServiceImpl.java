package cn.kgc.service.impl;

import cn.kgc.dao.AttendanceDao;
import cn.kgc.dao.impl.AttendanceDaoImpl;
import cn.kgc.entity.Attendance;
import cn.kgc.entity.Page;
import cn.kgc.service.AttendanceService;

public class AttendanceServiceImpl implements AttendanceService{
	AttendanceDao  attendanceDao = new AttendanceDaoImpl();

	@Override
	public Page<Attendance> findAttendance(String pageNum, int pageSize,
			String studentName, String attendanceDate) {
		int pn=1;
		try {
			pn=Integer.parseInt(pageNum);
		} catch (Exception e) {
		}
				
		Page<Attendance> page = new Page<Attendance>();
		
		page.setPageNum(pn);
		page.setPageSize(pageSize);
		
		return attendanceDao.findAttendance(page,studentName,attendanceDate);
	}

	@Override
	public int saveAttendance(Attendance attendance) {
		
		return attendanceDao.saveAttendance(attendance);
	}

	@Override
	public Attendance getAttendanceById(String attendanceId) {
		
		return attendanceDao.getAttendanceById(attendanceId);
	}

	@Override
	public int delAttendance(String attendanceId) {
		
		return attendanceDao.delAttendance(attendanceId);
	}

	@Override
	public int updateAttendance(Attendance attendance) {
		
		return attendanceDao.updateAttendance(attendance);
	}

}
