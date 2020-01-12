package com.example.experiment_1.DeleteEvent;

public class DeleteUser {



    private String deleteName;
    private String deleteUserName;

    public DeleteUser(String deleteName, String deleteUserName) {
        this.deleteName = deleteName;
        this.deleteUserName = deleteUserName;
    }


    public String getDeleteName() {
        return deleteName;
    }

    public String getDeleteUserName() {
        return deleteUserName;
    }
}
