package com.icss.Meeting_System.eneity;

import java.io.Serializable;

/*****************************
*@类名     Employee.java
*@作者      沐沐
*@日期    2018年7月17日-上午10:17:19
*@版本    V1.0
*@描述    
******************************/
public class Employee implements Serializable{
	private static final long serialVersionUID = 3927461121299517095L;
	private  Integer employeeid;
	private String employeename;
	private String username;
	private String phone;
	private String email;
	private String status;
	private Integer departmentid;
	private String password;
	private String role;
	public Integer getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String employeename, String username, String phone, String email, Integer departmentid,
			String password) {
		super();
		this.employeename = employeename;
		this.username = username;
		this.phone = phone;
		this.email = email;
		this.departmentid = departmentid;
		this.password = password;
	}
	public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
