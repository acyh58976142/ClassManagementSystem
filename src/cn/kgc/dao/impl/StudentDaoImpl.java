package cn.kgc.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.kgc.dao.BeanDao;
import cn.kgc.dao.StudentDao;
import cn.kgc.entity.Page;
import cn.kgc.entity.Score;
import cn.kgc.entity.Student;
import cn.kgc.entity.User;

public class StudentDaoImpl extends BeanDao<Student> implements StudentDao{
    

	/*
	 * 分页带索引查询方法
	 * parm Page<Student> page,String studentName,String studentNumber
	 * return page
	 */
	@Override
	public Page<Student> findStudent(Page<Student> page,String studentName,String studentNumber) {
		
		//创建一条StringBuffer类的sql，并写上一段查询条数的sql语句，准备拼接查询条件语句
		StringBuffer sql = new StringBuffer("select count(*) from student where 1=1 ");
		//因为页面上是多个查询条件，所以创建一个list集合
		List<Object> parmas = new ArrayList<Object>();
				//根据学生姓名做模糊查询，判断用户可能没有填写查询条件
				if(studentName != null && !studentName.trim().isEmpty()){
					sql.append(" and studentName like ?");
					parmas.add("%" +studentName+ "%");
				}
				//根据学生学号做查询，判断用户可能没有填写查询条件
				if(studentNumber != null && !studentNumber.trim().isEmpty()){
					sql.append(" and studentNumber = ?");
					parmas.add(studentNumber);
				}
		
		//通用方法对数据条数进行处理
		Long a = (Long)this.getSinleValue(sql.toString(),parmas.toArray());
		long totalRecode=(long)a;
		//给page类中的totalRecode  变量赋值
		page.setTotalRecode((int)totalRecode);
		
		//再创建一条StringBuffer类的sql2，并写上一段查询student信息的sql语句，准备拼接查询条件语句
		 StringBuffer sql2= new StringBuffer(" SELECT * FROM student WHERE 1=1 ");
		//因为页面上是多个查询条件，所以再创建一个list集合
		List<Object> parmas2 = new ArrayList<Object>();
		 
		//根据学生姓名做模糊查询，判断用户可能没有填写查询条件
				if(studentName != null && !studentName.trim().isEmpty()){
					sql2.append(" and studentName like ?");
					parmas2.add("%" +studentName+ "%");
				}
				//根据学生学号做查询，判断用户可能没有填写查询条件
				if(studentNumber != null && !studentNumber.trim().isEmpty()){
					sql2.append(" and studentNumber = ?");
					parmas2.add(studentNumber);
				}
				//拼接分页的信息
				sql2.append("ORDER BY createTime DESC limit ? , ?  ");
				parmas2.add(page.getIndex());     //索引
				parmas2.add(page.getPageSize());  //每页显示的条数
				
				//把sql语句和查询条件放入通用BeanDao的getListBean方法中，改方法使用duUtils的BeanListHandler<T>(type)
				//用一个list集合接收
				List date =this.getListBean(sql2.toString(),parmas2.toArray());
				//把查出来的数据放入page类的date属性中
				page.setDate(date);
								
		return page;
		
	}
    
	/*
	 * 增加方法
	 */
	@Override
	public int saveStudent(Student student) {
		String sql = "insert into student(student.id , student.studentName,student.studentNumber ,student.team , student.birthDate "
		 		+ ",student.graduationStudy ,student.graduationTime ,student.profession ,student.studentMobilephone "
		 		+ ",student.studentEmail ,student.imgPath ,student.createTime) value(uuid(),?,?,?,?,?,?,?,?,?,?,now())";
		
		 return this.update(sql,student.getStudentName(),student.getStudentNumber(),student.getTeam(),student.getBirthDate()
				,student.getGraduationStudy(),student.getGraduationTime(),student.getProfession(),student.getStudentMobilephone()
				,student.getStudentEmail(),student.getImgPath());
	}
    
	/*
	 * 查询一个的方法(列表中删除，修改用)
	 */
	@Override
	public Student getStudentById(String studentId) {
		
		String sql = "SELECT * FROM student WHERE id = ? ";
		
		return this.getQuery(sql, studentId);
	}
    
	/*
	 * 只删除student表的删除方法
	 */
	@Override
	public int delStudent(String studentId) {
		String sql="delete from student where id=? ";
		
		return this.update(sql, studentId);
	}
    

	/*
	 * 分数表有学生姓名的情况下，删除方法
	 */
	public int delStudentAndScore(String studentId){
		String sql="DELETE student , score FROM student , score WHERE student.id= ? AND student.studentName = score.studentName ";
		
		return this.update(sql, studentId);
	}
	
	/*
	 * 修改学生的方法
	 */
	@Override
	public int updateStudent(Student student) {
		String sql="update student set  student.studentName=? , student.studentNumber = ? ,student.team = ?, student.birthDate=?"
				+ ", student.graduationStudy = ?, student.graduationTime = ?, student.profession = ?, student.studentMobilephone = ?,"
				+ "student.studentEmail = ?, student.imgPath = ? where id=?";
		
		return this.update(sql,student.getStudentName(),student.getStudentNumber(),student.getTeam(),student.getBirthDate()
				,student.getGraduationStudy(),student.getGraduationTime(),student.getProfession(),student.getStudentMobilephone()
				,student.getStudentEmail(),student.getImgPath(),student.getId());
	}
	
	/*
	 * 分数表有学生姓名的情况下，修改方法
	 */
	public int updateStudentAndScore(Student student){
		String sql="update student,score set  student.studentName=? , student.studentNumber = ? ,student.team = ?, student.birthDate=?"
				+ ", student.graduationStudy = ?, student.graduationTime = ?, student.profession = ?, student.studentMobilephone = ?,"
				+ "student.studentEmail = ?, student.imgPath = ?,score.studentName = ? where student.id= ? AND student.studentName = score.studentName";
		
		return this.update(sql,student.getStudentName(),student.getStudentNumber(),student.getTeam(),student.getBirthDate()
				,student.getGraduationStudy(),student.getGraduationTime(),student.getProfession(),student.getStudentMobilephone()
				,student.getStudentEmail(),student.getImgPath(),student.getStudentName(),student.getId());
	}
	
	/*
     * 查询多个方法
     */
	@Override
	public List queryListStudent() {
		// TODO Auto-generated method stub
		String sql = "SELECT student.studentName,student.studentNumber,student.team,student.birthDate,student.graduationStudy,student.profession"
				+ ",student.graduationTime,student.studentMobilephone,student.studentEmail FROM student";
		return getListBean(sql);
	}
    
	/*
	 * 查询姓名
	 * @see cn.kgc.dao.StudentDao#queryListStudentName()
	 */
	@Override
	public List<Student> queryListStudentName() {
		String sql = "SELECT student.studentName FROM student";
		
		return getListBean(sql);
	}

}
