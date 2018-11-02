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
	 * ��ҳ��������ѯ����
	 * parm Page<Student> page,String studentName,String studentNumber
	 * return page
	 */
	@Override
	public Page<Student> findStudent(Page<Student> page,String studentName,String studentNumber) {
		
		//����һ��StringBuffer���sql����д��һ�β�ѯ������sql��䣬׼��ƴ�Ӳ�ѯ�������
		StringBuffer sql = new StringBuffer("select count(*) from student where 1=1 ");
		//��Ϊҳ�����Ƕ����ѯ���������Դ���һ��list����
		List<Object> parmas = new ArrayList<Object>();
				//����ѧ��������ģ����ѯ���ж��û�����û����д��ѯ����
				if(studentName != null && !studentName.trim().isEmpty()){
					sql.append(" and studentName like ?");
					parmas.add("%" +studentName+ "%");
				}
				//����ѧ��ѧ������ѯ���ж��û�����û����д��ѯ����
				if(studentNumber != null && !studentNumber.trim().isEmpty()){
					sql.append(" and studentNumber = ?");
					parmas.add(studentNumber);
				}
		
		//ͨ�÷����������������д���
		Long a = (Long)this.getSinleValue(sql.toString(),parmas.toArray());
		long totalRecode=(long)a;
		//��page���е�totalRecode  ������ֵ
		page.setTotalRecode((int)totalRecode);
		
		//�ٴ���һ��StringBuffer���sql2����д��һ�β�ѯstudent��Ϣ��sql��䣬׼��ƴ�Ӳ�ѯ�������
		 StringBuffer sql2= new StringBuffer(" SELECT * FROM student WHERE 1=1 ");
		//��Ϊҳ�����Ƕ����ѯ�����������ٴ���һ��list����
		List<Object> parmas2 = new ArrayList<Object>();
		 
		//����ѧ��������ģ����ѯ���ж��û�����û����д��ѯ����
				if(studentName != null && !studentName.trim().isEmpty()){
					sql2.append(" and studentName like ?");
					parmas2.add("%" +studentName+ "%");
				}
				//����ѧ��ѧ������ѯ���ж��û�����û����д��ѯ����
				if(studentNumber != null && !studentNumber.trim().isEmpty()){
					sql2.append(" and studentNumber = ?");
					parmas2.add(studentNumber);
				}
				//ƴ�ӷ�ҳ����Ϣ
				sql2.append("ORDER BY createTime DESC limit ? , ?  ");
				parmas2.add(page.getIndex());     //����
				parmas2.add(page.getPageSize());  //ÿҳ��ʾ������
				
				//��sql���Ͳ�ѯ��������ͨ��BeanDao��getListBean�����У��ķ���ʹ��duUtils��BeanListHandler<T>(type)
				//��һ��list���Ͻ���
				List date =this.getListBean(sql2.toString(),parmas2.toArray());
				//�Ѳ���������ݷ���page���date������
				page.setDate(date);
								
		return page;
		
	}
    
	/*
	 * ���ӷ���
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
	 * ��ѯһ���ķ���(�б���ɾ�����޸���)
	 */
	@Override
	public Student getStudentById(String studentId) {
		
		String sql = "SELECT * FROM student WHERE id = ? ";
		
		return this.getQuery(sql, studentId);
	}
    
	/*
	 * ֻɾ��student���ɾ������
	 */
	@Override
	public int delStudent(String studentId) {
		String sql="delete from student where id=? ";
		
		return this.update(sql, studentId);
	}
    

	/*
	 * ��������ѧ������������£�ɾ������
	 */
	public int delStudentAndScore(String studentId){
		String sql="DELETE student , score FROM student , score WHERE student.id= ? AND student.studentName = score.studentName ";
		
		return this.update(sql, studentId);
	}
	
	/*
	 * �޸�ѧ���ķ���
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
	 * ��������ѧ������������£��޸ķ���
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
     * ��ѯ�������
     */
	@Override
	public List queryListStudent() {
		// TODO Auto-generated method stub
		String sql = "SELECT student.studentName,student.studentNumber,student.team,student.birthDate,student.graduationStudy,student.profession"
				+ ",student.graduationTime,student.studentMobilephone,student.studentEmail FROM student";
		return getListBean(sql);
	}
    
	/*
	 * ��ѯ����
	 * @see cn.kgc.dao.StudentDao#queryListStudentName()
	 */
	@Override
	public List<Student> queryListStudentName() {
		String sql = "SELECT student.studentName FROM student";
		
		return getListBean(sql);
	}

}
