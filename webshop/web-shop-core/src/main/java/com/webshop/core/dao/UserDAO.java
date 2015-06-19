package com.webshop.core.dao;

import java.util.List;

import com.webshop.core.entity.User;

/**
 * This class is the dao invoked form the business implementation layer
 * 
 * @author speddyre
 * @date 2nd June 2015
 */
public interface UserDAO
{

   String createUser(User userId);

   boolean deleteUser(int user);

   boolean updateUser(User user);

   List<User> getUserList();

   User searchUserByUserId(int userId);

   User searchUsersByUserName(String userName);

   List<User> searchUsersByFirstName(String firstName);

   List<User> searchUsersByLastName(String lastName);

   User searchUsersByPhone(String phone);

   User searchUsersByEmail(String email);

   List<User> searchUsersByCriteria(User user);

}
