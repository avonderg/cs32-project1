package edu.brown.cs.student.main.api.client;

import edu.brown.cs.student.main.api.core.FileParser;

/**
 * This simple class is for reading the API Key from our secret file (NOT BE PUSHED TO GIT).
 */
public class ClientAuth {

    /**
     * Reads the API Key from the secret text file where it has been stored.
     *
     * @return a String of the api key.
     */
    public static String getApiKey() {
        FileParser parser = new FileParser("config/secret/apikey.txt");
        return parser.readNewLine();
    }
}