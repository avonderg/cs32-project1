package edu.brown.cs.student.main.api.json;

import com.google.gson.Gson;
import edu.brown.cs.student.main.api.client.APIInfoStudents;
import edu.brown.cs.student.main.api.client.APIMatchStudents;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

/**
 * JSONParser class to learn about JSONs in Java.
 *
 */
public class JsonParser {

    /**
     * Extracts the message parameter from a JSON object and stores it into a Message object.
     * Then, it prints the stored message in the Message object.
     *
     * @param jsonObject stores the message.
     */
    public static String printMessage(String jsonObject) {
        Gson parser = new Gson();
        String[] myMessages = parser.fromJson(jsonObject, String[].class);
        return Arrays.toString(myMessages);
        }

    /**
     * Method to parse and store all the student info extracted from a get request
      * @param apiResponse - HttpResponse containing JSON student data
     * @return - a list of APIInfoStudents
     */
    public static List<APIInfoStudents> storeInfo(HttpResponse<String> apiResponse) {
        Gson parser = new Gson();
        // need to check if status code is 200 (to prevent errors or exceptions)
        if (apiResponse.statusCode() == 200) {
            APIInfoStudents[] infoStudentsList = parser.fromJson(apiResponse.body(), APIInfoStudents[].class);
            return Arrays.asList(infoStudentsList);// returns parsed list of students
        }
        return new ArrayList<>();
    }



    /**
     * Method to parse and store all the student info extracted from a post request
     * @param apiResponse - HttpResponse containing JSON student data
     * @return - a list of APIMatchStudents
     */
    public static List<APIMatchStudents> storeMatch(HttpResponse<String> apiResponse) {
        Gson parser = new Gson();
        // need to check if status code is 200 (in order to prevent errors or exceptions)
        if (apiResponse.statusCode() == 200) {
            APIMatchStudents[] matchStudentsList = parser.fromJson(apiResponse.body(), APIMatchStudents[].class);
            return Arrays.asList(matchStudentsList); // returns parsed list of students
        }
        return new ArrayList<>();
    }

    }
