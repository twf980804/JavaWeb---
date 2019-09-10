package com.icss.Meeting_System.eneity;

import java.io.Serializable;

/*****************************
*@类名     Department.java
*@作者      沐沐
*@日期    2018年7月17日-下午5:50:43
*@版本    V1.0
*@描述    
******************************/
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1493150114352019347L;
	private Integer departmentid;
	private String departmentname;
	private Integer status;
	public Integer getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String toString() {
		return departmentid+"--"+departmentname+"--"+status;
	}

}
