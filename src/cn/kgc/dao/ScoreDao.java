package cn.kgc.dao;

import java.util.List;
import java.util.Map;

import cn.kgc.entity.Page;
import cn.kgc.entity.Score;
import cn.kgc.entity.Student;
import cn.kgc.entity.User;

public interface ScoreDao {
	

	/*
	 * ��ҳ��������ѯ����
	 */
	Page<Score> page = new Page<Score>();
	public Page<Score> findScore(Page<Score> page,String studentName,String scoreDate);
	
	
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
	 * �޸ĳɼ��ķ���
	 */
	int updateScore(Score score);
	
	/*
	 * ���������ѯ�������
	 */
	public List<Map<String,Object>> findOrderNeedInfo();
	
    
	/*
	 * ����ѧ�����id��ѯ�ɼ������޸�����
	 */
	public boolean queryScoreStudentNameById(String studentId);
	
	
	 /*
     * ��ѯ�������
     */
//	public List<Map<String, Object>>  findAll();

}
