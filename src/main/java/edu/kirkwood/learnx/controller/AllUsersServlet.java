package edu.kirkwood.learnx.controller;

import edu.kirkwood.learnx.data.UserDAO;
import edu.kirkwood.learnx.model.User;
import edu.kirkwood.shared.Helpers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/all-users")
public class AllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userFromSession = Helpers.getUserFromSession(req);
        if(userFromSession == null || !User.isActive(userFromSession) || !User.isAdmin(userFromSession)) {
            // Display a 404 error if not logged in
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        List<User> users = UserDAO.getAll();
        req.setAttribute("users", users);
        req.setAttribute("pageTitle", "All Users");
        req.getRequestDispatcher("WEB-INF/learnx/all-users.jsp").forward(req, resp);
    }
}
