
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="styles/common.css"/>
        <script src="styles/js/jquery-3.2.1.js"></script>
        <script>
           function router(roomid) {
        	   $("#see"+roomid).click(function() {
        		 location.href="${pageContext.request.contextPath }/SearchByIdServlet?roomid="+roomid;
        	   })
           }
        </script>
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
                    会议预定 > 查看会议室
                </div>
                <table class="listtable">
                    <caption>所有会议室:</caption>
                    <tr class="listheader">
                        <th>门牌编号</th>
                        <th>会议室名称</th>
                        <th>容纳人数</th>
                        <th>当前状态</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${lists}" var="dept" varStatus="st">
                    <tr>
                        <td>${dept.roomid }</td>
                        <td>${dept.roomname }</td>
                        <td>${dept.capacity }</td>
                        <td>${dept.status }</td>
                        <td>
                            <a class="clickbutton" href="javascript:router(${dept.roomid})" id="see${dept.roomid}">查看详情</a>
                        </td>
                    </tr>
                    </c:forEach>
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