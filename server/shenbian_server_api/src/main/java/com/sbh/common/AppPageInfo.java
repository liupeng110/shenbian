package com.sbh.common;

import java.io.Serializable;

/**
 * app分页信息，仅包括：当前页码/当前页显示多少数据/查询条件中，共有多少页<br>
 * 其中：<br>
 * 当前页显示多少数据：是客户端传递的参数值<br>
 * 当前页码：是客户端传递的参数值，表示客户端在请求第几页数据，如果客户端请求的页码已经是最后一页，页码值为数据最大页数
 * @author 张克行
 *
 */
public class AppPageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2151080798646621973L;
	private Integer currentPage;// 当前页码,页码从0开始
	private Integer pageSize;// 当前页显示多少数据
	private Integer totalPage;// 查询条件中，共有多少页

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
    public Integer getStartRow() {
    	return  getCurrentPage() * getPageSize()  ;
    }

	public Integer getEndRow() {
		return getStartRow() + getPageSize() - 1;
	}

}
