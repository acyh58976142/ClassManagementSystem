package cn.kgc.entity;

import java.util.List;

public class HomeworkPage<T> {

	//当前页
	private int pageNum;
	//每页显示的条数
	private int pageSize;
	
	//总记录数
	private int totalRecode;
	//总页数
	private int totalPage;
	//索引
	private int index;
	
	//数据的集合
	private List<T> date;

	public int getPageNum() {
		/**
		 * 如果指定的页码小于1，默认在第一页
		 * 如果大于最大的页码默认在最后一页
		 */
		if(pageNum<1){
			return 1;
		}
		if(pageNum>getTotalPage()){
			return getTotalPage();
		}
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecode() {
		return totalRecode;
	}

	public void setTotalRecode(int totalRecode) {
		this.totalRecode = totalRecode;
	}

	public int getTotalPage() {
		
		/**
		 * 总记录数 totalRecode   每页显示的条数pageSize   总页数totalPage
		 * 		10						4						3
		 * 		9						4						3
		 * 		8						4						2
		 * 
		 * totalRecode  %  pageSize==0?totalRecode  /  pageSize:totalRecode  /  pageSize+1;
		 * 
		 */
		if(getTotalRecode() % getPageSize() ==0){
			return getTotalRecode() / getPageSize();
		}else{
			return getTotalRecode() / getPageSize()+1;
		}
		
		
		
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getIndex() {
		
		/**
		 * pageSize    pageNum   index
		 * 	  4				1		0
		 * 	  4				2		4
		 * 	  4				3		8
		 * 
		 * (pageNum-1 ) *pageSize
		 * 
		 */
		
		
		
		
		return (getPageNum()-1)*getPageSize();
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<T> getDate() {
		return date;
	}

	public void setDate(List<T> date) {
		this.date = date;
	}
		
	
}
