/**
 * 
 */
package com.webshop.core.dao.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.webshop.core.dao.ProductDAO;
import com.webshop.core.entity.Product;
import com.webshop.core.utils.Constants;

/**
 * This class is the dao implementation class where all crud operations are performed
 * 
 * @author speddyre
 * @date 3rd June 2015
 */

public class ProductDAOImpl implements ProductDAO
{

   @PersistenceContext(unitName = "defaultPersistenceUnit")
   private EntityManager em;

   @Inject
   private transient Logger logger;

   /**
    * This method is for creating a product (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ProductDAO#createProduct(Product)
    * @param product
    * @return String
    */
   public String createProduct(Product product)
   {
      logger.info("**** In createProduct in DAOImpl *****");
      try
      {
         em.persist(product);
         return Constants.PRODUCT_CREATION_SUCCESS;
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in creating Product", e);
         return Constants.PRODUCT_CREATION_EXCEPTION;
      }
   }

   /**
    * This method is for deleting a product (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ProductDAO#deleteProduct(Product)
    * @param product
    * @return boolean
    */
   public boolean deleteProduct(Product product)
   {
      logger.info("**** In deleteProduct in DAOImpl *****");
      em.remove(product);
      return true;
   }

   /**
    * This method is for updating the product (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ProductDAO#updateProduct(com.webshop.core.entity.Product)
    * @param Product
    * @return boolean
    */
   public boolean updateProduct(Product product)
   {
      logger.info("**** In updateProduct in DAOImpl *****");
      em.merge(product);
      return true;
   }

   /**
    * This method is for fetching all the products (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ProductDAO#getProductList()
    * @return List
    */
   public List<Product> getProductList()
   {
      logger.info("**** In getProductList in DAOImpl *****");
      Query query = em.createNamedQuery("findAllProducts", Product.class);
      return (List<Product>) query.getResultList();
   }

   /**
    * This method is for fetching the product by product Id (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ProductDAO#getProductbyProductId(int)
    * @param int
    * @return Product
    */
   public Product getProductbyProductId(int productId)
   {
      logger.info("**** In getProductbyProductId in DAOImpl *****");
      try
      {
         Query query = em.createNamedQuery("findProductsById", Product.class).setParameter(Constants.PRODUCT_ID, productId);
         return (Product) query.getSingleResult();
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Searching Product by Id", e);
         return null;
      }
   }

   /**
    * This method is for searching product by product code (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ProductDAO#getProductbyProductId(String)
    * @param String
    * @return Product
    */
   public Product getProductbyProductCode(String productCode)
   {
      logger.info("**** In getProductbyProductCode in DAOImpl *****");
      try
      {
         Query query = em.createNamedQuery("findProductsByCode", Product.class).setParameter(Constants.PRODUCT_CODE, productCode);
         return (Product) query.getSingleResult();
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Searching Product by Code", e);
         return null;
      }
   }

   /**
    * This method is for searching products by productName (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ProductDAO#getProductByProductName(String)
    * @param String
    * @return Product
    */
   public Product getProductByProductName(String productName)
   {
      logger.info("**** In getProductByProductName in DAOImpl *****");
      try
      {
         Query query = em.createNamedQuery("findProductsByName", Product.class).setParameter(Constants.PRODUCT_NAME, productName);
         return (Product) query.getSingleResult();
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Searching Product By Name", e);
         return null;
      }
   }

   /**
    * This method is for searching products by description (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ProductDAO#getProductByDescription(String)
    * @param String
    * @return List
    */
   public List<Product> getProductByDescription(String productDescription)
   {
      logger.info("**** In getProductByDescription in DAOImpl *****");
      Query query = em.createNamedQuery("findProductsByDescription", Product.class).setParameter(Constants.PRODUCT_DESC, productDescription);
      return (List<Product>) query.getResultList();
   }

   /**
    * This method is for searching products by Price (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ProductDAO#getProductByPrice(double)
    * @param double
    * @return List
    */
   public List<Product> getProductByPrice(double productPrice)
   {
      logger.info("**** In getProductByPrice in DAOImpl *****");
      Query query = em.createNamedQuery("findProductsByPrice", Product.class).setParameter(Constants.PRODUCT_PRICE, productPrice);
      return (List<Product>) query.getResultList();
   }

   /**
    * This method is for searching products by category Id (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ProductDAO#getProductByCategoryId(long)
    * @param long
    * @return List
    */
   public List<Product> getProductByCategoryId(long categoryId)
   {
      logger.info("**** In getProductByCategoryId in DAOImpl *****");
      Query query = em.createNamedQuery("findProductsByCategoryId", Product.class).setParameter(Constants.CATEGORY_ID, categoryId);
      return (List<Product>) query.getResultList();
   }

   /**
    * This method is for searching products by order id (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ProductDAO#getProductByOrderId(long)
    * @param Long
    * @return List
    */
   public List<Product> getProductByOrderId(long orderId)
   {
      logger.info("**** In getProductByOrderId in DAOImpl *****");
      Query query = em.createNamedQuery("findProductsByOrderId", Product.class).setParameter("productorderId", orderId);
      return (List<Product>) query.getResultList();
   }

   /**
    * This method is for searching products by criteria (non-Javadoc)
    * 
    * @see com.webshop.core.dao.ProductDAO#searchProductsByCriteria(Product)
    * @param Product
    * @return List
    */
   public List<Product> searchProductsByCriteria(Product product)
   {
      logger.info("**** In searchProductsByCriteria in DAOImpl *****");
      Query query = em.createNamedQuery("findProductsByCriteria", Product.class).setParameter(Constants.PRODUCT_CODE, product.getProductCode())
            .setParameter(Constants.PRODUCT_NAME, product.getProductName());
      return (List<Product>) query.getResultList();
   }

}
