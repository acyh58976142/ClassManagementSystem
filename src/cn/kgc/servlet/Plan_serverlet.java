package cn.kgc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.dao.Plan_UserDao;
import cn.kgc.dao.impl.Plan_BaseDaoImpl;
import cn.kgc.entity.Page;
import cn.kgc.entity.Plan;

/**
 * Servlet implementation class Plan_serverlet
 */
@WebServlet("/Plan_serverlet")
public class Plan_serverlet extends BeanServlet {
	private static final long serialVersionUID = 1L;
	Plan_UserDao dao=new Plan_BaseDaoImpl();
	
	 //新建或者编辑的方法
		protected void plan_add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			String ids = request.getParameter("ids");
			System.out.println("这是获取的ids："+ids);
			if(ids==""){
				
				String title = request.getParameter("plan_titles");
				System.out.println(title);
				String content = request.getParameter("plan_concent");
				System.out.println(content);
				//日期
				String plan_date = request.getParameter("plan_date");
				//
		        //姓名
				String name = request.getParameter("name");
				Plan plan2 = new Plan();
				plan2.setTeacher_names(name);
				Plan plan_getID = dao.plan_getID(plan2);
				int job_number = plan_getID.getJob_number();
				
				Plan plan=new Plan(name,title,content,plan_date,null,job_number);
				System.out.println(plan);
				int i = dao.plan_save(plan);
				System.out.println(i);
				if(i!=0){
					System.out.println("保存成功，执行分页");
					page_fen(request,response);
				}else{
					System.out.println("新建失败");
					request.getRequestDispatcher("/web/jiaoxuehuodong/youhua12/Plan_false.jsp").forward(request, response);
				}
				
			}else{
				request.setAttribute("ids", ids);
				this.Plan_Update_Servlet(request,response);
				System.out.println("修改完成");
			}
		  
		}
	     //更改方法
		private void Plan_Update_Servlet(HttpServletRequest request, HttpServletResponse response) {
			String id = (String)request.getAttribute("ids");
			
			
			String date = request.getParameter("plan_date");
		    System.out.println("更改方法获取日期="+date);
			String titles = request.getParameter("plan_titles");
		
			String concent = request.getParameter("plan_concent");
		
			String name = request.getParameter("name");
		     System.out.println("更改方法获取姓名="+name);
		     Plan plan2 = new Plan();
				plan2.setTeacher_names(name);
				Plan plan_getID = dao.plan_getID(plan2);
				int job_number = plan_getID.getJob_number();
		     
			Plan plan = new Plan(name,titles,concent,date,id,job_number);

			int chang = dao.plan_chang(plan);
			System.out.println(chang);
			if(chang!=0){
				try {
					page_fen(request,response);
				} catch (ServletException e) {
				
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
			
		}
	
	//分页方法
	protected void page_fen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page<Plan> page=new Page<Plan>();
        String  a= request.getParameter("pagenum");
        System.out.println("执行删除后得到的页码"+a);
        //此处判断用于提交保存后，回到分页第一页
        if(a==null){
        	a="1";
        }
       
        int pagenum1 = Integer.parseInt(a);
        int pagenum=pagenum1;
        page.setPageNum(pagenum);
        page.setPageSize(7);
        String findstyle = request.getParameter("findstyle");
        System.out.println("得到的方法是"+findstyle);
        if(findstyle==""){
        	System.out.println("进入方法0");
        	Page<Plan> findBook = dao.findBook(page);
        	System.out.println(findBook+"得到方法0的对象");
           if(findBook!=null){
        	   request.setAttribute("page", findBook);
               request.setAttribute("planss", findBook.getDate());
              request.setAttribute("findstyle", findstyle);
              request.getRequestDispatcher("/web/jiaoxuehuodong/youhua12/index2.jsp").forward(request, response);	
           }
        }else if(findstyle.equals("1")){
        	System.out.println("进入方法1");
        	Page<Plan> plan_date = dao.findPlan_date(page);
        	System.out.println(plan_date+"得到方法1的page对象");
        	 if(plan_date!=null){
        		 request.setAttribute("page", plan_date);
                 request.setAttribute("planss", plan_date.getDate());
                 request.setAttribute("findstyle", findstyle);
                request.getRequestDispatcher("/web/jiaoxuehuodong/youhua12/index2.jsp").forward(request, response); 
        	 }	
        	
        }else if(findstyle.equals("2")){
        	System.out.println("进入方法2");
        	Page<Plan> plan_date = dao.findPlan_jobnum(page);
        	System.out.println(plan_date+"得到方法2的对象");
       	   if(plan_date!=null){
       		 request.setAttribute("page", plan_date);
             request.setAttribute("planss", plan_date.getDate());
             request.setAttribute("findstyle", findstyle);
            request.getRequestDispatcher("/web/jiaoxuehuodong/youhua12/index2.jsp").forward(request, response);	
       	   }
        }
        
	}
	
	//查询所有
	
	protected void plan_chaall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		  Plan_UserDao daoImpl = new Plan_BaseDaoImpl();
		  List<Plan> chas = daoImpl.plan_chas();
		 if(chas!=null){
			 request.setAttribute("planss", chas);
			  request.getRequestDispatcher("/web/jiaoxuehuodong/youhua12/index2.jsp").forward(request, response);
		 }
	}
    //查询一个
	protected void Plan_cha_Servlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String parameter = request.getParameter("ids");
		String findstyle = request.getParameter("findstyle");
		if(parameter!=null){
			Plan plan_cha = dao.plan_cha(parameter);
		    if(plan_cha!=null){
		    	request.setAttribute("findstyle", findstyle);
		    	request.setAttribute("yi", plan_cha);
		    	request.getRequestDispatcher("/web/jiaoxuehuodong/youhua12/show1.jsp").forward(request, response);
		    }
		}
	}

//删除
	protected void Plan_del_Servlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String ids = request.getParameter("ids");
		System.out.println(ids);
		if(ids!=null){
			int del= dao.Plan_del(ids);
			if(del!=0){
				String findstyle = request.getParameter("findstyle");
				System.out.println("删除成功，执行分页"+findstyle);
				this.page_fen(request,response);
			}
		}
		
		
	}
	
	
	//第一次遍历的调用方法
	protected void page_fen1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page<Plan> page=new Page<Plan>();
      
         page.setPageNum(1);
        page.setPageSize(7);
        Page<Plan> findBook = dao.findBook(page);
        if(findBook!=null){
        	request.setAttribute("page", findBook);
            request.setAttribute("planss", findBook.getDate());
           request.getRequestDispatcher("/web/jiaoxuehuodong/youhua12/index2.jsp").forward(request, response);	
        }
	}

	//模糊查询
protected void plan_sousuo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("你已经进入模糊搜索的方法");	
	     Page<Plan> page=new Page<Plan>();
	     System.out.println("查看是否新建成功对象"+page);
		String findstyle = request.getParameter("findstyle");
		 System.out.println("获取style"+findstyle);
		 //得到页码，建好page对象
	        String  a= request.getParameter("pagenum");
	        System.out.println("a"+a);
	        if(a==null){
	        	System.out.println("jinru ");
	        	a="1";
	        }
	        int pagenum1 = Integer.parseInt(a);
	        int aaa=1;
	        int zo=aaa+pagenum1;
	        System.out.println("输出转化的int值"+zo);
	        page.setPageNum(pagenum1);
	        System.out.println("分页的页码"+page.getPageNum());
	        page.setPageSize(7);
	        
//乱码问题
	        //获取页面输入关键字
		String sousuo = request.getParameter("sousuo");
		//将字符流转换成字符流
		sousuo = new String(sousuo.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("页面获取的关键字"+sousuo);
		//调用方法，根据sql语句，先判断是否存在表，如果存在，删除，调用sql'语句，创建模糊查询结果值列表，判断是否为空，为
		//空则换字段查询，该方法返回值为int，创建表plan_sousuo
		dao.plan_sousuoadd(sousuo);
		//调用方法分页plan_sousuo
		Page<Plan> findBook_mohu = dao.findBook_mohu(page);
		 if(findBook_mohu!=null){
      	   request.setAttribute("page", findBook_mohu);
             request.setAttribute("planss", findBook_mohu.getDate());
            request.setAttribute("findstyle", findstyle);
            request.getRequestDispatcher("/web/jiaoxuehuodong/youhua12/index2.jsp").forward(request, response);	
         }

			
	}

	protected void Plan_ads(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String findstyle = request.getParameter("findstyle");
		request.setAttribute("findstyle", findstyle);
		request.getRequestDispatcher("/web/jiaoxuehuodong/youhua12/show1.jsp").forward(request, response);
	}
}
