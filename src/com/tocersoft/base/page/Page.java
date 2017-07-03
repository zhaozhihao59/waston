package com.tocersoft.base.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

public class Page<T> extends RowBounds implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<T> result = new ArrayList<T>();

	private String orderBy;

	private String sort = "asc";

	/**
	 * 获得数据总条数
	 */
	private long totalCount;

	/**
	 * 页码
	 */
	private int page;

	private int size = 50;

	/**
	 * 默认构造，默认从第一页查询 返回10条数据
	 */
	public Page() {
		super(0, 10);
	}

	public Page(int page, int size) {
		super((page - 1) * size < 0 ? 0 : (page - 1) * size, size);
		this.page = page;
		this.size = size;
	}

	public Page(List<T> result, long totalCount, int page, int size) {
		super((page - 1) * size < 0 ? 0 : (page - 1) * size, size);
		this.result = result;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * 获得总页数
	 * 
	 * @return
	 */
	public long getTotalPages() {
		long rows = this.totalCount;
		long mod = rows % this.size;
		if (mod == 0) {
			return rows / this.size;
		}
		return rows / this.size + 1;
	}

	/**
	 * 获得当前页的索引
	 * 
	 * @return
	 */
	public int getStartIndex() {
		return (this.getPage() - 1) * this.getSize() < 0 ? 0
				: (this.getPage() - 1) * this.getSize();
	}

	/**
	 * 获得当前页的索引
	 * 
	 * @return
	 */
	public int getStartIndex(int page, int size) {
		return (page - 1) * size < 0 ? 0 : (page - 1) * size;
	}

	/**
	 * 得到上一页
	 * 
	 * @return
	 */
	public long getPrePage() {
		if (this.getPage() > 1)
			return this.getPage() - 1;
		return 1;
	}

	/**
	 * 得到下一页
	 * 
	 * @return
	 */
	public long getNextPage() {
		if (this.getPage() >= this.getTotalPages())
			return this.getTotalPages();
		return this.getPage() + 1;
	}

	/**
	 * 排序字段
	 * 
	 * @return
	 */
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 排序方向
	 * 
	 * @return
	 */
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public int getLimit() {
		return this.getSize();
	}

	@Override
	public int getOffset() {
		return this.getStartIndex();
	}

}
