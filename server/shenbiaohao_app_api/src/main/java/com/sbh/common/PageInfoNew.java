package com.sbh.common;
/**
 *@Description 新建分页类,解决以往分页每页最多10条的问题
 *@since 2015/12/18
 *@author jun
 *@version
 *mailto:guojun828@126.com
 */
public class PageInfoNew extends PageInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2188268521691453141L;
	private static final Integer DEAFULT_PAGE_SIZE = 10; //每页默认记录数
	@Override
	public Integer getPageSize() {
		 	if(pageSize < 1) {
	            pageSize = DEAFULT_PAGE_SIZE;
	        }
	        return pageSize;
	}
	@Override
	 public Integer getStartRow() {
	    	return (getCurrentPage() - 1 ) * getPageSize()  ;
	    }
}
