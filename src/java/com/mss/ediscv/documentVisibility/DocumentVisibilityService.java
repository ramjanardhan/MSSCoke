/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.documentVisibility;

import com.mss.ediscv.util.ServiceLocatorException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public interface DocumentVisibilityService {
      public ArrayList<DocumentVisibilityBean> buildDocumentQuery(DocumentVisibilityAction documentVisibilityAction,HttpServletRequest http)throws ServiceLocatorException;
}
