/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.ltResponse;

import com.mss.ediscv.util.ServiceLocatorException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public interface LtResponseService {

    public ArrayList<LtResponseBean> getLtResponseList(LtResponse ltResponse, HttpServletRequest http) throws ServiceLocatorException;

}
