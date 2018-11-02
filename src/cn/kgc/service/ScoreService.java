package cn.kgc.service;

import java.util.List;
import java.util.Map;

import cn.kgc.entity.Page;
import cn.kgc.entity.Score;

public interface ScoreService {
	
	/*
	 * ��ҳ��������ѯ����
	 */
	Page<Score> page = new Page<Score>();
	public Page<Score> findScore(String pageNum, int pageSize,String studentName,String scoreDate);
	
	/*
	 * ���ӷ���
	 */
	public int saveScore(Score score);
	
	/*
	 * ��ѯһ���ķ���(�б���ɾ�����޸���)
	 */
	Score getScoreById(String scoreId);
	
	
	/*
	 * ɾ������
	 */
	public int delScore(String scoreId);
	
	
	/*
	 * �޸��û��ķ���
	 */
	int updateScore(Score score);
	
	 /*
     * ��ѯ�������
     */
	public List<Map<String, Object>> findOrderNeedInfo();
}
