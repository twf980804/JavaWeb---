package com.icss.Meeting_System.service;

import java.util.List;

import com.icss.Meeting_System.eneity.Department;
import com.icss.Meeting_System_dao.DepartmentDao;

/*****************************
*@类名     DepartmentService.java
*@作者      沐沐
*@日期    2018年7月17日-下午6:01:31
*@版本    V1.0
*@描述    
******************************/
public class DepartmentService {
	DepartmentDao dd=new DepartmentDao();
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月17日-下午6:26:31
	 *@描述  逻辑层调用display方法
	 */
	public List<Department> display(){
		List<Department> list=null;
		try {
			list=dd.display();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dd.closeConnection();
		}
		return list;
	}
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月18日-下午4:40:00
	 *@描述  修改部门信息方法
	 */
	public int updateDept(int deptid, String deptname) {
		int result =0;
		try {
			result = dd.updateDept(deptid,deptname);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dd.closeConnection();
		}
		return result;
	}
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月18日-下午4:54:05
	 *@描述   service层调用dao层数据
	 */
	public int delDept(int id) {
		int result =0;
		try {
			result = dd.delDept(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dd.closeConnection();
		}
		return result;
	}
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月18日-下午5:59:12
	 *@描述   增加部门的方法
	 */
	public int addDepartment(String name)  {
		 int result =0;
		 try {
				result = dd.addDepartment(name);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dd.closeConnection();
			}
		return result; 
	}
}
