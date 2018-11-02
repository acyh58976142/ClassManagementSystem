package cn.kgc.service;

import java.util.List;
import java.util.Map;

public interface ScoreAnalysisService {
	
	/*
	 * 查询出1-12月份的java月平均分数
	 */
	public List<Integer> queryListMonthScore(String scoreDate);
   
	 /*
     * 查询java各分数段的百分比
     */
	public Map<String, Object> queryListPercentageScore();
	
	public Object[] queryListPercentageScore2();
	
}
