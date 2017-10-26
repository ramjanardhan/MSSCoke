/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.logisticsloadtendering;

import com.mss.ediscv.util.ServiceLocatorException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public interface LogisticsLoadService {

    public ArrayList<LogisticsLoadBean> buildLoadQuery(LogisticsLoadAction logisticsDocAction, HttpServletRequest http) throws ServiceLocatorException;
    public ArrayList<LogisticsLoadBean> buildLoadArchiveQuery(LogisticsLoadAction logisticsDocAction, HttpServletRequest http) throws ServiceLocatorException;

}
