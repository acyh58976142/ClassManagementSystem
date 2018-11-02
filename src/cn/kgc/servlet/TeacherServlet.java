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
	 * 分页查询所有
	 */
    protected void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNum = request.getParameter("pageNum");
		int pageSize=10;
		
		Page<Teacher> page = teacherService.findAll(pageNum, pageSize);
		
		request.setAttribute("page", page);
		//转发到查询所有的页面
		request.getRequestDispatcher("/web/teacher/teacher.jsp").forward(request, response);

	}
   
    /*
	 * 带索引查询多条数据
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
	 * 修改教师信息
	 */
	protected void updateTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 获取页面中表单提交的内容
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
		
		//放入数据库中
		int b =	teacherService.updateTeacher(teacher);
		
		/**
		 * 判断是否添加成功
		 * 失败转发到当前页面
		 * 成功调用查询方法
		 */
		if(!(b==0)){
			//成功重定向到
			response.sendRedirect(request.getContextPath()+"/manager/TeacherServlet?method=findAll");
			
		}else{
			//前台验证的内容，后台报“用户名存在”错误
			request.setAttribute("msg", "用户名已存在");
			//失败转发到当前页面
			request.getRequestDispatcher("/teacher/teacher.jsp").forward(request, response);
		}
		
		
	}
	
	
	/*
	 * 根据id删除教师信息
	 */
	protected void delTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取id
		String teacherId = request.getParameter("id");
		
		//执行删除操作
		int result = teacherService.delTeacher(teacherId);
		request.setAttribute("result",result);
		
		if (!(result==0)) {
			request.setAttribute("success","删除成功");
           	//完成后重新定向
			response.sendRedirect(request.getContextPath()+"/manager/TeacherServlet?method=findAll");
		}else{
			request.setAttribute("msg","删除失败");
			//完成后重新定向
			response.sendRedirect(request.getContextPath()+"/manager/TeacherServlet?method=findAll");
		}
		
	}
	
	/*
	 * 获取教师id
	 */
	protected void getTeacherById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();
	    response.setContentType("text/html;charset=utf-8");
	  
	    request.setCharacterEncoding("utf-8");

		//获取需要修改的id
	    String teacherId = request.getParameter("id"); 
		
	    Teacher teacher = teacherService.getTeacherById(teacherId);
		
		Gson gson = new Gson();
		//转为json对象
		String jsonteacher = gson.toJson(teacher);
		
		//将数据传到前端
		out.println(jsonteacher);    
		//将查询到的对象放入域中
		request.setAttribute("jsonteacher", jsonteacher);
		
		
	}
	
	/*
	 * 增加教师信息的方法 
	 */
	protected void saveTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		/**
		 * 获取页面中表单提交的内容
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
		
		//放入数据库中
		int b =	teacherService.saveTeacher(teacher);
		
		/**
		 * 判断是否添加成功
		 * 失败转发到当前页面
		 * 成功调用查询方法
		 */
		if(!(b==0)){
			//注册成功重定向到
			response.sendRedirect(request.getContextPath()+"/manager/TeacherServlet?method=findAll");
			
		}else{
			//前台验证注册的内容，后台报“用户名存在”错误
			request.setAttribute("msg", "用户名已存在");
			//注册失败转发到当前页面
			request.getRequestDispatcher("/teacher/teacher.jsp").forward(request, response);
		}
	}
    
	
   /*
    * 查询所有教师信息的方法
    */
	protected void TeacherList (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用service方法查询所有的教师信息
				List<Teacher> list = teacherService.getTeacherList();
				
				//将查询到的图书集合放到request域中
				request.setAttribute("list", list);
				
				//转发到pages\manager\teacher.jsp
				request.getRequestDispatcher("/web/teacher/teacher.jsp").forward(request, response);
	}
    
	
	protected void readimages(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
	
	/*
	 * 上传图片放入/upload路径下
	 */
	public void uploadFileimages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
		       //解决post请求乱码
				request.setCharacterEncoding("utf-8");
				
				//获取工厂类实例
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				//获取解析器类实例
				ServletFileUpload upload = new ServletFileUpload(factory);
				
				//闲置文件的大小需要通过设置解析器类的一个属性
				//setFileSizeMax设置上传的单个文件的大小
				//限制单个文件大小为50k
				upload.setFileSizeMax(1024*70*70);
				
				//限制全部文件大小为150k
				upload.setSizeMax(1024*150*150);
				
				try {
					//解析request
					List<FileItem> fileItems = upload.parseRequest(request);
					
					//遍历fileItems
					for (FileItem fileItem : fileItems) {
						
						//判断FileItem是否是一个普通表单项
						if(fileItem.isFormField()){
							//是一个普通表单项
							//获取表单name属性
							String fieldName = fileItem.getFieldName();
							
							//获取用户填写值，也就是表单的value属性
							String value = fileItem.getString("utf-8");
							
						}else{
							//如果是一个文件表单项
							//获取表单name属性
							String fieldName = fileItem.getFieldName();
							//获取文件的名字
							String name = fileItem.getName();
							//获取文件的类型
							String contentType = fileItem.getContentType();
							//获取文件的大小
							long size = fileItem.getSize();
							
							//判断文件名中是否包含\
							if(name.contains("\\")){
								//截取字符串
								name = name.substring(name.lastIndexOf("\\")+1);
							}
							
							//为文件名生成一个前缀
//							String prefix = UUID.randomUUID().toString().replace("-", "");
							
							//修改文件名
//							String fileName = prefix+"_"+name;
							
							
//							System.out.println("fieldName : "+fieldName);
							System.out.println("name : "+name);
//							System.out.println("fileName : "+fileName);
							System.out.println("contentType : "+contentType);
							System.out.println("size : "+size);
							
							//将文件写到服务器中
							
							//获取ServletContext
							ServletContext servletContext = getServletContext();
							//获取项目的真实路径
							String path = servletContext.getRealPath("/upload");
							//路径转换一个File
							File file = new File(path);
							//判断文件夹是否存在
							if(!file.exists()){
								//创建文件夹
								file.mkdirs();
							}
							
							//将文件写入到服务器中
							fileItem.write(new File(path+"/"+name));
							
							PrintWriter out = response.getWriter();
							//返回给前台
							Gson gson = new Gson();
							//转为json对象
							String jsonname = gson.toJson(name);
							
							out.println(jsonname);
							
						}
					}
					
					
					
					
				}catch(SizeLimitExceededException e){
					
					//出现该异常，说明文件总大小超过限制，设置一个错误消息
					request.setAttribute("msg", "文件总大小不要超过150KB");
					//转发到index.jsp
					//request.getRequestDispatcher("/index.jsp").forward(request, response);
					
				}catch(FileSizeLimitExceededException e){
					//出现该异常，说明单个文件大小超过限制，设置一个错误消息
					request.setAttribute("msg", "单个文件不要超过70KB");
					//转发到index.jsp
					//request.getRequestDispatcher("/index.jsp").forward(request, response);
					
				}catch (FileUploadException e) {
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
	}
}
