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
 * File    : WildCardSql.java
 *

 * *****************************************************************************
 */
package com.mss.ediscv.util;
/*
 * The WildCardSql class is to search the data and appending wild cards 
 */
/*public class WildCardSql {
	public static String getWildCardSql(String varName, String varVal) {
		if (varVal.indexOf("*") == -1) {
			return " AND lcase("+ varName + ")=lcase('" + varVal + "') "; 
		}
		else {
			varVal = varVal.replace("*", "%");
			return " AND lcase("+ varName + ") = lcase('" + varVal + "') ";
		}
	}

	



	public static String getWildCardSql1(String varName, String varVal) {
		if (varVal.indexOf("*") == -1) {
			return " AND lcase("+ varName + ") like lcase('%" + varVal + "%') "; 
		}
		else {
			varVal = varVal.replace("*", "%");
			return " AND lcase("+ varName + ") like lcase('" + varVal + "') ";
		}
		
	}
        
        	public static String getWildCardSqlIN(String varName, String varVal) {
		if (varVal.indexOf("*") == -1) {
			return " AND "+ varName + " IN (" + varVal + ") "; 
		}
		else {
			varVal = varVal.replace("*", "%");
			return " AND "+ varName + " IN (" + varVal + ") ";
		}
		
	}
        
}*/ 


public class WildCardSql {
	public static String getWildCardSql(String varName, String varVal) {
		if (varVal.indexOf("*") == -1) {
			return " AND "+ varName + "='" + varVal + "' "; 
		}
		else {
			varVal = varVal.replace("*", "%");
			return " AND "+ varName + " = '" + varVal + "'";
		}
	}

	public static String getWildCardSql1(String varName, String varVal) {
		if (varVal.indexOf("*") == -1) {
			return " AND "+ varName + " like '%" + varVal + "%' "; 
		}
		else {
			varVal = varVal.replace("*", "%");
			return " AND "+ varName + " like '" + varVal + "' ";
		}
		
	}
        
        	public static String getWildCardSqlIN(String varName, String varVal) {
		if (varVal.indexOf("*") == -1) {
			return " AND "+ varName + " IN (" + varVal + ") "; 
		}
		else {
			varVal = varVal.replace("*", "%");
			return " AND "+ varName + " IN (" + varVal + ") ";
		}
		
	}
        
}