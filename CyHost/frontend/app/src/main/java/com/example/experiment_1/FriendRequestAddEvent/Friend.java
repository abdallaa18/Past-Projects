package com.example.experiment_1.FriendRequestAddEvent;

public class Friend {
    private String friendName;
    private String friendUserName;

    public Friend(String friendName, String friendUserName) {
        this.friendName = friendName;
        this.friendUserName = friendUserName;
    }

    public String getFriendName() {
        return friendName;
    }

    public String getFriendUserName() {
        return friendUserName;
    }
}
