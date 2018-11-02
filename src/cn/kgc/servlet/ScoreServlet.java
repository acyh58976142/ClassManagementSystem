package cn.kgc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.entity.Page;
import cn.kgc.entity.Score;
import cn.kgc.entity.Student;
import cn.kgc.entity.User;
import cn.kgc.service.ScoreAnalysisService;
import cn.kgc.service.ScoreService;
import cn.kgc.service.StudentService;
import cn.kgc.service.impl.ScoreAnalysisServiceImpl;
import cn.kgc.service.impl.ScoreServiceImpl;
import cn.kgc.service.impl.StudentServiceImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ScoreServlet
 */
@WebServlet("/client/ScoreServlet")
public class ScoreServlet extends BeanServlet {
	private static final long serialVersionUID = 1L;
   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
   ScoreService scoreService = new ScoreServiceImpl();
   ScoreAnalysisService scoreAnalysisService = new ScoreAnalysisServiceImpl();
   
	/*
	 * 分页查询多条数据
	 */
	protected void findScore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNum = request.getParameter("pageNum");
		
		String studentName = request.getParameter("queryStudentName");
//		String studentId = request.getParameter("queryStudentNumber");
//		Integer studentNumber = Integer.parseInt(studentId);
		/* int studentNumber = 0;
			String studentId = request.getParameter("queryStudentNumber");
			
			
		    if (studentId==null) {
		    	studentNumber = 0 ;
			}else{
				try {
					studentNumber = Integer.valueOf(studentId);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
		/*Date date = dateFormat.parse(request.getParameter("queryScoreDate"));
		String scoreDate = dateFormat.format(date);*/
		String scoreDate =request.getParameter("queryScoreDate");
		int pageSize=10;
		
		Page<Score> page = scoreService.findScore(pageNum, pageSize, studentName, scoreDate);
	//	List<Map<String, Object>> listMap = scoreService.findScore(pageNum, pageSize, studentName, studentNumber);
		
		request.setAttribute("page", page);
		
		StudentService studentService = new StudentServiceImpl();
	    List<Student> listName = studentService.queryListStudentName();
	    
		
		request.setCharacterEncoding("utf-8");
		
		request.setAttribute("listName", listName);
		System.out.println(listName);
		
		//转发到查询所有的页面
		request.getRequestDispatcher("/web/student/studentScore.jsp").forward(request, response);

		
	}
	
	
	/*
	 * 两表联查方法
	 * 
	 */
	protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		
		
		
	//	List<Map<String, Object>> listMap= scoreService.findAll();
		List<Map<String, Object>> listMap = scoreService.findOrderNeedInfo();
		request.setAttribute("listMap", listMap);
		request.getRequestDispatcher("/web/student/studentScore.jsp").forward(request, response); 
	};
	
    
	/*
	 * 新增帆方法
	 */
	protected void saveScore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.setContentType("text/html");
		/**
		 * 获取页面中表单提交的内容
		 */
		//Score param2bean = WEBUtils.param2bean(request, new Score());
		String studentName = request.getParameter("studentName");
		Integer javaScore=0;
		Integer javasriptScore=0;
		Integer HTMLScore=0;
		Integer SQLScore=0;
		try {
			javaScore = Integer.parseInt(request.getParameter("javaScore"));
			javasriptScore = Integer.parseInt(request.getParameter("javascriptScore"));
			HTMLScore = Integer.parseInt(request.getParameter("HTMLScore"));
			SQLScore = Integer.parseInt(request.getParameter("SQLScore"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Score score = new Score(null, studentName, javaScore, javasriptScore, HTMLScore, SQLScore, null);
//		String info=gson.toJson(score);
		//放入数据库中
		int b =	scoreService.saveScore(score);
		
		/**
		 * 判断是否添加成功
		 * 失败转发到当前页面
		 * 成功调用查询方法
		 */
		if(!(b==0)){
			//注册成功重定向到regist_success.html
			response.sendRedirect(request.getContextPath()+"/client/ScoreServlet?method=findScore");
			
		}else{
			//前台验证注册的内容，后台报“用户名存在”错误
			request.setAttribute("msg", "用户名已存在");
			//注册失败转发到当前页面
			request.getRequestDispatcher("/web/student/studentScore.jsp").forward(request, response);
		}
			
	}
	
	
	/*
	 * 删除方法
	 */
	protected void delScore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取页面中的id
		String scoreId = request.getParameter("id");
		
		//执行删除操作
		int result=scoreService.delScore(scoreId);
		request.setAttribute("result",result);
		if (!(result==0)) {
			request.setAttribute("success","删除成功");
           	//完成后重新定向
			response.sendRedirect(request.getContextPath()+"/client/ScoreServlet?method=findScore");
		}else{
			request.setAttribute("msg","删除失败");
			//完成后重新定向
			response.sendRedirect(request.getContextPath()+"/client/ScoreServlet?method=findScore");
		}
		
	}
		
	
	/**
	 * 根据id查询显示修改模态框上
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	protected void toUpdatePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/html;charset=utf-8");
	  
	    request.setCharacterEncoding("utf-8");

		//获取需要修改的id
		String scoreId = request.getParameter("id");
		
		Score score = scoreService.getScoreById(scoreId);
		
		/*Gson gson = new Gson();
		String jsonScore = gson.toJson(score);*/
		Gson gson = new GsonBuilder()
			        .setDateFormat("yyyy-MM-dd")
			        .create();
		String jsonScore = gson.toJson(score);
	    
		//将数据传到前端
		out.println(jsonScore);
		//out.println(score);
		//将查询到的对象放入域中
		//request.setAttribute("jsonScore", jsonScore);
		
		
		
		//转发到修改页面
		//request.getRequestDispatcher("/web/student/studentScore.jsp").forward(request, response);
	
	}
	
	
	/*
	 * 修改方法
	 */
	protected void updateScore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("scoreDate"));
		/**
		 * 获取页面中表单提交的内容
		 */
		//Score param2bean = WEBUtils.param2bean(request, new Score());
		String scoreId = request.getParameter("id");
		String studentName = request.getParameter("studentName");
		Integer javaScore=0;
		Integer javasriptScore=0;
		Integer HTMLScore=0;
		Integer SQLScore=0;
		Date scoreDate=null;
		try {
			javaScore = Integer.parseInt(request.getParameter("javaScore"));
			javasriptScore = Integer.parseInt(request.getParameter("javascriptScore"));
			HTMLScore = Integer.parseInt(request.getParameter("HTMLScore"));
			SQLScore = Integer.parseInt(request.getParameter("SQLScore"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 scoreDate = dateFormat.parse(request.getParameter("scoreDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Score score = new Score(scoreId, studentName, javaScore, javasriptScore, HTMLScore, SQLScore,scoreDate);
//		String info=gson.toJson(score);
		//放入数据库中
		int b =	scoreService.updateScore(score);
		
		/**
		 * 判断是否修改成功
		 * 失败转发到当前页面
		 * 成功调用查询方法
		 */
		if(!(b==0)){
			//注册成功重定向到regist_success.html
			response.sendRedirect(request.getContextPath()+"/client/ScoreServlet?method=findScore");
			
		}else{
			//前台验证注册的内容，后台报“用户名存在”错误
			request.setAttribute("msg", "用户名已存在");
			//注册失败转发到当前页面
			request.getRequestDispatcher("/web/student/studentScore.jsp").forward(request, response);
		}
		
	}
	
	
	
	/*
	 * 查询学生姓名
	 */
	
	protected void queryListStudentName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	
	}
	
	/*
	 * 查询出1-12月份的java月平均分数
	 */
	
	protected void queryListMonthScore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	    String scoreDate = (request.getParameter("queryScoreDate"))!=null?(request.getParameter("queryScoreDate")):"2018"; 
		
		
		 //调用service方法查询所有的1-12月份的java月平均分数
		 List<Integer> list = scoreAnalysisService.queryListMonthScore(scoreDate);
		 
		
		 PrintWriter out = response.getWriter();
		 
		 //将数据转变成json格式
		 Gson gson = new Gson();
		 String jsonJavaScore = gson.toJson(list);
		 //将数据传到前端
		 //out.println(jsonJavaScore);   
		 response.setContentType("text/html; charset=utf-8");
	     response.getWriter().write(jsonJavaScore);
	     System.out.println(list);
		 System.out.println(jsonJavaScore);
	}

	
	/*
     * 查询java各分数段的百分比
     */
	protected void queryListPercentageScore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 
		 //调用service方法查询java各分数段的百分比
		Object[] list = scoreAnalysisService.queryListPercentageScore2();
		//Map<String,Object> map =  scoreAnalysisService.queryListPercentageScore();
		//Object obj = scoreAnalysisService.queryListPercentageScore2();
		 PrintWriter out = response.getWriter();
		 
		 //将数据转变成json格式
		 Gson gson = new Gson();
		 String jsonJavaScore = gson.toJson(list);
		 //将数据传到前端
		 //out.println(jsonJavaScore);   
		 response.setContentType("text/html; charset=utf-8");
	     response.getWriter().write(jsonJavaScore);
	     System.out.println(list);
		 System.out.println(jsonJavaScore);
		
	}
}
