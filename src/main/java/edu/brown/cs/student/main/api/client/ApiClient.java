package edu.brown.cs.student.main.api.client;


import edu.brown.cs.student.main.api.aggregator.APIAggregator;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates the client request handling logic. It is agnostic of what kinds of requests are being made.
 * The exact request formatting is outsourced to ClientRequestGenerator.
 */
public class ApiClient {

    private HttpClient client;
    public List<? extends Object> students;

    public ApiClient(List<? extends Object> students) {
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2).connectTimeout(Duration.ofSeconds(60)).build();
        this.students = students;
    }

    /**
     *
     * @param req
     * @return a HttpResponse<String>, or null on failure
     */
    public HttpResponse<String> makeRequest(HttpRequest req, int flag) {
        HttpResponse<String> apiResponse = null;
        try {
            apiResponse = client.send(req, HttpResponse.BodyHandlers.ofString());
            if (flag == 1) {  // if we need to aggregate, flag is set to 1
                APIAggregator agg = new APIAggregator();
                String toParse = apiResponse.body();
                this.students = new ArrayList<>();
                this.students = agg.loadData(toParse); // loadData method is called, which controls aggregating
            }
            if (flag == 0) { // otherwise, print out api response body
                System.out.println(apiResponse.body());
            }
        } catch (IOException ioe) {
            System.out.println("An I/O error occurred when sending or receiving data.");
            System.out.println(ioe.getMessage());

        } catch (InterruptedException ie) {
            System.out.println("The operation was interrupted.");
            System.out.println(ie.getMessage());

        } catch (IllegalArgumentException iae) {
            System.out.println(
                    "The request argument was invalid. It must be built as specified by HttpRequest.Builder.");
            System.out.println(iae.getMessage());

        } catch (SecurityException se) {
            System.out.println("There was a security configuration error.");
            System.out.println(se.getMessage());
        }
        return apiResponse;
    }
}