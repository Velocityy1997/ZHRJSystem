package com.web.common.util.spring;

import java.util.List;

public class PageTool<T> {

	private int total; //总页数
	private int page;  //页码
	private int records; //总数
	private List<T> rows; //数据集合
	
	public PageTool( int page, int records, List<T> rows,int pageSize) {
		super();
		this.total = getTotal(records,pageSize);
		this.page = page;
		this.records = records;
		this.rows = rows;
	}

//	public static PageTool getDatas(List<T> rows,int records,int pageSize,int page) {
//		PageTool pageTool = new PageTool();
//		pageTool.setRows(rows);
//		pageTool.setTotal(getTotal(records,pageSize));
//		pageTool.setPage(page);
//		pageTool.setRecords(records);
//		return pageTool;
//	}
	
	 public static int getTotal(int records,int pageSize) {
    	 int total = records/pageSize ;
    	 int other = records%pageSize;
    	if(other > 0 )
    		total++;
    	return total;
    }
	 
    public static void main(String[] args) {
		System.out.println( PageTool.getTotal(0, 10));
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

    
    
}
