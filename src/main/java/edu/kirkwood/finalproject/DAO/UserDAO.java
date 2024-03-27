package edu.kirkwood.finalproject.DAO;

import edu.kirkwood.finalproject.models.UserModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO extends Database{

    public boolean SignUp(UserModel user) {
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_create_user(?, ?)}")
        ) {
            statement.setString(1, user.getUserID());
            statement.setString(2, user.getPasswordHash());
            if (user.getEmail() != null) {

                statement.setString(3, user.getEmail());

            }
        } catch(SQLException e) {
            System.out.println("500, error with stored procedure");
            System.out.println(e.getMessage());
        }

        return false;
    }

}
