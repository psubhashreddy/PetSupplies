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

import com.webshop.core.dao.CategoryDAO;
import com.webshop.core.entity.Category;
import com.webshop.core.utils.Constants;

/**
 * This class is the dao implementation class for the category entity
 * 
 * @author speddyre
 * @date 3rd June 2015
 */
public class CategoryDAOImpl implements CategoryDAO {

	@PersistenceContext(unitName = "defaultPersistenceUnit")
	private EntityManager em;

	@Inject
	private transient Logger logger;

	/**
	 * This method is for creating a category (non-Javadoc)
	 * 
	 * @see com.webshop.core.dao.CategoryDAO#createCategory(com.webshop.core.entity.Category)
	 * @param category
	 * @return String
	 */
	public String createCategory(Category category) {
		logger.info("**** In createCategory in DAOImpl *****");
		try {
			em.persist(category);
			return Constants.CATEGORY_CREATION_SUCCESS;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in creating Category", e);
			return Constants.CATEGORY_CREATION_EXCEPTION;
		}
	}

	/**
	 * This method is for deleting the category (non-Javadoc)
	 * 
	 * @see com.webshop.core.dao.CategoryDAO#deleteCategory(Cateogry)
	 * @param category
	 * @return boolean
	 */
	public boolean deleteCategory(int categoryId) {
		logger.info("**** In deleteCategory in DAOImpl *****");
		Category category = getCategoryByCategoryId(categoryId);
		em.remove(category);
		return true;
	}

	/**
	 * This method is for updating the category (non-Javadoc)
	 * 
	 * @see com.webshop.core.dao.CategoryDAO#updateCategory(com.webshop.core.entity.Category)
	 * @param category
	 * @return boolean
	 */
	public boolean updateCategory(Category category) {
		logger.info("**** In updateCategory in DAOImpl *****");
		em.merge(category);
		return true;
	}

	/**
	 * This method is for fetching all the categories (non-Javadoc)
	 * 
	 * @see com.webshop.core.dao.CategoryDAO#getCategories()
	 * @return List
	 */
	public List<Category> getCategories() {
		logger.info("**** In getCategories in DAOImpl *****");
		Query query = em.createNamedQuery("findAllCategories", Category.class);
		return (List<Category>) query.getResultList();
	}

	/**
	 * This method is for searching categories by category id (non-Javadoc)
	 * 
	 * @see com.webshop.core.dao.CategoryDAO#getCategoryByCategoryId(long)
	 * @param long
	 * @return list
	 */
	public Category getCategoryByCategoryId(int categoryId) {
		logger.info("**** In getCategoryByCategoryId in DAOImpl *****");
		Query query = em.createNamedQuery("findCategoriesById", Category.class)
				.setParameter(Constants.CATEGORY_ID, categoryId);
		return (Category) query.getSingleResult();
	}

	/**
	 * This method is for searching categories by category name (non-Javadoc)
	 * 
	 * @see com.webshop.core.dao.CategoryDAO#getCategoryByCategoryName(String)
	 * @param String
	 * @return list
	 */
	public Category getCategoryByCategoryName(String categoryName) {
		logger.info("**** In getCategoryByCategoryName in DAOImpl *****");
		try {
			Query query = em.createNamedQuery("findCategoriesByName",
					Category.class).setParameter(Constants.CATEGORY_NAME, categoryName);
			return (Category) query.getSingleResult();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in getCategoryByCategoryName ", e);
			return null;
		}
	}

	/**
	 * This method is for searching categories by category description
	 * (non-Javadoc)
	 * 
	 * @see com.webshop.core.dao.CategoryDAO#getCategoryByCategoryDescription(String)
	 * @param String
	 * @return list
	 */
	public Category getCategoryByCategoryDescription(String categoryDescription) {
		logger.info("**** In getCategoryByCategoryDescription in DAOImpl *****");
		try {
			Query query = em.createNamedQuery("findCategoriesByDescription",
					Category.class).setParameter(Constants.CATEGORY_DESC,
					categoryDescription);
			return (Category) query.getSingleResult();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in getCategoryByCategoryDescription", e);
			return null;
		}
	}

	/**
	 * This method is for searching categories by criteria (non-Javadoc)
	 * 
	 * @see com.webshop.core.dao.CategoryDAO#searchCategoriesByCriteria(Category)
	 * @param Category
	 * @return List
	 */
	public List<Category> searchCategoriesByCriteria(Category category) {
		logger.info("**** In searchCategoriesByCriteria in DAOImpl *****");
		Query query = em
				.createNamedQuery("findCategoriesByCriteria", Category.class)
				.setParameter(Constants.CATEGORY_NAME, category.getCategoryName())
				.setParameter(Constants.CATEGORY_DESC, category.getCategoryDescription());
		return (List<Category>) query.getResultList();
	}

}
