package com.icss.Meeting_System.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.Meeting_System.eneity.Meeting;
import com.icss.Meeting_System.service.MeetingService;
import com.icss.Meeting_System.util.Page;

/**
 * Servlet implementation class SearchMeetingServlet
 */
@WebServlet("/SearchMeetingServlet")
public class SearchMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMeetingServlet() {
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
		//接收分页的条件参数
		String meetingname = request.getParameter("meetingname");//h会议名称
		String roomname = request.getParameter("roomname");                //会议室名称
		String reservername = request.getParameter("reservername");//预订者名称
		//预定日期
		String reservefromdate=request.getParameter("reservefromdate");//预定时间前
		String reservetodate=request.getParameter("reservetodate");//预定时间后
		//会议日期
		String meetingfromdate=request.getParameter("meetingfromdate");//会议时间前
		String meetingtodate=request.getParameter("meetingtodate");//会议时间后
		//处理日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
		Timestamp reservefromdateTime = null;
		Timestamp reservetodateTime = null;
		Timestamp meetingfromdateTime = null;
		Timestamp meetingtodateTime = null;
		try {
		if(reservefromdate!=null&&!reservefromdate.equals("")){
			reservefromdateTime=new Timestamp(sdf.parse(reservefromdate).getTime());
		}
		if(reservetodate!=null&&!reservetodate.equals("")){
			reservetodateTime=new Timestamp(sdf.parse(reservetodate).getTime());
		}
		if(meetingfromdate!=null&&!meetingfromdate.equals("")){
			meetingfromdateTime=new Timestamp(sdf.parse(meetingfromdate).getTime());
		}
		if(meetingtodate!=null&&!meetingtodate.equals("")){
			meetingtodateTime=new Timestamp(sdf.parse(meetingtodate).getTime());
		}
		} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		//接收当前页码值
		int currentPage = 1;
		String pageIndex = request.getParameter("pageIndex");
		if(pageIndex!=null&&!"".equals(pageIndex)){  //如果pageIndex不为null
			currentPage = Integer.parseInt(pageIndex);
		}
		//限定每页的记录数；
		int pageSize = 10;
		//创建业务层的对象封装方法1查询总记录数2分页数据
		MeetingService mts = new MeetingService();
		Page<Meeting> page = null;
		Meeting mt = new Meeting();
		mt.setMeetingname(meetingname);
		mt.setRoomname(roomname);
		mt.setEmployeename(reservername);
		page=mts.searchmrPage(mt,reservefromdateTime,reservetodateTime,meetingfromdateTime,meetingtodateTime,currentPage,pageSize);
		//记录相关数据
		request.setAttribute("page", page);
		request.setAttribute("mt", mt);
		request.setAttribute("reservefromdateTime",reservefromdateTime );
		request.setAttribute("reservetodateTime",reservetodateTime );
		request.setAttribute("meetingfromdateTime",meetingfromdateTime );
		request.setAttribute("meetingtodateTime",meetingtodateTime );
		//记录相关数据条件
		request.getRequestDispatcher("searchmeetings.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
