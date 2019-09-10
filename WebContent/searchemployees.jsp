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
        <script src="styles/js/jquery-1.7.1.js"></script>
        <script>
        function goPage(page){
    		$("#pageIndex").val(page);
    		$("#form").submit();
    	}
    	function gotoPage(page){
    		var pagenum = $("#pagenum").val();
    		/*
    		* isNaN()方法是用来检测数据是不是一个number数据类型的值
    		*/
    		if(isNaN(pagenum)){  
    			alert("请输入有效的页码!");
    			return;
    		}
    		if(pagenum>=1&&pagenum<=page){
    			goPage(pagenum);
    		}else{
    			alert("超过正常页码范围！");
    		}
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
                    会议预定 > 搜索员工
                </div>
               <form id="form" action="${pageContext.request.contextPath }/SearchEempServlet" method="post">
                    <input type="hidden" id="pageIndex" name="pageIndex" />
                    <fieldset>
                        <legend>搜索员工</legend>
                        <table class="formtable">
                            <tr>
                                <td>姓名：</td>
                                <td>
                                    <input type="text" id="employeename" name="employeename" value="${employeename }" maxlength="20"/>
                                </td>
                                <td>账号名：</td>
                                <td>
                                    <input type="text" id="accountname" name="username" value="${username }" maxlength="20"/>
                                </td>
                                <td>状态：</td>
                                <td>
                                    <input type="radio" id="status" name="status" value="1" <c:if test="${status==1 }">checked</c:if> /><label>已批准</label>
                                    <input type="radio" id="status" name="status" value="0" <c:if test="${status==0 }">checked</c:if>/><label>待审批</label>
                                    <input type="radio" id="status" name="status" value="2" <c:if test="${status==2 }">checked</c:if>/><label>未通过</label>
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
                            共<span class="info-number">${page.pageCount }</span>条结果，
                            分成<span class="info-number">${page.totalPage }</span>页显示，
                            当前第<span class="info-number">${page.pageIndex }</span>页
                        </div>
                        <div class="header-nav">
                            <input type="button" class="clickbutton" onclick="goPage(1)" value="首页"/>
                        	<c:if test="${page.pageIndex >1}">
                            <input type="button" class="clickbutton" onclick="goPage(${page.pageIndex }-1)" value="上页"/>
                            </c:if>
                            <c:if test="${page.totalPage>page.pageIndex}">  <!--  10  > 1~9 10 -->
                            <input type="button" class="clickbutton" onclick="goPage(${page.pageIndex }+1)" value="下页"/>
                            </c:if>
                            <input type="button" class="clickbutton" onclick="goPage(${page.totalPage })" value="末页"/>
                            跳到第<input type="text" id="pagenum" class="nav-number"/>页
                            <input type="button" class="clickbutton" onclick="gotoPage(${page.totalPage })" value="跳转"/>
                            
                        </div>
                    </div>
                </div>
                <table class="listtable">
                    <tr class="listheader">
                        <th>姓名</th>
                        <th>账号名</th>
                        <th>联系电话</th>
                        <th>电子邮件</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${page.list }" var="emp" varStatus="st">
	                    <tr>
	                        <td>${emp.employeename }</td>
	                        <td>${emp.username }</td>
	                        <td>${emp.phone }</td>
	                        <td>${emp.email }</td>
	                        <td>
	                            <a class="clickbutton" href="#">关闭账号</a>
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