package com.webshop.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the CATEGORY database table.
 * 
 * @author speddyre
 * @date 31st May 2015
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "findAllCategories", query = "SELECT c FROM Category c"),
		@NamedQuery(name = "findCategoriesById", query = "SELECT c FROM Category c WHERE c.categoryId = :categoryid"),
		@NamedQuery(name = "findCategoriesByName", query = "SELECT c FROM Category c WHERE c.categoryName = :categoryname"),
		@NamedQuery(name = "findCategoriesByDescription", query = "SELECT c FROM Category c WHERE c.categoryDescription = :categorydesc"),
		@NamedQuery(name = "findCategoriesByCriteria", query = "SELECT c FROM Category c WHERE c.categoryName = :categoryname and c.categoryDescription = :categorydesc") })
@Table(name = "CATEGORY")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID", unique = true, nullable = false)
	private int categoryId;

	@Column(name = "CATEGORY_DESCRIPTION", nullable = true, length = 100)
	private String categoryDescription;

	@Column(name = "CATEGORY_NAME", nullable = false, length = 30)
	private String categoryName;

	@Column(name = "LAST_UPDATED_DATE", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp lastUpdatedDate;

	public Category() {
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryDescription() {
		return this.categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Timestamp getLastUpdatedDate() {
		return this.lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryDescription="
				+ categoryDescription + ", categoryName=" + categoryName
				+ ", lastUpdatedDate=" + lastUpdatedDate + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryName == null) ? 0 : categoryName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Category other = (Category) obj;
		if (categoryName == null) {
			if (other.categoryName != null) {
				return false;
			}
		} else if (!categoryName.equals(other.categoryName)) {
			return false;
		}
		return true;
	}

}