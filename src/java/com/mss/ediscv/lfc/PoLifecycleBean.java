 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.lfc;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author miracle
 */
public class PoLifecycleBean {
            private String fileId;
            private String fileType; 
            private String tranType; 
            private String direction;
            private Timestamp datetimeRec; 
            private String stCtrlNum; 
            private String gsCtrlNum;
            private String senderId; 
            private String recId; 
            private String status;
            private String sapIdocNum;
            private String isaCtrlNum;
            private String isaNum;
            private String poNumber;
            private String asnNumber;
            private String invNumber;
            private String res;

            private String PreFile;
            private String PostTranFile;
            private String OrgFile;
            private String AckFile;
            
            
            private String podate;
            private String poValue;
            private String poStatus;
            private String soNumber;
            private String iteamQty;
            
            private String bolNumber;
            private String isaDate;
            private String isaTime;
            private String invAmt;
            private String chequeNum;
            private String ackStatus;
            
            private String senName;
            private String recName;
            
            private String reProcessStatus;
            
          
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
     * @return the TtranType
     */
    public String getTranType() {
        return tranType;
    }

    /**
     * @param TtranType the TtranType to set
     */
    public void setTranType(String tranType) {
        this.tranType = tranType;
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
     * @return the stCtrlNum
     */
    public String getStCtrlNum() {
        return stCtrlNum;
    }

    /**
     * @param stCtrlNum the stCtrlNum to set
     */
    public void setStCtrlNum(String stCtrlNum) {
        this.stCtrlNum = stCtrlNum;
    }

    /**
     * @return the gsCtrlNum
     */
    public String getGsCtrlNum() {
        return gsCtrlNum;
    }

    /**
     * @param gsCtrlNum the gsCtrlNum to set
     */
    public void setGsCtrlNum(String gsCtrlNum) {
        this.gsCtrlNum = gsCtrlNum;
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
     * @return the recId
     */
    public String getRecId() {
        return recId;
    }

    /**
     * @param recId the recId to set
     */
    public void setRecId(String recId) {
        this.recId = recId;
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
     * @return the sapIdocNum
     */
    public String getSapIdocNum() {
        return sapIdocNum;
    }

    /**
     * @param sapIdocNum the sapIdocNum to set
     */
    public void setSapIdocNum(String sapIdocNum) {
        this.sapIdocNum = sapIdocNum;
    }

    /**
     * @return the isaCtrlNum
     */
    public String getIsaCtrlNum() {
        return isaCtrlNum;
    }

    /**
     * @param isaCtrlNum the isaCtrlNum to set
     */
    public void setIsaCtrlNum(String isaCtrlNum) {
        this.isaCtrlNum = isaCtrlNum;
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
     * @return the datetimeRec
     */
    public Timestamp getDatetimeRec() {
        return datetimeRec;
    }

    /**
     * @param datetimeRec the datetimeRec to set
     */
    public void setDatetimeRec(Timestamp datetimeRec) {
        this.datetimeRec = datetimeRec;
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
     * @return the res
     */
    public String getRes() {
        return res;
    }

    /**
     * @param res the res to set
     */
    public void setRes(String res) {
        this.res = res;
    }

    /**
     * @return the asnNumber
     */
    public String getAsnNumber() {
        return asnNumber;
    }

    /**
     * @param asnNumber the asnNumber to set
     */
    public void setAsnNumber(String asnNumber) {
        this.asnNumber = asnNumber;
    }

    /**
     * @return the invNumber
     */
    public String getInvNumber() {
        return invNumber;
    }

    /**
     * @param invNumber the invNumber to set
     */
    public void setInvNumber(String invNumber) {
        this.invNumber = invNumber;
    }

    /**
     * @return the PreFile
     */
    public String getPreFile() {
        return PreFile;
    }

    /**
     * @param PreFile the PreFile to set
     */
    public void setPreFile(String PreFile) {
        this.PreFile = PreFile;
    }

    /**
     * @return the PostTranFile
     */
    public String getPostTranFile() {
        return PostTranFile;
    }

    /**
     * @param PostTranFile the PostTranFile to set
     */
    public void setPostTranFile(String PostTranFile) {
        this.PostTranFile = PostTranFile;
    }

    /**
     * @return the OrgFile
     */
    public String getOrgFile() {
        return OrgFile;
    }

    /**
     * @param OrgFile the OrgFile to set
     */
    public void setOrgFile(String OrgFile) {
        this.OrgFile = OrgFile;
    }

    /**
     * @return the AckFile
     */
    public String getAckFile() {
        return AckFile;
    }

    /**
     * @param AckFile the AckFile to set
     */
    public void setAckFile(String AckFile) {
        this.AckFile = AckFile;
    }

    /**
     * @return the podate
     */
    public String getPodate() {
        return podate;
    }

    /**
     * @param podate the podate to set
     */
    public void setPodate(String podate) {
        this.podate = podate;
    }

    /**
     * @return the poVale
     */
    public String getPoValue() {
        return poValue;
    }

    /**
     * @param poVale the poVale to set
     */
    public void setPoValue(String poValue) {
        this.poValue = poValue;
    }

    /**
     * @return the poStatus
     */
    public String getPoStatus() {
        return poStatus;
    }

    /**
     * @param poStatus the poStatus to set
     */
    public void setPoStatus(String poStatus) {
        this.poStatus = poStatus;
    }

    /**
     * @return the soNumber
     */
    public String getSoNumber() {
        return soNumber;
    }

    /**
     * @param soNumber the soNumber to set
     */
    public void setSoNumber(String soNumber) {
        this.soNumber = soNumber;
    }

    /**
     * @return the iteamQty
     */
    public String getIteamQty() {
        return iteamQty;
    }

    /**
     * @param iteamQty the iteamQty to set
     */
    public void setIteamQty(String iteamQty) {
        this.iteamQty = iteamQty;
    }

    /**
     * @return the bolNumber
     */
    public String getBolNumber() {
        return bolNumber;
    }

    /**
     * @param bolNumber the bolNumber to set
     */
    public void setBolNumber(String bolNumber) {
        this.bolNumber = bolNumber;
    }

    /**
     * @return the isaDate
     */
    public String getIsaDate() {
        return isaDate;
    }

    /**
     * @param isaDate the isaDate to set
     */
    public void setIsaDate(String isaDate) {
        this.isaDate = isaDate;
    }

    /**
     * @return the isaTime
     */
    public String getIsaTime() {
        return isaTime;
    }

    /**
     * @param isaTime the isaTime to set
     */
    public void setIsaTime(String isaTime) {
        this.isaTime = isaTime;
    }

    /**
     * @return the invAmt
     */
    public String getInvAmt() {
        return invAmt;
    }

    /**
     * @param invAmt the invAmt to set
     */
    public void setInvAmt(String invAmt) {
        this.invAmt = invAmt;
    }

    /**
     * @return the chequeNum
     */
    public String getChequeNum() {
        return chequeNum;
    }

    /**
     * @param chequeNum the chequeNum to set
     */
    public void setChequeNum(String chequeNum) {
        this.chequeNum = chequeNum;
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
     * @return the senName
     */
    public String getSenName() {
        return senName;
    }

    /**
     * @param senName the senName to set
     */
    public void setSenName(String senName) {
        this.senName = senName;
    }

    /**
     * @return the recName
     */
    public String getRecName() {
        return recName;
    }

    /**
     * @param recName the recName to set
     */
    public void setRecName(String recName) {
        this.recName = recName;
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


}

