/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.ediscv.issues;

import java.sql.Timestamp;

/**
 *
 * @author miracle
 */
public class IssueBean {
    
    private String assignTo;
    private String category;
    private String priority;
    private Timestamp created_date ; 
    private String devEstTime;
    private String status ;
    private int id;

    /**
     * @return the assignTo
     */
    public String getAssignTo() {
        return assignTo;
    }

    /**
     * @param assignTo the assignTo to set
     */
    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the created_date
     */
    public Timestamp getCreated_date() {
        return created_date;
    }

    /**
     * @param created_date the created_date to set
     */
    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    /**
     * @return the devEstTime
     */
    public String getDevEstTime() {
        return devEstTime;
    }

    /**
     * @param devEstTime the devEstTime to set
     */
    public void setDevEstTime(String devEstTime) {
        this.devEstTime = devEstTime;
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
    
}
