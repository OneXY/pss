package com.onexy.pss.page;

import java.util.ArrayList;
import java.util.List;

public class PageResult<T> {
	private int totalCount;
	private int totalPage;
	private int pageSize;
	private int currentPage;
	private List<T> rows = new ArrayList<T>();

	public PageResult() {
	}

	public PageResult(int totalCount, int pageSize, int currentPage) {
		this.totalCount = totalCount;
		this.pageSize = pageSize < 1 ? 10 : pageSize;
		this.currentPage = currentPage < 1 ? 1 : currentPage;
//		this.totalPage = (int) Math.ceil((double) this.totalCount / this.pageSize);
		this.totalPage = (this.totalCount + this.pageSize - 1) / this.pageSize;
		this.currentPage = this.currentPage < this.totalPage ? this.currentPage
				: this.totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageResult [totalCount=" + totalCount + ", totalPage="
				+ totalPage + ", pageSize=" + pageSize + ", currentPage="
				+ currentPage + ", rows.size=" + rows.size() + "]";
	}

}
