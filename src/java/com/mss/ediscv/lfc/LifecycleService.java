/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.lfc;
import com.mss.ediscv.lfc.*;
import com.mss.ediscv.util.ServiceLocatorException;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author miracle
 */
public interface LifecycleService {
    
    
      // public LifecycleBeans buildLifeCycleBeans(LifecycleAction docbean,HttpServletRequest httpServletRequest)throws ServiceLocatorException; 
     public void buildLifeCycleBeans(LifecycleAction docbean,HttpServletRequest httpServletRequest)throws ServiceLocatorException; 
    
}

