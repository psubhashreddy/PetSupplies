/**
 * 
 */
package com.webshop.core.service;

import java.util.List;

import com.webshop.core.entity.Order;
import com.webshop.core.entity.OrderDetail;
import com.webshop.core.entity.Product;

/**
 * @author speddyre
 * @dated 12th June 2015
 */
public interface ShoppingService
{

   List<Product> searchProducts(int categoryId, String productDesc);

   boolean createOrder(Order order);

   List<OrderDetail> searchOrderDetailsByOrderNo(String orderNo);

   Order searchOrderByOrderNo(String orderNo);
}
