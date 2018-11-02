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
							//String path = "E:/xuexispaceB/xuexispaceC/ClassManagementSystem/WebContent/upload";
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
