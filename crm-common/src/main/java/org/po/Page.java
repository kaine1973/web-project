package org.po;

import java.util.List;

/**
 * 分页信息
 * 需要外界给定 总记录数， 固定好每一页的记录数， 当前页传入
 * 
 * 总页数， 是否有下一页， 是否有上一页， 
 * 
 * @author Administrator
 *
 */
public class Page<T> {
	//1、总记录数    需要外界传入的
	private int totalRecords;
	//2、每页记录数
	private int pageSize =10;
	//3、总页数
	private int totalPage;
	//4、当前页 外界传入的
	private int currentPage;
	//5、显示页码总数
	private int barSize=9;
	//6、起始页
	private int startPage;
	//7、结束页
	private int endPage;
	//8、是否存在上一页
	private boolean hasPre;
	//9、是否存在下一页
	private boolean hasNext;
	//10、起始位置
	private int start;
	//11、记录
	private List<T> datas;
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
		if(totalRecords<=0){
			return ;
		}
		//计算总页数
		totalPage =(totalRecords+pageSize-1)/pageSize;
		
		//totalPage = totalRecords/pageSize + (totalRecords%pageSize==0?0:1);
		
		
		//修正显示页码总数
		barSize=barSize>totalPage?totalPage:barSize;	
		
		//修正当前页
		currentPage =currentPage<1?1:currentPage;
		currentPage =currentPage>totalPage?totalPage:currentPage;
		
		//起始页与结束页
		if(currentPage<=barSize/2){
			startPage=1;
			endPage =barSize;
		}else if(currentPage+barSize/2>=totalPage){
			endPage =totalPage;
			startPage =totalPage-barSize+1;
		}else{ 
			startPage =currentPage-barSize/2;
			endPage =currentPage+barSize/2;
		}
		//避免偶数的情况  显示2页 ，2 -->1、2    or     2-->2、3  
		if(endPage-startPage>=barSize){
			startPage+=1;
		}
		//上一页下一页
		hasPre= (currentPage!=1);
		hasNext = (currentPage!=totalPage);
		
		//起始索引
		start =(currentPage-1)*pageSize;		
	
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isHasPre() {
		return hasPre;
	}
	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}	
	
	
	public static void main(String[] args) {
		//总记录数
		int totalRecords= 17;
		//每页记录数
		int pageSize =3;
		//当前页
		int currentPage =2;
		//显示页码数
		int barSize =2;
		
		//1、总页数
		//int totalPage =(int)Math.ceil(totalRecords*1.0/pageSize);
		int totalPage =(totalRecords+pageSize-1)/pageSize;
		System.out.println(totalPage);
		
		//修正显示页码总数
		barSize=barSize>totalPage?totalPage:barSize;	
		
		//2、修正当前页
		currentPage =currentPage<1?1:currentPage;
		currentPage =currentPage>totalPage?totalPage:currentPage;
		
		//3、起始页与结束页
		int startPage =1;
		int endPage =1;
		
		//====5========
		// 1  -->1  5
		// 2  -->1  5
		// 3  -->1  5
		// 4  -->23456
		// 5  -->23456
		// 6  -->23456		
		//====6==========
		//1 -->1 2 3 4 5 6
		//2 -->123456
		//3 -->123456
		//4 -->123456
		//5 -->123456
		//6 -->123456
		if(currentPage<=barSize/2){
			startPage=1;
			endPage =barSize;
		}else if(currentPage+barSize/2>=totalPage){
			endPage =totalPage;
			startPage =totalPage-barSize+1;
		}else{ 
			startPage =currentPage-barSize/2;
			endPage =currentPage+barSize/2;
		}
		if(endPage-startPage>=barSize){
			startPage+=1;
		}
		System.out.println(startPage+"-->"+currentPage+"-->"+endPage);
		
		//3、上一页下一页
		boolean hasPre= (currentPage!=1);
		boolean hasNext = (currentPage!=totalPage);
		
		//4、起始索引
		int start =(currentPage-1)*pageSize;
		
		
		
		
	}
}
