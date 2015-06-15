package com.webshop.core.service;

import com.webshop.core.entity.User;

/**
 * This class is an interface for the business service implementation class
 * 
 * @author speddyre
 * @date 2nd June 2015
 */
public interface LoginService {

	User validateLogin(String userName, String password, String roleId);

}
