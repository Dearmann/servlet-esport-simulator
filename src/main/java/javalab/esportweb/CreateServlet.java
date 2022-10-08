/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package javalab.esportweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javalab.db.DatabaseManager;
import javalab.exception.WrongPlayerPositionException;
import javalab.model.Team;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dominik Uszok
 */
@WebServlet(name = "CreateTeamServlet", urlPatterns = {"/Create"})
public class CreateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Create Team</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Create team:</h1>");
            out.println("<form method='POST'>");
            out.println("<label>Team name: </label>");
            out.println("<input type='text' placeholder='Team name' name='team'><br>");
            out.println("<label>Top: </label>");
            out.println("<input type='text' placeholder='Player name' name='player1'>");
            out.println("<input type='number' placeholder='Player strength' name='str1'><br>");
            out.println("<label>Jungle: </label>");
            out.println("<input type='text' placeholder='Player name' name='player2'>");
            out.println("<input type='number' placeholder='Player strength' name='str2'><br>");
            out.println("<label>Mid: </label>");
            out.println("<input type='text' placeholder='Player name' name='player3'>");
            out.println("<input type='number' placeholder='Player strength' name='str3'><br>");
            out.println("<label>Bot: </label>");
            out.println("<input type='text' placeholder='Player name' name='player4'>");
            out.println("<input type='number' placeholder='Player strength' name='str4'><br>");
            out.println("<label>Support: </label>");
            out.println("<input type='text' placeholder='Player name' name='player5'>");
            out.println("<input type='number' placeholder='Player strength' name='str5'><br>");
            out.println("<input type='submit' value='Create team' style='margin: 15px 0;'>");
            out.println("</form>");
            out.println("<a href='/EsportWeb'><button>Cancel</button></a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Team> teamList = (ArrayList<Team>) request.getSession().getAttribute("teamList");
        
        Team newTeam = new Team(request.getParameter("team").length() == 0 ? "Unnamed team" : request.getParameter("team"), 0);
        DatabaseManager database = new DatabaseManager();
        database.insertTeam(newTeam.getTeamName());
        try {
            newTeam.addPlayer(0,
                    request.getParameter("player1").length() == 0 ? "Player1" : request.getParameter("player1"),
                    request.getParameter("str1").length() == 0 ? 0 : Integer.parseInt(request.getParameter("str1")));
            database.insertPlayer(0, newTeam.getTeamPlayers().get(0).getPlayerName(), newTeam.getTeamPlayers().get(0).getPlayerStrength(), database.checkmaxTeamID());
            newTeam.addPlayer(1,
                    request.getParameter("player2").length() == 0 ? "Player2" : request.getParameter("player2"),
                    request.getParameter("str2").length() == 0 ? 0 : Integer.parseInt(request.getParameter("str2")));
                        database.insertPlayer(1, newTeam.getTeamPlayers().get(1).getPlayerName(), newTeam.getTeamPlayers().get(1).getPlayerStrength(), database.checkmaxTeamID());
            newTeam.addPlayer(2,
                    request.getParameter("player3").length() == 0 ? "Player3" : request.getParameter("player3"),
                    request.getParameter("str3").length() == 0 ? 0 : Integer.parseInt(request.getParameter("str3")));
                        database.insertPlayer(2, newTeam.getTeamPlayers().get(2).getPlayerName(), newTeam.getTeamPlayers().get(2).getPlayerStrength(), database.checkmaxTeamID());
            newTeam.addPlayer(3,
                    request.getParameter("player4").length() == 0 ? "Player4" : request.getParameter("player4"),
                    request.getParameter("str4").length() == 0 ? 0 : Integer.parseInt(request.getParameter("str4")));
                        database.insertPlayer(3, newTeam.getTeamPlayers().get(3).getPlayerName(), newTeam.getTeamPlayers().get(3).getPlayerStrength(), database.checkmaxTeamID());
            newTeam.addPlayer(4,
                    request.getParameter("player5").length() == 0 ? "Player5" : request.getParameter("player5"),
                    request.getParameter("str5").length() == 0 ? 0 : Integer.parseInt(request.getParameter("str5")));
                        database.insertPlayer(4, newTeam.getTeamPlayers().get(4).getPlayerName(), newTeam.getTeamPlayers().get(4).getPlayerStrength(), database.checkmaxTeamID());
        } catch (WrongPlayerPositionException ex) {
            ex.getMessage();
        }
        
        teamList.add(newTeam);
        
        int newTeams;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("newTeams")) {
                    newTeams = Integer.parseInt(cookie.getValue());
                    newTeams++;
                    cookie.setValue("" + newTeams);
                    response.addCookie(cookie);
                }
            }
        }
        
        response.sendRedirect("/EsportWeb");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
