/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.logisticreports;

//import com.mss.ediscv.reports.*;
import com.mss.ediscv.util.ServiceLocatorException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author miracle
 */
public interface LogisticReportsService {

    public ArrayList<LogisticReportsBean> getDocumentList(LogisticReportsAction logisticreportsAction, String roleId, HttpSession hsession, HttpServletRequest http) throws ServiceLocatorException;

}
