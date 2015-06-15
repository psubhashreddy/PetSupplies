/**
 * 
 */
package com.webshop.user.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author speddyre
 * @date 12th June 2015
 *
 */
public class CategoryVO {
	
	private int categoryId;
	private String categoryName;
	private String categoryDesc;
	
	private List<ProductVO> productList = new ArrayList<ProductVO>();

	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the categoryDesc
	 */
	public String getCategoryDesc() {
		return categoryDesc;
	}

	/**
	 * @param categoryDesc the categoryDesc to set
	 */
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	/**
	 * @return the productSet
	 */
	public List<ProductVO> getProductList() {
		return productList;
	}

	/**
	 * @param productSet the productSet to set
	 */
	public void setProductList(List<ProductVO> productList) {
		this.productList = productList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CategoryVO [categoryId=" + categoryId + ", categoryName="
				+ categoryName + ", categoryDesc=" + categoryDesc
				+ ", productSet=" + productList + "]";
	}
	
}
