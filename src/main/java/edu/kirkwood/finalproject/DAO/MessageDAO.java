package edu.kirkwood.finalproject.DAO;

import edu.kirkwood.finalproject.models.ChannelModel;
import edu.kirkwood.finalproject.models.MessageModel;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO extends Database {

    public static ArrayList<MessageModel> ViewChannelMessages(String channelID) {
        ArrayList<MessageModel> messageList = new ArrayList<MessageModel>();
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_user_view_channel_messages(?)}")
        ) {
            statement.setString(1, channelID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    do {
                        String userID = resultSet.getString("UserID");
                        String content = resultSet.getString("Content");
                        Time timeSent = resultSet.getTime("TimeSent");
                        MessageModel message = new MessageModel();
                        message.setUserID(userID);
                        message.setContent(content);
                        message.setTimeSent(timeSent);
                        messageList.add(message);

                    } while (!resultSet.isLast());
                }
            }
        } catch (SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }
        return messageList;
    }

}
