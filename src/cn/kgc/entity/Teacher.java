package cn.kgc.entity;

import java.util.Date;

public class Teacher {
       /*
        * 序号
        */
	   private String id;
	   
	   /*
	    * 教师姓名
	    */
	   private String teatherName;
	   
	   /*
	    * 工号
	    */
	   private Integer jobNumber;
	   
	   /*
	    * 职位
	    */
	   private String  position;
	   
	   /*
	    * 出生日期
	    */
	   private Date birthDate;
	   
	   /*
	    * 婚姻状况  0未婚 1已婚
	    */
	   private Integer isMarry;
	   
	   /*
	    * 联系方式
	    */
	   private String mobilephone;
	   
	   /*
	    * 电子邮件
	    */
	   private String email;
	   /*
	    * 图片路径
	    */
	   private String imgPath="/img/background1.jpg";
	   

	

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTeatherName() {
		return teatherName;
	}

	public void setTeatherName(String teatherName) {
		this.teatherName = teatherName;
	}

	public Integer getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(Integer jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getIsMarry() {
		return isMarry;
	}

	public void setIsMarry(Integer isMarry) {
		this.isMarry = isMarry;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Teacher(String id, String teatherName, Integer jobNumber,
			String position, Date birthDate, Integer isMarry,
			String mobilephone, String email, String imgPath) {
		super();
		this.id = id;
		this.teatherName = teatherName;
		this.jobNumber = jobNumber;
		this.position = position;
		this.birthDate = birthDate;
		this.isMarry = isMarry;
		this.mobilephone = mobilephone;
		this.email = email;
		this.imgPath = imgPath;
	}



	@Override
	public String toString() {
		return "Teacher [id=" + id + ", teatherName=" + teatherName
				+ ", jobNumber=" + jobNumber + ", position=" + position
				+ ", birthDate=" + birthDate + ", isMarry=" + isMarry
				+ ", mobilephone=" + mobilephone + ", email=" + email
				+ ", imgPath=" + imgPath + "]";
	}

	
	   
	   
	
}
