/**
 * 
 */
package com.webshop.core.dao;

import java.util.List;

import com.webshop.core.entity.Order;
import com.webshop.core.entity.OrderDetail;
import com.webshop.core.entity.Product;

/**
 * @author speddyre
 * @dated 12th June 2015
 *
 */
public interface ShoppingDAO {

	List<Product> searchProducts(int categoryId, String productDesc);

	boolean createOrder(Order order);

	Order searchOrderByOrderNo(String orderNo);

	List<OrderDetail> searchOrderDetailsByOrderNo(String orderNo);
	
}
