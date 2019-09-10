package com.icss.Meeting_System.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.Meeting_System.eneity.MeetingRoom;
import com.icss.Meeting_System.service.MeetingRoomService;

/**
 * Servlet implementation class AddMeetingRoomServlet
 */
@WebServlet("/AddMeetingRoomServlet")
public class AddMeetingRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMeetingRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		MeetingRoomService mr = new MeetingRoomService();
		MeetingRoom m = new MeetingRoom();
		String roomid=request.getParameter("roomid");
		String roomname=request.getParameter("roomname");
		String capacity=request.getParameter("capacity");
		String status=request.getParameter("status");
		String description=request.getParameter("description");
		m.setRoomid(Integer.parseInt(roomid));
		m.setRoomname(roomname);
		m.setCapacity(Integer.parseInt(capacity));
		m.setStatus(status);
		m.setDescription(description);
		int result=mr.addMeetingRoom(m);
		PrintWriter out = response.getWriter();
		if(result==1) {
			out.print("<script>alert('添加成功!');location.href='${pageContext.request.contextPath}/SearchMeetingRoomServlet'</script>");
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
