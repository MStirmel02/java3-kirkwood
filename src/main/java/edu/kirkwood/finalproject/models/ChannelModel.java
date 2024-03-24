package edu.kirkwood.finalproject.models;

public class ChannelModel {
    private String ChannelID;
    private int UsersInChannel;
    private String ChannelHash;
    private boolean Deleted;

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
