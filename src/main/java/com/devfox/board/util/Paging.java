package com.devfox.board.util;

public class Paging {
	private int pageSize;
	private int firstPageNo;
	private int prevPageNo;
	private int startPageNo;
	private int pageNo;
	private int endPageNo;
	private int nextPageNo;
	private int finalPageNo;
	private int totalCount;
	public int getPageSize() {
	    return pageSize;
	}
	public void setPageSize(int pageSize) {
	    this.pageSize = pageSize;
	}
	public int getFirstPageNo() {
	    return firstPageNo;
	}
	public void setFirstPageNo(int firstPageNo) {
	    this.firstPageNo = firstPageNo;
	}
	public int getPrevPageNo() {
	    return prevPageNo;
	}
	public void setPrevPageNo(int prevPageNo) {
	    this.prevPageNo = prevPageNo;
	}
	public int getStartPageNo() {
	    return startPageNo;
	}
	public void setStartPageNo(int startPageNo) {
	    this.startPageNo = startPageNo;
	}
	public int getPageNo() {
	    return pageNo;
	}
	public void setPageNo(int pageNo) {
	    this.pageNo = pageNo;
	}
	public int getEndPageNo() {
	    return endPageNo;
	}
	public void setEndPageNo(int endPageNo) {
	    this.endPageNo = endPageNo;
	}
	public int getNextPageNo() {
	    return nextPageNo;
	}
	public void setNextPageNo(int nextPageNo) {
	    this.nextPageNo = nextPageNo;
	}
	public int getFinalPageNo() {
	    return finalPageNo;
	}
	public void setFinalPageNo(int finalPageNo) {
	    this.finalPageNo = finalPageNo;
	}
	public int getTotalCount() {
	    return totalCount;
	}
	public void setTotalCount(int totalCount) {
	    this.totalCount = totalCount;
	    this.makePaging();
	}
	private void makePaging() {
	    if (this.totalCount == 0) return;
	    if (this.pageNo == 0) this.setPageNo(1);
	    if (this.pageSize == 0) this.setPageSize(10);
	    int finalPage = (totalCount + (pageSize - 1)) / pageSize;
	    if (this.pageNo > finalPage) this.setPageNo(finalPage);
	    if (this.pageNo < 0 || this.pageNo > finalPage) this.pageNo = 1;
	    boolean isNowFirst = pageNo == 1 ? true : false;
	    boolean isNowFinal = pageNo == finalPage ? true : false;
	    int startPage = ((pageNo - 1) / 10) * 10 + 1;
	    int endPage = startPage + 10 - 1;
	    if (endPage > finalPage) {
	        endPage = finalPage;
	    }
	    this.setFirstPageNo(1);
	    if (isNowFirst) {
	        this.setPrevPageNo(1);
	    } else {
	        this.setPrevPageNo(((pageNo - 1) < 1 ? 1 : (pageNo - 1)));
	    }
	    this.setStartPageNo(startPage);
	    this.setEndPageNo(endPage);
	
	    if (isNowFinal) {
	        this.setNextPageNo(finalPage);
	    } else {
	        this.setNextPageNo(((pageNo + 1) > finalPage ? finalPage : (pageNo + 1)));
	    }
	    this.setFinalPageNo(finalPage);
	}

	@Override
	public String toString() {
		return "Paging [pageSize=" + pageSize + ", firstPageNo=" + firstPageNo + ", prevPageNo=" + prevPageNo
				+ ", startPageNo=" + startPageNo + ", pageNo=" + pageNo + ", endPageNo=" + endPageNo + ", nextPageNo="
				+ nextPageNo + ", finalPageNo=" + finalPageNo + ", totalCount=" + totalCount + "]";
	}
}
