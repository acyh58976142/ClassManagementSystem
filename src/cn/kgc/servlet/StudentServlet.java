package cn.kgc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.dao.StudentDao;
import cn.kgc.dao.impl.StudentDaoImpl;
import cn.kgc.entity.Page;
import cn.kgc.entity.Student;
import cn.kgc.entity.User;
import cn.kgc.service.StudentService;
import cn.kgc.service.impl.StudentServiceImpl;
import cn.kgc.util.CommonExcel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/client/StudentServlet")
public class StudentServlet extends BeanServlet {
	private static final long serialVersionUID = 1L;
	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
     StudentService studentService = new StudentServiceImpl();
     
	 /*
		 * 分页查询多条数据
		 */
		protected void findStudent(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			String pageNum = request.getParameter("pageNum");
			String studentName = request.getParameter("queryStudentName");
     		String studentNumber =  request.getParameter("queryStudentNumber");
			int pageSize=10;
			
			Page<Student> page = studentService.findStudent(pageNum, pageSize, studentName, studentNumber);
					
			request.setAttribute("page", page);
			//转发到查询所有的页面
			request.getRequestDispatcher("/web/student/student.jsp").forward(request, response);
		}

        
		/*
		 * 新增方法
		 */
		protected void saveStudent(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			 response.setContentType("text/html");
				/**
				 * 获取页面中表单提交的内容
				 */
				String studentName = request.getParameter("studentName");
				Integer studentNumber=0;
				try {
					studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String  team= request.getParameter("team");
				Date birthDate=null;
				try {
					birthDate = dateFormat.parse(request.getParameter("birthDate")!=null?request.getParameter("birthDate"):null);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String  graduationStudy= request.getParameter("graduationStudy");
				String  profession= request.getParameter("profession");
				Date graduationTime=null;
				try {
					graduationTime = dateFormat.parse(request.getParameter("graduationTime")!=null?request.getParameter("graduationTime"):null);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String  studentMobilephone= request.getParameter("studentMobilephone");
				String  studentEmail= request.getParameter("studentEmail");
				String  relfile= request.getParameter("relfile");
				System.out.println(relfile);
				Student student = new Student(null,studentName,studentNumber,team,birthDate,graduationStudy,
						graduationTime,profession,studentMobilephone,studentEmail,relfile,null);
//				String info=gson.toJson(score);
				//放入数据库中
				int result =	studentService.saveStudent(student);
				PrintWriter out = response.getWriter();
				out.print(result);
				/**
				 * 判断是否添加成功
				 * 失败转发到当前页面
				 * 成功调用查询方法
				 */
				
				if(!(result==0)){
					//注册成功重定向到regist_success.html
					response.sendRedirect(request.getContextPath()+"/client/StudentServlet?method=findStudent");
					
				}else{
					//前台验证注册的内容，后台报“用户名存在”错误
					request.setAttribute("e", "系统错误：");
					//PrintWriter out = response.getWriter();
					
					//注册失败转发到当前页面
					request.getRequestDispatcher("/web/student/student.jsp").forward(request, response);
				}
		}
		
		
		/*
		 * 删除学生信息
		 */
		
		protected void delStudent(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			//获取页面中的id
			String studentId = request.getParameter("id");
			
			//执行删除操作
			int result=studentService.delStudent(studentId);
			request.setAttribute("result",result);
			if (!(result==0)) {
				request.setAttribute("success","删除成功");
	           	//完成后重新定向
				response.sendRedirect(request.getContextPath()+"/client/StudentServlet?method=findStudent");
			}else{
				request.setAttribute("msg","删除失败");
				//完成后重新定向
				response.sendRedirect(request.getContextPath()+"/client/StudentServlet?method=findStudent");
			}
			
		}
		
		
		/*
		 * 获取学生id查询该条信息并把该条信息返还给前台
		 */
		protected void getStudentById(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
						
			PrintWriter out = response.getWriter();
		    response.setContentType("text/html;charset=utf-8");
		  
			//获取需要修改的id
		    String studentId = request.getParameter("id"); 
			Student student = studentService.getStudentById(studentId);
			
			Gson gson = new GsonBuilder()
				        .setDateFormat("yyyy-MM-dd")
				        .create();
			//转为json对象
			String jsonteacher = gson.toJson(student);
			
			//将数据传到前端
			out.println(jsonteacher);    
			//将查询到的对象放入域中
			request.setAttribute("jsonteacher", jsonteacher);
			
		}
		
		
		/*
		 * 修改方法
		 */
		protected void updateStudent(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			 response.setContentType("text/html");
				/**
				 * 获取页面中表单提交的内容
				 */
			 String studentId = request.getParameter("id");
				String studentName = request.getParameter("studentName");
				Integer studentNumber=0;
				try {
					studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String  team= request.getParameter("team");
				Date birthDate=null;
				try {
					birthDate = dateFormat.parse(request.getParameter("birthDate")!=null?request.getParameter("birthDate"):null);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String  graduationStudy= request.getParameter("graduationStudy");
				String  profession= request.getParameter("profession");
				Date graduationTime=null;
				try {
					graduationTime = dateFormat.parse(request.getParameter("graduationTime")!=null?request.getParameter("graduationTime"):null);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String  studentMobilephone= request.getParameter("studentMobilephone");
				String  studentEmail= request.getParameter("studentEmail");
				String  relfile= request.getParameter("relfile");
				
				Student student = new Student(studentId,studentName,studentNumber,team,birthDate,graduationStudy,
						graduationTime,profession,studentMobilephone,studentEmail,relfile,null);
//				String info=gson.toJson(score);
				//放入数据库中
				int b =	studentService.updateStudent(student);
				
				/**
				 * 判断是否添加成功
				 * 失败转发到当前页面
				 * 成功调用查询方法
				 */
				if(!(b==0)){
					//注册成功重定向到regist_success.html
					response.sendRedirect(request.getContextPath()+"/client/StudentServlet?method=findStudent");
					
				}else{
					//前台验证注册的内容，后台报“用户名存在”错误
					request.setAttribute("msg", "系统错误");
					//注册失败转发到当前页面
					request.getRequestDispatcher("/web/student/student.jsp").forward(request, response);
				}
		}
		
		
		/*
		 * excel导出功能
		 */

       
		protected void downExcel(HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			response=null;
			//StudentDao studentdao = new StudentDaoImpl();
			
			request.setCharacterEncoding("utf-8");
			String   set = request.getParameter("set");
			String [] sets = set.split(",");
			System.out.println(sets);
			
			
			 String title = "学生档案报表";
			    String[] rowsName = new String[]{"学生姓名","学号","班级","出生日期","毕业学校","所学专业","毕业时间","手机号码","邮箱"};
			    List<Object[]>  dataList = new ArrayList<Object[]>();
			   
			    Object[] objs =null;
			    dataList.add(objs);
			   /* for (int i = 0; i < 10; i++) {
			        objs = new Object[rowsName.length];
			        objs[0] = i;
			        objs[1] = i;
			        objs[2] = i;
			        objs[3] = i;
			        objs[4] = i;
			        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        String date = df.format(new Date());
			        objs[5] = date;
			        dataList.add(objs);
			    }*/
			    String fileName="学生档案报表-"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xlsx";
			    CommonExcel ex = new CommonExcel(title, rowsName,dataList ,response,fileName);
			    ex.downloadExcel();
			    
			
		}
		
}
