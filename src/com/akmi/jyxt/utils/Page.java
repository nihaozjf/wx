package com.akmi.jyxt.utils;
import java.util.List;

public class Page<T> {
	private int pagesize;
	private int curpage;
	private int pagecount;
	private int sumcount;
	private String pageurl;
	private List<T> result;


	
	public int getSumcount() {
		return sumcount;
	}
	public void setSumcount(int sumcount) {
		this.sumcount = sumcount;
	}
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public int getCurpage() {
		return curpage;
	}
	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}
	
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	/**
	 * @param pageurl the pageurl to set
	 */
	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}
	/**
	 * @return the pageurl
	 */
	public String getPageurl() {
		return pageurl;
	}
	

}