package edu.brown.cs.student.main.bloomTest;

import edu.brown.cs.student.main.bloom.BloomCommand;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BloomCommandTest {
    /**
     * Method to test the checkCommand method in the BloomCommand class
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws IllegalAccessException
     */
    @Test
    public void testCheckCommand() throws NoSuchAlgorithmException, IOException, IllegalAccessException {
        List<String> tokens1 = new ArrayList<String>();
        tokens1.add("create_bf");
        tokens1.add("0.1");
        tokens1.add("5");
        BloomCommand bloom1 = new BloomCommand();
        assertEquals(bloom1.checkCommand(tokens1), "");
        BloomCommand bloom2 = new BloomCommand();
        List<String> tokens2 = new ArrayList<String>();
        tokens2.add("create_bf");
        assertEquals(bloom2.checkCommand(tokens2), null);
        List<String> tokens3 = new ArrayList<String>();
        tokens3.add("insert_bf");
        tokens3.add("alex");
        assertEquals(bloom1.checkCommand(tokens3), "");
        List<String> tokens4 = new ArrayList<String>();
        tokens4.add("query_bf");
        tokens4.add("alex");
        assertEquals(bloom1.checkCommand(tokens4), "");
        List<String> tokens5 = new ArrayList<String>();
        tokens5.add("load_bf");
        tokens5.add("data/project1/proj1_big.csv");
        assertEquals(bloom1.checkCommand(tokens5), "");
        List<String> tokens6 = new ArrayList<String>();
        tokens6.add("hello");
        tokens6.add("test");
        assertEquals(bloom1.checkCommand(tokens6), null);
        List<String> tokens7 = new ArrayList<String>();
    }
}
