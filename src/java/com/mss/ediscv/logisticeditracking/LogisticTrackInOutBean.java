/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.logisticeditracking;
import com.mss.ediscv.editracking.*;
import java.io.Serializable;

import java.sql.Timestamp;
import java.util.ArrayList;
/**
 *
 * @author miracle
 */
public class LogisticTrackInOutBean implements Serializable{
    
    
    private ArrayList inboundList=new ArrayList();
    private ArrayList outboundList=new ArrayList();
    private ArrayList documentTypeList=new ArrayList();
    private ArrayList dateMonth=new ArrayList();
    private ArrayList dateMonthdocType=new ArrayList();
    private String transaction_type;
    private String pname;
    private int inbound;
    private int outbound;
    private int total;
    private double filesize;
    private double filesize1;
    private double filesizeTotal; 
    private String direction;
    private Timestamp date_time_rec;
    private String ackStatus;

    public String getAckStatus() {
        return ackStatus;
    }

    public void setAckStatus(String ackStatus) {
        this.ackStatus = ackStatus;
    }

    public Timestamp getDate_time_rec() {
        return date_time_rec;
    }

    public void setDate_time_rec(Timestamp date_time_rec) {
        this.date_time_rec = date_time_rec;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getInbound() {
        return inbound;
    }

    public void setInbound(int inbound) {
        this.inbound = inbound;
    }

    public int getOutbound() {
        return outbound;
    }

    public void setOutbound(int outbound) {
        this.outbound = outbound;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public ArrayList getInboundList() {
        return inboundList;
    }

    public void setInboundList(ArrayList inboundList) {
        this.inboundList = inboundList;
    }

    public ArrayList getOutboundList() {
        return outboundList;
    }

    public void setOutboundList(ArrayList outboundList) {
        this.outboundList = outboundList;
    }

    public ArrayList getDocumentTypeList() {
        return documentTypeList;
    }

    public void setDocumentTypeList(ArrayList documentTypeList) {
        this.documentTypeList = documentTypeList;
    }

    public ArrayList getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(ArrayList dateMonth) {
        this.dateMonth = dateMonth;
    }

    public ArrayList getDateMonthdocType() {
        return dateMonthdocType;
    }

    public void setDateMonthdocType(ArrayList dateMonthdocType) {
        this.dateMonthdocType = dateMonthdocType;
    }

    public double getFilesize() {
        return filesize;
    }

    public void setFilesize(double filesize) {
        this.filesize = filesize;
    }

    public double getFilesize1() {
        return filesize1;
    }

    public void setFilesize1(double filesize1) {
        this.filesize1 = filesize1;
    }

    public double getFilesizeTotal() {
        return filesizeTotal;
    }

    public void setFilesizeTotal(double filesizeTotal) {
        this.filesizeTotal = filesizeTotal;
    }

  

 

  

 
  
    
    
}
