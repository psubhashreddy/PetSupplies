/**
 * 
 */
package com.webshop.core.service.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.webshop.core.dao.ShoppingDAO;
import com.webshop.core.entity.Product;
import com.webshop.core.service.ShoppingService;

/**
 * @author speddyre
 * @dated 12th June 2015
 *
 */
public class ShoppingServiceImpl implements ShoppingService {

	@Inject
	private Logger logger;

	@Inject
	private ShoppingDAO shoppingDAO;

	/**
	 * This method is for searching the products based on the search criteria
	 * (non-Javadoc)
	 * 
	 * @see com.webshop.core.service.ShoppingService#searchProducts(int,
	 *      java.lang.String)
	 * @param int, String
	 * @return List<Product>
	 */
	public List<Product> searchProducts(int categoryId, String productDesc) {
		logger.info("*** In searchProducts in ServiceImpl");
		return shoppingDAO.searchProducts(categoryId, productDesc);
	}

}
