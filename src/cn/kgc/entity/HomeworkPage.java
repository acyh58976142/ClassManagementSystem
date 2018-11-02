package cn.kgc.entity;

import java.util.List;

public class HomeworkPage<T> {

	//��ǰҳ
	private int pageNum;
	//ÿҳ��ʾ������
	private int pageSize;
	
	//�ܼ�¼��
	private int totalRecode;
	//��ҳ��
	private int totalPage;
	//����
	private int index;
	
	//���ݵļ���
	private List<T> date;

	public int getPageNum() {
		/**
		 * ���ָ����ҳ��С��1��Ĭ���ڵ�һҳ
		 * �����������ҳ��Ĭ�������һҳ
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
		 * �ܼ�¼�� totalRecode   ÿҳ��ʾ������pageSize   ��ҳ��totalPage
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
