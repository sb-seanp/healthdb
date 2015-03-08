package edu.ucsb.cs;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by sean on 3/4/15.
 */
public class main {

    public void main(String[] args) {
        displayMenu();
    }

    public void displayMenu() {

        // Set write to console and read from console
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to the Health Information System");
        System.out.println("Who would you like to log in as? Please enter a number or 0 to exit program.");
        System.out.println("Administrator: 1");
        System.out.println("Doctor: 2");
        System.out.println("Patient: 3");
        System.out.println("Log in as: ");

        // Select user
        String menuInput = in.next();
        if(menuInput.equals("0")) {
            System.out.println("Exiting HIS");
            return;
        }
        else if(menuInput.equals("1")) {
            adminConsole();
        }
        else if(menuInput.equals("2")) {
            doctorConsole();
        }
        else if(menuInput.equals("3")) {
            patientConsole();
        }
        else {
            System.out.println("Error; exiting");
            return;
        }
    }

    // Get the current date and time for whatever needs it
    public String getDate() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        return date;
    }

    // Read everything from the input file and put it in the database
    public void initDb(String source, String dest, String table) {

        // Set some connection variables to null in case
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
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
            resultSet = statement.executeQuery("select * from " + source + "." + table);

            // Connect to destination database
            destDb = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + source +"?" + "user=root&password="
            );

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Get every single input
        try {
            while(resultSet.next()) {

                // Get metadata
                String lastAccessed = resultSet.getString("Last_Accessed");

                // Get patient info
                String patientId    = resultSet.getString("patientId");
                String givenName    = resultSet.getString("GivenName");
                String familyName   = resultSet.getString("FamilyName");
                String birthTime    = resultSet.getString("BirthTime");
                String providerId   = resultSet.getString("providerId");

                // Get guardian info
                String guardianNo   = resultSet.getString("GuardianNo");
                String relationship = resultSet.getString("Relationship");
                String firstName    = resultSet.getString("FirstName");
                String lastName     = resultSet.getString("LastName");
                String phone        = resultSet.getString("phone");
                String address      = resultSet.getString("address");
                String city         = resultSet.getString("city");
                String state        = resultSet.getString("state");
                String zip          = resultSet.getString("zip");

                // Get author info
                String authorId             = resultSet.getString("AuthorId");
                String authorTitle          = resultSet.getString("AuthorTitle");
                String authorFirstName      = resultSet.getString("AuthorFirstName");
                String authorLastName       = resultSet.getString("AuthorLastName");
                String participatingRole    = resultSet.getString("ParticipatingRole");

                // Get insurance info
                String payerId      = resultSet.getString("PayerId");
                String name         = resultSet.getString("Name");
                String policyHolder = resultSet.getString("PolicyHolder");
                String policyType   = resultSet.getString("PolicyType");
                String purpose      = resultSet.getString("Purpose");

                // Get family history info
                String relativeId   = resultSet.getString("RelativeId");
                String relation     = resultSet.getString("Relation");
                String age          = resultSet.getString("age");

                // Get allergy info
                String diagnosis    = resultSet.getString("Diagnosis");
                String id           = resultSet.getString("Id");
                String substance    = resultSet.getString("Substance");
                String reaction     = resultSet.getString("Reaction");
                String status       = resultSet.getString("Status");

                // Get lab test info
                String labTestResultId      = resultSet.getString("LabTestResultId");
                String patientVisitId       = resultSet.getString("PatientVisitId");
                String labTestPerformedDate = resultSet.getString("LabTestPerformedDate");
                String labTestType          = resultSet.getString("LabTestType");
                String testResultValue      = resultSet.getString("TestResultValue");
                String referenceRangeHigh   = resultSet.getString("ReferenceRangeHigh");
                String referenceRangeLow    = resultSet.getString("ReferenceRangeLow");

                // Get plan info
                String planId           = resultSet.getString("PlanId");
                String activity         = resultSet.getString("Activity");
                String scheduledDate    = resultSet.getString("ScheduledDate");

                // Get missing info
                String currentDate = getDate();
                String suffix = "";
                String gender = "";
                String patientRole = "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void adminConsole() {
        return;
    }

    public void doctorConsole() {
        return;
    }

    public void patientConsole() {
        return;
    }
}
