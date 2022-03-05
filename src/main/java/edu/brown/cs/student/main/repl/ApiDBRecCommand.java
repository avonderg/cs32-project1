package edu.brown.cs.student.main.repl;

import edu.brown.cs.student.main.api.client.APIInfoStudents;
import edu.brown.cs.student.main.api.client.ApiClient;
import edu.brown.cs.student.main.api.client.ClientRequestGenerator;
import edu.brown.cs.student.main.api.core.APICommand;
import edu.brown.cs.student.main.csvReader.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApiDBRecCommand implements Command {
    List<APIInfoStudents> infoStudents = new ArrayList<>();
    List<APIInfoStudents> matchStudents = new ArrayList<>();
    // maps attributes to type (qualitative or quantitative)
    HashMap<String, String> headers;

    // maps student IDs to hashmap of their attributes
    HashMap<String, Student> students;


    /**
     * Class constructor. Initializes global variables to new objects.
     */
    public ApiDBRecCommand() {
        this.headers = new HashMap<String, String>();
        this.students = new HashMap<String, Student>();
    }

    /**
     * Returns a String output if tokens contain certain command. Returns null otherwise.
     *
     * @param tokens -- list of REPL input Strings
     * @return String output if command is found, null otherwise
     */
    @Override
    public String checkCommand(List<String> tokens) {
        if (tokens.get(0).equals("load_api")) {
            String info_req = "https://studentinfoapi.herokuapp.com/get-active";
            String match_req = "https://studentmatchapi.herokuapp.com/get-active";
            ApiClient client1 = new ApiClient(null);
            ApiClient client2 = new ApiClient(null);
            client1.makeRequest(ClientRequestGenerator.getSecuredGetRequest(info_req), 1);
            client2.makeRequest(ClientRequestGenerator.getSecuredGetRequest(match_req), 1);
            this.infoStudents = (List<APIInfoStudents>) client1.students;
            this.matchStudents = (List<APIInfoStudents>) client2.students;
        }
        else if (tokens.get(0).equals("load_db")) {
            // insert ben stuff
        }
        else if (tokens.get(0).equals("api_db_recommend")) {
            if (students.size()<=0) {
                // if no students are stored, return appropriate message
                return "No students found";
            } else if (!students.containsKey(tokens.get(2))) { // if target student is not in hashmap,
                // return appropriate message
                return "Target student not found";
            }

            // TODO: Iterate through API and DB Proxy Student Lists and store in global students hashmap.




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
        return null;
    }


}
