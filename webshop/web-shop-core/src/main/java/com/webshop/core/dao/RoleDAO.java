/**
 * 
 */
package com.webshop.core.dao;

import java.util.List;

import com.webshop.core.entity.Role;

/**
 * This class is the dao invoked form the business implementation layer
 * 
 * @author speddyre
 * @date 3rd June 2015
 */
public interface RoleDAO
{

   String createRole(Role role);

   boolean deleteRole(int role);

   boolean updateRole(Role role);

   List<Role> getRoles();

   List<Role> getRolesByCriteria(Role role);

   Role getRoleByRoleName(String roleName);

   List<Role> getRoleByRoleDescription(String roleDesc);

   Role getUserRole();

   Role getRoleByRoleId(int roleId);

}
