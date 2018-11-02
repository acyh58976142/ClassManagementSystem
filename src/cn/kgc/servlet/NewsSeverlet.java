package cn.kgc.servlet;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.dao.New_UserDao;
import cn.kgc.dao.impl.News_UserDaoImpl;
import cn.kgc.entity.News;
import cn.kgc.entity.Page;

/**
 * Servlet implementation class NewsSeverlet
 */
@WebServlet("/NewsSeverlet")
public class NewsSeverlet extends BeanServlet {
	private static final long serialVersionUID = 1L;
	 New_UserDao dao = new News_UserDaoImpl();
  
	protected void newFen1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*request.setCharacterEncoding("UTF-8");  
		String yinCang = request.getParameter("yinCang");
		Integer yin = Integer.valueOf(yinCang);
		int yincangs=(int)yin;
		request.setAttribute("yinCang", yincangs);
		*/
		
		
		
		
		//获取当前页码
		      String pagenum = request.getParameter("pagenum");
		      //创建实体类对象
		      Page<News> page = new Page<News>();
		      //页码转型
		      Integer new_pagenum = Integer.valueOf(pagenum);
		      //实体类中页码赋值
		      page.setPageNum((int)new_pagenum);
		      //赋值size
		      page.setPageSize(10);
		      int a=1;
		      int b=a+page.getPageNum();
		      //调用分页方法
		      Page<News> news_fen = dao.news_fen(page);
		      //查看是否查询成功
		      if(news_fen!=null){
		    	//将查询的page放入req域中
			      request.setAttribute("page", news_fen);
			      //将page中的date放入域中
			      request.setAttribute("News",news_fen.getDate());
			      request.setAttribute("chose", "no");
			  /*    request.setAttribute("yinCang", yin);*/
			       
			      //转发
			      request.getRequestDispatcher("/web/home/list-text1.jsp").forward(request, response);
		      }
	}
	
	//根据类型分页的servlet
	protected void news_fenByType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//获取当前页码
		      String pagenum = request.getParameter("pagenum");
		      String type = request.getParameter("type");
		      //请求乱码
		      type = new String(type.getBytes("ISO-8859-1"),"UTF-8");
		      //创建实体类对象
		      Page<News> page = new Page<News>();
		      //页码转型
		      Integer new_pagenum = Integer.valueOf(pagenum);
		      //实体类中页码赋值
		      page.setPageNum((int)new_pagenum);
		      //赋值size
		      page.setPageSize(10);
		      int a=1;
		      int b=a+page.getPageNum();
		
		      //调用分页方法
		     Page<News> news_fen = dao.news_fenByType(page, type);
		      //查看是否查询成功
		      if(news_fen!=null){
		    	//将查询的page放入req域中
			      request.setAttribute("page", news_fen);
			      //将page中的date放入域中
			      request.setAttribute("News",news_fen.getDate());
			      request.setAttribute("chose", "have");
			      //转发
			      request.getRequestDispatcher("/web/home/list-text1.jsp").forward(request, response);
		      }
	}
	//方法选择--类型和所有的区别
	protected void new_chose(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chose = request.getParameter("chose");
		if(chose.equals("have")){
			this.news_fenByType(request, response);
		}else {
			this.newFen1(request, response);
		}
	}
	//查询一条的方法
	protected void news_cha1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chose = request.getParameter("chose");
		String pagenum = request.getParameter("pagenum");
		String type = request.getParameter("type");
		
		String title = request.getParameter("title");
		title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
		News news = new News();
		news.setNew_title(title);
		news = dao.news_cha1(news);
		request.setAttribute("news",news);
		request.setAttribute("pagenum", pagenum);
		request.setAttribute("chose", chose);
		request.setAttribute("type", type);
		request.getRequestDispatcher("/web/home/detail1.jsp").forward(request, response);
		
	}
	//模糊查询方法
	protected void news_mohu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 ArrayList<News> list = new ArrayList<News>();
	   	String getWhat = request.getParameter("textfield");
	    String pagenums = request.getParameter("pagenum");
	   Integer a = Integer.valueOf(pagenums);
	    Page<News> page = new Page<News>();
	    page.setPageNum((int)a);
	    page.setPageSize(10);
	    Page<News> mohu_title = dao.new_mohu_title(page, getWhat);
	       if(mohu_title.getDate()!=null){
	    	
	    	  for(int i=0;i<mohu_title.getDate().size();i++){
	    		list.add(mohu_title.getDate().get(i));
	    	}
	    }
	     Page<News> mohu_content = dao.new_mohu_content(page, getWhat);
		if(mohu_content.getDate()!=null){
		  for(int i=0;i<mohu_content.getDate().size();i++){
		   	list.add(mohu_content.getDate().get(i));
		  	}
		}
		  Page<News> mohu_date = dao.new_mohu_date(page, getWhat);
		if(mohu_date.getDate()!=null){
				 	for(int i=0;i<mohu_date.getDate().size();i++){
		 	   list.add(mohu_date.getDate().get(i));
		 	}
		}
	    
	   
	   
	    page.setDate(list);
	  
	    if(page.getDate()!=null){
	    	//将查询的page放入req域中
		      request.setAttribute("page", page);
		      //将page中的date放入域中
		      request.setAttribute("News",page.getDate());
		      request.setAttribute("chose", "no");
		      //转发
		      request.getRequestDispatcher("/web/home/list-text1.jsp").forward(request, response);
	      }
	}
	//删除
	protected void new_del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
	    title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
	    News news = new News();
	    news.setNew_title(title);
			int new_del = dao.new_del(news);
			String pagenum = request.getParameter("pagenum");
			Integer pagenums = Integer.valueOf(pagenum);
			Page<News> page = new Page<News>();
			page.setPageNum((int)pagenums);
			page.setPageSize(10);
			Page<News> news_del = dao.news_fen(page);
			//将查询的page放入req域中
		      request.setAttribute("page", news_del);
		      //将page中的date放入域中
		      request.setAttribute("News",news_del.getDate());
		      request.setAttribute("chose", "no");
		      request.setCharacterEncoding("UTF-8");  
				String yinCang = request.getParameter("yinCang");
				int yincangs;
				if(yinCang!=""){
					Integer yin = Integer.valueOf(yinCang);
					 yincangs=(int)yin;
					request.setAttribute("yinCang", yincangs);
				}else{
					yincangs=0;
				}
				
		       
		      //转发
		      request.getRequestDispatcher("/web/home/list-text1.jsp").forward(request, response);
	}
	//增加和修改的跳转页面方法
protected void adode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String add = request.getParameter("add");
	//如果adds为a，则执行添加方法，否则执行修改
	if(add.equals("a")){
		System.out.println("添加方法");
		String title = request.getParameter("titles");
		System.out.println("修改方法获取="+title);
		News news = new News();
		news.setNew_title(title);
		
		
		request.setAttribute("news", news);
		request.getRequestDispatcher("moban2/article-add.jsp").forward(request, response);
	}else{
		System.out.println("修改方法");
		String title = request.getParameter("title");
		System.out.println("判断修稿="+title);
		request.setCharacterEncoding("UTF-8");
		 title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
		if(!title.equals(null)){
			News news = new News();
			news.setNew_title(title);
			News news_cha1 = dao.news_cha1(news);
			String pagenum = request.getParameter("pagenum");
			request.setAttribute("pagenum", pagenum);
			request.setAttribute("news",news_cha1);
			request.getRequestDispatcher("moban2/article-add.jsp").forward(request, response);
		}

	}
		}
		protected void save_or_del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//获取输入的标题
			request.setCharacterEncoding("UTF-8");
			String new_title = request.getParameter("articletitle");
			
			 System.out.println("获取title"+new_title);
			 //获取选择的类型
			 String type = request.getParameter("articlecolumn");
			 System.out.println("type="+type);
			 String new_type=null;
			  switch(type){
			  case "1":
				  new_type="表彰公告";
				  break;
			  case "11":
				  new_type="惩罚公告";
				  break;
			  case "12":
				  new_type="校园趣事";
				  break;
			  case "13":
				  new_type="体育活动";
				  break;
			  default:
				  break;
				  
			  }
			 //获取数日的日期--ok
			 String new_date = request.getParameter("commentdatemin");
			 System.out.println("日期=="+new_date);
			 new_date = new String(new_date.getBytes("ISO-8859-1"),"UTF-8");
			 //时间String转date
			    
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-mm-dd, 会出现时间不对, 因为小写的mm是代表: 秒
			      
				java.util.Date date=null;
				try {
					 date = sdf.parse(new_date);
					 System.out.println("转换后的日期"+date);
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				//时间格式化
			 SimpleDateFormat dateType = new SimpleDateFormat("yyyy-MM-dd");
			 String dateString = dateType.format(date);
			  //输出时间格式
			 System.out.println("获取的时间String类型"+dateString);
			 //格式化后的转date'
			 Date date2 = java.sql.Date.valueOf(dateString);
			 System.out.println("date2="+date2);
			 //图片
			 String img_path = "tetet";//图片
			 
			 //内容
			 String new_content = request.getParameter("abstract");
			 System.out.println("内容"+new_content);
			 
			 News news = new News(new_title,new_content,date2,new_type,img_path);
			String title = request.getParameter("titles");
			 title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
			 System.out.println("url传值的title="+title);
			  System.out.println("用于判断的title="+title);
			 if(!title.equals("")){
				 //dao.修改方法
				 System.out.println("更改方法");
				 System.out.println(news);
				 int change = dao.new_change(news,title);
				 System.out.println("判断是否修改成功"+change);
				NewsSeverlet severlet = new NewsSeverlet();
				severlet.newFen1(request, response);
			 }else{
				 //dao。new_add方法
				 System.out.println("添加方法");
				 int add = dao.new_add(news);
				 System.out.println("添加=="+add);
				 Page<News> page = new Page<News>();
				 page.setPageNum(1);
				 page.setPageSize(10);
				 Page<News> fen = dao.news_fen(page);
				  request.setAttribute("page", fen);
			      //将page中的date放入域中
			      request.setAttribute("News",fen.getDate());
			      request.setAttribute("chose", "no");
			  /*    request.setAttribute("yinCang", yin);*/
			       
			      //转发
			      request.getRequestDispatcher("/web/home/list-text1.jsp").forward(request, response);
			 }
			 
		
	}
	/*//新增severlet
	protected void new_add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	//修改方法
protected void new_change(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}*/
}
