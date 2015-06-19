/**
 * 
 */
package com.webshop.core.utils;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * This class is the logger producer to inject in other class through out the application
 * 
 * @author speddyre
 * @date 5th June 2015
 */
public class ApplicationLogger
{

   /**
    * This method is for proving logger to inject in the required class
    * 
    * @param injectionPoint
    * @return
    */
   @Produces
   public java.util.logging.Logger appLogger(InjectionPoint injectionPoint)
   {
      return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
   }

}
