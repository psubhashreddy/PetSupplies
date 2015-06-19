/**
 * 
 */
package com.webshop.core.dao;

import java.util.List;

import com.webshop.core.entity.Product;

/**
 * This class is the dao invoked form the business implementation layer
 * 
 * @author speddyre
 * @date 3rd June 2015
 */
public interface ProductDAO
{

   String createProduct(Product product);

   boolean deleteProduct(Product product);

   boolean updateProduct(Product product);

   List<Product> getProductList();

   Product getProductbyProductId(int productId);

   Product getProductbyProductCode(String productCode);

   Product getProductByProductName(String productName);

   List<Product> getProductByDescription(String productDescription);

   List<Product> getProductByPrice(double productPrice);

   List<Product> getProductByCategoryId(long categoryId);

   List<Product> getProductByOrderId(long orderId);

   List<Product> searchProductsByCriteria(Product product);
}
