package com.webshop.core.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the PRODUCT database table.
 * 
 * @author speddyre
 * @date 31st May 2015
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllProducts", query = "SELECT p FROM Product p"),
		@NamedQuery(name = "findProductsById", query = "SELECT p FROM Product p WHERE p.productId = :productid"),
		@NamedQuery(name = "findProductsByCode", query = "SELECT p FROM Product p WHERE p.productCode = :productcode"),
		@NamedQuery(name = "findProductsByName", query = "SELECT p FROM Product p WHERE p.productName = :productname"),
		@NamedQuery(name = "findProductsByDescription", query = "SELECT p FROM Product p WHERE p.productDescription = :productdesc"),
		@NamedQuery(name = "findProductsByPrice", query = "SELECT p FROM Product p WHERE p.productPrice = :productprice"),
		@NamedQuery(name = "findProductsByCategoryId", query = "SELECT p FROM Product p WHERE p.category.categoryId = :categoryid"),
		@NamedQuery(name = "findProductsByOrderId", query = "SELECT p FROM Product p WHERE p.orderDetailsOrderId = :productorderId"),
		@NamedQuery(name = "findProductsByCriteria", query = "SELECT p FROM Product p WHERE p.productCode = :productcode and p.productName = :productname"),
		@NamedQuery(name = "findProductsByCategory", query = "SELECT p FROM Product p WHERE p.category.categoryId = :categoryid and UPPER(p.productDescription) like :productdesc")

})
@Table(name = "PRODUCT")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID", unique = true, nullable = false)
	private int productId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	private Category category;

	@Column(name = "IMAGE", nullable = true)
	private Blob image;

	@Column(name = "LAST_UPDATED_DATE", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp lastUpdatedDate;

	@Column(name = "ORDER_DETAILS_ORDER_ID", nullable = true)
	private int orderDetailsOrderId;

	@Column(name = "PRODUCT_CODE", nullable = false, length = 10)
	private String productCode;

	@Column(name = "PRODUCT_DESCRIPTION", nullable = true, length = 100)
	private String productDescription;

	@Column(name = "PRODUCT_NAME", nullable = false, length = 50)
	private String productName;

	@Column(name = "PRODUCT_PRICE", nullable = false, precision = 10, scale = 2)
	private Double productPrice;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Timestamp getLastUpdatedDate() {
		return this.lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public int getOrderDetailsOrderId() {
		return this.orderDetailsOrderId;
	}

	public void setOrderDetailsOrderId(int orderDetailsOrderId) {
		this.orderDetailsOrderId = orderDetailsOrderId;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", category=" + category
				+ ", image=" + image + ", lastUpdatedDate=" + lastUpdatedDate
				+ ", orderDetailsOrderId=" + orderDetailsOrderId
				+ ", productCode=" + productCode + ", productDescription="
				+ productDescription + ", productName=" + productName
				+ ", productPrice=" + productPrice + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productCode == null) ? 0 : productCode.hashCode());
		return result;
	}

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
		Product other = (Product) obj;
		if (productCode == null) {
			if (other.productCode != null) {
				return false;
			}
		} else if (!productCode.equals(other.productCode)) {
			return false;
		}
		return true;
	}

}