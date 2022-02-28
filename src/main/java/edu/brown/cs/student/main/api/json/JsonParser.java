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
//        GsonBuilder builder = new GsonBuilder();
//        Object o = builder.create().fromJson(jsonObject, Object.class);
//        Gson parser = new Gson();
//        String[] myMessage = parser.fromJson(jsonObject, String[].class);
//        System.out.println(myMessage);
        Gson parser = new Gson();
        String[] myMessages = parser.fromJson(jsonObject, String[].class);
        return Arrays.toString(myMessages);
//        System.out.println(Arrays.toString(myMessages));
//
//        Gson parser = new Gson();
//        String[] myMessages = parser.fromJson(jsonObject, String[].class);
//        return Arrays.toString(myMessages);

//        if (o.getClass().isArray()) {
//            Type collectionType = new TypeToken<Collection<String>>() {
//            }.getType();
//            Collection<String> enums = new Gson().fromJson(jsonObject, collectionType);
//            System.out.println(enums);
//        } else {
//            Gson parser = new Gson();
//            Message myMessage = parser.fromJson(jsonObject, Message.class);
//            System.out.println(myMessage.getMessage());
//        }

//        JsonParser p = new JsonParser();
//        JsonElement json = p.parse(jsonObject);
//        Gson gson = new Gson();
////        Message[] messages = GsonUtils.fromJsonAsArray(gson, json, Message.class, Message[].class);
//        Message[] messages = gson.fromJson(fromJsonAsArray(gson, json, Message.class, Message[].class), Message[].class);

        }
    }
