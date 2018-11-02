package cn.kgc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import cn.kgc.dao.BeanDao;
import cn.kgc.dao.HomeworkDao;
import cn.kgc.entity.Homework;
import cn.kgc.entity.HomeworkPage;

public class HomeworkDaoImpl extends BeanDao<Homework> implements HomeworkDao{

	@Override
	public int saveHomework(Homework homework) {
		String sql="insert into homework(id,notescore,code_number,isannotate,correct_rate,homework_time,studentName,question,team)"
				+ "values (UUID(),?,?,?,?,now(),?,?,?)";
		
		return this.update(sql,homework.getNotescore(),homework.getCode_number(),homework.getIsannotate(),homework.getCorrect_rate(),homework.getStudentName(),homework.getQuestion(),homework.getTeam());
		
	}

	@Override
	public int delHomework(String id) {
		String sql="delete from homework where id=?";
		return this.update(sql, id);
	}


	@Override
	public Homework getHomeworkById(String id) {
		String sql="select id,notescore,code_number,isannotate,correct_rate,homework_time,studentName,question,team from homework where id=?";
		return this.getQuery(sql, id);
	}

	@Override
	public List<Homework> getHomeworkListByStudentName(String studentName) {
		String sql="select id,notescore,code_number,isannotate,correct_rate,homework_time,studentName,question,team from homework where studentName=?";
        
		return this.getListBean(sql,studentName);
	}

	@Override
	public int updateHomework(Homework homework) {
		String sql="update homework set notescore=?,code_number=?,isannotate=?,correct_rate=?,studentName=?,question=?,team=? where id=?";
		return this.update(sql,homework.getNotescore(),homework.getCode_number(),homework.getIsannotate(),homework.getCorrect_rate(),homework.getStudentName(),homework.getQuestion(),homework.getTeam(),homework.getId()
		);
	}

	@Override
	public HomeworkPage<Homework> findHomework(HomeworkPage<Homework> page) {
		// TODO Auto-generated method stub
		/**
		 * pageNum  pageSize   前台获取
		 * 
		 * totalPage   totalRecode   date  index  
		 */
		String sql="SELECT COUNT(*)  FROM homework";
		
		Long a = (Long) this.getSinleValue(sql);
		long  totalRecode=(long)a;
		//给page类中的totalRecode  变量赋值
		page.setTotalRecode((int)totalRecode);
		
		sql="select id,notescore,code_number,isannotate,correct_rate,homework_time,studentName,question,team from homework limit ?,?";
		
		List<Homework> date = this.getListBean(sql, page.getIndex(),page.getPageSize());
		
		page.setDate(date);
		
		
		return page;
	}

	
	public List<Homework> getHomeworkListByStudentName(){
		String sql="select id,notescore,code_number,isannotate,correct_rate,homework_time,studentName,question,team from homework";
        
		return this.getListBean(sql);
		
	}

	@Override
	public HomeworkPage<Homework> lookingforHomework(
			HomeworkPage<Homework> page, String studentName, String homeworkDate) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("select count(*) from homework where 1=1 ");
		List<Object> parmas = new ArrayList<Object>();
		
		if(studentName != null){
			sql.append(" and studentName like ?");
			parmas.add("%" +studentName+ "%");
		}
		/*if(studentNumber!= 0 ){
			sql.append(" and score.studentName = ( select student.studentName from student where student.studentNumber =? )");
			parmas.add("%" +studentNumber+ "%");
		}*/
		if(homeworkDate!= null && !homeworkDate.trim().isEmpty()){
			sql.append(" and time = ?");
			parmas.add(homeworkDate);
		}	
			Homework homework = new Homework();
			
			//通用方法对数据条数进行处理
			Long a = (Long)this.getSinleValue(sql.toString(),parmas.toArray());
			long totalRecode=(long)a;
			
			//给page类中的totalRecode  变量赋值
			page.setTotalRecode((int)totalRecode);
		  
		  List<Object> parmas2 = new ArrayList<Object>();
		  StringBuffer sql2= new StringBuffer("select id,notescore,code_number,isannotate,correct_rate,homework_time,studentName,question,team from homework where 1=1");
				
		  if(studentName!= null && !studentName.trim().isEmpty()){
				sql2.append(" and studentName like ? ");
				parmas2.add("%" +studentName+ "%");
				
			}
		  /*if(studentNumber!= 0 ){
				sql2.append(" and studentNumber like ? ");
				parmas2.add("%" +studentNumber+ "%");
				
			}*/
		  if(homeworkDate!= null && !homeworkDate.trim().isEmpty()){
				sql2.append(" and time = ? ");
				parmas2.add(homeworkDate);
				
			}
		   
		   sql2.append(" limit ? , ? ");
		   parmas2.add(page.getIndex());
		   parmas2.add(page.getPageSize());
		   List date =this.getListBean(sql2.toString(),parmas2.toArray());
		   page.setDate(date);
			return page; 
	}


	
}
