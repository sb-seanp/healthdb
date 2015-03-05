package edu.ucsb.cs;

import java.sql.*;

/**
 * Created by sean on 3/4/15.
 */
public class main {

    public void main(String[] args) {
        menu();
    }

    public void menu() {
        System.out.println("Welcome to the Health Information System");
        System.out.println("Who would you like to log in as? Please enter a number.");
        System.out.println("Administrator: 0");
        System.out.println("Doctor: 1");
        System.out.println("Patient: 2");
        System.out.println("Log in as: ");
    }

    public void readDb(String source, String dest, String table) {

        // Set some connection variables to null in case
        Connection connection = null;
        Statement statement = null;
        ResultSet rSet = null;
        Connection destDb = null;

        try {
            // Load driver for source database
            Class.forName("com.mysql.jdbc.Driver");

            // Connect to source database
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + source +"?" + "user=root&password="
            );

            // Something that allows us to query the database
            statement = connection.createStatement();

            // Get result of query
            rSet = statement.executeQuery("select * from " + source + "." + table);

            // Connect to destination database
            destDb = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + source +"?" + "user=root&password="
            );

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
