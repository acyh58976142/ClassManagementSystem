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
		//转发到查询所有的页面
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
	 * 查询一个并转发到修改页面
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
		//获取需要修改的id
		String homeworkId = request.getParameter("homeworkId");
		
		Homework homework = homeworkService.getHomeworkById(homeworkId);
		
		Gson gson = new Gson();
		String jsonHomework = gson.toJson(homework);
		
		//将数据传到前端
		out.println(jsonHomework);   
		//将查询到的对象放入域中
		request.setAttribute("homework", homework);
		
	/*	//转发到修改页面
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);*/
	}
	
	
	/**
	 * 根据id删除作业
	 */
	protected void delHomework(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取id
		String homeworkId = request.getParameter("id");
		System.out.println(homeworkId);
		//调用service中方法
		int result=homeworkService.delHomework(homeworkId);
		System.out.println(result);
		request.setAttribute("result",result);

		//重定向到查询所有的方法
		response.sendRedirect(request.getContextPath()+"/HomeworkServlet?method=findHomework");

		
	}
	
	/**
	 * 增加作业的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void saveHomework(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
/*	    //获取表单中需要增加的信息
		Homework homework = WEBUtils.param2bean(request, new Homework());*/
		String studentName=request.getParameter("studentName");
		System.out.println("第一次展示"+studentName);
		/*studentName = new String(studentName.getBytes("ISO-8859-1"),"UTF-8");*/
		int notescore=Integer.parseInt(request.getParameter("notescore"));
		int code_number=Integer.parseInt(request.getParameter("code_number"));
		int isannotate=Integer.parseInt(request.getParameter("isannotate"));
		double correct_rate=Double.parseDouble(request.getParameter("correct_rate"));
		String question=request.getParameter("question");
		String team=request.getParameter("team");
		System.out.println("第二次展示"+studentName);
		System.out.println(studentName+"\n"+notescore+"\n"+code_number+"\n"+isannotate+"\n"+correct_rate+"\n"+question+"\n"+team);
		
		Homework homework=new Homework(null,notescore,code_number,isannotate,correct_rate, null,studentName,question,team);
		
		
		//调用service中的方法
		homeworkService.saveHomework(homework);
		
		//重定向到查询所有的方法
		response.sendRedirect(request.getContextPath()+"/HomeworkServlet?method=findHomework");
		    
		
		
	}
	
	
	/**
	 * 查询所有作业的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void HomeworkList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用service方法查询所有的作业
		List<Homework> list = homeworkService.getHomeworkList();
		//将查询到的作业集合放到request域中
		request.setAttribute("list", list);
		
		//转发到
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
/*	    //获取表单中需要增加的信息
		Homework homework = WEBUtils.param2bean(request, new Homework());*/
		String studentName=request.getParameter("studentName");
		System.out.println("第一次展示"+studentName);
		/*studentName = new String(studentName.getBytes("ISO-8859-1"),"UTF-8");*/
		int notescore=Integer.parseInt(request.getParameter("notescore"));
		int code_number=Integer.parseInt(request.getParameter("code_number"));
		int isannotate=Integer.parseInt(request.getParameter("isannotate"));
		double correct_rate=Double.parseDouble(request.getParameter("correct_rate"));
		String question=request.getParameter("question");
		String team=request.getParameter("team");
		String id=request.getParameter("id");
		System.out.println("第二次展示"+studentName);
		
		System.out.println(studentName+"\n"+notescore+"\n"+code_number+"\n"+isannotate+"\n"+correct_rate+"\n"+question+"\n"+team+"\n"+id);
		
		Homework homework=new Homework(id,notescore,code_number,isannotate,correct_rate, null,studentName,question,team);
		
		
		//调用service中的方法
		homeworkService.updateHomework(homework);
		
		//重定向到查询所有的方法
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
		//转发到查询所有的页面
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
		//转发到查询所有的页面
		req.getRequestDispatcher("/web/classManagement/operation.jsp").forward(req, resp);

	}

}
