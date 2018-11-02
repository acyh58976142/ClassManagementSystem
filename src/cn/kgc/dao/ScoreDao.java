package cn.kgc.dao;

import java.util.List;
import java.util.Map;

import cn.kgc.entity.Page;
import cn.kgc.entity.Score;
import cn.kgc.entity.Student;
import cn.kgc.entity.User;

public interface ScoreDao {
	

	/*
	 * 分页带索引查询方法
	 */
	Page<Score> page = new Page<Score>();
	public Page<Score> findScore(Page<Score> page,String studentName,String scoreDate);
	
	
	/*
	 * 增加方法
	 */
	public int saveScore(Score score);
	
	
	/*
	 * 查询一个的方法(列表中删除，修改用)
	 */
	Score getScoreById(String scoreId);
	
	
	/*
	 * 删除方法
	 */
	public int delScore(String scoreId);
	
	
	/*
	 * 修改成绩的方法
	 */
	int updateScore(Score score);
	
	/*
	 * 两表联查查询多个方法
	 */
	public List<Map<String,Object>> findOrderNeedInfo();
	
    
	/*
	 * 根据学生表的id查询成绩表有无该姓名
	 */
	public boolean queryScoreStudentNameById(String studentId);
	
	
	 /*
     * 查询多个方法
     */
//	public List<Map<String, Object>>  findAll();

}
