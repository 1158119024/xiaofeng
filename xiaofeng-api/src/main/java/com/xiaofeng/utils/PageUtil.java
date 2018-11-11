package com.xiaofeng.utils;

import java.util.List;

public class PageUtil<T> {
	
	private static final Integer PAGESIZE_DEFAULT = 10;// 如果前端未传入pageSize，则采用此默认值

	private Integer currentPage = 1;//当前第几页

	private Integer pageSize = PAGESIZE_DEFAULT;//页面大小

	private Integer totalRecords;// 总记录数

	private Integer recordStart;//从那条记录开始查询

	private Integer totalPages;//总页数

	private List<T> list;//记录

	private String likeName;//模糊匹配词

	private String orderColumn;//排序字段

	private boolean orderASC;//是否升序排列

	private String startTime;//开始时间
	
	private String endTime;//结束时间
	
	private String state;//状态


	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public String getLikeName() {
		return likeName;
	}

	public void setLikeName(String likeName) {
		this.likeName = likeName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		if (null == pageSize || 0 == pageSize) {
			return PAGESIZE_DEFAULT;
		}
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	public Integer getTotalRecords() {
		return totalRecords == null?0:totalRecords;
	}
	
	public Integer getTotalPages() {
		if(totalPages != null && totalPages != 0){
			return totalPages;
		}else{
			return Double.valueOf(Math.ceil(getTotalRecords()/(double)getPageSize())).intValue();
		}
	}
	
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public void setRecordStart(Integer recordStart) {
		this.recordStart = recordStart;
	}

	public int getRecordStart() {
		if(recordStart == null){
			if (getCurrentPage() == 0 || getCurrentPage() == 1) {
				return 0;
			}
			int start = (getCurrentPage().intValue() - 1)* this.pageSize.intValue();
			return start < 0 ? 0 : start;
		}else{
			return recordStart;
		}
	}
	
	public String getOrderColumn() {
		return this.orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public boolean isOrderASC() {
		return this.orderASC;
	}

	public void setOrderASC(boolean orderASC) {
		this.orderASC = orderASC;
	}


	
	
}
