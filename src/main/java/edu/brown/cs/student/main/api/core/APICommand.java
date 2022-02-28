package edu.brown.cs.student.main.api.core;

import edu.brown.cs.student.main.api.client.ApiClient;
import edu.brown.cs.student.main.api.client.ClientRequestGenerator;
import edu.brown.cs.student.main.api.json.JsonParser;
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
        ApiClient client = new ApiClient();
        if (tokens.get(0).equals("active")) {
            if (tokens.size() != 2) {
                System.out.println("ERROR: invalid arguments");
            }
            String type = tokens.get(1);
            String req = "";
            if (type.equals("info")) {
                req = "https://studentinfoapi.herokuapp.com/get-active";
            }
            else if (type.equals("match")) {
                req = "https://studentmatchapi.herokuapp.com/get-active";
            }
            client.makeRequest(ClientRequestGenerator.getSecuredGetRequest(req));
            return "";
        }
        else if (tokens.get(0).equals("api")) {
            String method = tokens.get(1);
            String url = tokens.get(2);
            if (method.toLowerCase().equals("get")){
//                client.makeRequest(ClientRequestGenerator.getSecuredGetRequest(url));
                if (tokens.size () < 3) {
                    System.out.println("ERROR: invalid args");
                    return null;
                }
                client.makeRequest(ClientRequestGenerator.makeGetRequest(url,tokens.get(3)));
            }
            else if (method.toLowerCase().equals("post")) {
                if (tokens.size () < 3) {
                    System.out.println("ERROR: invalid args");
                    return null;
                }
                client.makeRequest(ClientRequestGenerator.makePostRequest(url, tokens.get(3)));
            }
            else {
                System.out.println("ERROR: invalid method");
                return null;
            }
            return "";
        }
        return null;
    }
}
