package com.icss.Meeting_System.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.icss.Meeting_System.eneity.Meeting;
import com.icss.Meeting_System.util.Page;
import com.icss.Meeting_System_dao.MeetingDao;

/*****************************
*@类名     MeetingService.java
*@作者      沐沐
*@日期    2018年7月22日-下午4:17:08
*@版本    V1.0
*@描述    
******************************/
	public class MeetingService {
		MeetingDao md = new MeetingDao();
		/**
		 * 
		 *@作者  沐沐
		 *@日期  2018年7月22日-下午4:17:48
		 *@描述  获取未来七天是我要参加会议的service层进行逻辑控制
		 */
		public List<Meeting> getAttendMeeting(int employeeid) {
			List<Meeting> list =new ArrayList<Meeting>();
			try {
				list = md.getAttendMeeting(employeeid);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				md.closeConnection();
			}
			return list;
		}
		/**
		 * 
		 *@作者  沐沐
		 *@日期  2018年7月22日-下午4:18:27
		 *@描述  我取消的会议
		 */
		public List<Meeting> getCancelMeeting(int employeeid) {
			List<Meeting> list =new ArrayList<Meeting>();
			try {
				list = md.getCancelMeeting(employeeid);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				md.closeConnection();
			}
			return list;
		}
		/**
		 * 
		 *@作者  沐沐
		 *@日期  2018年7月24日-下午3:39:55
		 *@描述  添加会议的方法
		 *@事务注意: 在哪里用到事务?   
		 *      业务层调用两个以上dao层方法（增删改）  查询除外
		 *      如果业务层调用了两个以上dao层的查询方法，不用事务，因为事务会影响效率
		 */
		public int addMeeting(Meeting meeting, String employeeids) {
			int result =0;
			try {
				md.beginTrans();   //开始事务
				//dao 添加会议表
				md.addMeeting(meeting);
				//dao 添加参会人员表
				String[] empids = employeeids.split(",");   // 0->8  1->13  2->9 3->null ""
				int employeeid=0;  //参会员工的编号 
				for (String id : empids) {
					if(id!=null&&!"".equals(id)){
						employeeid =Integer.parseInt(id);
						md.addMeetingPartciPants(meeting.getMeetingid(),employeeid);
					}
				}
				result = 1;
				md.commit();    //提交事务
			} catch (Exception e) {
				e.printStackTrace();
				md.rollBack();   //回滚事务 
			} finally {
				md.closeConnection();
			}
			return result;
		}
		public Page<Meeting> searchmrPage(Meeting mt, Timestamp reservefromdateTime, Timestamp reservetodateTime,
				Timestamp meetingfromdateTime, Timestamp meetingtodateTime, int currentPage, int pageSize) {
			/**
			 *@作者  沐沐
			 *@日期  2018年7月26日-上午10:23:42
			 *@描述  
			 */
			Page<Meeting> page = new Page<Meeting>();
			try {
				//起始行编号
				int startnum = (currentPage-1)*pageSize;
				//返回会议的列表(数据库查询出来的)
				List<Meeting> list=  md.searchMt(mt,reservefromdateTime,reservetodateTime,meetingfromdateTime,meetingtodateTime,startnum,pageSize);
				//返回会议的记录数
				int count = md.searchMtCount(mt,reservefromdateTime,reservetodateTime,meetingfromdateTime,meetingtodateTime);
				//构造相关的工具类给属性赋值
				page.setPageIndex(currentPage);
				page.setPageSize(pageSize);
				page.setPageCount(count);
				page.setList(list);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				md.closeConnection();
			}
			return page;
		}
	}

