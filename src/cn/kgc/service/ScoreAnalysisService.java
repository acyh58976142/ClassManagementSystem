package cn.kgc.service;

import java.util.List;
import java.util.Map;

public interface ScoreAnalysisService {
	
	/*
	 * ��ѯ��1-12�·ݵ�java��ƽ������
	 */
	public List<Integer> queryListMonthScore(String scoreDate);
   
	 /*
     * ��ѯjava�������εİٷֱ�
     */
	public Map<String, Object> queryListPercentageScore();
	
	public Object[] queryListPercentageScore2();
	
}
