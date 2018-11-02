package cn.kgc.service.impl;

import java.util.List;
import java.util.Map;

import cn.kgc.dao.ScoreAnalysisDao;
import cn.kgc.dao.impl.ScoreAnalysisDaoImpl;
import cn.kgc.entity.Score;
import cn.kgc.service.ScoreAnalysisService;

public class ScoreAnalysisServiceImpl implements ScoreAnalysisService{
	
	ScoreAnalysisDao scoreAnalysisDao = new ScoreAnalysisDaoImpl();
    
	/*
	 * ��ѯ��1-12�·ݵ�java��ƽ������
	 */
	@Override
	public List<Integer> queryListMonthScore(String scoreDate) {
		
		return scoreAnalysisDao.queryListMonthScore(scoreDate);
	}
    
	/*
     * ��ѯjava�������εİٷֱ�
     */
	@Override
	public Map<String, Object> queryListPercentageScore() {
		
		return scoreAnalysisDao.queryListPercentageScore();
	}

	@Override
	public Object[] queryListPercentageScore2() {
		
		return scoreAnalysisDao.queryListPercentageScore2();
	}

	
	
}
