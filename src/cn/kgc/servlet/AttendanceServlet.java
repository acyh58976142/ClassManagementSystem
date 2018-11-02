package cn.kgc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.dao.AttendanceDao;
import cn.kgc.dao.impl.AttendanceDaoImpl;
import cn.kgc.entity.Attendance;
import cn.kgc.entity.Page;
import cn.kgc.service.AttendanceService;
import cn.kgc.service.impl.AttendanceServiceImpl;

import com.google.gson.Gson;

/**
 * Servlet implementation class AttendanceServlet
 */
/*@WebServlet("/client/AttendanceServlet")*/
public class AttendanceServlet extends BeanServlet {
	private static final long serialVersionUID = 1L;
	AttendanceService attendanceService = new AttendanceServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/*
	 * 分页查询多条数据
	 */
	protected void findAttendance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNum = request.getParameter("pageNum");
		String AttendanceName = request.getParameter("queryAttendanceName");
     	String attendanceDate =request.getParameter("queryAttendanceDate");
		int pageSize=10;
		
		Page<Attendance> page = attendanceService.findAttendance(pageNum, pageSize, AttendanceName, attendanceDate);
	//	List<Map<String, Object>> listMap = scoreService.findScore(pageNum, pageSize, AttendanceName, AttendanceNumber);
		
		request.setAttribute("page", page);
		//转发到查询所有的页面
		request.getRequestDispatcher("/web/kaoqin/kaoqin_student.jsp").forward(request, response);

		
	}
	
	
	/*
	 * 新增方法
	 */
	protected void saveAttendance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.setContentType("text/html");
			/**
			 * 获取页面中表单提交的内容
			 */
		 System.out.println("1");
		 int isabsence=Integer.parseInt(request.getParameter("isabsence"));
		 String studentName=request.getParameter("studentName");
		 System.out.println(studentName+"\n"+isabsence);
		 
		 
		 Attendance attendance = new Attendance(null,studentName,null,isabsence);

			//放入数据库中
			int b =	attendanceService.saveAttendance(attendance);
			
			/**
			 * 判断是否添加成功
			 * 失败转发到当前页面
			 * 成功调用查询方法
			 */
			if(!(b==0)){
				//注册成功重定向到regist_success.html
				response.sendRedirect(request.getContextPath()+"/AttendanceServlet?method=findAttendance");
				
			}else{
				//前台验证注册的内容，后台报“用户名存在”错误
				request.setAttribute("e", "系统错误");
				//注册失败转发到当前页面
				request.getRequestDispatcher("/web/kaoqin/kaoqin_student.jsp").forward(request, response);
			}
	}
	
	
	
	/*
	 * 删除学生信息
	 */
	
	protected void delAttendance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取页面中的id
		String attendanceServiceId = request.getParameter("id");
		
		//执行删除操作
		int result=attendanceService.delAttendance(attendanceServiceId);
		request.setAttribute("result",result);
		if (!(result==0)) {
			request.setAttribute("success","删除成功");
           	//完成后重新定向
			response.sendRedirect(request.getContextPath()+"/client/AttendanceServlet?method=findAttendance");
		}else{
			request.setAttribute("msg","删除失败");
			//完成后重新定向
			response.sendRedirect(request.getContextPath()+"/client/AttendanceServlet?method=findAttendance");
		}
		
	}
	
	
	/*
	 * 获取学生id查询该条信息并把该条信息返还给前台
	 */
	protected void getAttendanceById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
					
		PrintWriter out = response.getWriter();
	    response.setContentType("text/html;charset=utf-8");
	  
		//获取需要修改的id
	    String attendanceId = request.getParameter("id"); 
		Attendance attendance = attendanceService.getAttendanceById(attendanceId);
		
		Gson gson = new Gson();
		//转为json对象
		String jsonattendance = gson.toJson(attendance);
		
		//将数据传到前端
		out.println(jsonattendance);    
		//将查询到的对象放入域中
		request.setAttribute("jsonattendance", jsonattendance);
		
	}
	
	
	/*
	 * 修改方法
	 */
	protected void updateAttendance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.setContentType("text/html");
			/**
			 * 获取页面中表单提交的内容
			 */
		  String attendanceId = request.getParameter("id");
		 int isabsence=Integer.parseInt(request.getParameter("isabsence"));
		 String studentName=request.getParameter("studentName");
		 System.out.println(studentName+"\n"+isabsence);
		 		
			
		 Attendance attendance = new Attendance(attendanceId,studentName,null,isabsence);
//			String info=gson.toJson(score);
			//放入数据库中
			int b =	attendanceService.updateAttendance(attendance);
			
			/**
			 * 判断是否添加成功
			 * 失败转发到当前页面
			 * 成功调用查询方法
			 */
			if(!(b==0)){
				//注册成功重定向到regist_success.html
				response.sendRedirect(request.getContextPath()+"/AttendanceServlet?method=findAttendance");
				
			}else{
				//前台验证注册的内容，后台报“用户名存在”错误
				request.setAttribute("msg", "系统错误");
				//注册失败转发到当前页面
				request.getRequestDispatcher("/web/kaoqin/kaoqin_student.jsp").forward(request, response);
			}
	}
	
	/*protected void getStudentName(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		AttendanceDao attendanceDaoImpl=new AttendanceDaoImpl();
		List<Integer> studentName=attendanceDaoImpl.queryListStudentName();
		req.setAttribute("studentName", studentName);
	}*/
	
	

}
