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

import com.webshop.core.entity.Role;
import com.webshop.core.service.RoleService;

/**
 * This class is used for converting the Role list to the list of displayable values in the drop down box
 * 
 * @author Subhash Peddyreddy
 * @date 12th June 2015
 */
@ManagedBean(name = "roleConverter")
@RequestScoped
public class RoleConverter implements Converter
{

   @Inject
   private transient Logger logger = Logger.getLogger(RoleConverter.class.getName());

   @Inject
   private RoleService roleService;

   /*
    * (non-Javadoc)
    * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext , javax.faces.component.UIComponent, java.lang.String)
    */
   @Override
   public Object getAsObject(FacesContext context, UIComponent component, String submittedValue)
   {
      if (StringUtils.isEmpty(submittedValue))
      {
         return null;
      }

      try
      {
         logger.info("submitted Value=" + submittedValue);
         return roleService.getRoleByRoleId(Integer.valueOf(submittedValue));
      }
      catch (NumberFormatException e)
      {
         logger.log(Level.SEVERE, "provided is Not a valid value", e);
         throw new ConverterException(new FacesMessage(String.format(" Is not a valid Role ID", submittedValue)), e);
      }
   }

   /*
    * (non-Javadoc)
    * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext , javax.faces.component.UIComponent, java.lang.Object)
    */
   @Override
   public String getAsString(FacesContext context, UIComponent component, Object value)
   {
      if (value == null)
      {
         return "";
      }

      if (value instanceof Role)
      {
         logger.info(" Value=" + value);
         return String.valueOf(((Role) value).getRoleId());
      }
      else
      {
         throw new ConverterException(new FacesMessage(String.format(" Is not a valid Role", value)));
      }
   }

}
