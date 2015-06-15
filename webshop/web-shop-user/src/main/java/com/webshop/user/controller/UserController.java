/**
 * 
 */
package com.webshop.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.webshop.core.entity.Role;
import com.webshop.core.entity.User;
import com.webshop.core.service.RoleService;
import com.webshop.core.service.UserService;
import com.webshop.core.utils.Constants;
import com.webshop.core.utils.ObjectComparison;

/**
 * This class is the controller for managing all crud operations done on the
 * user
 * 
 * @author speddyre
 * @date 2nd June 2015
 */
@ManagedBean(name = "userController")
@RequestScoped
public class UserController {

	@Inject
	private UserService userService;

	@Inject
	private RoleService roleService;

	@Inject
	private transient Logger logger;

	private User user = new User();
	private List<User> userList = new ArrayList<User>();
	private int userId;
	private int roleId;

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the userList
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * @param userList
	 *            the userList to set
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
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
	 * This method is for creating a new user by doing necessary validations.
	 * (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.UserController#createUser()
	 * @return String @
	 */
	public String createUser() {
		logger.info("**** In createUser in Controller ****");
		if (user != null && StringUtils.isNotEmpty(user.getUserName())) {
			user.setRole(getUserRole());
			String creationMessage = userService.createUser(user);
			if (creationMessage
					.equalsIgnoreCase(Constants.USER_CREATION_SUCCESS)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						Constants.USER_CREATION_SUCCESS,
						Constants.USER_CREATION_SUCCESS);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			} else if (creationMessage
					.equalsIgnoreCase(Constants.USER_ALREADY_EXISTS)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						Constants.USER_ALREADY_EXISTS,
						Constants.USER_ALREADY_EXISTS);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			} else if (creationMessage
					.equalsIgnoreCase(Constants.USER_CREATION_EXCEPTION)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						Constants.USER_CREATION_EXCEPTION,
						Constants.USER_CREATION_EXCEPTION);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			} else if (creationMessage.equalsIgnoreCase(Constants.FAILURE)) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						Constants.USER_CREATION_FAILURE,
						Constants.USER_CREATION_FAILURE);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}
		}
		return Constants.CREATE_NEW_USER;
	}

	/**
	 * This method is for getting the role id of the user role
	 * 
	 * @return
	 */
	public Role getUserRole() {
		return roleService.getUserRole();
	}

	/**
	 * This method is for deleting a selected user. (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.UserController#createUser()
	 * @return String
	 */
	public String deleteUser() {
		logger.info("**** In deleteUser in Controller ****");
		return Constants.SEARCH_USER_VIEW;
	}

	/**
	 * This method is for deleting a selected user. (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.UserController#editUser()
	 * @return String
	 */
	public String editUserView() {
		logger.info("**** In editUser in Controller ****");
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(false);
		setUser((User) httpSession.getAttribute(Constants.LOGGED_IN_USER));
		this.roleId = this.user.getRole().getRoleId();
		return Constants.UPDATE_USER_VIEW;
	}

	public String updateUser() {
		logger.info("**** In updateUser in Controller ****");
		User userOld = (User) ((HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false))
				.getAttribute(Constants.LOGGED_IN_USER);
		if (ObjectComparison.deepCompareObjects(userOld, user)) {
			user.setRole(roleService.getRoleByRoleId(roleId));
			boolean flag = userService.updateUser(user);
			if (flag) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						Constants.USER_UPDATION_SUCCESS,
						Constants.USER_UPDATION_SUCCESS);
				FacesContext.getCurrentInstance().getExternalContext()
						.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}
		} else {
			FacesMessage facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_INFO, Constants.NO_UPDATE_FOUND,
					Constants.NO_UPDATE_FOUND);
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}

		return Constants.UPDATE_USER_VIEW;
	}

	/**
	 * This method is for searching all the users wthout any criteria
	 * (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.UserController#searchUser()
	 * @return String
	 */
	public String searchUser() {
		logger.info("**** In searchUser in Controller ****");
		userList = userService.getUserList();
		return Constants.SEARCH_USER_VIEW;
	}

	/**
	 * This method is for searching a user based on the user criteria.
	 * (non-Javadoc)
	 * 
	 * @see com.webshop.admin.controller.UserController#searchUsersByCriteria()
	 * @return String
	 */
	public String searchUsersByCriteria() {
		logger.info("**** In searchUsersByCriteria in Controller ****");
		if (user != null
				&& (StringUtils.isNotEmpty(user.getUserName())
						|| StringUtils.isNotEmpty(user.getFirstName())
						|| StringUtils.isNotEmpty(user.getEmail()) || StringUtils
							.isNotEmpty(user.getPhone()))) {
			userList = userService.searchUsersByCriteria(user);
		} else {
			FacesMessage facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_INFO, Constants.NO_SEARCH_CRITERIA,
					Constants.NO_SEARCH_CRITERIA);
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
		return Constants.SEARCH_USER_VIEW;
	}

}
