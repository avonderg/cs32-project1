package edu.brown.cs.student.main.api.core;

import edu.brown.cs.student.main.api.client.ApiClient;
import edu.brown.cs.student.main.api.client.ClientRequestGenerator;
import edu.brown.cs.student.main.repl.Command;

import java.util.List;

/**
 * Class for handling commands interacting with the project's API functionality
 */
public class APICommand implements Command {


    /**
     * Returns a String output indicating if tokens contain certain command. Returns null otherwise.
     *
     * @param tokens -- list of REPL input Strings
     * @return String output if command is found, null otherwise
     */
    @Override
    public String checkCommand(List<String> tokens) {
        ApiClient client = new ApiClient(); // creates a new client
        if (tokens.get(0).equals("active")) { // if the command is active
            if (tokens.size() != 2) { // error checking / handling
                System.out.println("ERROR: invalid arguments");
                return null;
            }
            String type = tokens.get(1); // parses for type
            String req = "";
            if (type.equals("info")) { // sets the req URL accordingly
                req = "https://studentinfoapi.herokuapp.com/get-active";
            }
            else if (type.equals("match")) {
                req = "https://studentmatchapi.herokuapp.com/get-active";
            }
            client.makeRequest(ClientRequestGenerator.getSecuredGetRequest(req), 0); // flag used to determine if aggregating should be done
            return "";
        }
        else if (tokens.get(0).equals("api")) { // if command is api
            if (tokens.size () < 3) { // error checking
                System.out.println("ERROR: invalid args");
                return null;
            }
            String method = tokens.get(1); // gets method
            String url = tokens.get(2); // gets url
            if (method.toLowerCase().equals("get")){ // depending on which request it is, makes post or get request
                client.makeRequest(ClientRequestGenerator.makeGetRequest(url,tokens.get(3)), 0);
            }
            else if (method.toLowerCase().equals("post")) {
                client.makeRequest(ClientRequestGenerator.makePostRequest(url, tokens.get(3)), 0);
            }
            else { // error checking
                System.out.println("ERROR: invalid method");
                return null;
            }
            return "";
        }
        else if (tokens.get(0).equals("api_aggregate")) { // if the command is to aggregate from api endpoints
            if (tokens.size () < 2) { // error checking
                System.out.println("ERROR: invalid args");
                return null;
            }
            String type = tokens.get(1); /// gets the type
            String req = "";
            if (type.equals("info")) { // sets req URL accordingly
                req = "https://studentinfoapi.herokuapp.com/get-active";
            }
            else if (type.equals("match")) {
                req = "https://studentmatchapi.herokuapp.com/get-active";
            }
            client.makeRequest(ClientRequestGenerator.getSecuredGetRequest(req), 1); // flag set to 1 so aggregating can be handled
            return "";
        }
        return null;
    }
}
