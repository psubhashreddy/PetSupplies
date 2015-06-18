/**
 * 
 */
package com.webshop.user.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.webshop.core.entity.User;

/**
 * @author speddyre
 * @date 12th June 2015
 *
 */
public class OrderVO {
	
	private List<ProductVO> prodList = new ArrayList<ProductVO>();
	private User user = new User();
	private double totalAmt = 0.0;
	private Date orderDate;
	private String orderNum;
	private int totalQuantity = 0;
	private String status;

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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderVO [prodList=" + prodList + ", user=" + user
				+ ", totalAmt=" + totalAmt + ", orderDate=" + orderDate
				+ ", orderNum=" + orderNum + ", totalQuantity=" + totalQuantity
				+ "]";
	}
	
	
	
}
