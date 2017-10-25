/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.partner;

import com.mss.ediscv.util.ServiceLocatorException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public interface PartnerService {

    public String addPartner(PartnerAction partnerAction) throws ServiceLocatorException;

    public ArrayList<PartnerBean> buildPartnerQuery(PartnerAction partnerAction, HttpServletRequest http) throws ServiceLocatorException;

    public PartnerAction partnerEdit(PartnerAction partnerAction) throws ServiceLocatorException;

    public String editPartner(PartnerAction partnerAction) throws ServiceLocatorException;

    public String addDeliveryChannelInfo(PartnerAction partnerAction) throws ServiceLocatorException;

    public ArrayList<PartnerBean> buildDeliverChannelQuery(PartnerAction partnerAction) throws ServiceLocatorException;

    public PartnerAction deliveryChannelEdit(PartnerAction partnerAction) throws ServiceLocatorException;

    public String editDeliveryChannel(PartnerAction partnerAction) throws ServiceLocatorException;

}
