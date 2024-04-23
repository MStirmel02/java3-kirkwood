package edu.kirkwood.finalproject;

import com.google.common.hash.Hashing;
import edu.kirkwood.finalproject.DAO.ChannelDAO;
import edu.kirkwood.finalproject.DAO.MessageDAO;
import edu.kirkwood.finalproject.models.ChannelModel;
import edu.kirkwood.finalproject.models.MessageModel;
import edu.kirkwood.finalproject.models.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserModel user = (UserModel) session.getAttribute("currentUser");


        ArrayList<ChannelModel> channelList = new ArrayList<ChannelModel>();
        channelList = ChannelDAO.ViewUserChannels(user.getUserID());

        req.setAttribute("language", user.getLanguage());
        req.setAttribute("channelList", channelList);
        req.getRequestDispatcher("WEB-INF/project/homepage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel user = (UserModel) req.getSession().getAttribute("currentUser");
        String formType = req.getParameter("formtype");
        String channelID;
        String password;
        Map<String, String> results = new HashMap<>();
        ArrayList<MessageModel> messageList = new ArrayList<MessageModel>();
        switch (formType) {
            case "join":
                try{
                    channelID = req.getParameter("joinchannelid");
                    password = req.getParameter("joinchannelpassword");
                    if(!ChannelDAO.ChannelLogin(user.getUserID(), channelID, HashIt(password))) {
                        results.put("ChannelError", "One or more inputs are incorrect.");
                    }
                } catch (Exception e) {
                    results.put("ChannelError", "One or more inputs are incorrect.");
                }
                break;

            case "create":
                try {
                    channelID = req.getParameter("createchannelid");
                    password = req.getParameter("createchannelpassword");
                    if(!ChannelDAO.ChannelCreate(user.getUserID(), channelID, HashIt(password))) {
                        results.put("ChannelError", "One or more inputs are incorrect.");
                    };
                } catch (Exception e) {
                    results.put("ChannelError", "A channel with that name already exists.");
                }
                break;

            case "view":
                try {
                    channelID = req.getParameter("channel");
                    messageList = MessageDAO.ViewChannelMessages(channelID);
                } catch (Exception e) {
                    results.put("ChannelError", "Not able to view channel");
                }
                break;

            case "leave":
                try {
                    channelID = req.getParameter("channel");
                    if(!ChannelDAO.ChannelLeave(user.getUserID(), channelID)) {
                        results.put("ChannelError", "Not able to leave channel");
                    }
                } catch (Exception e) {
                    results.put("ChannelError", "Not able to leave channel");
                }
                break;
            case "delete":
                try {
                    channelID = req.getParameter("channel");

                } catch (Exception e) {
                    results.put("ChannelError", "Not able to remove channel");
                }
                break;
        }


        if(formType != "view") {
            ArrayList<ChannelModel> channelList = new ArrayList<ChannelModel>();
            channelList = ChannelDAO.ViewUserChannels(user.getUserID());
            req.setAttribute("channelList", channelList);
        }else{
            req.setAttribute("messageList", messageList);
        }

        req.setAttribute("results", results);
        req.getRequestDispatcher("WEB-INF/project/homepage.jsp").forward(req, resp);
    }

    private String HashIt(String sufficientlyUnHashed){
        return Hashing.sha256()
                .hashString(sufficientlyUnHashed, StandardCharsets.UTF_8)
                .toString();
    }
}
