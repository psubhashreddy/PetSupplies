package com.webshop.core.service.impl;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.webshop.core.dao.LoginDAO;
import com.webshop.core.entity.User;
import com.webshop.core.service.LoginService;

/**
 * This class is the implementation class for the business interface
 * 
 * @author speddyre
 * @date 2nd June 2015
 */
@Stateless
public class LoginServiceImpl implements LoginService
{

   @Inject
   private LoginDAO loginDAO;

   @Inject
   private transient Logger logger;

   /**
    * This method is for validating the user login (non-Javadoc)
    * 
    * @see com.webshop.core.service.LoginService#validateLogin(java.lang.String, java.lang.String)
    * @param String , String
    * @return User
    */
   public User validateLogin(String userName, String password, String roleId)
   {
      logger.info("**** In validateLogin in ServiceImpl ****");
      return loginDAO.validateLogin(userName, password, Integer.valueOf(roleId));
   }
}
