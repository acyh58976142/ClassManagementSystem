package cn.kgc.entity;

import java.util.Date;

public class Score {
	
	private String  id;              //����id
	private String  studentName;     //����ѧ������ ��student�������studentName  
	private Integer javaScore;       //java����
	private Integer javascriptScore;  //javascript ����
	private Integer HTMLScore;       //HTML����
	private Integer SQLScore;        //SQL����
	private Date    scoreDate;       //��������
	
	@Override
	public String toString() {
		return "Score [id=" + id + ", studentName=" + studentName
				+ ", javaScore=" + javaScore + ", javascriptScore="
				+ javascriptScore + ", HTMLScore=" + HTMLScore + ", SQLScore="
				+ SQLScore + ", scoreDate=" + scoreDate + "]";
	}
	public Score() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Score(String id, String studentName, Integer javaScore,
			Integer javascriptScore, Integer hTMLScore, Integer sQLScore,
			Date scoreDate) {
		super();
		this.id = id;
		this.studentName = studentName;
		this.javaScore = javaScore;
		this.javascriptScore = javascriptScore;
		HTMLScore = hTMLScore;
		SQLScore = sQLScore;
		this.scoreDate = scoreDate;
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
	public Integer getJavaScore() {
		return javaScore;
	}
	public void setJavaScore(Integer javaScore) {
		this.javaScore = javaScore;
	}
	public Integer getjavascriptScore() {
		return javascriptScore;
	}
	public void setjavascriptScore(Integer javascriptScore) {
		this.javascriptScore = javascriptScore;
	}
	public Integer getHTMLScore() {
		return HTMLScore;
	}
	public void setHTMLScore(Integer hTMLScore) {
		HTMLScore = hTMLScore;
	}
	public Integer getSQLScore() {
		return SQLScore;
	}
	public void setSQLScore(Integer sQLScore) {
		SQLScore = sQLScore;
	}
	public Date getScoreDate() {
		return scoreDate;
	}
	public void setScoreDate(Date scoreDate) {
		this.scoreDate = scoreDate;
	}
	
	
	
	

}
