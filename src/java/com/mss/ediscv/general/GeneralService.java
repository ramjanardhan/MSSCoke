
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
 * File    : GeneralService.java
 *
 * 
 * *****************************************************************************
 */
package com.mss.ediscv.general;

import java.util.Map;

import com.mss.ediscv.util.ServiceLocatorException;

public interface GeneralService {
	public UserInfoBean getUserInfo(String loginId,String dsnName)throws ServiceLocatorException;
	public Map<Integer,Integer> getUserRoles(int userId,String dsnName) throws ServiceLocatorException;
}
