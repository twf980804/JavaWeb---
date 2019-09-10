package com.icss.Meeting_System.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.icss.Meeting_System.eneity.Department;
import com.icss.Meeting_System.service.DepartmentService;

/**
 * Servlet implementation class DisplayServlet
 */
@WebServlet("/DisplayServlet")
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		DepartmentService ds=new DepartmentService();
		List<Department> list=ds.display();
		System.out.println("即将取值");
		String obj=request.getParameter("opr");
		PrintWriter out = response.getWriter();
		if(list != null) {
			request.setAttribute("lists", list);
			if(obj.equals("dept")) {
			request.getRequestDispatcher("departments.jsp").forward(request, response);
			}
			else if(obj.equals("reg")) {
				request.getRequestDispatcher("register.jsp").forward(request, response);
			} else if(obj.equals("check")) {
				//request.setAttribute("data", "我是服器的数据");
				String data=request.getParameter("data");
				System.out.println(data);
				String json=JSON.toJSONString(list);
				out.print(json);
				
				}
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
