<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
           <meta charset = "utf-8"/>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="styles/common.css"/>
        
          <script type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/styles/js/jquery-1.7.1.js"></script>
        <style type="text/css">
            #divfrom{
                float:left;
                width:150px;
            }
            #divto{
                float:left;
                width:150px;
            }
            #divoperator{
                float:left;
                width:50px;
                padding:60px 5px;
            }
            #divoperator input[type="button"]{
                margin:10px 0;
            }
            #selDepartments{
                display:block;
                width:100%;
            }
            #selEmployees{
                display:block;
                width:100%;
                height:200px;
            }
            #selSelectedEmployees{
                display:block;
                width:100%;
                height:225px;
            }
        </style>
    <script type="application/javascript">
            function employee(employeeid, employeename){
                this.employeeid = employeeid;
                this.employeename = employeename;
            }
            function department(departmentid, departmentname, employees){
                this.departmentid = departmentid;
                this.departmentname = departmentname;
                this.employees = employees;
            }
            var data = new Array(
                new department(1, "技术部", new Array(
                    new employee(1001, "a00"), new employee(1002, "a01"), new employee(1003, "a02"), new employee(1004, "a03"))),
                new department(2, "销售部", new Array(
                    new employee(2001, "b00"), new employee(2002, "b01"), new employee(2003, "b02"), new employee(2004, "b03"))),
                new department(3, "市场部", new Array(
                    new employee(3001, "c00"), new employee(3002, "c01"), new employee(3003, "c02"), new employee(3004, "c03"))),
                new department(4, "行政部", new Array(
                    new employee(4001, "d00"), new employee(4002, "d01"), new employee(4003, "d02"), new employee(4004, "d03"))));
            
            var selDepartments;/* 部门下拉列表框 对象  */
            var selEmployees;  /* 左侧员列表  */
            var selSelectedEmployees; /* 右侧员工列表 参会人员信息  */
            
            function body_load(){
                selDepartments = document.getElementById("selDepartments");
                selEmployees = document.getElementById("selEmployees");
                selSelectedEmployees = document.getElementById("selSelectedEmployees");
                /* 有没有用了？ */
                /* for(var i=0;i<data.length;i++){
                    var dep = document.createElement("option");
                    dep.value = data[i].departmentid;
                    dep.text = data[i].departmentname;
                    selDepartments.appendChild(dep);
                } */
                
                fillEmployees();
            }
            
            function fillEmployees(){
                clearList(selEmployees);/* 清除左侧员工列表 */
                /* 获取部门id */
                var departmentid = selDepartments.options[selDepartments.selectedIndex].value;
                var employees;
                /* 通过ajax 根据部门id查询部门员工信息  */
                $.ajax({
                	url:"ajaxSearchEmployees",
                	data:"departmentid="+departmentid,
                	datatype:"json",
                	success:function(data){
                		var emps =eval('('+data+')');
                		for(var i=0;i<emps.length;i++){
                			var emp = document.createElement("option");
                			 emp.value = emps[i].employeeid;
                             emp.text = emps[i].employeename;
                             selEmployees.appendChild(emp);
                		}
                	},
                	error:function(){
                		alert("系统错误！");
                	}
                });
                
                
                
               /* for(var i=0;i<data.length;i++){
                    if (departmentid == data[i].departmentid){
                        employees = data[i].employees;
                        break;
                    }
                }
                for(i=0;i<employees.length;i++){
                    var emp = document.createElement("option");
                    emp.value = employees[i].employeeid;
                    emp.text = employees[i].employeename;
                    selEmployees.appendChild(emp);
                }  */
            }
            
            function clearList(list){
                while(list.childElementCount > 0){
                    list.removeChild(list.lastChild);
                }
            }
            /*点击  > 添加 */
            function selectEmployees(){
                for(var i=0;i<selEmployees.options.length;i++){
                    if (selEmployees.options[i].selected){
                        addEmployee(selEmployees.options[i]);
                        selEmployees.options[i].selected = false;
                    }
                }
            }
            /* 拼接员工的ID字符串  8,13,26,17, */
            var employeeids = $("#employeeids").val();
            /* 点击 <  删除 */
            function deSelectEmployees(){
            	//加1 获取当前参会员工的编号字符串
            	var employeeids=$("#employeeids").val();
            	
                var elementsToRemoved = new Array();  /* 声明数组 */
                var options = selSelectedEmployees.options;
                for(var i=0;i<options.length;i++){
                    if (options[i].selected){
                        elementsToRemoved.push(options[i]); /* 往 数组 中添加的 */
                        ///加2 参会人员字符串移除  options[i].value+"," 相当于  17,
                        employeeids=employeeids.replace(options[i].value+",","")
                    }
                }
                for(i=0;i<elementsToRemoved.length;i++){
                    selSelectedEmployees.removeChild(elementsToRemoved[i]);
                }
               ///加3 修改页面中存放的员工编号字符串值  
                $("#employeeids").val(employeeids);	
                
            }
           
            /* 添加  */
            function addEmployee(optEmployee){
                var options = selSelectedEmployees.options;
                var i = 0;
                var insertIndex = -1;
                while(i < options.length){
                    if (optEmployee.value == options[i].value){
                        return;
                    } else if (optEmployee.value < options[i].value) {
                        insertIndex = i;
                        break;
                    }
                    i++;
                }
                var opt = document.createElement("option");
                opt.value = optEmployee.value;
                opt.text = optEmployee.text;
                /* 加   8,13, */
                if(employeeids!=null){
                	if(!employeeids.match(opt.value)){
                		employeeids =employeeids + opt.value+",";
                	}
                }else{
                	employeeids =opt.value+",";
                }
                //改变量
              	$("#employeeids").val(employeeids);
                
                if (insertIndex == -1){
                    selSelectedEmployees.appendChild(opt);
                } else {
                    selSelectedEmployees.insertBefore(opt, options[insertIndex]);
                }
            }            
        </script>
    </head>
    <body onload="body_load()">
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
                    会议预定 > 预定会议
                </div>
                <form action="${pageContext.request.contextPath }/AddMeetingServlet" method="post">
                	<!-- 重要：记录参会人员的编号 8,13,23,   -->
                	<input type="hidden" id="employeeids" name="employeeids"/>
                    <fieldset>
                        <legend>会议信息</legend>
                        <table class="formtable">
                            <tr>
                                <td>会议名称：</td>
                                <td>
                                    <input type="text" id="meetingname" name="meetingname" maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td>预计参加人数：</td>
                                <td>
                                    <input type="text" id="numofattendents" name="numofattendents" />
                                </td>
                            </tr>
                            <tr>
                                <td>预计开始时间：</td>
                                <td>
                                   <!--  <input type="date" id="startdate"/>
                                    <input type="time" id="starttime"/> -->
									<input class="Wdate" id="startTime" name="startTime" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-{%d+1}'})"/>
                                </td>
                            </tr>
                            <tr>
                                <td>预计结束时间：</td>
                                <td>
                                    <!-- <input type="date" id="enddate" />
                                    <input type="time" id="endtime" /> -->
                                    <input class="Wdate" id="endtime" name="endtime" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-{%d+1}'})"/>
                                </td>
                            </tr>
							<tr>
                                <td>会议室名称：</td>
                                <td>
                                    <select name="roomid">
                                    	<c:forEach items="${meetingroom }" var="room" >
                                     		<option value="${room.roomid }">${room.roomname }</option>
                                     	</c:forEach>
                                     </select>
                                </td>
                            </tr>
                            <tr>
                                <td>会议说明：</td>
                                <td>
                                    <textarea id="description" name="description" rows="5"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>选择参会人员：</td>
                                <td>
                                    <div id="divfrom">
                                        <select id="selDepartments" onchange="fillEmployees()">
                                        	<c:forEach items="${departments }" var="dept">
                                        		<option value="${dept.departmentid }">${dept.departmentname }</option>
                                        	</c:forEach>
                                        </select>
                                        <select id="selEmployees" multiple="true">
                                        
                                        </select>
                                    </div>
                                    <div id="divoperator">
                                        <input type="button" class="clickbutton" value="&gt;" onclick="selectEmployees()"/>
                                        <input type="button" class="clickbutton" value="&lt;" onclick="deSelectEmployees()"/>
                                    </div>
                                    <div id="divto">
                                        <select id="selSelectedEmployees" multiple="true">
                                        </select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="command" colspan="2">
                                    <input type="submit" class="clickbutton" value="预定会议"/>
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