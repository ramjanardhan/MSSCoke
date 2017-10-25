/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.documentVisibility;

import java.sql.Timestamp;

/**
 *
 * @author miracle
 */
public class DocumentVisibilityBean {
    private int id;
private String file_origin;
private String file_type;
private String interchange_ControlNo;
private String transaction_type;
private String direction;
private Timestamp date_time_rec;
private String status;
private String pname;
private String poNumber;
private String reProcessStatus;
private String ackStatus;
//declaring strings senderId,senderName,receiverId,gs_number to use to pass data to UI : Added on 05/01/2014
private String senderId;
private String senderName;
private String receiverId;
private String functional_ControlNo;
private String instanceId;

private String message_ControlNo;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the file_origin
     */
    public String getFile_origin() {
        return file_origin;
    }

    /**
     * @param file_origin the file_origin to set
     */
    public void setFile_origin(String file_origin) {
        this.file_origin = file_origin;
    }

    /**
     * @return the file_type
     */
    public String getFile_type() {
        return file_type;
    }

    /**
     * @param file_type the file_type to set
     */
    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    /**
     * @return the interchange_ControlNo
     */
    public String getInterchange_ControlNo() {
        return interchange_ControlNo;
    }

    /**
     * @param interchange_ControlNo the interchange_ControlNo to set
     */
    public void setInterchange_ControlNo(String interchange_ControlNo) {
        this.interchange_ControlNo = interchange_ControlNo;
    }

    /**
     * @return the transaction_type
     */
    public String getTransaction_type() {
        return transaction_type;
    }

    /**
     * @param transaction_type the transaction_type to set
     */
    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
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
     * @return the date_time_rec
     */
    public Timestamp getDate_time_rec() {
        return date_time_rec;
    }

    /**
     * @param date_time_rec the date_time_rec to set
     */
    public void setDate_time_rec(Timestamp date_time_rec) {
        this.date_time_rec = date_time_rec;
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
     * @return the pname
     */
    public String getPname() {
        return pname;
    }

    /**
     * @param pname the pname to set
     */
    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * @return the poNumber
     */
    public String getPoNumber() {
        return poNumber;
    }

    /**
     * @param poNumber the poNumber to set
     */
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    /**
     * @return the reProcessStatus
     */
    public String getReProcessStatus() {
        return reProcessStatus;
    }

    /**
     * @param reProcessStatus the reProcessStatus to set
     */
    public void setReProcessStatus(String reProcessStatus) {
        this.reProcessStatus = reProcessStatus;
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
     * @return the senderId
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * @param senderId the senderId to set
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    /**
     * @return the senderName
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * @param senderName the senderName to set
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * @return the receiverId
     */
    public String getReceiverId() {
        return receiverId;
    }

    /**
     * @param receiverId the receiverId to set
     */
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * @return the functional_ControlNo
     */
    public String getFunctional_ControlNo() {
        return functional_ControlNo;
    }

    /**
     * @param functional_ControlNo the functional_ControlNo to set
     */
    public void setFunctional_ControlNo(String functional_ControlNo) {
        this.functional_ControlNo = functional_ControlNo;
    }

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
     * @return the message_ControlNo
     */
    public String getMessage_ControlNo() {
        return message_ControlNo;
    }

    /**
     * @param message_ControlNo the message_ControlNo to set
     */
    public void setMessage_ControlNo(String message_ControlNo) {
        this.message_ControlNo = message_ControlNo;
    }
}
