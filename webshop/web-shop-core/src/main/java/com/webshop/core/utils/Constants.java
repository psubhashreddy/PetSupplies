/**
 * 
 */
package com.webshop.core.utils;

/**
 * This class is for maintaining the constants being used across the application
 * 
 * @author speddyre
 * @date 5th June 2015
 */
public final class Constants
{

   /**
    * Constructor not to allow to instantiate it from outside
    */
   private Constants()
   {

   }

   public static final String LOGGED_IN_USER = "userLoggedIn";
   public static final String DISPLAY_LIST = "displayList";
   public static final String IN_PROGRESS = "In Progress";
   public static final String DELIVERED = "Delivered";
   public static final String SUCCESS = "Success";
   public static final String FAILURE = "Failure";
   public static final String USER_TYPE = "User";
   public static final String PERCENTAGE = "%";
   public static final String INVALID_CREDENTIALS = "Invalid UserId or password. Please login again.";
   public static final String NO_UPDATE_FOUND = "There are no updations found, to update the record";

   public static final String ROLE_ALREADY_EXISTS = "Role Already Exists.";
   public static final String ROLE_CREATION_SUCCESS = "Role is created successfully";
   public static final String ROLE_CREATION_FAILURE = "Failed to Create Role. Please try after some time.";
   public static final String ROLE_CREATION_EXCEPTION = "Exception occured while creating Role.";

   public static final String ROLE_UPDATION_SUCCESS = "Role is updated successfully";
   public static final String ROLE_UPDATION_FAILURE = "Failed to Update Role. Please try after some time.";
   public static final String ROLE_UPDATION_EXCEPTION = "Exception occured while updating Role.";

   public static final String ROLE_DELETION_SUCCESS = "Role is deleted successfully";
   public static final String ROLE_DELETION_FAILURE = "Failed to Delete Role. Please try after some time.";
   public static final String ROLE_DELETION_EXCEPTION = "Exception occured while deleting Role.";

   public static final String USER_ALREADY_EXISTS = "User Already Exists.";
   public static final String USER_CREATION_SUCCESS = "User is created successfully";
   public static final String USER_CREATION_FAILURE = "Failed to Create User. Please try after some time.";
   public static final String USER_CREATION_EXCEPTION = "Exception occured while creating User.";

   public static final String USER_UPDATION_SUCCESS = "User is updated successfully";
   public static final String USER_UPDATION_FAILURE = "Failed to Update User. Please try after some time.";
   public static final String USER_UPDATION_EXCEPTION = "Exception occured while updating User.";

   public static final String USER_DELETION_SUCCESS = "User is deleted successfully";
   public static final String USER_DELETION_FAILURE = "Failed to Delete User. Please try after some time.";
   public static final String USER_DELETION_EXCEPTION = "Exception occured while deleting User.";

   public static final String PRODUCT_ALREADY_EXISTS = "Product Already Exists.";
   public static final String PRODUCT_CREATION_SUCCESS = "Product is created successfully";
   public static final String PRODUCT_CREATION_FAILURE = "Failed to Create Product. Please try after some time.";
   public static final String PRODUCT_CREATION_EXCEPTION = "Exception occured while creating Product.";

   public static final String PRODUCT_UPDATION_SUCCESS = "Product is updated successfully";
   public static final String PRODUCT_UPDATION_FAILURE = "Failed to Update Product. Please try after some time.";
   public static final String PRODUCT_UPDATION_EXCEPTION = "Exception occured while updating Product.";

   public static final String PRODUCT_DELETION_SUCCESS = "Product is deleted successfully";
   public static final String PRODUCT_DELETION_FAILURE = "Failed to Delete Product. Please try after some time.";
   public static final String PRODUCT_DELETION_EXCEPTION = "Exception occured while deleting Product.";

   public static final String CATEGORY_ALREADY_EXISTS = "Category Already Exists.";
   public static final String CATEGORY_CREATION_SUCCESS = "Category is created successfully";
   public static final String CATEGORY_CREATION_FAILURE = "Failed to Create Category. Please try after some time.";
   public static final String CATEGORY_CREATION_EXCEPTION = "Exception occured while creating Category.";

   public static final String CATEGORY_UPDATION_SUCCESS = "Category is updated successfully";
   public static final String CATEGORY_UPDATION_FAILURE = "Failed to Update Category. Please try after some time.";
   public static final String CATEGORY_UPDATION_EXCEPTION = "Exception occured while updating Category.";

   public static final String CATEGORY_DELETION_SUCCESS = "Category is deleted successfully";
   public static final String CATEGORY_DELETION_FAILURE = "Failed to Delete Category. Please try after some time.";
   public static final String CATEGORY_DELETION_EXCEPTION = "Exception occured while deleting Category.";

   public static final String ORDER_CREATION_SUCCESS = "Congratulations ! Your order has been created successfully.";

   /** Controller URL Mapping **/
   public static final String WEBSHOP_LOGIN = "/login.xhtml";
   public static final String WEBSHOP_HOME = "/jsf/user/home.xhtml";

   public static final String CREATE_USER_VIEW = "/jsf/user/create";
   public static final String EDIT_USER_VIEW = "/jsf/user/edit";
   public static final String SEARCH_USER_VIEW = "/jsf/user/search";
   public static final String DELETE_USER_VIEW = "/jsf/user/delete";

   public static final String CREATE_ROLE_VIEW = "/jsf/role/create";
   public static final String EDIT_ROLE_VIEW = "/jsf/role/edit";
   public static final String SEARCH_ROLE_VIEW = "/jsf/role/search";
   public static final String DELETE_ROLE_VIEW = "/jsf/role/delete";

   public static final String CREATE_PRODUCT_VIEW = "/jsf/product/create";
   public static final String EDIT_PRODUCT_VIEW = "/jsf/product/edit";
   public static final String SEARCH_PRODUCT_VIEW = "/jsf/product/search";
   public static final String DELETE_PRODUCT_VIEW = "/jsf/product/delete";

   public static final String CREATE_CATEGORY_VIEW = "/jsf/category/create";
   public static final String EDIT_CATEGORY_VIEW = "/jsf/category/edit";
   public static final String SEARCH_CATEGORY_VIEW = "/jsf/category/search";
   public static final String DELETE_CATEGORY_VIEW = "/jsf/category/delete";

   public static final String NO_RECORDS_FOUND = "No Records Found";
   public static final String NO_SEARCH_CRITERIA = "Please enter some thing for search!";

   public static final String CREATE_NEW_USER = "/register";
   public static final String UPDATE_USER_VIEW = "/jsf/user/profile";

   public static final String SHOW_ALL_PRODUCTS = "/jsf/order/view";
   public static final String PREVIEW_MY_ORDER = "/jsf/order/preview";
   public static final String CONFIRM_MY_ORDER = "/jsf/order/confirm";
   public static final String SEARCH_MY_ORDER = "/jsf/order/search";

   public static final String ROLE_ID = "roleid";
   public static final String ROLE_NAME = "rolename";
   public static final String ROLE_DESC = "roledesc";
   public static final String USER_ID = "userid";
   public static final String USER_NAME = "username";
   public static final String FIRST_NAME = "firstname";
   public static final String LAST_NAME = "lastname";
   public static final String USER_PHONE = "phone";
   public static final String USER_MAIL = "email";
   public static final String CATEGORY_ID = "categoryid";
   public static final String CATEGORY_NAME = "categoryname";
   public static final String CATEGORY_DESC = "categorydesc";
   public static final String PRODUCT_ID = "productid";
   public static final String PRODUCT_CODE = "productcode";
   public static final String PRODUCT_NAME = "productname";
   public static final String PRODUCT_PRICE = "productprice";
   public static final String PRODUCT_DESC = "productdesc";

   public static final String ORDER_NO = "orderno";

}
