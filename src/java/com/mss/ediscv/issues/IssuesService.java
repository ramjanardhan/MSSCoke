/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.issues;

import com.mss.ediscv.util.ServiceLocatorException;
import java.io.File;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public interface IssuesService {
    public String doCreateIssue(IssuesAction issuesAction,String fileName,String ContentType,File file,HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    //public String do
    public ArrayList<IssueBean> buildIssueQuery(IssuesAction issueAction,HttpServletRequest httpServletRequest)throws ServiceLocatorException;
    public IssuesAction issueEdit(IssuesAction issuesAction) throws ServiceLocatorException;
    
     public String doIssueEdit(IssuesAction issuesAction,HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    public ArrayList<IssueBean> getMyIssueList(IssuesAction issueAction,HttpServletRequest httpServletRequest)throws ServiceLocatorException;
    public ArrayList<IssueBean> getMyTasksList(IssuesAction issueAction,HttpServletRequest httpServletRequest)throws ServiceLocatorException;
}
