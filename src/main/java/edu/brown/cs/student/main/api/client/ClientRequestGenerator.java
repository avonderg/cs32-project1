package edu.brown.cs.student.main.api.client;

import java.net.URI;
import java.net.http.HttpRequest;

/**
 * This class generates the HttpRequests that are then used to make requests from the ApiClient.
 */
public class ClientRequestGenerator {

    /**
     * The basic introductory GET request. You should fill it out so it calls our server at the given URL.
     *
     * @return an HttpRequest object for accessing the introductory resource.
     */
    public static HttpRequest getIntroGetRequest(String reqUri) {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(reqUri)).build();
        return req;
    }
}