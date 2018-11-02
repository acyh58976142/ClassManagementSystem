package cn.kgc.dao;

import java.util.List;
import java.util.Map;

import cn.kgc.entity.Score;
import cn.kgc.entity.User;

public interface ScoreAnalysisDao {
	
	/*
	 * 查询出1-12月份的java月平均分数
	 */
	public List<Integer> queryListMonthScore(String scoreDate);
	
    /*
     * 查询java各分数段的百分比
     */
	public Map<String,Object> queryListPercentageScore();
	
	public Object[] queryListPercentageScore2();
}
