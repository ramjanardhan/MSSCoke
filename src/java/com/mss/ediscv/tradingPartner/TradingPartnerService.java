/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.tradingPartner;

import com.mss.ediscv.util.ServiceLocatorException;
import java.util.ArrayList;

/**
 *
 * @author miracle1
 */
public interface TradingPartnerService {
    public String addTP(TradingPartnerAction tpAction) throws ServiceLocatorException;
    public ArrayList<TradingPartnerBean> buildTradingQuery(TradingPartnerAction tradingPartnerAction) throws ServiceLocatorException ;
     public TradingPartnerAction tpEdit(TradingPartnerAction tradingPartnerAction) throws ServiceLocatorException;
     public String editTP(TradingPartnerAction tpAction) throws ServiceLocatorException;
}
