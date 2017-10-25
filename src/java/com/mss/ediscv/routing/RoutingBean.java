/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.routing;

import java.io.Serializable;

/**
 *
 * @author miracle
 */
public class RoutingBean implements Serializable{
     private int routingId;
 private String name;
 private String acceptorLookupAlias;
 private String direction;
 private String internalRouteEmail;
 private String systemType;
 private String status;
 private String destMailBox;
 private String envelope;
 private String createdDate;
 private String changedDate;
    /**
     * @return the routingId
     */
    public int getRoutingId() {
        return routingId;
    }

    /**
     * @param routingId the routingId to set
     */
    public void setRoutingId(int routingId) {
        this.routingId = routingId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the acceptorLookupAlias
     */
    public String getAcceptorLookupAlias() {
        return acceptorLookupAlias;
    }

    /**
     * @param acceptorLookupAlias the acceptorLookupAlias to set
     */
    public void setAcceptorLookupAlias(String acceptorLookupAlias) {
        this.acceptorLookupAlias = acceptorLookupAlias;
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
     * @return the internalRouteEmail
     */
    public String getInternalRouteEmail() {
        return internalRouteEmail;
    }

    /**
     * @param internalRouteEmail the internalRouteEmail to set
     */
    public void setInternalRouteEmail(String internalRouteEmail) {
        this.internalRouteEmail = internalRouteEmail;
    }

    /**
     * @return the systemType
     */
    public String getSystemType() {
        return systemType;
    }

    /**
     * @param systemType the systemType to set
     */
    public void setSystemType(String systemType) {
        this.systemType = systemType;
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
     * @return the destMailBox
     */
    public String getDestMailBox() {
        return destMailBox;
    }

    /**
     * @param destMailBox the destMailBox to set
     */
    public void setDestMailBox(String destMailBox) {
        this.destMailBox = destMailBox;
    }

    /**
     * @return the envelope
     */
    public String getEnvelope() {
        return envelope;
    }

    /**
     * @param envelope the envelope to set
     */
    public void setEnvelope(String envelope) {
        this.envelope = envelope;
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
    
    
    
}
