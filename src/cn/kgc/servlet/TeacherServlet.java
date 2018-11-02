package cn.kgc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;

import java.text.SimpleDateFormat;


import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.kgc.entity.Page;
import cn.kgc.entity.Teacher;
import cn.kgc.service.TeacherService;
import cn.kgc.service.impl.TeacherServiceImpl;

import com.google.gson.Gson;


@WebServlet("/manager/TeacherServlet")
public class TeacherServlet extends BeanServlet {
	private static final long serialVersionUID = 1L;
	
	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 
    TeacherService teacherService=new TeacherServiceImpl();
    
    /*
	 * ��ҳ��ѯ����
	 */
    protected void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNum = request.getParameter("pageNum");
		int pageSize=10;
		
		Page<Teacher> page = teacherService.findAll(pageNum, pageSize);
		
		request.setAttribute("page", page);
		//ת������ѯ���е�ҳ��
		request.getRequestDispatcher("/web/teacher/teacher.jsp").forward(request, response);

	}
   
    /*
	 * ��������ѯ��������
	 */
	protected void findTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNum = request.getParameter("pageNum");
		int pageSize=10;
		                                               
        String queryTeacherName=request.getParameter("queryTeacherName");		
		String queryJobNumber=request.getParameter("queryJobNumber");
		
		
		Page<Teacher> page = teacherService.findTeacher(pageNum, pageSize, queryTeacherName, queryJobNumber);
		request.setAttribute("page", page);
		
			request.getRequestDispatcher("/web/teacher/teacher.jsp").forward(request, response);
		
	}
	
	
	
	
	/*
	 * �޸Ľ�ʦ��Ϣ
	 */
	protected void updateTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * ��ȡҳ���б��ύ������
		 */
		String teacherId = request.getParameter("id");
		String teacherName = request.getParameter("teacherName");
		Integer jobNumber=0;
		String position = request.getParameter("position");
        String birthDate = request.getParameter("birthDate");
        Date date = null;
        Integer isMarry=0;
		String mobilephone = request.getParameter("mobilephone");
		String email = request.getParameter("email");
		try {
			
			jobNumber = Integer.parseInt(request.getParameter("jobNumber"));
			date =dateFormat.parse(birthDate);
			isMarry = Integer.parseInt(request.getParameter("isMarry"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String relfile = request.getParameter("relfile");
		
		Teacher teacher = new Teacher(teacherId, teacherName, jobNumber, position, date, isMarry, mobilephone,email,relfile);
		
		//�������ݿ���
		int b =	teacherService.updateTeacher(teacher);
		
		/**
		 * �ж��Ƿ���ӳɹ�
		 * ʧ��ת������ǰҳ��
		 * �ɹ����ò�ѯ����
		 */
		if(!(b==0)){
			//�ɹ��ض���
			response.sendRedirect(request.getContextPath()+"/manager/TeacherServlet?method=findAll");
			
		}else{
			//ǰ̨��֤�����ݣ���̨�����û������ڡ�����
			request.setAttribute("msg", "�û����Ѵ���");
			//ʧ��ת������ǰҳ��
			request.getRequestDispatcher("/teacher/teacher.jsp").forward(request, response);
		}
		
		
	}
	
	
	/*
	 * ����idɾ����ʦ��Ϣ
	 */
	protected void delTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��ȡid
		String teacherId = request.getParameter("id");
		
		//ִ��ɾ������
		int result = teacherService.delTeacher(teacherId);
		request.setAttribute("result",result);
		
		if (!(result==0)) {
			request.setAttribute("success","ɾ���ɹ�");
           	//��ɺ����¶���
			response.sendRedirect(request.getContextPath()+"/manager/TeacherServlet?method=findAll");
		}else{
			request.setAttribute("msg","ɾ��ʧ��");
			//��ɺ����¶���
			response.sendRedirect(request.getContextPath()+"/manager/TeacherServlet?method=findAll");
		}
		
	}
	
	/*
	 * ��ȡ��ʦid
	 */
	protected void getTeacherById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();
	    response.setContentType("text/html;charset=utf-8");
	  
	    request.setCharacterEncoding("utf-8");

		//��ȡ��Ҫ�޸ĵ�id
	    String teacherId = request.getParameter("id"); 
		
	    Teacher teacher = teacherService.getTeacherById(teacherId);
		
		Gson gson = new Gson();
		//תΪjson����
		String jsonteacher = gson.toJson(teacher);
		
		//�����ݴ���ǰ��
		out.println(jsonteacher);    
		//����ѯ���Ķ����������
		request.setAttribute("jsonteacher", jsonteacher);
		
		
	}
	
	/*
	 * ���ӽ�ʦ��Ϣ�ķ��� 
	 */
	protected void saveTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		/**
		 * ��ȡҳ���б��ύ������
		 */
		
		String teacherName = request.getParameter("teacherName");
		Integer jobNumber=0;
		String position = request.getParameter("position");
        String birthDate = request.getParameter("birthDate");
        Date date = null;
        Integer isMarry=0;
		String mobilephone = request.getParameter("mobilephone");
		String email = request.getParameter("email");
		try {
			jobNumber = Integer.parseInt(request.getParameter("jobNumber"));
			date =(Date) dateFormat.parse(birthDate);
			isMarry = Integer.parseInt(request.getParameter("isMarry"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String relfile = request.getParameter("relfile");
		Teacher teacher = new Teacher(null, teacherName, jobNumber, position,date, isMarry, mobilephone,email,relfile);
		
		//�������ݿ���
		int b =	teacherService.saveTeacher(teacher);
		
		/**
		 * �ж��Ƿ���ӳɹ�
		 * ʧ��ת������ǰҳ��
		 * �ɹ����ò�ѯ����
		 */
		if(!(b==0)){
			//ע��ɹ��ض���
			response.sendRedirect(request.getContextPath()+"/manager/TeacherServlet?method=findAll");
			
		}else{
			//ǰ̨��֤ע������ݣ���̨�����û������ڡ�����
			request.setAttribute("msg", "�û����Ѵ���");
			//ע��ʧ��ת������ǰҳ��
			request.getRequestDispatcher("/teacher/teacher.jsp").forward(request, response);
		}
	}
    
	
   /*
    * ��ѯ���н�ʦ��Ϣ�ķ���
    */
	protected void TeacherList (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����service������ѯ���еĽ�ʦ��Ϣ
				List<Teacher> list = teacherService.getTeacherList();
				
				//����ѯ����ͼ�鼯�Ϸŵ�request����
				request.setAttribute("list", list);
				
				//ת����pages\manager\teacher.jsp
				request.getRequestDispatcher("/web/teacher/teacher.jsp").forward(request, response);
	}
    
	
	protected void readimages(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
	
	/*
	 * �ϴ�ͼƬ����/upload·����
	 */
	public void uploadFileimages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
		       //���post��������
				request.setCharacterEncoding("utf-8");
				
				//��ȡ������ʵ��
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				//��ȡ��������ʵ��
				ServletFileUpload upload = new ServletFileUpload(factory);
				
				//�����ļ��Ĵ�С��Ҫͨ�����ý��������һ������
				//setFileSizeMax�����ϴ��ĵ����ļ��Ĵ�С
				//���Ƶ����ļ���СΪ50k
				upload.setFileSizeMax(1024*70*70);
				
				//����ȫ���ļ���СΪ150k
				upload.setSizeMax(1024*150*150);
				
				try {
					//����request
					List<FileItem> fileItems = upload.parseRequest(request);
					
					//����fileItems
					for (FileItem fileItem : fileItems) {
						
						//�ж�FileItem�Ƿ���һ����ͨ����
						if(fileItem.isFormField()){
							//��һ����ͨ����
							//��ȡ��name����
							String fieldName = fileItem.getFieldName();
							
							//��ȡ�û���дֵ��Ҳ���Ǳ���value����
							String value = fileItem.getString("utf-8");
							
						}else{
							//�����һ���ļ�����
							//��ȡ��name����
							String fieldName = fileItem.getFieldName();
							//��ȡ�ļ�������
							String name = fileItem.getName();
							//��ȡ�ļ�������
							String contentType = fileItem.getContentType();
							//��ȡ�ļ��Ĵ�С
							long size = fileItem.getSize();
							
							//�ж��ļ������Ƿ����\
							if(name.contains("\\")){
								//��ȡ�ַ���
								name = name.substring(name.lastIndexOf("\\")+1);
							}
							
							//Ϊ�ļ�������һ��ǰ׺
//							String prefix = UUID.randomUUID().toString().replace("-", "");
							
							//�޸��ļ���
//							String fileName = prefix+"_"+name;
							
							
//							System.out.println("fieldName : "+fieldName);
							System.out.println("name : "+name);
//							System.out.println("fileName : "+fileName);
							System.out.println("contentType : "+contentType);
							System.out.println("size : "+size);
							
							//���ļ�д����������
							
							//��ȡServletContext
							ServletContext servletContext = getServletContext();
							//��ȡ��Ŀ����ʵ·��
							String path = servletContext.getRealPath("/upload");
							//·��ת��һ��File
							File file = new File(path);
							//�ж��ļ����Ƿ����
							if(!file.exists()){
								//�����ļ���
								file.mkdirs();
							}
							
							//���ļ�д�뵽��������
							fileItem.write(new File(path+"/"+name));
							
							PrintWriter out = response.getWriter();
							//���ظ�ǰ̨
							Gson gson = new Gson();
							//תΪjson����
							String jsonname = gson.toJson(name);
							
							out.println(jsonname);
							
						}
					}
					
					
					
					
				}catch(SizeLimitExceededException e){
					
					//���ָ��쳣��˵���ļ��ܴ�С�������ƣ�����һ��������Ϣ
					request.setAttribute("msg", "�ļ��ܴ�С��Ҫ����150KB");
					//ת����index.jsp
					//request.getRequestDispatcher("/index.jsp").forward(request, response);
					
				}catch(FileSizeLimitExceededException e){
					//���ָ��쳣��˵�������ļ���С�������ƣ�����һ��������Ϣ
					request.setAttribute("msg", "�����ļ���Ҫ����70KB");
					//ת����index.jsp
					//request.getRequestDispatcher("/index.jsp").forward(request, response);
					
				}catch (FileUploadException e) {
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
	}
}
