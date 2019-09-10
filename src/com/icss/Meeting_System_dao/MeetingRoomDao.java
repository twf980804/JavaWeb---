package com.icss.Meeting_System_dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.icss.Meeting_System.eneity.MeetingRoom;

/*****************************
*@类名     MeetingRoomDao.java
*@作者      沐沐
*@日期    2018年7月24日-上午9:28:04
*@版本    V1.0
*@描述    
******************************/
public class MeetingRoomDao extends baseDao {
	
	private PreparedStatement pstmt= null;  //执行sql语句对象 
	private ResultSet rs = null;    //结 果集
/**
 * 
 *@作者  沐沐
 *@日期  2018年7月24日-上午9:29:50
 *@描述  查询房间
 */
	public List<MeetingRoom> findMeetingRooms() {
		List<MeetingRoom> list = new ArrayList<MeetingRoom>();
		try {
			//创建connection
			connection=GetConnection();
			//第一个问从1开始   ，
			String sql="select roomid,roomnum,roomname,capacity,`status`,description from meetingroom";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				MeetingRoom r =new MeetingRoom();
				r.setRoomid(rs.getInt("roomid"));
				r.setRoomnum(rs.getInt("roomnum"));
				r.setRoomname(rs.getString("roomname"));
				r.setCapacity(rs.getInt("capacity"));
				r.setStatus(rs.getString("status"));
				r.setDescription(rs.getString("description"));
				list.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
public int addDepartment(MeetingRoom m) {
	/**
	 *@作者  沐沐
	 *@日期  2018年7月25日-下午2:00:03
	 *@描述  
	 */
	int result = 0;
	  try {
		  connection =GetConnection();
		  String sql="insert into meetingroom(roomid,roomname,capacity,status,description) values(?,?,?,?,?)";
		  pstmt = connection.prepareStatement(sql);
		  pstmt.setInt(1, m.getRoomid());
		  pstmt.setString(2, m.getRoomname());
		  pstmt.setInt(3, m.getCapacity());
		  pstmt.setString(4, m.getStatus());
		  pstmt.setString(5, m.getDescription());
		  result = pstmt.executeUpdate();
	} catch (Exception e) {
		// TODO: handle exception
	} finally {
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return result;
}
public MeetingRoom findById(int roomid) {
	/**
	 *@作者  沐沐
	 *@日期  2018年7月25日-下午3:59:54
	 *@描述  
	 */
	MeetingRoom mr=null;
	try {
		 connection =GetConnection();
		  String sql="select roomname,capacity,status,description from meetingroom where roomid="+roomid;
			pstmt=connection.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				mr = new MeetingRoom();
			    mr.setRoomid(roomid);
			    mr.setRoomname(rs.getString("roomname"));
			    mr.setCapacity(rs.getInt("capacity"));
			    mr.setStatus(rs.getString("status"));
			    mr.setDescription(rs.getString("description"));
			}
	} catch (Exception e) {
		// TODO: handle exception
	}
	return mr;
}
}

