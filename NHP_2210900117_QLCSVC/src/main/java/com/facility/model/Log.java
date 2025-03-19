package com.facility.model;

public class Log {
    private int id;
    private Integer userId;
    private String action;

    // Constructors
    public Log() {}

    public Log(int id, Integer userId, String action) {
        this.id = id;
        this.userId = userId;
        this.action = action;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
}