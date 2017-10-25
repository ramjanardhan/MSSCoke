/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.logisticeditracking;

import com.mss.ediscv.editracking.*;
import com.mss.ediscv.util.ServiceLocatorException;
import java.util.ArrayList;

/**
 *
 * @author miracle
 */
public interface LogisticTrackInOutService {
  public ArrayList<LogisticTrackInOutBean> getDocumentList(LogisticTrackInOutAction logistictrackInOutAction)throws ServiceLocatorException; 
  public ArrayList<LogisticTrackInOutBean> getSummaryDetails(LogisticTrackInOutAction logistictrackInOutAction)throws ServiceLocatorException; 
  public ArrayList<LogisticTrackInOutBean> getInquiryDetails(LogisticTrackInOutAction logistictrackInOutAction)throws ServiceLocatorException; 
}
