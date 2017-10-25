/*******************************************************************************
 *
 * Project : Electronic Data Interchange Portal v1.0
 *
 * Package : com.walgreens.edi.search
 *
 * Date    : mar 06, 2013 5:22:19 pm
 *
 * Author  : Ajay Tummapala <atummapala@miraclesoft.com>
 *
 * File    : Download.java
 *
 * 
 * *****************************************************************************
 */



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.download;

import com.opensymphony.xwork2.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author Ajay Tummapala <atummapala@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class DownloadAction implements
        Action,ServletRequestAware,ServletResponseAware{
    
    private String inputPath;
    // private String URL="/images/flower.GIF";
    private String contentDisposition="FileName=inline";
    public InputStream inputStream;
    public OutputStream outputStream;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private int id;
    private String attachmentLocation;
    private String fileName;
    private String locationAvailable;
    
    /** Creates a new instance of DownloadAction */
    public DownloadAction() {
    }
    
    @Override
    public String execute() throws Exception {
        return null;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        
        String responseString="";
        try {
            
            this.setAttachmentLocation(httpServletRequest.getParameter("locationAvailable").trim());
            
           // fileName = this.getAttachmentLocation().substring(this.getAttachmentLocation().lastIndexOf("\\")+1,getAttachmentLocation().length());
           // fileName = this.getAttachmentLocation().substring(this.getAttachmentLocation().lastIndexOf(locationAvailable)+1,getAttachmentLocation().length());
          //  StringTokenizer st = new StringTokenizer(fileName);
            
            //System.out.println("pathname--->"+st.nextToken());
           // fileName = st.nextToken();
            //System.out.println("fileName----->"+fileName);
            
        //String date = st.nextToken();
        //String time = st.nextToken();
            
            httpServletResponse.setContentType("application/force-download");
        File file = new File(getAttachmentLocation());
        System.out.println("file-------------------"+file);
           //File file = new File("//172.17.0.131"+getAttachmentLocation());
            if(file.exists()){
                fileName = file.getName();
                System.out.println("filename"+fileName);
            inputStream = new FileInputStream(file);
            outputStream =  httpServletResponse.getOutputStream();
            httpServletResponse.setHeader("Content-Disposition","attachment;filename=\"" + fileName +"\"");
            int noOfBytesRead = 0;
            byte[] byteArray = null;
            while(true) {
                byteArray = new byte[1024];
                noOfBytesRead = inputStream.read(byteArray);
                if(noOfBytesRead==0) break;
                outputStream.write(byteArray, 0, noOfBytesRead);
            }
            responseString = "downLoaded!!";
                httpServletResponse.setContentType("text");
                httpServletResponse.getWriter().write(responseString);
                
            }else{
                throw new FileNotFoundException("File not found");
            }   
                
             
            
        }catch (FileNotFoundException ex) {
            try {
                httpServletResponse.sendRedirect("../general/exception.action?exceptionMessage='No File found'");
            } catch (IOException ex1) {
                Logger.getLogger(DownloadAction.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }/*catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        }*/finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getAttachmentLocation() {
        return attachmentLocation;
    }
    
    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
    }
    
    public String getLocationAvailable() {
        return locationAvailable;
    }
    
    public void setLocationAvailable(String locationAvailable) {
        this.locationAvailable = locationAvailable;
    }
    
}
