<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="styles/common.css"/>
        <style type="text/css">
            <meta charset = "utf-8"/>  
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
                        <li class="sidebar-menuitem"><a href="notifications.html">最新通知</a></li>
                        <li class="sidebar-menuitem active"><a href="mybookings.html">我的预定</a></li>
                        <li class="sidebar-menuitem"><a href="mymeetings.html">我的会议</a></li>
                    </ul>
                </div>
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle">人员管理</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="departments.html">部门管理</></li>
                        <li class="sidebar-menuitem"><a href="register.html">员工注册</a></li>
                        <li class="sidebar-menuitem"><a href="approveaccount.html">注册审批</a></li>
                        <li class="sidebar-menuitem"><a href="searchemployees.html">搜索员工</a></li>
                    </ul>
                </div>
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle">会议预定</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="addmeetingroom.html">添加会议室</a></li>
                        <li class="sidebar-menuitem"><a href="meetingrooms.html">查看会议室</a></li>
                        <li class="sidebar-menuitem"><a href="bookmeeting.html">预定会议</a></li>
                        <li class="sidebar-menuitem"><a href="searchmeetings.html">搜索会议</a></li>
                    </ul>
                </div>
            </div>
            <div class="page-content">
                <div class="content-nav">
                    会议预定 > 搜索会议
                </div>
               <form id="form" action="${pageContext.request.contextPath}/SearchMeetingServlet" method="post">
                <input type="hidden" id="pageIndex" name="pageIndex"  />
                    <fieldset>
                        <legend>搜索会议</legend>
                        <table class="formtable">
                            <tr>
                                <td>会议名称：</td>
                                <td>
                                    <input type="text" id="meetingname" name="meetingname" value= "${ mt.meetingname}" maxlength="20"/>
                                </td>
                                <td>会议室名称：</td>
                                <td>
                                    <input type="text" id="roomname" name="roomname" value= "${mt.roomname }" maxlength="20"/>
                                </td>
                                <td>预定者姓名：</td>
                                <td>
                                    <input type="text" id="reservername" name="reservername" value= "${ mt.reservername}" maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td>预定日期：</td>
                                <td colspan="5">
                                    从&nbsp; <input type="date" id="reservefromdate" name="reservefromdate" class="Wdate" value="${reservefromdateTime }" placeholder="例如：2013-10-20" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-{%d+1}'})"/>
                                    
                                    到&nbsp;<input type="date" id="reservetodate" name="reservetodate" class="Wdate" value="${reservetodateTime}" placeholder="例如：2013-10-22" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-{%d+1}'})"/> 
                                    
                                </td>
                            </tr>
                            <tr>
                                <td>会议日期：</td>
                                <td colspan="5">
                                    从&nbsp;<input class="Wdate" type="date" id="meetingfromdate" value="${meetingfromdateTime }" placeholder="例如：2013-10-20" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-{%d+1}'})"/>
                                    
                                    到&nbsp;<input class="Wdate" type="date" id="meetingtodate"value="${meetingtodateTime }" placeholder="例如：2013-10-22" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-{%d+1}'})"/> 
                                    
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" class="command">
                                    <input type="submit" class="clickbutton" value="查询"/>
                                    <input type="reset" class="clickbutton" value="重置"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
                <div>
                    <h3 style="text-align:center;color:black">查询结果</h3>
                    <div class="pager-header">
                        <div class="header-info">
                            共<span class="info-number">54</span>条结果，
                            分成<span class="info-number">6</span>页显示，
                            当前第<span class="info-number">1</span>页
                        </div>
                        <div class="header-nav">
                            <input type="button" class="clickbutton" value="首页"/>
                            <input type="button" class="clickbutton" value="上页"/>
                            <input type="button" class="clickbutton" value="下页"/>
                            <input type="button" class="clickbutton" value="末页"/>
                            跳到第<input type="text" id="pagenum" class="nav-number"/>页
                            <input type="button" class="clickbutton" value="跳转"/>
                        </div>
                    </div>
                </div>
                <table class="listtable">
                    <tr class="listheader">
                        <th>会议名称</th>
                        <th>会议室名称</th>
                        <th>会议开始时间</th>
                        <th>会议结束时间</th>
                        <th>会议预定时间</th>
                        <th>预定者</th>
                        <th>操作</th>
                    </tr>
                    <tr>
                        <td>业务洽谈会</td>
                        <td>第一会议室</td>
                        <td>2013-10-12 8:00</td>
                        <td>2013-10-12 12:00</td>
                        <td>2013-10-10 16:00</td>
                        <td>Jerry</td>
                        <td>
                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>业务洽谈会</td>
                        <td>第一会议室</td>
                        <td>2013-10-12 8:00</td>
                        <td>2013-10-12 12:00</td>
                        <td>2013-10-10 16:00</td>
                        <td>Jerry</td>
                        <td>
                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>业务洽谈会</td>
                        <td>第一会议室</td>
                        <td>2013-10-12 8:00</td>
                        <td>2013-10-12 12:00</td>
                        <td>2013-10-10 16:00</td>
                        <td>Jerry</td>
                        <td>
                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
                            
                        </td>
                    </tr>
                    <tr>
                        <td>业务洽谈会</td>
                        <td>第一会议室</td>
                        <td>2013-10-12 8:00</td>
                        <td>2013-10-12 12:00</td>
                        <td>2013-10-10 16:00</td>
                        <td>Jerry</td>
                        <td>
                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>业务洽谈会</td>
                        <td>第一会议室</td>
                        <td>2013-10-12 8:00</td>
                        <td>2013-10-12 12:00</td>
                        <td>2013-10-10 16:00</td>
                        <td>Jerry</td>
                        <td>
                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>业务洽谈会</td>
                        <td>第一会议室</td>
                        <td>2013-10-12 8:00</td>
                        <td>2013-10-12 12:00</td>
                        <td>2013-10-10 16:00</td>
                        <td>Jerry</td>
                        <td>
                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>业务洽谈会</td>
                        <td>第一会议室</td>
                        <td>2013-10-12 8:00</td>
                        <td>2013-10-12 12:00</td>
                        <td>2013-10-10 16:00</td>
                        <td>Jerry</td>
                        <td>
                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>业务洽谈会</td>
                        <td>第一会议室</td>
                        <td>2013-10-12 8:00</td>
                        <td>2013-10-12 12:00</td>
                        <td>2013-10-10 16:00</td>
                        <td>Jerry</td>
                        <td>
                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>业务洽谈会</td>
                        <td>第一会议室</td>
                        <td>2013-10-12 8:00</td>
                        <td>2013-10-12 12:00</td>
                        <td>2013-10-10 16:00</td>
                        <td>Jerry</td>
                        <td>
                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
                        </td>
                    </tr>
                    <tr>
                        <td>业务洽谈会</td>
                        <td>第一会议室</td>
                        <td>2013-10-12 8:00</td>
                        <td>2013-10-12 12:00</td>
                        <td>2013-10-10 16:00</td>
                        <td>Jerry</td>
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