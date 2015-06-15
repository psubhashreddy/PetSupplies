/**
 * 
 */
package com.webshop.core.utils;

import org.apache.commons.lang3.StringUtils;

import com.webshop.core.entity.User;

/**
 * This class is the util method for deep comparison of two objects
 * 
 * @author speddyre
 * @date 11th June 2015
 */
public final class ObjectComparison {

	/**
	 * private constructor for not allowing any other class to instantiate it
	 * from outside
	 */
	private ObjectComparison() {

	}

	/**
	 * This method is for deep comparing the 2 objects and return a boolean
	 * value based on the comparison result.
	 * 
	 * @param obj1
	 * @param obj2
	 * @return boolean
	 */
	public static boolean deepCompareObjects(Object obj1, Object obj2) {
		boolean flag = false;
		if (obj1 instanceof User && obj2 instanceof User) {
			User oldUserObj = (User) obj1;
			User newUserObj = (User) obj2;

			if (!StringUtils.endsWithIgnoreCase(oldUserObj.getFirstName(),
					newUserObj.getFirstName())
					|| !StringUtils.endsWithIgnoreCase(
							oldUserObj.getPassword(), newUserObj.getPassword())
					|| !StringUtils.endsWithIgnoreCase(
							oldUserObj.getLastName(), newUserObj.getLastName())
					|| !StringUtils.endsWithIgnoreCase(oldUserObj.getAddress(),
							newUserObj.getAddress())
					|| !StringUtils.endsWithIgnoreCase(oldUserObj.getCity(),
							newUserObj.getCity())
					|| !StringUtils.endsWithIgnoreCase(oldUserObj.getState(),
							newUserObj.getState())
					|| !StringUtils.endsWithIgnoreCase(oldUserObj.getCountry(),
							newUserObj.getCountry())
					|| !StringUtils.endsWithIgnoreCase(
							oldUserObj.getPostalCode(),
							newUserObj.getPostalCode())) {
				flag = true;
			}
		}

		return flag;
	}
}
