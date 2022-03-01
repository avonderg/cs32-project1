package edu.brown.cs.student.main.api.client;

import java.net.URI;
import java.net.http.HttpRequest;

/**
 * This class generates the HttpRequests that are then used to make requests from the ApiClient.
 */
public class ClientRequestGenerator {

//    /**
//     * The basic introductory GET request. Calls the server at the given URL.
//     *
//     * @return an HttpRequest object for accessing the introductory resource.
//     */
//    public static HttpRequest getIntroGetRequest(String reqUri) {
//        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(reqUri)).build();
//        return req;
//    }

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

//    /**
//     * This method gets a POST request that is restricted to api key holders only.
//     * Sources:
//     * https://docs.oracle.com/en/java/javase/11/docs/api/java.net.
//     * http/java/net/http/HttpRequest.html
//     * https://docs.oracle.com/en/java/javase/11/docs/api/java.net.
//     * http/java/net/http/HttpRequest.Builder.html
//     *
//     * @param requestUri the given uri request
//     * @param param      the parameter
//     * @return an HttpRequest object for accessing the introductory resource.
//     */
//    public static HttpRequest getSecuredPostRequest(String requestUri, String param) {
//        String apiKey = ClientAuth.getApiKey();
//        return HttpRequest.newBuilder(URI.create(requestUri)).GET().header("apikey.txt", apiKey)
//                .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"" + param + "\"}")).build();
//    }

    /**
     * This method makes a GET request that is restricted to api key holders only.
     * Method referenced from: project-1-ihuang8-jjia6-mhe36
     * @param url the given uri request
     * @param params given input parameters
     * @return an HttpRequest object for accessing the secured resource.
     */
    // FORMAT: key=<key>;auth=<cslogin>
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
        System.out.println(reqUri);
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