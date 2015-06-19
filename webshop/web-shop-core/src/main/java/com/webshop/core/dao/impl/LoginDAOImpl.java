package com.webshop.core.dao.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.webshop.core.dao.LoginDAO;
import com.webshop.core.entity.User;

/**
 * This class is for user logging
 * 
 * @author speddyre
 * @date 2nd June 2015
 */
public class LoginDAOImpl implements LoginDAO
{

   @PersistenceContext(unitName = "defaultPersistenceUnit")
   private EntityManager em;

   @Inject
   private transient Logger logger;

   /**
    * This method is for validating login (non-Javadoc)
    * 
    * @see com.webshop.core.dao.LoginDAO#validateLogin(String, String)
    * @param String , String
    * @return User
    */
   public User validateLogin(String userName, String password, int roleId)
   {
      logger.info("**** In validateLogin in DAOImpl ****");
      try
      {
         Query query = em.createNamedQuery("findUserByUserNameAndPassWord").setParameter("username", userName).setParameter("password", password).setParameter("roleId", roleId);
         return (User) query.getSingleResult();
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in validating Login", e);
         return null;
      }
   }

}
