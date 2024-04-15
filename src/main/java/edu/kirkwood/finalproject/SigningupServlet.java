package edu.kirkwood.finalproject;

import com.google.common.hash.Hashing;
import edu.kirkwood.finalproject.DAO.UserDAO;
import edu.kirkwood.finalproject.models.UserModel;
import edu.kirkwood.shared.MyValidator;
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
import java.util.regex.Matcher;

@WebServlet("/signingup")
public class SigningupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Sign up for an account");
        req.getRequestDispatcher("WEB-INF/project/signingup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("inputUser1");
        String password1 = req.getParameter("inputPassword1");
        String password2 = req.getParameter("inputPassword2");
        String[] terms = req.getParameterValues("checkbox-1");


        Map<String, String> results = new HashMap<>();
        results.put("username", username);
        results.put("password1", password1);
        results.put("password2", password2);

        UserModel user = new UserModel();
        try {
            user.setUserID(username);
        } catch(IllegalArgumentException e) {
            results.put("userError", e.getMessage());
        }

        UserModel userFromDatabase = UserDAO.GetUser(username);
        if(userFromDatabase.UserID != null) {
            results.put("userError", "User already exists");
        }
        try {
            // hash this
            String passwordHashed = Hashing.sha256()
                    .hashString(password1, StandardCharsets.UTF_8)
                    .toString();
            user.setPasswordHash(passwordHashed);
        } catch(IllegalArgumentException e) {
            results.put("password1Error", e.getMessage());
        }
        if(!password2.equals(password1)) {
            results.put("password2Error", "Passwords must match");
        }
        if(terms == null || !terms[0].equals("agree")) {
            results.put("agreeError", "You must agree to the terms");
            results.put("agree", "false");
        } else {
            results.put("agree", "true");
        }

        if (!MyValidator.passwordPattern.matcher(password1).matches()) {
            results.put("password1Error", "Password must have minimum 8 characters and 3 of 4 types of characters (uppercase, lowercase, digit, symbol)");
        }
        if (username.length() < 3) {
            results.put("userError", "Username must be at least 3 characters long");
        }

        if(!results.containsKey("userError") &&
                !results.containsKey("password1Error") &&
                !results.containsKey("password2Error") &&
                !results.containsKey("agreeError")
        ) {
            boolean result = UserDAO.SignUp(user);

            if(result) {
                HttpSession session = req.getSession();
                session.invalidate(); // Remove any existing session data
                session = req.getSession();
                session.setAttribute("activeUser", user);
                resp.sendRedirect("home");
                return;
            }
        }

        req.setAttribute("results", results);
        req.setAttribute("pageTitle", "Sign up for an account");
        req.getRequestDispatcher("WEB-INF/project/signingup.jsp").forward(req, resp);
    }
}
