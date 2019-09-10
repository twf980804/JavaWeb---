package com.icss.Meeting_System_dao;

import com.icss.Meeting_System.eneity.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*****************************
*@类名     EmployeeDao.java
*@作者      沐沐
*@日期    2018年7月17日-下午2:23:17
*@版本    V1.0
*@描述    
******************************/
public class EmployeeDao extends baseDao {
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月17日-下午2:26:20
	 *@描述  Dao层逻辑登录
	 */
	public Employee Login(String username,String password) {
		Employee emp=null;
		try {
			connection = GetConnection();
			//第一个问号从1开始，有几个问号就写几个
			String sql="select * from employee where username=? and password=?";
			ps=connection.prepareStatement(sql);
			//问号赋值
			ps.setString(1, username);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs.next()) {
				emp=new Employee();
				emp.setEmployeeid(rs.getInt("employeeid"));
				emp.setEmployeename(rs.getString("employeename"));
				emp.setUsername(rs.getString("username"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return emp;
	}
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月19日-上午10:21:43
	 *@描述  员工注册的方法
	 */
	public int employeeRegister(Employee emp) {
		int result=0;
		try {
			connection = GetConnection();
			String sql="insert into employee(employeename,username,password,phone,email,departmentid) "
					+ "values(?,?,?,?,?,?)";
			ps=connection.prepareStatement(sql);
			ps.setString(1, emp.getEmployeename());
			ps.setString(2, emp.getUsername());
			ps.setString(3, emp.getPassword());
			ps.setString(4, emp.getPhone());
			ps.setString(5, emp.getEmail());
			ps.setInt(6, emp.getDepartmentid());
			result = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月19日-下午5:51:12
	 *@描述  查找所有员工的信息
	 */
	public List<Employee> searchEmployees(String employeename, String username, String status, int startnum,
			int pageSize) {
		List<Employee> list = new ArrayList<Employee>();
		try {
			//创建connection
			connection=GetConnection();
			//创建 sql语句
			String sql = "select * from employee where 1=1  ";
			//判断姓名不为空
			if(employeename!=null&&!employeename.equals("")){
				sql += "  and employeename like '%"+employeename+"%'";
			} 
			//判断账户不为空
			if(username!=null&&!username.equals("")){
				sql += "  and username like '%"+username+"%'";
			}
			//判断用户状态不空
			if(status!=null&&!status.equals("")){
				sql += "  and status="+status;
			}
			//拼接分页sql
			sql += "  limit  "+startnum+","+pageSize;
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Employee emp = new Employee();
				emp.setEmployeeid(rs.getInt("employeeid"));
				emp.setEmployeename(rs.getString("employeename"));
				emp.setUsername(rs.getString("username"));
				emp.setPhone(rs.getString("phone"));
				emp.setEmail(rs.getString("email"));
				list.add(emp);
			}
			/*StringBuilder sql1 = new StringBuilder();
			StringBuffer sql2 = new StringBuffer();*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月19日-下午5:51:50
	 *@描述  查找数据库中的页码标号
	 */
	public int searchEmpCount(String employeename, String username, String status) {
		int result = 0;
		try {
			//创建connection
			connection=GetConnection();
			//创建 sql语句
			String sql = "select count(*) as num from employee where 1=1  ";
			//判断姓名不为空
			if(employeename!=null&&!employeename.equals("")){
				sql += "  and employeename like '%"+employeename+"%'";
			} 
			//判断账户不为空
			if(username!=null&&!username.equals("")){
				sql += "  and username like '%"+username+"%'";
			}
			//判断用户状态不空
			if(status!=null&&!status.equals("")){
				sql += "  and status="+status;
			}
			
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getInt("num");  //rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月22日-上午10:52:04
	 *@描述  审核员工的方法
	 */
	public  List<Employee>  forEach(){
		List<Employee> list = new ArrayList<Employee>();
		try {
			connection = GetConnection();
			String sql="select * from employee";
			//先开后关的原则
			ps=connection.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				Employee emp = new Employee();
			   emp.setEmployeename(rs.getString("employeename"));
			   emp.setUsername(rs.getString("username"));
			   emp.setPhone(rs.getString("phone"));
			   emp.setEmail(rs.getString("email"));
			    list.add(emp);
		}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	public List<Employee> findEmpsByDeptId(int deptid) {
		/**
		 *@作者  沐沐
		 *@日期  2018年7月24日-下午12:41:38
		 *@描述   通过id找到相应的部门
		 */
		List<Employee> list = new ArrayList<Employee>();
		try {
			//创建connection
			connection=GetConnection();
			//第一个问从1开始
			String sql="select employeeid,employeename,username from employee where departmentid=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, deptid);			
			rs = ps.executeQuery();
			while(rs.next()){
				Employee emp = new Employee();
				emp.setEmployeeid(rs.getInt("employeeid"));
				emp.setEmployeename(rs.getString("employeename"));
				emp.setUsername(rs.getString("username"));
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
