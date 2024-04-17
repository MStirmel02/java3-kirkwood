package edu.kirkwood.finalproject;

import edu.kirkwood.finalproject.DAO.ChannelDAO;
import edu.kirkwood.finalproject.DAO.MessageDAO;
import edu.kirkwood.finalproject.DAO.UserDAO;
import edu.kirkwood.finalproject.models.ChannelModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<ChannelModel> channelList = ChannelDAO.AllChannels();
        // Default 24 hours
        int hours = 24;
        for (ChannelModel channel : channelList) {
            int msgCount = MessageDAO.CountMessagesLastXHours(channel.getChannelID(), hours);
            channel.setMessages(msgCount);
        }


        req.setAttribute("hours", hours);
        req.setAttribute("channelList", channelList);
        req.getRequestDispatcher("WEB-INF/project/admin.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
