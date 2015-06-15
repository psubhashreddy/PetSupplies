/**
 * 
 */
package com.webshop.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.webshop.core.entity.Category;
import com.webshop.core.entity.Product;
import com.webshop.core.service.CategoryService;
import com.webshop.core.service.ProductService;
import com.webshop.core.utils.Constants;

/**
 * This class is the controller for performing all the crud operations on the
 * product
 * 
 * @see
 * @author speddyre
 * @date 3rd June 2015
 */
@ManagedBean(name = "productController")
@RequestScoped
public class ProductController {

	@Inject
	private ProductService productService;

	@Inject
	private CategoryService categoryService;

	@Inject
	private transient Logger logger;

	private Product product = new Product();
	private List<Product> productList = new ArrayList<Product>();
	private List<Category> categoryList = new ArrayList<Category>();
	private int productId;
	private int categoryId;

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the productList
	 */
	public List<Product> getProductList() {
		return productList;
	}

	/**
	 * @param productList
	 *            the productList to set
	 */
	public void setProductList(List<Product> productList) {
		this.productList = productList;
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
	 */
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
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
	 * This method is for loading the category list for displaying in the
	 * product creation form
	 */
	@PostConstruct
	public void init() {
		this.categoryList = categoryService.getCategories();
	}

	/**
	 * Clears the current product bean
	 */
	public void clearForm() {
		this.product = new Product();
	}

	/**
	 * This method is for creating a new product by doing necessary validations.
	 * (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.ProductController#createProduct()
	 * @return String @
	 */
	public String createProduct() {
		logger.info("**** In createProduct in Controller ****");
		if (product != null && StringUtils.isNotEmpty(product.getProductCode())) {
			String creationMessage = productService.createProduct(product);
			if (creationMessage
					.equalsIgnoreCase(Constants.PRODUCT_CREATION_SUCCESS)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						Constants.PRODUCT_CREATION_SUCCESS,
						Constants.PRODUCT_CREATION_SUCCESS);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			} else if (creationMessage
					.equalsIgnoreCase(Constants.PRODUCT_ALREADY_EXISTS)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						Constants.PRODUCT_ALREADY_EXISTS,
						Constants.PRODUCT_ALREADY_EXISTS);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			} else if (creationMessage
					.equalsIgnoreCase(Constants.PRODUCT_CREATION_EXCEPTION)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						Constants.PRODUCT_CREATION_EXCEPTION,
						Constants.PRODUCT_CREATION_EXCEPTION);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			} else if (creationMessage.equalsIgnoreCase(Constants.FAILURE)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						Constants.PRODUCT_CREATION_FAILURE,
						Constants.PRODUCT_CREATION_FAILURE);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}
		}

		return Constants.CREATE_PRODUCT_VIEW;
	}

	/**
	 * This method is for deleting product selected by the user (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.ProductController#deleteView()
	 * @return String
	 */
	public String deleteView() {
		logger.info("**** In deleteView in Controller ****");
		this.product = productService.getProductbyProductId(productId);
		return Constants.DELETE_PRODUCT_VIEW;
	}

	/**
	 * This method is for deleting product selected by the user (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.ProductController#deleteProduct()
	 * @return String
	 */
	public String deleteProduct() {
		logger.info("**** In deleteProduct in Controller ****");
		boolean deleteFlag = productService.deleteProduct(productId);
		if (deleteFlag) {
			FacesMessage facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					Constants.PRODUCT_DELETION_SUCCESS,
					Constants.PRODUCT_DELETION_SUCCESS);
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return searchProduct();
	}

	/**
	 * This method is for opening the edit view for editing the Product
	 * (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.ProductController#editView()
	 * @return String
	 */
	public String editView() {
		logger.info("**** In editView in Controller ****");
		this.product = productService.getProductbyProductId(productId);
		this.categoryId = this.product.getCategory().getCategoryId();
		return Constants.EDIT_PRODUCT_VIEW;
	}

	/**
	 * This method is for updating the details of the product (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.ProductController#updateProduct()
	 * @return String
	 */
	public String updateProduct() {
		logger.info("**** In updateProduct in Controller ****");
		product.setCategory(categoryService.getCategoryByCategoryId(categoryId));
		boolean updateFlag = productService.updateProduct(product);
		if (updateFlag) {
			FacesMessage facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					Constants.PRODUCT_UPDATION_SUCCESS,
					Constants.PRODUCT_UPDATION_SUCCESS);
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return searchProduct();
	}

	/**
	 * This method is for listing all the products without any filter
	 * (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.ProductController#searchProduct()
	 * @return String @
	 */
	public String searchProduct() {
		logger.info("**** In searchProduct in Controller ****");
		productList = productService.getProductList();
		return Constants.SEARCH_PRODUCT_VIEW;
	}

	/**
	 * This method is for searching a product based on the filter criteria
	 * (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.ProductController#searchProductByCriteria()
	 * @return String @
	 */
	public String searchProductsByCriteria() {
		logger.info("*** In searchProductsByCriteria with criteria in Controller ***");
		if (product != null
				&& (StringUtils.isNotEmpty(product.getProductCode()) || StringUtils
						.isNotEmpty(product.getProductName()))) {
			productList = productService.searchProductsByCriteria(product);
		} else {
			FacesMessage facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_INFO, Constants.NO_SEARCH_CRITERIA,
					Constants.NO_SEARCH_CRITERIA);
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return Constants.SEARCH_PRODUCT_VIEW;
	}
}
