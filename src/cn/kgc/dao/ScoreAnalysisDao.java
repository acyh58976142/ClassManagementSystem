package cn.kgc.dao;

import java.util.List;
import java.util.Map;

import cn.kgc.entity.Score;
import cn.kgc.entity.User;

public interface ScoreAnalysisDao {
	
	/*
	 * ��ѯ��1-12�·ݵ�java��ƽ������
	 */
	public List<Integer> queryListMonthScore(String scoreDate);
	
    /*
     * ��ѯjava�������εİٷֱ�
     */
	public Map<String,Object> queryListPercentageScore();
	
	public Object[] queryListPercentageScore2();
}
