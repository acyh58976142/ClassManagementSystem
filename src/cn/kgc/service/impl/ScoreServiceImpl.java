package cn.kgc.service.impl;

import java.util.List;
import java.util.Map;

import cn.kgc.dao.ScoreDao;
import cn.kgc.dao.impl.ScoreDaoImpl;
import cn.kgc.entity.Page;
import cn.kgc.entity.Score;
import cn.kgc.entity.User;
import cn.kgc.service.ScoreService;

public class ScoreServiceImpl implements ScoreService{
	
	ScoreDao scoreDao = new ScoreDaoImpl();
	
	/*
	 * ��ҳ��������ѯ����
	 */
	@Override
	public Page<Score> findScore(String pageNum, int pageSize, String studentName,
			String scoreDate) {
		int pn=1;
		try {
			pn=Integer.parseInt(pageNum);
		} catch (Exception e) {
		}
				
		Page<Score> page = new Page<Score>();
		
		page.setPageNum(pn);
		page.setPageSize(pageSize);
		
		return scoreDao.findScore(page,studentName,scoreDate);
	}
	

	/*
	 * ���ӳɼ�����
	 */
	@Override
	public int saveScore(Score score) {
		
		return scoreDao.saveScore(score);
	}

	/*
	 * ��ѯһ���ķ���(�б���ɾ�����޸���)
	 */
	@Override
	public Score getScoreById(String scoreId) {
		
		return scoreDao.getScoreById(scoreId);
	}
	
	/*
	 * ɾ������
	 */
	@Override
	public int delScore(String scoreId) {
		
		return scoreDao.delScore(scoreId);
	}
    
	/*
	 * �޸��û��ķ���
	 */
	@Override
	public int updateScore(Score score) {
		
		return scoreDao.updateScore(score);
	}

	@Override
	public List<Map<String, Object>> findOrderNeedInfo() {
		
		return scoreDao.findOrderNeedInfo();
	}

}
