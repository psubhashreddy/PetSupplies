/**
 * 
 */
package com.webshop.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.webshop.core.entity.Category;
import com.webshop.core.entity.Product;
import com.webshop.core.service.CategoryService;
import com.webshop.core.service.ProductService;
import com.webshop.core.service.ShoppingService;
import com.webshop.core.utils.Constants;
import com.webshop.user.vo.CategoryVO;
import com.webshop.user.vo.OrderVO;
import com.webshop.user.vo.ProductVO;

/**
 * @author speddyre
 * @dated 12th June 2015
 *
 */
@ManagedBean(name = "shoppingController")
@SessionScoped
public class ShoppingController {

	@Inject
	private ProductService productService;

	@Inject
	private CategoryService categoryService;

	@Inject
	private ShoppingService shoppingService;

	@Inject
	private Logger logger;

	private int categoryId;
	private String productDesc;
	private List<Product> productList = new ArrayList<Product>();
	private List<Category> categoryList = new ArrayList<Category>();
	private Map<String, List<Product>> catMap = new HashMap<String, List<Product>>();
	private OrderVO orderVO = new OrderVO();

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
	 * @return the productDesc
	 */
	public String getProductDesc() {
		return productDesc;
	}

	/**
	 * @param productDesc
	 *            the productDesc to set
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
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
	 * @return the catMap
	 */
	public Map<String, List<Product>> getCatMap() {
		return catMap;
	}

	/**
	 * @param catMap
	 *            the catMap to set
	 */
	public void setCatMap(Map<String, List<Product>> catMap) {
		this.catMap = catMap;
	}

	/**
	 * @return the orderVO
	 */
	public OrderVO getOrderVO() {
		return orderVO;
	}

	/**
	 * @param orderVO
	 *            the orderVO to set
	 */
	public void setOrderVO(OrderVO orderVO) {
		this.orderVO = orderVO;
	}

	/**
	 * This method is to load all the categories and products on start
	 */
	@PostConstruct
	public void init() {
		setCategoryList(categoryService.getCategories());
		setProductList(productService.getProductList());
	}

	/**
	 * This method is for loading all the products and categorizing them based
	 * on the categories and then setting them to a value Object
	 * 
	 * @see com.webshop.user.controller.ShoppingController#showAllProducts()
	 * @return String
	 */
	public String showAllProducts() {
		logger.info("*** showAllProducts in Controller ***");
		HttpSession currentSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(false);
		List<CategoryVO> categoryLst = new ArrayList<CategoryVO>();
		Object object = currentSession.getAttribute(Constants.DISPLAY_CAT_LIST);
		if (object != null) {
			currentSession.setAttribute(Constants.DISPLAY_CAT_LIST, null);
		} else {
			categoryLst.addAll(constructCatList(getProductList()));
			this.orderVO.setCatList(categoryLst);
		}
		return Constants.SHOW_ALL_PRODUCTS;
	}

	/**
	 * This method is for searching the products based on the search criteria.
	 * 
	 * @see com.webshop.user.controller.ShoppingController#showAllProducts()
	 * @return String
	 */
	public String searchProducts() {
		logger.info("*** searchProducts in Controller ***");
		this.orderVO.setCatList(constructCatList(shoppingService
				.searchProducts(categoryId, productDesc)));
		return Constants.SHOW_ALL_PRODUCTS;
	}

	/**
	 * This method is for showing the preview of the products selected in the
	 * show all products.
	 * 
	 * @see com.webshop.user.controller.ShoppingController#placeOrder()
	 * @return String
	 */
	public String placeOrder() {
		logger.info("*** placeOrder in Controller ***");
		return Constants.PREVIEW_MY_ORDER;
	}

	/**
	 * This method is for confirming the order placed by user after preview of
	 * the products selected.
	 * 
	 * @see com.webshop.user.controller.ShoppingController#confirmOrder()
	 * @return String
	 */
	public String confirmOrder() {
		logger.info("*** confirmOrder in Controller ***");
		return Constants.CONFIRM_MY_ORDER;
	}

	/**
	 * This method is for constructing the value Object with the list of
	 * categories and products used to display in the view
	 * 
	 * @see com.webshop.user.controller.ShoppingController#constructCatList()
	 * @return List<CategoryVO>
	 */
	private List<CategoryVO> constructCatList(List<Product> productList) {
		List<CategoryVO> catList = new ArrayList<CategoryVO>();
		Map<String, List<ProductVO>> productMap = constructCategoryMap(productList);
		for (Category category : getCategoryList()) {
			CategoryVO categoryVO = new CategoryVO();
			categoryVO.setCategoryId(category.getCategoryId());
			categoryVO.setCategoryName(category.getCategoryName());
			categoryVO.setCategoryDesc(category.getCategoryDescription());
			categoryVO
					.setProductList(productMap.get(category.getCategoryName()));
			catList.add(categoryVO);
		}
		return catList;
	}

	/**
	 * This method is for constructing a Map with the categoryName as the Key
	 * and productList for each category as the value.
	 * 
	 * @see com.webshop.user.controller.ShoppingController#constructCategoryMap()
	 * @return Map<String, List<ProductVO>>
	 */
	private Map<String, List<ProductVO>> constructCategoryMap(
			List<Product> productList) {
		Map<String, List<ProductVO>> categoryMap = new HashMap<String, List<ProductVO>>();
		for (Product product : productList) {
			if (categoryMap.get(product.getCategory().getCategoryName()) != null) {
				ProductVO productVO = new ProductVO();
				productVO.setCategoryId(product.getCategory().getCategoryId());
				productVO.setProductId(product.getProductId());
				productVO.setProductCode(product.getProductCode());
				productVO.setProductName(product.getProductName());
				productVO.setProductPrice(product.getProductPrice());
				productVO.setProductDesc(product.getProductDescription());
				categoryMap.get(product.getCategory().getCategoryName()).add(productVO);
			}else{
				categoryMap.put(product.getCategory().getCategoryName(),
						new ArrayList<ProductVO>());
			}
		}
		return categoryMap;
	}

}
