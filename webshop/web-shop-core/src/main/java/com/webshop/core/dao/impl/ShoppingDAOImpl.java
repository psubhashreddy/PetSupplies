/**
 * 
 */
package com.webshop.core.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.webshop.core.dao.ShoppingDAO;
import com.webshop.core.entity.Product;
import com.webshop.core.utils.Constants;

/**
 * @author speddyre
 * @dated 12th June 2015
 *
 */
public class ShoppingDAOImpl implements ShoppingDAO {

	@Inject
	private Logger logger;

	@PersistenceContext(unitName = "defaultPersistenceUnit")
	EntityManager em;

	/**
	 * This method is for searching the products based on the search criteria
	 * (non-Javadoc)
	 * 
	 * @see com.webshop.core.dao.ShoppingDAO#searchProducts(int,
	 *      java.lang.String)
	 * @param int, String
	 * @return List<Product>
	 */
	public List<Product> searchProducts(int categoryId, String productDesc) {
		logger.info("**** In searchProductsByCriteria in DAOImpl *****");
		logger.info("CategoryId=" + categoryId + " productDesc =" + productDesc);
		Query query = em
				.createNamedQuery("findProductsByCategory", Product.class)
				.setParameter("categoryid", categoryId)
				.setParameter(
						"productdesc",
						StringUtils.upperCase(Constants.PERCENTAGE
								+ productDesc + Constants.PERCENTAGE));
		return (List<Product>) query.getResultList();
	}

}
