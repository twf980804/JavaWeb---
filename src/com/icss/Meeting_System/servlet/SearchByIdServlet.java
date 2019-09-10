package com.icss.Meeting_System.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.Meeting_System.eneity.MeetingRoom;
import com.icss.Meeting_System.service.MeetingRoomService;

/**
 * Servlet implementation class SearchByIdServlet
 */
@WebServlet("/SearchByIdServlet")
public class SearchByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   String roomid = request.getParameter("roomid");
		   MeetingRoomService mrs=new MeetingRoomService();
		   MeetingRoom mr= new MeetingRoom();
	   	 mr =  mrs.findById(Integer.parseInt(roomid));
	   	  request.setAttribute("m", mr);
	  	request.getRequestDispatcher("roomdetails.jsp").forward(request, response);
	   	 System.out.println(mr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
