package cn.kgc.dao.impl;

import java.util.List;

import cn.kgc.dao.Plan_BaseDao;
import cn.kgc.dao.Plan_UserDao;
import cn.kgc.entity.Page;
import cn.kgc.entity.Plan;

public class Plan_BaseDaoImpl extends Plan_BaseDao<Plan> implements Plan_UserDao {
  
	
	@Override
	public Plan plan_getID(Plan plan) {
		String sql="SELECT * FROM teacher WHERE teatherName=?";
		return this.plan_getQuery(sql,plan.getTeacher_names());
	}


  
	@Override
	public Plan plan_cha(String id) {
		String sql="select plan_date,plan_titles,plan_concent,teacher_names,id,job_number from teacher_plan where id=?";
		return this.plan_getQuery(sql,id);
	}

	@Override
	public List<Plan> plan_chas() {
		String sql="SELECT plan_date,plan_titles,plan_concent,teacher_names,id,job_number FROM teacher_plan";
		return this.plan_getListBean(sql);
	}
	@Override
	public Page<Plan> findBook(Page<Plan> page) {
		/**
		 * pageNum  pageSize   鍓嶅彴鑾峰彇
		 * 
		 * totalPage   totalRecode   date  index  
		 */
		
		String sql="SELECT COUNT(*)  FROM teacher_plan";
		Long a =(Long) this.getSinvelue(sql);
		long totalRecod=(long)a;
		System.out.println(totalRecod);
		page.setTotalRecode((int)totalRecod);
		sql="SELECT id,plan_date,plan_titles,plan_concent,teacher_names,job_number FROM teacher_plan LIMIT ?,?";
		List<Plan> date = this.plan_getListBean(sql, page.getIndex(),page.getPageSize());
		System.out.println("sql璇彞鏌ヨ缁撴瀯"+date);
		page.setDate(date);
		return page;
	}
	@Override
	public int Plan_del(String id) {
		String sql="DELETE FROM `plan`.`teacher_plan` WHERE `id` = ?";
		return this.plan_update(sql, id);
	}

	@Override
	public int plan_save(Plan plan) {
		String sql="INSERT INTO teacher_plan(id,plan_date,plan_titles,plan_concent,teacher_names,job_number) VALUES (UUID(),?,?,?,?,2017666)";
		return this.plan_update(sql,plan.getPlan_date(),plan.getPlan_titles(),plan.getPlan_concent(),plan.getTeacher_names());
	}
	@Override
	public int plan_chang(Plan plan) {
		
		String sql="UPDATE teacher_plan SET plan_date =?,plan_titles =?,plan_concent=?,teacher_names=? WHERE id=?";
		return this.plan_update(sql,plan.getPlan_date(),plan.getPlan_titles(),plan.getPlan_concent(),plan.getTeacher_names(),plan.getId());
	}


	@Override
	public Page<Plan> findPlan_jobnum(Page<Plan> page) {
		String sql="SELECT COUNT(*)  FROM plan_dates";
		Long a =(Long) this.getSinvelue(sql);
		long totalRecod=(long)a;
		System.out.println(totalRecod);
		if(totalRecod!=0){
	  //	System.out.println("date琛ㄥ凡缁忓瓨鍦�);
			sql="DROP TABLE plan_dates";
			int i = this.plan_drop(sql);
		//	System.out.println("i涓�鍒欏垹闄ゆ垚鍔�+i);
		}
		page.setTotalRecode((int)totalRecod);
		sql="CREATE TABLE plan_dates AS SELECT id,plan_date,plan_titles,plan_concent,teacher_names,job_number FROM teacher_plan ORDER BY job_number";
		int drop = this.plan_drop(sql);
		//System.out.println("drop涓�鍒欐柊寤烘垚鍔�+drop);
		sql= "SELECT id,plan_date,plan_titles,plan_concent,teacher_names,job_number FROM plan_dates LIMIT ?,?";
		List<Plan> date = this.plan_getListBean(sql, page.getIndex(),page.getPageSize());
		//System.out.println("sql璇彞鏌ヨ缁撴瀯"+date);
		page.setDate(date);
		return page;
	}

	@Override
	public Page<Plan> findPlan_date(Page<Plan> page) {
		String sql="SELECT COUNT(*)  FROM plan_dates";
		Long a =(Long) this.getSinvelue(sql);
		long totalRecod=(long)a;
		System.out.println(totalRecod);
		if(totalRecod!=0){
		//	System.out.println("date琛ㄥ凡缁忓瓨鍦�);
			sql="DROP TABLE plan_dates";
			int i = this.plan_drop(sql);
		//	System.out.println("i涓�鍒欏垹闄ゆ垚鍔�+i);
		}
		page.setTotalRecode((int)totalRecod);
		sql="CREATE TABLE plan_dates AS SELECT id,plan_date,plan_titles,plan_concent,teacher_names,job_number FROM teacher_plan ORDER BY plan_date";
		int drop = this.plan_drop(sql);
		//System.out.println("drop涓�鍒欐柊寤烘垚鍔�+drop);
		sql= "SELECT id,plan_date,plan_titles,plan_concent,teacher_names,job_number FROM plan_dates LIMIT ?,?";
		List<Plan> date = this.plan_getListBean(sql, page.getIndex(),page.getPageSize());
		System.out.println("sql璇彞鏌ヨ缁撴瀯"+date);
		page.setDate(date);
		return page;
		
	}
	


	public void plan_sousuodel(){
	
		
			String sqlss="drop table plan_sousuo";
			int i = this.plan_drop(sqlss);
		//	System.out.println("濡傛灉i涓�鍒欏垹闄ゆ垚鍔�+i);
		
		
	}
	
	public void plan_sousuoadd(String ss){
		this.plan_sousuodel();
		//System.out.println("鍒ゆ柇琛ㄤ功鍚﹀瓨鍦ㄥ畬鎴�);
		String sqls="CREATE TABLE plan_sousuo AS SELECT id,plan_date,plan_titles,plan_concent,teacher_names,job_number FROM teacher_plan WHERE plan_titles LIKE '%"+ss+"%'"; 
		int mohuadd = this.plan_drop(sqls);
		//System.out.println("妯＄硦鏌ヨ骞跺缓琛ㄥ畬鎴�+mohuadd);
		sqls="select * from plan_sousuo";
		List<Plan> list = this.plan_getListBean(sqls);
		//System.out.println("瀵规柊寤虹殑琛ㄦ牸杩涜鏌ヨ鎵�湁锛堟牴鎹爣棰橈級"+list);
		if(list==null){
			 this.plan_sousuodel();
			sqls="CREATE TABLE plan_sousuo AS SELECT id,plan_date,plan_titles,plan_concent,teacher_names,job_number FROM teacher_plan WHERE teacher_names LIKE '%"+ss+"%'"; 
              this.plan_drop(sqls);
               sqls="select * from plan_sousuo";
       		List<Plan> list1 = this.plan_getListBean(sqls);
       		//System.out.println("瀵规柊寤虹殑琛ㄦ牸杩涜鏌ヨ鎵�湁锛堟牴鎹畁ame锛�+list1);
       		if(list1==null){
       			this.plan_sousuodel();
       			sqls="CREATE TABLE plan_sousuo AS SELECT id,plan_date,plan_titles,plan_concent,teacher_names,job_number FROM teacher_plan WHERE job_number LIKE '%"+ss+"%'"; 
       		   this.plan_drop(sqls);
       		    sqls="select * from plan_sousuo";
         		List<Plan> list2 = this.plan_getListBean(sqls); 
         		//System.out.println("瀵规柊寤虹殑琛ㄦ牸杩涜鏌ヨ鎵�湁锛堟牴鎹畁umber锛�+list2);
         		
         		
       		
       		}
               
		}
		
		
		
	}
	
	
	//鍒嗛〉妯＄硦鏌ヨ
	@Override
	public Page<Plan> findBook_mohu(Page<Plan> page) {
		/**
		 * pageNum  pageSize   鍓嶅彴鑾峰彇
		 * 
		 * totalPage   totalRecode   date  index  
		 */
		
		String sql="SELECT COUNT(*)  FROM plan_sousuo";
		Long a =(Long) this.getSinvelue(sql);
		long totalRecod=(long)a;
		//System.out.println("鍒嗛〉妯＄硦鏌ヨ缁撴灉鐨勬潯鏁�+totalRecod);
		page.setTotalRecode((int)totalRecod);
	//	System.out.println("鏌ヨ鎬讳綋娆ц檸"+page.getTotalRecode());
		sql="SELECT id,plan_date,plan_titles,plan_concent,teacher_names,job_number FROM plan_sousuo LIMIT ?,?";
		List<Plan> date = this.plan_getListBean(sql, page.getIndex(),page.getPageSize());
		//System.out.println("sql璇彞鏌ヨ缁撴瀯妯＄硦chaxun"+date);
		page.setDate(date);
		return page;
	}
	
	
}
