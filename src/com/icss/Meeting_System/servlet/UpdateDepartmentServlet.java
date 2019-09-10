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
 * Servlet implementation class UpdateDepartmentServlet
 */
@WebServlet("/UpdateDepartmentServlet")
public class UpdateDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDepartmentServlet() {
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
				String  deptid = request.getParameter("deptid");
				String deptname = request.getParameter("deptname");
				//调用业务层的方法
				DepartmentService dept =new DepartmentService();
				//字符串转int
				int did = Integer.parseInt(deptid);
				//调用修改方法 
				int result = dept.updateDept(did,deptname);
				
				if(result!=0){
					//修改成功
					out.print("<script>alert('修改成功!');location.href='DisplayServlet?opr=dept'</script>");
				}else{
					//修改失败 
					out.print("<script>alert('修改失败!');location.href='DisplayServlet?opr=dept'</script>");
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
