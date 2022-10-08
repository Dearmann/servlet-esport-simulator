/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package javalab.esportweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javalab.exception.WrongPlayerPositionException;
import javalab.model.Team;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dominik Uszok
 */
@WebServlet(name = "EditServlet", urlPatterns = {"/Edit"})
public class EditServlet extends HttpServlet {

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
        ArrayList<Team> teamList = (ArrayList<Team>) request.getSession().getAttribute("teamList");
        int indexToEdit = (int) request.getSession().getAttribute("teamToEdit");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Edit Team</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Edit team:</h1>");
            out.println("<form method='POST'>");
            out.println("<label>Team name: </label>");
            out.println("<input type='text' placeholder='Team name' name='team' value='" + teamList.get(indexToEdit).getTeamName() + "'><br>");
            out.println("<label>Top: </label>");
            out.println("<input type='text' placeholder='Player name' name='player1' value='" + teamList.get(indexToEdit).getTeamPlayers().get(0).getPlayerName() + "'>");
            out.println("<input type='number' placeholder='Player strength' name='str1' value='" + teamList.get(indexToEdit).getTeamPlayers().get(0).getPlayerStrength() + "'><br>");
            out.println("<label>Jungle: </label>");
            out.println("<input type='text' placeholder='Player name' name='player2' value='" + teamList.get(indexToEdit).getTeamPlayers().get(1).getPlayerName() + "'>");
            out.println("<input type='number' placeholder='Player strength' name='str2' value='" + teamList.get(indexToEdit).getTeamPlayers().get(1).getPlayerStrength() + "'><br>");
            out.println("<label>Mid: </label>");
            out.println("<input type='text' placeholder='Player name' name='player3' value='" + teamList.get(indexToEdit).getTeamPlayers().get(2).getPlayerName() + "'>");
            out.println("<input type='number' placeholder='Player strength' name='str3' value='" + teamList.get(indexToEdit).getTeamPlayers().get(2).getPlayerStrength() + "'><br>");
            out.println("<label>Bot: </label>");
            out.println("<input type='text' placeholder='Player name' name='player4' value='" + teamList.get(indexToEdit).getTeamPlayers().get(3).getPlayerName() + "'>");
            out.println("<input type='number' placeholder='Player strength' name='str4' value='" + teamList.get(indexToEdit).getTeamPlayers().get(3).getPlayerStrength() + "'><br>");
            out.println("<label>Support: </label>");
            out.println("<input type='text' placeholder='Player name' name='player5' value='" + teamList.get(indexToEdit).getTeamPlayers().get(4).getPlayerName() + "'>");
            out.println("<input type='number' placeholder='Player strength' name='str5' value='" + teamList.get(indexToEdit).getTeamPlayers().get(4).getPlayerStrength() + "'><br>");
            out.println("<input type='submit' value='Edit team' style='margin: 15px 0;'>");
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
        int indexToEdit = (int) request.getSession().getAttribute("teamToEdit");

        Team editedTeam = new Team(request.getParameter("team").length() == 0 ? "Unnamed team" : request.getParameter("team"), 0);
        try {
            editedTeam.addPlayer(0,
                    request.getParameter("player1").length() == 0 ? "Player1" : request.getParameter("player1"),
                    request.getParameter("str1").length() == 0 ? 0 : Integer.parseInt(request.getParameter("str1")));
            editedTeam.addPlayer(1,
                    request.getParameter("player2").length() == 0 ? "Player2" : request.getParameter("player2"),
                    request.getParameter("str2").length() == 0 ? 0 : Integer.parseInt(request.getParameter("str2")));
            editedTeam.addPlayer(2,
                    request.getParameter("player3").length() == 0 ? "Player3" : request.getParameter("player3"),
                    request.getParameter("str3").length() == 0 ? 0 : Integer.parseInt(request.getParameter("str3")));
            editedTeam.addPlayer(3,
                    request.getParameter("player4").length() == 0 ? "Player4" : request.getParameter("player4"),
                    request.getParameter("str4").length() == 0 ? 0 : Integer.parseInt(request.getParameter("str4")));
            editedTeam.addPlayer(4,
                    request.getParameter("player5").length() == 0 ? "Player5" : request.getParameter("player5"),
                    request.getParameter("str5").length() == 0 ? 0 : Integer.parseInt(request.getParameter("str5")));
        } catch (WrongPlayerPositionException ex) {
            ex.getMessage();
        }

        teamList.set(indexToEdit, editedTeam);
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
