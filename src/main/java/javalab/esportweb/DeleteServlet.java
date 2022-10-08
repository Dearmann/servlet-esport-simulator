/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package javalab.esportweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javalab.db.DatabaseManager;
import javalab.model.Player;
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
@WebServlet(name = "DeleteServlet", urlPatterns = {"/Delete"})
public class DeleteServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Delete Team</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form method='post'>");
            out.println("<h1>Select team to delete:</h1>");
            StringBuilder htmlTeams = new StringBuilder();
            int teamCounter = 0;
            for (Team team : teamList) {
                htmlTeams.append("<input type='radio' name='delete").append("'")
                        .append(" value='").append(teamCounter).append("'>");
                htmlTeams.append("<label>").append(team.getTeamName()).append("</label>");
                htmlTeams.append("<ul>");
                for (Player player : team.getTeamPlayers()) {
                    htmlTeams.append("<li>");
                    htmlTeams.append(player.getPlayerName());
                    htmlTeams.append(" - ");
                    htmlTeams.append(player.getPlayerStrength());
                    htmlTeams.append(" STR");
                    htmlTeams.append("</li>");
                }
                htmlTeams.append("</ul><br>");
                teamCounter++;
            }
            out.println(htmlTeams.toString());
            out.println("<input type='submit' value='Delete team' style='margin: 15px 0;'>");
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
        if (request.getParameter("delete") == null) {
//            response.sendRedirect("/EsportWeb");
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Delete Team</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<form method='post'>");
                out.println("<h1>Team not selected!</h1>");
                out.println("<h1>Select team to delete:</h1>");
                StringBuilder htmlTeams = new StringBuilder();
                int teamCounter = 0;
                for (Team team : teamList) {
                    htmlTeams.append("<input type='radio' name='delete").append("'")
                            .append(" value='").append(teamCounter).append("'>");
                    htmlTeams.append("<label>").append(team.getTeamName()).append("</label>");
                    htmlTeams.append("<ul>");
                    for (Player player : team.getTeamPlayers()) {
                        htmlTeams.append("<li>");
                        htmlTeams.append(player.getPlayerName());
                        htmlTeams.append(" - ");
                        htmlTeams.append(player.getPlayerStrength());
                        htmlTeams.append(" STR");
                        htmlTeams.append("</li>");
                    }
                    htmlTeams.append("</ul><br>");
                    teamCounter++;
                }
                out.println(htmlTeams.toString());
                out.println("<input type='submit' value='Delete team' style='margin: 15px 0;'>");
                out.println("</form>");
                out.println("<a href='/EsportWeb'><button>Cancel</button></a>");
                out.println("</body>");
                out.println("</html>");
            }
            return;
        }
        int indexToDelete = Integer.parseInt(request.getParameter("delete"));
        DatabaseManager database = new DatabaseManager();
        database.deleteTeam(teamList.get(indexToDelete).getTeamName());
        teamList.remove(indexToDelete);
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
