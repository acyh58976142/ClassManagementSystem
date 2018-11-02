package cn.kgc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.kgc.entity.Homework;
import cn.kgc.entity.HomeworkPage;
import cn.kgc.service.HomeworkService;
import cn.kgc.service.impl.HomeworkServiceImpl;
import cn.kgc.util.WEBUtils;

public class HomeworkServlet extends BeanServlet{
	
	HomeworkService homeworkService = new HomeworkServiceImpl();
	
	
	
	protected void findHomework(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNum = request.getParameter("pagenum");
		
		int pageSize=10;
		
		HomeworkPage<Homework> page = homeworkService.findHomework(pageNum, pageSize);
		
		request.setAttribute("page", page);
		//ת������ѯ���е�ҳ��
		request.getRequestDispatcher("/web/classManagement/operation.jsp").forward(request, response);

		
	}
	
	
	protected void saveOrUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Homework homework = WEBUtils.param2bean(request, new Homework());
		
		if(homework.getId()==null) {
			homeworkService.saveHomework(homework);
		}else {
			homeworkService.updateHomework(homework);
		}
		
		response.sendRedirect(request.getContextPath()+"/HomeworkServlet?method=findHomework");
     
	
		
		
	}
	
	/**
	 * ��ѯһ����ת�����޸�ҳ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void toUpdatePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/html;charset=utf-8");
	  
	    request.setCharacterEncoding("utf-8");
		//��ȡ��Ҫ�޸ĵ�id
		String homeworkId = request.getParameter("homeworkId");
		
		Homework homework = homeworkService.getHomeworkById(homeworkId);
		
		Gson gson = new Gson();
		String jsonHomework = gson.toJson(homework);
		
		//�����ݴ���ǰ��
		out.println(jsonHomework);   
		//����ѯ���Ķ����������
		request.setAttribute("homework", homework);
		
	/*	//ת�����޸�ҳ��
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);*/
	}
	
	
	/**
	 * ����idɾ����ҵ
	 */
	protected void delHomework(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��ȡid
		String homeworkId = request.getParameter("id");
		System.out.println(homeworkId);
		//����service�з���
		int result=homeworkService.delHomework(homeworkId);
		System.out.println(result);
		request.setAttribute("result",result);

		//�ض��򵽲�ѯ���еķ���
		response.sendRedirect(request.getContextPath()+"/HomeworkServlet?method=findHomework");

		
	}
	
	/**
	 * ������ҵ�ķ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void saveHomework(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
/*	    //��ȡ������Ҫ���ӵ���Ϣ
		Homework homework = WEBUtils.param2bean(request, new Homework());*/
		String studentName=request.getParameter("studentName");
		System.out.println("��һ��չʾ"+studentName);
		/*studentName = new String(studentName.getBytes("ISO-8859-1"),"UTF-8");*/
		int notescore=Integer.parseInt(request.getParameter("notescore"));
		int code_number=Integer.parseInt(request.getParameter("code_number"));
		int isannotate=Integer.parseInt(request.getParameter("isannotate"));
		double correct_rate=Double.parseDouble(request.getParameter("correct_rate"));
		String question=request.getParameter("question");
		String team=request.getParameter("team");
		System.out.println("�ڶ���չʾ"+studentName);
		System.out.println(studentName+"\n"+notescore+"\n"+code_number+"\n"+isannotate+"\n"+correct_rate+"\n"+question+"\n"+team);
		
		Homework homework=new Homework(null,notescore,code_number,isannotate,correct_rate, null,studentName,question,team);
		
		
		//����service�еķ���
		homeworkService.saveHomework(homework);
		
		//�ض��򵽲�ѯ���еķ���
		response.sendRedirect(request.getContextPath()+"/HomeworkServlet?method=findHomework");
		    
		
		
	}
	
	
	/**
	 * ��ѯ������ҵ�ķ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void HomeworkList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����service������ѯ���е���ҵ
		List<Homework> list = homeworkService.getHomeworkList();
		//����ѯ������ҵ���Ϸŵ�request����
		request.setAttribute("list", list);
		
		//ת����
		request.getRequestDispatcher("/web/classManagement/operation.jsp").forward(request, response);
		
		
	}
	protected void getHomeworkById(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		
		String homeworkId=req.getParameter("id");
		
		Homework homework=homeworkService.getHomeworkById(homeworkId);
		Gson gson=new Gson();
		
		String jsonHomework=gson.toJson(homework);
		
		out.println(jsonHomework);
		req.setAttribute("jsonHomework", jsonHomework);
	}
	
	protected void updateHomework(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
/*	    //��ȡ������Ҫ���ӵ���Ϣ
		Homework homework = WEBUtils.param2bean(request, new Homework());*/
		String studentName=request.getParameter("studentName");
		System.out.println("��һ��չʾ"+studentName);
		/*studentName = new String(studentName.getBytes("ISO-8859-1"),"UTF-8");*/
		int notescore=Integer.parseInt(request.getParameter("notescore"));
		int code_number=Integer.parseInt(request.getParameter("code_number"));
		int isannotate=Integer.parseInt(request.getParameter("isannotate"));
		double correct_rate=Double.parseDouble(request.getParameter("correct_rate"));
		String question=request.getParameter("question");
		String team=request.getParameter("team");
		String id=request.getParameter("id");
		System.out.println("�ڶ���չʾ"+studentName);
		
		System.out.println(studentName+"\n"+notescore+"\n"+code_number+"\n"+isannotate+"\n"+correct_rate+"\n"+question+"\n"+team+"\n"+id);
		
		Homework homework=new Homework(id,notescore,code_number,isannotate,correct_rate, null,studentName,question,team);
		
		
		//����service�еķ���
		homeworkService.updateHomework(homework);
		
		//�ض��򵽲�ѯ���еķ���
		response.sendRedirect(request.getContextPath()+"/HomeworkServlet?method=findHomework");
		    
		
		
	}
	protected void lookingforHomework(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNum = request.getParameter("pageNum");
		
		String studentName = request.getParameter("queryStudentName");
//		studentName = new String(studentName.getBytes("ISO-8859-1"),"UTF-8");

		//String scoreDate = dateFormat.format(request.getParameter("queryScoreDate"));
		String homeworkDate =request.getParameter("queryHomeworkDate");
		int pageSize=10;
		
		HomeworkPage<Homework> page = homeworkService.lookingforHomework(pageNum, pageSize, studentName, null);
	
		
		request.setAttribute("page", page);
		//ת������ѯ���е�ҳ��
		request.getRequestDispatcher("/web/classManagement/operation.jsp").forward(request, response);

		
	}
	
	   protected void getHomeworkListByStudentName(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String studentName=req.getParameter("queryStudentName");
		studentName = new String(studentName.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(studentName);		
		List<Homework> page=homeworkService.getHomeworkListByStudentName(studentName);
		req.setAttribute("page", page);
		//ת������ѯ���е�ҳ��
		req.getRequestDispatcher("/web/classManagement/operation.jsp").forward(req, resp);

	}

}
