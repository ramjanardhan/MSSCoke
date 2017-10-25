/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.logisticreports;

//import com.mss.ediscv.reports.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author miracle
 */
public class LogisticReportsBean implements Serializable{
     
  private String file_id;
private String file_origin;
private String file_type;
private String isa_number;
private String transaction_type;
private String direction;
private Timestamp date_time_rec;
private String status;
private String pname;
private String shipmentNumber;
private String reProcessStatus;
private String ackStatus;
private String file_name;
private String sce_key_val;
private String orderNum;
private String inv_Num;
private String transactionStatus;

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

    public String getSce_key_val() {
        return sce_key_val;
    }

    public void setSce_key_val(String sce_key_val) {
        this.sce_key_val = sce_key_val;
    }

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    public String getInv_Num() {
        return inv_Num;
    }

    public void setInv_Num(String inv_Num) {
        this.inv_Num = inv_Num;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
    
    
}
