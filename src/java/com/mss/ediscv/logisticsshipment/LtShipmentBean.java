/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.logisticsshipment;

import java.sql.Timestamp;



/**
 *
 * @author miracle
 */
public class LtShipmentBean {
    private String instanceId;
    private String asnNum;
    private String direction;
    private Timestamp dateTime;
    private String partner;
    private String status;
    private String ackStatus;
    private String poNum;
    private String stopNum;
    private String ordernum;
    
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
    
    private int fileId;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
    
   

    public String getStopNum() {
        return stopNum;
    }

    public void setStopNum(String stopNum) {
        this.stopNum = stopNum;
    }

   

   
    private String carrierStatus;

    /**
     * @return the instanceId
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * @param instanceId the instanceId to set
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * @return the asnNum
     */
    public String getAsnNum() {
        return asnNum;
    }

    /**
     * @param asnNum the asnNum to set
     */
    public void setAsnNum(String asnNum) {
        this.asnNum = asnNum;
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
     * @return the dateTime
     */
    public Timestamp getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the partner
     */
    public String getPartner() {
        return partner;
    }

    /**
     * @param partner the partner to set
     */
    public void setPartner(String partner) {
        this.partner = partner;
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
    
     public String getCarrierStatus() {
        return carrierStatus;
    }

    public void setCarrierStatus(String carrierStatus) {
        this.carrierStatus = carrierStatus;
    }
    
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int id;

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }
    
}
