/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.download;
import com.mss.ediscv.util.ServiceLocatorException;

/**
 *
 * @author miracle
 */
public interface DownloadReportActionService {
    
    public String getReportattachment(int scheduleId,String startDate) throws ServiceLocatorException;
    
}