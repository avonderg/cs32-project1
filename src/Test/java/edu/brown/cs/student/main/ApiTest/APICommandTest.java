package edu.brown.cs.student.main.ApiTest;
import edu.brown.cs.student.main.api.client.ApiClient;
import edu.brown.cs.student.main.api.client.ClientRequestGenerator;
import edu.brown.cs.student.main.api.core.APICommand;
import org.junit.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class APICommandTest {

    @Test
    public void testCheckCommand() {
        APICommand command = new APICommand();
        List<String> tokens1 = new ArrayList<String>();
        tokens1.add("failure!");
        assertEquals(null, command.checkCommand(tokens1));
        List<String> tokens2 = new ArrayList<String>();
        tokens2.add("active");
        tokens2.add("info");
        tokens2.add("alex");
        assertEquals(null, command.checkCommand(tokens2));
        List<String> tokens3 = new ArrayList<String>();
        tokens3.add("api");
        tokens3.add("get");
        assertEquals(null, command.checkCommand(tokens3));
        List<String> tokens4 = new ArrayList<String>();
        tokens4.add("api");
        tokens4.add("post");
        assertEquals(null, command.checkCommand(tokens4));
    }

    @Test
    public void testGetSecuredGetRequest() {
        ClientRequestGenerator client = new ClientRequestGenerator();
        HttpRequest activeInfo = ClientRequestGenerator.getSecuredGetRequest("https://studentinfoapi.herokuapp.com/get-active");
        HttpRequest activeMatch = ClientRequestGenerator.getSecuredGetRequest("https://studentmatchapi.herokuapp.com/get-active");
        HttpClient.Builder clientBuilder = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2).connectTimeout(Duration.ofSeconds(60));
        assertEquals(activeInfo, client.getSecuredGetRequest("https://studentinfoapi.herokuapp.com/get-active"));
        assertEquals(activeMatch, client.getSecuredGetRequest("https://studentmatchapi.herokuapp.com/get-active"));
    }

    @Test
    public void testMakeRequest() throws IOException, InterruptedException {
        ApiClient client = new ApiClient(null);

        HttpRequest activeInfo = ClientRequestGenerator.getSecuredGetRequest("https://studentinfoapi.herokuapp.com/get-active");
        HttpRequest activeMatch = ClientRequestGenerator.getSecuredGetRequest("https://studentmatchapi.herokuapp.com/get-active");
        HttpClient clientB = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2).connectTimeout(Duration.ofSeconds(60)).build();
        HttpResponse<String> apiResponseInfo = clientB.send(activeInfo, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> apiResponseMatch = clientB.send(activeMatch, HttpResponse.BodyHandlers.ofString());
        assertEquals(apiResponseInfo.body(), client.makeRequest(activeInfo, 0).body());
        assertEquals(apiResponseMatch.body(), client.makeRequest(activeMatch, 0).body());
    }



}
