/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.util;

/**
 *
 * @author miracle
 */
public class AuthorizationManager {
    public static AuthorizationManager _instance;
    
    /** Creates a new instance of AuthorizationManager */
    private AuthorizationManager() {
    }
    
    public static AuthorizationManager getInstance(){
        if(_instance == null){
            _instance = new AuthorizationManager();
        }
        return _instance;
    }
    
    public boolean isAuthorizedUser(String accessKey, int roleId){
        boolean isAuthorized = false;
       
        try{
            
            int noOfRoles = Integer.parseInt(SecurityProperties.getProperty("TOTAL_ROLES"));
            String authorizedRoleIds = SecurityProperties.getProperty(accessKey);
            String authorizedRoleIdsArray[] = new String[noOfRoles];
            authorizedRoleIdsArray = authorizedRoleIds.split(",");
            for(int counter=0;counter < authorizedRoleIdsArray.length;counter++){
               
                if(roleId == Integer.parseInt(authorizedRoleIdsArray[counter])) { 
                    isAuthorized = true;
                
                
                }
            }
        }catch(Exception slex){
            System.out.println("In catch block-->"+slex.getMessage());
        }
        
        return isAuthorized;
    }
    
      public boolean isAuthorizedReceiver(String reciverIds,int useriD){
        boolean isAuthorized = false;
       
        try{
            
            //int noOfRoles = Integer.parseInt(SecurityProperties.getProperty("TOTAL_ROLES"));
            //String authorizedRoleIds = SecurityProperties.getProperty(Emailids);
          //  String authorizedRoleIdsArray[] = new String[Emailids];
            String authorizedRoleIdsArray[] = reciverIds.split(",");
            for(int counter=0;counter < authorizedRoleIdsArray.length;counter++){
               
                if(useriD == Integer.parseInt(authorizedRoleIdsArray[counter].trim())) { 
                    isAuthorized = true;
                
                
                }
            }
        }catch(Exception slex){
            System.out.println("In catch block-->"+slex.getMessage());
        }
        
        return isAuthorized;
    }  
    
}
