package edu.kirkwood.finalproject.DAO;

import edu.kirkwood.finalproject.models.UserModel;
import edu.kirkwood.learnx.model.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends Database{

    public static boolean SignUp(UserModel user) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_create_user(?, ?)}")
        ) {
            statement.setString(1, user.getUserID());
            statement.setString(2, user.getPasswordHash());

            statement.executeUpdate();
            return true;
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
                    return count == 1;
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
            CallableStatement statement = connection.prepareCall("{CALL sp_get_user(?)}")
        ) {
            statement.setString(1, username);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    user.setUserID(resultSet.getString("UserID"));
                    user.setEmail(resultSet.getString("Email"));
                    user.setDateCreated(resultSet.getTimestamp("DateCreated").toInstant());
                    user.setLastLoggedIn(resultSet.getTimestamp("LastLoggedIn").toInstant());
                    user.setLanguage(resultSet.getString("Language"));
                }
            }
        } catch(SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }
        return user;
    }

    public static boolean UpdateLastLogin(String userID) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_update_lastlogin(?)}")
        ) {
            statement.setString(1, userID);

            statement.executeUpdate();
            return true;
        } catch(SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }
        return false;
    }


    public static boolean UpdateEmail(String email, String userId) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_set_email(?, ?)}")
        ) {
            statement.setString(1, email);
            statement.setString(2, userId);

            return statement.executeUpdate() == 1;

        } catch(SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }
        return false;
    }


    public static boolean UpdateLanguage(String language, String userId) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_set_language(?, ?)}")
        ) {
            statement.setString(1, language);
            statement.setString(2, userId);

            return statement.executeUpdate() == 1;

        } catch(SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static ArrayList<UserModel> AllUsers() {
        ArrayList<UserModel> userList = new ArrayList<UserModel>();
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_get_all_users()}")
        ) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    UserModel user = new UserModel();
                    user.setUserID(resultSet.getString("UserID"));
                    user.setEmail(resultSet.getString("Email"));
                    user.setLastLoggedIn(resultSet.getTimestamp("LastLoggedIn").toInstant());
                    userList.add(user);
                }
            }
        } catch(SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }
        return userList;
    }


    public static boolean DeleteUser(String userId) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_delete_user(?)}")
        ) {
            statement.setString(1, userId);

            return statement.executeUpdate() >= 0;

        } catch(SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static ArrayList<String> getEmails() {
        ArrayList<String> emailList = new ArrayList<String>();
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_get_emails()}")
        ) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    emailList.add(resultSet.getString("Email"));
                }
            }
        } catch(SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }
        return emailList;
    }
}
