package com.icss.Meeting_System.eneity;

import java.io.Serializable;

/*****************************
*@类名     MeetingRoom.java
*@作者      沐沐
*@日期    2018年7月24日-上午9:26:40
*@版本    V1.0
*@描述    
******************************/
	public class MeetingRoom implements Serializable {

		private static final long serialVersionUID = 1L;
		private Integer roomid;
		private Integer roomnum;
		private String roomname;
		private Integer capacity;
		private String status;
		private String description;
		public Integer getRoomid() {
			return roomid;
		}
		public void setRoomid(Integer roomid) {
			this.roomid = roomid;
		}
		public Integer getRoomnum() {
			return roomnum;
		}
		public void setRoomnum(Integer roomnum) {
			this.roomnum = roomnum;
		}
		public String getRoomname() {
			return roomname;
		}
		public void setRoomname(String roomname) {
			this.roomname = roomname;
		}
		public Integer getCapacity() {
			return capacity;
		}
		public void setCapacity(Integer capacity) {
			this.capacity = capacity;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		


}
