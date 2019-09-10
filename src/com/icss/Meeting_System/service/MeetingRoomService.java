package com.icss.Meeting_System.service;

import java.util.ArrayList;
import java.util.List;

import com.icss.Meeting_System.eneity.MeetingRoom;
import com.icss.Meeting_System_dao.MeetingRoomDao;

/*****************************
*@类名     MeetingRoomService.java
*@作者      沐沐
*@日期    2018年7月24日-上午9:30:53
*@版本    V1.0
*@描述    
******************************/
public class MeetingRoomService {
	MeetingRoomDao mrd  = new MeetingRoomDao();
/**
 * 
 *@作者  沐沐
 *@日期  2018年7月24日-上午9:31:10
 *@描述  service层
 */
	public List<MeetingRoom> findMeetingRooms() {
		List<MeetingRoom> list = new ArrayList<MeetingRoom>();
		try {
			list = mrd.findMeetingRooms();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mrd.closeConnection();
		}
		return list;
	}
public int addMeetingRoom(MeetingRoom m) {
	/**
	 *@作者  沐沐
	 *@日期  2018年7月25日-上午11:53:09
	 *@描述  
	 */
	 int result =0;
	 try {
			result = mrd.addDepartment(m);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mrd.closeConnection();
		}
	return result; 
}
public MeetingRoom findById(int roomid) {
	/**
	 *@作者  沐沐
	 *@日期  2018年7月25日-下午3:56:51
	 *@描述  
	 */
	MeetingRoom mr = null;
	try {
		 mr = mrd.findById(roomid);
	} catch (Exception e) {
	
	} finally {
		mrd.closeConnection();
	}
	return mr ;
}

}
