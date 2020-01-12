package com.example.experiment_1.DeleteEvent;

public class DeleteUserEvent {
    private String eventName;
    private int id;
    private String eventDescription;
    private String eventDate;
    private String host;
    private String privacy;
    //test to run pipelines
    public DeleteUserEvent(String eventName, int id, String eventDescription, String eventDate, String host, String privacy) {
        this.eventName = eventName;
        this.id = id;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.host = host;
        this.privacy = privacy;
    }

    public String getEventName() {
        return eventName;
    }
    //This is a test
    public int getId() {
        return id;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getHost() {
        return host;
    }

    public String getPrivacy() {
        return privacy;
    }
}
