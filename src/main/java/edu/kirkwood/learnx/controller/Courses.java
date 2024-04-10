package edu.kirkwood.learnx.controller;

import edu.kirkwood.learnx.data.CourseDAO;
import edu.kirkwood.learnx.model.Course;
import edu.kirkwood.learnx.model.CourseCategory;
import edu.kirkwood.learnx.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.TreeMap;

@WebServlet("/courses")
public class Courses extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] categoryParams = req.getParameterValues("category");
        String skillFilter = req.getParameter("skill-level");
        String categoryFilter = "";
        if(categoryParams != null && categoryParams.length > 0) {
            categoryFilter = String.join(",", categoryParams);
        }
        if(skillFilter == null) {
            skillFilter = "";
        }
        // To do: For pagination
        int limit = 5;
        int offset = 0;
        List<Course> courses = CourseDAO.get(limit, offset, categoryFilter, skillFilter);
        List<CourseCategory> categories = CourseDAO.getAllCategories();

        // Get enrolled courses if user
        TreeMap<Course, Instant> enrolledCourses = new TreeMap<Course, Instant>();
        if(req.getSession().getAttribute("activeUser") != null) {
            User user = (User) req.getSession().getAttribute("activeUser");
            enrolledCourses = CourseDAO.getCoursesEnrolled(limit, offset, user.getId());
        }
        req.setAttribute("enrolledCourses", enrolledCourses);
        req.setAttribute("courses", courses);
        req.setAttribute("categories", categories);
        req.setAttribute("pageTitle", "Courses");
        req.getRequestDispatcher("WEB-INF/learnx/all-courses.jsp").forward(req, resp);

    }
}
