package cn.kgc.entity;

import java.util.List;
import java.util.Map;

public class Page<T> {
	 
		private int pageNum;        //��ǰҳ
		private int pageSize;       //ÿҳ��ʾ������
		private int totalRecode;    //�ܼ�¼��
		private int totalPage;      //��ҳ��
		private int index;          //����
		private List<T> date;       //��ǰ�ļ���
		
		private List<Map<String,Object>> dates;  //��ǰ���ϵ�map����
        
		public List<Map<String, Object>> getDates() {
			return dates;
		}
		public void setDates(List<Map<String, Object>> dates) {
			this.dates = dates;
		}
		public int getPageNum() {
			/*
			 * ���ָ����ҳ��С��1��Ĭ���ڵ�һҳ
			 */
			if (pageNum<1) {
				return 1;
			}
			if (pageNum>getTotalPage()) {
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
			/*
			 * �ܼ�¼��totalRecode   ÿҳ��ʾ������pageSize     ��ҳ��totalPage
			 * 
			 * totalRecode     %   pageSize==0��       totalRecode/pageSize :   (totalRecode/pageSize)+(totalRecode     %   pageSize)
			 */ 
			if(getTotalRecode() % getPageSize()==0){
				return  getTotalRecode()/getPageSize();				
			}else{
				return  getTotalRecode()/getPageSize() + 1;
			}
			
		}
		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}
		public int getIndex() {
			/*
			 * pageSize      pageNum        index
			 *   4              1             0
			 *   4              2             4
			 *   4              3             8
			 *   (pageNum-1)*pageSize
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
