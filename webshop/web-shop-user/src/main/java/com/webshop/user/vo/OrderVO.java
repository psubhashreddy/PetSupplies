/**
 * 
 */
package com.webshop.user.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author speddyre
 * @date 12th June 2015
 *
 */
public class OrderVO {
	
	private List<CategoryVO> catList = new ArrayList<CategoryVO>();
	private List<ProductVO> prodList = new ArrayList<ProductVO>();
	private double totalAmt = 0.0;
	private Date orderDate;
	private String orderNum;
	private int totalQuantity = 0;
	/**
	 * @return the catList
	 */
	public List<CategoryVO> getCatList() {
		return catList;
	}
	/**
	 * @param catList the catList to set
	 */
	public void setCatList(List<CategoryVO> catList) {
		this.catList = catList;
	}
	/**
	 * @return the prodList
	 */
	public List<ProductVO> getProdList() {
		return prodList;
	}
	/**
	 * @param prodList the prodList to set
	 */
	public void setProdList(List<ProductVO> prodList) {
		this.prodList = prodList;
	}
	/**
	 * @return the totalAmt
	 */
	public double getTotalAmt() {
		return totalAmt;
	}
	/**
	 * @param totalAmt the totalAmt to set
	 */
	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}
	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the orderNum
	 */
	public String getOrderNum() {
		return orderNum;
	}
	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * @return the totalQuantity
	 */
	public int getTotalQuantity() {
		return totalQuantity;
	}
	/**
	 * @param totalQuantity the totalQuantity to set
	 */
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderVO [catList=" + catList + ", prodList=" + prodList
				+ ", totalAmt=" + totalAmt + ", orderDate=" + orderDate
				+ ", orderNum=" + orderNum + ", totalQuantity=" + totalQuantity
				+ "]";
	}
	
}
