package edu.kirkwood.finalproject;

import edu.kirkwood.finalproject.DAO.UserDAO;
import edu.kirkwood.finalproject.models.UserModel;
import edu.kirkwood.learnx.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete")
public class DeleteAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel user = (UserModel) req.getSession().getAttribute("currentUser");

        if(UserDAO.DeleteUser(user.getUserID())) {
            req.getSession().invalidate();
            req.getRequestDispatcher("WEB-INF/project/landing.jsp").forward(req, resp);
        }else {
            req.setAttribute("DeleteError", "An error occured.");
            req.getRequestDispatcher("WEB-INF/project/profile.jsp").forward(req, resp);
        }
    }
}
