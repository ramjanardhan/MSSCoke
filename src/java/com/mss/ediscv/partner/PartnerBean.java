/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.partner;

import java.io.Serializable;

/**
 *
 * @author miracle
 */
public class PartnerBean implements Serializable{
 private String partnerId;
 private String partnerName;
 private String internalIdentifier;
 private String partnerIdentifier;
 private String applicationId;
 private String countryCode;
 private String status;
 private String createdBy;
 private String createdDate;
 private String changedBy;
 private String changedDate;
 
 // DeliveryChannelInfo
 private int translationId;
 private int documentExtarctId;
 private int producerMailBoxId;
  private String routerId;
  private String routingName;
 private int sequence=1;
  private String businessProcessId;
 private String translationMapName;
 private String docExtractMapName;
 private int archiveFlag;
 private String archiveDirectory;
 private String outputFileName;
 private String outputFormat;
 private String producerMailBox;
  private String encodingMailBoxId;
private String businessProcessName;
private String encodingMailBoxName;
private int deliveryChannelId;

    /**
     * @return the partnerId
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * @param partnerId the partnerId to set
     */
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
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
     * @return the internalIdentifier
     */
    public String getInternalIdentifier() {
        return internalIdentifier;
    }

    /**
     * @param internalIdentifier the internalIdentifier to set
     */
    public void setInternalIdentifier(String internalIdentifier) {
        this.internalIdentifier = internalIdentifier;
    }

    /**
     * @return the partnerIdentifier
     */
    public String getPartnerIdentifier() {
        return partnerIdentifier;
    }

    /**
     * @param partnerIdentifier the partnerIdentifier to set
     */
    public void setPartnerIdentifier(String partnerIdentifier) {
        this.partnerIdentifier = partnerIdentifier;
    }

    /**
     * @return the applicationId
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * @param applicationId the applicationId to set
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the changedBy
     */
    public String getChangedBy() {
        return changedBy;
    }

    /**
     * @param changedBy the changedBy to set
     */
    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    /**
     * @return the changedDate
     */
    public String getChangedDate() {
        return changedDate;
    }

    /**
     * @param changedDate the changedDate to set
     */
    public void setChangedDate(String changedDate) {
        this.changedDate = changedDate;
    }

    /**
     * @return the translationId
     */
    public int getTranslationId() {
        return translationId;
    }

    /**
     * @param translationId the translationId to set
     */
    public void setTranslationId(int translationId) {
        this.translationId = translationId;
    }

    /**
     * @return the documentExtarctId
     */
    public int getDocumentExtarctId() {
        return documentExtarctId;
    }

    /**
     * @param documentExtarctId the documentExtarctId to set
     */
    public void setDocumentExtarctId(int documentExtarctId) {
        this.documentExtarctId = documentExtarctId;
    }

    /**
     * @return the producerMailBoxId
     */
    public int getProducerMailBoxId() {
        return producerMailBoxId;
    }

    /**
     * @param producerMailBoxId the producerMailBoxId to set
     */
    public void setProducerMailBoxId(int producerMailBoxId) {
        this.producerMailBoxId = producerMailBoxId;
    }

    /**
     * @return the routerId
     */
    public String getRouterId() {
        return routerId;
    }

    /**
     * @param routerId the routerId to set
     */
    public void setRouterId(String routerId) {
        this.routerId = routerId;
    }

    /**
     * @return the routingName
     */
    public String getRoutingName() {
        return routingName;
    }

    /**
     * @param routingName the routingName to set
     */
    public void setRoutingName(String routingName) {
        this.routingName = routingName;
    }

    /**
     * @return the sequence
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * @param sequence the sequence to set
     */
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    /**
     * @return the businessProcessId
     */
    public String getBusinessProcessId() {
        return businessProcessId;
    }

    /**
     * @param businessProcessId the businessProcessId to set
     */
    public void setBusinessProcessId(String businessProcessId) {
        this.businessProcessId = businessProcessId;
    }

    /**
     * @return the translationMapName
     */
    public String getTranslationMapName() {
        return translationMapName;
    }

    /**
     * @param translationMapName the translationMapName to set
     */
    public void setTranslationMapName(String translationMapName) {
        this.translationMapName = translationMapName;
    }

    /**
     * @return the docExtractMapName
     */
    public String getDocExtractMapName() {
        return docExtractMapName;
    }

    /**
     * @param docExtractMapName the docExtractMapName to set
     */
    public void setDocExtractMapName(String docExtractMapName) {
        this.docExtractMapName = docExtractMapName;
    }

    /**
     * @return the archiveFlag
     */
    public int getArchiveFlag() {
        return archiveFlag;
    }

    /**
     * @param archiveFlag the archiveFlag to set
     */
    public void setArchiveFlag(int archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    /**
     * @return the archiveDirectory
     */
    public String getArchiveDirectory() {
        return archiveDirectory;
    }

    /**
     * @param archiveDirectory the archiveDirectory to set
     */
    public void setArchiveDirectory(String archiveDirectory) {
        this.archiveDirectory = archiveDirectory;
    }

    /**
     * @return the outputFileName
     */
    public String getOutputFileName() {
        return outputFileName;
    }

    /**
     * @param outputFileName the outputFileName to set
     */
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    /**
     * @return the outputFormat
     */
    public String getOutputFormat() {
        return outputFormat;
    }

    /**
     * @param outputFormat the outputFormat to set
     */
    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    /**
     * @return the producerMailBox
     */
    public String getProducerMailBox() {
        return producerMailBox;
    }

    /**
     * @param producerMailBox the producerMailBox to set
     */
    public void setProducerMailBox(String producerMailBox) {
        this.producerMailBox = producerMailBox;
    }

    /**
     * @return the encodingMailBoxId
     */
    public String getEncodingMailBoxId() {
        return encodingMailBoxId;
    }

    /**
     * @param encodingMailBoxId the encodingMailBoxId to set
     */
    public void setEncodingMailBoxId(String encodingMailBoxId) {
        this.encodingMailBoxId = encodingMailBoxId;
    }

    /**
     * @return the businessProcessName
     */
    public String getBusinessProcessName() {
        return businessProcessName;
    }

    /**
     * @param businessProcessName the businessProcessName to set
     */
    public void setBusinessProcessName(String businessProcessName) {
        this.businessProcessName = businessProcessName;
    }

    /**
     * @return the encodingMailBoxName
     */
    public String getEncodingMailBoxName() {
        return encodingMailBoxName;
    }

    /**
     * @param encodingMailBoxName the encodingMailBoxName to set
     */
    public void setEncodingMailBoxName(String encodingMailBoxName) {
        this.encodingMailBoxName = encodingMailBoxName;
    }

    /**
     * @return the deliveryChannelId
     */
    public int getDeliveryChannelId() {
        return deliveryChannelId;
    }

    /**
     * @param deliveryChannelId the deliveryChannelId to set
     */
    public void setDeliveryChannelId(int deliveryChannelId) {
        this.deliveryChannelId = deliveryChannelId;
    }

   
}
