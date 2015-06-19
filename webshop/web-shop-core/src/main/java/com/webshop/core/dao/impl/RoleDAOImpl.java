/**
 * 
 */
package com.webshop.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.webshop.core.dao.RoleDAO;
import com.webshop.core.entity.Role;
import com.webshop.core.utils.Constants;

/**
 * This class is the dao implementation class interacting with the database for all crud operations
 * 
 * @author speddyre
 * @date 3rd June 2015
 */
public class RoleDAOImpl implements RoleDAO
{

   @PersistenceContext(unitName = "defaultPersistenceUnit")
   private EntityManager em;

   @Inject
   private transient Logger logger;

   /**
    * This method is for creating a role (non-Javadoc)
    * 
    * @see com.webshop.core.dao.RoleDAO#createRole(Role)
    * @param Role
    * @return String
    */
   public String createRole(Role role)
   {
      logger.info("**** In createRole in DAOImpl*****");
      try
      {
         em.persist(role);
         return Constants.ROLE_CREATION_SUCCESS;
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Creating Role", e);
         return Constants.ROLE_CREATION_EXCEPTION;
      }
   }

   /**
    * This method is for deleting a role (non-Javadoc)
    * 
    * @see com.webshop.core.dao.RoleDAO#deleteRole(Role)
    * @param Role
    * @return boolean
    */
   public boolean deleteRole(int roleId)
   {
      logger.info("**** In deleteRole in DAOImpl*****");
      Role role = getRoleByRoleId(roleId);
      em.remove(role);
      return true;
   }

   /**
    * This method is for updating the role details with the modified once (non-Javadoc)
    * 
    * @see com.webshop.core.dao.RoleDAO#updateRole(com.webshop.core.entity.Role)
    * @param Role
    * @return boolean
    */
   public boolean updateRole(Role role)
   {
      logger.info("**** In updateRole in DAOImpl*****");
      em.merge(role);
      return true;
   }

   /**
    * This method is for fetching all the roles (non-Javadoc)
    * 
    * @see com.webshop.core.dao.RoleDAO#getRoles()
    * @return List
    */
   public List<Role> getRoles()
   {
      logger.info("**** In getRoles in DAOImpl*****");
      Query query = em.createNamedQuery("findAllRoles", Role.class);
      return (List<Role>) query.getResultList();
   }

   /**
    * This method is for searching a role by role id (non-Javadoc)
    * 
    * @see com.webshop.core.dao.RoleDAO#getRoleByRoleId(long)
    * @param long
    * @return Role
    */
   public Role getRoleByRoleId(int roleId)
   {
      logger.info("**** In getRoleByRoleId in DAOImpl*****");
      try
      {
         Query query = em.createNamedQuery("findRoleById", Role.class).setParameter(Constants.ROLE_ID, roleId);
         return (Role) query.getSingleResult();
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Searching Role By Id", e);
         return null;
      }
   }

   /**
    * This method is for searching a role by role name (non-Javadoc)
    * 
    * @see com.webshop.core.dao.RoleDAO#getRoleByRoleName(String)
    * @param String
    * @return Role
    */
   public Role getRoleByRoleName(String roleName)
   {
      logger.info("**** In getRoleByRoleName in DAOImpl*****");
      try
      {
         Query query = em.createNamedQuery("findRoleByRoleName", Role.class).setParameter(Constants.ROLE_NAME, roleName);
         return (Role) query.getSingleResult();
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Searching Role By Name", e);
         return null;
      }
   }

   /**
    * This method is for searching roles by roles description (non-Javadoc)
    * 
    * @see com.webshop.core.dao.RoleDAO#getRoleByRoleDescription(String)
    * @param String
    * @return List
    */
   public List<Role> getRoleByRoleDescription(String roleDescrption)
   {
      logger.info("**** In getRoleByRoleDescription in DAOImpl*****");
      try
      {
         Query query = em.createNamedQuery("findRoleByRoleDescription", Role.class).setParameter(Constants.ROLE_DESC, Constants.PERCENTAGE + roleDescrption + Constants.PERCENTAGE);
         return (List<Role>) query.getResultList();
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Searching Role By Description", e);
         return new ArrayList<Role>();
      }
   }

   /**
    * This method is for searching the roles by criteria (non-Javadoc)
    * 
    * @see com.webshop.core.dao.RoleDAO#getRolesByCriteria(Role)
    * @param Role
    * @return List
    */
   public List<Role> getRolesByCriteria(Role role)
   {
      logger.info("**** In getRolesByCriteria in DAOImpl*****");
      Query query = em.createNamedQuery("findRoleByCriteria", Role.class).setParameter(Constants.ROLE_NAME, role.getRoleName())
            .setParameter(Constants.ROLE_DESC, Constants.PERCENTAGE + role.getRoleDesc() + Constants.PERCENTAGE);
      return (List<Role>) query.getResultList();
   }

   /**
    * This method is for getting the role id of the user role for the new user creation.
    * 
    * @return int
    */
   public Role getUserRole()
   {
      try
      {
         return getRoleByRoleName(Constants.USER_TYPE);
      }
      catch (Exception e)
      {
         logger.log(Level.SEVERE, "Exception in Searching Role By User Role", e);
         return null;
      }
   }
}
