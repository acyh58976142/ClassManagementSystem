package cn.kgc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import cn.kgc.dao.AttendanceDao;
import cn.kgc.dao.BeanDao;
import cn.kgc.entity.Attendance;
import cn.kgc.entity.Page;
import cn.kgc.entity.Score;


public class AttendanceDaoImpl extends BeanDao<Attendance> implements AttendanceDao{
	/*
	 * 分页带索引查询方法
	 */
	@Override
	public Page<Attendance> findAttendance(Page<Attendance> page,
			String studentName, String attendanceDate) {
	
		StringBuffer sql = new StringBuffer("select count(*) from attendance where 1=1 ");
		List<Object> parmas = new ArrayList<Object>();
		
		if(studentName != null && !studentName.trim().isEmpty()){
			sql.append(" and attendance.attendanceName like ?");
			parmas.add("%" +studentName+ "%");
		}
		/*if(studentNumber!= 0 ){
			sql.append(" and score.studentName = ( select student.studentName from student where student.studentNumber =? )");
			parmas.add("%" +studentNumber+ "%");
		}*/
		if(attendanceDate!= null && !attendanceDate.trim().isEmpty()){
			sql.append(" and attendanceDate.attendanceDate = ?");
			parmas.add(attendanceDate);
		}	
			Score score = new Score();
			
			//通用方法对数据条数进行处理
			Long a = (Long)this.getSinleValue(sql.toString(),parmas.toArray());
			long totalRecode=(long)a;
			
			//给page类中的totalRecode  变量赋值
			page.setTotalRecode((int)totalRecode);
		  
		  List<Object> parmas2 = new ArrayList<Object>();
		  StringBuffer sql2= new StringBuffer("SELECT attendance.attendanceId,attendance.attendanceName,attendance.attendanceDate,attendance.isabsence"
		  		+ ",student.studentMobilephone,student.studentNumber  FROM attendance INNER JOIN student ON attendance.attendanceName = student.studentName ");
				
		  if(studentName!= null && !studentName.trim().isEmpty() && studentName!=null){
				sql2.append(" and attendance.attendanceName like ? ");
				parmas2.add("%" +studentName+ "%");
				
			}
		  /*if(studentNumber!= 0 ){
				sql2.append(" and studentNumber like ? ");
				parmas2.add("%" +studentNumber+ "%");
				
			}*/
		  if(attendanceDate!= null && !attendanceDate.trim().isEmpty()){
				sql2.append(" and attendanceDate = ? ");
				parmas2.add(attendanceDate);
				
			}
		   
		   sql2.append(" limit ? , ? ");
		   parmas2.add(page.getIndex());
		   parmas2.add(page.getPageSize());
		   List date =this.getListMapBean(sql2.toString(),parmas2.toArray());
		   page.setDate(date);
			return page; 
	}
	/*
	 * 增加学生考勤方法
	 */
	@Override
	public int saveAttendance(Attendance attendance) {
		String sql ="insert into attendance(attendanceId,attendanceName,isabsence,attendanceDate) value(uuid(),?,?,now())";
		
		return this.update(sql, attendance.getAttendanceName(),attendance.getIsabsence());
	}
	/*
	 * 查询一个学生考勤的方法(列表中删除，修改用)
	 */
	@Override
	public Attendance getAttendanceById(String attendanceId) {
		String sql = "SELECT * FROM attendance WHERE attendanceId = ?";
		
		return this.getQuery(sql, attendanceId);
	}
	/*
	 * 删除方法
	 */
	@Override
	public int delAttendance(String attendanceId) {
		String sql="delete from attendance where attendanceId = ? ";
		
		return update(sql, attendanceId);
	}
	/*
	 * 修改学生考勤的方法
	 */
	@Override
	public int updateAttendance(Attendance attendance) {
		String sql="update attendance set attendanceName = ? , attendanceDate = now(),isabsence = ?  where attendanceId = ? ";
		return  this.update(sql,attendance.getAttendanceName(),attendance.getIsabsence(),attendance.getAttendanceId());
	}
	
	/*@Override
	public List<Integer> queryListStudentName() {
		// TODO Auto-generated method stub
		String sql="SELECT studentName FROM student";
		return getColumnList(sql);
		
	}*/
	
/*	public List<Integer> queryListMonthScore() {
	    
		String sql = "SELECT AVG(score.javaScore) FROM score  group by month(score.scoreDate) ORDER BY score.scoreDate";
		
		return getColumnList(sql);
	}
    */

}
