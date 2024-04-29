package edu.kirkwood.finalproject.models;

import java.sql.Time;
import java.time.Instant;

public class UserModel {
    public String UserID;
    private String PasswordHash;
    private String Email;
    private Instant LastLoggedIn;
    private Instant DateCreated;
    private String Language;

    public int getMessages() {
        return messages;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }

    private int messages;


    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public Instant getLastLoggedIn() {
        return LastLoggedIn;
    }

    public void setLastLoggedIn(Instant lastLoggedIn) {
        LastLoggedIn = lastLoggedIn;
    }

    public Instant getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
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
