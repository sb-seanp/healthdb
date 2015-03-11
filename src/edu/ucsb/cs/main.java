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
        }
    }

    public void displayPatientMenu() {
        System.out.println("1: View my medical record.");
        System.out.println("2: View my author's information.");
        System.out.println("3: View my guardian's information.");
        System.out.println("4: View my allergies.");
        System.out.println("5: View my lab test reports.");
        System.out.println("6: View my medical plans.");
        System.out.println("7: Edit my information.");
        System.out.println("8: Edit my guardian's information.");
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
                String currentDate  = getDate();
                String suffix       = "";
                String gender       = "";
                String patientRole  = "";

                // ****Class it up then ship it out****

                // Patient
                Patient patient = new Patient(Integer.parseInt(patientId), patientRole, givenName, familyName,
                        suffix, gender, birthTime, providerId, currentDate, Integer.parseInt(payerId));

                PreparedStatement patientSt = destDb.prepareStatement(
                        "INSERT INTO Patient " + "(PatientId, PatientRole, GivenName, FamilyName, Suffix, Gender, " +
                                "Birthtime, ProviderId, xmlHealthCreation, PayerId) " + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );

                patientSt.setInt(1, patient.getPatientId());
                patientSt.setString(2, patient.getPatientRole());
                patientSt.setString(3, patient.getGivenName());
                patientSt.setString(4, patient.getFamilyName());
                patientSt.setString(5, patient.getSuffix());
                patientSt.setString(6, patient.getGender());
                patientSt.setString(7, patient.getBirthtime());
                patientSt.setString(8, patient.getProviderId());
                patientSt.setString(9, patient.getCreationDate());
                patientSt.setInt(10, patient.getPayerId());

                patientSt.executeUpdate();

                // Guardian
                Guardian guardian = new Guardian(Integer.parseInt(guardianNo), givenName, familyName, phone, address,
                        city, state, Integer.parseInt(zip), relationship);

                PreparedStatement guardianSt = destDb.prepareStatement(
                        "INSERT INTO Guardian " + "(GuardianNo, GivenName, FamilyName, Phone, Address, City, State, Zip) "
                                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
                );

                guardianSt.setInt(1, guardian.getGuardianNo());
                guardianSt.setString(2, guardian.getGivenName());
                guardianSt.setString(3, guardian.getFamilyName());
                guardianSt.setString(4, guardian.getPhone());
                guardianSt.setString(5, guardian.getAddress());
                guardianSt.setString(6, guardian.getCity());
                guardianSt.setString(7, guardian.getState());
                guardianSt.setInt(8, guardian.getZip());

                guardianSt.executeUpdate();

                // Author
                Author author = new Author(Integer.parseInt(authorId), authorTitle, authorFirstName, authorLastName,
                        participatingRole);

                PreparedStatement authorSt = destDb.prepareStatement(
                        "INSERT INTO Author " + "(AuthorId, AuthorTitle, AuthorFirstName, AuthorLastName) " + "VALUES(?, ?, ?, ?)"
                );

                authorSt.setInt(1, author.getAuthorId());
                authorSt.setString(2, author.getAuthorTitle());
                authorSt.setString(3, author.getAuthorFirstName());
                authorSt.setString(4, author.getAuthorLastName());

                authorSt.executeUpdate();

                // Insurance Company
                InsuranceCompany ic = new InsuranceCompany(Integer.parseInt(payerId), name, policyHolder, policyType,
                        purpose);

                PreparedStatement icSt = destDb.prepareStatement(
                        "INSERT INTO InsuranceCompany " + "(PayerId, Name, Purpose, PolicyType, PolicyHolder) " + "VALUES(?, ?, ?, ?, ?)"
                );

                icSt.setInt(1, ic.getPayerId());
                icSt.setString(2, ic.getName());
                icSt.setString(3, ic.getPurpose());
                icSt.setString(4, ic.getPolicyType());
                icSt.setString(5, ic.getPolicyHolder());

                icSt.executeUpdate();

                // Family History
                FamilyHistory fam = new FamilyHistory(Integer.parseInt(relativeId), relationship, Integer.parseInt
                        (age), diagnosis);

                PreparedStatement famSt = destDb.prepareStatement(
                        "INSERT INTO FamilyHistory " + "(id, Relationship, Age, Diagnosis) " + "VALUES(?,?,?," +
                                "?)"
                );

                famSt.setInt(1, fam.getRelativeId());
                famSt.setString(2, fam.getRelationship());
                famSt.setInt(3, fam.getAge());
                famSt.setString(4, fam.getDiagnosis());

                famSt.executeUpdate();

                // Allergies
                Allergies alg = new Allergies(Integer.parseInt(patientId), substance, reaction, status);

                PreparedStatement algSt = destDb.prepareStatement(
                        "INSERT INTO Allergies " + "(PatientId, Substance, Reaction, Status) " + "VALUES(?,?,?,?)"
                );

                algSt.setInt(1, alg.getPatientId());
                algSt.setString(2, alg.getSubstance());
                algSt.setString(3, alg.getReaction());
                algSt.setString(4, alg.getStatus());

                algSt.executeUpdate();

                // Lab Test Report
                LabTestReport ltr = new LabTestReport(Integer.parseInt(labTestResultId), Integer.parseInt
                        (patientVisitId), labTestPerformedDate, labTestType, Integer.parseInt(testResultValue),
                        referenceRangeHigh, referenceRangeLow);

                PreparedStatement ltrSt = destDb.prepareStatement(
                        "INSERT INTO LabTestReport " + "(LabTestResultId, PatientVisitId, LabTestPerformedDate, " +
                                "LabTestType, TestResultValue, ReferenceRangeHigh, ReferenceRangeLow) " + "VALUES(?,?,?,?,?,?,?)"
                );

                ltrSt.setInt(1, ltr.getLabTestResultId());
                ltrSt.setInt(2, ltr.getPatientVisitId());
                ltrSt.setString(3, ltr.getLabTestPerformedDate());
                ltrSt.setString(4, ltr.getLabTestType());
                ltrSt.setInt(5, ltr.getLabTestResultId());
                ltrSt.setString(6, ltr.getReferenceRangeHigh());
                ltrSt.setString(7, ltr.getReferenceRangeLow());

                ltrSt.executeUpdate();

                // Plan
                Plan plan = new Plan(Integer.parseInt(planId), activity, scheduledDate);

                PreparedStatement planSt = destDb.prepareStatement(
                        "INSERT INTO Plan " + "(PlanId, Activity, ScheduledDate) " + "VALUES(?,?,?)"
                );

                planSt.setInt(1, plan.getPlanId());
                planSt.setString(2, plan.getActivity());
                planSt.setString(3, plan.getDate());

                planSt.executeUpdate();
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
        try {
            Scanner con = new Scanner(System.in);

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = null;

            connection = DriverManager.getConnection("jdbc:mysql://localhost/HealthInformationSystem?user=root%password=");

            System.out.println("USER: PATIENT");
            System.out.println("Enter user ID: ");
            String patientId;
            patientId = con.next();

            String input;
            System.out.println("What would you like to do?");
            displayPatientMenu();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }
}
