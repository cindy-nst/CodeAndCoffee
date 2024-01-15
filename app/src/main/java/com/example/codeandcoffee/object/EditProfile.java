package com.example.codeandcoffee.object;

public class EditProfile {
    String userID;
    String username;
    String password;

    public EditProfile(String userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public EditProfile() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

