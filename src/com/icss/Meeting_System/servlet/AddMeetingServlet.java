package com.icss.Meeting_System.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.Meeting_System.eneity.Employee;
import com.icss.Meeting_System.eneity.Meeting;
import com.icss.Meeting_System.service.MeetingService;

/**
 * Servlet implementation class AddMeetingServlet
 */
@WebServlet("/AddMeetingServlet")
public class AddMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMeetingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理乱码
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				//生成一个meetingid
				String meetingid = UUID.randomUUID().toString();  //32随机数
				//接收页面的参数
				String employeeids = request.getParameter("employeeids");  //8,13,14,2,11,
				
				String meetingname = request.getParameter("meetingname");
				String numofattendents = request.getParameter("numofattendents");
				String startTime = request.getParameter("startTime");
				String endtime = request.getParameter("endtime");
				String roomid = request.getParameter("roomid");
				String description = request.getParameter("description");
				//预订者id
				Employee emp = (Employee) request.getSession().getAttribute("emp");
				int reservationistid= emp.getEmployeeid();		
				//格式化日期的对象 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");	
				//预订的时间(当前web服务器时间)
				Date dt =new Date();
				//调用业层方法实预订会议（添加会议表,添加参会人员表 ）使用事务 
				MeetingService ms = new MeetingService();
				Meeting meeting = new Meeting();
				meeting.setMeetingid(meetingid);  
				meeting.setMeetingname(meetingname);
				meeting.setRoomid(Integer.parseInt(roomid));
				meeting.setReservationistid(reservationistid);
				meeting.setNumberofparticipants(Integer.parseInt(numofattendents));
				try {
					meeting.setStarttime(new Timestamp(sdf.parse(startTime).getTime()));
					meeting.setEndtime(new Timestamp(sdf.parse(endtime).getTime()));
					meeting.setReservationtime(new Timestamp(dt.getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				meeting.setDescription(description);
				meeting.setStatus("0");
				
				//添加会议 
				int result = ms.addMeeting(meeting,employeeids);
				if(result!=0){
					//添加成功
					out.print("<script>alert('添加会议成功!')</script>");
				}else{
					//添加失败
					out.print("<script>alert('添加会议失败!')</script>");
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
