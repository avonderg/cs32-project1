package edu.brown.cs.student.main.api;

import edu.brown.cs.student.main.api.client.ApiClient;
import edu.brown.cs.student.main.api.client.ClientRequestGenerator;
import edu.brown.cs.student.main.repl.Command;

import java.util.List;

public class APICommand implements Command {
    public ApiEndpoints apiEndpoints;
    public ApiRequest apiRequest;

    public APICommand() {
        this.apiEndpoints = new ApiEndpoints();
        this.apiRequest = new ApiRequest();
    }

    /**
     * Returns a String output if tokens contain certain command. Returns null otherwise.
     *
     * @param tokens -- list of REPL input Strings
     * @return String output if command is found, null otherwise
     */
    @Override
    public String checkCommand(List<String> tokens) {
        ApiClient client = new ApiClient();
        if (tokens.get(0).equals("active")) {
            System.out.println("entered active");
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
            client.makeRequest(ClientRequestGenerator.getIntroGetRequest(req));
            System.out.println("done");
        }
        else if (tokens.get(0).equals("api")) {
            String method = tokens.get(1);
            String url = tokens.get(2);
            String[] params = new String[10];
            int j = 0;
            for (int i = 3; i < tokens.size(); i++) {
                params[j] = tokens.get(i);
                j++;
            }
        }
        return null;
    }
}
