package com.icss.Meeting_System.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.Meeting_System.eneity.Employee;
import com.icss.Meeting_System.eneity.Meeting;
import com.icss.Meeting_System.service.MeetingService;

/**
 * Servlet implementation class NotificationsServlet
 */
@WebServlet("/NotificationsServlet")
public class NotificationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//从session 中获取登录的用户对象 
				Employee emp = (Employee)request.getSession().getAttribute("emp");
				//获取员工的编号 
				int employeeid = emp.getEmployeeid();  
				MeetingService ms = new MeetingService();		
				//获取7天我参加 的会议 
				List<Meeting> attendMeeting = ms.getAttendMeeting(employeeid);
				request.setAttribute("atm", attendMeeting);
				//取消会议
				List<Meeting> cancelMeeting = ms.getCancelMeeting(employeeid);
				request.setAttribute("cam", cancelMeeting);
				//跳转页面 
				request.getRequestDispatcher("notifications.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
