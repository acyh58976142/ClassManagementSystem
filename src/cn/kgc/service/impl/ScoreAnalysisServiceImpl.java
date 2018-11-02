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
	 * 查询出1-12月份的java月平均分数
	 */
	@Override
	public List<Integer> queryListMonthScore(String scoreDate) {
		
		return scoreAnalysisDao.queryListMonthScore(scoreDate);
	}
    
	/*
     * 查询java各分数段的百分比
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
