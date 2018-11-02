package cn.kgc.entity;

public class Plan {
private String teacher_names;
private String plan_titles;
private String plan_concent;
private String plan_date;
private String id;
private int job_number;
public Plan() {
	super();
	// TODO Auto-generated constructor stub
}


public Plan(String teacher_names, String plan_titles, String plan_concent,
		String plan_date, String id, int job_number) {
	super();
	this.teacher_names = teacher_names;
	this.plan_titles = plan_titles;
	this.plan_concent = plan_concent;
	this.plan_date = plan_date;
	this.id = id;
	this.job_number = job_number;
}


@Override
public String toString() {
	return "Plan [teacher_names=" + teacher_names + ", plan_titles="
			+ plan_titles + ", plan_concent=" + plan_concent + ", plan_date="
			+ plan_date + ", id=" + id + ", job_number=" + job_number + "]";
}
public String getTeacher_names() {
	return teacher_names;
}
public void setTeacher_names(String teacher_names) {
	this.teacher_names = teacher_names;
}
public String getPlan_titles() {
	return plan_titles;
}
public void setPlan_titles(String plan_titles) {
	this.plan_titles = plan_titles;
}
public String getPlan_concent() {
	return plan_concent;
}
public void setPlan_concent(String plan_concent) {
	this.plan_concent = plan_concent;
}
public String getPlan_date() {
	return plan_date;
}
public void setPlan_date(String plan_date) {
	this.plan_date = plan_date;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public int getJob_number() {
	return job_number;
}
public void setJob_number(int job_number) {
	this.job_number = job_number;
}




}
