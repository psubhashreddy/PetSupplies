/**
 * 
 */
package com.webshop.user.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.webshop.core.entity.Category;
import com.webshop.core.entity.Order;
import com.webshop.core.entity.OrderDetail;
import com.webshop.core.entity.Product;
import com.webshop.core.entity.User;
import com.webshop.core.service.CategoryService;
import com.webshop.core.service.ProductService;
import com.webshop.core.service.ShoppingService;
import com.webshop.core.utils.Constants;
import com.webshop.core.utils.DateConverterUtil;
import com.webshop.user.util.NumericGenerator;
import com.webshop.user.vo.OrderVO;
import com.webshop.user.vo.ProductVO;

/**
 * @author speddyre
 * @dated 12th June 2015
 */
@ManagedBean(name = "shoppingController")
@SessionScoped
public class ShoppingController
{

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
   private OrderVO orderVO = new OrderVO();
   private String orderNo;

   /**
    * @return the categoryId
    */
   public int getCategoryId()
   {
      return categoryId;
   }

   /**
    * @param categoryId the categoryId to set
    */
   public void setCategoryId(int categoryId)
   {
      this.categoryId = categoryId;
   }

   /**
    * @return the productDesc
    */
   public String getProductDesc()
   {
      return productDesc;
   }

   /**
    * @param productDesc the productDesc to set
    */
   public void setProductDesc(String productDesc)
   {
      this.productDesc = productDesc;
   }

   /**
    * @return the productList
    */
   public List<Product> getProductList()
   {
      return productList;
   }

   /**
    * @param productList the productList to set
    */
   public void setProductList(List<Product> productList)
   {
      this.productList = productList;
   }

   /**
    * @return the categoryList
    */
   public List<Category> getCategoryList()
   {
      return categoryList;
   }

   /**
    * @param categoryList the categoryList to set
    */
   public void setCategoryList(List<Category> categoryList)
   {
      this.categoryList = categoryList;
   }

   /**
    * @return the orderVO
    */
   public OrderVO getOrderVO()
   {
      return orderVO;
   }

   /**
    * @param orderVO the orderVO to set
    */
   public void setOrderVO(OrderVO orderVO)
   {
      this.orderVO = orderVO;
   }

   /**
    * @return the orderNo
    */
   public String getOrderNo()
   {
      return orderNo;
   }

   /**
    * @param orderNo the orderNo to set
    */
   public void setOrderNo(String orderNo)
   {
      this.orderNo = orderNo;
   }

   /**
    * This method is to load all the categories and products on start
    */
   @PostConstruct
   public void init()
   {
      setCategoryList(categoryService.getCategories());
      setProductList(productService.getProductList());
   }

   /**
    * This method is for loading all the products and categorizing them based on the categories and then setting them to a value Object
    * 
    * @see com.webshop.user.controller.ShoppingController#showAllProducts()
    * @return String
    */
   public String showAllProducts()
   {
      logger.info("*** showAllProducts in Controller ***");
      HttpSession currentSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
      Object object = currentSession.getAttribute(Constants.DISPLAY_LIST);
      if (object != null)
      {
         currentSession.setAttribute(Constants.DISPLAY_LIST, null);
      }
      else
      {
         this.orderVO.setTotalAmt(0);
         this.orderVO.setTotalQuantity(0);
         this.orderVO.setUser((User) currentSession.getAttribute(Constants.LOGGED_IN_USER));
         this.orderVO.setProdList(constructProductVOList(getProductList()));
      }
      return Constants.SHOW_ALL_PRODUCTS;
   }

   /**
    * This method is for searching the products based on the search criteria.
    * 
    * @see com.webshop.user.controller.ShoppingController#showAllProducts()
    * @return String
    */
   public String searchProducts()
   {
      logger.info("*** searchProducts in Controller ***");
      this.orderVO.setTotalAmt(0);
      this.orderVO.setTotalQuantity(0);
      this.orderVO.setProdList(constructProductVOList(shoppingService.searchProducts(categoryId, productDesc)));
      return Constants.SHOW_ALL_PRODUCTS;
   }

   /**
    * This method is for adding a product to the cart and doing necessary calculations
    * 
    * @see com.webshop.user.controller.ShoppingController#addToCart()
    * @return String
    */
   public String addToCart()
   {
      logger.info("*** addToCart in Controller ***");
      calculateTotals();
      return Constants.SHOW_ALL_PRODUCTS;
   }

   /**
    * This method is for calculating the totals for the quantity and amount
    * 
    * @see com.webshop.user.controller.ShoppingController#calculateTotals()
    */
   private void calculateTotals()
   {
      List<ProductVO> prodtList = new ArrayList<ProductVO>();
      double totalAmount = 0.0;
      int totalQuantity = 0;
      for (ProductVO prod : this.orderVO.getProdList())
      {
         prod.setTotalAmount(prod.getQuantity() * prod.getProductPrice());
         totalAmount = totalAmount + prod.getTotalAmount();
         totalQuantity = totalQuantity + prod.getQuantity();
         prodtList.add(prod);
      }
      this.orderVO.setTotalAmt(totalAmount);
      this.orderVO.setTotalQuantity(totalQuantity);
      this.orderVO.setProdList(prodtList);
   }

   /**
    * This method is for showing the preview of the products selected in the show all products.
    * 
    * @see com.webshop.user.controller.ShoppingController#placeOrder()
    * @return String
    */
   public String placeOrder()
   {
      logger.info("*** placeOrder in Controller ***");
      calculateTotals();
      List<ProductVO> productList = new ArrayList<ProductVO>();
      for (ProductVO productVO : this.orderVO.getProdList())
      {
         if (productVO.getQuantity() > 0)
         {
            productList.add(productVO);
         }
      }
      this.orderVO.setProdList(productList);

      return Constants.PREVIEW_MY_ORDER;
   }

   /**
    * This method is for confirming the order placed by user after preview of the products selected.
    * 
    * @see com.webshop.user.controller.ShoppingController#confirmOrder()
    * @return String
    */
   public String confirmOrder()
   {
      logger.info("*** confirmOrder in Controller ***");
      this.orderVO.setOrderDate(DateConverterUtil.dateToTimeStamp());
      this.orderVO.setOrderNum(NumericGenerator.generateAlphaNumericNumeric());
      boolean flag = shoppingService.createOrder(processOrderVO(this.orderVO));
      if (flag)
      {
         FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, Constants.ORDER_CREATION_SUCCESS, Constants.ORDER_CREATION_SUCCESS);
         FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
         FacesContext.getCurrentInstance().addMessage(null, facesMsg);
      }
      return Constants.CONFIRM_MY_ORDER;
   }

   public String searchMyOrder()
   {
      if (StringUtils.isNotEmpty(orderNo))
      {
         this.orderVO = constructOrderVO(shoppingService.searchOrderByOrderNo(orderNo));
      }
      return Constants.SEARCH_MY_ORDER;
   }

   /**
    * This method is for constructing productVOList
    * 
    * @see com.webshop.user.controller.ShoppingController#constructProductVOList()
    * @return List<ProductVO>
    */
   private List<ProductVO> constructProductVOList(List<Product> productList)
   {
      List<ProductVO> prodList = new ArrayList<ProductVO>();
      for (Product product : productList)
      {
         ProductVO productVO = new ProductVO();
         productVO.setCategoryId(product.getCategory().getCategoryId());
         productVO.setProductId(product.getProductId());
         productVO.setProductCode(product.getProductCode());
         productVO.setProductName(product.getProductName());
         productVO.setProductPrice(product.getProductPrice());
         productVO.setProductDesc(product.getProductDescription());
         prodList.add(productVO);
      }
      return prodList;
   }

   /**
    * This method is for constructing the order entity from the orderVO.
    * 
    * @see com.webshop.user.controller.ShoppingController#constructProductVOList()
    * @param OrderVO
    * @return Order
    */
   private Order processOrderVO(OrderVO orderVO)
   {
      Order order = new Order();
      order.setOrderNo(orderVO.getOrderNum());
      order.setShipName(orderVO.getUser().getFirstName() + " " + orderVO.getUser().getLastName());
      order.setShipPhone(orderVO.getUser().getPhone());
      order.setShipEmail(orderVO.getUser().getEmail());
      order.setShipAddress(orderVO.getUser().getAddress());
      order.setShipCity(orderVO.getUser().getCity());
      order.setShipState(orderVO.getUser().getState());
      order.setShipCountry(orderVO.getUser().getCountry());
      order.setShipPostalCode(orderVO.getUser().getPostalCode());
      order.setShippingDate(DateConverterUtil.getDateAfterDays(5));

      Set<OrderDetail> orderDetailSet = new HashSet<OrderDetail>();
      for (ProductVO productVO : orderVO.getProdList())
      {
         OrderDetail orderDetail = new OrderDetail();
         orderDetail.setOrderCost(productVO.getTotalAmount());
         orderDetail.setOrderDiscount(0);
         orderDetail.setOrderQuantity(productVO.getQuantity());

         Product product = new Product();
         product.setProductId(productVO.getProductId());
         product.setProductCode(productVO.getProductCode());
         product.setProductName(productVO.getProductName());
         product.setProductPrice(productVO.getProductPrice());
         product.setProductDescription(productVO.getProductDesc());

         orderDetail.setProduct(product);
         orderDetail.setOrder(order);
         orderDetailSet.add(orderDetail);

      }
      order.setOrderDetail(orderDetailSet);
      order.setUser(orderVO.getUser());
      return order;
   }

   /**
    * This method is for constructing the order vo from the order that loaded from the database.
    */
   private OrderVO constructOrderVO(Order order)
   {
      OrderVO ordrVO = new OrderVO();
      ordrVO.setOrderNum(order.getOrderNo());
      ordrVO.setOrderDate(order.getShippingDate());

      for (OrderDetail orderDetail : order.getOrderDetail())
      {
         ordrVO.setTotalAmt(orderDetail.getOrderCost());
         ordrVO.setTotalQuantity(orderDetail.getOrderQuantity());
      }
      ordrVO.setUser(order.getUser());
      if (DateConverterUtil.dateToTimeStamp().after(order.getShippingDate()))
      {
         ordrVO.setStatus(Constants.DELIVERED);
      }
      else
      {
         ordrVO.setStatus(Constants.IN_PROGRESS);
      }
      return ordrVO;
   }

}
