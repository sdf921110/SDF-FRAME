package com.sdf.codeGeneration.code.tool.utils;

import java.util.List;

/**
 * 分页
 * 
 * @author: SDF
 * @dateTime: 2017-3-12 上午12:33:02
 * @version: 1.0.0
 * @param <T>
 */
public class Page<T> {

	private Integer pageStart;
	private Integer pageCount;
	private Integer totalCount;

	private List<T> list;

	public Integer getPageStart() {
		return pageStart;
	}

	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
