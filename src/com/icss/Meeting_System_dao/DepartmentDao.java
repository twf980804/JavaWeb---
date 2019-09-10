package com.icss.Meeting_System_dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.icss.Meeting_System.eneity.Department;

/*****************************
*@类名     DepartemntDao.java
*@作者      沐沐
*@日期    2018年7月17日-下午5:54:40
*@版本    V1.0
*@描述    
******************************/
public class DepartmentDao extends baseDao {
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月17日-下午6:12:19
	 *@描述  这个方法是遍历数据库信息
	 */
	public List<Department> display(){
		List<Department> list=new ArrayList<Department>();
		try {
			connection =GetConnection();
			String sql="select * from department";
			//先开后关的原则
			ps=connection.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				Department dp=new Department();
			    dp.setDepartmentid(rs.getInt("departmentid"));
			    dp.setDepartmentname(rs.getString("departmentname"));
			    dp.setStatus(rs.getInt("status"));
			    list.add(dp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return list;
	}
	/**
	 * 
	 *@作者  沐沐
	 *@日期  2018年7月18日-下午1:58:23
	 *@描述  增加部门的方法
	 */
	public int addDepartment(String name)  {
		 int result =0;
		  try {
			  connection =GetConnection();
			  String sql="insert into department(departmentid,departmentname,status) values(null,?,1)";
			  ps = connection.prepareStatement(sql);
			  ps.setString(1, name);
			  result = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
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
	 *@日期  2018年7月18日-下午4:39:03
	 *@描述  修改部门信息的方法
	 */
	public int updateDept(int deptid, String deptname) {
		int result =0;
		try {
			//创建connection
			connection=GetConnection();
			String sql="update department set departmentname=? where departmentid=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, deptname);
			ps.setInt(2, deptid);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
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
	 *@日期  2018年7月18日-下午4:52:44
	 *@描述  删除部门的方法
	 */
	public int delDept(int id) {
		int result =0;
		try {
			connection=GetConnection();
			String sql="delete  from  department  where  departmentid=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			System.out.println(sql);
			result=ps.executeUpdate();
		} catch (Exception e) {
			
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
   
}
