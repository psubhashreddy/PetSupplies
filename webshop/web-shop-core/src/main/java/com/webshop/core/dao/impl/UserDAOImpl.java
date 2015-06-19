/**
 * 
 */
package com.webshop.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.webshop.core.dao.UserDAO;
import com.webshop.core.entity.User;
import com.webshop.core.utils.Constants;

/**
 * @author speddyre
 * @date 2nd June 2015
 */
public class UserDAOImpl implements UserDAO
{

   @PersistenceContext(unitName = "defaultPersistenceUnit")
   private EntityManager em;

   @Inject
   private Logger logger;

   /**
    * This method is for creating a user (non-Javadoc)
    * 
    * @see com.webshop.core.dao.UserDAO#createUser(User)
    * @param User
    * @return String
    */
   public String createUser(User user)
   {
      logger.info("**** In createUser in DAOImpl*****");
      try
      {
         em.persist(user);
         return Constants.USER_CREATION_SUCCESS;
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Creating User", e);
         return Constants.USER_CREATION_EXCEPTION;
      }
   }

   /**
    * This method is for deleting a user (non-Javadoc)
    * 
    * @see com.webshop.core.dao.UserDAO#deleteUser(List)
    * @param List
    * @return int
    */
   public boolean deleteUser(int userId)
   {
      logger.info("**** In deleteUser in DAOImpl*****");
      User user = searchUserByUserId(userId);
      em.remove(user);
      return true;
   }

   /**
    * This method is for updating the User (non-Javadoc)
    * 
    * @see com.webshop.core.dao.UserDAO#updateUser(com.webshop.core.entity.User)
    * @param User
    * @return boolean
    */
   public boolean updateUser(User user)
   {
      logger.info("*** In updateUser in ServiceImpl ***");
      em.merge(user);
      return true;
   }

   /**
    * This method is for fetching all the users (non-Javadoc)
    * 
    * @see com.webshop.core.dao.UserDAO#getUserList()
    * @return List
    */
   public List<User> getUserList()
   {
      logger.info("**** In getUserList in DAOImpl*****");
      Query query = em.createNamedQuery("findAllUsers", User.class);
      return (List<User>) query.getResultList();
   }

   /**
    * This method is for searching a user by user id (non-Javadoc)
    * 
    * @see com.webshop.core.dao.UserDAO#searchUserByUserId(int)
    * @param int
    * @return User
    */
   public User searchUserByUserId(int userId)
   {
      logger.info("**** In searchUserByUserId in DAOImpl*****");
      try
      {
         Query query = em.createNamedQuery("findUsersByUserId", User.class).setParameter(Constants.USER_ID, userId);
         return (User) query.getSingleResult();
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Searching User By User Id", e);
         return null;
      }
   }

   /**
    * This method is for searching a user by user name (non-Javadoc)
    * 
    * @see com.webshop.core.dao.UserDAO#searchUsersByUserName(String)
    * @param String
    * @return User
    */
   public User searchUsersByUserName(String userName)
   {
      logger.info("**** In searchUsersByUserName in DAOImpl*****");
      try
      {
         Query query = em.createNamedQuery("findUsersByUserName", User.class).setParameter(Constants.USER_NAME, userName);
         return (User) query.getSingleResult();
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Searching User By User Name", e);
         return null;
      }
   }

   /**
    * This method is for searching a user by user first name (non-Javadoc)
    * 
    * @see com.webshop.core.dao.UserDAO#searchUsersByFirstName(String)
    * @param String
    * @return List
    */
   public List<User> searchUsersByFirstName(String firstName)
   {
      logger.info("**** In searchUsersByFirstName in DAOImpl*****");
      try
      {
         Query query = em.createNamedQuery("findUsersByFirstName", User.class).setParameter(Constants.FIRST_NAME, firstName);
         return (List<User>) query.getResultList();
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Searching User By First Name", e);
         return new ArrayList<User>();
      }
   }

   /**
    * This method is for for searching a user by user last name. (non-Javadoc)
    * 
    * @see com.webshop.core.dao.UserDAO#searchUsersByLastName(String)
    * @param String
    * @return List
    */
   public List<User> searchUsersByLastName(String lastName)
   {
      logger.info("**** In searchUsersByLastName in DAOImpl*****");
      try
      {
         Query query = em.createNamedQuery("findUsersByLastName", User.class).setParameter(Constants.LAST_NAME, lastName);
         return (List<User>) query.getResultList();
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Searching User By Last Name", e);
         return new ArrayList<User>();
      }
   }

   /**
    * This method is for searching a user by his phone (non-Javadoc)
    * 
    * @see com.webshop.core.dao.UserDAO#searchUsersByPhone(String)
    * @param String
    * @return User
    */
   public User searchUsersByPhone(String phone)
   {
      logger.info("**** In searchUsersByPhone in DAOImpl*****");
      try
      {
         Query query = em.createNamedQuery("findUsersByPhone", User.class).setParameter(Constants.USER_PHONE, phone);
         return (User) query.getSingleResult();
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Searching User By Phone", e);
         return null;
      }
   }

   /**
    * This method is for searching a user by his email id (non-Javadoc)
    * 
    * @see com.webshop.core.dao.UserDAO#searchUsersByEmail(String)
    * @param String
    * @return User
    */
   public User searchUsersByEmail(String email)
   {
      logger.info("**** In searchUsersByEmail in DAOImpl*****");
      try
      {
         Query query = em.createNamedQuery("findUsersByEmail", User.class).setParameter(Constants.USER_MAIL, email);
         return (User) query.getSingleResult();
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Searching User By Email", e);
         return null;
      }
   }

   /**
    * This method is for searching a user by filter criteria (non-Javadoc)
    * 
    * @see com.webshop.core.dao.UserDAO#searchUsersByCriteria(User)
    * @param User
    * @return List
    */
   public List<User> searchUsersByCriteria(User user)
   {
      logger.info("**** In searchUsersByCriteria in DAOImpl*****");
      Query query = em.createNamedQuery("findUsersBySearchCriteria", User.class).setParameter(Constants.USER_NAME, user.getUserName()).setParameter(Constants.FIRST_NAME, user.getFirstName())
            .setParameter(Constants.USER_PHONE, user.getPhone()).setParameter(Constants.USER_MAIL, user.getEmail());
      return (List<User>) query.getResultList();
   }
}
