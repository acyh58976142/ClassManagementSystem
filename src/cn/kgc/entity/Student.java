package cn.kgc.entity;

import java.util.Date;

public class Student {
	
	private String  id;
	private String  studentName;         //学生姓名
	private Integer studentNumber;       //学号
	private String  team;                //班级
	private Date    birthDate;           //出生日期
	private String  graduationStudy;     //毕业学校
	private Date    graduationTime;      //毕业日期
	private String  profession;          //专业
	private String  studentMobilephone;  //学生手机号码
	private String  studentEmail;        //学生email
	private String  imgPath;             //照片路径
	private Date    createTime;          //建档时间  
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", studentName=" + studentName
				+ ", studentNumber=" + studentNumber + ", team=" + team
				+ ", birthDate=" + birthDate + ", graduationStudy="
				+ graduationStudy + ", graduationTime=" + graduationTime
				+ ", profession=" + profession + ", studentMobilephone="
				+ studentMobilephone + ", studentEmail=" + studentEmail
				+ ", imgPath=" + imgPath + ", createTime=" + createTime + "]";
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String id, String studentName, Integer studentNumber,
			String team, Date birthDate, String graduationStudy,
			Date graduationTime, String profession, String studentMobilephone,
			String studentEmail, String imgPath, Date createTime) {
		super();
		this.id = id;
		this.studentName = studentName;
		this.studentNumber = studentNumber;
		this.team = team;
		this.birthDate = birthDate;
		this.graduationStudy = graduationStudy;
		this.graduationTime = graduationTime;
		this.profession = profession;
		this.studentMobilephone = studentMobilephone;
		this.studentEmail = studentEmail;
		this.imgPath = imgPath;
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Integer getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getGraduationStudy() {
		return graduationStudy;
	}
	public void setGraduationStudy(String graduationStudy) {
		this.graduationStudy = graduationStudy;
	}
	public Date getGraduationTime() {
		return graduationTime;
	}
	public void setGraduationTime(Date graduationTime) {
		this.graduationTime = graduationTime;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getStudentMobilephone() {
		return studentMobilephone;
	}
	public void setStudentMobilephone(String studentMobilephone) {
		this.studentMobilephone = studentMobilephone;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
	
	

}
