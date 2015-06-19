/**
 * 
 */
package com.webshop.user.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.webshop.core.utils.Constants;

/**
 * @author speddyre
 */
@ManagedBean
@SessionScoped
public class NavigationController implements Serializable
{

   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;

   public String redirectToLogin()
   {
      return "/login.xhtml?faces-redirect=true";
   }

   public String login()
   {
      return Constants.WEBSHOP_LOGIN;
   }

   public String redirectToHome()
   {
      return "/jsf/user/home.xhtml?faces-redirect=true";
   }

   public String toHome()
   {
      return Constants.WEBSHOP_HOME;
   }
}
