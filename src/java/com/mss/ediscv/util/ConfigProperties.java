/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.util;

/**
 *
 * @author miracle
 */

import java.util.ResourceBundle;
import java.util.MissingResourceException;

public class ConfigProperties {
    private static final String BUNDLE_NAME = "com/mss/ediscv/config/config";
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
