package cn.kgc.service;

import java.util.List;
import java.util.Map;

import cn.kgc.entity.Page;
import cn.kgc.entity.Score;

public interface ScoreService {
	
	/*
	 * 分页带索引查询方法
	 */
	Page<Score> page = new Page<Score>();
	public Page<Score> findScore(String pageNum, int pageSize,String studentName,String scoreDate);
	
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
	 * 修改用户的方法
	 */
	int updateScore(Score score);
	
	 /*
     * 查询多个方法
     */
	public List<Map<String, Object>> findOrderNeedInfo();
}
