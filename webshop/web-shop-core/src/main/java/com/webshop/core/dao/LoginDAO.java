package com.webshop.core.dao;

import com.webshop.core.entity.User;

/**
 * This class is the dao invoked form the business implementation layer
 * 
 * @author speddyre
 * @date 2nd June 2015
 */
public interface LoginDAO
{

   User validateLogin(String userName, String password, int roleId);

}
