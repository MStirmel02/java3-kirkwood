package edu.kirkwood.finalproject;

import edu.kirkwood.finalproject.DAO.UserDAO;
import edu.kirkwood.finalproject.models.UserModel;
import edu.kirkwood.shared.CommunicationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/massmail")
public class EmailingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel user = (UserModel) req.getSession().getAttribute("currentUser");
        if (user.getUserID().equals("MStirmel")) {

            ArrayList<String> emailList = UserDAO.getEmails();
            req.setAttribute("emailList", emailList);

            req.getRequestDispatcher("WEB-INF/project/emailing.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = (String) req.getParameter("inputEmail");
        String content = (String) req.getParameter("inputContent");
        ArrayList<String> emailList = UserDAO.getEmails();
        req.setAttribute("emailList", emailList);

        if(input.isEmpty()) {
            req.setAttribute("InputError", "Must contain content.");
            req.getRequestDispatcher("WEB-INF/project/emailing.jsp").forward(req, resp);
            return;
        }


        try {
            if(input.equals("All Emails")) {
                for (String email : emailList) {
                    CommunicationService.sendEmail(email, "A message from ChatApp", content);
                }
            } else {
                CommunicationService.sendEmail(input, "A message from ChatApp", content);
            }
        } catch (Exception e) {
            req.setAttribute("EmailError", "Email sending failed.");
        }

        req.getRequestDispatcher("WEB-INF/project/emailing.jsp").forward(req, resp);
    }
}
