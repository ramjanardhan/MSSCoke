/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.griddownload;

import com.mss.ediscv.util.ServiceLocatorException;

/**
 *
 * @author miracle
 */
public interface GridDownloadService {
     public String getReportattachment(int scheduleId,String startDate) throws ServiceLocatorException;
}
