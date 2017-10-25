/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.user;

import com.mss.ediscv.util.ConnectionProvider;
import com.mss.ediscv.util.DataSourceDataProvider;
import com.mss.ediscv.util.DateUtility;
import com.mss.ediscv.util.MailManager;
import com.mss.ediscv.util.PasswordUtil;
import com.mss.ediscv.util.Properties;
import com.mss.ediscv.util.ServiceLocatorException;
import com.mss.ediscv.util.WildCardSql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author miracle1
 */
public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    CallableStatement callableStatement = null;
    String responseString = null;
    String queryString = null;
    Statement statement = null;

    ResultSet resultSet = null;
    ArrayList userList = null;
    UserBean userBean = null;

    public String addUser(UserAction userAction) throws ServiceLocatorException {

        logger.info("Entered into the :::: UserServiceImpl :::: addUser");

        String fname = userAction.getFname();
        String lname = userAction.getLname();
        String email = userAction.getEmail();
        String ophno = userAction.getOphno();
        // String status = userAction.getActive();
        PasswordUtil passwordUtil = new PasswordUtil();
        String deptno = userAction.getDeptId();
        String status = userAction.getStatus();
        String role = userAction.getRole();

        String generatedPassword = passwordUtil.encryptPwd(generatePassword(8));
        String loginId = generateUserId(email);
        String createdBy = userAction.getCreatedBy();
        System.out.println("email" + email + "loginid" + loginId);
        /*
         SPCREATEUSER---

         
         */

        Timestamp activate_ts = DateUtility.getInstance().getCurrentDB2Timestamp();
        //int deptId = userAction.getDeptId();
        int i = 0;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call SPCREATEUSER(?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?)}");
            callableStatement.setString(1, loginId);
            callableStatement.setString(2, generatedPassword);
            callableStatement.setString(3, fname);
            callableStatement.setString(4, lname);
            callableStatement.setString(5, email);
            callableStatement.setString(6, ophno);
            callableStatement.setInt(7, Integer.parseInt(deptno));
            callableStatement.setString(8, status);
            callableStatement.setString(9, createdBy);
            callableStatement.setString(10, createdBy);
            callableStatement.setTimestamp(11, activate_ts);
            callableStatement.setInt(12, Integer.parseInt(role));
            callableStatement.setTimestamp(13, activate_ts);
            callableStatement.setString(14, createdBy);
            callableStatement.setInt(15, 1);
            callableStatement.setTimestamp(16, activate_ts);
            callableStatement.setTimestamp(17, activate_ts);
            callableStatement.registerOutParameter(18, Types.VARCHAR);
            int updatedRows = callableStatement.executeUpdate();
            responseString = "<font color='green'>" + callableStatement.getString(18) + "</font>";
            if (email != null && !email.equals("")) {

                //System.out.println("sendMailId---"+sendMailId);
                MailManager sendMail = new MailManager();
                sendMail.sendUserIdPwd(loginId, fname + " " + lname, passwordUtil.decryptPwd(generatedPassword));

            }
        } catch (SQLException e) {
            System.out.println("I am in catch block coming in UserServiceIMpl" + e.getMessage());
            // TODO Auto-generated catch block
            responseString = "<font color='red'>Please try with different Id!</font>";
            e.printStackTrace();
        } catch (Exception ex) {
            responseString = "<font color='red'>Please try later!</font>";
            System.out.println("hi" + ex.getMessage());
        } finally {
            try {

                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return responseString;

    }

    public String generateUserId(String mailId) {

        /*@param atOccurance is used to store index of mailId upto @ char*/
        int atOccurance = mailId.indexOf("@");

        /*finally those string return here*/
        return mailId.substring(0, atOccurance).toLowerCase();
    }

    public String generatePassword(int noOfCharacters) {

        /*@param generatedPwd used to store String*/
        String generatedPwd = "";

        int randInt;

        /**
         * Random() method Creates a new random number generator
         * currentTimeMillis() method Returns the current value of the most
         * precise available system timer, in nanoseconds.
         */
        Random random = new Random(System.currentTimeMillis());

        /*
         *the next pseudorandom, uniformly distributed value from this random number generator's sequence.
         *and that is stored in randomOne and randomTwo
         */
        long randomOne = random.nextLong();
        long randomTwo = random.nextLong();

        /**
         * toHexString() method return the string representation of the unsigned
         * value represented by the argument in hexadecimal that String stored
         * in hashCodeOne and hashCodeTwo
         */
        String hashCodeOne = Long.toHexString(randomOne);
        String hashCodeTwo = Long.toHexString(randomTwo);

        /*@param generatedPwd used to store concatinate string of hashCodeOne and hashCodeTwo*/
        generatedPwd = hashCodeOne + hashCodeTwo;

        if (generatedPwd.length() > noOfCharacters) {
            generatedPwd = generatedPwd.substring(0, noOfCharacters);
        }

        return generatedPwd.toUpperCase();
    }

    public boolean userCheckExist(UserAction userAction) throws ServiceLocatorException {
        boolean isUserExist = false;
        logger.info("Entered into the :::: UserServiceImpl :::: userCheckExist");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String loginId = generateUserId(userAction.getEmail());
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT LOGINID FROM M_USER where LOGINID =?");
            preparedStatement.setString(1, loginId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isUserExist = true;
            }

        } catch (SQLException e) {
            System.out.println("I am in catch block coming in User IMpl" + e.getMessage());
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception ex) {
            System.out.println("hi" + ex.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return isUserExist;
    }
    /*
     * For reset user password
     * Date : 05/01/2013
     * 
     */

    public int updateUserPwd(UserAction userAction) throws ServiceLocatorException {
        logger.info("Entered into the :::: UserServiceImpl :::: resetUserPwd");
        /*@param isUpdate is used to store boolean value false*/
        int updatedRows = 0;
        /*@param password is used to store password of the user*/
        String password = null;
        PasswordUtil passwordUtility = new PasswordUtil();
        connection = ConnectionProvider.getInstance().getConnection();
        try {

            /*here checking weather passwor equal or not ,if it equal the update will be done.*/
            if (userAction.getNewPwd().equalsIgnoreCase(userAction.getConfirmPwd())) {
                String encryptPass = passwordUtility.encryptPwd(userAction.getNewPwd());
//                 System.out.println("LoginId-->"+userAction.getLoginId());
//                System.out.println("New Password-->"+userAction.getNewPwd());
//                System.out.println("New Password Encrypted-->"+encryptPass);
                queryString = "UPDATE M_USER SET PASSWD='" + encryptPass + "' WHERE LOGINID='" + userAction.getLoginId() + "'";
                statement = connection.createStatement();
                updatedRows = statement.executeUpdate(queryString);
            }

        } catch (SQLException sqle) {
            throw new ServiceLocatorException(sqle);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return updatedRows;
    }

    @Override
    public int updateMyPwd(UserAction userAction) throws ServiceLocatorException {
        logger.info("Entered into the :::: UserServiceImpl :::: updateMyPwd");
        /*@param isUpdate is used to store boolean value false*/
        int updatedRows = 0;
        /*@param password is used to store password of the user*/
        String password = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        PasswordUtil passwordUtility = new PasswordUtil();
//        System.out.println("getLoginId-->"+userAction.getLoginId());
//        System.out.println("getNewPwd-->"+userAction.getNewPwd());
//        System.out.println("getOldPwd-->"+userAction.getOldPwd());
        String queryString = "SELECT LOGINID,PASSWD FROM M_USER WHERE LOGINID='" + userAction.getLoginId() + "'";

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                password = resultSet.getString("PASSWD");
            }

            password = passwordUtility.decryptPwd(password);
            // System.out.println("password-->"+password);
            /*here checking weather passwor exist or not ,if it exit the update will be done.*/
            if (userAction.getOldPwd().equalsIgnoreCase(password)) {
                String encryptPass = passwordUtility.encryptPwd(userAction.getNewPwd());
                queryString = "UPDATE M_USER SET PASSWD='" + encryptPass + "' WHERE LOGINID='" + userAction.getLoginId() + "'";
                statement = connection.createStatement();
                updatedRows = statement.executeUpdate(queryString);
            }
            //System.out.println("after updation!!");

        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return updatedRows;
    }

    @Override
    public ArrayList getSearchUserList(UserAction userAction, HttpServletRequest http) throws ServiceLocatorException {
        StringBuffer userSearchQuery = new StringBuffer();
        logger.info("Entered into the :::: UserServiceImpl :::: getSearchUserList");
        String fname = userAction.getFname();
        String lname = userAction.getLname();
        String loginId = userAction.getLoginId();
        String status = userAction.getStatus();

        //tradingSearchQuery.append("select tp.name,tp_details.tp_id,tp_details.email,tp_details.phone_number,tp_details.ADDRESS from tp LEFT OUTER JOIN tp_details on(tp_details.TP_ID=tp.ID) ");
        userSearchQuery.append("SELECT ID,FNME,LNME,EMAIL,OFFICE_PHONE,ACTIVE,ROLE_ID,LOGINID FROM M_USER LEFT OUTER JOIN M_USER_ROLES on(M_USER_ROLES.USER_ID=M_USER.ID)");
        //  userSearchQuery.append("SELECT ID,FNME,LNME,EMAIL,OFFICE_PHONE,LOGINID FROM M_USER");
        userSearchQuery.append(" WHERE 1=1");
        userSearchQuery.append(" AND M_USER_ROLES.ROLE_ID != 1");
        if (fname != null && !"".equals(fname.trim())) {
            userSearchQuery.append(WildCardSql.getWildCardSql1("FNME", fname.trim()));
        }
        if (lname != null && !"".equals(lname.trim())) {
            userSearchQuery.append(WildCardSql.getWildCardSql1("LNME", lname.trim()));
        }
        if (loginId != null && !"".equals(loginId.trim())) {
            userSearchQuery.append(WildCardSql.getWildCardSql1("LOGINID", loginId.trim()));
        }
        if (status != null && !"-1".equals(status.trim())) {
            userSearchQuery.append(WildCardSql.getWildCardSql1("ACTIVE", status.trim()));
        }
        //System.out.println("USer Search query-->"+userSearchQuery.toString());
        String searchQuery = userSearchQuery.toString();
        try {
            connection = ConnectionProvider.getInstance().getConnection();

            statement = connection.createStatement();

            resultSet = statement.executeQuery(searchQuery);

            userList = new ArrayList();
            while (resultSet.next()) {

                UserBean userBean = new UserBean();
                // userBean.setFname(resultSet.getString("FNME"));
                userBean.setName(resultSet.getString("FNME") + " " + resultSet.getString("LNME"));
                userBean.setEmail(resultSet.getString("EMAIL"));
                userBean.setOphno(resultSet.getString("OFFICE_PHONE"));
                userBean.setLoginId(resultSet.getString("LOGINID"));
                userBean.setId(resultSet.getString("ID"));

                //userBean.setStatus(resultSet.getString("ACTIVE"));
                if (resultSet.getString("ACTIVE").equals("A")) {
                    userBean.setStatus("Active");
                } else if (resultSet.getString("ACTIVE").equals("I")) {
                    userBean.setStatus("InActive");
                } else if (resultSet.getString("ACTIVE").equals("T")) {
                    userBean.setStatus("Terminated");
                } else {
                    userBean.setStatus("-");
                }

                           // System.out.println("Role---->"+Properties.getProperty("user."+resultSet.getString("ROLE_ID")+".name"));
                //System.out.println("RoleName---->"+Properties.getProperty(Properties.getProperty("user."+resultSet.getString("ROLE_ID")+".name")));
                // String role = 
                //userBean.setRoleId(Properties.getProperty(Properties.getProperty("user."+resultSet.getString("ROLE_ID")+".name")));
                userBean.setRoleId(DataSourceDataProvider.getInstance().getRoleNameByRoleId(resultSet.getString("ROLE_ID")));
                //  {'101':'Admin','100':'Compliance','102':'Logistics','103':'Credit','104':'Developer'}"
                userList.add(userBean);

            }
            logger.info("End :::: UserServiceImpl :::: getSearchUserList");
        } catch (SQLException e) {
			//System.out.println("I am in catch block coming in IMpl");
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception ex) {
            //System.out.println("hi"+ex.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        // System.out.println("Length--->"+purchaseList.size());
        return userList;
    }

    public UserAction editUser(UserAction userAction) throws ServiceLocatorException {
        logger.info("Entered into the :::: UserServiceImpl :::: editUser");
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("select ID,FNME,LNME,EMAIL,OFFICE_PHONE,DEPT_ID,ACTIVE,ROLE_ID  from M_USER LEFT OUTER JOIN M_USER_ROLES on (M_USER.ID=M_USER_ROLES.USER_ID) where M_USER.ID=?");
            preparedStatement.setString(1, userAction.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userAction.setId(resultSet.getString("ID"));
                userAction.setFname(resultSet.getString("FNME"));
                userAction.setLname(resultSet.getString("LNME"));
                userAction.setEmail(resultSet.getString("EMAIL"));
                userAction.setOphno(resultSet.getString("OFFICE_PHONE"));
                userAction.setDeptId(resultSet.getString("DEPT_ID"));
                userAction.setStatus(resultSet.getString("ACTIVE"));
                userAction.setRole(resultSet.getString("ROLE_ID"));

            }
        } catch (SQLException e) {
            System.out.println("I am in catch block coming in UserServiceImpl" + e.getMessage());
            // TODO Auto-generated catch block
            responseString = "<font color='red'>Please try Again!</font>";
            e.printStackTrace();
        } catch (Exception ex) {
            responseString = "<font color='red'>Please try later!</font>";
            System.out.println("hi" + ex.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return userAction;
    }

    public String updateUser(UserAction userAction) throws ServiceLocatorException {
        logger.info("Entered into the :::: UserServiceImpl :::: updateUser");
     //System.out.println("type--->"+tpAction.getTpType());
        /*
         * IN frmID BIGINT,
         IN frmFNME varchar(50), 
         IN frmLNME varchar(50), 
         IN frmOFFICE_PHONE varchar(50), 
         IN frmDEPT_ID INTEGER, 
         IN frmACTIVE varchar(50),
         IN frmROLE_ID BIGINT,
         IN frmMODIFIED_BY varchar(30),
         IN frmMODIFIED_TS TIMESTAMP,
         IN frmDEACTIVATED_BY varchar(30),
         IN frmDEACTIVATED_TS TIMESTAMP,
         OUT RESULT_MESSAGE varchar(100)
         */
        String id = userAction.getId();
        String fname = userAction.getFname();
        String lname = userAction.getLname();
        String ophno = userAction.getOphno();
        String deptId = userAction.getDeptId();
        String status = userAction.getStatus();
        String roleId = userAction.getRole();
        String modified_BY = userAction.getCreatedBy();
        Timestamp modified_TS = DateUtility.getInstance().getCurrentDB2Timestamp();
        String deactivated_BY = null;
        Timestamp deactivated_TS = null;
        if (!status.equals("A")) {
            deactivated_BY = userAction.getCreatedBy();
            deactivated_TS = modified_TS;
        }

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{call SPUPDATEUSER(?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?)}");
            callableStatement.setInt(1, Integer.parseInt(id));
            callableStatement.setString(2, fname);
            callableStatement.setString(3, lname);
            callableStatement.setString(4, ophno);
            callableStatement.setInt(5, Integer.parseInt(deptId));
            callableStatement.setString(6, status);
            callableStatement.setInt(7, Integer.parseInt(roleId));
            callableStatement.setString(8, modified_BY);
            callableStatement.setTimestamp(9, modified_TS);
            callableStatement.setString(10, deactivated_BY);
            callableStatement.setTimestamp(11, deactivated_TS);

            callableStatement.registerOutParameter(12, Types.VARCHAR);
            int updatedRows = callableStatement.executeUpdate();
            responseString = "<font color='green'>" + callableStatement.getString(12) + "</font>";

        } catch (SQLException e) {
            System.out.println("I am in catch block coming in UserServiceIMpl" + e.getMessage());
            // TODO Auto-generated catch block
            responseString = "<font color='red'>Please try with different Id!</font>";
            e.printStackTrace();
        } catch (Exception ex) {
            responseString = "<font color='red'>Please try later!</font>";
            System.out.println("hi" + ex.getMessage());
        } finally {
            try {

                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return responseString;
    }

    /*Author : Santosh
     * Date : 05/03/2013
     * For getting User Details
     */
    public UserBean userDetails(int userId) throws ServiceLocatorException {
        userBean = new UserBean();

        /**
         * The queryString is created depends on the employeeId
         */
        queryString = "select LOGINID,ROLE_ID,FNME+'.'+LNME as USERNAME   from M_USER LEFT OUTER JOIN M_USER_ROLES on (M_USER.ID=M_USER_ROLES.USER_ID) WHERE ID=" + userId;
        // queryString = "select LOGINID,ROLE_ID,concat(FNME ,LNME) as USERNAME   from M_USER LEFT OUTER JOIN M_USER_ROLES on (M_USER.ID=M_USER_ROLES.USER_ID) WHERE ID="+userId;
        try {

            /**
             * @return connection Object
             * @throws SQLException If a SQLExceptionexists and its <code>{@link
             *          com.mss.mirage.util.ServiceLocatorException}</code>
             * @see com.mss.mirage.util.ServiceLocatorException.              {@link
             *          com.mss.mirage.util.ConnectionProvider#getConnection()}
             */
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            // set the Id in adminroleVTO
            userBean.setUserId(userId);
            // Loop Starts
            while (resultSet.next()) {

                // Sets  the  value to corresponding setter methods of  adminroleVTO
                userBean.setLoginId(resultSet.getString("LOGINID"));
                // userBean.setPrimaryFlow(resultSet.getString("ROLE_ID"));
                userBean.setPrimaryRole(DataSourceDataProvider.getInstance().getRoleNameByRoleId(resultSet.getString("ROLE_ID")));
                userBean.setName(resultSet.getString("USERNAME"));

            }
            userBean.setPrimaryFlow(DataSourceDataProvider.getInstance().getPrimaryFlowID(userId));

        } catch (SQLException se) {
            throw new ServiceLocatorException(se);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    /*Releasing Statement Object*/
                    statement = null;
                }
                if (connection != null) {
                    // If null Closes the Connection
                    connection.close();
                    connection = null;
                }
            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }

        return userBean;
    }

    public int insertFlows(String[] assignedFlowIds, int employeeId, int primaryFlowId) throws ServiceLocatorException {
        int insertedRows = 0;
        int updateRows = 0;
        int deletedRows = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            /**
             * The queryString is created depends on the employeeId
             */
            queryString = "DELETE FROM M_USER_FLOWS_ACTION WHERE USER_ID=" + employeeId;

            deletedRows = statement.executeUpdate(queryString);
            statement.close();
            statement = null;
            statement = connection.createStatement();

            /**
             * it loops the roles.length and inserts the data into database for
             * each addedVals
             *
             * @throws NullPointerException If a NullPointerException exists and
             * its <code>{@link
             *          java.lang.NullPointerException}</code>
             */
            for (int counter = 0; counter < assignedFlowIds.length; counter++) {
                if (Integer.parseInt(assignedFlowIds[counter]) == primaryFlowId) {
                    queryString = "Insert into M_USER_FLOWS_ACTION(PRIORITY,USER_ID,FLOWID) values(1," + employeeId + ", " + Integer.parseInt(assignedFlowIds[counter]) + ")";

                } else {
                    queryString = "Insert into M_USER_FLOWS_ACTION(PRIORITY,USER_ID,FLOWID) values(2," + employeeId + ", " + Integer.parseInt(assignedFlowIds[counter]) + ")";

                }
                insertedRows = statement.executeUpdate(queryString);
            }

        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }

            } catch (SQLException se) {
                throw new ServiceLocatorException(se);
            }
        }
        return insertedRows;

    }
}
