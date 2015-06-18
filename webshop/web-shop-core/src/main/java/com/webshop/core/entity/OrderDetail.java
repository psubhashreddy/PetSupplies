package com.webshop.core.entity;

import java.io.Serializable;

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
import javax.persistence.Table;

/**
 * The persistent class for the ORDER_DETAILS database table.
 * 
 * @author speddyre
 * @date 31st May 2015
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "findAllOrderDetails", query = "SELECT o FROM OrderDetail o"), 
	@NamedQuery(name = "findOrderDetailByOrderNo", query = "SELECT o FROM OrderDetail o where o.order.orderNo = :orderNo")
	})
@Table(name = "ORDER_DETAILS")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DETAIL_ID", unique = true, nullable = false)
	private int detailId;

	@Column(name = "ORDER_COST", nullable = false)
	private double orderCost;

	@Column(name = "ORDER_DISCOUNT", nullable = false)
	private double orderDiscount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORDER_NO", nullable = false)
	private Order order;

	@Column(name = "ORDER_QUANTITY", nullable = false)
	private int orderQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product product;

	public OrderDetail() {
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public double getOrderCost() {
		return this.orderCost;
	}

	public void setOrderCost(double orderCost) {
		this.orderCost = orderCost;
	}

	public double getOrderDiscount() {
		return this.orderDiscount;
	}

	public void setOrderDiscount(double orderDiscount) {
		this.orderDiscount = orderDiscount;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
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

	public void setProduct(Product product) {
		this.product = product;
	}

}