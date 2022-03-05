package edu.brown.cs.student.main.api.client;

import java.net.URI;
import java.net.http.HttpRequest;

/**
 * This class generates the HttpRequests that are then used to make requests from the ApiClient.
 */
public class ClientRequestGenerator {

    /**
     * This method gets a GET request that is restricted to api key holders only.
     *
     * @param requestUri the given uri request
     * @return an HttpRequest object for accessing the secured resource.
     */
    public static HttpRequest getSecuredGetRequest(String requestUri) {
        String apiKey = ClientAuth.getApiKey();
        return HttpRequest.newBuilder(URI.create(requestUri)).GET()
                .header("apikey.txt", apiKey).build();
    }


    /**
     * This method makes a GET request that is restricted to api key holders only.
     * Method referenced from: project-1-ihuang8-jjia6-mhe36
     * @param url the given uri request
     * @param params given input parameters
     * @return an HttpRequest object for accessing the secured resource.
     */
    // FORMAT: auth=<cslogin>;key=<key>
    public static HttpRequest makeGetRequest(String url, String params) {
        StringBuilder urlParams = new StringBuilder("?");
        String[] parameters = params.split(";"); // finds parameters
        for (String param : parameters) {
            urlParams.append(param);
            urlParams.append("&");
        }
        urlParams.deleteCharAt(urlParams.length() - 1);
        String reqUri = url + urlParams;
        return HttpRequest.newBuilder().uri(URI.create(reqUri)).build();
    }

    /**
     * This method gets a POST request that is restricted to api key holders only.
     *
     * Method referenced from: project-1-ihuang8-jjia6-mhe36
     * @param url the given uri request
     * @param params given input parameters
     * @return an HttpRequest object for accessing the introductory resource.
     */
    public static HttpRequest makePostRequest(String url, String params) {
        String[] parameters = params.split(";");
        String csLogin = null;
        String apiKey = null;
        for (String param : parameters) {
            String[] paramParsed = param.split("=");
            if (paramParsed[0].equals("key")) {
                apiKey = paramParsed[1];
            } else if (paramParsed[0].equals("auth")) {
                csLogin = paramParsed[1];
            }
        }
        return HttpRequest.newBuilder().uri(URI.create(url))
                .header("x-api-key", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString("{\"auth\":\"" + csLogin + "\"}"))
                .build();
    }
}