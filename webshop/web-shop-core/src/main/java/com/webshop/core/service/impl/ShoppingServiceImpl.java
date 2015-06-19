/**
 * 
 */
package com.webshop.core.service.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.webshop.core.dao.ShoppingDAO;
import com.webshop.core.entity.Order;
import com.webshop.core.entity.OrderDetail;
import com.webshop.core.entity.Product;
import com.webshop.core.service.ShoppingService;

/**
 * @author speddyre
 * @dated 12th June 2015
 */
@Stateless
public class ShoppingServiceImpl implements ShoppingService
{

   @Inject
   private Logger logger;

   @Inject
   private ShoppingDAO shoppingDAO;

   /**
    * This method is for searching the products based on the search criteria
    * 
    * @see com.webshop.core.service.ShoppingService#searchProducts(int, java.lang.String)
    * @param int, String
    * @return List<Product>
    */
   public List<Product> searchProducts(int categoryId, String productDesc)
   {
      logger.info("*** In searchProducts in ServiceImpl");
      return shoppingDAO.searchProducts(categoryId, productDesc);
   }

   /**
    * This method is for creating the order (non-Javadoc)
    * 
    * @see com.webshop.core.service.ShoppingService#createOrder(com.webshop.core.entity.Order)
    * @param Order
    * @return boolean
    */
   public boolean createOrder(Order order)
   {
      logger.info("*** In createOrder in ServiceImpl");
      return shoppingDAO.createOrder(order);
   }

   /**
    * This method is for searching the Order details by OrderNo. (non-Javadoc)
    * 
    * @see com.webshop.core.service.ShoppingService#searchOrderDetailsByOrderNo(java.lang.String)
    * @param String
    * @return List<OrderDetail>
    */
   public List<OrderDetail> searchOrderDetailsByOrderNo(String orderNo)
   {
      logger.info("**** In searchOrderDetailsByOrderNo in ServiceImpl *****");
      return shoppingDAO.searchOrderDetailsByOrderNo(orderNo);
   }

   /**
    * This method is for searching the order by order no (non-Javadoc)
    * 
    * @see com.webshop.core.service.ShoppingService#searchOrderByOrderNo(java.lang.String)
    * @param String
    * @return List<Order>
    */
   public Order searchOrderByOrderNo(String orderNo)
   {
      logger.info("**** In searchOrderByOrderNo in ServiceImpl *****");
      return shoppingDAO.searchOrderByOrderNo(orderNo);
   }

}
