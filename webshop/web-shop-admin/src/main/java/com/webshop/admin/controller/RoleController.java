/**
 * 
 */
package com.webshop.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.webshop.core.entity.Role;
import com.webshop.core.service.RoleService;
import com.webshop.core.utils.Constants;

/**
 * This class is the controller for managing the role for doing all the crud
 * operations.
 * 
 * @author speddyre
 * @date 3rd June 2015
 */
@ManagedBean(name = "roleController")
@RequestScoped
public class RoleController {

	@Inject
	private RoleService roleService;

	@Inject
	private transient Logger logger;

	private Role role = new Role();
	private List<Role> roleList = new ArrayList<Role>();
	private int roleId;

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the roleList
	 */
	public List<Role> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList
	 *            the roleList to set
	 */
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * Clears the current role bean
	 */
	public void clearForm() {
		this.role = new Role();
	}

	/**
	 * This method is for creating a new role by doing necessary validations.
	 * (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.RoleController#createRole()
	 * @return String @
	 */
	public String createRole() {
		logger.info("**** In createRole in Controller ****");
		if (role != null && StringUtils.isNotEmpty(role.getRoleName())) {
			String creationMessage = roleService.createRole(role);
			if (creationMessage
					.equalsIgnoreCase(Constants.ROLE_CREATION_SUCCESS)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						Constants.ROLE_CREATION_SUCCESS,
						Constants.ROLE_CREATION_SUCCESS);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			} else if (creationMessage
					.equalsIgnoreCase(Constants.ROLE_ALREADY_EXISTS)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						Constants.ROLE_ALREADY_EXISTS,
						Constants.ROLE_ALREADY_EXISTS);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			} else if (creationMessage
					.equalsIgnoreCase(Constants.ROLE_CREATION_EXCEPTION)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						Constants.ROLE_CREATION_EXCEPTION,
						Constants.ROLE_CREATION_EXCEPTION);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			} else if (creationMessage.equalsIgnoreCase(Constants.FAILURE)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						Constants.ROLE_CREATION_FAILURE,
						Constants.ROLE_CREATION_FAILURE);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}
		}
		return Constants.CREATE_ROLE_VIEW;
	}

	/**
	 * This method is for loading the delete view (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.RoleController#deleteView()
	 * @return String @
	 */
	public String deleteView() {
		logger.info("****In deleteView in Controller ****");
		this.role = roleService.getRoleByRoleId(roleId);
		return Constants.DELETE_ROLE_VIEW;
	}

	/**
	 * This method is for deleting the selected role. (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.RoleController#deleteRole()
	 * @return String @
	 */
	public String deleteRole() {
		logger.info("****In deleteRole in Controller ****");
		boolean deleteFlag = roleService.deleteRole(roleId);
		if (deleteFlag) {
			FacesMessage facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					Constants.ROLE_DELETION_SUCCESS,
					Constants.ROLE_DELETION_SUCCESS);
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return searchRole();
	}

	/**
	 * This method is for redirecting the view to the update view by loading the
	 * role details (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.RoleController#editView()
	 * @return String
	 */
	public String editView() {
		logger.info("****In editView in Controller ****");
		this.role = roleService.getRoleByRoleId(roleId);
		return Constants.EDIT_ROLE_VIEW;
	}

	/**
	 * This method is for updating the details of the selected role
	 * (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.RoleController#updateRole(Role)
	 * @param
	 * @return String
	 */
	public String updateRole() {
		logger.info("****In updateRole in Controller ****");
		boolean updateFlag = roleService.updateRole(role);
		if (updateFlag) {
			FacesMessage facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					Constants.ROLE_UPDATION_SUCCESS,
					Constants.ROLE_UPDATION_SUCCESS);
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return searchRole();
	}

	/**
	 * This method is for searching all the roles without any filter
	 * (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.RoleController#searchRole()
	 * @return String @
	 */
	public String searchRole() {
		logger.info("*** In searchRole without criteria in Controller ***");
		roleList = roleService.getRoles();
		return Constants.SEARCH_ROLE_VIEW;

	}

	/**
	 * This method is for searching the roles based on the filter criteria
	 * recieved from the user. (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.RoleController#searchRolesByCriteria()
	 * @return String @
	 */
	public String searchRolesByCriteria() {
		logger.info("*** In searchRole with criteria in Controller ***");
		if (role != null
				&& (StringUtils.isNotEmpty(role.getRoleName()) || StringUtils
						.isNotEmpty(role.getRoleDesc()))) {
			roleList = roleService.getRolesByCriteria(role);
		} else {
			FacesMessage facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_INFO, Constants.NO_SEARCH_CRITERIA,
					Constants.NO_SEARCH_CRITERIA);
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return Constants.SEARCH_ROLE_VIEW;
	}
}
