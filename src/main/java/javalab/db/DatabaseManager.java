package javalab.db;

import java.sql.*;
import javalab.model.Team;

public class DatabaseManager {

    public int checkmaxTeamID() {
        int id = 0;
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select max(id) from Team");
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        return id;
    }

    public int checkmaxPlayerID() {
        int id = 0;
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select max(id) from Player");
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        return id;
    }

    public void createTables() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }

        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE Team "
                    + "(id INTEGER NOT NULL PRIMARY KEY, teamName VARCHAR(50))");
            System.out.println("Table created");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }

        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE Player "
                    + "(id INTEGER NOT NULL PRIMARY KEY, playerPosition INTEGER, playerName VARCHAR(50), playerStrength INTEGER,"
                    + "teamId INTEGER REFERENCES Team(id))");
            System.out.println("Table created");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    public void insertData() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }

        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            Statement statement = con.createStatement();

            statement.executeUpdate("INSERT INTO Team VALUES (1, 'SKT1')");
            statement.executeUpdate("INSERT INTO Team VALUES (2, 'Misfits')");
            statement.executeUpdate("INSERT INTO Team VALUES (3, 'G2')");
            statement.executeUpdate("INSERT INTO Team VALUES (4, 'Fnatic')");
            statement.executeUpdate("INSERT INTO Team VALUES (5, 'Team Liquid')");
            System.out.println("Data inserted");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }

        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            Statement statement = con.createStatement();
            int counter = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    String query = String.format("%s, %s, '%s', %s, %s", counter, j, "Player" + (j + 1), j + i, i + 1);
                    statement.executeUpdate("INSERT INTO Player VALUES (" + query + ")");
                    counter++;
                }
            }
            System.out.println("Data inserted");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    public void insertTeam(String teamName) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }

        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO Team VALUES (" + (checkmaxTeamID() + 1) + ", '" + teamName + "'  )");
            System.out.println("Data inserted");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    public void insertPlayer(int position, String playerName, int strength, int teamID) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }

        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            Statement statement = con.createStatement();
            String query = String.format("%s, %s, '%s', %s, %s", (checkmaxPlayerID() + 1), position, playerName, strength, teamID);
            statement.executeUpdate("INSERT INTO Player VALUES (" + query + ")");
            System.out.println("Data inserted");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    public void deleteTeam(String teamName) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app")) {
            Statement statement = con.createStatement();
            int numberOfDeletedRows = 0;
            ResultSet rs = statement.executeQuery("SELECT * FROM Team WHERE teamName = '" + teamName + "'");
            while (rs.next()) {
                int idToDelete = rs.getInt("id");
                numberOfDeletedRows += statement.executeUpdate("DELETE FROM Player WHERE teamID = " + idToDelete + "");
                numberOfDeletedRows += statement.executeUpdate("DELETE FROM Team WHERE ID = " + idToDelete + "");
            }
            rs.close();
            System.out.println("Data removed: " + numberOfDeletedRows + " rows");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
}
