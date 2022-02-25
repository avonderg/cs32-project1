package edu.brown.cs.student.main.api.json;

import com.google.gson.Gson;

/**
 * JSONParser class to learn about JSONs in Java.
 *
 */
public class JsonParser {

    /**
     * Extracts the message parameter from a JSON object and stores it into a Message object.
     * Then, it prints the stored message in the Message object.
     * @param jsonObject stores the message.
     */
    public static void printMessage(String jsonObject) {
        Gson parser = new Gson();
        Message myMessage = parser.fromJson(jsonObject, Message.class);
        System.out.println(myMessage.getMessage());
    }
}