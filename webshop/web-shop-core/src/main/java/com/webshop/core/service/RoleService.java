/**
 * 
 */
package com.webshop.core.service;

import java.util.List;

import com.webshop.core.entity.Role;

/**
 * This class is an interface for the business service implementation class
 * 
 * @author speddyre
 * @date 3rd June 2015
 */
public interface RoleService
{

   String createRole(Role role);

   boolean deleteRole(int role);

   boolean updateRole(Role role);

   List<Role> getRoles();

   List<Role> getRolesByCriteria(Role role);

   Role getUserRole();

   Role getRoleByRoleId(int roleId);

}
