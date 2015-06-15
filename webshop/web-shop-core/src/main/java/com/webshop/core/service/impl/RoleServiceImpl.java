/**
 * 
 */
package com.webshop.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.webshop.core.dao.RoleDAO;
import com.webshop.core.entity.Role;
import com.webshop.core.service.RoleService;
import com.webshop.core.utils.Constants;
import com.webshop.core.utils.DateConverterUtil;

/**
 * This class is the implementation class for the business interface
 * 
 * @author speddyre
 * @date 3rd June 2015
 */
@Stateless
public class RoleServiceImpl implements RoleService {

	@Inject
	private RoleDAO roleDAO;

	@Inject
	private transient Logger logger;

	/**
	 * This method is for creating a role (non-Javadoc)
	 * 
	 * @see com.webshop.core.service.RoleService#createRole(com.webshop.core.entity.Role)
	 * @param Role
	 * @return String
	 */
	public String createRole(Role role) {
		logger.info("*** In createRole in ServiceImpl ****");
		try {
			Role roleTest = roleDAO.getRoleByRoleName(role.getRoleName());
			if (roleTest == null) {
				role.setLastUpdatedDate(DateConverterUtil.dateToTimeStamp());
				return roleDAO.createRole(role);
			} else {
				return Constants.ROLE_ALREADY_EXISTS;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in Creating Role", e);
			return Constants.ROLE_CREATION_EXCEPTION;
		}
	}

	/**
	 * This method is for deleting a role (non-Javadoc)
	 * 
	 * @see com.webshop.core.service.RoleService#deleteRole(com.webshop.core.entity.Role)
	 * @param Role
	 * @return boolean
	 */
	public boolean deleteRole(int roleId) {
		logger.info("*** In deleteRole in ServiceImpl ****");
		return roleDAO.deleteRole(roleId);
	}

	/**
	 * This method is for updating the role with the modified details
	 * (non-Javadoc)
	 * 
	 * @see com.webshop.core.service.RoleService#updateRole(com.webshop.core.entity.Role)
	 * @param Role
	 * @return boolean
	 */
	public boolean updateRole(Role role) {
		logger.info("*** In updateRole in ServiceImpl ****");
		role.setLastUpdatedDate(DateConverterUtil.dateToTimeStamp());
		return roleDAO.updateRole(role);
	}

	/**
	 * This method is for fetching all the roles (non-Javadoc)
	 * 
	 * @see com.webshop.core.service.RoleService#getRoles()
	 * @return List
	 */
	public List<Role> getRoles() {
		logger.info("*** In getRoles in ServiceImpl ****");
		return roleDAO.getRoles();
	}

	/**
	 * This method is for searching the roles by criteria (non-Javadoc)
	 * 
	 * @see com.webshop.core.service.RoleService#getRolesByCriteria(com.webshop.core.entity.Role)
	 * @param Role
	 * @return List
	 */
	public List<Role> getRolesByCriteria(Role role) {
		logger.info("*** In getRolesByCriteria in ServiceImpl ****");
		try {
			List<Role> roleList = new ArrayList<Role>();
			if (StringUtils.isNotEmpty(role.getRoleName())
					&& StringUtils.isEmpty(role.getRoleDesc())) {
				Role result = roleDAO.getRoleByRoleName(role.getRoleName());
				if (result != null) {
					roleList.add(result);
				}
			} else if (StringUtils.isEmpty(role.getRoleName())
					&& StringUtils.isNotEmpty(role.getRoleDesc())) {
				roleList = roleDAO.getRoleByRoleDescription(role.getRoleDesc());
			} else {
				roleList = roleDAO.getRolesByCriteria(role);
			}
			logger.info("Size of the list = " + roleList.size());
			return roleList;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in Searching Role", e);
			return new ArrayList<Role>();
		}
	}

	/**
	 * This method is for getting the non-admin role id for the new user
	 * creation from the user module
	 * 
	 * @return int
	 */
	public Role getUserRole() {
		logger.info("*** In getUserRole in ServiceImpl ****");
		return roleDAO.getUserRole();
	}

	/**
	 * This method is for fetching Role by role id (non-Javadoc)
	 * 
	 * @see com.webshop.core.service.RoleService#getRoleByRoleId(int)
	 * @param int
	 * @return Role
	 */
	public Role getRoleByRoleId(int roleId) {
		logger.info("*** In getRoleByRoleId in ServiceImpl ****");
		return roleDAO.getRoleByRoleId(roleId);
	}

}
