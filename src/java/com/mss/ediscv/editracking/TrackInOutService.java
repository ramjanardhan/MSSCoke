/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.editracking;

import com.mss.ediscv.util.ServiceLocatorException;
import java.util.ArrayList;

/**
 *
 * @author miracle
 */
public interface TrackInOutService {
  public ArrayList<TrackInOutBean> getDocumentList(TrackInOutAction trackInOutAction)throws ServiceLocatorException; 
  public ArrayList<TrackInOutBean> getSummaryDetails(TrackInOutAction trackInOutAction)throws ServiceLocatorException; 
  public ArrayList<TrackInOutBean> getInquiryDetails(TrackInOutAction trackInOutAction)throws ServiceLocatorException; 
}
