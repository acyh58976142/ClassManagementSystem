package cn.kgc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import cn.kgc.dao.BeanDao;
import cn.kgc.dao.TeacherDao;
import cn.kgc.entity.Page;
import cn.kgc.entity.Teacher;

public class TeacherDaoImpl extends BeanDao<Teacher> implements TeacherDao {

	@Override
	public int saveTeacher(Teacher teacher) {
		String sql="insert into teacher(id,teatherName,jobNumber,position,birthDate,isMarry,teacherMobilephone,teacherEmail,imgPath) "
				+ "values (uuid(),?,?,?,?,?,?,?,?)";
		return this.update(sql,teacher.getTeatherName(),teacher.getJobNumber(),teacher.getPosition(),teacher.getBirthDate(),teacher.getIsMarry(),teacher.getMobilephone(),teacher.getEmail(),teacher.getImgPath());
	}

	@Override
	public int delTeacher(String teacherId) {
		String sql="delete from teacher where id=?";
		return this.update(sql, teacherId);
	}

	@Override
	public int updateTeacher(Teacher teacher) {
		String sql="update teacher set teatherName=?,jobNumber=?,position=?,birthDate=?,isMarry=?,teacherMobilephone=?,teacherEmail=?,imgPath=? where id=?";
		return this.update(sql, teacher.getTeatherName(),teacher.getJobNumber(),teacher.getPosition(),teacher.getBirthDate(),teacher.getIsMarry(),teacher.getMobilephone(),teacher.getEmail(),teacher.getImgPath(),teacher.getId());
	}

	@Override
	public Teacher getTeacherById(String TeacherId) {
		String sql="select id,teatherName ,jobNumber ,position,birthDate birthDate,isMarry,teacherMobilephone,teacherEmail,imgPath  from teacher where id=?";
		return this.getQuery(sql, TeacherId);
	}

	@Override
	public List<Teacher> getTeacherList() {
		String sql="select id,teatherName ,jobNumber ,position,birthDate birthDate,isMarry,teacherMobilephone ,teacherEmail ,imgPath  from teacher ";
		return this.getListBean(sql);
	}

	
	
	/*
	 * 分页带索引查询方法
	 */
	@Override
	public Page<Teacher> findTeacher(Page<Teacher> page, String teatherName,
			String jobNumber) {
	
		StringBuffer sql = new StringBuffer("select count(*) from teacher where 1=1 ");
		List<Object> parmas = new ArrayList<Object>();
		
		if(teatherName != null && !teatherName.trim().isEmpty()){
			sql.append(" and teatherName like ?");
			parmas.add("%" +teatherName+ "%");
		}

		if(jobNumber!= null&&!jobNumber.trim().isEmpty()){
			sql.append(" and jobNumber = ?");
			parmas.add(jobNumber);
		}	
		Teacher teacher = new Teacher();
			
			//通用方法对数据条数进行处理
			Long a = (Long)this.getSinleValue(sql.toString(),parmas.toArray());
			long totalRecode=(long)a;
			
			//给page类中的totalRecode  变量赋值
			page.setTotalRecode((int)totalRecode);
		  
		  List<Object> parmas2 = new ArrayList<Object>();
		  StringBuffer sql2= new StringBuffer("select id,teatherName ,jobNumber ,position,birthDate ,isMarry,teacherMobilephone ,teacherEmail , imgPath from teacher   where 1=1 ");
				
		  if(teatherName!= null && !teatherName.trim().isEmpty()){
				sql2.append(" and teatherName like ? ");
				parmas2.add("%" +teatherName+ "%");
				
			}
		 
		  if(jobNumber!= null&&!jobNumber.trim().isEmpty()){
				sql2.append(" and jobNumber = ? ");
				parmas2.add(jobNumber);
				
			}
		   
		   sql2.append(" limit ? , ? ");
		   parmas2.add(page.getIndex());
		   parmas2.add(page.getPageSize());
		   List date =this.getListBean(sql2.toString(),parmas2.toArray());
		   page.setDate(date);
			return page; 
		
	}

	
	@Override
	public Page<Teacher> findAll(Page<Teacher> page) {
		/**
		 * pageNum  pageSize   前台获取
		 * 
		 * totalPage   totalRecode   date  index  
		 */
		String sql="SELECT COUNT(*)  FROM teacher";
		
		Long a = (Long) this.getSinleValue(sql);
		long  totalRecode=(long)a;
		//给page类中的totalRecode  变量赋值
		page.setTotalRecode((int)totalRecode);
		
		sql="select id,teatherName ,jobNumber ,position,birthDate ,isMarry,"
				+ "teacherMobilephone ,teacherEmail ,imgPath  from teacher  limit ?,?";
		
		List<Teacher> date = this.getListBean(sql, page.getIndex(),page.getPageSize());
		
		page.setDate(date);
		
		return page;
	}

	@Override
	public Teacher teahcerInfo(Teacher teacher) {
		
		String sql="select id,teatherName teatherName,jobNumber jobNumber,position,birthDate birthDate,"
					+ "isMarry,teacherMobilephone mobilephone,teacherEmail email,imgPath imgPath from teacher where teatherName=? and jobNumber=?";
		
		return this.getQuery(sql, teacher.getTeatherName(),teacher.getJobNumber());
	}

	
	
}
