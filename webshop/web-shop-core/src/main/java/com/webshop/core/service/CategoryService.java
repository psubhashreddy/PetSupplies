/**
 * 
 */
package com.webshop.core.service;

import java.util.List;

import com.webshop.core.entity.Category;

/**
 * This class is an interface for the business service implementation class
 * 
 * @author speddyre
 * @date 3rd June 2015
 */
public interface CategoryService {

	String createCategory(Category category);

	boolean deleteCategory(int categoryId);

	boolean updateCategory(Category category);

	List<Category> getCategories();

	Category getCategoryByCategoryId(int categoryId);

	List<Category> searchCategoriesByCriteria(Category category);

}
