package com.icss.Meeting_System.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.Meeting_System.eneity.Employee;

/*****************************
*@类名     LoginFilter.java
*@作者      沐沐
*@日期    2018年7月25日-上午9:52:24
*@版本    V1.0
*@描述    
******************************/
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		/**
		 *@作者  沐沐
		 *@日期  2018年7月25日-上午10:28:46
		 *@描述  
		 */
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/**
		 *@作者  沐沐
		 *@日期  2018年7月25日-上午10:28:46
		 *@描述  
		 */
		HttpServletRequest req= (HttpServletRequest)request;
		HttpServletResponse res= (HttpServletResponse)response;
		String path=req.getRequestURI();
		if(path.contains("style")||path.contains("images")) {
			chain.doFilter(req, res);
		} else {
			if (path.endsWith("login.jsp")||path.endsWith("LoginServlet")) {
				chain.doFilter(req, res);
			} else {
				Employee emp = (Employee)req.getSession().getAttribute("emp");
				if(emp != null) {
					chain.doFilter(req, res);
				}
				else {
					req.setAttribute("msg", "请先登录");
					req.getRequestDispatcher("login.jsp").forward(req, res);
				}
			}
		}
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		/**
		 *@作者  沐沐
		 *@日期  2018年7月25日-上午10:28:46
		 *@描述  
		 */
		
	}




}
