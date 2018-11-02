package cn.kgc.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class BFilter
 */
//@WebFilter(filterName = "BFilter.java", urlPatterns = {"/BFilter"})
public class BFilter implements Filter {

    /**
     * Default constructor. 
     */
    public BFilter() {
        // TODO Auto-generated constructor stub
    }
     
	private Map<String, String> map = new HashMap<>();
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		map.put("client/ScoreServlet", "Score");
		map.put("index","/index.jsp");
		map.put("login", "/client/UserInfoServlet");
		System.out.println("开始拦截...");
	}
    
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest) arg0;
	        HttpServletResponse response = (HttpServletResponse) arg1;

		
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		

		
		String username = request.getParameter("username");
		HttpSession session=request.getSession();
		session.setAttribute("user", username);
		
	
		 //如果session里的user（账户名称）为null，则跳转到登陆页面
    /* if (request.getSession().getAttribute("user") == null) {
           request.getRequestDispatcher("/web/login/login.jsp").forward(request, response);
     	//response.sendRedirect(request.getContextPath()+"/web/login/login.jsp");
         
     }*/
		/*System.out.println(map.get("index"));
		if (!(request.getServletPath().equals(map.get("index")))) {
			 request.getRequestDispatcher("/web/login/login.jsp").forward(request, response);
		}*/
		
     arg2.doFilter(request, response);
	}



}
