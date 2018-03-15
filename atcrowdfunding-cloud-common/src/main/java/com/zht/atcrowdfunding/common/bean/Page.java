package com.zht.atcrowdfunding.common.bean;

import java.util.List;

public class Page<T> {

	private Integer pageno; //当前页
	private Integer pagesize;//每页记录数
	private Integer totalPageno;//总页数
	private Integer totalPagesize;//总记录数
	private List<T> datas;//集合，存放用户等
	
	
	public Page() {
		super();
	}
	public Page(Integer pageno, Integer pagesize, Integer totalPageno, Integer totalPagesize, List<T> datas) {
		super();
		this.pageno = pageno;
		this.pagesize = pagesize;
		this.totalPageno = totalPageno;
		this.totalPagesize = totalPagesize;
		this.datas = datas;
	}
	public Integer getPageno() {
		return pageno;
	}
	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getTotalPageno() {
		return totalPageno;
	}
	public void setTotalPageno(Integer totalPageno) {
		this.totalPageno = totalPageno;
	}
	public Integer getTotalPagesize() {
		return totalPagesize;
	}
	public void setTotalPagesize(Integer totalPagesize) {
//		if(totalPagesize % pagesize == 0) {
//			totalPageno = totalPagesize / pagesize;
//		}else {
//			totalPageno = totalPagesize / pagesize + 1;
//		}
		this.totalPagesize = totalPagesize;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	
	
}
