package edu.kirkwood.finalproject.models;

public class ChannelModel {
    private String ChannelID;
    private int UsersInChannel;
    private String ChannelHash;
    private boolean Deleted;
    private String UserRole;
    private int Messages;

    public int getMessages() {
        return Messages;
    }

    public void setMessages(int messages) {
        Messages = messages;
    }

    public String getUserRole() {return UserRole;}

    public void setUserRole(String userRole) {UserRole = userRole;}
    public String getChannelID() {
        return ChannelID;
    }

    public void setChannelID(String channelID) {
        ChannelID = channelID;
    }

    public int getUsersInChannel() {
        return UsersInChannel;
    }

    public void setUsersInChannel(int usersInChannel) {
        UsersInChannel = usersInChannel;
    }

    public String getChannelHash() {
        return ChannelHash;
    }

    public void setChannelHash(String channelHash) {
        ChannelHash = channelHash;
    }

    public boolean isDeleted() {
        return Deleted;
    }

    public void setDeleted(boolean deleted) {
        Deleted = deleted;
    }
}
