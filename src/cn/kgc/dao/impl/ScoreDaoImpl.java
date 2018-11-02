package cn.kgc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import sun.invoke.empty.Empty;
import cn.kgc.dao.BeanDao;
import cn.kgc.dao.ScoreDao;
import cn.kgc.entity.Page;
import cn.kgc.entity.Score;
import cn.kgc.entity.Student;
import cn.kgc.util.JDBCUtils;

import com.mchange.v1.util.ArrayUtils;
import com.mysql.jdbc.PreparedStatement;

public class ScoreDaoImpl extends BeanDao<Score> implements ScoreDao{
    
//	BeanListMapDao beanListMapDao = new BeanListMapDao();
	/*
	 * ��ҳ��������ѯ����
	 */
	@Override
	public Page findScore(Page page, String studentName,
			String scoreDate) {
		StringBuffer sql = new StringBuffer("select count(*) from score where 1=1 ");
		List<Object> parmas = new ArrayList<Object>();
		
		if(studentName != null && !studentName.trim().isEmpty()){
			sql.append(" and score.studentName like ?");
			parmas.add("%" +studentName+ "%");
		}
		/*if(studentNumber!= 0 ){
			sql.append(" and score.studentName = ( select student.studentName from student where student.studentNumber =? )");
			parmas.add("%" +studentNumber+ "%");
		}*/
		if(scoreDate!= null && !scoreDate.trim().isEmpty()){
			sql.append(" and scoreDate = ?");
			parmas.add(scoreDate);
		}	
			Score score = new Score();
			
			//ͨ�÷����������������д���
			Long a = (Long)this.getSinleValue(sql.toString(),parmas.toArray());
			long totalRecode=(long)a;
			
			//��page���е�totalRecode  ������ֵ
			page.setTotalRecode((int)totalRecode);
		  
		  List<Object> parmas2 = new ArrayList<Object>();
		  StringBuffer sql2= new StringBuffer("SELECT score.id , score.studentName , score.javaScore , score.javascriptScore , score.HTMLScore , score.SQLScore "
					+ ", score.scoreDate, student.studentNumber  FROM score  LEFT JOIN student ON score.studentName = student.studentName where 1=1 ");
				
		  if(studentName!= null && !studentName.trim().isEmpty()){
				sql2.append(" and score.studentName like ? ");
				parmas2.add("%" +studentName+ "%");
				
			}
		  /*if(studentNumber!= 0 ){
				sql2.append(" and studentNumber like ? ");
				parmas2.add("%" +studentNumber+ "%");
				
			}*/
		  if(scoreDate!= null && !scoreDate.trim().isEmpty()){
				sql2.append(" and scoreDate = ? ");
				parmas2.add(scoreDate);
				
			}
		   
		   sql2.append(" ORDER BY score.scoreDate limit ? , ? ");
		   parmas2.add(page.getIndex());
		   parmas2.add(page.getPageSize());
		   List date =this.getListMapBean(sql2.toString(),parmas2.toArray());
		   page.setDate(date);
			return page; 
		/*if (studentName==null && studentNumber==0) {
            String sql ="select count(*) from score";
			
			Score score = new Score();
			
			//ͨ�÷����������������д���
			Long a = (Long)this.getSinleValue(sql);
			long totalRecode=(long)a;
			
			//��page���е�totalRecode  ������ֵ
			page.setTotalRecode((int)totalRecode);
			//������ѯ
			sql="SELECT score.id , score.studentName , score.javaScore , score.javascriptScore , score.HTMLScore , score.SQLScore "
					+ ", score.scoreDate, student.studentNumber  FROM score  LEFT JOIN student ON score.studentName = student.studentName limit ?,?";
			List date =this.getListMapBean(sql, page.getIndex(),page.getPageSize());
			 
			page.setDate(date);
			
			return page;
		}else {
			
			return page;
		}*/
	       
	}
	
    /*
     * ��������	(non-Javadoc)
     * @see cn.kgc.dao.ScoreDao#saveScore(cn.kgc.entity.Score)
     */
	@Override
	public int saveScore(Score score) {
		 String sql ="insert into score(score.id , score.studentName, score.javaScore, score.javascriptScore,score.HTMLScore"
		 		+ ",score.SQLScore,score.scoreDate) value(uuid(),?,?,?,?,?,now())";
		return this.update(sql, score.getStudentName(),score.getJavaScore(),score.getjavascriptScore(),score.getHTMLScore(),score.getSQLScore());
	}

	/*
	 * ��ѯһ���ķ���(�б���ɾ�����޸���)
	 */
	@Override
	public Score getScoreById(String scoreId) {
		String sql="SELECT score.id , score.studentName , score.javaScore , score.javascriptScore , score.HTMLScore , score.SQLScore "
				+ ", score.scoreDate, student.studentNumber  FROM score  LEFT JOIN student ON score.studentName = student.studentName where score.id = ?";
		return this.getQuery(sql, scoreId);
	}
    
	/*
	 * ɾ������
	 */
	@Override
	public int delScore(String scoreId) {
		String sql="delete from score where score.id=?";
		return update(sql, scoreId);
	}
	
	/*
	 * �޸��û��ķ���
	 */
	@Override
	public int updateScore(Score score) {
		String sql="update score set  score.studentName=? , score.javaScore = ? , score.javascriptScore = ?, score.HTMLScore=?, score.SQLScore = ?,"
				+ "score.scoreDate = ? where id=?";
		return this.update(sql,score.getStudentName(),score.getJavaScore(),score.getjavascriptScore(),score.getHTMLScore(),score.getSQLScore(),score.getScoreDate(),score.getId());
	}
         
	
	/*
	 * �������鷽��2
	 */

public List<Map<String,Object>> findOrderNeedInfo()  {
	
	
		String sql="SELECT score.id , score.studentName , score.javaScore , score.javascriptScore , score.HTMLScore , score.SQLScore "
				+ ", score.scoreDate, student.studentNumber  FROM score  LEFT JOIN student ON score.studentName = student.studentName";
	
		
		return this.getListMapBean(sql);
	}


/*
 * ����ѧ�����id��ѯ�ɼ������޸�����
 */
public boolean queryScoreStudentNameById(String studentId) {
	String sql = "SELECT score.studentName FROM score  LEFT JOIN student ON score.studentName = student.studentName WHERE student.id= ? ";
	
	return getListMapBean2(sql, studentId);
}






	

     


	/*@Override
	public List<Score> queryListScore() {
		
		JDBCUtils jdbcUtils = new JDBCUtils();
		QueryRunner runner = new QueryRunner();
		//�������ݿ�
   	   Connection conn = jdbcUtils.connJDBC();
		
		String sql = "select score.id,score.student_name,score.javaScore,score.javascriptScore,score.HTMLScore,score.SQLScore, score.score_date from score";
		List<Score> score= getListBean(sql);
		
		String sql2="SELECT score.id,score.student_name AS studentName,score.javaScore,score.javascriptScore,score.HTMLScore,score.SQLScore"
				+ ",score.score_date,student.student_number FROM score  LEFT JOIN student ON score.student_name = student.student_name";
		try {
			List<Student> student = (List<Student>) runner.query(conn,sql2,  new BeanListHandler(Student.class));
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		//List<Student> student = (List<Student>) runner.query(sql2,  new	BeanListHandler(Student.class));
		// list=runner.query(conn, sql,new BeanListHandler<T>(type), params);
	}*/


/*
 * �������鷽��1
 * 
 */
/*@Override
public List<Map<String, Object>> findAll() {
	Connection conn = null;
	JDBCUtils jdbcUtils = new JDBCUtils();
    try {
        conn = jdbcUtils.connJDBC();
        // ͨ��sql������ѯ��Ա������Ϣ�����ڲ�������
        String sql="SELECT score.id , score.student_name  'studentName', score.javaScore , score.javascriptScore , score.HTMLScore , score.SQLScore "
				+ ", score.scoreDate, student.student_number  'studentNumber' FROM score  LEFT JOIN student ON score.student_name = student.student_name";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Map<String, Object>> list = convertList(rs);
        return list;
    } catch (SQLException e1) {
        e1.printStackTrace();
        throw new RuntimeException("��ѯ�û�ʧ��", e1);
    } finally {
    	jdbcUtils.close(conn);
    }
}*/


}
