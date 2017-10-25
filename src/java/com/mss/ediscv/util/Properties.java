
/*******************************************************************************
 *
 * Project : Miracle Supply Chain Visibility portal v2.0
 *
 * Package : com.mss.ediscv.general
 *
 * Date    : Mar 11, 2013 1:28:58 PM
 *
 * Author  : Nagireddy seerapu <nseerapu@miraclesoft.com>
 *
 * File    : Properties.java
 *

 * *****************************************************************************
 */
package com.mss.ediscv.util;
import java.util.ResourceBundle;
import java.util.MissingResourceException;

public class Properties {
	private static final String BUNDLE_NAME = "com/mss/ediscv/config/mscvp";
	private static final ResourceBundle RESOURCE_BUNDLE =
		ResourceBundle.getBundle(BUNDLE_NAME);

	public static String getProperty(String property) {
		try {
			return RESOURCE_BUNDLE.getString(property);
		} catch (MissingResourceException e) {
			return '!' + property + '!';
		}
	}
}
