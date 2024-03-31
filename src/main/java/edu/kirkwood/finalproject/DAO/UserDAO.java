package edu.kirkwood.finalproject.DAO;

import edu.kirkwood.finalproject.models.UserModel;
import edu.kirkwood.learnx.model.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends Database{

    public static void main(String[] args) {
        UserModel user = new UserModel();
        user.setUserID("TestUser");
        user.setPasswordHash("eb97d409396a3e5392936dad92b909da6f08d8c121a45e1f088fe9768b0c0339");

        boolean result = false;

        result = LoginUser(user);

    }
    public static boolean SignUp(UserModel user) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_create_user(?, ?)}")
        ) {
            statement.setString(1, user.getUserID());
            statement.setString(2, user.getPasswordHash());
            if (user.getEmail() != null) {

                statement.setString(3, user.getEmail());

            }
            statement.executeUpdate();
        } catch(SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }

        return false;
    }

    public static boolean LoginUser(UserModel user) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_user_sign_in(?, ?)}")
        ) {
            statement.setString(1, user.getUserID());
            statement.setString(2, user.getPasswordHash());
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    int count = resultSet.getInt("user");
                    if (count == 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch(SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static UserModel GetUser(String username) {
        UserModel user = new UserModel();
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_get_user(?, ?)}")
        ) {
            statement.setString(1, username);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    user.setUserID(resultSet.getString("UserID"));
                    user.setEmail(resultSet.getString("Email"));
                    user.setDateCreated(resultSet.getTime("DateCreated").toInstant());
                    user.setLastLoggedIn(resultSet.getTime("LastLoggedIn").toInstant());
                }
            }
        } catch(SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }
        return new UserModel();
    }

    public static boolean UpdateLastLogin(String userID) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_update_lastlogin(?)}")
        ) {
            statement.setString(1, userID);

            statement.executeUpdate();
        } catch(SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }
        return false;
    }

}
