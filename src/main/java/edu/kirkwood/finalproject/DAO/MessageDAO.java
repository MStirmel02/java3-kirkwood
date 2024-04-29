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
             CallableStatement statement = connection.prepareCall("{CALL sp_view_channel_messages(?)}")
        ) {
            statement.setString(1, channelID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    String userID = resultSet.getString("UserID");
                    String content = resultSet.getString("Content");
                    Time timeSent = resultSet.getTime("TimeSent");
                    MessageModel message = new MessageModel();
                    message.setUserID(userID);
                    message.setContent(content);
                    message.setTimeSent(timeSent);
                    messageList.add(message);
                }
            }
        } catch (SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }
        return messageList;
    }

    public static int CountMessagesLastXHours(String channelID, int hours) {
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_view_last_h_messages(?, ?)}")
        ) {
            statement.setString(1, channelID);
            statement.setInt(2, hours);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static boolean SendMessage(String userId, String channelId, String content) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_create_message(?,?,?)}")
        ) {
            statement.setString(1, userId);
            statement.setString(2, channelId);
            statement.setString(3, content);
            return statement.executeUpdate() > 0;
        } catch(SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static int CountUserMessagesLastXHours(String userID, int hours) {
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_user_view_last_h_messages(?, ?)}")
        ) {
            statement.setString(1, userID);
            statement.setInt(2, hours);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
