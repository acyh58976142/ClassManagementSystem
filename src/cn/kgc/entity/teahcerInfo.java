package cn.kgc.entity;

import java.util.Date;
import java.util.List;



public class teahcerInfo {
	private String id;
	private String teather_name;
	private Integer job_number;
	private String position;
	private Date birth_date;
	private Integer ismarry;
	private String teacher_mobilephone;
	private String teacher_eamil;
	private Integer isabsence;//考勤
	private List<teahcerInfo> date; /* 数据的集合*/
	private Date attendance_date;
	public teahcerInfo() {
		// TODO Auto-generated constructor stub
		
		
	}
	public teahcerInfo(String id, String teather_name,
			Integer job_number, String position,
			String teacher_mobilephone, Integer isabsence,Date attendance_date) {
		this.id = id;
		this.teather_name = teather_name;
		this.job_number = job_number;
		this.position = position;
		this.teacher_mobilephone = teacher_mobilephone;
		this.isabsence = isabsence;
		this.attendance_date=attendance_date;
	}
	
	
	
	
	public teahcerInfo(String id, String teather_name, Integer job_number,
			String position, Date birth_date, Integer ismarry,
			String teacher_mobilephone, String teacher_eamil) {
		super();
		this.id = id;
		this.teather_name = teather_name;
		this.job_number = job_number;
		this.position = position;
		this.birth_date = birth_date;
		this.ismarry = ismarry;
		this.teacher_mobilephone = teacher_mobilephone;
		this.teacher_eamil = teacher_eamil;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTeather_name() {
		return teather_name;
	}
	public void setTeather_name(String teather_name) {
		this.teather_name = teather_name;
	}
	public Integer getJob_number() {
		return job_number;
	}
	public void setJob_number(Integer job_number) {
		this.job_number = job_number;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public Integer getIsmarry() {
		return ismarry;
	}
	public void setIsmarry(Integer ismarry) {
		this.ismarry = ismarry;
	}
	public String getTeacher_mobilephone() {
		return teacher_mobilephone;
	}
	public void setTeacher_mobilephone(String teacher_mobilephone) {
		this.teacher_mobilephone = teacher_mobilephone;
	}
	public String getTeacher_eamil() {
		return teacher_eamil;
	}
	public void setTeacher_eamil(String teacher_eamil) {
		this.teacher_eamil = teacher_eamil;
	}
	public Integer getIsabsence() {
		return isabsence;
	}
	public void setIsabsence(Integer isabsence) {
		this.isabsence = isabsence;
	}
	public List<teahcerInfo> getDate() {
		return date;
	}
	public void setDate(List<teahcerInfo> date) {
		this.date = date;
	}
	public Date getAttendance_date() {
		return attendance_date;
	}
	public void setAttendance_date(Date attendance_date) {
		this.attendance_date = attendance_date;
	}
	
}
