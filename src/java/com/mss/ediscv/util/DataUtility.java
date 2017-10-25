/*******************************************************************************
 *
 * Project : Miracle Supply Chain Visibility portal v2.0
 *
 * Package : com.mss.mscvp.general
 *
 * Date    : Mar 11, 2013 1:28:58 PM
 *
 * Author  : Nagireddy seerapu <nseerapu@miraclesoft.com>
 *
 * File    : DataUtility.java
 *
 * *****************************************************************************
 */
package com.mss.ediscv.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DataUtility {
	
	public static DataUtility _instance;
	private DataUtility(){
		
	}
	
	public static DataUtility getInstance()throws ServiceLocatorException {
		if(_instance == null){
			_instance = new DataUtility();
		}
		return _instance;
	}

	// This method is used for getting the Map object keys in the format of
	// String array
	public String[] readMapKeys(Map<String, String> mapObj)
			throws ServiceLocatorException {
		
		String keysArray[] = new String[mapObj.size()];
		
		Set<String> keysSet = mapObj.keySet();
		Iterator<String> keysItr = keysSet.iterator();
		
		int index = 0;
		while (keysItr.hasNext()) {
			keysArray[index] = (String) keysItr.next();
			index++;
		}
		
		return keysArray;
	}

}
