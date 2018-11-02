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
		
		//获取表单中输入的值
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//解决中文所产生的乱码，对两个参数进行转码，从默认的iso-8859-1转为UTF-8,这是一个很不好的解决方法
		//username = new String(username.getBytes("iso-8859-1"),"UTF-8");
		//password = new String(password.getBytes("iso-8859-1"),"UTF-8");
		
		//调用service中的方法得到use中的用户名和密码
		User user = new User(null, username, password, null, null, 0, null, 0);
		
		User login =  userService.login(user);
		//将两个用户名和密码进行比对
		if (login==null) {
			//登陆失败就转发到本页面
			System.out.println("登陆失败！");
			req.getRequestDispatcher("/web/login/login.jsp").forward(req, resp);
		}else{
			//成功重定向到login_success.html
			System.out.println("登陆成功！");
			List<User>  list = userService.queryAllUser();
			req.setAttribute("list", list);
		  //登录成功，设置session，标记一下这个用户已经登录
			HttpSession session=req.getSession();
			session.setAttribute("user", username);
			
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
	
			
		}
		
		
	}
	
}
