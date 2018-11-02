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
	
	 //�½����߱༭�ķ���
		protected void plan_add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			String ids = request.getParameter("ids");
			System.out.println("���ǻ�ȡ��ids��"+ids);
			if(ids==""){
				
				String title = request.getParameter("plan_titles");
				System.out.println(title);
				String content = request.getParameter("plan_concent");
				System.out.println(content);
				//����
				String plan_date = request.getParameter("plan_date");
				//
		        //����
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
					System.out.println("����ɹ���ִ�з�ҳ");
					page_fen(request,response);
				}else{
					System.out.println("�½�ʧ��");
					request.getRequestDispatcher("/web/jiaoxuehuodong/youhua12/Plan_false.jsp").forward(request, response);
				}
				
			}else{
				request.setAttribute("ids", ids);
				this.Plan_Update_Servlet(request,response);
				System.out.println("�޸����");
			}
		  
		}
	     //���ķ���
		private void Plan_Update_Servlet(HttpServletRequest request, HttpServletResponse response) {
			String id = (String)request.getAttribute("ids");
			
			
			String date = request.getParameter("plan_date");
		    System.out.println("���ķ�����ȡ����="+date);
			String titles = request.getParameter("plan_titles");
		
			String concent = request.getParameter("plan_concent");
		
			String name = request.getParameter("name");
		     System.out.println("���ķ�����ȡ����="+name);
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
	
	//��ҳ����
	protected void page_fen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page<Plan> page=new Page<Plan>();
        String  a= request.getParameter("pagenum");
        System.out.println("ִ��ɾ����õ���ҳ��"+a);
        //�˴��ж������ύ����󣬻ص���ҳ��һҳ
        if(a==null){
        	a="1";
        }
       
        int pagenum1 = Integer.parseInt(a);
        int pagenum=pagenum1;
        page.setPageNum(pagenum);
        page.setPageSize(7);
        String findstyle = request.getParameter("findstyle");
        System.out.println("�õ��ķ�����"+findstyle);
        if(findstyle==""){
        	System.out.println("���뷽��0");
        	Page<Plan> findBook = dao.findBook(page);
        	System.out.println(findBook+"�õ�����0�Ķ���");
           if(findBook!=null){
        	   request.setAttribute("page", findBook);
               request.setAttribute("planss", findBook.getDate());
              request.setAttribute("findstyle", findstyle);
              request.getRequestDispatcher("/web/jiaoxuehuodong/youhua12/index2.jsp").forward(request, response);	
           }
        }else if(findstyle.equals("1")){
        	System.out.println("���뷽��1");
        	Page<Plan> plan_date = dao.findPlan_date(page);
        	System.out.println(plan_date+"�õ�����1��page����");
        	 if(plan_date!=null){
        		 request.setAttribute("page", plan_date);
                 request.setAttribute("planss", plan_date.getDate());
                 request.setAttribute("findstyle", findstyle);
                request.getRequestDispatcher("/web/jiaoxuehuodong/youhua12/index2.jsp").forward(request, response); 
        	 }	
        	
        }else if(findstyle.equals("2")){
        	System.out.println("���뷽��2");
        	Page<Plan> plan_date = dao.findPlan_jobnum(page);
        	System.out.println(plan_date+"�õ�����2�Ķ���");
       	   if(plan_date!=null){
       		 request.setAttribute("page", plan_date);
             request.setAttribute("planss", plan_date.getDate());
             request.setAttribute("findstyle", findstyle);
            request.getRequestDispatcher("/web/jiaoxuehuodong/youhua12/index2.jsp").forward(request, response);	
       	   }
        }
        
	}
	
	//��ѯ����
	
	protected void plan_chaall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		  Plan_UserDao daoImpl = new Plan_BaseDaoImpl();
		  List<Plan> chas = daoImpl.plan_chas();
		 if(chas!=null){
			 request.setAttribute("planss", chas);
			  request.getRequestDispatcher("/web/jiaoxuehuodong/youhua12/index2.jsp").forward(request, response);
		 }
	}
    //��ѯһ��
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

//ɾ��
	protected void Plan_del_Servlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String ids = request.getParameter("ids");
		System.out.println(ids);
		if(ids!=null){
			int del= dao.Plan_del(ids);
			if(del!=0){
				String findstyle = request.getParameter("findstyle");
				System.out.println("ɾ���ɹ���ִ�з�ҳ"+findstyle);
				this.page_fen(request,response);
			}
		}
		
		
	}
	
	
	//��һ�α����ĵ��÷���
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

	//ģ����ѯ
protected void plan_sousuo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("���Ѿ�����ģ�������ķ���");	
	     Page<Plan> page=new Page<Plan>();
	     System.out.println("�鿴�Ƿ��½��ɹ�����"+page);
		String findstyle = request.getParameter("findstyle");
		 System.out.println("��ȡstyle"+findstyle);
		 //�õ�ҳ�룬����page����
	        String  a= request.getParameter("pagenum");
	        System.out.println("a"+a);
	        if(a==null){
	        	System.out.println("jinru ");
	        	a="1";
	        }
	        int pagenum1 = Integer.parseInt(a);
	        int aaa=1;
	        int zo=aaa+pagenum1;
	        System.out.println("���ת����intֵ"+zo);
	        page.setPageNum(pagenum1);
	        System.out.println("��ҳ��ҳ��"+page.getPageNum());
	        page.setPageSize(7);
	        
//��������
	        //��ȡҳ������ؼ���
		String sousuo = request.getParameter("sousuo");
		//���ַ���ת�����ַ���
		sousuo = new String(sousuo.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("ҳ���ȡ�Ĺؼ���"+sousuo);
		//���÷���������sql��䣬���ж��Ƿ���ڱ�������ڣ�ɾ��������sql'��䣬����ģ����ѯ���ֵ�б��ж��Ƿ�Ϊ�գ�Ϊ
		//�����ֶβ�ѯ���÷�������ֵΪint��������plan_sousuo
		dao.plan_sousuoadd(sousuo);
		//���÷�����ҳplan_sousuo
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
