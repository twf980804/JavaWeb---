package com.icss.Meeting_System.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.Meeting_System.service.DepartmentService;

/**
 * Servlet implementation class AddDeparServlet
 */
@WebServlet("/AddDeparServlet")
public class AddDeparServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDeparServlet() {
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
				DepartmentService ds =new DepartmentService();
				String name=request.getParameter("departmentname");
				int result = ds.addDepartment(name);
				PrintWriter out = response.getWriter();
				if(result!=0){
					//删除成功
					out.print("<script>alert('添加成功!');location.href='DisplayServlet?opr=dept'</script>");
				}else{
					//删除失败 
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
