/**
 * 
 */
package com.webshop.admin.converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.webshop.core.entity.Category;
import com.webshop.core.service.CategoryService;

/**
 * This class is used for converting the Role list to the list of displayable
 * values in the drop down box
 * 
 * @author Subhash Peddyreddy
 * @date 12th June 2015
 *
 */
@ManagedBean(name = "categoryConverter")
@RequestScoped
public class CategoryConverter implements Converter {

	@Inject
	private transient Logger logger = Logger.getLogger(CategoryConverter.class
			.getName());

	@Inject
	private CategoryService categoryService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String valueSubmitted) {
		if (StringUtils.isEmpty(valueSubmitted)) {
			return null;
		}

		try {
			logger.info("submitted Value=" + valueSubmitted);
			return categoryService.getCategoryByCategoryId(Integer
					.valueOf(valueSubmitted));
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE, "provided is Not a valid value", e);
			throw new ConverterException(new FacesMessage(String.format(
					" Is not a valid Category ID", valueSubmitted)), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return "";
		}

		if (value instanceof Category) {
			logger.info("Value=" + value);
			return String.valueOf(((Category) value).getCategoryId());
		} else {
			throw new ConverterException(new FacesMessage(String.format(
					" Is not a valid Category", value)));
		}
	}

}
