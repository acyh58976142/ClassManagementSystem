package cn.kgc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/client/UploadServlet")
public class UploadServlet extends BeanServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
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
							//String path = "E:/xuexispaceB/xuexispaceC/ClassManagementSystem/WebContent/upload";
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
