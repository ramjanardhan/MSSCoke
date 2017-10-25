/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.user;

import com.mss.ediscv.util.ServiceLocatorException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle1
 */
public interface UserService {

    public String addUser(UserAction userAction) throws ServiceLocatorException;

    public boolean userCheckExist(UserAction userAction) throws ServiceLocatorException;

    public int updateUserPwd(UserAction userAction) throws ServiceLocatorException;

    public int updateMyPwd(UserAction userAction) throws ServiceLocatorException;

    public ArrayList getSearchUserList(UserAction userAction, HttpServletRequest http) throws ServiceLocatorException;

    public UserAction editUser(UserAction userAction) throws ServiceLocatorException;

    public String updateUser(UserAction userAction) throws ServiceLocatorException;

    //for Assign roles Start
    public UserBean userDetails(int userId) throws ServiceLocatorException;

    public int insertFlows(String[] assignedFlowIds, int employeeId, int primaryFlowId) throws ServiceLocatorException;

    //For assign roles End
}
