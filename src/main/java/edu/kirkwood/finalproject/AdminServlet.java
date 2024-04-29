package edu.kirkwood.finalproject;

import edu.kirkwood.finalproject.DAO.ChannelDAO;
import edu.kirkwood.finalproject.DAO.MessageDAO;
import edu.kirkwood.finalproject.DAO.UserDAO;
import edu.kirkwood.finalproject.models.ChannelModel;
import edu.kirkwood.finalproject.models.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.channels.Channel;
import java.util.ArrayList;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserModel currentUser = (UserModel) req.getSession().getAttribute("currentUser");
        if(currentUser.getUserID().equals("MStirmel")) {
            ArrayList<ChannelModel> channelList = ChannelDAO.AllChannels();
            // Default 24 hours
            int hours = 24;
            for (ChannelModel channel : channelList) {
                int msgCount = MessageDAO.CountMessagesLastXHours(channel.getChannelID(), hours);
                channel.setMessages(msgCount);
            }

            ArrayList<UserModel> userList = UserDAO.AllUsers();
            for (UserModel user : userList) {
                int msgCount = MessageDAO.CountUserMessagesLastXHours(user.getUserID(), hours);
                user.setMessages(msgCount);
            }

            req.setAttribute("hours", hours);
            req.setAttribute("userList", userList);
            req.setAttribute("channelList", channelList);
            req.getRequestDispatcher("WEB-INF/project/admin.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String formType = req.getParameter("formtype");

        switch (formType) {
            case "filter":
                try{
                    int hours = Integer.parseInt(req.getParameter("hours").toString());
                    if(hours < 1) {
                        hours = 24;
                    }
                    ArrayList<ChannelModel> channelList = ChannelDAO.AllChannels();
                    for (ChannelModel channel : channelList) {
                        int msgCount = MessageDAO.CountMessagesLastXHours(channel.getChannelID(), hours);
                        channel.setMessages(msgCount);
                    }
                    ArrayList<UserModel> userList = UserDAO.AllUsers();
                    for (UserModel user : userList) {
                        int msgCount = MessageDAO.CountUserMessagesLastXHours(user.getUserID(), hours);
                        user.setMessages(msgCount);
                    }
                    req.setAttribute("hours", hours);
                    req.setAttribute("channelList", channelList);
                    req.setAttribute("userList", userList);
                }catch (Exception e) {
                    req.setAttribute("FilterError", "Filter failed");
                }
                break;
            case "delete":
                try{
                    String channelID = req.getParameter("channelid");
                    if(!ChannelDAO.ChannelDelete(channelID)) {
                        req.setAttribute("ChannelError", "Not able to remove channel");
                    }
                    ArrayList<ChannelModel> channelList = ChannelDAO.AllChannels();
                    req.setAttribute("hours", req.getAttribute("hours"));
                    req.setAttribute("channelList", channelList);
                    req.setAttribute("userList", req.getAttribute("userList"));
                } catch (Exception e) {
                    req.setAttribute("ChannelError", "Not able to remove channel");
                }
                break;
        }




        req.getRequestDispatcher("WEB-INF/project/admin.jsp").forward(req, resp);
    }

}
