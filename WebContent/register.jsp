<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
          <meta charset = "utf-8"/>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="styles/common.css"/>
          <script src="styles/js/jquery-1.7.1.js"></script>
        <style type="text/css">
            
        </style>
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
                    人员管理 > 员工注册
                </div>
                <form action="${pageContext.request.contextPath}/EmployeeRegisterServlet">
                    <fieldset>
                        <legend>员工信息</legend>
                        <table class="formtable" style="width:50%">
                            <tr>
                                <td>姓名：</td>
                                <td>
                                    <input type="text" id="employeename" maxlength="20" name="employeename"/>
                                </td>
                            </tr>
                            <tr>
                                <td>账户名：</td>
                                <td>
                                    <input type="text" id="accountname" maxlength="20" name="username"/>
                                </td>
                            </tr>
                            <tr>
                                <td>密码：</td>
                                <td>
                                    <input type="password" id="password" maxlength="20" placeholder="请输入6位以上的密码" name="password"/>
                                </td>
                            </tr>
                            <tr>
                                <td>确认密码：</td>
                                <td>
                                    <input type="password" id="confirm" maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td>联系电话：</td>
                                <td>
                                    <input type="text" id="phone" maxlength="20" name="phone"/>
                                </td>
                            </tr>
                            <tr>
                                <td>电子邮件：</td>
                                <td>
                                    <input type="text" id="email" maxlength="20" name="email"/>
                                </td>
                            </tr>
							<td>所在部门：</td>
                                <td>
                                    <select name="deptid"> 
                                       <option value="-1">---请选择---</option>   
                                       <c:forEach items="${lists}" var="dept" varStatus="st">
                                     	<option value="${dept.departmentid}">${dept.departmentname}</option>
										 </c:forEach>
                                     </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" class="command">
                                    <input type="submit" class="clickbutton" value="注册"/>
                                    <input type="reset" class="clickbutton" value="重置"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <div class="page-footer">
            <hr/>
            更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
            <img src="images/footer.png" alt="CoolMeeting"/>
        </div>
    </body>
</html>