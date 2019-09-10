package com.icss.Meeting_System.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.Meeting_System.eneity.Employee;
import com.icss.Meeting_System.service.EmployeeService;
import com.icss.Meeting_System.util.Page;

/**
 * Servlet implementation class SearchEempServlet
 */
@WebServlet("/SearchEempServlet")
public class SearchEempServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEempServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//处理乱码
				request.setCharacterEncoding("utf-8");
				//接收分页的条件参数
				String employeename = request.getParameter("employeename");   //员工的姓名
				String username = request.getParameter("username");    //员工的账号
				String status = request.getParameter("status");     //员工的状态 
				//接收当前页码值 
				int currentPage = 1;
				String pageIndex = request.getParameter("pageIndex");
				if(pageIndex!=null&&!"".equals(pageIndex)){   //如果pageIndex参数不为空
					currentPage = Integer.parseInt(pageIndex);
				}
				//限定每页记录数
				int pageSize = 10;
				//创建业务层对象封装方法1查询总记录数  2分页数据 
				EmployeeService emp = new EmployeeService();
				Page<Employee> page = null;
				page = emp.searchEmpPage(employeename,username,status,currentPage,pageSize);
				//记录相关数据 
				request.setAttribute("page", page);
				//记录相关条件数据 
				request.setAttribute("employeename", employeename);
				request.setAttribute("username", username);
				request.setAttribute("status", status);
				//跳转页面
				request.getRequestDispatcher("searchemployees.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
