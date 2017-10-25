/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.lfc;

//import com.mss.ediscv.doc.*;
//import com.mss.ediscv.po.PurchaseOrderBean;
import com.mss.ediscv.util.AppConstants;
import com.mss.ediscv.util.DateUtility;
import com.mss.ediscv.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;


/**
 *
 * @author miracle
 */
public class LifecycleAction extends ActionSupport implements
ServletRequestAware {
    
    
    
        private HttpServletRequest httpServletRequest;
	private String sqlQuery;
	private String docSearchQuery;
	private String submitFrm;
	private String resultType;
	
       // private List<LifecycleBeans> lifeCycleList;
        private LifecycleBeans lifeCycleBeans;
	private String currentDsnName;
        
        private String poNumber;
        private PoLifecycleBean poLifecycleBean;
        
       
   

	private static Logger logger = Logger.getLogger(LifecycleAction.class
			.getName());
	
	
	
	public String prepare() throws Exception {
            
            //System.out.println("in DocRepositor Action----");
		logger.info("Entered into the ::::LifecycleAction :::: prepare ");
		resultType = LOGIN;
	       if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
		HttpSession httpSession = httpServletRequest.getSession(false);
		try {
                    //setLifeCycleBeans();
                    
                            ServiceLocator.getLifeCycleService().buildLifeCycleBeans(this,httpServletRequest);
                     
			resultType = SUCCESS;
		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.error("problem :: " + ex.getMessage()
						+ " method name :: prepare() :: class name :: "
						+ getClass().getName());
			}
			httpServletRequest.getSession(false).setAttribute(
					AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
			resultType = "error";
		}
	       }
		logger.info("End of ::::SearchDOCUMENTSAction :::: prepare ");
		return resultType;
	}
        
        
        
        public void setServletRequest(HttpServletRequest reqObj) {
		this.setHttpServletRequest(reqObj);
	}

	/**
	 * @param reqObj
	 */
	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}


	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setDocSearchQuery(String docSearchQuery) {
		this.docSearchQuery = docSearchQuery;
	}

	public String getDocSearchQuery() {
		return docSearchQuery;
	}

	public void setSubmitFrm(String submitFrm) {
		this.submitFrm = submitFrm;
	}

	public String getSubmitFrm() {
		return submitFrm;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getResultType() {
		return resultType;
	}

	public void setCurrentDsnName(String currentDsnName) {
		this.currentDsnName = currentDsnName;
	}

	public String getCurrentDsnName() {
		return currentDsnName;
	}

    /**
     * @return the poNumber
     */
    public String getPoNumber() {
        return poNumber;
    }

    /**
     * @param poNumber the poNumber to set
     */
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    /**
     * @return the lifeCycleBeans
     */
    public LifecycleBeans getLifeCycleBeans() {
        return lifeCycleBeans;
    }

    /**
     * @param lifeCycleBeans the lifeCycleBeans to set
     */
    public void setLifeCycleBeans(LifecycleBeans lifeCycleBeans) {
        this.lifeCycleBeans = lifeCycleBeans;
    }

    /**
     * @return the poLifecycleBean
     */
    public PoLifecycleBean getPoLifecycleBean() {
        return poLifecycleBean;
    }

    /**
     * @param poLifecycleBean the poLifecycleBean to set
     */
    public void setPoLifecycleBean(PoLifecycleBean poLifecycleBean) {
        this.poLifecycleBean = poLifecycleBean;
    }
 
}
