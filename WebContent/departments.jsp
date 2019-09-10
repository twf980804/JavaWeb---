<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
          <meta charset = "utf-8"/>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="styles/common.css"/>
         <script src="styles/js/jquery-3.2.1.js"></script>
          <script>
          function edit(obj,value){
    		  $("#editBtn"+obj).css({
    			  display:"none"
    		  }); 
    		  $("#confirmBtn"+obj).css({
    			  display:"inline"
    		  });
    		  $("#cancelBtn"+obj).css({
    			  display:"inline"
    		  });
    		  $("#td"+obj).html("<input type='text' value="+value+" id=target"+obj+">");
    		  
    	  }
          function cancel(obj,value){
        	  $("#editBtn"+obj).css({
    			  display:"inline"
    		  });
    		  $("#confirmBtn"+obj).css({
    			  display:"none"
    		  });
    		  $("#cancelBtn"+obj).css({
    			  display:"none"
    		  });
    		  $("#td"+obj).html(value);
    		  
          }
          function ok(obj){
        	  var $value=$("#target"+obj).val();
        	  location.href="${pageContext.request.contextPath }/UpdateDepartmentServlet?deptid="+obj+"&deptname="+$value;
          }
          function del(obj){
        	  var flag = confirm("确定要删除吗？");  //true 点击确定按钮    false点击取消按钮
      		if(flag){
      			location.href="${pageContext.request.contextPath }/DelDepartmentServlet?deptid="+obj;
      		}
          }
          $(function(){
        	 $("#departmentname").change(function(){
            	 const name=$("#departmentname").val();
            	 $.get("DisplayServlet?opr=check",
               	 {data:name},
            	 function(data,err){
            		var json = JSON.stringify(data);
            		var reljson=JSON.parse(json);
            		console.log(reljson[0].departmentname)
            		$.each(reljson,function(index,val){
            			$("#display>table").append("<tr><td>"+val.departmentid++-
            					"</td><td>"+val.departmentname+"</td></tr>")
            		})
            		//$()
            	 },
            	 "json"
            	 )
        	 });
          }) 
          </script>
    </head>
    <body>
      <div id="display">
        <table border="1">
        </table>
      </div>
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
                    人员管理 > 部门管理
                </div>
                <form action="${pageContext.request.contextPath}/AddDeparServlet" method="post">
                    <fieldset>
                        <legend>添加部门</legend>
                        部门名称:
                        <input type="text" id="departmentname" maxlength="20" name="departmentname"/>
                        <input type="submit" class="clickbutton" value="添加" id="add"/>
                    </fieldset>
                </form>
                <table class="listtable">
                    <caption>所有部门:</caption>
                    <tr class="listheader">
                        <th>序号</th>
                        <th>部门编号</th>
                        <th>部门名称</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${lists}" var="dept" varStatus="st">
                    <tr>
                        <td>${st.count}</td> 
                        <td>${dept.departmentid}</td>
                        <td id="td${dept.departmentid}">${dept.departmentname}</td> 
                        <td>
                            <a class="clickbutton" href="javascript:edit(${dept.departmentid},'${dept.departmentname}')" id="editBtn${dept.departmentid}">编辑</a>
                            <a class="clickbutton" href="javascript:ok(${dept.departmentid})" id="confirmBtn${dept.departmentid}" style="display:none">确定</a>
                            <a class="clickbutton" href="javascript:cancel(${dept.departmentid},'${dept.departmentname}')" id="cancelBtn${dept.departmentid}" style="display:none">取消</a>
                            <a class="clickbutton" href="javascript:del(${dept.departmentid})" id="delBtn${dept.departmentid}">删除</a>
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