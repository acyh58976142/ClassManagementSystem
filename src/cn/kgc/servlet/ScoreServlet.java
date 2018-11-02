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
	 * ��ҳ��ѯ��������
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
		
		//ת������ѯ���е�ҳ��
		request.getRequestDispatcher("/web/student/studentScore.jsp").forward(request, response);

		
	}
	
	
	/*
	 * �������鷽��
	 * 
	 */
	protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		
		
		
	//	List<Map<String, Object>> listMap= scoreService.findAll();
		List<Map<String, Object>> listMap = scoreService.findOrderNeedInfo();
		request.setAttribute("listMap", listMap);
		request.getRequestDispatcher("/web/student/studentScore.jsp").forward(request, response); 
	};
	
    
	/*
	 * ����������
	 */
	protected void saveScore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.setContentType("text/html");
		/**
		 * ��ȡҳ���б��ύ������
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
		//�������ݿ���
		int b =	scoreService.saveScore(score);
		
		/**
		 * �ж��Ƿ���ӳɹ�
		 * ʧ��ת������ǰҳ��
		 * �ɹ����ò�ѯ����
		 */
		if(!(b==0)){
			//ע��ɹ��ض���regist_success.html
			response.sendRedirect(request.getContextPath()+"/client/ScoreServlet?method=findScore");
			
		}else{
			//ǰ̨��֤ע������ݣ���̨�����û������ڡ�����
			request.setAttribute("msg", "�û����Ѵ���");
			//ע��ʧ��ת������ǰҳ��
			request.getRequestDispatcher("/web/student/studentScore.jsp").forward(request, response);
		}
			
	}
	
	
	/*
	 * ɾ������
	 */
	protected void delScore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��ȡҳ���е�id
		String scoreId = request.getParameter("id");
		
		//ִ��ɾ������
		int result=scoreService.delScore(scoreId);
		request.setAttribute("result",result);
		if (!(result==0)) {
			request.setAttribute("success","ɾ���ɹ�");
           	//��ɺ����¶���
			response.sendRedirect(request.getContextPath()+"/client/ScoreServlet?method=findScore");
		}else{
			request.setAttribute("msg","ɾ��ʧ��");
			//��ɺ����¶���
			response.sendRedirect(request.getContextPath()+"/client/ScoreServlet?method=findScore");
		}
		
	}
		
	
	/**
	 * ����id��ѯ��ʾ�޸�ģ̬����
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	protected void toUpdatePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/html;charset=utf-8");
	  
	    request.setCharacterEncoding("utf-8");

		//��ȡ��Ҫ�޸ĵ�id
		String scoreId = request.getParameter("id");
		
		Score score = scoreService.getScoreById(scoreId);
		
		/*Gson gson = new Gson();
		String jsonScore = gson.toJson(score);*/
		Gson gson = new GsonBuilder()
			        .setDateFormat("yyyy-MM-dd")
			        .create();
		String jsonScore = gson.toJson(score);
	    
		//�����ݴ���ǰ��
		out.println(jsonScore);
		//out.println(score);
		//����ѯ���Ķ����������
		//request.setAttribute("jsonScore", jsonScore);
		
		
		
		//ת�����޸�ҳ��
		//request.getRequestDispatcher("/web/student/studentScore.jsp").forward(request, response);
	
	}
	
	
	/*
	 * �޸ķ���
	 */
	protected void updateScore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("scoreDate"));
		/**
		 * ��ȡҳ���б��ύ������
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
		//�������ݿ���
		int b =	scoreService.updateScore(score);
		
		/**
		 * �ж��Ƿ��޸ĳɹ�
		 * ʧ��ת������ǰҳ��
		 * �ɹ����ò�ѯ����
		 */
		if(!(b==0)){
			//ע��ɹ��ض���regist_success.html
			response.sendRedirect(request.getContextPath()+"/client/ScoreServlet?method=findScore");
			
		}else{
			//ǰ̨��֤ע������ݣ���̨�����û������ڡ�����
			request.setAttribute("msg", "�û����Ѵ���");
			//ע��ʧ��ת������ǰҳ��
			request.getRequestDispatcher("/web/student/studentScore.jsp").forward(request, response);
		}
		
	}
	
	
	
	/*
	 * ��ѯѧ������
	 */
	
	protected void queryListStudentName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	
	}
	
	/*
	 * ��ѯ��1-12�·ݵ�java��ƽ������
	 */
	
	protected void queryListMonthScore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	    String scoreDate = (request.getParameter("queryScoreDate"))!=null?(request.getParameter("queryScoreDate")):"2018"; 
		
		
		 //����service������ѯ���е�1-12�·ݵ�java��ƽ������
		 List<Integer> list = scoreAnalysisService.queryListMonthScore(scoreDate);
		 
		
		 PrintWriter out = response.getWriter();
		 
		 //������ת���json��ʽ
		 Gson gson = new Gson();
		 String jsonJavaScore = gson.toJson(list);
		 //�����ݴ���ǰ��
		 //out.println(jsonJavaScore);   
		 response.setContentType("text/html; charset=utf-8");
	     response.getWriter().write(jsonJavaScore);
	     System.out.println(list);
		 System.out.println(jsonJavaScore);
	}

	
	/*
     * ��ѯjava�������εİٷֱ�
     */
	protected void queryListPercentageScore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 
		 //����service������ѯjava�������εİٷֱ�
		Object[] list = scoreAnalysisService.queryListPercentageScore2();
		//Map<String,Object> map =  scoreAnalysisService.queryListPercentageScore();
		//Object obj = scoreAnalysisService.queryListPercentageScore2();
		 PrintWriter out = response.getWriter();
		 
		 //������ת���json��ʽ
		 Gson gson = new Gson();
		 String jsonJavaScore = gson.toJson(list);
		 //�����ݴ���ǰ��
		 //out.println(jsonJavaScore);   
		 response.setContentType("text/html; charset=utf-8");
	     response.getWriter().write(jsonJavaScore);
	     System.out.println(list);
		 System.out.println(jsonJavaScore);
		
	}
}
