package com.icss.Meeting_System.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.icss.Meeting_System.eneity.Employee;
import com.icss.Meeting_System.service.EmployeeService;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class ajaxSearchEmployees
 */
@WebServlet("/ajaxSearchEmployees")
public class ajaxSearchEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajaxSearchEmployees() {
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
		response.setContentType("text/html;charset=utf-8");
		//获取传入的部门的id
		String id = request.getParameter("departmentid");
		//转换
		int deptid = 0;
		if(id!=null){
			deptid = Integer.parseInt(id);
		}
		//调用业务层
		EmployeeService es = new EmployeeService();
		List<Employee> list = es.findEmpsByDeptId(deptid);
		//将集合对象转换成json
		PrintWriter out = response.getWriter();
		JSONArray employees = JSONArray.fromObject(list);
		System.out.println(employees.toString());
		//输出 
		out.print(employees);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
