/**
 * 
 */
package com.webshop.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.webshop.core.dao.ShoppingDAO;
import com.webshop.core.entity.Order;
import com.webshop.core.entity.OrderDetail;
import com.webshop.core.entity.Product;
import com.webshop.core.utils.Constants;

/**
 * @author speddyre
 * @dated 12th June 2015
 */
public class ShoppingDAOImpl implements ShoppingDAO
{

   @PersistenceContext(unitName = "defaultPersistenceUnit")
   EntityManager em;

   @Inject
   private Logger logger;

   /**
    * This method is for searching the products based on the search criteria (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ShoppingDAO#searchProducts(int, java.lang.String)
    * @param int, String
    * @return List<Product>
    */
   public List<Product> searchProducts(int categoryId, String productDesc)
   {
      logger.info("**** In searchProductsByCriteria in DAOImpl *****");
      Query query = em.createNamedQuery("findProductsByCategory", Product.class).setParameter("categoryid", categoryId)
            .setParameter("productdesc", StringUtils.upperCase(Constants.PERCENTAGE + productDesc + Constants.PERCENTAGE));
      return (List<Product>) query.getResultList();
   }

   /**
    * This method is for creating the shopping order (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ShoppingDAO#createOrder(com.webshop.core.entity.Order)
    * @param Order
    * @return boolean
    */
   public boolean createOrder(Order order)
   {
      logger.info("**** In createOrder in DAOImpl *****");
      em.persist(order);
      return true;
   }

   /**
    * This method is for searching the order by Order No (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ShoppingDAO#searchOrderByOrderNo(java.lang.String)
    * @param String
    * @return Order
    */
   public Order searchOrderByOrderNo(String orderNo)
   {
      logger.info("**** In searchOrderByOrderNo in DAOImpl *****");
      try
      {
         Query query = em.createNamedQuery("findOrderByOrderNo", Order.class).setParameter(Constants.ORDER_NO, orderNo);
         return (Order) query.getSingleResult();
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in findOrderByOrderNo ", e);
         return null;
      }

   }

   /**
    * This method is for searching the order details by order no. (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ShoppingDAO#searchOrderDetailsByOrderNo(java.lang.String)
    * @param String
    * @return List<OrderDetail>
    */
   public List<OrderDetail> searchOrderDetailsByOrderNo(String orderNo)
   {
      logger.info("**** In searchOrderDetailsbyOrderNo in DAOImpl *****");
      try
      {
         Query query = em.createNamedQuery("findOrderDetailByOrderNo", OrderDetail.class).setParameter(Constants.ORDER_NO, orderNo);
         return (List<OrderDetail>) query.getResultList();
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in searchOrderDetailsbyOrderNo ", e);
         return new ArrayList<OrderDetail>();
      }
   }

}
