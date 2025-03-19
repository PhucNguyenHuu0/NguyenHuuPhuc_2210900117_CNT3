package com.facility.model;

import java.util.Date;

public class MaintenanceRequest {
    private int id;
    private Integer facilityId;
    private Integer userId;
    private String description;
    private String status;
    private Date requestDate;
    private Date completionDate;
    private Integer technicianId;
    private String progress;

    // Constructors
    public MaintenanceRequest() {}

    public MaintenanceRequest(int id, Integer facilityId, Integer userId, String description, String status,
                             Date requestDate, Date completionDate, Integer technicianId, String progress) {
        this.id = id;
        this.facilityId = facilityId;
        this.userId = userId;
        this.description = description;
        this.status = status;
        this.requestDate = requestDate;
        this.completionDate = completionDate;
        this.technicianId = technicianId;
        this.progress = progress;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Integer getFacilityId() { return facilityId; }
    public void setFacilityId(Integer facilityId) { this.facilityId = facilityId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Date getRequestDate() { return requestDate; }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }
    public Date getCompletionDate() { return completionDate; }
    public void setCompletionDate(Date completionDate) { this.completionDate = completionDate; }
    public Integer getTechnicianId() { return technicianId; }
    public void setTechnicianId(Integer technicianId) { this.technicianId = technicianId; }
    public String getProgress() { return progress; }
    public void setProgress(String progress) { this.progress = progress; }
}