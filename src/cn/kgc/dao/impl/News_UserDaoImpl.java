package cn.kgc.dao.impl;

import java.util.List;

import cn.kgc.dao.New_UserDao;
import cn.kgc.dao.Plan_BaseDao;
import cn.kgc.entity.News;
import cn.kgc.entity.Page;

public class News_UserDaoImpl extends Plan_BaseDao<News> implements New_UserDao {
//分页首页
	@Override
	public Page<News> news_fen(Page<News> page) {
		String sql="SELECT COUNT(*)  FROM news";
		Long a =(Long) this.getSinvelue(sql);
		long totalRecod=(long)a;
		System.out.println(totalRecod);
		page.setTotalRecode((int)totalRecod);
		System.out.println("index="+page.getIndex());
		System.out.println("size="+page.getPageSize());
		sql="SELECT new_title,new_content,new_date,new_type FROM news LIMIT ?,?";
		List<News> new_date = this.plan_getListBean(sql, page.getIndex(),page.getPageSize());
		System.out.println("hahahh:--"+this.plan_getListBean(sql, page.getIndex(),page.getPageSize()));
		page.setDate(new_date);
		page.setPageSize(10);
		System.out.println("impl类中查询方法得到的date"+page.getDate());
		return page;
	}
//根据type查询
	 //type表彰公告
	@Override
	public Page<News> news_fenByType(Page<News> page,String type) {
		String sql="SELECT COUNT(*)  FROM news where new_type='"+type+"'";
		Long a =(Long) this.getSinvelue(sql);
		long totalRecod=(long)a;
		System.out.println(totalRecod);
		page.setTotalRecode((int)totalRecod);
		System.out.println("index="+page.getIndex());
		System.out.println("size="+page.getPageSize());
		sql="SELECT new_title,new_content,new_date,new_type FROM news where new_type='"+type+"' LIMIT ?,?";
		List<News> new_date = this.plan_getListBean(sql, page.getIndex(),page.getPageSize());
		System.out.println("hahahh:--"+this.plan_getListBean(sql, page.getIndex(),page.getPageSize()));
		page.setDate(new_date);
		page.setPageSize(10);
		System.out.println("impl类中查询方法得到的date"+page.getDate());
		return page;
	}
//根据title查询
	@Override
	public Page<News> news_fenByTitle(Page<News> page,String title) {
		String sql="SELECT COUNT(*)  FROM news";
		Long a =(Long) this.getSinvelue(sql);
		long totalRecod=(long)a;
		System.out.println(totalRecod);
		page.setTotalRecode((int)totalRecod);
		System.out.println("index="+page.getIndex());
		System.out.println("size="+page.getPageSize());
		sql="SELECT new_title,new_content,new_date,new_type FROM news where new_type='"+title+"' LIMIT ?,?";
		List<News> new_date = this.plan_getListBean(sql, page.getIndex(),page.getPageSize());
		System.out.println("hahahh:--"+this.plan_getListBean(sql, page.getIndex(),page.getPageSize()));
		page.setDate(new_date);
		page.setPageSize(10);
		System.out.println("impl类中查询方法得到的date"+page.getDate());
		return page;
	}
	@Override
	public News news_cha1(News news) {
		String sql="SELECT new_title,new_content,new_date,new_type FROM news where new_title=?";
        news = this.plan_getQuery(sql, news.getNew_title());
		return news;
	}
	//模糊查询by--title
	@Override
	public Page<News> new_mohu_title(Page<News> page, String getWhat) {
		String sql="SELECT COUNT(*)  FROM news";
		Long a =(Long) this.getSinvelue(sql);
		long totalRecod=(long)a;
		System.out.println(totalRecod);
		page.setTotalRecode((int)totalRecod);
		System.out.println("index="+page.getIndex());
		System.out.println("size="+page.getPageSize());
		sql="SELECT new_title,new_content,new_date,new_type FROM news where new_title like '%"+getWhat+"%' LIMIT ?,?";
		List<News> new_date = this.plan_getListBean(sql, page.getIndex(),page.getPageSize());
		int size = new_date.size();
		System.out.println("查询后的条数"+size);
		page.setTotalRecode(size);
		System.out.println("hahahh:--"+this.plan_getListBean(sql, page.getIndex(),page.getPageSize()));
		page.setDate(new_date);
		page.setPageSize(10);
		System.out.println("impl类中查询方法得到的date"+page.getDate());
		return page;
	}
	//模糊查询by--content
	@Override
	public Page<News> new_mohu_content(Page<News> page, String getWhat) {
		String sql="SELECT COUNT(*)  FROM news";
		Long a =(Long) this.getSinvelue(sql);
		long totalRecod=(long)a;
		System.out.println(totalRecod);
		page.setTotalRecode((int)totalRecod);
		System.out.println("index="+page.getIndex());
		System.out.println("size="+page.getPageSize());
		sql="SELECT new_title,new_content,new_date,new_type FROM news where new_content like '%"+getWhat+"%' LIMIT ?,?";
		List<News> new_date = this.plan_getListBean(sql, page.getIndex(),page.getPageSize());
		int size = new_date.size();
		System.out.println("查询后的条数"+size);
		page.setTotalRecode(size);
		System.out.println("hahahh:--"+this.plan_getListBean(sql, page.getIndex(),page.getPageSize()));
		page.setDate(new_date);
		page.setPageSize(10);
		System.out.println("impl类中查询方法得到的date"+page.getDate());
		return page;
	}
	//模糊查询by--date
	@Override
	public Page<News> new_mohu_date(Page<News> page, String getWhat) {
		String sql="SELECT COUNT(*)  FROM news";
		Long a =(Long) this.getSinvelue(sql);
		long totalRecod=(long)a;
		System.out.println(totalRecod);
		page.setTotalRecode((int)totalRecod);
		System.out.println("index="+page.getIndex());
		System.out.println("size="+page.getPageSize());
		sql="SELECT new_title,new_content,new_date,new_type FROM news where new_date like '%"+getWhat+"%' LIMIT ?,?";
		List<News> new_date = this.plan_getListBean(sql, page.getIndex(),page.getPageSize());
		int size = new_date.size();
		System.out.println("查询后的条数"+size);
		page.setTotalRecode(size);
		System.out.println("hahahh:--"+this.plan_getListBean(sql, page.getIndex(),page.getPageSize()));
		page.setDate(new_date);
		page.setPageSize(10);
		System.out.println("impl类中查询方法得到的date"+page.getDate());
		return page;
	}
	@Override
	public int new_del(News news) {
		String sql="DELETE FROM news WHERE new_title =?";
		return this.plan_update(sql,news.getNew_title());
	}
	
	//新增方法
	@Override
	public int new_add(News news) {
		String sql="INSERT INTO news (new_title,new_content,new_date,new_type,img_path) VALUES (?,?,?,?,?)";
		return this.plan_update(sql, news.getNew_title(),news.getNew_content(),news.getNew_date(),news.getNew_type(),news.getImg_path());
	}
	//修改方法
	@Override
	public int new_change(News news,String titles) {
		String sql="update news set new_title=?,new_content=?,new_date=?,new_type=?,img_path=? where new_title='"+titles+"'";
		return this.plan_update(sql, news.getNew_title(),news.getNew_content(),news.getNew_date(),news.getNew_type(),news.getImg_path());
	}
	
}
