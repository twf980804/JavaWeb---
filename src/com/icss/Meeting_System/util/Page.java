package com.icss.Meeting_System.util;

import java.util.List;

/*****************************
*@类名     Page.java
*@作者      沐沐
*@日期    2018年7月19日-下午5:47:29
*@版本    V1.0
*@描述    
******************************/
	public class Page<T> {
		private int pageIndex; //当前页码
		private int pageSize;   //页面大小(每页的记录数)  10
		private int pageCount;  //总的记录数    101
		private int totalPage;  //总页码   ?11
		private List<T> list;   //分页的数据 
		public int getPageIndex() {
			return pageIndex;
		}
		public void setPageIndex(int pageIndex) {
			this.pageIndex = pageIndex;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getPageCount() {
			return pageCount;
		}
		public void setPageCount(int pageCount) {
			this.pageCount = pageCount;
			this.totalPage = this.pageCount%this.pageSize==0?this.pageCount/this.pageSize:this.pageCount/this.pageSize+1;
		}
		public int getTotalPage() {
			return totalPage;
		}
		/*public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}*/
		public List<T> getList() {
			return list;
		}
		public void setList(List<T> list) {
			this.list = list;
		}

	}

