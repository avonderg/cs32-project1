package edu.brown.cs.student.main.repl;

import edu.brown.cs.student.main.api.client.APIInfoStudents;
import edu.brown.cs.student.main.api.client.APIMatchStudents;
import edu.brown.cs.student.main.api.client.ApiClient;
import edu.brown.cs.student.main.api.client.ClientRequestGenerator;
import edu.brown.cs.student.main.Student;
import edu.brown.cs.student.main.dbProxy.DatabaseProxy;
import edu.brown.cs.student.main.dbProxy.StudentFromDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ApiDBRecCommand implements Command {
    List<APIInfoStudents> infoStudents = new ArrayList<>();
    List<APIMatchStudents> matchStudents = new ArrayList<>();
    // maps attributes to type (qualitative or quantitative)
    HashMap<String, String> headers;
    Boolean api_loaded;
    Boolean db_loaded;

    // maps student IDs to hashmap of their attributes
    HashMap<String, Student> students;
    DatabaseProxy dataDB;
    Map<Integer, StudentFromDB> idToDBstudentMap;


    /**
     * Class constructor. Initializes global variables to new objects.
     */
    public ApiDBRecCommand() {
        this.headers = new HashMap<String, String>();
        this.students = new HashMap<String, Student>();
        this.idToDBstudentMap = new HashMap<>();
        this.api_loaded = false;
        this.db_loaded = false;
    }

    /**
     * Returns a String output if tokens contain certain command. Returns null otherwise.
     *
     * @param tokens -- list of REPL input Strings
     * @return String output if command is found, null otherwise
     */
    @Override
    public String checkCommand(List<String> tokens) {
        if (tokens.get(0).equals("load_api_students")) {
            return loadApiStudents(tokens);
        }
        else if (tokens.get(0).equals("load_db_students")) {
            return loadDbStudents(tokens);
        }
        else if (tokens.get(0).equals("api_db_recommend")) {
            return produceRecommendations(tokens);
        } else {
            return null;
        }
    }

    /**
     * Reads students from API using ApiClient.
     * @param tokens REPL input
     * @return REPL output
     */
    private String loadApiStudents(List<String> tokens) {
        String info_req = "https://studentinfoapi.herokuapp.com/get-active";
        String match_req = "https://studentmatchapi.herokuapp.com/get-active";
        ApiClient client1 = new ApiClient(null);
        ApiClient client2 = new ApiClient(null);
        client1.makeRequest(ClientRequestGenerator.getSecuredGetRequest(info_req), 1);
        client2.makeRequest(ClientRequestGenerator.getSecuredGetRequest(match_req), 1);
        this.infoStudents = (List<APIInfoStudents>) client1.students;
        this.matchStudents = (List<APIMatchStudents>) client2.students;
        return "Successfully loaded students from API.";
    }

    /**
     * Reads students from database proxy using DatabaseProxy object.
     * @param tokens REPL input
     * @return REPL output
     */
    private String loadDbStudents(List<String> tokens) {
        HashMap<String, String> tablePermissions = new HashMap<String, String>();
        tablePermissions.put("names", "R");
        tablePermissions.put("interests", "R");
        tablePermissions.put("skills", "R");
        tablePermissions.put("traits", "R");
        try {
            this.dataDB =
                new DatabaseProxy("data/recommendation_data/sql/data.sqlite3",
                    tablePermissions);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR: problem creating proxy.");
            e.printStackTrace();
        }
        // System.out.println("Successfully loaded data_tester.sqlite3");

        ResultSet studentFields = null;
        try {
            studentFields =
                this.dataDB.executeSQL("SELECT names.id,names.name " +
                    ",names.email,skills.skill FROM names JOIN skills ON names.id = skills.id");
        } catch (SQLException exception) {
            System.out.println("SQLException Thrown");
            exception.printStackTrace();
        }

        if (studentFields == null) {
            return null;
        }

        try {
            this.populateStudentObjects(studentFields);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            this.addTraits("strengths");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        try {
            this.addTraits("weaknesses");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        try {
            this.addInterests();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return "Read " + this.idToDBstudentMap.size()
            + " students from data.sqlite3";
    }

    /**
     * Produces recommendations using Recommend object.
     * @param tokens REPL input
     * @return REPL output
     */
    private String produceRecommendations(List<String> tokens) {
        if (infoStudents.size() <= 0 || matchStudents.size() <= 0 || idToDBstudentMap.size() <= 0) {
            // if no students are stored, return appropriate message
            return "No students found";
        }

        List<StudentFromDB> dbStudents =
            this.idToDBstudentMap.values().stream().collect(Collectors.toList());

        for (int i = 0; i < 60; i++) {
            StudentFromDB currDB = dbStudents.get(i);
            APIInfoStudents currInfo = infoStudents.get(i);
            APIMatchStudents currMatch = matchStudents.get(i);
            this.students.put(String.valueOf(currDB.getId()),
                new Student(currDB, currInfo, currMatch));
        }

        if (!students.containsKey(tokens.get(2))) { // if target student is not in hashmap,
            // return appropriate message
            return "Target student not found";
        }

        // initialize new recommender object and pass in k value, students hashmap,
        // and target student object
        Recommender recommender =
            new Recommender(tokens.get(1), students, tokens.get(2));

        // initialize output string as empty string
        String output = "";

        // add recommendation ids to the string
        Object[] recs = recommender.getRecommendations();
        for (Object r : recs) {
            String id = r.toString();
            output = output + id + "\n";
        }

        // return string with stored recommendation ids
        return output.trim();
    }

    /**
     * This helper retreives the interests for each student from data.sqlite3 and adds it to the
     * student object.
     * @throws SQLException
     */
    private void addInterests() throws SQLException {
        int i = 1;
        while (i <= this.idToDBstudentMap.size()) {
            StudentFromDB currStudent = this.idToDBstudentMap.get(i);
            ResultSet interestRS = null;
            try {
                interestRS = this.dataDB.executeSQL("SELECT interest FROM interests" +
                        " WHERE id='" + i + "'");
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            ArrayList<String> studentInterests = new ArrayList<String>();
            while (interestRS.next()) {
                String currInterest = interestRS.getString(1);
                studentInterests.add(currInterest);
            }

            currStudent.setInterests(studentInterests);
            i++;
        }
    }

    /**
     * This method retreives either or weaknesses for each student from the database using a sql commands
     * @param strengthsOrWeaknesses
     * @throws SQLException
     */
    private void addTraits(String strengthsOrWeaknesses) throws SQLException {
        //parse in the interests and the traits

        if (!(strengthsOrWeaknesses.equals("strengths") || strengthsOrWeaknesses.equals("weaknesses"))) {
            System.out.println("ERROR: String must be strengths or weaknesses");
            return;
        }
        int i = 1;
        while (i <= this.idToDBstudentMap.size()) {
            StudentFromDB currStudent = this.idToDBstudentMap.get(i);
            ResultSet traitRS = null;
            try {
                traitRS = this.dataDB.executeSQL("SELECT trait FROM traits WHERE lower(type_of_attribute) = '"
                        + strengthsOrWeaknesses + "' AND traits.id='" + i + "'");
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            ArrayList<String> studentTraits = new ArrayList<String>();
            while (traitRS.next()) {
                String currTrait = traitRS.getString(1);
                studentTraits.add(currTrait);
            }
            if (strengthsOrWeaknesses.equals("strengths")) {
                currStudent.setStrengths(studentTraits);
            } else {
                currStudent.setWeaknesses(studentTraits);
            }
            i++;
        }
    }

    /**
     * This helper creates students from the parsed input.
     * @param studentFields - the ResultSet of fields for the student from the SQL query in checkCommand().
     * @throws SQLException
     */
    private void populateStudentObjects(ResultSet studentFields) throws SQLException {
        while (studentFields.next()) {
            int id = studentFields.getInt(1);
            String name = studentFields.getString(2);
            String email = studentFields.getString(3);
            String skill = studentFields.getString(4);
            this.idToDBstudentMap.put(id, new StudentFromDB(id, name, email, skill));
        }
    }
}
