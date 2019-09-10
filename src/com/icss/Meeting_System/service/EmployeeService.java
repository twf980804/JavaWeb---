package com.icss.Meeting_System.service;

import java.util.ArrayList;
import java.util.List;

import com.icss.Meeting_System.eneity.Employee;
import com.icss.Meeting_System.util.Page;
import com.icss.Meeting_System_dao.EmployeeDao;

/*****************************
*@类名     EmployeeService.java
*@作者      沐沐
*@日期    2018年7月17日-下午2:17:01
*@版本    V1.0
*@描述    
******************************/
public class EmployeeService {
/**
 * 
 *@作者  沐沐
 *@日期  2018年7月17日-下午2:18:13
 *@描述  登录层的业务描述。如果登陆失败就返回null，如果不等于null就代表成功
 */
	EmployeeDao ed=new EmployeeDao();
	public Employee Login(String name,String psd) {
		Employee emp=null;
		try {
			emp=ed.Login(name, psd);
		} catch (Exception e) {
			// TODO: handle exception
		  e.printStackTrace();
		} finally {
			ed.closeConnection();
		}
		return emp;
	}
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月19日-上午10:51:26
	 *@描述  员工注册
	 */
	public int employeeRegister(Employee emp) {
		int result=0;
		try {
			result = ed.employeeRegister(emp);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ed.closeConnection();
		}
		return result;
		}
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月19日-下午6:02:01
	 *@描述
	 */
	public Page<Employee> searchEmpPage(String employeename, String username, String status, int currentPage,
			int pageSize) {
		Page<Employee> page = new Page<Employee>();
		try {
			//起始行编号
			int startnum = (currentPage-1)*pageSize;
			//返回员工的列表 (数据库查询出来的)
			List<Employee> list =ed.searchEmployees(employeename,username,status,startnum,pageSize);
			//返回员工的记录数
			int count = ed.searchEmpCount(employeename,username,status);
			//构造 相关的工具类给属性赋值
			page.setPageIndex(currentPage);
			page.setPageSize(pageSize);
			page.setPageCount(count);
			page.setList(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ed.closeConnection();
		}
		return page;
	}
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月22日-上午11:06:59
	 *@描述  service 层调用dao层方法
	 */
	public  List<Employee>  forEach(){
		List<Employee> list = null;
		try {
			list=ed.forEach();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ed.closeConnection();
		}
		return list;
	}
	public List<Employee> findEmpsByDeptId(int deptid) {
		/**
		 *@作者  沐沐
		 *@日期  2018年7月24日-下午12:40:35
		 *@描述  
		 */
		List<Employee> list = new ArrayList<Employee>();
		try {
			list = ed.findEmpsByDeptId(deptid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ed.closeConnection();
		}
		return list;
	}
}
