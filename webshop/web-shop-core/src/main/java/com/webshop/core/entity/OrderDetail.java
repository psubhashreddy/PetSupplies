package com.webshop.core.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
 * The persistent class for the ORDER_DETAILS database table.
 * 
 * @author speddyre
 * @date 31st May 2015
 */
@Entity
@NamedQueries({ @NamedQuery(name = "OrderDetail.findAll", query = "SELECT o FROM OrderDetail o") })
@Table(name = "ORDER_DETAILS")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DETAIL_ID", unique = true, nullable = false)
	private int detailId;

	@Column(name = "ORDER_COST", nullable = false)
	private int orderCost;

	@Column(name = "ORDER_DISCOUNT", nullable = false)
	private int orderDiscount;

	@OneToMany(mappedBy = "orderDetailsOrder", cascade = { CascadeType.PERSIST })
	private Set<Order> order = new HashSet<Order>();

	@Column(name = "ORDER_QUANTITY", nullable = false)
	private int orderQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product product;

	public OrderDetail() {
	}

	/**
	 * @return the detailId
	 */
	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public int getOrderCost() {
		return this.orderCost;
	}

	public void setOrderCost(int orderCost) {
		this.orderCost = orderCost;
	}

	public int getOrderDiscount() {
		return this.orderDiscount;
	}

	public void setOrderDiscount(int orderDiscount) {
		this.orderDiscount = orderDiscount;
	}

	public Set<Order> getOrder() {
		return this.order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

	public int getOrderQuantity() {
		return this.orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProductId(Product product) {
		this.product = product;
	}

}