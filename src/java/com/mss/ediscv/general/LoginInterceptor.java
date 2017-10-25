/*******************************************************************************
 *
 * Project : Miracle Supply Chain Visibility portal v2.0
 *
 * Package : com.mss.mscvp.general
 *
 * Date    : Mar 11, 2013 1:28:58 PM
 *
 * Author  : Nagireddy seerapu <nseerapu@miraclesoft.com>
 *
 * File    : LoginInterceptor.java
 *
 * 
 * *****************************************************************************
 */
package com.mss.ediscv.general;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.StrutsStatics;


/**
 * @author miracle
 *
 */
 public class LoginInterceptor extends AbstractInterceptor implements StrutsStatics{
    
   

	/** Creates a new instance of LoginInterceptor */
    public LoginInterceptor() {
    }
    
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        
       // System.out.println("in intercepter start --->");
        ActionContext context = actionInvocation.getInvocationContext();
        HttpServletRequest request= (HttpServletRequest) context.get(HTTP_REQUEST);
        HttpSession session =  request.getSession(false);
        if(session==null) {
            return "sessionExpire";
        }
        
             
//
//      String userName=(String)request.getAttribute("name");
//      System.out.println(userName.length());
//    if(userName.length()>0) return "login";
//     if(userName.length()==0) return "sessionExpire";
//      System.out.println(" output : "+userName);
        return actionInvocation.invoke();
    }
    
}

