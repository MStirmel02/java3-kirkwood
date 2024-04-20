package edu.kirkwood.finalproject.models;

import java.sql.Time;
import java.time.Instant;

public class UserModel {
    public String UserID;
    private String PasswordHash;
    private String Email;
    private Time LastLoggedIn;
    private Time DateCreated;
    private String Language;

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public Time getLastLoggedIn() {
        return LastLoggedIn;
    }

    public void setLastLoggedIn(Time lastLoggedIn) {
        LastLoggedIn = lastLoggedIn;
    }

    public Time getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(Time dateCreated) {
        DateCreated = dateCreated;
    }

    public String getUserID() {return UserID;}

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }

    public String getEmail() {
        if (Email == null) {
            return "";
        }
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
