package edu.kirkwood.finalproject;

import edu.kirkwood.finalproject.DAO.ChannelDAO;
import edu.kirkwood.finalproject.DAO.UserDAO;
import edu.kirkwood.finalproject.models.ChannelModel;
import edu.kirkwood.finalproject.models.UserModel;
import edu.kirkwood.shared.EmailVerification;
import edu.kirkwood.shared.MyValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserModel user = (UserModel) session.getAttribute("currentUser");

        req.setAttribute("currentUser", user);
        req.getRequestDispatcher("WEB-INF/project/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserModel user = (UserModel) req.getSession().getAttribute("currentUser");

        String email = req.getParameter("inputEmail");
        String language = req.getParameter("inputLanguage");

        if(!email.equals(user.getEmail())) {
            if(EmailVerification.VerifyEmail(email)) {
                if(!UserDAO.UpdateEmail(email, user.getUserID())) {
                    req.setAttribute("EmailError", "Email not updated.");
                } else {
                    user.setEmail(email);
                }
            } else {
                req.setAttribute("EmailError", "This email is not valid.");
            }
        }
        if(!language.equals(user.getLanguage())) {
            if(!UserDAO.UpdateLanguage(language, user.getUserID())) {
                req.setAttribute("LanguageError", "Language not updated.");
            } else {
                user.setLanguage(language);
            }
        }

        req.getSession().setAttribute("currentUser", user);
        req.setAttribute("language", user.getLanguage());

        req.getRequestDispatcher("WEB-INF/project/profile.jsp").forward(req, resp);

    }
}
