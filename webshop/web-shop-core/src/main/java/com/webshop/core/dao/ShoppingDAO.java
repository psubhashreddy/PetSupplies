/**
 * 
 */
package com.webshop.core.dao;

import java.util.List;

import com.webshop.core.entity.Product;

/**
 * @author speddyre
 * @dated 12th June 2015
 *
 */
public interface ShoppingDAO {

	List<Product> searchProducts(int categoryId, String productDesc);

}
