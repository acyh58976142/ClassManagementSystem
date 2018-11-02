package cn.kgc.entity;

import java.util.Date;

public class Attendance {
	
	private String attendanceId;
	private String attendanceName;
	private Date attendanceDate;
	private Integer isabsence; //ÊÇ·ñÈ±ÇÚ 0Î´È±ÇÚ1È±ÇÚ×´Ì¬
	
	@Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId + ", attendanceName="
				+ attendanceName + ", attendanceDate=" + attendanceDate
				+ ", isabsence=" + isabsence + "]";
	}
	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Attendance(String attendanceId, String attendanceName,
			Date attendanceDate, Integer isabsence) {
		super();
		this.attendanceId = attendanceId;
		this.attendanceName = attendanceName;
		this.attendanceDate = attendanceDate;
		this.isabsence = isabsence;
	}
	public String getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}
	public String getAttendanceName() {
		return attendanceName;
	}
	public void setAttendanceName(String attendanceName) {
		this.attendanceName = attendanceName;
	}
	public Date getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	public Integer getIsabsence() {
		return isabsence;
	}
	public void setIsabsence(Integer isabsence) {
		this.isabsence = isabsence;
	}
	
	
	

}
