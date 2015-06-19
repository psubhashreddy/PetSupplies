package com.webshop.admin.controller;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.webshop.core.entity.User;
import com.webshop.core.service.LoginService;
import com.webshop.core.utils.Constants;

/**
 * @author speddyre
 * @date 2nd June 2015
 */
@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController
{

   @Inject
   private LoginService loginService;

   @Inject
   private transient Logger logger;

   @ManagedProperty(value = "#{navigationController}")
   private NavigationController navigationController;

   private User user = new User();
   private String userName;
   private String password;

   /**
    * @return the navigationController
    */
   public NavigationController getNavigationController()
   {
      return navigationController;
   }

   /**
    * @param navigationController the navigationController to set
    */
   public void setNavigationController(NavigationController navigationController)
   {
      this.navigationController = navigationController;
   }

   /**
    * @return the user
    */
   public User getUser()
   {
      return user;
   }

   /**
    * @param user the user to set
    */
   public void setUser(User user)
   {
      this.user = user;
   }

   /**
    * @return the userName
    */
   public String getUserName()
   {
      return userName;
   }

   /**
    * @param userName the userName to set
    */
   public void setUserName(String userName)
   {
      this.userName = userName;
   }

   /**
    * @return the password
    */
   public String getPassword()
   {
      return password;
   }

   /**
    * @param password the password to set
    */
   public void setPassword(String password)
   {
      this.password = password;
   }

   /**
    * This method is for logging into the application by validating the user credentials
    * 
    * @return String
    */
   public String login()
   {
      logger.info("**** In login in Controller ****");
      User userObj = loginService.validateLogin(userName, password, "1");
      if (userObj != null)
      {
         HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
         httpSession.setAttribute("userLoggedIn", userObj);
         return navigationController.redirectToHome();
      }
      else
      {
         FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constants.INVALID_CREDENTIALS, Constants.INVALID_CREDENTIALS);
         FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
         FacesContext.getCurrentInstance().addMessage(null, facesMsg);
         return navigationController.redirectToLogin();
      }
   }

   /**
    * This method is for logging out the user from the application.
    * 
    * @return String
    */
   public String logout()
   {
      logger.info("*** In logout in Controller ***");
      ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
      return navigationController.redirectToLogin();
   }
}
