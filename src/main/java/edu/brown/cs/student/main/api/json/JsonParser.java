package edu.brown.cs.student.main.api.json;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

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
    }
