package com.icss.Meeting_System.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.Meeting_System.eneity.Department;
import com.icss.Meeting_System.eneity.MeetingRoom;
import com.icss.Meeting_System.service.DepartmentService;
import com.icss.Meeting_System.service.MeetingRoomService;

/**
 * Servlet implementation class BookMeetingServlet
 */
@WebServlet("/BookMeetingServlet")
public class BookMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookMeetingServlet() {
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
				//查询会议室集合
				MeetingRoomService mrs = new MeetingRoomService();
				List<MeetingRoom> mr = mrs.findMeetingRooms();
				request.setAttribute("meetingroom", mr);
				//查询部门集合
				DepartmentService ds = new DepartmentService();
				List<Department> depts = ds.display();
				request.setAttribute("departments", depts);
				//跳转页面 
				request.getRequestDispatcher("bookmeeting.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
