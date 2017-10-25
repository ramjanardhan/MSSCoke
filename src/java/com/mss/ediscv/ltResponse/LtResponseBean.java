/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.ltResponse;

import java.sql.Timestamp;

/**
 *
 * @author miracle
 */
public class LtResponseBean {
     private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String fileId;
    private String fileOrgin;
    private String fileType;
    private String isaNum;
    private String transType;
    private String direction;
    private String status;
    private String partnerName;
    private String poNum;
    private String reprocess;
    private String ackStatus;
    private String refId;
    private String shipmentId;
    private String responseStatus;
    private Timestamp date_time_rec;
    private String tmwSenderid;
private String tmwReceivererid;

    public String getTmwReceivererid() {
        return tmwReceivererid;
    }

    public void setTmwReceivererid(String tmwReceivererid) {
        this.tmwReceivererid = tmwReceivererid;
    }

    public String getTmwSenderid() {
        return tmwSenderid;
    }

    public void setTmwSenderid(String tmwSenderid) {
        this.tmwSenderid = tmwSenderid;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    

    /**
     * @return the fileId
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * @param fileId the fileId to set
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * @return the fileOrgin
     */
    public String getFileOrgin() {
        return fileOrgin;
    }

    /**
     * @param fileOrgin the fileOrgin to set
     */
    public void setFileOrgin(String fileOrgin) {
        this.fileOrgin = fileOrgin;
    }

    /**
     * @return the fileType
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * @param fileType the fileType to set
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * @return the isaNum
     */
    public String getIsaNum() {
        return isaNum;
    }

    /**
     * @param isaNum the isaNum to set
     */
    public void setIsaNum(String isaNum) {
        this.isaNum = isaNum;
    }

    /**
     * @return the transType
     */
    public String getTransType() {
        return transType;
    }

    /**
     * @param transType the transType to set
     */
    public void setTransType(String transType) {
        this.transType = transType;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the partnerName
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * @param partnerName the partnerName to set
     */
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    /**
     * @return the poNum
     */
    public String getPoNum() {
        return poNum;
    }

    /**
     * @param poNum the poNum to set
     */
    public void setPoNum(String poNum) {
        this.poNum = poNum;
    }

    /**
     * @return the reprocess
     */
    public String getReprocess() {
        return reprocess;
    }

    /**
     * @param reprocess the reprocess to set
     */
    public void setReprocess(String reprocess) {
        this.reprocess = reprocess;
    }

    /**
     * @return the ackStatus
     */
    public String getAckStatus() {
        return ackStatus;
    }

    /**
     * @param ackStatus the ackStatus to set
     */
    public void setAckStatus(String ackStatus) {
        this.ackStatus = ackStatus;
    }

    /**
     * @return the refId
     */
    public String getRefId() {
        return refId;
    }

    /**
     * @param refId the refId to set
     */
    public void setRefId(String refId) {
        this.refId = refId;
    }
    
    
    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Timestamp getDate_time_rec() {
        return date_time_rec;
    }

    public void setDate_time_rec(Timestamp date_time_rec) {
        this.date_time_rec = date_time_rec;
    }
    
}
