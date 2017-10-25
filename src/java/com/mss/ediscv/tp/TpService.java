/*
 ******************************************************************************
 *
 * Project : Miracle Supply Chain Visibility portal v2.0
 *
 * Package : com.mss.mscvp.util
 *
 * Date    : Mar 11, 2013 1:43:29 PM
 *
 * Author  : Nagireddy seerapu <nseerapu@miraclesoft.com>
 *
 * File    : TpService.java
 *
 * 
 * *****************************************************************************
 */
package com.mss.ediscv.tp;

//import com.mss.ediscv.shipment.ShipmentSearchAction;
import com.mss.ediscv.util.ServiceLocatorException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author miracle
 *
 */
public interface TpService {

	//public List<ShipmentBean> buildshipmentSQuery(ShipmentSearchAction shipmentSearchbean)throws ServiceLocatorException; 
    public String addTP(TpAction tpAction) throws ServiceLocatorException;
    public ArrayList getTpList(TpAction tpAction) throws ServiceLocatorException;
}