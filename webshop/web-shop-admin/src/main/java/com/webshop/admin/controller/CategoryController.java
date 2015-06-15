package com.webshop.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.webshop.core.entity.Category;
import com.webshop.core.service.CategoryService;
import com.webshop.core.utils.Constants;

/**
 * This class is the controller for doing for doing all the crud operations on
 * the categories 
 * 
 * @see
 * @author speddyre
 * @date 3rd June 2015
 */
@ManagedBean(name = "categoryController")
@RequestScoped
public class CategoryController {

	@Inject
	private CategoryService categoryService;

	@Inject
	private transient Logger logger;

	private Category category = new Category();
	private List<Category> categoryList = new ArrayList<Category>();
	private int categoryId;

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 * 
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the categoryList
	 */
	public List<Category> getCategoryList() {
		return categoryList;
	}

	/**
	 * @param categoryList
	 *            the categoryList to set
	 * 
	 */
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Clears the current category bean
	 */
	public void clearForm() {
		this.category = new Category();
	}

	/**
	 * This method is for creating a new category by doing necessary
	 * validations. (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.CategoryController#createCategory()
	 * @return String
	 */
	public String createCategory() {
		logger.info("**** In createCategory in Controller ****");
		if (category != null
				&& StringUtils.isNotEmpty(category.getCategoryName())) {
			String creationMessage = categoryService.createCategory(category);
			if (creationMessage
					.equalsIgnoreCase(Constants.CATEGORY_CREATION_SUCCESS)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						Constants.CATEGORY_CREATION_SUCCESS,
						Constants.CATEGORY_CREATION_SUCCESS);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			} else if (creationMessage
					.equalsIgnoreCase(Constants.CATEGORY_ALREADY_EXISTS)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						Constants.CATEGORY_ALREADY_EXISTS,
						Constants.CATEGORY_ALREADY_EXISTS);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			} else if (creationMessage
					.equalsIgnoreCase(Constants.CATEGORY_CREATION_EXCEPTION)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						Constants.CATEGORY_CREATION_EXCEPTION,
						Constants.CATEGORY_CREATION_EXCEPTION);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			} else if (creationMessage.equalsIgnoreCase(Constants.FAILURE)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						Constants.CATEGORY_CREATION_FAILURE,
						Constants.CATEGORY_CREATION_FAILURE);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}
		}

		return Constants.CREATE_CATEGORY_VIEW;
	}

	/**
	 * This method is for opening the category in delete view for deleting the
	 * item (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.CategoryController#deleteView()
	 * @return String
	 */
	public String deleteView() {
		logger.info("**** In deleteView in Controller ****" + categoryId);
		this.category = categoryService.getCategoryByCategoryId(categoryId);
		return Constants.DELETE_CATEGORY_VIEW;
	}

	/**
	 * This method is for deleting a category that user has selected
	 * (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.CategoryController#deleteCategory()
	 * @return String
	 */
	public String deleteCategory() {
		logger.info("**** In deleteCategory in Controller ****");
		boolean deleteFlag = categoryService.deleteCategory(categoryId);
		if (deleteFlag) {
			FacesMessage facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					Constants.CATEGORY_DELETION_SUCCESS,
					Constants.CATEGORY_DELETION_SUCCESS);
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}

		return searchCategory();
	}

	/**
	 * This method is for opening the category in edit mode (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.CategoryController#editView()
	 * @return String
	 */
	public String editView() {
		logger.info("**** In editView in Controller ****" + categoryId);
		this.category = categoryService.getCategoryByCategoryId(categoryId);
		return Constants.EDIT_CATEGORY_VIEW;
	}

	/**
	 * This method is for updating the category (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.CategoryController#updateCategory()
	 * @return String
	 */
	public String updateCategory() {
		logger.info("**** In updateCategory in Controller ****");
		boolean flag = categoryService.updateCategory(category);
		if (flag) {
			FacesMessage facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					Constants.CATEGORY_UPDATION_SUCCESS,
					Constants.CATEGORY_UPDATION_SUCCESS);
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return searchCategory();
	}

	/**
	 * This method is for searching and listing all the categories with out any
	 * criteria (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.CategoryController#searchCategory()
	 * @return String
	 */
	public String searchCategory() {
		logger.info("**** In searchCategory in Controller ****");
		categoryList = categoryService.getCategories();
		return Constants.SEARCH_CATEGORY_VIEW;
	}

	/**
	 * This method is for searching the Categories by the criteria
	 * 
	 * @return String
	 */
	public String searchCategoriesByCriteria() {
		logger.info("**** In searchCategoriesByCriteria in Controller ****");
		if (category != null
				&& (StringUtils.isNotEmpty(category.getCategoryName()) || StringUtils
						.isNotEmpty(category.getCategoryDescription()))) {
			categoryList = categoryService.searchCategoriesByCriteria(category);
		} else {
			FacesMessage facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_INFO, Constants.NO_SEARCH_CRITERIA,
					Constants.NO_SEARCH_CRITERIA);
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return Constants.SEARCH_CATEGORY_VIEW;
	}

}
