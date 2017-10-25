/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.routing;

import com.mss.ediscv.util.ServiceLocatorException;
import java.util.ArrayList;

/**
 *
 * @author miracle
 */
public interface RoutingService {
      public String addRouting(RoutingAction routingAction) throws ServiceLocatorException;
         public ArrayList<RoutingBean> buildRoutingQuery(RoutingAction routingAction) throws ServiceLocatorException ;
         public RoutingAction getRouting(RoutingAction routingAction) throws ServiceLocatorException ;
          public String editRouting(RoutingAction routingAction) throws ServiceLocatorException;
}
