/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.issues;

//import com.mss.ediscv.doc.DocRepositoryAction;
import com.mss.ediscv.util.*;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author miracle
 */
public class IssuesAction extends ActionSupport implements
ServletRequestAware {
     private HttpServletRequest httpServletRequest;
     private String resultType;
     private String submitFrm;
     private static Logger logger = Logger.getLogger(IssuesAction.class
			.getName());
    private String category;
    private String priority;
    private String assignment;
    private String group;
    private String summary;
    private String desc;
    private String time;
   // private File file;
    private String status;
    private String ticketId ; 
    private String project;
     private List<IssueBean> issueList;
    private String fileLocation;
    private String fcategory;
    private String fromtime;
    private String totime;
    private int id;
    private String formAction;
    private String update;
      /** The uploadContentType is useful to get a type of the upload file*/
 //   private String fileContentType;
    
    /** The uploadFileName is useful to get a upload File Name */
  //  private String fileFileName;
    
    private Map usersMap;
    private Map priorityMap;
    private Map categoryMap;
 private File file;
      private String contentType;
      private String filename;
      private String check;
      private String issuedatepickerfrom;
      private String issuedatepicker;
      private String userFlowMap;
      private Map selectUsers;
      
      private String searchType;
      
        
      
     public String prepare() throws Exception {
            
            //System.out.println("in DocRepositor Action----");
		logger.info("Entered into the ::::IssuesAction :::: prepare ");
		resultType = LOGIN;
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
		HttpSession httpSession = getHttpServletRequest().getSession(false);
		try {
                    
                    httpSession.removeAttribute(AppConstants.SES_PAYMENT_LIST);
                    httpSession.removeAttribute(AppConstants.SES_SHIPMENT_LIST);
                    httpSession.removeAttribute(AppConstants.SES_DOC_LIST);
                    httpSession.removeAttribute(AppConstants.SES_INV_LIST);
                    httpSession.removeAttribute(AppConstants.SES_PO_LIST);
                    httpSession.removeAttribute(AppConstants.SES_TRADINGPARTNER_LIST);
                    httpSession.removeAttribute(AppConstants.SES_ISSUE_LIST);
                    setFormAction("../issues/doCreateIssue.action");
                    setUsersMap(DataSourceDataProvider.getInstance().getUsers());
                    setPriorityMap(DataSourceDataProvider.getInstance().getPriority());
                    setCategoryMap(DataSourceDataProvider.getInstance().getCategory());
                  
                    
			resultType = SUCCESS;
		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.error("problem :: " + ex.getMessage()
						+ " method name :: prepare() :: class name :: "
						+ getClass().getName());
			}
			getHttpServletRequest().getSession(false).setAttribute(
					AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
			resultType = "error";
		}
	       }
		logger.info("End of ::::IssuesAction :::: prepare ");
		return resultType;
	}

     
     public String doCreateIssue() throws Exception
        {
            resultType = LOGIN;
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
		try {
                        
			if (logger.isDebugEnabled()) {
				logger
						.info("Entered into the ::::IssuesActioo :::: doCreateIssue ");
			}
			
                   
                        
			prepare();
                        String responseString ="";
                        
                       setFormAction("../issues/doSearchIssue.action"); 
    
         
        //  FileUtils.copyFile(new File(""),new File(""));
		          //    setCreatedBy(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_LOGIN_ID).toString());  
			 responseString = ServiceLocator.getIssuesService().doCreateIssue(this,filename,contentType,file,httpServletRequest);
			getHttpServletRequest().setAttribute(AppConstants.REQ_RESULT_MSG,responseString);
                              //  resetValues();
                                
			resultType = SUCCESS;

		}  catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger
						.error("problem :: "
								+ ex.getMessage()
								+ " method name :: getaddTp() :: class name :: "
								+ getClass().getName());
			}
			getHttpServletRequest().getSession(false).setAttribute(
					AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
			resultType = "error";
		}
	       }
		logger.info("End of ::::IssuesActioo :::: doCreateIssue ");
		return resultType;
        }

     
      /*
       * Author : ajay Kumar Tummapala <atummapala@miraclesoft.com>
       * Desc : for searching the issue
       */
     
     public String doSearchIssue() throws Exception {
           // System.out.println("getQuery---");
		resultType = LOGIN;
	       if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
		try {
			if (logger.isDebugEnabled()) {
				logger
						.info("Entered into the ::::SearchpuchaseAction :::: getPurchaseSearchQuery ");
			}
			prepare();
                               if(getCheck()==null)
                                    {
                                       // System.out.println("in if"+getCheck());
                                        setCheck("1");
                                    }
                                    else if(getCheck().equals(""))
                                        {
                                       // System.out.println("in else"+getCheck());
                                        setCheck("1");
                                        } 
                                   
			issueList = ServiceLocator.getIssuesService().buildIssueQuery(this,getHttpServletRequest());
			httpServletRequest.getSession(false).setAttribute(AppConstants.SES_ISSUE_LIST,issueList);
                                setSearchType("AllIssues");
                               setFormAction("../issues/doSearchIssue.action"); 
                        
                      //  System.out.println("value"+getSampleValue());
                        
			resultType = SUCCESS;

		}  catch (Exception ex) {
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
		logger.info("End of ::::SearchPOAction :::: getPOSearchQuery ");
		return resultType;
	}
     
     
     
     
     
      public String issueEdit() throws Exception
     {
         
         resultType = LOGIN;
	       if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                   HttpSession httpSession = getHttpServletRequest().getSession(false);
		try {
                        
			if (logger.isDebugEnabled()) {
				logger
						.info("Entered into the ::::TradingPartnerAction :::: tpEdit ");
			}
                        String responseString ="";                     
                         httpSession.removeAttribute(AppConstants.SES_PAYMENT_LIST);
                    httpSession.removeAttribute(AppConstants.SES_SHIPMENT_LIST);
                    httpSession.removeAttribute(AppConstants.SES_DOC_LIST);
                    httpSession.removeAttribute(AppConstants.SES_INV_LIST);
                    httpSession.removeAttribute(AppConstants.SES_PO_LIST);
                    httpSession.removeAttribute(AppConstants.SES_TRADINGPARTNER_LIST);
                    httpSession.removeAttribute(AppConstants.SES_ISSUE_LIST);
                    setUsersMap(DataSourceDataProvider.getInstance().getUsers());
                    setPriorityMap(DataSourceDataProvider.getInstance().getPriority());
                    setCategoryMap(DataSourceDataProvider.getInstance().getCategory());
			 ServiceLocator.getIssuesService().issueEdit(this);
                        setFormAction("../issues/doIssueEdit.action");
                        setUpdate("1");
                        setSearchType(getSearchType());
                        
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
     
     
   
     public String searchIssuePrepare() throws Exception
     {
         
         resultType = LOGIN;
	       if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                   HttpSession httpSession = getHttpServletRequest().getSession(false);
		try {
                        
			if (logger.isDebugEnabled()) {
				logger
						.info("Entered into the ::::TradingPartnerAction :::: tpEdit ");
			}
                        String responseString ="";                     
                         httpSession.removeAttribute(AppConstants.SES_PAYMENT_LIST);
                    httpSession.removeAttribute(AppConstants.SES_SHIPMENT_LIST);
                    httpSession.removeAttribute(AppConstants.SES_DOC_LIST);
                    httpSession.removeAttribute(AppConstants.SES_INV_LIST);
                    httpSession.removeAttribute(AppConstants.SES_PO_LIST);
                    httpSession.removeAttribute(AppConstants.SES_TRADINGPARTNER_LIST);
                    httpSession.removeAttribute(AppConstants.SES_ISSUE_LIST);
                    setUsersMap(DataSourceDataProvider.getInstance().getUsers());
                    setPriorityMap(DataSourceDataProvider.getInstance().getPriority());
                    setCategoryMap(DataSourceDataProvider.getInstance().getCategory());
			 //ServiceLocator.getIssuesService().issueEdit(this);
                    if(getSearchType().equals("MyIssues")){
                        setFormAction("../issues/doSearchMyIssue.action");
                        httpServletRequest.setAttribute("searchType", "MyIssues");
                    }else if(getSearchType().equals("AllIssues")){
                        setFormAction("../issues/doSearchIssue.action");
                        httpServletRequest.setAttribute("searchType", "AllIssues");
                    }
                        
                        
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
   
  public String doSearchMyIssue() throws Exception {
      // System.out.println("getQuery---");
		resultType = LOGIN;
	       if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
		try {
			if (logger.isDebugEnabled()) {
				logger
						.info("Entered into the ::::IssueAction :::: doSearchMyIssues ");
			}
			prepare();
                               if(getCheck()==null)
                                    {
                                       // System.out.println("in if"+getCheck());
                                        setCheck("1");
                                    }
                                    else if(getCheck().equals(""))
                                        {
                                       // System.out.println("in else"+getCheck());
                                        setCheck("1");
                                        } 
                               setSearchType("MyIssues");
                               setFormAction("../issues/doSearchMyIssue.action");
			issueList = ServiceLocator.getIssuesService().getMyIssueList(this,getHttpServletRequest());
			httpServletRequest.getSession(false).setAttribute(AppConstants.SES_ISSUE_LIST,issueList);
                               
                        
                      //  System.out.println("value"+getSampleValue());
                        
			resultType = SUCCESS;

		}  catch (Exception ex) {
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
		logger.info("End of ::::SearchPOAction :::: getPOSearchQuery ");
		return resultType;
  }  
  
  public String searchTasksPrepare() throws Exception
     {
         
         resultType = LOGIN;
	       if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
                   HttpSession httpSession = getHttpServletRequest().getSession(false);
		try {
                        
			if (logger.isDebugEnabled()) {
				logger
						.info("Entered into the ::::TradingPartnerAction :::: tpEdit ");
			}
                        String responseString ="";                     
                         httpSession.removeAttribute(AppConstants.SES_PAYMENT_LIST);
                    httpSession.removeAttribute(AppConstants.SES_SHIPMENT_LIST);
                    httpSession.removeAttribute(AppConstants.SES_DOC_LIST);
                    httpSession.removeAttribute(AppConstants.SES_INV_LIST);
                    httpSession.removeAttribute(AppConstants.SES_PO_LIST);
                    httpSession.removeAttribute(AppConstants.SES_TRADINGPARTNER_LIST);
                    httpSession.removeAttribute(AppConstants.SES_ISSUE_LIST);
                    setUsersMap(DataSourceDataProvider.getInstance().getUsers());
                    setPriorityMap(DataSourceDataProvider.getInstance().getPriority());
                    setCategoryMap(DataSourceDataProvider.getInstance().getCategory());
			 //ServiceLocator.getIssuesService().issueEdit(this);
                    if(getSearchType().equals("MyTasks")){
                        setFormAction("../issues/doSearchMyTasks.action");
                        httpServletRequest.setAttribute("searchType", "MyTasks");
                    }else if(getSearchType().equals("AllTasks")){
                        setFormAction("../issues/doSearchTasks.action");
                        httpServletRequest.setAttribute("searchType", "AllTasks");
                    }
                        
                        
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
     public String doSearchMyTasks() throws Exception {
      // System.out.println("getQuery---");
		resultType = LOGIN;
	       if(httpServletRequest.getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
		try {
			if (logger.isDebugEnabled()) {
				logger
						.info("Entered into the ::::IssueAction :::: doSearchMyIssues ");
			}
			prepare();
                               if(getCheck()==null)
                                    {
                                       // System.out.println("in if"+getCheck());
                                        setCheck("1");
                                    }
                                    else if(getCheck().equals(""))
                                        {
                                       // System.out.println("in else"+getCheck());
                                        setCheck("1");
                                        } 
                               setSearchType("MyTasks");
                               setFormAction("../issues/doSearchMyTasks.action");
			issueList = ServiceLocator.getIssuesService().getMyTasksList(this,getHttpServletRequest());
			httpServletRequest.getSession(false).setAttribute(AppConstants.SES_ISSUE_LIST,issueList);
                               
                        
                      //  System.out.println("value"+getSampleValue());
                        
			resultType = SUCCESS;

		}  catch (Exception ex) {
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
		logger.info("End of ::::SearchPOAction :::: getPOSearchQuery ");
		return resultType;
  }  
  
     
    public String doIssueEdit() throws Exception
        {
            resultType = LOGIN;
	       if(getHttpServletRequest().getSession(false).getAttribute(AppConstants.SES_USER_NAME) != null) {
		try {
                        
			if (logger.isDebugEnabled()) {
				logger
						.info("Entered into the ::::IssuesActioo :::: doCreateIssue ");
			}
			prepare();
                        String responseString ="";
                        setUpdate("1");
                       setFormAction("../issues/doIssueEdit.action");
                       
                        System.out.println("assigment-->"+getAssignment());
                       StringTokenizer st = new StringTokenizer(getAssignment(),",");
                        System.out.println("after stokenizer");
                         System.out.println("after treemap");
                     selectUsers = new TreeMap();
                       while(st.hasMoreTokens())
                       {
                           String token = null;
                           token = st.nextToken();
                       selectUsers.put(token.trim(), DataSourceDataProvider.getInstance().getNameByLoginId(token.trim()));
                       }
                       setSelectUsers(selectUsers);
			 responseString = ServiceLocator.getIssuesService().doIssueEdit(this,httpServletRequest);
			getHttpServletRequest().setAttribute(AppConstants.REQ_RESULT_MSG,responseString);
                              //  resetValues();
                             setSearchType(getSearchType());   
			resultType = SUCCESS;

		}  catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger
						.error("problem :: "
								+ ex.getMessage()
								+ " method name :: getaddTp() :: class name :: "
								+ getClass().getName());
			}
			getHttpServletRequest().getSession(false).setAttribute(
					AppConstants.REQ_EXCEPTION_MSG, ex.getMessage());
			resultType = "error";
		}
	       }
		logger.info("End of ::::IssuesActioo :::: doCreateIssue ");
		return resultType;
        } 
     
     
    /**
     * @return the submitFrm
     */
    public String getSubmitFrm() {
        return submitFrm;
    }

    /**
     * @return the selectUsers
     */
    public Map getSelectUsers() {
        return selectUsers;
    }

    /**
     * @param selectUsers the selectUsers to set
     */
    public void setSelectUsers(Map selectUsers) {
        this.selectUsers = selectUsers;
    }

    /**
     * @param submitFrm the submitFrm to set
     */
    public void setSubmitFrm(String submitFrm) {
        this.submitFrm = submitFrm;
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

    /**
     * @return the httpServletRequest
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the assignment
     */
  

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

  
    /**
     * @return the uploadContentType
     */
 
    /**
     * @return the fileLocation
     */
    public String getFileLocation() {
        return fileLocation;
    }

    /**
     * @param fileLocation the fileLocation to set
     */
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public void setUpload(File file) {
         this.file = file;
      }
 
      public void setUploadContentType(String contentType) {
         this.contentType = contentType;
      }
  public void setUploadFileName(String filename) {
         this.filename = filename;
      }

    /**
     * @return the usersMap
     */
    public Map getUsersMap() {
        return usersMap;
    }

    /**
     * @param usersMap the usersMap to set
     */
    public void setUsersMap(Map usersMap) {
        this.usersMap = usersMap;
    }

    /**
     * @return the priorityMap
     */
    public Map getPriorityMap() {
        return priorityMap;
    }

    /**
     * @param priorityMap the priorityMap to set
     */
    public void setPriorityMap(Map priorityMap) {
        this.priorityMap = priorityMap;
    }

    /**
     * @return the categoryMap
     */
    public Map getCategoryMap() {
        return categoryMap;
    }

    /**
     * @param categoryMap the categoryMap to set
     */
    public void setCategoryMap(Map categoryMap) {
        this.categoryMap = categoryMap;
    }

    /**
     * @param assignment the assignment to set
     */
    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

     public String getAssignment() {
        return assignment;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the userFlowMap
     */
    public String getUserFlowMap() {
        return userFlowMap;
    }

    /**
     * @param userFlowMap the userFlowMap to set
     */
    public void setUserFlowMap(String userFlowMap) {
        this.userFlowMap = userFlowMap;
    }

    /**
     * @return the issuedatepickerfrom
     */
    public String getIssuedatepickerfrom() {
        return issuedatepickerfrom;
    }

    /**
     * @param issuedatepickerfrom the issuedatepickerfrom to set
     */
    public void setIssuedatepickerfrom(String issuedatepickerfrom) {
        this.issuedatepickerfrom = issuedatepickerfrom;
    }

    /**
     * @return the issuedatepicker
     */
    public String getIssuedatepicker() {
        return issuedatepicker;
    }

    /**
     * @param issuedatepicker the issuedatepicker to set
     */
    public void setIssuedatepicker(String issuedatepicker) {
        this.issuedatepicker = issuedatepicker;
    }

    /**
     * @return the ticketId
     */
    public String getTicketId() {
        return ticketId;
    }

    /**
     * @param ticketId the ticketId to set
     */
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * @return the project
     */
    public String getProject() {
        return project;
    }

    /**
     * @return the update
     */
    public String getUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(String update) {
        this.update = update;
    }

    /**
     * @param project the project to set
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * @return the fcategory
     */
    public String getFcategory() {
        return fcategory;
    }

    /**
     * @param fcategory the fcategory to set
     */
    public void setFcategory(String fcategory) {
        this.fcategory = fcategory;
    }

    /**
     * @return the fromtime
     */
    public String getFromtime() {
        return fromtime;
    }

    /**
     * @param fromtime the fromtime to set
     */
    public void setFromtime(String fromtime) {
        this.fromtime = fromtime;
    }

    /**
     * @return the totime
     */
    public String getTotime() {
        return totime;
    }

    /**
     * @param totime the totime to set
     */
    public void setTotime(String totime) {
        this.totime = totime;
    }

    /**
     * @return the check
     */
    public String getCheck() {
        return check;
    }

    /**
     * @param check the check to set
     */
    public void setCheck(String check) {
        this.check = check;
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
     * @return the searchType
     */
    public String getSearchType() {
        return searchType;
    }

    /**
     * @param searchType the searchType to set
     */
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }


}
