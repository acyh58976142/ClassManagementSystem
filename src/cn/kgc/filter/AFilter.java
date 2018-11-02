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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.kgc.entity.User;
import cn.kgc.service.UserService;
import cn.kgc.service.impl.UserServiceImpl;

//@WebFilter(filterName = "AFilter.java", urlPatterns = { "/*" })
public class AFilter implements Filter{
	
	private Map<String, String> map = new HashMap<>();
	UserService userService = new UserServiceImpl(); 

	
	public void init(FilterConfig arg0) throws ServletException {
		/*map.put("client/ScoreServlet", "Score");
		map.put("/index","index");
		map.put("/client/UserInfoServlet", "login");*/
		System.out.println("¿ªÊ¼À¹½Ø...");
	}
	

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		    System.out.println("dofilter......");
		    HttpServletRequest request = (HttpServletRequest) arg0;
	        HttpServletResponse response = (HttpServletResponse) arg1;

		
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
	
		
		
		arg2.doFilter(request, response);
	}


	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
