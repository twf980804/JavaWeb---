package com.icss.Meeting_System.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.Meeting_System.eneity.Employee;
import com.icss.Meeting_System.service.EmployeeService;

/**
 * Servlet implementation class EmployeeRegisterServlet
 */
@WebServlet("/EmployeeRegisterServlet")
public class EmployeeRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 中文乱码
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				//输出对象 
				PrintWriter out = response.getWriter();
				// 获取页面传递的参数
				String  employeename = request.getParameter("employeename");
				String  username = request.getParameter("username");
				String  password = request.getParameter("password");
				String  phone = request.getParameter("phone");
				String  email = request.getParameter("email");
				String  deptid = request.getParameter("deptid");
				//调用业务层的方法
				EmployeeService es=new EmployeeService();
				//字符串转int
				int id = Integer.parseInt(deptid);
				//调用修改方法 
				Employee emp=new Employee(employeename,username,phone,email,id,password);
				int result = es.employeeRegister(emp);
				System.out.println(result);
				if(result!=0){
					//注册成功
					out.print("<script>alert('注册成功!');</script>");
				}else{
					
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
