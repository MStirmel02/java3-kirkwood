package edu.kirkwood.finalproject;

import com.google.common.hash.Hashing;
import edu.kirkwood.finalproject.DAO.UserDAO;
import edu.kirkwood.finalproject.models.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirect = req.getParameter("redirect");
        req.setAttribute("redirect", redirect);
        req.setAttribute("pageTitle", "Sign in");
        req.getRequestDispatcher("WEB-INF/project/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("inputUser1");
        String password1 = req.getParameter("inputPassword1");
        String[] remember = req.getParameterValues("checkbox-1");
        String redirect = req.getParameter("redirect");
        Map<String, String> results = new HashMap<>();
        results.put("user1", id);
        results.put("password1", password1);
        if(remember != null && remember[0].equals("yes")) {
            results.put("remember", "yes");
        }

        //Usermodel
        UserModel user = new UserModel();
        user.setUserID(id);

        String passwordHashed = Hashing.sha256()
                .hashString(password1, StandardCharsets.UTF_8)
                .toString();
        user.setPasswordHash(passwordHashed);


        if (UserDAO.LoginUser(user)) {
            UserDAO.UpdateLastLogin(user.getUserID());
            user = UserDAO.GetUser(user.getUserID());

            HttpSession session = req.getSession();
            session.setAttribute("activeUser", user);
            if(remember != null && remember[0].equals("yes")) {
                session.setMaxInactiveInterval(7 * 24 * 60 * 60); // 7 days
            }
            if(redirect != null && !redirect.equals("")) {
                resp.sendRedirect(redirect);
            } else {
                resp.sendRedirect("home");
            }
        } else {
            results.put("loginError", "The username or password entered is not correct.");
            req.setAttribute("results", results);
            req.setAttribute("pageTitle", "Sign in");
            req.getRequestDispatcher("WEB-INF/project/login.jsp").forward(req, resp);
        }

        /*
        if(userFromDatabase == null) {
            // Email entered is not correct
            results.put("loginError", "The email or password you entered is not correct");
        } else {
            String hashedPassword = String.valueOf(userFromDatabase.getPassword());
            if (!BCrypt.checkpw(password1, hashedPassword)) {
                // Password entered is not correct
                results.put("loginError", "The email or password you entered is not correct");
            } else {
                if(userFromDatabase.getStatus() == null || !userFromDatabase.getStatus().equals("active")) {
                    results.put("loginError", "Your account is not active. Please contact support for help.");
                }  else {
                    // Email and password are correct, and it's an active user
                    // To Do: Get an instant representing UTC 0
                    userFromDatabase.setLast_logged_in(Instant.now().atOffset(ZoneOffset.UTC).toInstant());
                    UserDAO.update(userFromDatabase);
                    userFromDatabase.setPassword(null);
                    HttpSession session = req.getSession();
                    session.setAttribute("activeUser", userFromDatabase);
                    if(remember != null && remember[0].equals("yes")) {
                        session.setMaxInactiveInterval(7 * 24 * 60 * 60); // 7 days
                    }
                    if(redirect != null && !redirect.equals("")) {
                        resp.sendRedirect(redirect);
                    } else {
                        session.setAttribute("flashMessageSuccess", "Welcome Back!");
                        resp.sendRedirect("learnx");
                    }
                    return;
                }
            }
        }
        req.setAttribute("results", results);
        req.setAttribute("pageTitle", "Sign in");
        req.getRequestDispatcher("WEB-INF/project/login.jsp").forward(req, resp); */
    }
}
