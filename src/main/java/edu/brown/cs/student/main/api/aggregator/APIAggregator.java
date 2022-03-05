package edu.brown.cs.student.main.api.aggregator;

import edu.brown.cs.student.main.api.client.*;
import edu.brown.cs.student.main.api.json.JsonParser;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for handling API aggregating. Loads student data from active endpoints, parses JSON strings, and stores data
 * accordingly
 */
public class APIAggregator {
    // global variables
    public List<String> tokens = new ArrayList<String>(); // contains active endpoints
    private static final ApiClient finalClient = new ApiClient(null); // api client
    private List<APIInfoStudents> infoStudents;
    private List<APIMatchStudents> matchStudents;

    /**
     * Constructor for APIAggregator class
     */
    public void APIAggregator() {
        this.infoStudents = new ArrayList<APIInfoStudents>(); // init list
        this.matchStudents = new ArrayList<APIMatchStudents>();
    }

    /**
     * Method called within ApiClient in order to handle aggregating. Loads data and calls
     * appropriate helpers
     * @param lineToParse - input JSON string returned from active get request
     */
    public List<? extends Object> loadData(String lineToParse) {
        List<String> items = parseActive(lineToParse); // parses JSON string
        if (items.get(0).contains("info")) { // if the JSON string contains info endpoints
            return getData("info");
        }
        else if (items.get(0).contains("match")) { // if the JSON string contains match endpoints
            return getData("match");
        }
        return null;
    }

    /**
     * Parses input JSON string, removing items stored within quotation marks
     * @param parse - JSON string to parse
     * @return - List<String> of parsed JSON tokens
     */
    private List<String> parseActive(String parse) {
        Pattern p = Pattern.compile("\"([^\"]*)\""); // regex to find matches on
        Matcher m = p.matcher(parse);
        while (m.find()) {
            tokens.add(m.group(1));
        }
        return tokens;
    }

    /**
     * Loops through all the active endpoints stored within the tokens array, makes get or post requests depending
     * on type specified by the user, and parses and stores student data.
     * @param type - input string specifying either a get or post request to be made
     * @return - List of either APIInfoStudents, or APIMatchStudents
     */
    private List<? extends Object> getData(String type) {
        String req = "";
        int determinant  = 0; // 1 if get request, 2 if post request
        if (type.equals("info")) {
            determinant = 1;
            req = "https://studentinfoapi.herokuapp.com";
        }
        else if (type.equals("match")) {
            determinant = 2;
            req = "https://studentmatchapi.herokuapp.com";
        }
        System.out.println("Students:");
        String baseString = "";
        for (String token : tokens) { // loops through tokens array
            HttpRequest activeRequest = null;
            HttpResponse<String> response = null;
            baseString = req; // reset URL
            baseString += token; // append correct token to the URL
            String auth = "Avonderg"; // may generalize in the future
            String apiKey = ClientAuth.getApiKey(); // to maintain key security
            if (determinant == 1) { // get request
                StringBuilder urlParams = new StringBuilder("?");
                String url = "";
                url += urlParams.append("auth=").append(auth).append("&key=").append(apiKey); // append correct parameters
                baseString += url; // appends appropriate auth and key info to the URL
                activeRequest = HttpRequest.newBuilder().uri(URI.create(baseString)).build(); // makes get requests, per token
                response = finalClient.makeRequest(activeRequest,3); // flag == 3 to prevent from active matches being printed
                if (response.statusCode() != 200) { // if the endpoint needs to be hit again
                    activeRequest = HttpRequest.newBuilder().uri(URI.create(baseString)).build(); // makes get requests, per token
                    response = finalClient.makeRequest(activeRequest,3); // flag == 3 to prevent from active matches being printed
                }
                infoStudents = JsonParser.storeInfo(response); // parses and stores APIInfoStudents in list
                for (APIInfoStudents m : infoStudents) { // prints out students
                    System.out.println(m.convertToString());
                }
            }
            else if (determinant == 2) { // post request
                activeRequest = HttpRequest.newBuilder().uri(URI.create(baseString))
                        .header("x-api-key", apiKey)
                        .POST(HttpRequest.BodyPublishers.ofString("{\"auth\":\"" + auth + "\"}"))
                        .build(); // makes post requests, per token
                response = finalClient.makeRequest(activeRequest,3); // flag == 3 to prevent from active matches being printed
                if (response.statusCode() != 200) { // if the endpoint needs to be hit again
                    activeRequest = HttpRequest.newBuilder().uri(URI.create(baseString))
                            .header("x-api-key", apiKey)
                            .POST(HttpRequest.BodyPublishers.ofString("{\"auth\":\"" + auth + "\"}"))
                            .build(); // makes post requests, per token
                    response = finalClient.makeRequest(activeRequest,3); // flag == 3 to prevent from active matches being printed
                }
                matchStudents = JsonParser.storeMatch(response);  // parses and stores APIMatchStudents in list
                for (APIMatchStudents m : matchStudents) { // prints out students
                    System.out.println(m.convertToString());
                }
            }
        }
        if (determinant == 1) {
            return infoStudents;
        }
        else if (determinant == 2) {
            return matchStudents;
        }
        return null;
    }

}
