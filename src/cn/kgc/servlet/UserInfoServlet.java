package cn.kgc.servlet;
/*
 * chenyuhao
 * 2018-08-01
 * User�û���servlet��
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
	 * ��ҳ��ѯ��������
	 */
	protected void findBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNum = request.getParameter("pageNum");
		String userName = request.getParameter("queryUserName");
		int pageSize=5;
		
		Page<User> page = userService.findBook(pageNum, pageSize,userName);
		
		
		
		request.setAttribute("page", page);
		//ת������ѯ���е�ҳ��
		request.getRequestDispatcher("/web/user/admin-list.jsp").forward(request, response);

		
	}
	
	
	/*
	 * �û���½servlet
	 */
	protected void login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//��ȡ���������ֵ
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//����service�еķ����õ�use�е��û���������
		User user = new User(null, username, password, null, null, 0, null, 0);
		
		User login =  userService.login(user);
	//	int isUsing = login.getIsUsing();
		//�������û�����������бȶ�
	
		if (login==null){
			//��¼���󴫵ݴ�����Ϣ
			req.setAttribute("msg", "�û��������벻����");
			//��½ʧ�ܾ�ת������ҳ��
			req.getRequestDispatcher("/web/login/login.jsp").forward(req, resp);
		}else{
			if ((login.getPassword().equals(password))&&(login.getUserName().equals(username))&&login.getIsUsing()==1) {
				
				int role = login.getRole();//�����û�����ѯ�û�Ȩ��
				//��¼�ɹ�������session�����һ������û��Ѿ���¼
				HttpSession session=req.getSession();
				session.setAttribute("user", username);
				session.setAttribute("role", role);
				//�ɹ��ض���login_success.html
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
			}else{
				//��¼���󴫵ݴ�����Ϣ
				req.setAttribute("msg", "�û����������������˺�δ��������ϵ����Ա");
				//��½ʧ�ܾ�ת������ҳ��
				req.getRequestDispatcher("/web/login/login.jsp").forward(req, resp);
			}
			
			
		}
		
		
	}
	
	
	/*
	 * �˳���½״̬
	 */
	
	protected void out(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/web/login/login.jsp");
	}
	
	
	/**
	 * ��ѯ�����û��ķ���
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	protected void queryAllUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		        //����service������ѯ���е�ͼ��
				List<User> list = userService.queryAllUser();
				
				request.setCharacterEncoding("utf-8");
				//����ѯ����ͼ�鼯�Ϸŵ�request����
				request.setAttribute("list", list);
				request.setAttribute("size", list.size());
				//ת��
				request.getRequestDispatcher("/web/user/admin-list.jsp").forward(request, response);
	}
	
	
	/**
	 * �����û��ķ���
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	protected void saveUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		/**
		 * ��ȡҳ���б��ύ������
		 */
		User param2bean = WEBUtils.param2bean(request, new User());
		
		/*String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");*/
		
		//�������ݿ���
		int b =	userService.saveUser(param2bean);
		

		/**
		 * �ж��Ƿ���ӳɹ�
		 * ʧ��ת������ǰҳ��
		 * �ɹ����ò�ѯ����
		 */
		if(!(b==0)){
			//ע��ɹ��ض���regist_success.html
			response.sendRedirect(request.getContextPath()+"/client/UserInfoServlet?method=findBook");
			
		}else{
			//ǰ̨��֤ע������ݣ���̨�����û������ڡ�����
			request.setAttribute("msg", "�û����Ѵ���");
			//ע��ʧ��ת������ǰҳ��
			request.getRequestDispatcher("/web/user/admin-add.jsp").forward(request, response);
		}
	
	}
	
	
	/**
	 * ɾ���û��ķ���
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	protected void delUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		       //��ȡҳ���е�id
				String userId = request.getParameter("userId");
				
				//ִ��ɾ������
				int result=userService.delUser(userId);
				
				//��ɺ����¶���
				response.sendRedirect(request.getContextPath()+"/client/UserInfoServlet?method=findBook");
		
	}
	
	
	/**
	 * ���ݲ�����ѯ�û��ķ���
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	protected void getUserById(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
	
	/**
	 * ����id��ѯ��ʾ�޸�ģ̬����
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	protected void toUpdatePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		//��ȡ��Ҫ�޸ĵ�id
		String userId = request.getParameter("userId");
		
		User user = userService.getUserById(userId);
		
		//����ѯ���Ķ����������
		request.setAttribute("user", user);
		
		//ת�����޸�ҳ��
		request.getRequestDispatcher("/web/user/admin-add.jsp").forward(request, response);
	
	}
	
	/**
	 * �����û�����ѯ�û���Ȩ��
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	 public void getUserRole(String userName){
				 
	}
	
	
	/**
	 * �޸Ļ������û��ķ���
	 * @param request response
	 * @throws  ServletException  IOException
	 */
	protected void updateUserOrSaveUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User user = WEBUtils.param2bean(req, new User());
		
		//���user������idδnull����ִ��������������ִ֮���޸ķ���
		if ((user.getId())=="") {
			int b=userService.saveUser(user);	
			
			if(!(b==0)){
				//�½��ɹ�
				req.setAttribute("success", "�½���ɫ�ɹ���");
				//ע��ɹ��ض���regist_success.html
				resp.sendRedirect(req.getContextPath()+"/client/UserInfoServlet?method=findBook&success=save_success");
				
			}else{
				//ǰ̨��֤ע������ݣ���̨�����û������ڡ�����
				req.setAttribute("msg", "ϵͳ����");
				//ע��ʧ��ת������ǰҳ��
				req.getRequestDispatcher("/client/UserInfoServlet?method=queryAllUser&message=save_error").forward(req, resp);
			}
		}else{
			int b=userService.updateUser(user);
			
			if(!(b==0)){
				//ע��ɹ��ض���regist_success.html
				resp.sendRedirect(req.getContextPath()+"/client/UserInfoServlet?method=findBook&success=save_success");
				
			}else{
				//ǰ̨��֤ע������ݣ���̨�����û������ڡ�����
				req.setAttribute("msg", "ϵͳ����");
				//ע��ʧ��ת������ǰҳ��
				req.getRequestDispatcher("/client/UserInfoServlet?method=queryAllUser&message=save_error").forward(req, resp);
			}
		}
		
		
	}
	
	
/*	protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		*//**
		 * ��ȡҳ���б��ύ������
		 *//*
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		*//**
		 * .����service��ע��ķ���
		 *//*
		User user = new User(null, userName, password, email, email, 0, null, 0);
		boolean b = userService.regist(user);
		
		

		*//**
		 * �ж��Ƿ�ע��ɹ�
		 * ʧ��ת������ǰҳ��
		 * �ɹ��ض���regist_success.html
		 *//*
		if(b){
			//ע��ɹ��ض���regist_success.html
			resp.sendRedirect("/BookStork/pages/user/regist_success.jsp");
			
		}else{
			//ǰ̨��֤ע������ݣ���̨�����û������ڡ�����
			req.setAttribute("msg", "�û����Ѵ���");
			//ע��ʧ��ת������ǰҳ��
			req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
		}


}*/
	
}