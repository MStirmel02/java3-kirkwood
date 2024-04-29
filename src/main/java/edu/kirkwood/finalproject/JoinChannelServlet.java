package edu.kirkwood.finalproject;

import com.google.common.hash.Hashing;
import edu.kirkwood.finalproject.DAO.ChannelDAO;
import edu.kirkwood.finalproject.DAO.UserDAO;
import edu.kirkwood.finalproject.models.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
@WebServlet("/joinchannel")
public class JoinChannelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirect = req.getParameter("redirect");
        req.setAttribute("redirect", redirect);
        req.setAttribute("pageTitle", "Sign in");
        req.getRequestDispatcher("WEB-INF/project/joinchannel.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("inputChannel");
        String password1 = req.getParameter("inputPassword1");
        Map<String, String> results = new HashMap<>();
        results.put("channel", id);
        results.put("password1", password1);

        //Usermodel
        UserModel user;

        String passwordHashed = Hashing.sha256()
                .hashString(password1, StandardCharsets.UTF_8)
                .toString();

        user = (UserModel) req.getSession().getAttribute("currentUser");

        if (ChannelDAO.ChannelLogin(user.getUserID(), id, passwordHashed)) {
            resp.sendRedirect("home");
        } else {
            results.put("loginError", "The channel id or password entered is not correct.");
            req.setAttribute("results", results);
            req.setAttribute("pageTitle", "Sign in");
            req.getRequestDispatcher("WEB-INF/project/joinchannel.jsp").forward(req, resp);
        }

    }
}
