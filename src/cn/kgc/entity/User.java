package cn.kgc.entity;

import java.sql.Date;

public class User {
    
	private String  id;
	private String  userName;
	private String  password;
	private String  mobilePhone;
	private String  email;
	private int     role;         //0超级管理员1学生账号2教师账号3宿管账号
	private Date    joinTime;     //创建时间
	private int     isUsing;      //0未启用1已启用
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String id, String userName, String password,
			String mobilePhone, String email, int role, Date joinTime,
			int isUsing) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.mobilePhone = mobilePhone;
		this.email = email;
		this.role = role;
		this.joinTime = joinTime;
		this.isUsing = isUsing;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public int getIsUsing() {
		return isUsing;
	}
	public void setIsUsing(int isUsing) {
		this.isUsing = isUsing;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + ", mobilePhone=" + mobilePhone + ", email="
				+ email + ", role=" + role + ", joinTime=" + joinTime
				+ ", isUsing=" + isUsing + "]";
	}
}
