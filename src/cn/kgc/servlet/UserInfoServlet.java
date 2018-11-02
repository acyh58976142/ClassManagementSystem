package cn.kgc.servlet;
/*
 * chenyuhao
 * 2018-08-01
 * User用户的servlet层
 */

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.kgc.entity.Page;
import cn.kgc.entity.User;
import cn.kgc.service.UserService;
import cn.kgc.service.impl.UserServiceImpl;
import cn.kgc.util.WEBUtils;



/*@WebServlet("/client/UserInfoServlet")*/
public class UserInfoServlet extends BeanServlet{
	private static final long serialVersionUID = 1L;
    
	
	UserService userService = new UserServiceImpl();
    
	

	/*
	 * 分页查询多条数据
	 */
	protected void findBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNum = request.getParameter("pageNum");
		String userName = request.getParameter("queryUserName");
		int pageSize=5;
		
		Page<User> page = userService.findBook(pageNum, pageSize,userName);
		
		
		
		request.setAttribute("page", page);
		//转发到查询所有的页面
		request.getRequestDispatcher("/web/user/admin-list.jsp").forward(request, response);

		
	}
	
	
	/*
	 * 用户登陆servlet
	 */
	protected void login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//获取表单中输入的值
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//调用service中的方法得到use中的用户名和密码
		User user = new User(null, username, password, null, null, 0, null, 0);
		
		User login =  userService.login(user);
	//	int isUsing = login.getIsUsing();
		//将两个用户名和密码进行比对
	
		if (login==null){
			//登录错误传递错误消息
			req.setAttribute("msg", "用户名或密码不存在");
			//登陆失败就转发到本页面
			req.getRequestDispatcher("/web/login/login.jsp").forward(req, resp);
		}else{
			if ((login.getPassword().equals(password))&&(login.getUserName().equals(username))&&login.getIsUsing()==1) {
				
				int role = login.getRole();//根据用户名查询用户权限
				//登录成功，设置session，标记一下这个用户已经登录
				HttpSession session=req.getSession();
				session.setAttribute("user", username);
				session.setAttribute("role", role);
				//成功重定向到login_success.html
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
			}else{
				//登录错误传递错误消息
				req.setAttribute("msg", "用户名或密码错误或者账号未启用请联系管理员");
				//登陆失败就转发到本页面
				req.getRequestDispatcher("/web/login/login.jsp").forward(req, resp);
			}
			
			
		}
		
		
	}
	
	
	/*
	 * 退出登陆状态
	 */
	
	protected void out(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/web/login/login.jsp");
	}
	
	
	/**
	 * 查询所有用户的方法
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	protected void queryAllUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		        //调用service方法查询所有的图书
				List<User> list = userService.queryAllUser();
				
				request.setCharacterEncoding("utf-8");
				//将查询到的图书集合放到request域中
				request.setAttribute("list", list);
				request.setAttribute("size", list.size());
				//转发
				request.getRequestDispatcher("/web/user/admin-list.jsp").forward(request, response);
	}
	
	
	/**
	 * 新增用户的方法
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	protected void saveUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		/**
		 * 获取页面中表单提交的内容
		 */
		User param2bean = WEBUtils.param2bean(request, new User());
		
		/*String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");*/
		
		//放入数据库中
		int b =	userService.saveUser(param2bean);
		

		/**
		 * 判断是否添加成功
		 * 失败转发到当前页面
		 * 成功调用查询方法
		 */
		if(!(b==0)){
			//注册成功重定向到regist_success.html
			response.sendRedirect(request.getContextPath()+"/client/UserInfoServlet?method=findBook");
			
		}else{
			//前台验证注册的内容，后台报“用户名存在”错误
			request.setAttribute("msg", "用户名已存在");
			//注册失败转发到当前页面
			request.getRequestDispatcher("/web/user/admin-add.jsp").forward(request, response);
		}
	
	}
	
	
	/**
	 * 删除用户的方法
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	protected void delUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		       //获取页面中的id
				String userId = request.getParameter("userId");
				
				//执行删除操作
				int result=userService.delUser(userId);
				
				//完成后重新定向
				response.sendRedirect(request.getContextPath()+"/client/UserInfoServlet?method=findBook");
		
	}
	
	
	/**
	 * 根据参数查询用户的方法
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	protected void getUserById(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
	
	/**
	 * 根据id查询显示修改模态框上
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	protected void toUpdatePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		//获取需要修改的id
		String userId = request.getParameter("userId");
		
		User user = userService.getUserById(userId);
		
		//将查询到的对象放入域中
		request.setAttribute("user", user);
		
		//转发到修改页面
		request.getRequestDispatcher("/web/user/admin-add.jsp").forward(request, response);
	
	}
	
	/**
	 * 根据用户名查询用户的权限
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	 public void getUserRole(String userName){
				 
	}
	
	
	/**
	 * 修改或新增用户的方法
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	protected void updateUserOrSaveUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User user = WEBUtils.param2bean(req, new User());
		
		//如果user的主键id未null，则执行新增方法，反之执行修改方法
		if ((user.getId())=="") {
			int b=userService.saveUser(user);	
			
			if(!(b==0)){
				//新建成功
				req.setAttribute("success", "新建角色成功！");
				//注册成功重定向到regist_success.html
				resp.sendRedirect(req.getContextPath()+"/client/UserInfoServlet?method=findBook&success=save_success");
				
			}else{
				//前台验证注册的内容，后台报“用户名存在”错误
				req.setAttribute("msg", "系统错误");
				//注册失败转发到当前页面
				req.getRequestDispatcher("/client/UserInfoServlet?method=queryAllUser&message=save_error").forward(req, resp);
			}
		}else{
			int b=userService.updateUser(user);
			
			if(!(b==0)){
				//注册成功重定向到regist_success.html
				resp.sendRedirect(req.getContextPath()+"/client/UserInfoServlet?method=findBook&success=save_success");
				
			}else{
				//前台验证注册的内容，后台报“用户名存在”错误
				req.setAttribute("msg", "系统错误");
				//注册失败转发到当前页面
				req.getRequestDispatcher("/client/UserInfoServlet?method=queryAllUser&message=save_error").forward(req, resp);
			}
		}
		
		
	}
	
	
/*	protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		*//**
		 * 获取页面中表单提交的内容
		 *//*
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		*//**
		 * .调用service中注册的方法
		 *//*
		User user = new User(null, userName, password, email, email, 0, null, 0);
		boolean b = userService.regist(user);
		
		

		*//**
		 * 判断是否注册成功
		 * 失败转发到当前页面
		 * 成功重定向到regist_success.html
		 *//*
		if(b){
			//注册成功重定向到regist_success.html
			resp.sendRedirect("/BookStork/pages/user/regist_success.jsp");
			
		}else{
			//前台验证注册的内容，后台报“用户名存在”错误
			req.setAttribute("msg", "用户名已存在");
			//注册失败转发到当前页面
			req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
		}


}*/
	
}