package cn.kgc.dao;

import java.util.List;

import cn.kgc.entity.Page;
import cn.kgc.entity.Plan;

public interface Plan_UserDao {
	
	
public Plan plan_getID(Plan plan);
 //锟斤拷删锟斤拷
 public int plan_zsg(Plan plan);
 //锟斤拷1锟斤拷
 public Plan plan_cha(String id);
 //锟斤拷锟斤拷锟�
 public List<Plan> plan_chas();
 //鍒嗛〉鏌ヨ
 public Page<Plan> findBook(Page<Plan> page);
 //鍒犻櫎鏂规硶
 public int Plan_del(String id);
 //娣诲姞
 public int plan_save(Plan plan);
 //鏇存敼
 public int plan_chang(Plan plan);
 //鎸夊伐鍙疯繘琛屽垎椤垫煡璇�
 public Page<Plan> findPlan_jobnum(Page<Plan> page);
 //鎸夋棩鏈熸煡璇�
 public Page<Plan> findPlan_date(Page<Plan>page);
 
 public void plan_sousuoadd(String ss);
Page<Plan> findBook_mohu(Page<Plan> page);
 
 /*Page<Plan> plan_sousuo(Page<Plan> page,String sql);*/
}
