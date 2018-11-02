package cn.kgc.entity;

import java.util.Date;

public class studentInfo {
		private String id;
		private String student_name;
		private Integer student_id;
		private String team;
		private Date birth_date;
		private String graduation_study;
		private Date graduation_time;
		private String profession;
		private String student_mobilephone;
		private String student_email;
		private String img_path;
		private Date create_time;
		public studentInfo() {
			super();
			// TODO Auto-generated constructor stub
		}
		public studentInfo(String id, String student_name, Integer student_id,
				String team, Date birth_date, String graduation_study,
				Date graduation_time, String profession,
				String student_mobilephone, String student_email,
				String img_path, Date create_time) {
			this.id = id;
			this.student_name = student_name;
			this.student_id = student_id;
			this.team = team;
			this.birth_date = birth_date;
			this.graduation_study = graduation_study;
			this.graduation_time = graduation_time;
			this.profession = profession;
			this.student_mobilephone = student_mobilephone;
			this.student_email = student_email;
			this.img_path = img_path;
			this.create_time = create_time;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getStudent_name() {
			return student_name;
		}
		public void setStudent_name(String student_name) {
			this.student_name = student_name;
		}
		public Integer getStudent_id() {
			return student_id;
		}
		public void setStudent_id(Integer student_id) {
			this.student_id = student_id;
		}
		public String getTeam() {
			return team;
		}
		public void setTeam(String team) {
			this.team = team;
		}
		public Date getBirth_date() {
			return birth_date;
		}
		public void setBirth_date(Date birth_date) {
			this.birth_date = birth_date;
		}
		public String getGraduation_study() {
			return graduation_study;
		}
		public void setGraduation_study(String graduation_study) {
			this.graduation_study = graduation_study;
		}
		public Date getGraduation_time() {
			return graduation_time;
		}
		public void setGraduation_time(Date graduation_time) {
			this.graduation_time = graduation_time;
		}
		public String getProfession() {
			return profession;
		}
		public void setProfession(String profession) {
			this.profession = profession;
		}
		public String getStudent_mobilephone() {
			return student_mobilephone;
		}
		public void setStudent_mobilephone(String student_mobilephone) {
			this.student_mobilephone = student_mobilephone;
		}
		public String getStudent_email() {
			return student_email;
		}
		public void setStudent_email(String student_email) {
			this.student_email = student_email;
		}
		public String getImg_path() {
			return img_path;
		}
		public void setImg_path(String img_path) {
			this.img_path = img_path;
		}
		public Date getCreate_time() {
			return create_time;
		}
		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}
		
} 
