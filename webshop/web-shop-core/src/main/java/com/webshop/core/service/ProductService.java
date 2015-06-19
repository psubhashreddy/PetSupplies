/**
 * 
 */
package com.webshop.core.service;

import java.util.List;

import com.webshop.core.entity.Product;

/**
 * This class is an interface for the business service implementation class
 * 
 * @author speddyre
 * @date 3rd June 2015
 */
public interface ProductService
{

   String createProduct(Product product);

   boolean deleteProduct(int product);

   boolean updateProduct(Product product);

   List<Product> getProductList();

   Product getProductbyProductId(int productId);

   List<Product> searchProductsByCriteria(Product product);

}
