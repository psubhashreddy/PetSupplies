package com.webshop.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
 * The persistent class for the ORDERS database table.
 * 
 * @author speddyre
 * @date 31st May 2015
 */
@Entity
@NamedQueries({ @NamedQuery(name = "findAllOrders", query = "SELECT o FROM Order o"), @NamedQuery(name = "findOrderByOrderNo", query = "SELECT o FROM Order o WHERE o.orderNo = :orderno"),
      @NamedQuery(name = "findOrdersByPostalCode", query = "SELECT o FROM Order o WHERE o.shipPostalCode = :postalcode"),
      @NamedQuery(name = "findOrdersByUserPhone", query = "SELECT o FROM Order o WHERE o.shipPhone = :userphone"),
      @NamedQuery(name = "findOrdersByUserEmail", query = "SELECT o FROM Order o WHERE o.shipEmail = :useremail") })
@Table(name = "ORDERS")
public class Order implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ORDER_ID", unique = true, nullable = false)
   private int orderId;

   @Column(name = "ORDER_NO", nullable = false)
   private String orderNo;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = { CascadeType.PERSIST })
   private Set<OrderDetail> orderDetail = new HashSet<OrderDetail>();

   @Column(name = "SHIP_ADDRESS", nullable = false, length = 100)
   private String shipAddress;

   @Column(name = "SHIP_CITY", nullable = false, length = 50)
   private String shipCity;

   @Column(name = "SHIP_COUNTRY", nullable = false, length = 50)
   private String shipCountry;

   @Column(name = "SHIP_EMAIL", nullable = false, length = 30)
   private String shipEmail;

   @Column(name = "SHIP_NAME", nullable = false, length = 50)
   private String shipName;

   @Column(name = "SHIP_PHONE", nullable = false, length = 15)
   private String shipPhone;

   @Column(name = "SHIP_POSTAL_CODE", nullable = false, length = 10)
   private String shipPostalCode;

   @Column(name = "SHIP_STATE", nullable = false, length = 50)
   private String shipState;

   @Column(name = "SHIPPING_DATE", nullable = false)
   private Timestamp shippingDate;

   // Uni-directional many-to-one association to User
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "USER_ID", nullable = false)
   private User user;

   public Order()
   {
   }

   public int getOrderId()
   {
      return this.orderId;
   }

   public void setOrderId(int orderId)
   {
      this.orderId = orderId;
   }

   public String getOrderNo()
   {
      return orderNo;
   }

   public void setOrderNo(String orderNo)
   {
      this.orderNo = orderNo;
   }

   public Set<OrderDetail> getOrderDetail()
   {
      return orderDetail;
   }

   public void setOrderDetail(Set<OrderDetail> orderDetail)
   {
      this.orderDetail = orderDetail;
   }

   public String getShipAddress()
   {
      return this.shipAddress;
   }

   public void setShipAddress(String shipAddress)
   {
      this.shipAddress = shipAddress;
   }

   public String getShipCity()
   {
      return this.shipCity;
   }

   public void setShipCity(String shipCity)
   {
      this.shipCity = shipCity;
   }

   public String getShipCountry()
   {
      return this.shipCountry;
   }

   public void setShipCountry(String shipCountry)
   {
      this.shipCountry = shipCountry;
   }

   public String getShipEmail()
   {
      return this.shipEmail;
   }

   public void setShipEmail(String shipEmail)
   {
      this.shipEmail = shipEmail;
   }

   public String getShipName()
   {
      return this.shipName;
   }

   public void setShipName(String shipName)
   {
      this.shipName = shipName;
   }

   public String getShipPhone()
   {
      return this.shipPhone;
   }

   public void setShipPhone(String shipPhone)
   {
      this.shipPhone = shipPhone;
   }

   public String getShipPostalCode()
   {
      return this.shipPostalCode;
   }

   public void setShipPostalCode(String shipPostalCode)
   {
      this.shipPostalCode = shipPostalCode;
   }

   public String getShipState()
   {
      return this.shipState;
   }

   public void setShipState(String shipState)
   {
      this.shipState = shipState;
   }

   public Timestamp getShippingDate()
   {
      return this.shippingDate;
   }

   public void setShippingDate(Timestamp shippingDate)
   {
      this.shippingDate = shippingDate;
   }

   public User getUser()
   {
      return this.user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }

}