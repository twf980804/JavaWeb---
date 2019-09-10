package com.icss.Meeting_System.eneity;

import java.io.Serializable;
import java.sql.Timestamp;

/*****************************
*@类名     Meeting.java
*@作者      沐沐
*@日期    2018年7月22日-下午4:11:59
*@版本    V1.0
*@描述    
******************************/
public class Meeting implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8785423604940074658L;
	private String meetingid;
	private String meetingname;
	private Integer roomid;   //会议室编号
	private Integer reservationistid;  //预订者ID
	private Integer numberofparticipants;  //参会人员个数
	private Timestamp starttime;
	private Timestamp endtime;
	private Timestamp reservationtime;
	private Timestamp canceledtime;
	private String description;
	private String status;
	
	//会议室名称(扩展属性)
	private String roomname;   //会议室名字 	
	private String employeename;
	
	public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public String getMeetingid() {
		return meetingid;
	}
	public void setMeetingid(String meetingid) {
		this.meetingid = meetingid;
	}
	public String getMeetingname() {
		return meetingname;
	}
	public void setMeetingname(String meetingname) {
		this.meetingname = meetingname;
	}
	public Integer getRoomid() {
		return roomid;
	}
	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}
	public Integer getReservationistid() {
		return reservationistid;
	}
	public void setReservationistid(Integer reservationistid) {
		this.reservationistid = reservationistid;
	}
	public Integer getNumberofparticipants() {
		return numberofparticipants;
	}
	public void setNumberofparticipants(Integer numberofparticipants) {
		this.numberofparticipants = numberofparticipants;
	}
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public Timestamp getEndtime() {
		return endtime;
	}
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
	public Timestamp getReservationtime() {
		return reservationtime;
	}
	public void setReservationtime(Timestamp reservationtime) {
		this.reservationtime = reservationtime;
	}
	public Timestamp getCanceledtime() {
		return canceledtime;
	}
	public void setCanceledtime(Timestamp canceledtime) {
		this.canceledtime = canceledtime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
