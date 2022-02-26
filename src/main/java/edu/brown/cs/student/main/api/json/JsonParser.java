package edu.brown.cs.student.main.api.json;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

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
        Type collectionType = new TypeToken<Collection<String>>(){}.getType();
        Collection<String> enums = new Gson().fromJson(jsonObject, collectionType);
        System.out.println(enums);
    }
}