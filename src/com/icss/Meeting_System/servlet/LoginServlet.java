package com.icss.Meeting_System.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.Meeting_System.eneity.Employee;
import com.icss.Meeting_System.service.EmployeeService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println(username+password);
		EmployeeService es=new  EmployeeService();
		Employee emp=es.Login(username,password);
	  if(emp == null) {
		  //失败
		  System.out.println("用户名错误，没我进来了");
		  request.setAttribute("msg", "用户名或者密码错误");
		  request.getRequestDispatcher("login.jsp").forward(request, response);
	  }else{
		  //成功
		  System.out.println("我进来了");
		HttpSession session = request.getSession();
		session.setAttribute("name", emp.getUsername());
		session.setAttribute("emp", emp); 
		request.getRequestDispatcher("login.jsp").forward(request, response);
	  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
