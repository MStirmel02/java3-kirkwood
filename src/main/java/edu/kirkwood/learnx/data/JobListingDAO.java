package edu.kirkwood.learnx.data;

import edu.kirkwood.learnx.model.Course;
import edu.kirkwood.learnx.model.JobListing;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class JobListingDAO  extends Database{

    public static void main(String[] args) {
        get(5, 0 , "", "").forEach(System.out::println);

    }
    public static ArrayList<JobListing> get(int limit, int offset, String department, String location) {
        ArrayList<JobListing> listings = new ArrayList<>();
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_get_job_listings(?,?,?,?)}");
        ) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            statement.setString(3, department);
            statement.setString(4, location);
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    JobListing listing = new JobListing(
                            resultSet.getInt("job_id"),
                            resultSet.getInt("department_id"),
                            resultSet.getString("department_name"),
                            resultSet.getBoolean("featured"),
                            resultSet.getString("position"),
                            resultSet.getTimestamp("posted_at"),
                            resultSet.getString("contract"),
                            resultSet.getString("location"),
                            resultSet.getString("description")
                    );
                    listings.add(listing);
                }
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return listings;
    }
}
