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
 * File    : ApplicationConstants.java
 *
 ** *****************************************************************************
 */
package com.mss.ediscv.util;

import com.mss.ediscv.lfc.LifecycleBeans;

public class AppConstants {

    public static final String SES_LOGIN_ID = "loginId";
    public static final String SES_USER_NAME = "userName";
    public static final String SES_ROLE_ID = "roleId";
    public static final String SES_ROLE_NAME = "roleName";
    public static final String SES_LAST_LOGIN_TS = "lastLoginTS";
    public static final String SES_FIRST_DB = "initialDB";

    public static final String SES_USER_ROLES = "userRoles";

    public static final String SES_DOCUMENTS_QUERY = "documentsSearchQuery";
    public static final String SES_PO_LIST = "poSearchList";

    public static final String REQ_RESULT_MSG = "resultMessage";

    public static final String REQ_ERROR_INFO = "errorMessage";

    public static final String REQ_EXCEPTION_MSG = "exceptionMessage";

    // START: Keys for DBGrid functionality
    public static final String REQ_GRD_CURR_POS = "txtCurr";
    public static final String REQ_GRD_SORT_COL = "txtSortCol";
    public static final String REQ_GRD_SORT_ORD = "txtSortAsc";
	// END: Keys for DBGrid functionality

    public static final String PROP_USER_DEF_ROLE = "user.default.roleid";
    public static final String PROP_DS_NAME = "ds.name";
    public static final String PROP_CURRENT_DS_NAME = "current.ds.name";
    public static final String PROP_PROD_DS_NAME = "prod.ds.name";
    public static final String PROP_ARCH_DS_NAME = "arch.ds.name";
	//public static final String PLATFORM_TITLE = "DEV -";
    //public static final String PLATFORM_TITLE = "SIT -";
    public static final String PLATFORM_TITLE = "Demo -";

    public static final String ACTIVE_FLAG = "A";

    // PO and invoce 
    public static final String SES_SHP_QUERY = "shipmentSearchQuery";
    public static final String SES_INV_QUERY = "invoiceSearchQuery";
    public static final String SES_ASN_QUERY = "asnSearchQuery";
    public static final String SES_SC_QUERY = "contentSearchQuery";
    public static final String SES_PAYMENT_QUERY = "paymentSearchQuery";

        //DataSource 
    public static final String DB_USER = "DB.User";
    public static final String DB_PWD = "DB.Password";
    public static final String DB_NAME = "DB.Name";
    public static final String DB_HOST = "DB.Host";
    public static final String DB_Port = "DB.Port";
    public static final String DB_DRIVERTYPE = "DB.DriverType";

    // 
    public static final String SES_SHIPMENT_LIST = "shipmentSearchList";
    public static final String SES_DOC_LIST = "documentList";
    public static final String SES_LOG_DOC_LIST = "logdocumentList";
    public static final String SES_PAYMENT_LIST = "paymentSearchList";
    public static final String SES_INV_LIST = "invoiceList";

    public static final String SES_TP_LIST = "tpList";

    public static final String SES_Lifecycle = "lifecycle";

    //  LifecycleBeans lifecycleBeans=new LifecycleBeans();
    public static final String SES_LifecycleBeansList = "lifecycleBeans";
         //public static final String SES_TP_LIST = "tpList";

    /**
     * New values to display the list of PO's in life cycle *
     */
    public static final String LFC_SES_PO_LIST = "lfcpoSearchList";

    public static final String LFC_SES_ASN_LIST = "lfcasnSearchList";

    public static final String LFC_SES_INVOICE_LIST = "lfcinvoiceSearchList";

    public static final String LFC_SES_PAYMENT_LIST = "lfcPaymentSearchList";

    public static final String CORRELATION_LIST = "correlationAttributeList";

    public static final String DOCMENT_TYPE_MAP = "documentTypeMap";

    public static final String MSCVPROLE = "mscvpRole";

    public static final String SES_TRADINGPARTNER_LIST = "tradingpartnerList";
    public static final String SES_STATES_MAP = "statesMap";

    public static final String SES_USER_LIST = "userList";

    /**
     * to maintain the user flow access
     */
    public static final String SES_USER_FLOW_MAP = "userFlowMap";

    public static final String SES_USER_DEFAULT_FLOWID = "userDefaultFlowID";

    public static final String SES_USER_ROLE_NAME = "userRoleName";
    public static final String SES_ISSUE_LIST = "issuesList";

    public static final String SES_ISSUE_QUERY = "issueSearchQuery";

    public static final String FLOWS_OPTIONS = "flowsMap";
    public static final String SES_LOGSHIPMENT_LIST = "shipmentList";
    public static final String SES_LOAD_LIST = "loadList";
    public static final String SES_LTRESPONSE_LIST = "ltResponseList";
    public static final String SES_LTINVOICE_LIST = "ltInvoiceList";
    public static final String SES_LTSHIPMENT_LIST = "ltShipmentList";
    public static final String CONTEXT_PATH = "ediscv";

    public static final String SES_PARTNER_LIST = "partnerList";
    public static final String SES_ROUTING_LIST = "routingList";
    public static final String SES_B2BCHANNEL_LIST = "b2bChannelList";

    public static final String SES_DOCREPORT_LIST = "documentReportList";
    public static final String SES_DELIVERY_CHANNEL_LIST = "deliverChannelList";
    public static final String SES_SCHDULAR_LIST = "schdularList";

}
