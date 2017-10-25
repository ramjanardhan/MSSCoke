/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.tradingPartner;

import com.mss.ediscv.util.AppConstants;
import com.mss.ediscv.util.AuthorizationManager;
import com.mss.ediscv.util.DataSourceDataProvider;
import com.mss.ediscv.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author miracle1
 */
public class TradingPartnerAction extends ActionSupport implements
ServletRequestAware{
private HttpServletRequest httpServletRequest;
private String id;
private String commId;
private String commName;
private String phno;
private String email;
private String address;
private String city;
private String state;
private String zip;
private String qualifier;
private String network;
private String asurl;
private String ascert;
private String version;
private String vendorNo;
private String deptNo;
private String duns;
private String orderDuns;
private String shipDuns;
private String billingDuns;
private String buyerId;
private String resultType;
private String createdBy;
private Map statesMap;
private String tppageId;

private String tpid;
private String tpname;
private List<TradingPartnerBean> tradingList;
private String formAction;

private String tpType;
private String tpStatus;

/*
 * Newly added fields for new TP Changes
 */

private String url;
private String basic;
private String soq;
private String store;
private String master;
private String developing;
private String payDuns;
private String buyerName;
private String buyerPhone;
private String buyerEmail;
private String csName;
private String csPhone;
private String csEmail;
private String tradingPartnerName;
private String contactName;
private String bvrUdiCommId;
private String bvrUdiName;
private String defaultFlowId;
private String notes;
        
   private static Logger logger = Logger.getLogger(TradingPartnerAction.class
			.getName());
        
        public String prepare() throws Exception {
            
           // System.out.println("in tp Action----");
		logger.info("Entered into the ::::TradingPartnerAction :::: prepare ");
		setResultType(LOGIN);
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                   setResultType("accessFailed");
                   int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
                 if(AuthorizationManager.getInstance().isAuthorizedUser("L_TP_CREATE",userRoleId)){  
		HttpSession httpSession = getHttpServletRequest().getSession(false);
		try {
                    
                    httpSession.removeAttribute(AppConstants.SES_PAYMENT_LIST);
                    httpSession.removeAttribute(AppConstants.SES_SHIPMENT_LIST);
                    httpSession.removeAttribute(AppConstants.SES_DOC_LIST);
                    httpSession.removeAttribute(AppConstants.SES_INV_LIST);
                    httpSession.removeAttribute(AppConstants.SES_PO_LIST);
                  
                    httpSession.removeAttribute(AppConstants.SES_TRADINGPARTNER_LIST);
                   
			//setStatesMap(DataSourceDataProvider.getInstance().getStates());
                        setStatesMap((HashMap)httpSession.getAttribute(AppConstants.SES_STATES_MAP));
                        setFormAction("../tradingPartner/doAddTradingPartner.action");
			setResultType(SUCCESS);
                        setTppageId("0");
                        
		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.error("problem :: " + ex.getMessage()
						+ " method name :: prepare() :: class name :: "
						+ getClass().getName());
			}
			getHttpServletRequest().getSession(false).setAttribute(
					AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
			setResultType("error");
		}
	       }
               }
		logger.info("End of ::::TradingPartnerAction :::: prepare ");
		return getResultType();
	}

        /**
         * 
         * @return
         * @throws Exception 
         */
        
     public String doAdd() throws Exception {
            
           // System.out.println("in tp Action----");
		logger.info("Entered into the ::::TradingPartnerAction :::: prepare ");
		setResultType(LOGIN);
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                   setResultType("accessFailed");
                   int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
                 if(AuthorizationManager.getInstance().isAuthorizedUser("L_TP_CREATE",userRoleId)){  
		HttpSession httpSession = getHttpServletRequest().getSession(false);
		try {
                   prepare();
                    setDeveloping("Y");
                    setUrl("http://");
        } catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.error("problem :: " + ex.getMessage()
						+ " method name :: prepare() :: class name :: "
						+ getClass().getName());
			}
			getHttpServletRequest().getSession(false).setAttribute(
					AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
			setResultType("error");
		}
	       }
               }
		logger.info("End of ::::TradingPartnerAction :::: prepare ");
		return getResultType();
	}
     
     
public String doAddTP() throws Exception
        {
            resultType = LOGIN;
	       if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                   setResultType("accessFailed");
                   int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
                 if(AuthorizationManager.getInstance().isAuthorizedUser("L_TP_CREATE",userRoleId)){  
		try {
                        
			if (logger.isDebugEnabled()) {
				logger
						.info("Entered into the ::::TradingPartnerAction :::: doaddTP ");
			}
			
			
                        String responseString ="";
                        //System.out.println("Default FlowId-->"+httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString());
		        setDefaultFlowId(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString());     
                        setCreatedBy(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_LOGIN_ID).toString());  
			 responseString = ServiceLocator.getTradingPartnerService().addTP(this);
                         prepare();
			httpServletRequest.setAttribute(AppConstants.REQ_RESULT_MSG,responseString);
                                resetValues();
                                
			resultType = SUCCESS;

		}  catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger
						.error("problem :: "
								+ ex.getMessage()
								+ " method name :: getaddTp() :: class name :: "
								+ getClass().getName());
			}
			httpServletRequest.getSession(false).setAttribute(
					AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
			resultType = "error";
		}
	       }
               }
		logger.info("End of ::::TPAction :::: doAddTP ");
		return resultType;
        }

public String getTradingSearchQuery() throws Exception {
          //  System.out.println("getQuery---");
		resultType = LOGIN;
                
				logger.info("Entered into the ::::TradingPartnerAction :::: getTradingSearchQuery ");
			
	       if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                   
                   resultType = "accessFailed";
                   int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
                 
                   if(AuthorizationManager.getInstance().isAuthorizedUser("L_TP_SEARCH",userRoleId)){  
		try {
			
			
			//prepare();
                        setDefaultFlowId(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString());     
                            setTradingList(ServiceLocator.getTradingPartnerService().buildTradingQuery(this));
                            httpServletRequest.getSession(false).setAttribute(AppConstants.SES_TRADINGPARTNER_LIST, getTradingList());
                       
			//ttpServletRequest.getSession(false).setAttribute(AppConstants.SES_TRADINGPARTNER_LIST, getTradingList());
                       
			resultType = SUCCESS;

		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger
						.error("problem :: "
								+ ex.getMessage()
								+ " method name :: getPOSearchQuery() :: class name :: "
								+ getClass().getName());
			}
			httpServletRequest.getSession(false).setAttribute(
					AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
			resultType = "error";
		}
	       }
               }
		logger.info("End of ::::TradingPartnerAction :::: getTradingSearchQuery ");
		return resultType;
	}


public String backToSearchList() throws Exception {
                    setResultType("accessFailed");
                   int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
                 if(AuthorizationManager.getInstance().isAuthorizedUser("L_TP_SEARCH",userRoleId)){  
                        getTradingSearchQuery();
                    setResultType(SUCCESS);
                 }
    return getResultType();
}
        
public String tpEdit() throws Exception
        {
            resultType = LOGIN;
	       if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                    setResultType("accessFailed");
                   int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
                 if(AuthorizationManager.getInstance().isAuthorizedUser("L_TP_SEARCH",userRoleId)){ 
		try {
                        
			if (logger.isDebugEnabled()) {
				logger
						.info("Entered into the ::::TradingPartnerAction :::: tpEdit ");
			}
                       
			//prepare();
                     
                        String responseString ="";
                         setDefaultFlowId(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_DEFAULT_FLOWID).toString());     
                        //setStatesMap(DataSourceDataProvider.getInstance().getStates());
                        setStatesMap((HashMap)httpServletRequest.getSession(false).getAttribute(AppConstants.SES_STATES_MAP));
		             // setCreatedBy(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_LOGIN_ID).toString());  
			 ServiceLocator.getTradingPartnerService().tpEdit(this);
			//httpServletRequest.setAttribute(AppConstants.REQ_RESULT_MSG,responseString);
                               // resetValues();
                         setFormAction("../tradingPartner/doEditTradingPartner.action");
                             setTppageId("1");   
                             //for setting search variables
                             setSearchValues();
			resultType = SUCCESS;

		}  catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger
						.error("problem :: "
								+ ex.getMessage()
								+ " method name :: getaddTp() :: class name :: "
								+ getClass().getName());
			}
			httpServletRequest.getSession(false).setAttribute(
					AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
			resultType = "error";
		}
	       }
               }
		logger.info("End of ::::TPAction :::: doAddTP ");
		return resultType;
        }

public void setSearchValues() {
    setTpid(getTpid());
   setTpname(getTpname());
}
public String doEditTradingPartner() throws Exception
        {
            resultType = LOGIN;
	       if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                    setResultType("accessFailed");
                   int userRoleId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_ROLE_ID).toString());
                 if(AuthorizationManager.getInstance().isAuthorizedUser("L_TP_SEARCH",userRoleId)){ 
		try {
                        
			if (logger.isDebugEnabled()) {
				logger
						.info("Entered into the ::::TradingPartnerAction :::: doEditTradingPartner ");
			}
			
			
                         //setStatesMap((HashMap)httpServletRequest.getSession(false).getAttribute(AppConstants.SES_STATES_MAP));
                        String responseString ="";
		              setCreatedBy(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_LOGIN_ID).toString());  
			 responseString = ServiceLocator.getTradingPartnerService().editTP(this);
                         prepare();
                         setFormAction("../tradingPartner/doEditTradingPartner.action");
                         setSearchValues();
                         //getTradingSearchQuery();
			httpServletRequest.setAttribute(AppConstants.REQ_RESULT_MSG,responseString);
                               // resetValues();
                                setTppageId("1");   
                                
			resultType = SUCCESS;

		}  catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger
						.error("problem :: "
								+ ex.getMessage()
								+ " method name :: getaddTp() :: class name :: "
								+ getClass().getName());
			}
			httpServletRequest.getSession(false).setAttribute(
					AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
			resultType = "error";
		}
	       }
               }
		logger.info("End of ::::TPAction :::: doAddTP ");
		return resultType;
        }

public void resetValues() {
        setCommId("");
        setCommName("");
        setTradingPartnerName("");
        setContactName("");
        setBvrUdiCommId("");
        setBvrUdiName("");
        setPhno("");
        setEmail("");
        setAddress("");
        setCity("");
        setState("-1");
        setZip("");
        setNetwork("-1");
        setUrl("");
        setBasic("false");
        setSoq("false");
        setStore("false");
        setMaster("false");
        setDeveloping("Y");
        setVendorNo("");
        setOrderDuns("");
        setShipDuns("");
        setPayDuns("");
        setDeptNo("");
        setBuyerName("");
        setBuyerPhone("");
        setBuyerEmail("");
        setCsName("");
        setCsPhone("");
        setCsEmail("");
        setNotes("");
}

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * @return the commId
     */
    public String getCommId() {
        return commId;
    }

    /**
     * @param commId the commId to set
     */
    public void setCommId(String commId) {
        this.commId = commId;
    }

    /**
     * @return the commName
     */
    public String getCommName() {
        return commName;
    }

    /**
     * @param commName the commName to set
     */
    public void setCommName(String commName) {
        this.commName = commName;
    }

    /**
     * @return the phno
     */
    public String getPhno() {
        return phno;
    }

    /**
     * @param phno the phno to set
     */
    public void setPhno(String phno) {
        this.phno = phno;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the qualifier
     */
    public String getQualifier() {
        return qualifier;
    }

    /**
     * @param qualifier the qualifier to set
     */
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    /**
     * @return the network
     */
    public String getNetwork() {
        return network;
    }

    /**
     * @param network the network to set
     */
    public void setNetwork(String network) {
        this.network = network;
    }

    /**
     * @return the asurl
     */
    public String getAsurl() {
        return asurl;
    }

    /**
     * @param asurl the asurl to set
     */
    public void setAsurl(String asurl) {
        this.asurl = asurl;
    }

    /**
     * @return the ascert
     */
    public String getAscert() {
        return ascert;
    }

    /**
     * @param ascert the ascert to set
     */
    public void setAscert(String ascert) {
        this.ascert = ascert;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the vendorNo
     */
    public String getVendorNo() {
        return vendorNo;
    }

    /**
     * @param vendorNo the vendorNo to set
     */
    public void setVendorNo(String vendorNo) {
        this.vendorNo = vendorNo;
    }

    /**
     * @return the deptNo
     */
    public String getDeptNo() {
        return deptNo;
    }

    /**
     * @param deptNo the deptNo to set
     */
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    /**
     * @return the duns
     */
    public String getDuns() {
        return duns;
    }

    /**
     * @param duns the duns to set
     */
    public void setDuns(String duns) {
        this.duns = duns;
    }

    /**
     * @return the orderDuns
     */
    public String getOrderDuns() {
        return orderDuns;
    }

    /**
     * @param orderDuns the orderDuns to set
     */
    public void setOrderDuns(String orderDuns) {
        this.orderDuns = orderDuns;
    }

    /**
     * @return the shipDuns
     */
    public String getShipDuns() {
        return shipDuns;
    }

    /**
     * @param shipDuns the shipDuns to set
     */
    public void setShipDuns(String shipDuns) {
        this.shipDuns = shipDuns;
    }

    /**
     * @return the billingDuns
     */
    public String getBillingDuns() {
        return billingDuns;
    }

    /**
     * @param billingDuns the billingDuns to set
     */
    public void setBillingDuns(String billingDuns) {
        this.billingDuns = billingDuns;
    }

    /**
     * @return the buyerId
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * @param buyerId the buyerId to set
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * @return the resultType
     */
    public String getResultType() {
        return resultType;
    }

    /**
     * @param resultType the resultType to set
     */
    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the statesMap
     */
    public Map getStatesMap() {
        return statesMap;
    }

    /**
     * @param statesMap the statesMap to set
     */
    public void setStatesMap(Map statesMap) {
        this.statesMap = statesMap;
    }

    /**
     * @return the tppageId
     */
    public String getTppageId() {
        return tppageId;
    }

    /**
     * @param tppageId the tppageId to set
     */
    public void setTppageId(String tppageId) {
        this.tppageId = tppageId;
    }

    /**
     * @return the tpid
     */
    public String getTpid() {
        return tpid;
    }

    /**
     * @param tpid the tpid to set
     */
    public void setTpid(String tpid) {
        this.tpid = tpid;
    }

    /**
     * @return the tpname
     */
    public String getTpname() {
        return tpname;
    }

    /**
     * @param tpname the tpname to set
     */
    public void setTpname(String tpname) {
        this.tpname = tpname;
    }

    /**
     * @return the tradingList
     */
    public List<TradingPartnerBean> getTradingList() {
        return tradingList;
    }

    /**
     * @param tradingList the tradingList to set
     */
    public void setTradingList(List<TradingPartnerBean> tradingList) {
        this.tradingList = tradingList;
    }

    /**
     * @return the formAction
     */
    public String getFormAction() {
        return formAction;
    }

    /**
     * @param formAction the formAction to set
     */
    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the tpType
     */
    public String getTpType() {
        return tpType;
    }

    /**
     * @param tpType the tpType to set
     */
    public void setTpType(String tpType) {
        this.tpType = tpType;
    }

    /**
     * @return the tpStatus
     */
    public String getTpStatus() {
        return tpStatus;
    }

    /**
     * @param tpStatus the tpStatus to set
     */
    public void setTpStatus(String tpStatus) {
        this.tpStatus = tpStatus;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the basic
     */
    public String getBasic() {
        return basic;
    }

    /**
     * @param basic the basic to set
     */
    public void setBasic(String basic) {
        this.basic = basic;
    }

    /**
     * @return the soq
     */
    public String getSoq() {
        return soq;
    }

    /**
     * @param soq the soq to set
     */
    public void setSoq(String soq) {
        this.soq = soq;
    }

    /**
     * @return the store
     */
    public String getStore() {
        return store;
    }

    /**
     * @param store the store to set
     */
    public void setStore(String store) {
        this.store = store;
    }

    /**
     * @return the master
     */
    public String getMaster() {
        return master;
    }

    /**
     * @param master the master to set
     */
    public void setMaster(String master) {
        this.master = master;
    }

    /**
     * @return the developing
     */
    public String getDeveloping() {
        return developing;
    }

    /**
     * @param developing the developing to set
     */
    public void setDeveloping(String developing) {
        this.developing = developing;
    }

    /**
     * @return the payDuns
     */
    public String getPayDuns() {
        return payDuns;
    }

    /**
     * @param payDuns the payDuns to set
     */
    public void setPayDuns(String payDuns) {
        this.payDuns = payDuns;
    }

    /**
     * @return the buyerName
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * @param buyerName the buyerName to set
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * @return the buyerPhone
     */
    public String getBuyerPhone() {
        return buyerPhone;
    }

    /**
     * @param buyerPhone the buyerPhone to set
     */
    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    /**
     * @return the buyerEmail
     */
    public String getBuyerEmail() {
        return buyerEmail;
    }

    /**
     * @param buyerEmail the buyerEmail to set
     */
    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    /**
     * @return the csName
     */
    public String getCsName() {
        return csName;
    }

    /**
     * @param csName the csName to set
     */
    public void setCsName(String csName) {
        this.csName = csName;
    }

    /**
     * @return the csPhone
     */
    public String getCsPhone() {
        return csPhone;
    }

    /**
     * @param csPhone the csPhone to set
     */
    public void setCsPhone(String csPhone) {
        this.csPhone = csPhone;
    }

    /**
     * @return the csEmail
     */
    public String getCsEmail() {
        return csEmail;
    }

    /**
     * @param csEmail the csEmail to set
     */
    public void setCsEmail(String csEmail) {
        this.csEmail = csEmail;
    }

    /**
     * @return the tradingPartnerName
     */
    public String getTradingPartnerName() {
        return tradingPartnerName;
    }

    /**
     * @param tradingPartnerName the tradingPartnerName to set
     */
    public void setTradingPartnerName(String tradingPartnerName) {
        this.tradingPartnerName = tradingPartnerName;
    }

    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return the bvrUdiCommId
     */
    public String getBvrUdiCommId() {
        return bvrUdiCommId;
    }

    /**
     * @param bvrUdiCommId the bvrUdiCommId to set
     */
    public void setBvrUdiCommId(String bvrUdiCommId) {
        this.bvrUdiCommId = bvrUdiCommId;
    }

    /**
     * @return the bvrUdiName
     */
    public String getBvrUdiName() {
        return bvrUdiName;
    }

    /**
     * @param bvrUdiName the bvrUdiName to set
     */
    public void setBvrUdiName(String bvrUdiName) {
        this.bvrUdiName = bvrUdiName;
    }

    /**
     * @return the defaultFlowId
     */
    public String getDefaultFlowId() {
        return defaultFlowId;
    }

    /**
     * @param defaultFlowId the defaultFlowId to set
     */
    public void setDefaultFlowId(String defaultFlowId) {
        this.defaultFlowId = defaultFlowId;
    }

     /**
     * @return the note
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
 
    
}
