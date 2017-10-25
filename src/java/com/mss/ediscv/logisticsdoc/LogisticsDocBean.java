/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.logisticsdoc;

import java.sql.Timestamp;
import java.util.Comparator;

/**
 *
 * @author miracle
 */
public class LogisticsDocBean implements Comparator {
    
private String file_id;
private String file_origin;
private String file_type;
private String isa_number;
private String transaction_type;
private String direction;
private Timestamp date_time_rec;
private String status;
private String pname;
private String poNumber;
private String reProcessStatus;
private String ackStatus;
private String file_name;
private int id;
private String shipmentId;
private String transactionPurpose;
private String transactionStatus;
private String ordernum;

private String tmwSenderid;
private String tmwReceivererid;

private String shipmentNum;  




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

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionPurpose() {
        return transactionPurpose;
    }

    public void setTransactionPurpose(String transactionPurpose) {
        this.transactionPurpose = transactionPurpose;
    }

     /**
     * @return the file_name
     */
    public String getFile_name() {
        return file_name;
    }
    /**
     * @param file_id the file_name to set
     */
    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    /**
     * @return the file_id
     */
    public String getFile_id() {
        return file_id;
    }

    /**
     * @param file_id the file_id to set
     */
    public void setFile_id(String file_id) {
        this.file_id = file_id;
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
     * @return the isa_number
     */
    public String getIsa_number() {
        return isa_number;
    }

    /**
     * @param isa_number the isa_number to set
     */
    public void setIsa_number(String isa_number) {
        this.isa_number = isa_number;
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
           
     public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getShipmentNum() {
        return shipmentNum;
    }

    public void setShipmentNum(String shipmentNum) {
        this.shipmentNum = shipmentNum;
    }

    @Override
    public int compare(Object o1, Object o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
