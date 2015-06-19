/**
 * 
 */
package com.webshop.user.util;

/**
 * This class is used to generate the alpha numeric no.
 * 
 * @author speddyre
 * @16th June 2015
 */
public final class NumericGenerator
{

   private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

   private NumericGenerator()
   {
   }

   /**
    * This method is for generating the random alpha numeric no
    * 
    * @param int
    * @return String
    */
   public static String generateAlphaNumericNumeric()
   {
      int count = 10;
      StringBuilder builder = new StringBuilder();
      while (count-- != 0)
      {
         int character = (int) (Math.random() * ALPHA_NUMERIC.length());
         builder.append(ALPHA_NUMERIC.charAt(character));
      }
      return builder.toString();
   }

}
