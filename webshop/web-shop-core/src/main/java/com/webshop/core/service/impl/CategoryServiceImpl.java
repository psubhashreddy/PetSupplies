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

import com.webshop.core.dao.CategoryDAO;
import com.webshop.core.entity.Category;
import com.webshop.core.service.CategoryService;
import com.webshop.core.utils.Constants;
import com.webshop.core.utils.DateConverterUtil;

/**
 * This class is the implementation class for the business interface
 * 
 * @author speddyre
 * @date 3rd June 2015
 */
@Stateless
public class CategoryServiceImpl implements CategoryService {

	@Inject
	private CategoryDAO categoryDAO;

	@Inject
	private transient Logger logger;

	/**
	 * This method is for creating a category (non-Javadoc)
	 * 
	 * @see com.webshop.core.service.CategoryService#createCategory(com.webshop.core.entity.Category)
	 * @param Category
	 * @return String
	 */
	public String createCategory(Category category) {
		logger.info("*** In createCategory in ServiceImpl ***");
		if (categoryExistsByCategoryName(category).equalsIgnoreCase(
				Constants.CATEGORY_ALREADY_EXISTS)) {
			return Constants.CATEGORY_ALREADY_EXISTS;
		} else {
			category.setLastUpdatedDate(DateConverterUtil.dateToTimeStamp());
			return categoryDAO.createCategory(category);
		}
	}

	/**
	 * This method is a util method which does business validation for the
	 * existence of the category
	 * 
	 * @param Category
	 * @return String
	 */
	public String categoryExistsByCategoryName(Category category) {
		logger.info("*** In categoryExistsByCategoryName in ServiceImpl ***");
		Category result = null;
		if (category != null
				&& StringUtils.isNotEmpty(category.getCategoryName())) {
			result = categoryDAO.getCategoryByCategoryName(category
					.getCategoryName());
			if (result != null) {
				return Constants.CATEGORY_ALREADY_EXISTS;
			}
		}
		return Constants.FAILURE;
	}

	/**
	 * This method is for deleting the category (non-Javadoc)
	 * 
	 * @see com.webshop.core.service.CategoryService#deleteCategory(com.webshop.core.entity.Category)
	 * @param Category
	 *            \
	 * @return boolean
	 */
	public boolean deleteCategory(int categoryId) {
		logger.info("*** In deleteCategory in ServiceImpl ***");
		return categoryDAO.deleteCategory(categoryId);
	}

	/**
	 * This method is for updating the Category (non-Javadoc)
	 * 
	 * @see com.webshop.core.service.CategoryService#updateCategory(com.webshop.core.entity.Category)
	 * @param category
	 * @return boolean
	 */
	public boolean updateCategory(Category category) {
		logger.info("*** In updateCategory in ServiceImpl ***");
		category.setLastUpdatedDate(DateConverterUtil.dateToTimeStamp());
		return categoryDAO.updateCategory(category);
	}

	/**
	 * This method is for fetching all the categories (non-Javadoc)
	 * 
	 * @see com.webshop.core.service.CategoryService#getCategories()
	 * @return List
	 */
	public List<Category> getCategories() {
		logger.info("*** In getCategories in ServiceImpl ***");
		return categoryDAO.getCategories();
	}

	/**
	 * This method is for searching categories by category id (non-Javadoc)
	 * 
	 * @see com.webshop.core.service.CategoryService#getCategoryByCategoryId(int)
	 * @param int
	 * @return List
	 */
	public Category getCategoryByCategoryId(int categoryId) {
		logger.info("*** In getCategoryByCategoryId in ServiceImpl ***");
		return categoryDAO.getCategoryByCategoryId(categoryId);
	}

	/**
	 * This method is for searching the categories by criteria (non-Javadoc)
	 * 
	 * @see com.webshop.core.service.CategoryService#searchCategoriesByCriteria(com.webshop.core.entity.Category)
	 * @param Category
	 * @return List
	 */
	public List<Category> searchCategoriesByCriteria(Category category) {
		logger.info("*** In searchCategoriesByCriteria in ServiceImpl ***");
		try {
			List<Category> categoryList = new ArrayList<Category>();
			Category result = null;
			if (StringUtils.isNotEmpty(category.getCategoryName())
					&& StringUtils.isEmpty(category.getCategoryDescription())) {
				result = categoryDAO.getCategoryByCategoryName(category
						.getCategoryName());
				if (result != null) {
					categoryList.add(result);
				}
			} else if (StringUtils.isEmpty(category.getCategoryName())
					&& StringUtils
							.isNotEmpty(category.getCategoryDescription())) {
				result = categoryDAO.getCategoryByCategoryDescription(category
						.getCategoryDescription());
				categoryList.add(result);
			} else {
				categoryList = categoryDAO.searchCategoriesByCriteria(category);
			}
			logger.info("Size of the list = " + categoryList.size());
			return categoryList;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in Searching Category", e);
			return new ArrayList<Category>();
		}
	}

}
