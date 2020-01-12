package com.example.experiment_1.FriendRequestAddEvent;

public class FriendRequest {




    private String requesteeName;
    private String requesteeUserName;

    public FriendRequest(String requesteeName, String requesteeUserName) {
        this.requesteeName = requesteeName;
        this.requesteeUserName = requesteeUserName;
    }

    public String getRequesteeName() {
        return requesteeName;
    }

    public String getRequesteeUserName() {
        return requesteeUserName;
    }
}
