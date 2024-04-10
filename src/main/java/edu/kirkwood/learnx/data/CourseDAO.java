package edu.kirkwood.learnx.data;

import edu.kirkwood.learnx.model.Course;
import edu.kirkwood.learnx.model.CourseCategory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.Callable;

public class CourseDAO extends Database {
    public static void main(String[] args) {
        List<Course> courses = get(5, 0, "", "");
        courses.forEach(System.out::println);
    }

    // get all
    public static List<Course> get(int limit, int offset, String categories, String skillLevel) {
        List<Course> courses = new ArrayList<>();
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_get_courses(?,?,?,?)}");
        ) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            statement.setString(3, categories);
            statement.setString(4, skillLevel);
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    String level = resultSet.getString("level");
                    String picture = resultSet.getString("picture");
                    String first_name = resultSet.getString("first_name");
                    String last_name = resultSet.getString("last_name");
                    int category_id = resultSet.getInt("category_id");
                    String category_name = resultSet.getString("category_name");
                    Course course = new Course(id, name, description, level, picture, first_name,
                            last_name, category_id, category_name);
                    courses.add(course);
                }
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return courses;
    }
    

    public static List<CourseCategory> getAllCategories() {
        List<CourseCategory> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall("{CALL sp_get_all_course_categories()}");
             ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int numCourses = resultSet.getInt("num_courses");
                categories.add(new CourseCategory(id, name, numCourses));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }
    // get single

    // add new

    // update
    
    public static boolean enroll(int studentId, int courseId) {
        try(Connection connection = getConnection();
        CallableStatement statement = connection.prepareCall("{CALL sp_add_enrollment(?,?)}");
        ) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1;
        } catch(SQLException e) {
            
        }
        return false;
    }

    public static TreeMap<Course, Instant> getCoursesEnrolled(int limit, int offset, int userId) {
        TreeMap<Course, Instant> enrollments = new TreeMap<>();
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_get_courses_by_student(?,?,?)}");
        ) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            statement.setInt(3, userId);
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    String level = resultSet.getString("level");
                    String picture = resultSet.getString("picture");
                    String first_name = resultSet.getString("first_name");
                    String last_name = resultSet.getString("last_name");
                    int category_id = resultSet.getInt("category_id");
                    String category_name = resultSet.getString("category_name");
                    Course course = new Course(id, name, description, level, picture, first_name,
                            last_name, category_id, category_name);
                    Instant enrollmentDate = resultSet.getTimestamp("enrollment_date").toInstant();
                    enrollments.put(course, enrollmentDate);
                }
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return enrollments;
    }
    


}
