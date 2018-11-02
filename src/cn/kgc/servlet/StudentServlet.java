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
		 * ��ҳ��ѯ��������
		 */
		protected void findStudent(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			String pageNum = request.getParameter("pageNum");
			String studentName = request.getParameter("queryStudentName");
     		String studentNumber =  request.getParameter("queryStudentNumber");
			int pageSize=10;
			
			Page<Student> page = studentService.findStudent(pageNum, pageSize, studentName, studentNumber);
					
			request.setAttribute("page", page);
			//ת������ѯ���е�ҳ��
			request.getRequestDispatcher("/web/student/student.jsp").forward(request, response);
		}

        
		/*
		 * ��������
		 */
		protected void saveStudent(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			 response.setContentType("text/html");
				/**
				 * ��ȡҳ���б��ύ������
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
				//�������ݿ���
				int result =	studentService.saveStudent(student);
				PrintWriter out = response.getWriter();
				out.print(result);
				/**
				 * �ж��Ƿ���ӳɹ�
				 * ʧ��ת������ǰҳ��
				 * �ɹ����ò�ѯ����
				 */
				
				if(!(result==0)){
					//ע��ɹ��ض���regist_success.html
					response.sendRedirect(request.getContextPath()+"/client/StudentServlet?method=findStudent");
					
				}else{
					//ǰ̨��֤ע������ݣ���̨�����û������ڡ�����
					request.setAttribute("e", "ϵͳ����");
					//PrintWriter out = response.getWriter();
					
					//ע��ʧ��ת������ǰҳ��
					request.getRequestDispatcher("/web/student/student.jsp").forward(request, response);
				}
		}
		
		
		/*
		 * ɾ��ѧ����Ϣ
		 */
		
		protected void delStudent(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			//��ȡҳ���е�id
			String studentId = request.getParameter("id");
			
			//ִ��ɾ������
			int result=studentService.delStudent(studentId);
			request.setAttribute("result",result);
			if (!(result==0)) {
				request.setAttribute("success","ɾ���ɹ�");
	           	//��ɺ����¶���
				response.sendRedirect(request.getContextPath()+"/client/StudentServlet?method=findStudent");
			}else{
				request.setAttribute("msg","ɾ��ʧ��");
				//��ɺ����¶���
				response.sendRedirect(request.getContextPath()+"/client/StudentServlet?method=findStudent");
			}
			
		}
		
		
		/*
		 * ��ȡѧ��id��ѯ������Ϣ���Ѹ�����Ϣ������ǰ̨
		 */
		protected void getStudentById(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
						
			PrintWriter out = response.getWriter();
		    response.setContentType("text/html;charset=utf-8");
		  
			//��ȡ��Ҫ�޸ĵ�id
		    String studentId = request.getParameter("id"); 
			Student student = studentService.getStudentById(studentId);
			
			Gson gson = new GsonBuilder()
				        .setDateFormat("yyyy-MM-dd")
				        .create();
			//תΪjson����
			String jsonteacher = gson.toJson(student);
			
			//�����ݴ���ǰ��
			out.println(jsonteacher);    
			//����ѯ���Ķ����������
			request.setAttribute("jsonteacher", jsonteacher);
			
		}
		
		
		/*
		 * �޸ķ���
		 */
		protected void updateStudent(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			 response.setContentType("text/html");
				/**
				 * ��ȡҳ���б��ύ������
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
				//�������ݿ���
				int b =	studentService.updateStudent(student);
				
				/**
				 * �ж��Ƿ���ӳɹ�
				 * ʧ��ת������ǰҳ��
				 * �ɹ����ò�ѯ����
				 */
				if(!(b==0)){
					//ע��ɹ��ض���regist_success.html
					response.sendRedirect(request.getContextPath()+"/client/StudentServlet?method=findStudent");
					
				}else{
					//ǰ̨��֤ע������ݣ���̨�����û������ڡ�����
					request.setAttribute("msg", "ϵͳ����");
					//ע��ʧ��ת������ǰҳ��
					request.getRequestDispatcher("/web/student/student.jsp").forward(request, response);
				}
		}
		
		
		/*
		 * excel��������
		 */

       
		protected void downExcel(HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			response=null;
			//StudentDao studentdao = new StudentDaoImpl();
			
			request.setCharacterEncoding("utf-8");
			String   set = request.getParameter("set");
			String [] sets = set.split(",");
			System.out.println(sets);
			
			
			 String title = "ѧ����������";
			    String[] rowsName = new String[]{"ѧ������","ѧ��","�༶","��������","��ҵѧУ","��ѧרҵ","��ҵʱ��","�ֻ�����","����"};
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
			    String fileName="ѧ����������-"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xlsx";
			    CommonExcel ex = new CommonExcel(title, rowsName,dataList ,response,fileName);
			    ex.downloadExcel();
			    
			
		}
		
}
