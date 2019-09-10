package com.icss.Meeting_System_dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.icss.Meeting_System.eneity.Meeting;

/*****************************
*@类名     MeetingDao.java
*@作者      沐沐
*@日期    2018年7月22日-下午4:13:18
*@版本    V1.0
*@描述    
******************************/
public class MeetingDao extends baseDao {
	private PreparedStatement pstmt = null; // 执行sql语句对象
	private ResultSet rs = null; // 结 果集

	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月22日-下午4:15:04
	 *@描述  查询未来七天我要参加的会议
	 */
	public List<Meeting> getAttendMeeting(int employeeid) {
		List<Meeting> list =new ArrayList<Meeting>();
		try {
			// 创建connection
			connection = GetConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" select m.meetingid,m.meetingname,r.roomname,m.starttime,m.endtime ");
			sql.append(" from meeting m ,meetingroom r ");
			sql.append(" where m.roomid = r.roomid ");
			sql.append(" and m.starttime>SYSDATE() and starttime<DATE_ADD(SYSDATE(),INTERVAL 7 DAY) ");
			sql.append(" and m.reservationistid=? ");
			pstmt = connection.prepareStatement(sql.toString());
			pstmt.setInt(1, employeeid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Meeting m = new Meeting();
				m.setMeetingid(rs.getString("meetingid"));
				m.setMeetingname(rs.getString("meetingname"));
				m.setRoomname(rs.getString("roomname"));
				m.setStarttime(rs.getTimestamp("starttime"));
				m.setEndtime(rs.getTimestamp("endtime"));
				list.add(m);
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
		
		/**
		 * 
		 *@作者  沐沐
		 *@日期  2018年7月22日-下午4:15:46
		 *@描述   查询取消的会议列表
		 */
	public List<Meeting> getCancelMeeting(int employeeid) {
		List<Meeting> list =new ArrayList<Meeting>();
		try {
			// 创建connection
			connection = GetConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" select m.meetingid,m.meetingname,r.roomname,m.starttime,m.endtime,m.description ");
			sql.append(" from meeting m ,meetingroom r ");
			sql.append(" where m.roomid = r.roomid ");
			sql.append(" and m.reservationistid=? and m.status='1' ");
			pstmt = connection.prepareStatement(sql.toString());
			pstmt.setInt(1, employeeid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Meeting m = new Meeting();
				m.setMeetingid(rs.getString("meetingid"));
				m.setMeetingname(rs.getString("meetingname"));
				m.setRoomname(rs.getString("roomname"));
				m.setStarttime(rs.getTimestamp("starttime"));
				m.setEndtime(rs.getTimestamp("endtime"));
				m.setDescription(rs.getString("description"));
				list.add(m);
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
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月24日-下午3:38:01
	 *@描述  增加会议
	 */
	public void addMeeting(Meeting meeting) {
		try {
			String sql="INSERT into meeting (meetingid,meetingname,roomid,reservationistid,numberofparticipants,starttime,endtime,reservationtime,description,`status`)";
			sql +=" VALUES (?,?,?,?,?,?,?,?,?,?)  ";
			pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, meeting.getMeetingid());
			pstmt.setString(2, meeting.getMeetingname());
			pstmt.setInt(3, meeting.getRoomid());
			pstmt.setInt(4, meeting.getReservationistid());
			pstmt.setInt(5, meeting.getNumberofparticipants());
			pstmt.setTimestamp(6, meeting.getStarttime());
			pstmt.setTimestamp(7, meeting.getEndtime());
			pstmt.setTimestamp(8, meeting.getReservationtime());
			pstmt.setString(9, meeting.getDescription());
			pstmt.setString(10, meeting.getStatus());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月24日-下午3:38:13
	 *@描述   增加参会人员
	 */
	public void addMeetingPartciPants(String meetingid, int employeeid) {
		try {
			String sql="insert into meetingparticipants (meetingid,employeeid) values (?,?)";
		
			pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, meetingid);
			pstmt.setInt(2, employeeid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Meeting> searchMt(Meeting mt, Timestamp reservefromdateTime, Timestamp reservetodateTime,
			Timestamp meetingfromdateTime, Timestamp meetingtodateTime, int startnum, int pageSize) {
		/**
		 *@作者  沐沐
		 *@日期  2018年7月26日-上午10:24:51
		 *@描述  
		 */ 
		List<Meeting>  list = new ArrayList<Meeting> ();
		try {
			connection = GetConnection();
			String sql = "";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Meeting meeting = new Meeting();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public int searchMtCount(Meeting mt, Timestamp reservefromdateTime, Timestamp reservetodateTime,
			Timestamp meetingfromdateTime, Timestamp meetingtodateTime) {
		/**
		 *@作者  沐沐
		 *@日期  2018年7月26日-上午10:24:58
		 *@描述  
		 */
		return 0;
	}
}
