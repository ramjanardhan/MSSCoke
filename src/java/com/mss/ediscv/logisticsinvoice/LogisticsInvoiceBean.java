/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.logisticsinvoice;

import java.sql.Timestamp;

/**
 *
 * @author miracle
 */
public class LogisticsInvoiceBean {
private String instanceId;
private String partner;
private String invoiceNumber;
private String poNumber;
private String itemQty;
private String invAmount;
private String invDate;
private String status;
private String ackStatus;
private int id;
private String errormsg;
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

    private int idFiles;

    public int getIdFiles() {
        return idFiles;
    }

    public void setIdFiles(int idFiles) {
        this.idFiles = idFiles;
    }
private String shipmentId;

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
     * @return the invoiceNumber
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * @param invoiceNumber the invoiceNumber to set
     */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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
     * @return the itemQty
     */
    public String getItemQty() {
        return itemQty;
    }

    /**
     * @param itemQty the itemQty to set
     */
    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    /**
     * @return the invAmount
     */
    public String getInvAmount() {
        return invAmount;
    }

    /**
     * @param invAmount the invAmount to set
     */
    public void setInvAmount(String invAmount) {
        this.invAmount = invAmount;
    }

    /**
     * @return the invDate
     */
    public String getInvDate() {
        return invDate;
    }

    /**
     * @param invDate the invDate to set
     */
    public void setInvDate(String invDate) {
        this.invDate = invDate;
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

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public Timestamp getDate_time_rec() {
        return date_time_rec;
    }

    public void setDate_time_rec(Timestamp date_time_rec) {
        this.date_time_rec = date_time_rec;
    }
   
}
