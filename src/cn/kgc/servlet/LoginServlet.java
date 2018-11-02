package cn.kgc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.kgc.entity.User;
import cn.kgc.service.UserService;
import cn.kgc.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet{
	
	UserService userService = new UserServiceImpl();
          
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//��ȡ���������ֵ
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//������������������룬��������������ת�룬��Ĭ�ϵ�iso-8859-1תΪUTF-8,����һ���ܲ��õĽ������
		//username = new String(username.getBytes("iso-8859-1"),"UTF-8");
		//password = new String(password.getBytes("iso-8859-1"),"UTF-8");
		
		//����service�еķ����õ�use�е��û���������
		User user = new User(null, username, password, null, null, 0, null, 0);
		
		User login =  userService.login(user);
		//�������û�����������бȶ�
		if (login==null) {
			//��½ʧ�ܾ�ת������ҳ��
			System.out.println("��½ʧ�ܣ�");
			req.getRequestDispatcher("/web/login/login.jsp").forward(req, resp);
		}else{
			//�ɹ��ض���login_success.html
			System.out.println("��½�ɹ���");
			List<User>  list = userService.queryAllUser();
			req.setAttribute("list", list);
		  //��¼�ɹ�������session�����һ������û��Ѿ���¼
			HttpSession session=req.getSession();
			session.setAttribute("user", username);
			
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
	
			
		}
		
		
	}
	
}
