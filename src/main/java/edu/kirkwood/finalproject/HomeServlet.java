package edu.kirkwood.finalproject;

import com.google.common.hash.Hashing;
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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
        UserModel user = (UserModel) req.getSession().getAttribute("activeUser");
        String formType = req.getParameter("formtype");
        String channelID;
        String password;
        String channelAction;
        Map<String, String> results = new HashMap<>();
        switch (formType) {
            case "join":
                try{
                    channelID = req.getParameter("joinchannelid");
                    password = req.getParameter("joinchannelpassword");
                    ChannelDAO.ChannelLogin(user.getUserID(), channelID, HashIt(password));
                } catch (Exception e) {
                    results.put("joinchannelerror", "One or more inputs are incorrect.");
                }
                break;

            case "create":
                try {
                    channelID = req.getParameter("createchannelid");
                    password = req.getParameter("createchannelpassword");
                    ChannelDAO.ChannelCreate(user.getUserID(), channelID, HashIt(password));
                } catch (Exception e) {
                    results.put("createchannelerror", "One or more inputs are incorrect.");
                }
                break;

            case "view":
                try {
                    channelAction = req.getParameter("channelaction");
                } catch (Exception e) {

                }
                break;

            case "leave":
                try {
                    channelAction = req.getParameter("channelaction");
                } catch (Exception e) {

                }
                break;
        }



        ArrayList<ChannelModel> channelList = new ArrayList<ChannelModel>();
        channelList = ChannelDAO.ViewUserChannels(user.getUserID());

        req.setAttribute("results", results);
        req.setAttribute("channelList", channelList);
        req.getRequestDispatcher("WEB-INF/project/homepage.jsp").forward(req, resp);
    }

    private String HashIt(String sufficientlyUnHashed){
        return Hashing.sha256()
                .hashString(sufficientlyUnHashed, StandardCharsets.UTF_8)
                .toString();
    }
}
