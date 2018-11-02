package cn.kgc.dao.impl;

import java.util.List;
import java.util.Map;

import cn.kgc.dao.BeanDao;
import cn.kgc.dao.ScoreAnalysisDao;
import cn.kgc.entity.Score;

public class ScoreAnalysisDaoImpl extends BeanDao<Score> implements ScoreAnalysisDao{
    
	/*
	 * 查询出1-12月份的java月平均分数
	 */
	@Override
	public List<Integer> queryListMonthScore(String scoreDate) {
	    
		String sql = "SELECT AVG(score.javaScore) FROM score where date_format(scoreDate,'%Y')= ?  group by month(score.scoreDate) ORDER BY score.scoreDate";
		
		return getColumnList(sql,scoreDate);
	}
    
	  /*
     * 查询java各分数段的百分比
     */
	@Override
	public Map<String,Object> queryListPercentageScore() {
		String sql = "SELECT "
					+"(SELECT ROUND(T1.co1/T0.co0*100,1) FROM"
					+"(SELECT COUNT(*) as co1 FROM score WHERE javaScore <'60')T1,"
					+"(SELECT COUNT(*) as co0 FROM score WHERE javaScore)T0)'小于60',"
					+"( SELECT ROUND(T2.co2/T0.co0*100,1) FROM"
					+"(SELECT COUNT(*) as co2 FROM score WHERE javaScore >='60' AND javaScore <'70')T2,"
					+"(SELECT COUNT(*) as co0 FROM score WHERE javaScore)T0)'60-70',"
					+"( SELECT ROUND(T3.co3/T0.co0*100,1) FROM"
					+"(SELECT COUNT(*) as co3 FROM score WHERE javaScore >='70' AND javaScore <'80')T3,"
					+"(SELECT COUNT(*) as co0 FROM score WHERE javaScore)T0)'70-80',"
					+"( SELECT ROUND(T4.co4/T0.co0*100,1) FROM"
					+"(SELECT COUNT(*) as co4 FROM score WHERE javaScore >='80' AND javaScore <'90')T4,"
					+"(SELECT COUNT(*) as co0 FROM score WHERE javaScore)T0)'80-90',"
					+"( SELECT ROUND(T5.co5/T0.co0*100,1) FROM"
					+"(SELECT COUNT(*) as co5 FROM score WHERE javaScore >='90')T5,"
					+"(SELECT COUNT(*) as co0 FROM score WHERE javaScore)T0)'大于90'";
		return getMapHandler(sql);
	}

	@Override
	public  Object[]  queryListPercentageScore2() {
		  
		String sql = "SELECT "
				+"(SELECT ROUND(T1.co1/T0.co0*100,1) FROM"
				+"(SELECT COUNT(*) as co1 FROM score WHERE javaScore <'60')T1,"
				+"(SELECT COUNT(*) as co0 FROM score WHERE javaScore)T0)'小于60',"
				+"( SELECT ROUND(T2.co2/T0.co0*100,1) FROM"
				+"(SELECT COUNT(*) as co2 FROM score WHERE javaScore >='60' AND javaScore <'70')T2,"
				+"(SELECT COUNT(*) as co0 FROM score WHERE javaScore)T0)'60-70',"
				+"( SELECT ROUND(T3.co3/T0.co0*100,1) FROM"
				+"(SELECT COUNT(*) as co3 FROM score WHERE javaScore >='70' AND javaScore <'80')T3,"
				+"(SELECT COUNT(*) as co0 FROM score WHERE javaScore)T0)'70-80',"
				+"( SELECT ROUND(T4.co4/T0.co0*100,1) FROM"
				+"(SELECT COUNT(*) as co4 FROM score WHERE javaScore >='80' AND javaScore <'90')T4,"
				+"(SELECT COUNT(*) as co0 FROM score WHERE javaScore)T0)'80-90',"
				+"( SELECT ROUND(T5.co5/T0.co0*100,1) FROM"
				+"(SELECT COUNT(*) as co5 FROM score WHERE javaScore >='90')T5,"
				+"(SELECT COUNT(*) as co0 FROM score WHERE javaScore)T0)'大于90'";
		
		return getArrayHandler(sql);
	}

}
