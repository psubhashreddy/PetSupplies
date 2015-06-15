/**
 * 
 */
package com.webshop.user.util;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webshop.core.entity.User;
import com.webshop.core.utils.Constants;

/**
 * This class is for filtering the unauthorised access to the view pages
 * 
 * @author speddyre
 * @date 3rd June 2015
 */
public class LoginAuthFilter implements Filter {

	@Inject
	private transient Logger logger;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {

	}

	/**
	 * This method is for checking user aunthentication to access the xhtml
	 * files without login (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * @param request
	 *            , response filterChain
	 * @throws IOException
	 *             , ServletException
	 */
	public final void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain filterChain)
			throws IOException, ServletException {

		logger.info(" *** In LoginAuthFilter ***");
		User user = (User) ((HttpServletRequest) request).getSession()
				.getAttribute(Constants.LOGGED_IN_USER);

		if (user == null) {
			logger.info(" *** No User logged in ***");
			String contextPath = ((HttpServletRequest) request)
					.getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath
					+ "/login.xhtml");
		} else {
			logger.info(" *** User logged in ***");
			HttpServletResponse httpsResponse = (HttpServletResponse) response;

			httpsResponse.setHeader("Cache-Control",
					"no-cache, no-store, must-revalidate");
			httpsResponse.setHeader("Pragma", "no-cache");
			httpsResponse.setDateHeader("Expires", 0);

			filterChain.doFilter(request, response);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
