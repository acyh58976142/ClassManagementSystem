package cn.kgc.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.kgc.dao.teacherinfo_now;
import cn.kgc.dao.teacherinfo_nowImp;
import cn.kgc.entity.Page;
import cn.kgc.entity.Teacher;
import cn.kgc.entity.studentInfo;
import cn.kgc.entity.teahcerInfo;
import cn.kgc.service.TeacherService;
import cn.kgc.service.UserService;
import cn.kgc.service.impl.TeacherServiceImpl;
import cn.kgc.service.impl.UserServiceImpl;



@WebServlet("/client/teacherAttendance1")
public class teacherAttendance1 extends BeanServlet{
		
	
	private Page<teahcerInfo> pageSize;
	protected void teacherAttendance11(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		teacherinfo_now teacherinfo=new teacherinfo_nowImp();
				//搜索框
		String select_querylocation1=req.getParameter("select_querylocation1");
		String queryname1=req.getParameter("queryname1");
		System.out.println(queryname1);
		SimpleDateFormat ee=new SimpleDateFormat("yyyy-MM-dd");
		Date query=null;
		try{
		 query=ee.parse(queryname1);
		}catch(Exception e){
			e.printStackTrace();
		}
		teahcerInfo s=new teahcerInfo(null,select_querylocation1,null,null,null,null,query);
		teahcerInfo kk=teacherinfo.teacherAttendance(s);
		if(kk!=null){
			req.setAttribute("kk", kk);
/*		System.out.println(kk.getId()+" "+kk.getTeather_name()+" "+kk.getJob_number()+" "+kk.getPosition()+" "+kk.getTeacher_mobilephone()+" "+kk.getIsabsence());
*/		
			req.getRequestDispatcher("/web/kaoqin/kaoqin.jsp").forward(req, resp);
		}else{
			req.setAttribute("msg", "不好意思你查询失败了");
			req.getRequestDispatcher("/web/kaoqin/kaoqin.jsp").forward(req,resp);
	
		}
		
	}
	
	protected void teacherInfo(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		teacherinfo_now teacherinfo=new teacherinfo_nowImp();
		req.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		String input_name=req.getParameter("input_name");
		String input_code=req.getParameter("input_code");//教师姓名
		String input_modal=req.getParameter("input_model");//工号
		int modal=0;
			try{
				modal=Integer.parseInt(input_modal);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		String input_brand=req.getParameter("input_brand");//职位
		String input_birth=req.getParameter("input_birth");  //出生日期
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 Date date=null;
		try {
			date = sdf.parse(input_birth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String input_deviceGroup=req.getParameter("input_deviceGroup");//婚姻状态
		int Group=0;
		try{
			Group=Integer.parseInt(input_deviceGroup);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String input_deviceLevel=req.getParameter("input_deviceLevel");//联系方式
		String input_email=req.getParameter("input_email");//邮箱
		teahcerInfo teacher=new teahcerInfo(input_name,input_code,modal,input_brand,date,Group,input_deviceLevel,input_email);
		Integer teacherss=teacherinfo.updateTeacherInfo(teacher);
		if(teacherss!=0||teacherss!=null){
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
			resp.sendRedirect("/ClassManagementSystem/success.jsp");
		}/*else{
			req.getRequestDispatcher("web/teacher/teacher.jsp").forward(req,resp);
		}*/
	}
	
	protected void update_teacherinfo(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		teacherinfo_now teacherinfo=new teacherinfo_nowImp();
		String select_querylocation1=request.getParameter("select_querylocation11");
		String queryname1=request.getParameter("queryname11");
		Integer query=null;
		try{
		 query=Integer.parseInt(queryname1);
		}catch(Exception e){
			e.printStackTrace();
		}
		teahcerInfo s=new teahcerInfo(null,select_querylocation1,query,null,null,null, null);
		teahcerInfo kk=teacherinfo.teacherAttendance(s);
		HttpSession session= request.getSession();
		session.setAttribute("msg","信息输入有误");
		session.setAttribute("kkk", kk);
		try {
			request.getRequestDispatcher("/web/kaoqin/kaoqin.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*if(kk!=null){	
			try {
				request.getRequestDispatcher("/web/kaoqin/kaoqin.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			request.setAttribute("msg", "不好意思你查询失败了");
			try {
				request.getRequestDispatcher("/web/kaoqin/kaoqin.jsp").forward(request,response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
*/
		
	}
	
	
	protected void pageOfme(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		teacherinfo_now ss=new teacherinfo_nowImp();
		TeacherService  kk=new TeacherServiceImpl();
		String pageNum=request.getParameter("pagenum");
		int pageSize=4;
		request.setAttribute("msg", "删除此条信息");
		
			Page<Teacher> pages =kk.findAll(pageNum, pageSize);
			request.setAttribute("pages", pages);
			request.getRequestDispatcher("/web/teacher/teacher.jsp").forward(request,response);
		
	}
	
	protected void teacher_info(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		teacherinfo_now teacherinfo=new teacherinfo_nowImp();
	
	//搜索框
		String select_querylocation1=req.getParameter("select_querylocation1");
		
		String queryname1=req.getParameter("queryname1");
		Integer query=null;
		try{
		 query=Integer.parseInt(queryname1);
		}catch(Exception e){
			e.printStackTrace();
		}
		req.setAttribute("msg", "删除此条信息");
		teahcerInfo s=new teahcerInfo();
		
		s.setTeather_name(select_querylocation1);
		s.setJob_number(query);
		teahcerInfo kk=teacherinfo.teahcer_Info(s);
		if(kk!=null){
			req.setAttribute("kk", kk);
			req.getRequestDispatcher("/web/teacher/teacher.jsp").forward(req, resp);
		
	}
	
}
	protected void delete_OfOne(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("UTF-8");
		teacherinfo_now teacher=new teacherinfo_nowImp();
		String teather_name=request.getParameter("teacher_id");
		teather_name = new String(teather_name.getBytes("ISO-8859-1"),"UTF-8");
		teahcerInfo s=new teahcerInfo();
		s.setTeather_name(teather_name);
		int deleteTeacherInfo = teacher.deleteTeacherInfo(s);
		if(deleteTeacherInfo!=0){
			try {
				request.getRequestDispatcher("/web/teacher/teacher.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	protected void delete_all(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		teacherinfo_now teacher=new teacherinfo_nowImp();
		String teacher_name=request.getParameter("name_teacher");
		teacher_name = new String(teacher_name.getBytes("ISO-8859-1"),"UTF-8");
		teahcerInfo s=new teahcerInfo();
		s.setTeather_name(teacher_name);
		
		int deleteTeacherInfo = teacher.deleteTeacherInfo(s);
		if(deleteTeacherInfo!=0){
			try {
				request.getRequestDispatcher("/web/teacher/teacher.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	}
	
	protected void pageOfUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		UserService ss=new UserServiceImpl();
		TeacherService  kk=new TeacherServiceImpl();
		String pageNum=request.getParameter("pagenum");
		int pageSize=4;
		
			Page<Teacher> pages =kk.findAll(pageNum, pageSize);
			request.setAttribute("msg1", "确定修改此条信息");
			request.setAttribute("pagess", pages);
			try {
				request.getRequestDispatcher("/web/teacher/teacher.jsp").forward(request,response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/*修改的信息*/
	protected void update_all(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		teacherinfo_now teacher=new teacherinfo_nowImp();
		String teacher_name=request.getParameter("name_teacher");
		teacher_name = new String(teacher_name.getBytes("ISO-8859-1"),"UTF-8");	
		String job_number11=request.getParameter("job_number11");
		int job_number1=Integer.parseInt(job_number11);
		String position=request.getParameter("position");
		
		System.out.println(position);
		String birth_date=request.getParameter("birth_date");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 Date date=null;
		try {
			date = sdf.parse(birth_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date);
		String ismarry=request.getParameter("ismarry");
		int ismarrys=Integer.parseInt(ismarry);
		String phone=request.getParameter("phone");
		phone = new String(phone.getBytes("ISO-8859-1"),"UTF-8");	
		teahcerInfo s=new teahcerInfo();
		s.setTeather_name(teacher_name);
		s.setJob_number(job_number1);
		s.setPosition(position);
		s.setBirth_date(date);
		s.setIsmarry(ismarrys);
		s.setTeacher_mobilephone(phone);
		int updateTeacherInfo_one = teacher.updateTeacherInfo_one(s);
		if(updateTeacherInfo_one!=0){
			try {
				request.getRequestDispatcher("/web/teacher/teacher.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	protected void teacherAttendance_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		teacherinfo_now teacher=new teacherinfo_nowImp();
		String input_name1=request.getParameter("input_name1");	
		String input_code1=request.getParameter("input_code1");
		int number=Integer.parseInt(input_code1);
		String input_attendance=request.getParameter("input_attendance");
		int isdabase=Integer.parseInt(input_attendance);
		String input_attendanceDate=request.getParameter("input_attendanceDate");
		SimpleDateFormat sa=new SimpleDateFormat("yyyy-MM-dd");
		Date ll=null;
		try {
			ll=sa.parse(input_attendanceDate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		teahcerInfo s=new teahcerInfo();
		s.setTeather_name(input_name1);
		s.setJob_number(number);
		s.setIsabsence(isdabase);
		s.setAttendance_date(ll);
		int teacherAttendance_update = teacher.teacherAttendance_update(s);
		if(teacherAttendance_update!=0){
			try {
				
				request.getRequestDispatcher("/web/kaoqin/kaoqin.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			request.setAttribute("msg1", "信息更新失败!");
			try {
				request.getRequestDispatcher("/web/kaoqin/kaoqin.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	/*新增学生信息*/
	protected void add_studentInfo(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		teacherinfo_now kk=new teacherinfo_nowImp();
		String input_id=request.getParameter("input_id");
		String input_name=request.getParameter("input_name");
		String input_code=request.getParameter("input_code");
		int code=Integer.parseInt(input_code);
		String input_model=request.getParameter("input_model");
		String input_birth=request.getParameter("input_birth");
		SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try{
			date=ss.parse(input_birth);
		}catch(Exception e){
			e.printStackTrace();
		}
		String input_brand=request.getParameter("input_brand");
		String input_deviceGroup=request.getParameter("input_deviceGroup");
		String Out=request.getParameter("OutLine");
		Date datess=null;
		try{
			datess=ss.parse(Out);
		}catch(Exception e){
			e.printStackTrace();
		}
		String input_location=request.getParameter("input_location");
		String email=request.getParameter("email");
		studentInfo ss1=new studentInfo(input_id,input_name,code,input_model,date,input_brand,datess,input_deviceGroup,input_location,email,null,null);
		int student_Info = kk.student_Info(ss1);
		if(student_Info!=0){
			request.setAttribute("msg", "信息添加成功!");
			try {
				request.getRequestDispatcher("/web/Student/Student.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
	
}
