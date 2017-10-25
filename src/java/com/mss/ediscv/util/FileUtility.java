/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import java.util.StringTokenizer;

/**
 *
 * @author miracle
 */
public class FileUtility {
    
    private static FileUtility _instance;
    
    public static FileUtility getInstance() {

		if (_instance == null) {
			_instance = new FileUtility();
		}
		return _instance;
	}
    
public String copyFiles(ArrayList<File> selected, File destinationDirectory)
{
    
      String fileCopyStatus = "Error";
      
    for(File file : selected){
       try {
          if (!destinationDirectory.exists())
            destinationDirectory.mkdirs();
          FileUtils.copyFileToDirectory(file,destinationDirectory);
          
            fileCopyStatus = "Success";

        } catch (IOException ex) {
       //   Logger.getLogger(CommonMethod.class.getName()).log(Level.SEVERE, null, ex);
                   System.err.println("File Coping error--->"+ex.getMessage());
                   
        }   
    }
    
    return fileCopyStatus;
}


 public String copyMapFiles(Map files)
{
    
      String fileCopyStatus = "Error";
      
       
    Iterator it = files.entrySet().iterator();
     while (it.hasNext()) {
       try {
           
            Map.Entry pairs = (Map.Entry)it.next();
           File destinationDirectory = (File) pairs.getValue();
           File srcFile = (File) pairs.getKey();
          if (!destinationDirectory.exists())
            destinationDirectory.mkdirs();
          FileUtils.copyFileToDirectory(srcFile,destinationDirectory);
          
         
          
            fileCopyStatus = "Success";

        } catch (IOException ex) {
       //   Logger.getLogger(CommonMethod.class.getName()).log(Level.SEVERE, null, ex);
                   System.err.println("File Coping error--->"+ex.getMessage());
                   
        }   
    }
    
    return fileCopyStatus;
}

  public String copyPreMapFiles(Map files)
{
    
      String fileCopyStatus = "Error";
      
       
    Iterator it = files.entrySet().iterator();
     while (it.hasNext()) {
       try {
           
            Map.Entry pairs = (Map.Entry)it.next();
           //File destinationDirectory = (File) pairs.getValue();
            String destinationDetails = (String)pairs.getValue();
            //System.out.println("destinationDetails->"+destinationDetails);
            
           // StringTokenizer st = new StringTokenizer();
            
            String path = destinationDetails.substring(0,destinationDetails.lastIndexOf("\\")+1);
            
            String fullToken = destinationDetails.substring(destinationDetails.lastIndexOf("\\")+1,destinationDetails.length() );
            System.out.println("full token "+path+ " -- "+fullToken);
            
            StringTokenizer st = new StringTokenizer(fullToken,"|");
            String poNum = st.nextToken();
            String fileId = st.nextToken();
            String senderId= st.nextToken();
            String recv = st.nextToken();
            String tranc = st.nextToken();
           File destinationDirectory = new File(path);
           File srcFile = (File) pairs.getKey();
           // System.out.println("Src name->"+srcFile.getAbsolutePath());
          if (!destinationDirectory.exists())
            destinationDirectory.mkdirs();
          
          FileUtils.copyFileToDirectory(srcFile,destinationDirectory);
          String newFile = destinationDirectory+"\\"+srcFile.getName();
          File renameFile = new File(newFile);
          int mid = renameFile.getName().lastIndexOf(".");
          String fname=renameFile.getName().substring(0,mid);
          String ext=renameFile.getName().substring(mid+1,renameFile.getName().length()); 
          //String newName = fname+"_"+poNum+"_"+fileId+"."+ext;
          String newName = poNum.trim()+"_"+senderId.trim()+"_"+recv.trim()+"_"+tranc.trim()+"_Reprocess."+ext;
         // System.out.println("FilePath-->"+newName);
          renameFile.renameTo(new File(destinationDirectory+"\\"+newName));
         // System.out.println("AFter rename-->"+destinationDirectory+"\\"+newName);
            fileCopyStatus = "Success";
            
            if(fileCopyStatus.equals("Success")){
              //processBatchFile();
              fileCopyStatus = "Success";
            }else{
                fileCopyStatus = "error";
            }

        } catch (IOException ex) {
       //   Logger.getLogger(CommonMethod.class.getName()).log(Level.SEVERE, null, ex);
                   System.err.println("File Coping error--->"+ex.getMessage());
                   
        }   
    }
    
    return fileCopyStatus;
}



 
 public String copyPostMapFiles(Map files)
{
   // System.out.println("hiiii");
      String fileCopyStatus = "Error";
       
    Iterator it = files.entrySet().iterator();
     while (it.hasNext()) {
       try {
           
            Map.Entry pairs = (Map.Entry)it.next();
           File destinationDirectory = (File) pairs.getValue();
         System.out.println("Src-->"+pairs.getKey());
           File srcFile = (File) pairs.getKey();
          if (!destinationDirectory.exists())
            destinationDirectory.mkdirs();
          System.out.println("dest Dir-->"+destinationDirectory.getAbsolutePath());
          System.out.println("Src -->"+srcFile.getAbsolutePath());
          FileUtils.copyFileToDirectory(srcFile,destinationDirectory);
         
          
            fileCopyStatus = "Success";
            
            if(fileCopyStatus.equals("Success")){
                //postprocessBatchFile();
                fileCopyStatus = "Success";  
            }else{
                fileCopyStatus = "error";
            }

        } catch (IOException ex) {
       //   Logger.getLogger(CommonMethod.class.getName()).log(Level.SEVERE, null, ex);
                   System.err.println("File Coping error--->"+ex.getMessage());
                   
        }   
    }
    
    return fileCopyStatus;
}
/*
  * Method for LoadTendering Retransmit Process
  * Description : To copy map files from one location to another.
  * 
  */
 public String loadTenderCopyPostMapFiles(Map files)
{
    
      String fileCopyStatus = "Error";
       
    Iterator it = files.entrySet().iterator();
     while (it.hasNext()) {
       try {
           
            Map.Entry pairs = (Map.Entry)it.next();
           File destinationDirectory = (File) pairs.getValue();
         
           File srcFile = (File) pairs.getKey();
          if (!destinationDirectory.exists())
            destinationDirectory.mkdirs();
          FileUtils.copyFileToDirectory(srcFile,destinationDirectory);
         
          
            fileCopyStatus = "Success";
            
            if(fileCopyStatus.equals("Success")){
                fileCopyStatus = "Success";  
            }else{
                fileCopyStatus = "error";
            }

        } catch (IOException ex) {
       //   Logger.getLogger(CommonMethod.class.getName()).log(Level.SEVERE, null, ex);
                   System.err.println("File Coping error--->"+ex.getMessage());
                   
        }   
    }
    
    return fileCopyStatus;
}

 
 
public String loadTenderCopyPreMapFiles(Map files)
{
    
      String fileCopyStatus = "Error";
      
       
    Iterator it = files.entrySet().iterator();
     while (it.hasNext()) {
       try {
           
            Map.Entry pairs = (Map.Entry)it.next();
         
            File destinationDirectory = (File) pairs.getValue();
         
           File srcFile = (File) pairs.getKey();
          
          if (!destinationDirectory.exists())
            destinationDirectory.mkdirs();
          
          FileUtils.copyFileToDirectory(srcFile,destinationDirectory);
          String newFile = destinationDirectory+"\\"+srcFile.getName();
          File renameFile = new File(newFile);
          int mid = renameFile.getName().lastIndexOf(".");
          String fname=renameFile.getName().substring(0,mid);
          String ext=renameFile.getName().substring(mid+1,renameFile.getName().length()); 
          //String newName = fname+"_"+poNum+"_"+fileId+"."+ext;
          String newName = fname+"_Reprocess."+ext;
         // System.out.println("FilePath-->"+newName);
          renameFile.renameTo(new File(destinationDirectory+"\\"+newName));
          //System.out.println("AFter rename-->"+destinationDirectory+"\\"+newName);
            fileCopyStatus = "Success";
            
            if(fileCopyStatus.equals("Success")){
              loadPreprocessBatchFile();
              fileCopyStatus = "Success";
            }else{
                fileCopyStatus = "error";
            }

        } catch (IOException ex) {
       //   Logger.getLogger(CommonMethod.class.getName()).log(Level.SEVERE, null, ex);
                   System.err.println("File Coping error--->"+ex.getMessage());
                   
        }   
    }
    
    return fileCopyStatus;
} 
 /**
  * 
  * DESC : bat file Process
  *  
  * 
  */
   public void processBatchFile(){
       //String response = "";
      // String filePath = "C:\\SI5.2\\Resubmit\\ReSubmit.bat";
       
       String resubmitFilePath = Properties.getProperty("RESUBMT_PATH");
       System.err.println("resubmitFilePath-->"+resubmitFilePath);
       
       try
        {
      //  System.out.println("Start Running the batch file");
        String[] array = { "cmd", "/C", "start", "/min", resubmitFilePath};
        Runtime.getRuntime().exec(array);
       // System.out.println("Completed");
        }
        catch (Exception e)
        {
        e.printStackTrace();
        }

   }
 
  public void postprocessBatchFile(){
       //String response = "";
      // String filePath = "C:\\SI5.2\\Resubmit\\Retransmitted.bat";
          String retransmitFilePath = Properties.getProperty("RETRANSMIT_PATH");
       System.err.println("retransmitFilePath-->"+retransmitFilePath);
       try
        {
      //  System.out.println("Start Running the batch file");
        String[] array = { "cmd", "/C", "start", "/min", retransmitFilePath};
        Runtime.getRuntime().exec(array);
       // System.out.println("Completed");
        }
        catch (Exception e)
        {
        e.printStackTrace();
        }

   }
  
  /**
   * 
   *  Load tendering ReProcess batch file
   */
     public void loadPreprocessBatchFile(){
       //String response = "";
       String filePath = "C:\\SI5.2\\Resubmit\\L_ReSubmit.bat";
       try
        {
      // System.out.println("Start Running the batch file");
        String[] array = { "cmd", "/C", "start", "/min", filePath};
        Runtime.getRuntime().exec(array);
        //System.out.println("Completed");
        }
        catch (Exception e)
        {
        e.printStackTrace();
        }

   }
  
  
}
