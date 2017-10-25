				/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.download;

import com.mss.ediscv.util.DateUtility;
import java.io.InputStream;
import java.io.OutputStream;
import com.mss.ediscv.util.ServiceLocator;
import com.mss.ediscv.util.ServiceLocatorException;
import com.opensymphony.xwork2.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author miracle
 */
public class DownloadReportAction implements
        Action,ServletRequestAware,ServletResponseAware{
    
    
    private int scheduleId;
    private String startDate;
    private String reportattachment;
    private String fileName;
    
    private String contentDisposition="FileName=inline";
    public InputStream inputStream;
    public OutputStream outputStream;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getReportattachment() {
        return reportattachment;
    }

    public void setReportattachment(String reportattachment) {
        this.reportattachment = reportattachment;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
      /**
     * Creating a reference variable for HttpServletRequest.
     */
    private HttpServletRequest httpServletRequest;
    /**
     * Creating a reference variable for HttpServletResponse.
     */
    private HttpServletResponse httpServletResponse;

      public String reportDownloads() {
          System.out.println("reportDownloads method");

 try {
            this.setScheduleId(Integer.parseInt(httpServletRequest.getParameter("scheduleId").toString()));
            
            startDate =httpServletRequest.getParameter("startDate").toString();
        //startDate=startDate.substring(0, 10);
            System.out.println("startDate---->"+startDate);
            this.setReportattachment(ServiceLocator.getGridDownloadService().getReportattachment(this.getScheduleId(),this.getStartDate()));
            System.out.println("after set report");
            //fileName = this.getAttachmentLocation()
          //  .substring(this.getAttachmentLocation().lastIndexOf(Properties.getProperty("OS.Compatabliliy.Download"))+1,getAttachmentLocation().length());
            httpServletResponse.setContentType("application/force-download");
            
            File file = new File(getReportattachment());
            System.out.println("filesss report");
            fileName = file.getName();
            System.out.println("file---"+file);
            inputStream = new FileInputStream(file);
            outputStream =  httpServletResponse.getOutputStream();
            httpServletResponse.setHeader("Content-Disposition","attachment; filename=\"" + fileName +"\"");
            int noOfBytesRead = 0;
            byte[] byteArray = null;
            while(true) {
                byteArray = new byte[1024];
                noOfBytesRead = inputStream.read(byteArray);
                if(noOfBytesRead==-1) break;
                outputStream.write(byteArray, 0, noOfBytesRead);
            }
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }catch (ServiceLocatorException ex) {
            ex.printStackTrace();
        }finally {
            try {
                if(inputStream!=null)
                inputStream.close();
                if(outputStream!=null)
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
 return null;

    }
    
    
    
    @Override
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     *
     * This method is used to set the Servlet Response
     * @param httpServletResponse
     */
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    
}