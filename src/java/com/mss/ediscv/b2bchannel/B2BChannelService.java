/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.b2bchannel;

import com.mss.ediscv.util.ServiceLocatorException;
import java.util.ArrayList;

/**
 *
 * @author miracle
 */
public interface B2BChannelService {
    
     public String addB2BChannel(B2BChannelAction b2BChannelAction) throws ServiceLocatorException;
      public ArrayList<B2BChannelBean> buildB2BChannelQuery(B2BChannelAction b2BChannelAction) throws ServiceLocatorException ;
     public B2BChannelAction b2BChannelEdit(B2BChannelAction b2BChannelAction) throws ServiceLocatorException ;
       public String doEditB2BChannel(B2BChannelAction b2BChannelAction) throws ServiceLocatorException;
}
