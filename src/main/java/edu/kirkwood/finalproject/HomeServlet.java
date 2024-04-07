package edu.kirkwood.finalproject;

import edu.kirkwood.finalproject.DAO.ChannelDAO;
import edu.kirkwood.finalproject.models.ChannelModel;
import edu.kirkwood.finalproject.models.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserModel user = (UserModel) session.getAttribute("activeUser");

        ArrayList<ChannelModel> channelList = new ArrayList<ChannelModel>();
        channelList = ChannelDAO.ViewUserChannels(user.getUserID());

        req.setAttribute("channelList", channelList);
        req.getRequestDispatcher("WEB-INF/project/homepage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
