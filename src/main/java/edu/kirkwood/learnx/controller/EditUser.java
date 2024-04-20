package edu.kirkwood.learnx.controller;
import edu.kirkwood.learnx.data.UserDAO;
import edu.kirkwood.learnx.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/edit-user")
public class EditUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Todo: restrict page to admin users
        String idStr = req.getParameter("id");
        // Todo: validate the idStr
        int id = Integer.parseInt(idStr);
        User user = UserDAO.get(id);
        req.setAttribute("user", user);
        String name = (!user.getFirstName().equals("") && !user.getLastName().equals("") ? user.getFirstName() + " " + user.getLastName() : "User");
        req.setAttribute("pageTitle", "Edit " + name);
        req.getRequestDispatcher("WEB-INF/learnx/edit-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String firstNameInput = req.getParameter("firstNameInput");
        String lastNameInput = req.getParameter("lastNameInput");
        String emailInput = req.getParameter("emailInput");
        String phoneInput = req.getParameter("phoneInput");
        String languageInput = req.getParameter("languageInput");
        String statusInput = req.getParameter("statusInput");
        String privilegesInput = req.getParameter("privilegesInput");
        
        Map<String, String> results = new HashMap<>();
        // Todo: validate the idStr
        int id = Integer.parseInt(idStr);
        User user = UserDAO.get(id);
        if(user != null) {
            try {
                user.setFirstName(firstNameInput);
            } catch (IllegalArgumentException e) {
                results.put("firstNameError", e.getMessage());
            }

            try {
                user.setLastName(lastNameInput);
            } catch (IllegalArgumentException e) {
                results.put("lastNameError", e.getMessage());
            }
            
            if(!emailInput.equals(user.getEmail()) && UserDAO.get(emailInput) != null) {
                results.put("emailError", "User already exists");
            } else {
                try {
                   user.setEmail(emailInput);
                } catch (IllegalArgumentException e) {
                    results.put("emailError", e.getMessage());
                }
            }
            
            try {
                user.setPhone(phoneInput);
            } catch (IllegalArgumentException e) {
                results.put("phoneError", e.getMessage());
            }

            try {
                user.setLanguage(languageInput);
            } catch (IllegalArgumentException e) {
                results.put("languageError", e.getMessage());
            }

            try {
                user.setStatus(statusInput);
            } catch (IllegalArgumentException e) {
                results.put("statusError", e.getMessage());
            }

            try {
                user.setPrivileges(privilegesInput);
            } catch (IllegalArgumentException e) {
                results.put("privilegesError", e.getMessage());
            }
            
            HttpSession session = req.getSession();

            if (!results.containsKey("firstNameError") && !results.containsKey("lastNameError")
                    && !results.containsKey("emailError") && !results.containsKey("phoneError")
                    && !results.containsKey("languageError") && !results.containsKey("statusError") && !results.containsKey("privilegesError")
            ) {
                UserDAO.update(user);
                session.setAttribute("flashMessageSuccess", "User updated");
            } 
        }
        req.setAttribute("user", user);
        req.setAttribute("results", results);
        String name = (!user.getFirstName().equals("") && !user.getLastName().equals("") ? user.getFirstName() + " " + user.getLastName() : "User");
        req.setAttribute("pageTitle", "Edit " + name);
        req.getRequestDispatcher("WEB-INF/learnx/edit-user.jsp").forward(req, resp);
    }
}
