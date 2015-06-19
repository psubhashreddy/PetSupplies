/**
 * 
 */
package com.webshop.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.webshop.core.dao.ProductDAO;
import com.webshop.core.entity.Product;
import com.webshop.core.service.ProductService;
import com.webshop.core.utils.Constants;
import com.webshop.core.utils.DateConverterUtil;

/**
 * This class is the implementation class for the business interface
 * 
 * @author speddyre
 * @date 3rd June 2015
 */
@Stateless
public class ProductServiceImpl implements ProductService
{

   @Inject
   private ProductDAO productDAO;

   @Inject
   private transient Logger logger;

   /**
    * This method is for creating a product (non-Javadoc)
    * 
    * @see com.webshop.core.service.ProductService#createProduct(com.webshop.core.entity.Product)
    * @param Product
    * @return String
    */
   public String createProduct(Product product)
   {
      logger.info("*** In createProduct in ServiceImpl ****");
      if (productExistsByProductCode(product).equalsIgnoreCase(Constants.PRODUCT_ALREADY_EXISTS) || productExistsByProductName(product).equalsIgnoreCase(Constants.PRODUCT_ALREADY_EXISTS))
      {
         return Constants.PRODUCT_ALREADY_EXISTS;
      }
      else
      {
         product.setLastUpdatedDate(DateConverterUtil.dateToTimeStamp());
         return productDAO.createProduct(product);
      }
   }

   /**
    * This method is a utiltiy for validating the product existence by product code
    * 
    * @param product
    * @return String
    */
   public String productExistsByProductCode(Product product)
   {
      logger.info("*** In productExistsByProductCode in ServiceImpl ****");
      String returnStr = Constants.FAILURE;
      Product result = null;
      if (product != null && StringUtils.isNotEmpty(product.getProductCode()))
      {
         result = productDAO.getProductbyProductCode(product.getProductCode());
         if (result != null)
         {
            returnStr = Constants.PRODUCT_ALREADY_EXISTS;
         }
      }
      return returnStr;
   }

   /**
    * This method is a utlity method for validating the product existence by product name
    * 
    * @param product
    * @return String
    */
   public String productExistsByProductName(Product product)
   {
      logger.info("*** In productExistsByProductName in ServiceImpl ****");
      String returnStr = Constants.FAILURE;
      Product result = null;
      if (product != null && StringUtils.isNotEmpty(product.getProductName()))
      {
         result = productDAO.getProductByProductName(product.getProductName());
         if (result != null)
         {
            returnStr = Constants.PRODUCT_ALREADY_EXISTS;
         }
      }
      return returnStr;
   }

   /**
    * This method is for deleting the product (non-Javadoc)
    * 
    * @see com.webshop.core.service.ProductService#deleteProduct(com.webshop.core.entity.Product)
    * @param Product
    * @return boolean
    */
   public boolean deleteProduct(int productId)
   {
      logger.info("*** In deleteProduct in ServiceImpl ****");
      Product product = getProductbyProductId(productId);
      return productDAO.deleteProduct(product);
   }

   /**
    * This method is for updating the product (non-Javadoc)
    * 
    * @see com.webshop.core.service.ProductService#updateProduct(com.webshop.core.entity.Product)
    * @param Product
    * @return boolean
    */
   public boolean updateProduct(Product product)
   {
      logger.info("*** In updateProduct in ServiceImpl ****");
      product.setLastUpdatedDate(DateConverterUtil.dateToTimeStamp());
      return productDAO.updateProduct(product);
   }

   /**
    * This method is for fetching the product by product Id (non-Javadoc)
    * 
    * @see com.webshop.core.service.ProductService#getProductbyProductId(int)
    * @param int
    * @return Product
    */
   public Product getProductbyProductId(int productId)
   {
      logger.info("*** In getProductbyProductId in ServiceImpl ****");
      return productDAO.getProductbyProductId(productId);
   }

   /**
    * This method is for fetching all the products (non-Javadoc)
    * 
    * @see com.webshop.core.service.ProductService#getProductList()
    * @return List
    */
   public List<Product> getProductList()
   {
      logger.info("*** In getProductList in ServiceImpl ****");
      return productDAO.getProductList();
   }

   /**
    * This method is for searching products by criteria (non-Javadoc)
    * 
    * @see com.webshop.core.service.ProductService#searchProductsByCriteria(com.webshop.core.entity.Product)
    * @param Product
    * @return List
    */
   public List<Product> searchProductsByCriteria(Product product)
   {
      logger.info("*** In searchProductsByCriteria in ServiceImpl ****");
      try
      {
         List<Product> productList = new ArrayList<Product>();
         Product result = null;
         if (StringUtils.isNotEmpty(product.getProductCode()) && StringUtils.isEmpty(product.getProductName()))
         {
            result = productDAO.getProductbyProductCode(product.getProductCode());
            if (result != null)
            {
               productList.add(result);
            }
         }
         else if (StringUtils.isEmpty(product.getProductCode()) && StringUtils.isNotEmpty(product.getProductName()))
         {
            result = productDAO.getProductByProductName(product.getProductName());
            if (result != null)
            {
               productList.add(result);
            }
         }
         else
         {
            productList = productDAO.searchProductsByCriteria(product);
         }
         logger.info("Size of the list = " + productList.size());
         return productList;
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Searching Product", e);
         return new ArrayList<Product>();
      }
   }

}
