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
	 * ��ҳ��ѯ��������
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
		//ת������ѯ���е�ҳ��
		request.getRequestDispatcher("/web/kaoqin/kaoqin_student.jsp").forward(request, response);

		
	}
	
	
	/*
	 * ��������
	 */
	protected void saveAttendance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.setContentType("text/html");
			/**
			 * ��ȡҳ���б��ύ������
			 */
		 System.out.println("1");
		 int isabsence=Integer.parseInt(request.getParameter("isabsence"));
		 String studentName=request.getParameter("studentName");
		 System.out.println(studentName+"\n"+isabsence);
		 
		 
		 Attendance attendance = new Attendance(null,studentName,null,isabsence);

			//�������ݿ���
			int b =	attendanceService.saveAttendance(attendance);
			
			/**
			 * �ж��Ƿ���ӳɹ�
			 * ʧ��ת������ǰҳ��
			 * �ɹ����ò�ѯ����
			 */
			if(!(b==0)){
				//ע��ɹ��ض���regist_success.html
				response.sendRedirect(request.getContextPath()+"/AttendanceServlet?method=findAttendance");
				
			}else{
				//ǰ̨��֤ע������ݣ���̨�����û������ڡ�����
				request.setAttribute("e", "ϵͳ����");
				//ע��ʧ��ת������ǰҳ��
				request.getRequestDispatcher("/web/kaoqin/kaoqin_student.jsp").forward(request, response);
			}
	}
	
	
	
	/*
	 * ɾ��ѧ����Ϣ
	 */
	
	protected void delAttendance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��ȡҳ���е�id
		String attendanceServiceId = request.getParameter("id");
		
		//ִ��ɾ������
		int result=attendanceService.delAttendance(attendanceServiceId);
		request.setAttribute("result",result);
		if (!(result==0)) {
			request.setAttribute("success","ɾ���ɹ�");
           	//��ɺ����¶���
			response.sendRedirect(request.getContextPath()+"/client/AttendanceServlet?method=findAttendance");
		}else{
			request.setAttribute("msg","ɾ��ʧ��");
			//��ɺ����¶���
			response.sendRedirect(request.getContextPath()+"/client/AttendanceServlet?method=findAttendance");
		}
		
	}
	
	
	/*
	 * ��ȡѧ��id��ѯ������Ϣ���Ѹ�����Ϣ������ǰ̨
	 */
	protected void getAttendanceById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
					
		PrintWriter out = response.getWriter();
	    response.setContentType("text/html;charset=utf-8");
	  
		//��ȡ��Ҫ�޸ĵ�id
	    String attendanceId = request.getParameter("id"); 
		Attendance attendance = attendanceService.getAttendanceById(attendanceId);
		
		Gson gson = new Gson();
		//תΪjson����
		String jsonattendance = gson.toJson(attendance);
		
		//�����ݴ���ǰ��
		out.println(jsonattendance);    
		//����ѯ���Ķ����������
		request.setAttribute("jsonattendance", jsonattendance);
		
	}
	
	
	/*
	 * �޸ķ���
	 */
	protected void updateAttendance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.setContentType("text/html");
			/**
			 * ��ȡҳ���б��ύ������
			 */
		  String attendanceId = request.getParameter("id");
		 int isabsence=Integer.parseInt(request.getParameter("isabsence"));
		 String studentName=request.getParameter("studentName");
		 System.out.println(studentName+"\n"+isabsence);
		 		
			
		 Attendance attendance = new Attendance(attendanceId,studentName,null,isabsence);
//			String info=gson.toJson(score);
			//�������ݿ���
			int b =	attendanceService.updateAttendance(attendance);
			
			/**
			 * �ж��Ƿ���ӳɹ�
			 * ʧ��ת������ǰҳ��
			 * �ɹ����ò�ѯ����
			 */
			if(!(b==0)){
				//ע��ɹ��ض���regist_success.html
				response.sendRedirect(request.getContextPath()+"/AttendanceServlet?method=findAttendance");
				
			}else{
				//ǰ̨��֤ע������ݣ���̨�����û������ڡ�����
				request.setAttribute("msg", "ϵͳ����");
				//ע��ʧ��ת������ǰҳ��
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
