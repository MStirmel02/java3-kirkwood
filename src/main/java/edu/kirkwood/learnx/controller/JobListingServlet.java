package edu.kirkwood.learnx.controller;

import edu.kirkwood.learnx.data.JobListingDAO;
import edu.kirkwood.learnx.data.UserDAO;
import edu.kirkwood.learnx.model.JobListing;
import edu.kirkwood.learnx.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/joblistings")
public class JobListingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userFromSession = (User)session.getAttribute("activeUser");

        ArrayList<JobListing> listings = JobListingDAO.get(10, 0, "", "");

        req.setAttribute("listings", listings);
        req.setAttribute("pageTitle", "Careers");
        req.getRequestDispatcher("WEB-INF/learnx/joblistings.jsp").forward(req, resp);
    }
}
