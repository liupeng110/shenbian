<<<<<<< HEAD
package com.sbh.common;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 专用于app数据分页查询结果返回<br>
 * List&lt;T&gt; data 成员存放分页后的数据<br>
 * AppPageInfo page为分页信息<br>
 * 分页信息参见{@link com.mmb.common.AppPageInfo}
 * @author 张克行
 * 
 *
 * @param <T>	返回的结果集的具体数据类型
 */
public class AppRemotePage<T> implements Serializable {

	private static final long serialVersionUID = 5756345873158652812L;

	private List<T> data;

	private AppPageInfo page;

	public AppRemotePage(List<T> data, AppPageInfo page) {
		this.data = data;
		this.page = page;
	}

	public AppRemotePage() {

	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public AppPageInfo getPage() {
		return page;
	}

	public void setPage(AppPageInfo page) {
		this.page = page;
	}

}
=======
package com.sbh.common;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 专用于app数据分页查询结果返回<br>
 * List&lt;T&gt; data 成员存放分页后的数据<br>
 * AppPageInfo page为分页信息<br>
 * 分页信息参见{@link com.mmb.common.AppPageInfo}
 * @author 张克行
 * 
 *
 * @param <T>	返回的结果集的具体数据类型
 */
public class AppRemotePage<T> implements Serializable {

	private static final long serialVersionUID = 5756345873158652812L;

	private List<T> data;

	private AppPageInfo page;

	public AppRemotePage(List<T> data, AppPageInfo page) {
		this.data = data;
		this.page = page;
	}

	public AppRemotePage() {

	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public AppPageInfo getPage() {
		return page;
	}

	public void setPage(AppPageInfo page) {
		this.page = page;
	}

}
>>>>>>> e7eb74d832a92ae08214a7cb6b7869295e64c0c2
