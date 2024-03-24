package edu.kirkwood.finalproject.models;

import java.sql.Date;

public class MessageModel {
    private String MessageID;
    private String ChannelID;
    private String UserID;
    private String Content;
    private Date TimeSent;

    public String getMessageID() {
        return MessageID;
    }

    public void setMessageID(String messageID) {
        MessageID = messageID;
    }

    public String getChannelID() {
        return ChannelID;
    }

    public void setChannelID(String channelID) {
        ChannelID = channelID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getTimeSent() {
        return TimeSent;
    }

    public void setTimeSent(Date timeSent) {
        TimeSent = timeSent;
    }
}
