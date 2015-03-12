package edu.ucsb.cs;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by sean on 3/4/15.
 */
public class Main {

    public static void main(String[] args) {
        //initDb("healthmessagesexchange2", "HealthInformationSystem", "messages");
        //initDb("healthmessagesexchange3", "HealthInformationSystem", "messages");
        //initDb("healthmessagesexchange3", "HealthInformationSystem", "messages2");
        displayMenu();
    }

    public static void displayMenu() {

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
            return;
        }
    }

    public static void displayAdminMenu() {
        System.out.println("1: View number of patients for each type of allergy.");
        System.out.println("2: List patients with more than one allergy.");
        System.out.println("3: List patients who have a plan for surgery today.");
        System.out.println("4: View authors with more than one patient.");
        System.out.println("0: Exit");
    }

    public static void displayDoctorMenu() {
        System.out.println("1: View patient medical record.");
        System.out.println("2: View authors assigned to patient.");
        System.out.println("3: View patient guardian information.");
        System.out.println("4: View patient plans.");
        System.out.println("5: View patient allergies.");
        System.out.println("6: View patient lab test reports.");
        System.out.println("7: Edit patient plan.");
        System.out.println("8: Edit patient allergy information.");
        System.out.println("0: Exit");
    }

    public static void displayPatientMenu() {
        System.out.println("1: View my medical record.");
        System.out.println("2: View my author's information.");
        System.out.println("3: View my guardian's information.");
        System.out.println("4: View my allergies.");
        System.out.println("5: View my lab test reports.");
        System.out.println("6: View my medical plans.");
        System.out.println("7: Edit my information.");
        System.out.println("8: Edit my guardian's information.");
        System.out.println("0: Exit");
    }

    // Get the current date and time for whatever needs it
    public static String getDate() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        return date;
    }

    public static Patient getPatientObject(Connection db, String patientId) {
        try {
            int pid = Integer.parseInt(patientId);
            Statement statement = db.createStatement();
            ResultSet rSet = statement.executeQuery("SELECT * FROM Patients WHERE patientId=" + patientId);

            rSet.next();
            Patient p = new Patient(pid, rSet.getString("PatientRole"), rSet.getString("GivenName"),
                    rSet.getString("FamilyName"), rSet.getString("Suffix"), rSet.getString("Gender"),
                    rSet.getString("Birthtime"), rSet.getString("ProviderId"), rSet.getString("xmlHealthCreation"),
                    rSet.getInt("PayerId"));
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Plan getPlanObject(Connection db, String planId) {
        try {
            Statement statement = db.createStatement();
            ResultSet rSet = statement.executeQuery("SELECT * FROM Plans WHERE PlanId=" + planId);

            rSet.next();
            Plan p = new Plan(Integer.parseInt(planId), rSet.getString("Activity"), rSet.getString("CreatedOn"), rSet
                    .getString
                            ("ActivityTime"));
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void viewPatientInfo(Connection db, Patient p) {
        try {
            Statement statement = db.createStatement();
            ResultSet rSet = statement.executeQuery("SELECT * FROM Patients WHERE PatientId=" + p.getPatientId());
            rSet.next();

            System.out.println("First name: " + rSet.getString("GivenName"));
            System.out.println("Last name: " + rSet.getString("FamilyName"));
            System.out.println("Birth: " + rSet.getString("Birthtime"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void viewPlans(Connection db, String patientId) {
        int pid = Integer.parseInt(patientId);

        try {
            Statement statement = db.createStatement();
            ResultSet rSet = statement.executeQuery("SELECT * FROM Plans WHERE patientId=" + patientId);

            while (rSet.next()) {
                System.out.println("ID: " + rSet.getString("PlanId"));
                System.out.println("Activity: " + rSet.getString("Activity"));
                System.out.println("Activity time: " + rSet.getString("ActivityTime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewNumberPatientsAllergy(Connection db) {
        try {
            Statement statement = db.createStatement();

            Scanner scan = new Scanner(System.in);
            System.out.println("What substance?");
            String input = scan.next();
            String temp = "SELECT COUNT(Substance) FROM Allergies WHERE Substance='" + input +"';";
            ResultSet rSet = statement.executeQuery(temp);

            while(rSet.next()) {
                System.out.println(rSet.getString("COUNT(Substance)"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewPatientsMore1Allergy(Connection db) {
        try {
            Statement statement = db.createStatement();
            String temp = "SELECT PatientId FROM Patients WHERE PatientId IN (SELECT Patients.PatientId FROM Patients JOIN Allergies ON Patients.PatientId=Allergies.PatientId GROUP BY Patients.PatientId HAVING COUNT(Patients.PatientId) > 1);";
            ResultSet rSet = statement.executeQuery(temp);

            while(rSet.next()) {
                System.out.println(rSet.getString("PatientId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewPatientsSurgery(Connection db) {
        try {
            Statement statement = db.createStatement();
            String temp = "SELECT PlanId FROM Plans WHERE ActivityTime LIKE '3/12/2015%';";
            ResultSet rSet = statement.executeQuery(temp);

            while(rSet.next()) {
                System.out.println(rSet.getString("PlanId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewAuthorsMore1Patient(Connection db) {
        try {
            Statement statement = db.createStatement();
            String temp = "SELECT distinct AuthorId FROM AuthorsPatients WHERE AuthorId IN (SELECT AuthorId From AuthorsPatients GROUP BY AuthorId HAVING COUNT(AuthorId) > 1);";
            ResultSet rSet = statement.executeQuery(temp);

            while(rSet.next()) {
                System.out.println(rSet.getString("AuthorId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editPatientInfo(Connection db, Patient p) {

        try {
            Statement statement = db.createStatement();

            Scanner scan = new Scanner(System.in);
            System.out.println("Enter number of field you want to edit.");
            System.out.println("1: First name.");
            System.out.println("2: Last name.");
            System.out.println("3: Suffix.");
            String input = scan.next();

            if (input.equals("1")) {
                System.out.println("Enter first name: ");
                String name = scan.next();
                String update = "UPDATE Patients SET GivenName='" + name + "' WHERE PatientId=" + p.getPatientId();
                statement.executeUpdate(update);
            } else if (input.equals("2")) {
                System.out.println("Enter last name: ");
                String name = scan.next();
                String update = "UPDATE Patients SET FamilyName='" + name + "' WHERE PatientId=" + p.getPatientId();
                statement.executeUpdate(update);
            } else if (input.equals("3")) {
                System.out.println("Enter suffix: ");
                String suf = scan.next();
                String update = "UPDATE Patients SET Suffix='" + suf + "' WHERE PatientId=" + p.getPatientId();
                statement.executeUpdate(update);
            } else {
                System.out.println("Error");
                System.exit(0);
            }
            patientConsole();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editGuardianInfo(Connection db, Patient p) {

        try {
            Statement statement = db.createStatement();

            Scanner scan = new Scanner(System.in);
            System.out.println("Enter number of field you want to edit.");
            System.out.println("1: First name.");
            System.out.println("2: Last name.");
            System.out.println("3: Phone.");
            System.out.println("4: Address.");
            System.out.println("5: City.");
            System.out.println("6: State.");
            System.out.println("7: Zip.");
            System.out.println("8: Relationship.");
            String input = scan.next();

            if (input.equals("1")) {
                System.out.println("Enter first name: ");
                String name = scan.next();
                String update = "UPDATE Guardians SET GivenName='" + name + "' WHERE GuardianNo=" + p.getPatientRole();
                statement.executeUpdate(update);
            } else if (input.equals("2")) {
                System.out.println("Enter last name: ");
                String name = scan.next();
                String update = "UPDATE Guardians SET FamilyName='" + name + "' WHERE GuardianNo=" + p.getPatientRole();
                statement.executeUpdate(update);
            }
            // edit family name
            else if (input.equals("3")) {
                System.out.println("Enter phone: ");
                String phone = scan.next();
                String update = "UPDATE Guardians SET Phone='" + phone + "' WHERE PatientId=" + p.getPatientRole();
                statement.executeUpdate(update);
            }
            // edit given name
            else if (input.equals("4")) {
                System.out.println("Enter address: ");
                String temp = scan.next();
                String update = "UPDATE Guardians SET Address='" + temp + "' WHERE PatientId=" + p.getPatientRole();
                statement.executeUpdate(update);
            } else if (input.equals("5")) {
                System.out.println("Enter city: ");
                String temp = scan.next();
                String update = "UPDATE Guardians SET City='" + temp + "' WHERE PatientId=" + p.getPatientRole();
                statement.executeUpdate(update);
            } else if (input.equals("6")) {
                System.out.println("Enter state: ");
                String temp = scan.next();
                String update = "UPDATE Guardians SET State='" + temp + "' WHERE PatientId=" + p.getPatientRole();
                statement.executeUpdate(update);
            } else if (input.equals("7")) {
                System.out.println("Enter zip: ");
                String temp = scan.next();
                String update = "UPDATE Guardians SET Zip=" + temp + " WHERE PatientId=" + p.getPatientRole();
                statement.executeUpdate(update);
            } else if (input.equals("8")) {
                System.out.println("Enter relationship: ");
                String temp = scan.next();
                String update = "UPDATE Guardians SET Relationship='" + temp + "' WHERE PatientId=" + p.getPatientRole();
                statement.executeUpdate(update);
            } else {
                System.out.println("Error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editPatientPlan(Connection db, Patient p) {

        try {
            Statement statement = db.createStatement();

            Scanner scan = new Scanner(System.in);
            System.out.println("What is the plan id of the plan you would like to edit? ");
            String planId = scan.next();
            System.out.println("Enter number of field you want to edit.");
            System.out.println("1: Activity.");
            System.out.println("2: Activity time.");
            String input = scan.next();

            if (input.equals("1")) {
                System.out.println("Enter activity: ");
                String temp = scan.next();
                String update = "UPDATE Plans SET Activity='" + temp + "' WHERE PlanId=" + planId;
                statement.executeUpdate(update);
            } else if (input.equals("2")) {
                System.out.println("Enter activity time: ");
                String temp = scan.next();
                String update = "UPDATE Plans SET ActivityTime='" + temp + "' WHERE PlanId=" + planId;
                statement.executeUpdate(update);
            } else {
                System.out.println("Error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editPatientAllergy(Connection db, Patient p) {
        try {
            Statement statement = db.createStatement();

            Scanner scan = new Scanner(System.in);
            System.out.println("Enter number of field you want to edit.");
            System.out.println("1: Substance.");
            System.out.println("2: Reaction.");
            System.out.println("3: Status.");
            String input = scan.next();

            if (input.equals("1")) {
                System.out.println("Enter substance: ");
                String temp = scan.next();
                String update = "UPDATE Allergies SET Substance='" + temp + "' WHERE PatientId=" + p.getPatientId();
                statement.executeUpdate(update);
            }
            else if (input.equals("2")) {
                System.out.println("Enter reaction: ");
                String temp = scan.next();
                String update = "UPDATE Allergies SET Reaction='" + temp + "' WHERE PatientId=" + p.getPatientId();
                statement.executeUpdate(update);
            }
            else if (input.equals("3")) {
                System.out.println("Enter status: ");
                String temp = scan.next();
                String update = "UPDATE Allergies SET Status='" + temp + "' WHERE PatientId=" + p.getPatientId();
                statement.executeUpdate(update);
            }
            else {
                System.out.println("Error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read everything from the input file and put it in the database
    public static void initDb(String source, String dest, String table) {

        // Set some connection variables to null in case
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection destDb = null;
        int i =0;

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
            resultSet = statement.executeQuery("SELECT * FROM " + source + "." + table);

            // Connect to destination database
            destDb = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + dest +"?" + "user=root&password="
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

                // Insurance Company
                if(payerId != null) {
                    InsuranceCompany ic = new InsuranceCompany(Integer.parseInt(payerId), name, policyHolder, policyType,
                            purpose);

                    PreparedStatement icSt = destDb.prepareStatement(
                            "INSERT INTO InsuranceCompanies " + "(PayerId, Name, Purpose, PolicyType, PolicyHolder) " + "VALUES(?, ?, ?, ?, ?)"
                    );

                    icSt.setInt(1, ic.getPayerId());
                    icSt.setString(2, ic.getName());
                    icSt.setString(3, ic.getPurpose());
                    icSt.setString(4, ic.getPolicyType());
                    icSt.setString(5, ic.getPolicyHolder());

                    icSt.executeUpdate();
                }

                // Guardian
                if(guardianNo != null) {
                    Guardian guardian = new Guardian(Integer.parseInt(guardianNo), givenName, familyName, phone, address,
                            city, state, Integer.parseInt(zip), relationship);

                    PreparedStatement guardianSt = destDb.prepareStatement(
                            "INSERT INTO Guardians " + "(GuardianNo, GivenName, FamilyName, Phone, Address, City, State, Zip) "
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
                }

                // Patient
                if(givenName != null) {
                    int m_payerid = -10000;
                    if (payerId != null) {
                        m_payerid = Integer.parseInt(payerId);
                    }


                    Patient patient = new Patient(Integer.parseInt(patientId), guardianNo, givenName, familyName,
                            suffix, gender, birthTime, providerId, currentDate, m_payerid);

                    PreparedStatement patientSt = destDb.prepareStatement(
                            "INSERT INTO Patients " + "(PatientId, PatientRole, GivenName, FamilyName, Suffix, Gender, " +
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
                    if (patient.getPayerId() >= 0) {
                        patientSt.setInt(10, patient.getPayerId());
                    } else {
                        patientSt.setNull(10, java.sql.Types.NULL);
                    }
                    patientSt.executeUpdate();
                }


                // Author
                if(authorId != null) {
                    //Check if the author already exists
                    Author author = new Author(authorId, authorTitle, authorFirstName, authorLastName);

                    ResultSet exists;

                    statement  = destDb.createStatement();
                    exists = statement.executeQuery("select * from Authors where AuthorId='" + author.getAuthorId() +
                            "' LIMIT 1");


                    PreparedStatement authorSt;
                    if(!exists.next()) {

                        authorSt = destDb.prepareStatement(
                                "INSERT INTO Authors " + "(AuthorId, AuthorTitle, AuthorFirstName, AuthorLastName) " + "VALUES(?, ?, ?, ?)"
                        );

                        authorSt.setString(1, author.getAuthorId());
                        authorSt.setString(2, author.getAuthorTitle());
                        authorSt.setString(3, author.getAuthorFirstName());
                        authorSt.setString(4, author.getAuthorLastName());

                        authorSt.executeUpdate();
                    }


                    authorSt = destDb.prepareStatement(
                            "INSERT INTO AuthorsPatients (PatientId, AuthorId, ParticipatingRole) VALUES (?,?,?)"
                    );

                    authorSt.setInt(1, Integer.parseInt(patientId));
                    authorSt.setString(2, author.getAuthorId());
                    authorSt.setString(3, participatingRole);

                    authorSt.executeUpdate();


                }


                // Family History
                if(relativeId != null) {
                    FamilyHistory fam = new FamilyHistory(Integer.parseInt(relativeId), relation, Integer.parseInt
                            (age), diagnosis, Integer.parseInt(patientId));

                    PreparedStatement famSt = destDb.prepareStatement(
                            "INSERT INTO FamilyHistory " + "(id, Relationship, Age, Diagnosis, PatientId) " + "VALUES(?,?,?," +
                                    "?,?)"
                    );

                    famSt.setInt(1, fam.getRelativeId());
                    famSt.setString(2, fam.getRelationship());
                    famSt.setInt(3, fam.getAge());
                    famSt.setString(4, fam.getDiagnosis());
                    famSt.setInt(5, fam.getPatientId());

                    famSt.executeUpdate();
                }
                // Allergies
                Allergies alg = new Allergies(Integer.parseInt(patientId), substance, reaction, status);

                if(alg.getSubstance()  != null) {
                    ResultSet exists;

                    statement  = destDb.createStatement();
                    exists = statement.executeQuery("select * from Allergies where PatientId=" + alg.getPatientId() + " and Substance='" +
                          alg.getSubstance() + "'");

                    PreparedStatement algSt;

                    if(exists.next()) {
                        algSt = destDb.prepareStatement("Update Allergies SET PatientId=?,Substance=?,Reaction=?, Status=?" +
                                "where PatientId=" + alg.getPatientId() + " AND Substance='" + alg.getSubstance() + "'");
                    }
                    else {
                        algSt = destDb.prepareStatement(
                                "INSERT INTO Allergies " + "(PatientId, Substance, Reaction, Status) " + "VALUES(?,?,?,?)"
                        );
                    }

                    algSt.setInt(1, alg.getPatientId());
                    algSt.setString(2, alg.getSubstance());
                    algSt.setString(3, alg.getReaction());
                    algSt.setString(4, alg.getStatus());

                    algSt.executeUpdate();



                }

                // Lab Test Report

                if(labTestResultId != null) {
                    int trv = -10000;
                    int pvi = -10000;
                    if (testResultValue != null) {
                        trv = Integer.parseInt(testResultValue);
                    }
                    if(patientVisitId != null) {
                        pvi = Integer.parseInt(patientVisitId);
                    }

                    LabTestReport ltr = new LabTestReport(Integer.parseInt(labTestResultId), pvi, labTestPerformedDate,
                            labTestType, trv,
                            referenceRangeHigh, referenceRangeLow);

                    PreparedStatement ltrSt = destDb.prepareStatement(
                            "INSERT INTO LabTestReports " + "(LabTestResultId, PatientVisitId, LabTestPerformedDate, " +
                                    "LabTestType, TestResultValue, ReferenceRangeHigh, ReferenceRangeLow) " + "VALUES(?,?,?,?,?,?,?)"
                    );

                    ltrSt.setInt(1, ltr.getLabTestResultId());

                    if(ltr.getPatientVisitId()>=0) {
                        ltrSt.setInt(2, ltr.getPatientVisitId());
                    }
                    else {
                        ltrSt.setNull(2, java.sql.Types.NULL);
                    }
                    ltrSt.setString(3, ltr.getLabTestPerformedDate());
                    ltrSt.setString(4, ltr.getLabTestType());
                    if (ltr.getTestResultValue() >= 0) {
                        ltrSt.setInt(5, ltr.getTestResultValue());
                    } else {
                        ltrSt.setNull(5, java.sql.Types.NULL);
                    }
                    ltrSt.setString(6, ltr.getReferenceRangeHigh());
                    ltrSt.setString(7, ltr.getReferenceRangeLow());

                    ltrSt.executeUpdate();

                }

                // Plan
                if(planId != null) {
                    Plan plan = new Plan(Integer.parseInt(planId), activity, currentDate, scheduledDate);

                    PreparedStatement planSt = destDb.prepareStatement(
                            "INSERT INTO Plans " + "(PlanId, Activity, CreatedOn, ActivityTime, PatientId ) " + "VALUES(?,?,?,?,?)"
                    );

                    planSt.setInt(1, plan.getPlanId());
                    planSt.setString(2, plan.getActivity());
                    planSt.setString(3, plan.getDate());
                    planSt.setString(4, plan.getActivity_time());
                    planSt.setInt(5, Integer.parseInt(patientId));

                    planSt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void adminConsole() {
        try {
            Scanner con = new Scanner(System.in);

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = null;

            connection = DriverManager.getConnection("jdbc:mysql://localhost/HealthInformationSystem?user=root&password=");

            String input;
            System.out.println("What would you like to do? ");
            displayAdminMenu();
            input = con.next();
            //Patient p = getPatientObject(connection, patientId);
            if(input.equals("1")) {
                viewNumberPatientsAllergy(connection);
                adminConsole();
            }
            else if(input.equals("2")) {
                viewPatientsMore1Allergy(connection);
                adminConsole();
            }
            else if (input.equals("3")) {
                viewPatientsSurgery(connection);
                adminConsole();
            } else if (input.equals("4")) {
                viewAuthorsMore1Patient(connection);
                adminConsole();
            } else {
                System.out.println("Error");
                displayMenu();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void doctorConsole() {
        try {
            Scanner con = new Scanner(System.in);

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = null;

            connection = DriverManager.getConnection("jdbc:mysql://localhost/HealthInformationSystem?user=root&password=");

            String input;
            System.out.println("What would you like to do? ");
            displayDoctorMenu();
            input = con.next();
            System.out.println("What patient would you like to assess? Enter id.");
            String patientId = con.next();
            Patient p = getPatientObject(connection, patientId);
            if(input.equals("4")) {
                viewPlans(connection, patientId);
                doctorConsole();
            }
            else if(input.equals("7")) {
                editPatientPlan(connection, p);
                doctorConsole();
            }
            else if(input.equals("8")) {
                editPatientAllergy(connection, p);
                doctorConsole();
            }
            else {
                System.out.println("Error");
                displayMenu();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void patientConsole() {
        try {
            Scanner con = new Scanner(System.in);

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = null;

            connection = DriverManager.getConnection("jdbc:mysql://localhost/HealthInformationSystem?user=root&password=");

            System.out.println("USER: PATIENT");
            System.out.println("Enter user ID: ");
            String patientId;
            patientId = con.next();

            Patient p = getPatientObject(connection, patientId);

            String input;
            System.out.println("What would you like to do?");
            displayPatientMenu();

            input = con.next();
            if (input.equals("1")) {
                viewPatientInfo(connection, p);
                patientConsole();
            }
            else if(input.equals("7")) {
                editPatientInfo(connection, p);
                patientConsole();
            }
            else if(input.equals("8")) {
                editGuardianInfo(connection, p);
                patientConsole();
            }
            else {
                System.out.println("Error");
                displayMenu();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }
}
