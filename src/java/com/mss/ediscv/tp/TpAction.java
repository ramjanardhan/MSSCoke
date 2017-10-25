/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.tp;


//import com.mss.ediscv.po.PurchaseOrderBean;
import com.mss.ediscv.util.AppConstants;
import com.mss.ediscv.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author miracle
 */
public class TpAction extends ActionSupport implements
ServletRequestAware {
    
        private HttpServletRequest httpServletRequest;
	private String sqlQuery;
	private String tpSearchQuery;
	private String submitFrm;
	private String resultType;
        private String id;
        private String name;
        private String tpInPath;
        private String tpOutPath;
        private String contact;
        private String phno;
        private String dept;
        private String commid;
        private String qualifier;
                        
        private ArrayList tpList;
        
        
        private static Logger logger = Logger.getLogger(TpAction.class
			.getName());
        
        public String prepare() throws Exception {
            
           // System.out.println("in tp Action----");
		logger.info("Entered into the ::::SearchTPAction :::: prepare ");
		setResultType(LOGIN);
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
		HttpSession httpSession = getHttpServletRequest().getSession(false);
		try {
                    
                    httpSession.removeAttribute(AppConstants.SES_PAYMENT_LIST);
                    httpSession.removeAttribute(AppConstants.SES_SHIPMENT_LIST);
                    httpSession.removeAttribute(AppConstants.SES_DOC_LIST);
                    httpSession.removeAttribute(AppConstants.SES_INV_LIST);
                    httpSession.removeAttribute(AppConstants.SES_PO_LIST);
			if (getSubmitFrm() != null && (!getSubmitFrm().equals("frmDBGrid"))) {
				if (httpSession.getAttribute(AppConstants.SES_TP_LIST) != null) {
					httpSession.removeAttribute(AppConstants.SES_TP_LIST);
				}
			} else if (getSubmitFrm() == null && httpSession.getAttribute(AppConstants.SES_TP_LIST) != null) {
				httpSession.removeAttribute(AppConstants.SES_TP_LIST);
			}
			setResultType(SUCCESS);
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
		logger.info("End of ::::TPAction :::: prepare ");
		return getResultType();
	}
        
        public String doAddTP() throws Exception
        {
            resultType = LOGIN;
	       if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
		try {
                        
			if (logger.isDebugEnabled()) {
				logger
						.info("Entered into the ::::TpAction :::: doaddTP ");
			}
			
			prepare();
		                
			 String responseString = ServiceLocator.getTpService().addTP(this);
			httpServletRequest.getSession(false).setAttribute("responseString",responseString);
                                
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
		logger.info("End of ::::TPAction :::: doAddTP ");
		return resultType;
        }
        
        
        
        
        public String getTpList() throws Exception
        {
            resultType = LOGIN;
	       if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
		try {
                        
			if (logger.isDebugEnabled()) {
				logger
						.info("Entered into the ::::TpAction :::: doaddTP ");
			}
			
			prepare();
		                
			 tpList = ServiceLocator.getTpService().getTpList(this);
			httpServletRequest.getSession(false).setAttribute(AppConstants.SES_TP_LIST,tpList);
                                
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
		logger.info("End of ::::TPAction :::: doAddTP ");
		return resultType;
        }
        
        
        

    /**
     * @return the httpServletRequest
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * @param httpServletRequest the httpServletRequest to set
     */
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the sqlQuery
     */
    public String getSqlQuery() {
        return sqlQuery;
    }

    /**
     * @param sqlQuery the sqlQuery to set
     */
    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    /**
     * @return the tpSearchQuery
     */
    public String getTpSearchQuery() {
        return tpSearchQuery;
    }

    /**
     * @param tpSearchQuery the tpSearchQuery to set
     */
    public void setTpSearchQuery(String tpSearchQuery) {
        this.tpSearchQuery = tpSearchQuery;
    }

    /**
     * @return the submitFrm
     */
    public String getSubmitFrm() {
        return submitFrm;
    }

    /**
     * @param submitFrm the submitFrm to set
     */
    public void setSubmitFrm(String submitFrm) {
        this.submitFrm = submitFrm;
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

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.setHttpServletRequest(hsr);
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the tpInPath
     */
    public String getTpInPath() {
        return tpInPath;
    }

    /**
     * @param tpInPath the tpInPath to set
     */
    public void setTpInPath(String tpInPath) {
        this.tpInPath = tpInPath;
    }

    /**
     * @return the tpOutPath
     */
    public String getTpOutPath() {
        return tpOutPath;
    }

    /**
     * @param tpOutPath the tpOutPath to set
     */
    public void setTpOutPath(String tpOutPath) {
        this.tpOutPath = tpOutPath;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
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
     * @return the dept
     */
    public String getDept() {
        return dept;
    }

    /**
     * @param dept the dept to set
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
     * @return the commid
     */
    public String getCommid() {
        return commid;
    }

    /**
     * @param commid the commid to set
     */
    public void setCommid(String commid) {
        this.commid = commid;
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
     * @param tpList the tpList to set
     */
   
        
        
}
