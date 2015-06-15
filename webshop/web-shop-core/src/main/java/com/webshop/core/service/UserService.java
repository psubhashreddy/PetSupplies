package com.webshop.core.service;

import java.util.List;

import com.webshop.core.entity.User;

/**
 * This class is an interface for the business service implementation class
 * 
 * @author speddyre
 * @date 2nd June 2015
 */
public interface UserService {

	String createUser(User user);

	boolean deleteUser(int userId);

	boolean updateUser(User user);

	List<User> getUserList();

	User searchUserByUserId(int userId);

	List<User> searchUsersByCriteria(User user);
}
