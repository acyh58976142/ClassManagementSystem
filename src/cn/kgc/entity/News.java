package cn.kgc.entity;

import java.sql.Date;

public class News {
private String new_title;
private String new_content;
private Date new_date;
private String new_type;
private String img_path;
//有参方法
public News(){
	super();
}
public News(String new_title, String new_content, Date new_date,
		String new_type,String img_path) {
	super();
	this.new_title = new_title;
	this.new_content = new_content;
	this.new_date = new_date;
	this.new_type = new_type;
	this.img_path=img_path;
}
//toString

@Override
public String toString() {
	return "News [new_title=" + new_title + ", new_content=" + new_content
			+ ", new_date=" + new_date + ", new_type=" + new_type + ", img_path="+img_path+"]";
}
public String getNew_title() {
	return new_title;
}

public void setNew_title(String new_title) {
	this.new_title = new_title;
}
public String getNew_content() {
	return new_content;
}
public void setNew_content(String new_content) {
	this.new_content = new_content;
}
public Date getNew_date() {
	return new_date;
}
public void setNew_date(Date new_date) {
	this.new_date = new_date;
}
public String getNew_type() {
	return new_type;
}
public void setNew_type(String new_type) {
	this.new_type = new_type;
}
public void setImg_path(String img_path){
	this.img_path=img_path;
}
public String getImg_path(){
	return img_path;
}


}
