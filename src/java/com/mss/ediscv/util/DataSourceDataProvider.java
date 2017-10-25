/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author miracle
 */
public class DataSourceDataProvider {
    private static DataSourceDataProvider _instance;
    private DataSourceDataProvider() {

	}

	public static DataSourceDataProvider getInstance() {

		if (_instance == null) {
			_instance = new DataSourceDataProvider();
		}
		return _instance;
	}
    
    
    public List getCorrelationNames(int st,int groupId) throws ServiceLocatorException{
      Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection(); 
        List correlationList = new ArrayList();
        
        try {
            if(st == 0){
            queryString = "SELECT DISTINCT(NAME) FROM CORRELATION where GROUP_ID="+groupId+" order by NAME";
            }else{
                queryString = "SELECT DISTINCT(NAME) FROM CORRELATION where GROUP_ID="+groupId+" AND MODE_ID="+st+" order by NAME";
            }
           // System.out.println("Query-->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                correlationList.add(resultSet.getString("NAME"));
            }
            
            
        }catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                 if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        
        return correlationList;
    }
    
    /*
     * 
     * Method for getting DocumentTypes
     */
  /*  public Map getDocumentTypeMap(int st,int groupId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection(); 
        
         Map docTypeMap = new TreeMap();
        
        try {
          // System.out.println("in doc type map");
            if(st == 0){
                queryString = "SELECT ID,NAME FROM DOCUMENTTYPES where GROUP_ID="+groupId+" ORDER BY NAME";
            }else {
                queryString = "SELECT ID,NAME FROM DOCUMENTTYPES where GROUP_ID="+groupId+" AND MODE_ID="+st+" ORDER BY NAME";
            }
            //System.out.println("queryString--->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                docTypeMap.put(resultSet.getString("ID"), resultSet.getString("NAME"));
            }
            
            
        }catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        //System.out.println("map-->"+docTypeMap);
        return docTypeMap;
    }*/
   public List getDocumentTypeList() throws ServiceLocatorException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       String queryString = null;
       connection = ConnectionProvider.getInstance().getConnection();
       List docTypeMap = new ArrayList();
       //String Transaction_Type=null;
       try {
          // System.out.println("st--->"+st);
           
        
               queryString = "SELECT distinct(FILES.TRANSACTION_TYPE) FROM FILES where FILES.TRANSACTION_TYPE is not null";
           //System.out.println("queryString--->"+queryString);
           preparedStatement = connection.prepareStatement(queryString);
           resultSet = preparedStatement.executeQuery();
         
           while(resultSet.next()) {
               if(resultSet.getString("TRANSACTION_TYPE")!=null&&!"".equals(resultSet.getString("TRANSACTION_TYPE")))
               {
                             docTypeMap.add(resultSet.getString("TRANSACTION_TYPE"));
               }
           }
           
           
       }catch (SQLException sql) {
           throw new ServiceLocatorException(sql);
       }finally{
           try {
               if(resultSet != null) {
                   resultSet.close();
                   resultSet = null;
               }
               if(preparedStatement != null) {
                   preparedStatement.close();
                   preparedStatement = null;
               }
               if(connection != null) {
                   connection.close();
                   connection = null;
               }
           } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
           }
       }
       
       return docTypeMap;
   }
    /**
     *   DESC : doc type as list
     * @return
     * @throws ServiceLocatorException 
     */
    
        public List getDocumentTypeList(int st,int groupId) throws ServiceLocatorException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       String queryString = null;
       connection = ConnectionProvider.getInstance().getConnection();
       List docTypeMap = new ArrayList();
       
       try {
          // System.out.println("st--->"+st);
           
           if(st == 0){
               queryString = "SELECT NAME FROM DOCUMENTTYPES where GROUP_ID="+groupId;
           }else {
               queryString = "SELECT NAME FROM DOCUMENTTYPES where GROUP_ID="+groupId+" AND MODE_ID="+st;
           }
           System.out.println("queryString--->"+queryString);
           preparedStatement = connection.prepareStatement(queryString);
           resultSet = preparedStatement.executeQuery();
           while(resultSet.next()) {
               docTypeMap.add(resultSet.getString("NAME"));
           }
           
           
       }catch (SQLException sql) {
           throw new ServiceLocatorException(sql);
       }finally{
           try {
               if(resultSet != null) {
                   resultSet.close();
                   resultSet = null;
               }
               if(preparedStatement != null) {
                   preparedStatement.close();
                   preparedStatement = null;
               }
               if(connection != null) {
                   connection.close();
                   connection = null;
               }
           } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
           }
       }
       
       return docTypeMap;
   }
  /*
     * 
     * Map for Reteriving States
     */
    
    public Map getStates() throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection(); 
        Map statesMap = new HashMap();
        
        try {
            
            queryString = "SELECT DESCRIPTION,NAME FROM TBLLKSTATES ORDER BY DESCRIPTION";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                statesMap.put(resultSet.getString("DESCRIPTION"), resultSet.getString("NAME"));
            }
            
            
        }catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        
        return statesMap;
    }
    
    /**
     * DESC: to get category map
     */
    
     public Map getCategory() throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection(); 
        Map categoryMap = new HashMap();
        
        try {
            
            queryString = "SELECT CAT_ID,CAT_NAME FROM MSCVP_CATEGORY OREDER BY CAT_ID";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                categoryMap.put(resultSet.getString("CAT_ID"), resultSet.getString("CAT_NAME"));
            }
            
            
        }catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        
        return categoryMap;
    }
    
        /**
         * DESC: priority map
         * 
         */
     public Map getPriority() throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection(); 
        Map priorityMap = new HashMap();
        
        try {
            
            queryString = "SELECT PRI_ID,PRI_NAME FROM	MSCVP_PRIORITY ORDER BY PR_ID";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                priorityMap.put(resultSet.getString("PRI_ID"), resultSet.getString("PRI_NAME"));
            }
            
            
        }catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        
        return priorityMap;
    }
         
         /**
          *  Name : getEmailByLoginId() 
          *  DESC: To get the Email of an employee based on his logid
          *  Return : String , EmilaId
          *  
          *  Date created : 05-02-2013
          */
   public String getEmailByLoginId(String loginid) throws ServiceLocatorException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection(); 
        //Map priorityMap = new HashMap();
        String emialid=null;
        
        try {
            
            queryString = "select EMAIL from M_user where LOGINID LIKE '%"+loginid+"%'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                emialid = resultSet.getString("EMAIL");
                //priorityMap.put(resultSet.getString("PRI_ID"), resultSet.getString("PRI_NAME"));
            }
            
            
        }catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        
        return emialid;
         }
   
   
   
   public String getNameByLoginId(String loginid) throws ServiceLocatorException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection(); 
        //Map priorityMap = new HashMap();
        String name=null;
        
        try {
            
            queryString = "select concat(FNME,LNME) as name from M_user where LOGINID LIKE '%"+loginid+"%'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                name = resultSet.getString("name");
                //priorityMap.put(resultSet.getString("PRI_ID"), resultSet.getString("PRI_NAME"));
            }
            
            
        }catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        
        return name;
         }
   
    public Map getFlows(int empId) throws ServiceLocatorException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection(); 
        Map flowMap = new HashMap();
        
        try {
            
            queryString = "SELECT PRIORITY,MSCVP_FLOWS.FLOWNAME,MSCVP_FLOWS.ID FROM MSCVP_FLOWS LEFT OUTER JOIN M_USER_FLOWS_ACTION ON (MSCVP_FLOWS.ID = M_USER_FLOWS_ACTION.FLOWID) WHERE USER_ID="+empId+" order by PRIORITY";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
               // flowMap.put(resultSet.getString("PRIORITY"), resultSet.getString("FLOWNAME"));
                 flowMap.put(resultSet.getString("ID"), resultSet.getString("FLOWNAME"));
            }
            
            
        }catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        
        return flowMap;
    }
   
   /**
     * to get the roleName
   */
       public String getRoleNameByRoleId(String roleId) throws ServiceLocatorException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection(); 
        //Map priorityMap = new HashMap();
        String RoleName=null;
        
        try {
            
            queryString = "select ROLE_NAME from MSCVP_ROLES where Id="+roleId;
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                RoleName = resultSet.getString("ROLE_NAME");
                //priorityMap.put(resultSet.getString("PRI_ID"), resultSet.getString("PRI_NAME"));
            }
            
            
        }catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        
        return RoleName;
         }
       
       /**
        *  To getPriorityById
        */
       
      public String getPriorityById(int id) throws ServiceLocatorException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       String queryString = null;
       connection = ConnectionProvider.getInstance().getConnection();
       String priorityName = null;
       
       try {
           
           queryString = "SELECT PRI_NAME FROM MSCVP_PRIORITY WHERE PRI_ID ="+id;
           preparedStatement = connection.prepareStatement(queryString);
           resultSet = preparedStatement.executeQuery();
           while(resultSet.next()) {
               priorityName = resultSet.getString("PRI_NAME");
           }
           
           
       }catch (SQLException sql) {
           throw new ServiceLocatorException(sql);
       }finally{
           try {
               if(resultSet != null) {
                   resultSet.close();
                   resultSet = null;
               }
               if(preparedStatement != null) {
                   preparedStatement.close();
                   preparedStatement = null;
               }
               if(connection != null) {
                   connection.close();
                   connection = null;
               }
           } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
           }
       }
       
       return priorityName;
   } 
      
      /**
       * To getCategoryById
       * 
       */
      
      public String getCategoryById(int id) throws ServiceLocatorException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       String queryString = null;
       connection = ConnectionProvider.getInstance().getConnection();
       String categoryName = null;
       
       try {
           
           queryString = "SELECT CAT_NAME FROM MSCVP_CATEGORY WHERE CAT_ID ="+id;
           preparedStatement = connection.prepareStatement(queryString);
           resultSet = preparedStatement.executeQuery();
           while(resultSet.next()) {
               categoryName = resultSet.getString("CAT_NAME");
           }
           
           
       }catch (SQLException sql) {
           throw new ServiceLocatorException(sql);
       }finally{
           try {
               if(resultSet != null) {
                   resultSet.close();
                   resultSet = null;
               }
               if(preparedStatement != null) {
                   preparedStatement.close();
                   preparedStatement = null;
               }
               if(connection != null) {
                   connection.close();
                   connection = null;
               }
           } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
           }
       }
       
       return categoryName;
   }
      
      /**
       * To get user map
       */
      public Map getUsers() throws ServiceLocatorException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       String queryString = null;
       connection = ConnectionProvider.getInstance().getConnection();
       Map usersMap = new HashMap();
       
       try {
           
           queryString = "select LOGINID,concat(FNME,LNME) as cname from M_USER as MU left outer join M_USER_ROLES as MUR on (MU.id=MUR.USER_ID)WHERE MUR.ROLE_ID != 1";
           preparedStatement = connection.prepareStatement(queryString);
           resultSet = preparedStatement.executeQuery();
           while(resultSet.next()) {
               usersMap.put(resultSet.getString("LOGINID"), resultSet.getString("cname"));
           }
           
           
       }catch (SQLException sql) {
           throw new ServiceLocatorException(sql);
       }finally{
           try {
               if(resultSet != null) {
                   resultSet.close();
                   resultSet = null;
               }
               if(preparedStatement != null) {
                   preparedStatement.close();
                   preparedStatement = null;
               }
               if(connection != null) {
                   connection.close();
                   connection = null;
               }
           } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
           }
       }
       
       return usersMap;
   }
      
       /*
        * 
        * For Assgetting Assigned Flows
        * Date : )5/03/2013
        * Author : Santosh
        */
       public Map getAssignedFlows(int userId) throws ServiceLocatorException{
        
        Map assignedRoleMap =  new TreeMap(); // Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            
            
           // resultSet = statement.executeQuery("SELECT ID,FLOWNAME FROM MSCVP_ROLES LEFT OUTER JOIN M_USER_ROLES on (MSCVP_ROLES.ID=M_USER_ROLES.ROLE_ID) where USER_ID="+userId+" AND ID != 1 ORDER BY ROLE_NAME");
            resultSet = statement.executeQuery("SELECT MSCVP_FLOWS.ID,MSCVP_FLOWS.FLOWNAME FROM MSCVP_FLOWS LEFT OUTER JOIN M_USER_FLOWS_ACTION ON (MSCVP_FLOWS.ID = M_USER_FLOWS_ACTION.FLOWID) where USER_ID="+userId+" AND MSCVP_FLOWS.ID != 1 ORDER BY FLOWID");
            while(resultSet.next()) {
                assignedRoleMap.put(resultSet.getString("ID"),resultSet.getString("FLOWNAME"));
            }
            
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return assignedRoleMap; // returning the object.
    }//closing the method.
       
       
       public Map getNotAssignedFlows(int userId) throws ServiceLocatorException{
        Map notAssignedFlowMap = new TreeMap();// Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
        
        try {
            statement = connection.createStatement();
           // resultSet = statement.executeQuery("select  ID as RoleId, ROLE_NAME  from MSCVP_ROLES where MSCVP_ROLES.ID not in (select ROLE_ID from M_USER_ROLES where USER_ID="+userId+") AND ID != 1 ORDER BY ROLE_NAME");
             resultSet = statement.executeQuery("SELECT MSCVP_FLOWS.ID,MSCVP_FLOWS.FLOWNAME FROM MSCVP_FLOWS LEFT OUTER JOIN M_USER_FLOWS_ACTION ON (MSCVP_FLOWS.ID = M_USER_FLOWS_ACTION.FLOWID) WHERE MSCVP_FLOWS.ID NOT IN (SELECT M_USER_FLOWS_ACTION.FLOWID FROM M_USER_FLOWS_ACTION WHERE M_USER_FLOWS_ACTION.USER_ID="+userId+") AND MSCVP_FLOWS.ID != 1 ORDER BY MSCVP_FLOWS.ID");
            while(resultSet.next()) {
                notAssignedFlowMap.put(resultSet.getString("ID"),resultSet.getString("FLOWNAME"));
                
            }
            
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        
        return notAssignedFlowMap; // returning the object.
    }//closing the method.
       
     public Map getFlowbyflowKey(String flowsKey) throws ServiceLocatorException{
          Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection();
         Map flowsMap = new TreeMap();//key-Description
        if (CacheManager.getCache().containsKey(flowsKey)) {
            
            flowsMap  = (Map) CacheManager.getCache().get(flowsKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
           // Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
           // Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            
           // String SQL_STG="select tp.id,tp.description from RolesData as tp";
            try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT ID,FLOWNAME FROM MSCVP_FLOWS where Id != 1");
            while(resultSet.next()) {
                flowsMap.put(resultSet.getString("ID"),resultSet.getString("FLOWNAME"));
                
            }
            CacheManager.getCache().put(flowsKey, flowsMap);
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
            
            
        }// closing if condition.
        
        return flowsMap; // returning the object.
         
     }  
     
     
      public String getPrimaryFlowID(int userId) throws ServiceLocatorException{
        
        Map assignedRoleMap =  new TreeMap(); // Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String primaryFlowId = null;
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            
            
           // resultSet = statement.executeQuery("SELECT ID,FLOWNAME FROM MSCVP_ROLES LEFT OUTER JOIN M_USER_ROLES on (MSCVP_ROLES.ID=M_USER_ROLES.ROLE_ID) where USER_ID="+userId+" AND ID != 1 ORDER BY ROLE_NAME");
           // resultSet = statement.executeQuery("SELECT MSCVP_FLOWS.ID,MSCVP_FLOWS.FLOWNAME FROM MSCVP_FLOWS LEFT OUTER JOIN M_USER_FLOWS_ACTION ON (MSCVP_FLOWS.ID = M_USER_FLOWS_ACTION.FLOWID) where USER_ID="+userId+" AND MSCVP_FLOWS.ID != 1 ORDER BY FLOWID");
             //resultSet = statement.executeQuery("SELECT MSCVP_FLOWS.ID FROM MSCVP_FLOWS  LEFT OUTER JOIN M_USER_FLOWS_ACTION ON (MSCVP_FLOWS.ID = M_USER_FLOWS_ACTION.FLOWID) where USER_ID="+userId+" AND MSCVP_FLOWS.ID != 1 AND M_USER_FLOWS_ACTION.PRIORITY = 1");
              if(userId != 10000) {
             resultSet = statement.executeQuery("SELECT MSCVP_FLOWS.ID FROM MSCVP_FLOWS  LEFT OUTER JOIN M_USER_FLOWS_ACTION ON (MSCVP_FLOWS.ID = M_USER_FLOWS_ACTION.FLOWID) where USER_ID="+userId+" AND MSCVP_FLOWS.ID != 1 AND M_USER_FLOWS_ACTION.PRIORITY = 1");
            }else {
               resultSet = statement.executeQuery("SELECT MSCVP_FLOWS.ID FROM MSCVP_FLOWS  LEFT OUTER JOIN M_USER_FLOWS_ACTION ON (MSCVP_FLOWS.ID = M_USER_FLOWS_ACTION.FLOWID) where USER_ID="+userId+" AND M_USER_FLOWS_ACTION.PRIORITY = 1"); 
            }
            while(resultSet.next()) {
                //assignedRoleMap.put(resultSet.getString("ID"),resultSet.getString("FLOWNAME"));
                primaryFlowId = resultSet.getString("ID");
            }
            
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return primaryFlowId; // returning the object.
    }//closing the method.
       
      public String getFlowNameByFlowID(String flowId) throws ServiceLocatorException{
        
        Map assignedRoleMap =  new TreeMap(); // Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String flowName = null;
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            
            
           // resultSet = statement.executeQuery("SELECT ID,FLOWNAME FROM MSCVP_ROLES LEFT OUTER JOIN M_USER_ROLES on (MSCVP_ROLES.ID=M_USER_ROLES.ROLE_ID) where USER_ID="+userId+" AND ID != 1 ORDER BY ROLE_NAME");
           // resultSet = statement.executeQuery("SELECT MSCVP_FLOWS.ID,MSCVP_FLOWS.FLOWNAME FROM MSCVP_FLOWS LEFT OUTER JOIN M_USER_FLOWS_ACTION ON (MSCVP_FLOWS.ID = M_USER_FLOWS_ACTION.FLOWID) where USER_ID="+userId+" AND MSCVP_FLOWS.ID != 1 ORDER BY FLOWID");
             resultSet = statement.executeQuery("SELECT FLOWNAME FROM MSCVP_FLOWS where Id ="+flowId);
            while(resultSet.next()) {
                //assignedRoleMap.put(resultSet.getString("ID"),resultSet.getString("FLOWNAME"));
                flowName = resultSet.getString("FLOWNAME");
            }
            
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return flowName; // returning the object.
    }//closing the method.
         
     public String getFlowIdByFlowName(String flowName) throws ServiceLocatorException{
        
        Map assignedRoleMap =  new TreeMap(); // Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String flowId = null;
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            
            
           // resultSet = statement.executeQuery("SELECT ID,FLOWNAME FROM MSCVP_ROLES LEFT OUTER JOIN M_USER_ROLES on (MSCVP_ROLES.ID=M_USER_ROLES.ROLE_ID) where USER_ID="+userId+" AND ID != 1 ORDER BY ROLE_NAME");
           // resultSet = statement.executeQuery("SELECT MSCVP_FLOWS.ID,MSCVP_FLOWS.FLOWNAME FROM MSCVP_FLOWS LEFT OUTER JOIN M_USER_FLOWS_ACTION ON (MSCVP_FLOWS.ID = M_USER_FLOWS_ACTION.FLOWID) where USER_ID="+userId+" AND MSCVP_FLOWS.ID != 1 ORDER BY FLOWID");
             resultSet = statement.executeQuery("SELECT ID FROM MSCVP_FLOWS where FLOWNAME ='"+flowName+"'");
            while(resultSet.next()) {
                //assignedRoleMap.put(resultSet.getString("ID"),resultSet.getString("FLOWNAME"));
                flowId = resultSet.getString("ID");
            }
            
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return flowId; // returning the object.
    }//closing the method.  
     
     /** MSSCVP roles **/
     public Map getMsscvpRoles() throws ServiceLocatorException{
        
        Map assignedRoleMap =  new TreeMap(); // Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            
            
           // resultSet = statement.executeQuery("SELECT ID,FLOWNAME FROM MSCVP_ROLES LEFT OUTER JOIN M_USER_ROLES on (MSCVP_ROLES.ID=M_USER_ROLES.ROLE_ID) where USER_ID="+userId+" AND ID != 1 ORDER BY ROLE_NAME");
            resultSet = statement.executeQuery("select ID,Role_NAME From MSCVP_ROLES");
            while(resultSet.next()) {
                assignedRoleMap.put(resultSet.getString("ID"),resultSet.getString("Role_NAME"));
            }
            
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return assignedRoleMap; // returning the object.
    }//closing the method.
     
     public String UpdateReProcessStatus(String Status,String fileId,String sec_key_Value,String flowFlag) throws ServiceLocatorException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int i=0;
        String queryString = null;
        String result = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            //statement = connection.createStatement();
            
            queryString = "UPDATE FILES SET REPROCESSSTATUS = '"+ Status +"' WHERE FILE_ID LIKE '" + fileId + "' AND SEC_KEY_VAL LIKE '" +sec_key_Value+"' AND FLOWFLAG like '" +flowFlag+"'";
          //  System.out.println("update query --->"+queryString);
            preparedStatement = connection.prepareStatement(queryString);
            i = preparedStatement.executeUpdate();
            
            if(i==1){
                result = "success";
            }else{
                result = "fail";
            }
            
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return result; // returning the object.
    }//closing the method.
     
     public String getSapDetails(String instanceId,String poNumber) throws ServiceLocatorException{
         
         System.out.println("instanceId-->"+instanceId);
         System.out.println("poNumber-->"+poNumber);
          Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String responseString = "None";
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            
            
           // resultSet = statement.executeQuery("SELECT ID,FLOWNAME FROM MSCVP_ROLES LEFT OUTER JOIN M_USER_ROLES on (MSCVP_ROLES.ID=M_USER_ROLES.ROLE_ID) where USER_ID="+userId+" AND ID != 1 ORDER BY ROLE_NAME");
            resultSet = statement.executeQuery("select SAP_USER, IDOC_NUMBER, PO_NUMBER, PO_DATE, IDOC_STATUS_CODE, IDOC_STATUS_DESCRIPTION from SAP_RECONCILIATION_DETAILS WHERE INSTANCE_ID='"+instanceId+"' AND PO_NUMBER='"+poNumber+"'");
            if(resultSet.next()) {
                responseString = "";
                
                if(!"".equals(resultSet.getString("SAP_USER"))&&resultSet.getString("SAP_USER")!=null){
                    if(!"".equals(resultSet.getString("SAP_USER").trim())){
                responseString = resultSet.getString("SAP_USER");
                }else {
                        responseString = "--";
                    }
                }else {
                        responseString = "--";
                    }
                    //---------
                 if(!"".equals(resultSet.getString("IDOC_NUMBER"))&&resultSet.getString("IDOC_NUMBER")!=null){
                    if(!"".equals(resultSet.getString("IDOC_NUMBER").trim())){
                responseString = responseString+"|"+resultSet.getString("IDOC_NUMBER");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                 //-------
                  if(!"".equals(resultSet.getString("PO_NUMBER"))&&resultSet.getString("PO_NUMBER")!=null){
                    if(!"".equals(resultSet.getString("PO_NUMBER").trim())){
                responseString = responseString+"|"+resultSet.getString("PO_NUMBER");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                  //-----------
                   if(!"".equals(resultSet.getString("PO_DATE"))&&resultSet.getString("PO_DATE")!=null){
                    if(!"".equals(resultSet.getString("PO_DATE").trim())){
                responseString = responseString+"|"+resultSet.getString("PO_DATE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                   //------------
                    if(!"".equals(resultSet.getString("IDOC_STATUS_CODE"))&&resultSet.getString("IDOC_STATUS_CODE")!=null){
                    if(!"".equals(resultSet.getString("IDOC_STATUS_CODE").trim())){
                responseString = responseString+"|"+resultSet.getString("IDOC_STATUS_CODE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                    //--------IDOC_STATUS_DESCRIPTION
                    if(!"".equals(resultSet.getString("IDOC_STATUS_DESCRIPTION"))&&resultSet.getString("IDOC_STATUS_DESCRIPTION")!=null){
                    if(!"".equals(resultSet.getString("IDOC_STATUS_DESCRIPTION").trim())){
                responseString = responseString+"|"+resultSet.getString("IDOC_STATUS_DESCRIPTION");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                //}
              //  assignedRoleMap.put(resultSet.getString("ID"),resultSet.getString("Role_NAME"));
                //responseString = resultSet.getString("SAP_USER")+"|"+resultSet.getString("IDOC_NUMBER")+"|"+resultSet.getString("PO_NUMBER")+"|"+resultSet.getString("PO_DATE")+"|"+resultSet.getString("IDOC_STATUS_CODE")+"|"+resultSet.getString("IDOC_STATUS_DESCRIPTION");
            }
            
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return responseString;
     }
     
     
      /** MSSCVP roles **/
     public Map getPartnerMap() throws ServiceLocatorException{
        
        Map partnerMap =  new TreeMap(); // Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            
            
           // resultSet = statement.executeQuery("SELECT ID,FLOWNAME FROM MSCVP_ROLES LEFT OUTER JOIN M_USER_ROLES on (MSCVP_ROLES.ID=M_USER_ROLES.ROLE_ID) where USER_ID="+userId+" AND ID != 1 ORDER BY ROLE_NAME");
            resultSet = statement.executeQuery("SELECT NAME,ID From TP where STATUS='ACTIVE' AND NAME !='METRIE'");
            while(resultSet.next()) {
                partnerMap.put(resultSet.getString("ID"),resultSet.getString("NAME"));
            }
            
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return partnerMap; // returning the object.
    }//closing the method.
     
     /** MSSCVP roles **/
     public Map getRouterMap() throws ServiceLocatorException{
        
        Map routerMap =  new TreeMap(); // Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            
            
           // resultSet = statement.executeQuery("SELECT ID,FLOWNAME FROM MSCVP_ROLES LEFT OUTER JOIN M_USER_ROLES on (MSCVP_ROLES.ID=M_USER_ROLES.ROLE_ID) where USER_ID="+userId+" AND ID != 1 ORDER BY ROLE_NAME");
            resultSet = statement.executeQuery("SELECT ROUTER_ID, ROUTER_NAME FROM ROUTERINFO");
            while(resultSet.next()) {
                routerMap.put(resultSet.getString("ROUTER_ID"),resultSet.getString("ROUTER_NAME"));
            }
            
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return routerMap; // returning the object.
    }//closing the method.
     
     /** MSSCVP roles **/
     public Map getRelationMap(String mapType) throws ServiceLocatorException{
        
        Map processMap =  new TreeMap(); // Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            
            
           // resultSet = statement.executeQuery("SELECT ID,FLOWNAME FROM MSCVP_ROLES LEFT OUTER JOIN M_USER_ROLES on (MSCVP_ROLES.ID=M_USER_ROLES.ROLE_ID) where USER_ID="+userId+" AND ID != 1 ORDER BY ROLE_NAME");
            resultSet = statement.executeQuery("SELECT REL_ID,RELNAME FROM PROCESSRELATEDINFO WHERE FLAG='"+mapType+"'");
            while(resultSet.next()) {
                processMap.put(resultSet.getString("REL_ID"),resultSet.getString("RELNAME"));
            }
            
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return processMap; // returning the object.
    }//closing the method.
    
 public Map getEncodeMap() throws ServiceLocatorException{
        
        Map processMap =  new TreeMap(); // Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            
            
           // resultSet = statement.executeQuery("SELECT ID,FLOWNAME FROM MSCVP_ROLES LEFT OUTER JOIN M_USER_ROLES on (MSCVP_ROLES.ID=M_USER_ROLES.ROLE_ID) where USER_ID="+userId+" AND ID != 1 ORDER BY ROLE_NAME");
            resultSet = statement.executeQuery("SELECT VALUE,DESCRIPTION FROM DELEVERYCHANNELDESCRPTION WHERE STATUS='Active'");
            while(resultSet.next()) {
                processMap.put(resultSet.getString("VALUE"),resultSet.getString("DESCRIPTION"));
            }
            
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return processMap; // returning the object.
    }//closing the method.


public Map getDashboardPartnerMap() throws ServiceLocatorException{
        
        Map partnerMap =  new TreeMap(); // Key-Description
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            statement = connection.createStatement();
            
            
           // resultSet = statement.executeQuery("SELECT ID,FLOWNAME FROM MSCVP_ROLES LEFT OUTER JOIN M_USER_ROLES on (MSCVP_ROLES.ID=M_USER_ROLES.ROLE_ID) where USER_ID="+userId+" AND ID != 1 ORDER BY ROLE_NAME");
            resultSet = statement.executeQuery("SELECT ID,NAME From TP ");
            while(resultSet.next()) {
                partnerMap.put(resultSet.getString("ID"),resultSet.getString("NAME"));
            }
            
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return partnerMap; // returning the object.
    }//closing the method.
     
 


/**Getting File EXT Details
 * Date:24/8/2015
 */
/*public String getFileExtDetails(String instanceId,String docType) throws ServiceLocatorException{
         
         System.out.println("instanceId-->"+instanceId);
         //System.out.println("keyValue-->"+keyValue);
         //System.out.println("keyType-->"+keyType);
          System.out.println("docType-->"+docType);
          Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = null;
        String responseString = "None";
        connection = ConnectionProvider.getInstance().getConnection();
        StringBuffer documentSearchQuery = new StringBuffer();
        try {
            statement = connection.createStatement();
           
           
            if(docType.equalsIgnoreCase("850")){
            documentSearchQuery.append("SELECT FILES_EXT.PARTNER_NAME,FILES_EXT.PO,FILES_EXT.PO_DATE,FILES_EXT.RDD,FILES_EXT.SHIP_TO_NAME,FILES_EXT.SHIP_TO_CODE,"
                                        +"FILES_EXT.SHIP_TO_CITY,FILES_EXT.SHIP_TO_POSTAL_CODE,FILES_EXT.SHIP_TO_REGION,FILES_EXT.SELLING_SALES_ORG,"
                                        +"FILES_EXT.LINE_COUNT,FILES_EXT.PO_AMOUNT,FILES_EXT.IDOC,FILES_EXT.SALES_ORDER FROM FILES_EXT  " 
                                        +"LEFT OUTER JOIN FILES ON (FILES.PRI_KEY_VAL=FILES_EXT.PO AND FILES.TRANSACTION_TYPE='"+docType+"' AND FILES.FILE_ID=FILES_EXT.FILE_ID)");
              documentSearchQuery.append(" where FILES.FILE_ID='"+instanceId+"'");
            }
            if(docType.equalsIgnoreCase("810")){
            documentSearchQuery.append("SELECT FILES_EXT.PARTNER_NAME,FILES_EXT.INVOICE,FILES_EXT.INVOICE_DATE,FILES_EXT.PO,FILES_EXT.SHIP_TO_NAME,FILES_EXT.SHIP_TO_CODE,FILES_EXT.SHIP_TO_CITY,"
                                        +"FILES_EXT.SHIP_TO_POSTAL_CODE,FILES_EXT.SHIP_TO_REGION,FILES_EXT.SALES_AREA,FILES_EXT.ITEM_LINE_COUNT,FILES_EXT.INVOICE_TOTAL,"
                                        +"FILES_EXT.IDOC FROM FILES_EXT  " 
                                        +"LEFT OUTER JOIN FILES ON (FILES.PRI_KEY_VAL=FILES_EXT.INVOICE AND FILES.TRANSACTION_TYPE='"+docType+"' AND FILES.FILE_ID=FILES_EXT.FILE_ID)");
              documentSearchQuery.append(" where FILES.FILE_ID='"+instanceId+"'"); 
            }
            if(docType.equalsIgnoreCase("820")){
            documentSearchQuery.append("SELECT FILES_EXT.PARTNER_NAME,FILES_EXT.PAYMENT_METHOD,FILES_EXT.CURRENCY,FILES_EXT.TRANSACTION_TRACE_CODE,"
                                        +"FILES_EXT.CHEQUE_NUMBER,FILES_EXT.PAYEE_VENDOR_ID,FILES_EXT.PAYEE_NAME,FILES_EXT.MONETARY_AMOUNT,"
                                        +"FILES_EXT.FILE FROM FILES_EXT  " 
                                        +"LEFT OUTER JOIN FILES ON (FILES.PRI_KEY_VAL=FILES_EXT.CHEQUE_NUMBER AND FILES.TRANSACTION_TYPE='"+docType+"' AND FILES.FILE_ID=FILES_EXT.FILE_ID)");//ON (FILES.PRI_KEY_VAL=FILES_EXT.INVOICE)
              documentSearchQuery.append(" where FILES.FILE_ID='"+instanceId+"'");
            }
      
            
            
            String searchQuery = documentSearchQuery.toString();
           // resultSet = statement.executeQuery("SELECT ID,FLOWNAME FROM MSCVP_ROLES LEFT OUTER JOIN M_USER_ROLES on (MSCVP_ROLES.ID=M_USER_ROLES.ROLE_ID) where USER_ID="+userId+" AND ID != 1 ORDER BY ROLE_NAME");
            System.out.println("Query="+searchQuery);
           if(searchQuery!=null&&!searchQuery.equalsIgnoreCase(""))
           {
            resultSet = statement.executeQuery(searchQuery);
                    
                    //+ "WHERE FILE_ID='"+instanceId+"' AND PO='"+poNumber+"'");
            
            if(resultSet.next()) {
                responseString = "";
                 if(!"".equals(resultSet.getString("PARTNER_NAME"))&&resultSet.getString("PARTNER_NAME")!=null){
                    if(!"".equals(resultSet.getString("PARTNER_NAME").trim())){
                responseString = resultSet.getString("PARTNER_NAME");
                }else {
                        responseString = "--";
                    }
                }else {
                        responseString = "--";
                    }
                  if(docType.equalsIgnoreCase("850")){
               if(!"".equals(resultSet.getString("PO"))&&resultSet.getString("PO")!=null){
                    if(!"".equals(resultSet.getString("PO").trim())){
                responseString = responseString+"|"+resultSet.getString("PO");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
               if(!"".equals(resultSet.getString("PO_DATE"))&&resultSet.getString("PO_DATE")!=null){
                    if(!"".equals(resultSet.getString("PO_DATE").trim())){
                responseString = responseString+"|"+resultSet.getString("PO_DATE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                  if(!"".equals(resultSet.getString("RDD"))&&resultSet.getString("RDD")!=null){
                    if(!"".equals(resultSet.getString("RDD").trim())){
                responseString = responseString+"|"+resultSet.getString("RDD");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                  
                  
                   if(!"".equals(resultSet.getString("SHIP_TO_NAME"))&&resultSet.getString("SHIP_TO_NAME")!=null){
                    if(!"".equals(resultSet.getString("SHIP_TO_NAME").trim())){
                responseString = responseString+"|"+resultSet.getString("SHIP_TO_NAME");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                  if(!"".equals(resultSet.getString("SHIP_TO_CODE"))&&resultSet.getString("SHIP_TO_CODE")!=null){
                    if(!"".equals(resultSet.getString("SHIP_TO_CODE").trim())){
                responseString = responseString+"|"+resultSet.getString("SHIP_TO_CODE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
               if(!"".equals(resultSet.getString("SHIP_TO_CITY"))&&resultSet.getString("SHIP_TO_CITY")!=null){
                    if(!"".equals(resultSet.getString("SHIP_TO_CITY").trim())){
                responseString = responseString+"|"+resultSet.getString("SHIP_TO_CITY");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                
                  
                
                if(!"".equals(resultSet.getString("SHIP_TO_POSTAL_CODE"))&&resultSet.getString("SHIP_TO_POSTAL_CODE")!=null){
                    if(!"".equals(resultSet.getString("SHIP_TO_POSTAL_CODE").trim())){
                responseString = responseString+"|"+resultSet.getString("SHIP_TO_POSTAL_CODE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
               if(!"".equals(resultSet.getString("SHIP_TO_REGION"))&&resultSet.getString("SHIP_TO_REGION")!=null){
                    if(!"".equals(resultSet.getString("SHIP_TO_REGION").trim())){
                responseString = responseString+"|"+resultSet.getString("SHIP_TO_REGION");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
               if(!"".equals(resultSet.getString("SELLING_SALES_ORG"))&&resultSet.getString("SELLING_SALES_ORG")!=null){
              if(!"".equals(resultSet.getString("SELLING_SALES_ORG").trim())){
                responseString = responseString+"|"+resultSet.getString("SELLING_SALES_ORG");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
               if(!"".equals(resultSet.getString("LINE_COUNT"))&&resultSet.getString("LINE_COUNT")!=null){
                    if(!"".equals(resultSet.getString("LINE_COUNT").trim())){
                responseString = responseString+"|"+resultSet.getString("LINE_COUNT");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                   if(!"".equals(resultSet.getString("PO_AMOUNT"))&&resultSet.getString("PO_AMOUNT")!=null){
                       if(!"".equals(resultSet.getString("PO_AMOUNT").trim())){
                responseString = responseString+"|"+resultSet.getString("PO_AMOUNT");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }       
               if(!"".equals(resultSet.getString("IDOC"))&&resultSet.getString("IDOC")!=null){
                    if(!"".equals(resultSet.getString("IDOC").trim())){
                responseString = responseString+"|"+resultSet.getString("IDOC");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                 if(!"".equals(resultSet.getString("SALES_ORDER"))&&resultSet.getString("SALES_ORDER")!=null){
                    if(!"".equals(resultSet.getString("SALES_ORDER").trim())){
                responseString = responseString+"|"+resultSet.getString("SALES_ORDER");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
               }
              if(docType.equalsIgnoreCase("810")){
                  if(!"".equals(resultSet.getString("INVOICE"))&&resultSet.getString("INVOICE")!=null){
                    if(!"".equals(resultSet.getString("INVOICE").trim())){
                responseString = responseString+"|"+resultSet.getString("INVOICE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
               
                 //-------
                  if(!"".equals(resultSet.getString("INVOICE_DATE"))&&resultSet.getString("INVOICE_DATE")!=null){
                    if(!"".equals(resultSet.getString("INVOICE_DATE").trim())){
                responseString = responseString+"|"+resultSet.getString("INVOICE_DATE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                   if(!"".equals(resultSet.getString("PO"))&&resultSet.getString("PO")!=null){
                    if(!"".equals(resultSet.getString("PO").trim())){
                responseString = responseString+"|"+resultSet.getString("PO");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                if(!"".equals(resultSet.getString("SHIP_TO_NAME"))&&resultSet.getString("SHIP_TO_NAME")!=null){
                    if(!"".equals(resultSet.getString("SHIP_TO_NAME").trim())){
                responseString = responseString+"|"+resultSet.getString("SHIP_TO_NAME");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                   if(!"".equals(resultSet.getString("SHIP_TO_CODE"))&&resultSet.getString("SHIP_TO_CODE")!=null){
                    if(!"".equals(resultSet.getString("SHIP_TO_CODE").trim())){
                responseString = responseString+"|"+resultSet.getString("SHIP_TO_CODE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    } 
           
               if(!"".equals(resultSet.getString("SHIP_TO_CITY"))&&resultSet.getString("SHIP_TO_CITY")!=null){
                    if(!"".equals(resultSet.getString("SHIP_TO_CITY").trim())){
                responseString = responseString+"|"+resultSet.getString("SHIP_TO_CITY");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                
                  
                
                if(!"".equals(resultSet.getString("SHIP_TO_POSTAL_CODE"))&&resultSet.getString("SHIP_TO_POSTAL_CODE")!=null){
                    if(!"".equals(resultSet.getString("SHIP_TO_POSTAL_CODE").trim())){
                responseString = responseString+"|"+resultSet.getString("SHIP_TO_POSTAL_CODE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
               if(!"".equals(resultSet.getString("SHIP_TO_REGION"))&&resultSet.getString("SHIP_TO_REGION")!=null){
                    if(!"".equals(resultSet.getString("SHIP_TO_REGION").trim())){
                responseString = responseString+"|"+resultSet.getString("SHIP_TO_REGION");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                if(!"".equals(resultSet.getString("SALES_AREA"))&&resultSet.getString("SALES_AREA")!=null){
                    if(!"".equals(resultSet.getString("SALES_AREA").trim())){
                responseString = responseString+"|"+resultSet.getString("SALES_AREA");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                 if(!"".equals(resultSet.getString("ITEM_LINE_COUNT"))&&resultSet.getString("ITEM_LINE_COUNT")!=null){
                    if(!"".equals(resultSet.getString("ITEM_LINE_COUNT").trim())){
                responseString = responseString+"|"+resultSet.getString("ITEM_LINE_COUNT");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                  if(!"".equals(resultSet.getString("INVOICE_TOTAL"))&&resultSet.getString("INVOICE_TOTAL")!=null){
                    if(!"".equals(resultSet.getString("INVOICE_TOTAL").trim())){
                responseString = responseString+"|"+resultSet.getString("INVOICE_TOTAL");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                 
                 if(!"".equals(resultSet.getString("IDOC"))&&resultSet.getString("IDOC")!=null){
                    if(!"".equals(resultSet.getString("IDOC").trim())){
                responseString = responseString+"|"+resultSet.getString("IDOC");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
              }    
                  
              if(docType.equalsIgnoreCase("820")){
                  if(!"".equals(resultSet.getString("PAYMENT_METHOD"))&&resultSet.getString("PAYMENT_METHOD")!=null){
                    if(!"".equals(resultSet.getString("PAYMENT_METHOD").trim())){
                responseString = responseString+"|"+resultSet.getString("PAYMENT_METHOD");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                
                     if(!"".equals(resultSet.getString("CURRENCY"))&&resultSet.getString("CURRENCY")!=null){
                    if(!"".equals(resultSet.getString("CURRENCY").trim())){
                responseString = responseString+"|"+resultSet.getString("CURRENCY");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
               if(!"".equals(resultSet.getString("TRANSACTION_TRACE_CODE"))&&resultSet.getString("TRANSACTION_TRACE_CODE")!=null){
                    if(!"".equals(resultSet.getString("TRANSACTION_TRACE_CODE").trim())){
                responseString = responseString+"|"+resultSet.getString("TRANSACTION_TRACE_CODE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                if(!"".equals(resultSet.getString("CHEQUE_NUMBER"))&&resultSet.getString("CHEQUE_NUMBER")!=null){
                    if(!"".equals(resultSet.getString("CHEQUE_NUMBER").trim())){
                responseString = responseString+"|"+resultSet.getString("CHEQUE_NUMBER");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                if(!"".equals(resultSet.getString("PAYEE_VENDOR_ID"))&&resultSet.getString("PAYEE_VENDOR_ID")!=null){
                    if(!"".equals(resultSet.getString("PAYEE_VENDOR_ID").trim())){
                responseString = responseString+"|"+resultSet.getString("PAYEE_VENDOR_ID");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                 if(!"".equals(resultSet.getString("PAYEE_NAME"))&&resultSet.getString("PAYEE_NAME")!=null){
                    if(!"".equals(resultSet.getString("PAYEE_NAME").trim())){
                responseString = responseString+"|"+resultSet.getString("PAYEE_NAME");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                 if(!"".equals(resultSet.getString("MONETARY_AMOUNT"))&&resultSet.getString("MONETARY_AMOUNT")!=null){
                    if(!"".equals(resultSet.getString("MONETARY_AMOUNT").trim())){
                responseString = responseString+"|"+resultSet.getString("MONETARY_AMOUNT");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                if(!"".equals(resultSet.getString("FILE"))&&resultSet.getString("FILE")!=null){
                    if(!"".equals(resultSet.getString("FILE").trim())){
                responseString = responseString+"|"+resultSet.getString("FILE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString =responseString+"|"+"--";
                    }
                
                
              }
              
              
              /*
              if(keyType.equalsIgnoreCase("ASN")){
                  if(!"".equals(resultSet.getString("PAYMENT_METHOD"))&&resultSet.getString("PAYMENT_METHOD")!=null){
                    if(!"".equals(resultSet.getString("PAYMENT_METHOD").trim())){
                responseString = responseString+"|"+resultSet.getString("PAYMENT_METHOD");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                
                     if(!"".equals(resultSet.getString("CURRENCY"))&&resultSet.getString("CURRENCY")!=null){
                    if(!"".equals(resultSet.getString("CURRENCY").trim())){
                responseString = responseString+"|"+resultSet.getString("CURRENCY");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                       if(!"".equals(resultSet.getString("TRANSACTION_TRACE_CODE"))&&resultSet.getString("TRANSACTION_TRACE_CODE")!=null){
                    if(!"".equals(resultSet.getString("TRANSACTION_TRACE_CODE").trim())){
                responseString = responseString+"|"+resultSet.getString("TRANSACTION_TRACE_CODE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                  if(!"".equals(resultSet.getString("PAYEE_VENDOR_ID"))&&resultSet.getString("PAYEE_VENDOR_ID")!=null){
                    if(!"".equals(resultSet.getString("PAYEE_VENDOR_ID").trim())){
                responseString = responseString+"|"+resultSet.getString("PAYEE_VENDOR_ID");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                 if(!"".equals(resultSet.getString("PAYEE_NAME"))&&resultSet.getString("PAYEE_NAME")!=null){
                    if(!"".equals(resultSet.getString("PAYEE_NAME").trim())){
                responseString = responseString+"|"+resultSet.getString("PAYEE_NAME");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                 if(!"".equals(resultSet.getString("MONETARY_AMOUNT"))&&resultSet.getString("MONETARY_AMOUNT")!=null){
                    if(!"".equals(resultSet.getString("MONETARY_AMOUNT").trim())){
                responseString = responseString+"|"+resultSet.getString("MONETARY_AMOUNT");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                  if(!"".equals(resultSet.getString("IDOC"))&&resultSet.getString("IDOC")!=null){
                    if(!"".equals(resultSet.getString("IDOC").trim())){
                responseString = responseString+"|"+resultSet.getString("IDOC");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
              }    
                  */
                  
             // System.out.println("response string"+responseString);
           // }    
                  /*
                if(keyType.equalsIgnoreCase("ASN")){
              if(!"".equals(resultSet.getString("FILE"))&&resultSet.getString("FILE")!=null){
                    if(!"".equals(resultSet.getString("FILE").trim())){
                responseString = responseString+"|"+resultSet.getString("FILE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString =responseString+"|"+"--";
                    }
              System.out.println("file responseString"+responseString+resultSet.getString("FILE"));
               }
                if(!keyType.equalsIgnoreCase("ASN")){
              if(!"".equals(resultSet.getString("IDOC"))&&resultSet.getString("IDOC")!=null){
                    if(!"".equals(resultSet.getString("IDOC").trim())){
                responseString = responseString+"|"+resultSet.getString("IDOC");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
              System.out.println("idoc responseString"+responseString);
                }
                if(keyType.equalsIgnoreCase("ASN")||keyType.equalsIgnoreCase("PAYMENT")){
               if(!"".equals(resultSet.getString("PAYEE_NAME"))&&resultSet.getString("PAYEE_NAME")!=null){
                    if(!"".equals(resultSet.getString("PAYEE_NAME").trim())){
                responseString = responseString+"|"+resultSet.getString("PAYEE_NAME");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
               System.out.println("payee naame responseString"+responseString+resultSet.getString("PAYEE_NAME"));
                }
                if(keyType.equalsIgnoreCase("ASN")){
               if(!"".equals(resultSet.getString("PAYEE_VENDOR_ID"))&&resultSet.getString("PAYEE_VENDOR_ID")!=null){
                    if(!"".equals(resultSet.getString("PAYEE_VENDOR_ID").trim())){
                responseString = responseString+"|"+resultSet.getString("PAYEE_VENDOR_ID");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }
                if(keyType.equalsIgnoreCase("ASN")||keyType.equalsIgnoreCase("PAYMENT")){
               if(!"".equals(resultSet.getString("MONETARY_AMOUNT"))&&resultSet.getString("MONETARY_AMOUNT")!=null){
                    if(!"".equals(resultSet.getString("MONETARY_AMOUNT").trim())){
                responseString = responseString+"|"+resultSet.getString("MONETARY_AMOUNT");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                
                  if(!"".equals(resultSet.getString("PAYMENT_METHOD"))&&resultSet.getString("PAYMENT_METHOD")!=null){
                    if(!"".equals(resultSet.getString("PAYMENT_METHOD").trim())){
                responseString = responseString+"|"+resultSet.getString("PAYMENT_METHOD");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                
                     if(!"".equals(resultSet.getString("CURRENCY"))&&resultSet.getString("CURRENCY")!=null){
                    if(!"".equals(resultSet.getString("CURRENCY").trim())){
                responseString = responseString+"|"+resultSet.getString("CURRENCY");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                        }
                if(keyType.equalsIgnoreCase("ASN")){
                if(!"".equals(resultSet.getString("CHEQUE_NUMBER"))&&resultSet.getString("CHEQUE_NUMBER")!=null){
                    if(!"".equals(resultSet.getString("CHEQUE_NUMBER").trim())){
                responseString = responseString+"|"+resultSet.getString("CHEQUE_NUMBER");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }
                if(keyType.equalsIgnoreCase("ASN")||keyType.equalsIgnoreCase("PAYMENT")){
                           if(!"".equals(resultSet.getString("TRANSACTION_TRACE_CODE"))&&resultSet.getString("TRANSACTION_TRACE_CODE")!=null){
                    if(!"".equals(resultSet.getString("TRANSACTION_TRACE_CODE").trim())){
                responseString = responseString+"|"+resultSet.getString("TRANSACTION_TRACE_CODE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }
                if(keyType.equalsIgnoreCase("PO")||keyType.equalsIgnoreCase("IN")){
                if(!"".equals(resultSet.getString("SHIP_TO"))&&resultSet.getString("SHIP_TO")!=null){
                    if(!"".equals(resultSet.getString("SHIP_TO").trim())){
                responseString = responseString+"|"+resultSet.getString("SHIP_TO");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }
                if(keyType.equalsIgnoreCase("PO")){
               if(!"".equals(resultSet.getString("SHIP_TO_CITY"))&&resultSet.getString("SHIP_TO_CITY")!=null){
                    if(!"".equals(resultSet.getString("SHIP_TO_CITY").trim())){
                responseString = responseString+"|"+resultSet.getString("SHIP_TO_CITY");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }
                if(keyType.equalsIgnoreCase("PO")||keyType.equalsIgnoreCase("IN")){
                  if(!"".equals(resultSet.getString("SHIP_TO_REGION"))&&resultSet.getString("SHIP_TO_REGION")!=null){
                    if(!"".equals(resultSet.getString("SHIP_TO_REGION").trim())){
                responseString = responseString+"|"+resultSet.getString("SHIP_TO_REGION");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                
                if(!"".equals(resultSet.getString("SHIP_TO_POSTAL_CODE"))&&resultSet.getString("SHIP_TO_POSTAL_CODE")!=null){
                    if(!"".equals(resultSet.getString("SHIP_TO_POSTAL_CODE").trim())){
                responseString = responseString+"|"+resultSet.getString("SHIP_TO_POSTAL_CODE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }
               
                if(keyType.equalsIgnoreCase("PO")||keyType.equalsIgnoreCase("IN")){
               if(!"".equals(resultSet.getString("PO"))&&resultSet.getString("PO")!=null){
                    if(!"".equals(resultSet.getString("PO").trim())){
                responseString = responseString+"|"+resultSet.getString("PO");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }
                    //---------
                if(keyType.equalsIgnoreCase("PO")){
                 if(!"".equals(resultSet.getString("PO_DATE"))&&resultSet.getString("PO_DATE")!=null){
                    if(!"".equals(resultSet.getString("PO_DATE").trim())){
                responseString = responseString+"|"+resultSet.getString("PO_DATE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                
                if(!"".equals(resultSet.getString("PO_AMOUNT"))&&resultSet.getString("PO_AMOUNT")!=null){
                    if(!"".equals(resultSet.getString("PO_AMOUNT").trim())){
                responseString = responseString+"|"+resultSet.getString("PO_AMOUNT");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }
                if(keyType.equalsIgnoreCase("IN")){
             
               * if(!"".equals(resultSet.getString("INVOICE"))&&resultSet.getString("INVOICE")!=null){
                    if(!"".equals(resultSet.getString("INVOICE").trim())){
                responseString = responseString+"|"+resultSet.getString("INVOICE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
               
                 //-------
                  if(!"".equals(resultSet.getString("INVOICE_DATE"))&&resultSet.getString("INVOICE_DATE")!=null){
                    if(!"".equals(resultSet.getString("INVOICE_DATE").trim())){
                responseString = responseString+"|"+resultSet.getString("INVOICE_DATE");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                   
                if(!"".equals(resultSet.getString("INVOICE_TOTAL"))&&resultSet.getString("INVOICE_TOTAL")!=null){
                    if(!"".equals(resultSet.getString("INVOICE_TOTAL").trim())){
                responseString = responseString+"|"+resultSet.getString("INVOICE_TOTAL");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                
                          if(!"".equals(resultSet.getString("SALES_AREA"))&&resultSet.getString("SALES_AREA")!=null){
                    if(!"".equals(resultSet.getString("SALES_AREA").trim())){
                responseString = responseString+"|"+resultSet.getString("SALES_AREA");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }         
                if(keyType.equalsIgnoreCase("PO")){
              if(!"".equals(resultSet.getString("SELLING_SALES_ORG"))&&resultSet.getString("SELLING_SALES_ORG")!=null){
              if(!"".equals(resultSet.getString("SELLING_SALES_ORG").trim())){
                responseString = responseString+"|"+resultSet.getString("SELLING_SALES_ORG");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
               
                if(!"".equals(resultSet.getString("SALES_ORDER"))&&resultSet.getString("SALES_ORDER")!=null){
                    if(!"".equals(resultSet.getString("SALES_ORDER").trim())){
                responseString = responseString+"|"+resultSet.getString("SALES_ORDER");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                
                  //-----------
                   if(!"".equals(resultSet.getString("LINE_COUNT"))&&resultSet.getString("LINE_COUNT")!=null){
                    if(!"".equals(resultSet.getString("LINE_COUNT").trim())){
                responseString = responseString+"|"+resultSet.getString("LINE_COUNT");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                    }
                if(keyType.equalsIgnoreCase("IN")){
                if(!"".equals(resultSet.getString("ITEM_LINE_COUNT"))&&resultSet.getString("ITEM_LINE_COUNT")!=null){
                    if(!"".equals(resultSet.getString("ITEM_LINE_COUNT").trim())){
                responseString = responseString+"|"+resultSet.getString("ITEM_LINE_COUNT");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }
                if(keyType.equalsIgnoreCase("PO")){
                 if(!"".equals(resultSet.getString("RDD"))&&resultSet.getString("RDD")!=null){
                    if(!"".equals(resultSet.getString("RDD").trim())){
                responseString = responseString+"|"+resultSet.getString("RDD");
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }else {
                        responseString = responseString+"|"+"--";
                    }
                }*/
        //}
              //  assignedRoleMap.put(resultSet.getString("ID"),resultSet.getString("Role_NAME"));
                //responseString = resultSet.getString("SAP_USER")+"|"+resultSet.getString("IDOC_NUMBER")+"|"+resultSet.getString("PO_NUMBER")+"|"+resultSet.getString("PO_DATE")+"|"+resultSet.getString("IDOC_STATUS_CODE")+"|"+resultSet.getString("IDOC_STATUS_DESCRIPTION");
          /*  }
            
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(statement != null) {
                    statement.close();
                    statement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return responseString;
     }*/

    //new action added for senderid and reciver id 
    
        public List getSenderIdlist() throws ServiceLocatorException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       String queryString = null;
       connection = ConnectionProvider.getInstance().getConnection();
       List sendeIdMap = new ArrayList();
       //String Transaction_Type=null;
       try {
          // System.out.println("st--->"+st);
           
        
      queryString = "SELECT distinct(TP.ID) FROM TP JOIN FILES ON (TP.ID=FILES.SENDER_ID)";
           //System.out.println("queryString--->"+queryString);
           preparedStatement = connection.prepareStatement(queryString);
           resultSet = preparedStatement.executeQuery();
         
           while(resultSet.next()) {
               if(resultSet.getString("ID")!=null&&!"".equals(resultSet.getString("ID")))
               {
                             sendeIdMap.add(resultSet.getString("ID"));
               }
           }
           
           
       }catch (SQLException sql) {
           throw new ServiceLocatorException(sql);
       }finally{
           try {
               if(resultSet != null) {
                   resultSet.close();
                   resultSet = null;
               }
               if(preparedStatement != null) {
                   preparedStatement.close();
                   preparedStatement = null;
               }
               if(connection != null) {
                   connection.close();
                   connection = null;
               }
           } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
           }
       }
       
       return sendeIdMap;
   }
 
   public List getReciverIdlist() throws ServiceLocatorException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       String queryString = null;
       connection = ConnectionProvider.getInstance().getConnection();
       List receiverIdMap = new ArrayList();
       //String Transaction_Type=null;
       try {
          // System.out.println("st--->"+st);
           
        
      queryString = "SELECT distinct(TP.ID) FROM TP JOIN FILES ON (TP.ID=FILES.RECEIVER_ID)";
           //System.out.println("queryString--->"+queryString);
           preparedStatement = connection.prepareStatement(queryString);
           resultSet = preparedStatement.executeQuery();
         
           while(resultSet.next()) {
               if(resultSet.getString("ID")!=null&&!"".equals(resultSet.getString("ID")))
               {
                             receiverIdMap.add(resultSet.getString("ID"));
               }
           }
           
           
       }catch (SQLException sql) {
           throw new ServiceLocatorException(sql);
       }finally{
           try {
               if(resultSet != null) {
                   resultSet.close();
                   resultSet = null;
               }
               if(preparedStatement != null) {
                   preparedStatement.close();
                   preparedStatement = null;
               }
               if(connection != null) {
                   connection.close();
                   connection = null;
               }
           } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
           }
       }
       
       return receiverIdMap;
   }
   
    public List getSenderNamelist() throws ServiceLocatorException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       String queryString = null;
       connection = ConnectionProvider.getInstance().getConnection();
       List SenderNameMap = new ArrayList();
       //String Transaction_Type=null;
       try {
          // System.out.println("st--->"+st);
           
        
      queryString = "SELECT distinct(TP.NAME) FROM TP JOIN FILES ON (TP.ID=FILES.SENDER_ID)";
           //System.out.println("queryString--->"+queryString);
           preparedStatement = connection.prepareStatement(queryString);
           resultSet = preparedStatement.executeQuery();
         
           while(resultSet.next()) {
               if(resultSet.getString("NAME")!=null&&!"".equals(resultSet.getString("NAME")))
               {
                             SenderNameMap.add(resultSet.getString("NAME"));
               }
           }
           
           
       }catch (SQLException sql) {
           throw new ServiceLocatorException(sql);
       }finally{
           try {
               if(resultSet != null) {
                   resultSet.close();
                   resultSet = null;
               }
               if(preparedStatement != null) {
                   preparedStatement.close();
                   preparedStatement = null;
               }
               if(connection != null) {
                   connection.close();
                   connection = null;
               }
           } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
           }
       }
       
       return SenderNameMap;
   }
    
     public List getReciverNamelist() throws ServiceLocatorException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       String queryString = null;
       connection = ConnectionProvider.getInstance().getConnection();
       List receiverNameMap = new ArrayList();
       //String Transaction_Type=null;
       try {
          // System.out.println("st--->"+st);
           
        
      queryString = "SELECT distinct(TP.NAME) FROM TP JOIN FILES ON (TP.ID=FILES.RECEIVER_ID)";
           //System.out.println("queryString--->"+queryString);
           preparedStatement = connection.prepareStatement(queryString);
           resultSet = preparedStatement.executeQuery();
         
           while(resultSet.next()) {
               if(resultSet.getString("NAME")!=null&&!"".equals(resultSet.getString("NAME")))
               {
                             receiverNameMap.add(resultSet.getString("NAME"));
               }
           }
           
           
       }catch (SQLException sql) {
           throw new ServiceLocatorException(sql);
       }finally{
           try {
               if(resultSet != null) {
                   resultSet.close();
                   resultSet = null;
               }
               if(preparedStatement != null) {
                   preparedStatement.close();
                   preparedStatement = null;
               }
               if(connection != null) {
                   connection.close();
                   connection = null;
               }
           } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
           }
       }
       
       return receiverNameMap;
   }
     
     
   public String getEmail() throws ServiceLocatorException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String queryString = null;
        connection = ConnectionProvider.getInstance().getConnection(); 
        //Map priorityMap = new HashMap();
        String emialid=null;
          String result=null; 
          String responseString = "";
        try {
            
            queryString = "select EMAIL from M_USER LEFT OUTER JOIN M_USER_ROLES on (M_USER.ID=M_USER_ROLES.USER_ID) where ROLE_ID=100 AND ACTIVE='A'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
             // String responseString = "";
                emialid = resultSet.getString("EMAIL");
               responseString+=emialid+"|";
            // responseString = responseString+"|"+resultSet.getString("EMAIL")+"^"+resultSet.getString("cname")+"|";    
                //MailManager mail=new MailManager();
             //  result=MailManager.sendMail(emialid);
               // System.out.println("RESULT========="+result);
              // responseString+=resultSet.getString("EMAIL")+"|"+resultSet.getString("cname")+"|";
                //priorityMap.put(r;esultSet.getString("PRI_ID"), resultSet.getString("PRI_NAME"));
                  
            }
            
            
        }catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }      
        System.out.println("RESULT========="+responseString);
     return responseString;
         }
  
        public Map getUserMap() throws ServiceLocatorException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       String queryString = null;
       connection = ConnectionProvider.getInstance().getConnection();
       Map userMap = new TreeMap();
       
       try {
          // System.out.println("st--->"+st);
            queryString = "select ID,FNME+LNME as cname from M_USER as MU left outer join M_USER_ROLES as MUR on (MU.id=MUR.USER_ID)WHERE MUR.ROLE_ID != 1 AND ACTIVE='A' ";
           preparedStatement = connection.prepareStatement(queryString);
           resultSet = preparedStatement.executeQuery();
           while(resultSet.next()) {
               userMap.put(resultSet.getString("ID"), resultSet.getString("cname"));
           }
           
           
       }catch (SQLException sql) {
           throw new ServiceLocatorException(sql);
       }finally{
           try {
               if(resultSet != null) {
                   resultSet.close();
                   resultSet = null;
               }
               if(preparedStatement != null) {
                   preparedStatement.close();
                   preparedStatement = null;
               }
               if(connection != null) {
                   connection.close();
                   connection = null;
               }
           } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
           }
       }
       
       return userMap;
   }
        
    public int getUserIds(String loginid) throws ServiceLocatorException{
        System.out.println("loginid"+loginid);
        //Map assignedRoleMap =  new TreeMap(); // Key-Description
         PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String queryString = null;
        int Userid = 0;
        connection = ConnectionProvider.getInstance().getConnection();
        try {
            queryString = "select ID from M_user where LOGINID LIKE '%"+loginid+"%'";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Userid = resultSet.getInt("ID");
                //priorityMap.put(resultSet.getString("PRI_ID"), resultSet.getString("PRI_NAME"));
                  System.out.println("Userid====="+Userid);
            }
            
            
        } catch (SQLException sql) {
            throw new ServiceLocatorException(sql);
        }finally{
            try {
                // resultSet Object Checking if it's null then close and set null
                if(resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                
                if(preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                
                if(connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        
            System.out.println(Userid);
      // returning the object.
    } 
        System.out.println(Userid);
        
          return Userid; 
    }
    
    
       public List getNetworkVanList() throws ServiceLocatorException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       String queryString = null;
       connection = ConnectionProvider.getInstance().getConnection();
       List NetworkvanMap = new ArrayList();
       //String Transaction_Type=null;
       try {
          // System.out.println("st--->"+st);
           
        
               queryString = "SELECT distinct(FILES.NETWORK_VAN) FROM FILES where FILES.NETWORK_VAN is not null";
           //System.out.println("queryString--->"+queryString);
           preparedStatement = connection.prepareStatement(queryString);
           resultSet = preparedStatement.executeQuery();
         
           while(resultSet.next()) {
               if(resultSet.getString("NETWORK_VAN")!=null&&!"".equals(resultSet.getString("NETWORK_VAN")))
               {
                             NetworkvanMap.add(resultSet.getString("NETWORK_VAN"));
               }
           }
           
           
       }catch (SQLException sql) {
           throw new ServiceLocatorException(sql);
       }finally{
           try {
               if(resultSet != null) {
                   resultSet.close();
                   resultSet = null;
               }
               if(preparedStatement != null) {
                   preparedStatement.close();
                   preparedStatement = null;
               }
               if(connection != null) {
                   connection.close();
                   connection = null;
               }
           } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
           }
       }
       
       return NetworkvanMap;
   }
       
       
       public List getDocumentTypeListInvoice(int id,int groupId) throws ServiceLocatorException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       String queryString = null;
       connection = ConnectionProvider.getInstance().getConnection();
       List docTypeMap = new ArrayList();
       
       try {
          // System.out.println("st--->"+st);
           
           if(id == 0){
               queryString = "SELECT NAME FROM DOCUMENTTYPES where GROUP_ID="+groupId;
           }else {
               queryString = "SELECT NAME FROM DOCUMENTTYPES where GROUP_ID="+groupId+" AND ID!="+id;
           }
           System.out.println("queryString getDocumentTypeListInvoice--->"+queryString);
           preparedStatement = connection.prepareStatement(queryString);
           resultSet = preparedStatement.executeQuery();
           while(resultSet.next()) {
               docTypeMap.add(resultSet.getString("NAME"));
           }
           
           
       }catch (SQLException sql) {
           throw new ServiceLocatorException(sql);
       }finally{
           try {
               if(resultSet != null) {
                   resultSet.close();
                   resultSet = null;
               }
               if(preparedStatement != null) {
                   preparedStatement.close();
                   preparedStatement = null;
               }
               if(connection != null) {
                   connection.close();
                   connection = null;
               }
           } catch (SQLException ex) {
               throw new ServiceLocatorException(ex);
           }
       }
       
       return docTypeMap;
   }
       
}
        


