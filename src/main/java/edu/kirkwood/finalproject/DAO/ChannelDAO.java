package edu.kirkwood.finalproject.DAO;

import edu.kirkwood.finalproject.models.ChannelModel;
import io.netty.channel.ChannelId;

import java.nio.channels.Channel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChannelDAO extends Database {


    public static ArrayList<ChannelModel> ViewUserChannels(String userID) {
        ArrayList<ChannelModel> channelList = new ArrayList<ChannelModel>();
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_user_view_channels(?)}")
        ) {
            statement.setString(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    do {
                        String channelID = resultSet.getString("ChannelID");
                        int usersInChannel = resultSet.getInt("UsersInChannel");
                        String roleID = resultSet.getString("RoleID");
                        ChannelModel channel = new ChannelModel();
                        channel.setChannelID(channelID);
                        channel.setUsersInChannel(usersInChannel);
                        channel.setUserRole(roleID);
                        channelList.add(channel);
                    } while (!resultSet.isLast());
                }
            }
        } catch (SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }
        return channelList;
    }

    public static boolean ChannelLogin(String userID, String channelID, String channelHash) {
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_user_channel_sign_in(?, ?, ?)}")
        ) {
            statement.setString(1, userID);
            statement.setString(2, channelID);
            statement.setString(3, channelHash);
            int count = statement.executeUpdate();
            return count == 1;
        } catch (SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean ChannelCreate(String userID, String channelID, String channelHash) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_create_channel(?, ?, ?)}")
        ) {
            statement.setString(1, userID);
            statement.setString(2, channelID);
            statement.setString(3, channelHash);
            return statement.execute();
        } catch(SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean ChannelLeave(String userID, String channelID) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_user_channel_sign_out(?, ?)}")
        ) {
            statement.setString(1, userID);
            statement.setString(2, channelID);
            return statement.execute();
        } catch(SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
            return false;
        }
    }
}
