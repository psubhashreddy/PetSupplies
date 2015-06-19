/**
 * 
 */
package com.webshop.core.dao;

import java.util.List;

import com.webshop.core.entity.Category;

/**
 * This class is the dao invoked form the business implementation layer
 * 
 * @author speddyre
 * @date 3rd June 2015
 */
public interface CategoryDAO
{

   String createCategory(Category category);

   boolean deleteCategory(int categoryId);

   boolean updateCategory(Category category);

   List<Category> getCategories();

   Category getCategoryByCategoryId(int categoryId);

   Category getCategoryByCategoryName(String categoryName);

   Category getCategoryByCategoryDescription(String categoryDescription);

   List<Category> searchCategoriesByCriteria(Category category);

}
