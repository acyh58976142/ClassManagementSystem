package cn.kgc.dao;

import cn.kgc.entity.News;
import cn.kgc.entity.Page;

public interface New_UserDao {
//分页查询所有
Page<News> news_fen(Page<News> page);
//根据类型分页
Page<News> news_fenByType(Page<News> page,String type);
//点击标题，跳转到具体页面，根据标题查询
Page<News>  news_fenByTitle(Page<News> page,String title);
//查询一个
News news_cha1(News news);
//模糊查询by---title
Page<News> new_mohu_title(Page<News> page,String getWhat);
//模糊查询by---content
Page<News> new_mohu_content(Page<News> page,String getWhat);
//模糊查询by---date
Page<News> new_mohu_date(Page<News> page,String getWhat);
//删除方法
int new_del(News news);
//添加方法
int new_add(News news);
//修改方法
int new_change(News news,String titles);

}
