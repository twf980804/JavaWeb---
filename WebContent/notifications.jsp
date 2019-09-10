<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="styles/common.css"/>
    </head>
    <body>
        <div class="page-header">
            <div class="header-banner">
                <img src="images/header.png" alt="CoolMeeting"/>
            </div>
            <div class="header-title">
                欢迎访问Cool-Meeting会议管理系统
            </div>
            <div class="header-quicklink">
                欢迎您，<strong>${name}</strong>
                <a href="changepassword.html">[修改密码]</a>
            </div>
        </div>
        <div class="page-body">
            <div class="page-sidebar">
                <div class="sidebar-menugroup">
                      <div class="sidebar-grouptitle">个人中心</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="${pageContext.request.contextPath }/NotificationsServlet">最新通知</a></li>
                        <li class="sidebar-menuitem active"><a href="mybookings.html">我的预定</a></li>
                        <li class="sidebar-menuitem"><a href="mymeetings.html">我的会议</a></li>
                    </ul>
                </div>
                <div class="sidebar-menugroup">
                   <div class="sidebar-grouptitle">人员管理</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="${pageContext.request.contextPath}/DisplayServlet?opr=dept">部门管理</a></li>
                        <li class="sidebar-menuitem"><a href="${pageContext.request.contextPath}/DisplayServlet?opr=reg">员工注册</a></li>
                        <li class="sidebar-menuitem"><a href="${pageContext.request.contextPath}/EmpApprovalServlet">注册审批</a></li>
                        <li class="sidebar-menuitem"><a href="${pageContext.request.contextPath}/SearchEempServlet">搜索员工</a></li>
                    </ul>
                </div>
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle">会议预定</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="addmeetingroom.jsp">添加会议室</a></li>
                        <li class="sidebar-menuitem"><a href="${pageContext.request.contextPath}/SearchMeetingRoomServlet">查看会议室</a></li>
                        <li class="sidebar-menuitem"><a href="${pageContext.request.contextPath }/BookMeetingServlet">预定会议</a></li>
                        <li class="sidebar-menuitem"><a href="searchmeetings.html">搜索会议</a></li>
                    </ul>
                </div>
            </div>
            <div class="page-content">
                <div class="content-nav">
                    个人中心 > <a href="notifications">最新通知</a>
                </div>
                <table class="listtable">
                    <caption>
                        未来7天我要参加的会议:
                    </caption>
                    <tr class="listheader">
                        <th style="width:300px">会议名称</th>
                        <th>会议室</th>
                        <th>起始时间</th>
                        <th>结束时间</th>
                        <th style="width:100px">操作</th>
                    </tr>
                     <c:forEach items="${atm }" var="mt" >
	                    <tr>
	                        <td>${mt.meetingname }</td>
	                        <td>${mt.roomname }</td>
	                        <td>${mt.starttime }</td>
	                        <td>${mt.endtime }</td>
	                        <td>
	                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
	                        </td>
	                    </tr>
                    </c:forEach>
                </table>
                <table class="listtable">
                    <caption>
                        已取消的会议:
                    </caption>
                    <tr class="listheader">
                        <th style="width:300px">会议名称</th>
                        <th>会议室</th>
                        <th>起始时间</th>
                        <th>结束时间</th>
                        <th>取消原因</th>
                        <th style="width:100px">操作</th>
                    </tr>
                    <tr>
                        <td>三季度销售总结会</td>
                        <td>第一会议室</td>
                        <td>2013-11-20 9：00</td>
                        <td>2013-11-20 11：00</td>
                        <td>人员出差</td>
                        <td>
                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>与Google合作推广Android技术培训会议</td>
                        <td>第三会议室</td>
                        <td>2013-11-18 9：00</td>
                        <td>2013-11-18 11：00</td>
                        <td>人员出差</td>
                        <td>
                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>员工例行面谈</td>
                        <td>小会议室</td>
                        <td>2013-11-16 16：00</td>
                        <td>2013-11-16 17：00</td>
                        <td>人员出差</td>
                        <td>
                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
                        </td>
                    </tr>
                </table>
                
            </div>
        </div>
        <div class="page-footer">
            <hr/>
            更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
            <img src="images/footer.png" alt="CoolMeeting"/>
        </div>
    </body>
</html>