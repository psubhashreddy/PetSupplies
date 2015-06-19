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

import com.webshop.core.dao.UserDAO;
import com.webshop.core.entity.User;
import com.webshop.core.service.UserService;
import com.webshop.core.utils.Constants;
import com.webshop.core.utils.DateConverterUtil;

/**
 * This class is the implementation class for the business interface
 * 
 * @author speddyre
 * @date 2nd June 2015
 */
@Stateless
public class UserServiceImpl implements UserService
{

   @Inject
   private UserDAO userDAO;

   @Inject
   private transient Logger logger;

   /**
    * This method is for creating a user (non-Javadoc)
    * 
    * @see com.webshop.core.service.UserService#createUser(com.webshop.core.entity.User)
    * @param User
    * @return String
    */
   public String createUser(User user)
   {
      logger.info("*** In createUser in ServiceImpl ***");
      if (userNameAlreadyExists(user).equalsIgnoreCase(Constants.USER_ALREADY_EXISTS) || userPhoneAlreadyExists(user).equalsIgnoreCase(Constants.USER_ALREADY_EXISTS)
            || userEmailAlreadyExists(user).equalsIgnoreCase(Constants.USER_ALREADY_EXISTS))
      {
         return Constants.USER_ALREADY_EXISTS;
      }
      else
      {
         user.setLastUpdatedDate(DateConverterUtil.dateToTimeStamp());
         return userDAO.createUser(user);
      }
   }

   /**
    * This method is a utility method for validating the user existence by user name.
    * 
    * @param user
    * @return String
    */
   public String userNameAlreadyExists(User user)
   {
      logger.info("*** In userNameAlreadyExists in ServiceImpl ***" + user.toString());
      if (user != null && StringUtils.isNotBlank(user.getUserName()))
      {
         User result = userDAO.searchUsersByUserName(user.getUserName());
         if (result != null)
         {
            return Constants.USER_ALREADY_EXISTS;
         }
      }
      return Constants.FAILURE;
   }

   /**
    * This method is a utility method for validating the user existence by phone
    * 
    * @param user
    * @return String
    */
   public String userPhoneAlreadyExists(User user)
   {
      logger.info("*** In userPhoneAlreadyExists in ServiceImpl ***" + user.toString());
      if (user != null && StringUtils.isNotEmpty(user.getPhone()))
      {
         User result = userDAO.searchUsersByPhone(user.getPhone());
         if (result != null)
         {
            return Constants.USER_ALREADY_EXISTS;
         }
      }
      return Constants.FAILURE;
   }

   /**
    * This method is a utility method for validating the user existence by email
    * 
    * @param user
    * @return String
    */
   public String userEmailAlreadyExists(User user)
   {
      logger.info("*** In userEmailAlreadyExists in ServiceImpl ***" + user.toString());
      if (user != null && StringUtils.isNotEmpty(user.getEmail()))
      {
         User result = userDAO.searchUsersByEmail(user.getEmail());
         if (result != null)
         {
            return Constants.USER_ALREADY_EXISTS;
         }
      }
      return Constants.FAILURE;
   }

   /**
    * This method is for deleting a User (non-Javadoc)
    * 
    * @see com.webshop.core.service.UserService#deleteUser(java.util.List)
    * @param User
    * @return boolean
    */
   public boolean deleteUser(int userId)
   {
      logger.info("*** In deleteUser in ServiceImpl ***");
      return userDAO.deleteUser(userId);
   }

   /**
    * This method is to update the user (non-Javadoc)
    * 
    * @see com.webshop.core.service.UserService#updateUser(com.webshop.core.entity.User)
    * @param user
    * @return boolean
    */
   public boolean updateUser(User user)
   {
      logger.info("*** In updateUser in ServiceImpl ***");
      user.setLastUpdatedDate(DateConverterUtil.dateToTimeStamp());
      return userDAO.updateUser(user);
   }

   /**
    * This method is for fetching all the users (non-Javadoc)
    * 
    * @see com.webshop.core.service.UserService#getUserList()
    * @return List
    */
   public List<User> getUserList()
   {
      logger.info("*** In getUserList in ServiceImpl ***");
      return userDAO.getUserList();
   }

   /**
    * This method is for searching a user by user id. (non-Javadoc)
    * 
    * @see com.webshop.core.service.UserService#searchUserByUserId(int)
    * @param int
    * @return User
    */
   public User searchUserByUserId(int userId)
   {
      return userDAO.searchUserByUserId(userId);
   }

   /**
    * This method is for searching the users by criteria (non-Javadoc)
    * 
    * @see com.webshop.core.service.RoleService#getRolesByCriteria(com.webshop.core.entity.Role)
    * @param User
    * @return List
    */
   public List<User> searchUsersByCriteria(User user)
   {
      logger.info("*** In searchUsersByCriteria in ServiceImpl ***");
      try
      {
         List<User> userList = new ArrayList<User>();
         User result = null;
         if (StringUtils.isNotEmpty(user.getUserName()) && StringUtils.isEmpty(user.getPhone()) && StringUtils.isEmpty(user.getEmail()) && StringUtils.isEmpty(user.getFirstName()))
         {
            result = userDAO.searchUsersByUserName(user.getUserName());
            if (result != null)
            {
               userList.add(result);
            }
         }
         else if (StringUtils.isNotEmpty(user.getPhone()) && StringUtils.isEmpty(user.getUserName()) && StringUtils.isEmpty(user.getFirstName()) && StringUtils.isEmpty(user.getEmail()))
         {
            result = userDAO.searchUsersByPhone(user.getPhone());
            if (result != null)
            {
               userList.add(result);
            }
         }
         else if (StringUtils.isNotEmpty(user.getEmail()) && StringUtils.isEmpty(user.getUserName()) && StringUtils.isEmpty(user.getFirstName()) && StringUtils.isEmpty(user.getPhone()))
         {
            result = userDAO.searchUsersByEmail(user.getEmail());
            if (result != null)
            {
               userList.add(result);
            }
         }
         else if (StringUtils.isNotEmpty(user.getFirstName()) && StringUtils.isEmpty(user.getUserName()) && StringUtils.isEmpty(user.getEmail()) && StringUtils.isEmpty(user.getPhone()))
         {
            userList = userDAO.searchUsersByFirstName(user.getFirstName());
         }
         else
         {
            userList = userDAO.searchUsersByCriteria(user);
         }
         return userList;
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Searching User", e);
         return new ArrayList<User>();
      }
   }

}
