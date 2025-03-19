package com.facility.model;

public class Facility {
    private int id;
    private String name;
    private String description;
    private String status;
    private Integer roomId;

    // Constructors
    public Facility() {}

    public Facility(int id, String name, String description, String status, Integer roomId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.roomId = roomId;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getRoomId() { return roomId; }
    public void setRoomId(Integer roomId) { this.roomId = roomId; }
}