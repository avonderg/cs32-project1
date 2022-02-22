package edu.brown.cs.student.main.bloom;

import edu.brown.cs.student.main.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

/**
 * Class for handling command-line inputs from users for interacting with bloom filter commands.
 */
public class BloomCommand implements Command {
    public BloomMaker bloom; // class containing the bloom filter create, insert, query commands
    public HashMap<String, Student> students = new HashMap<String, Student>();
    BloomData data = new BloomData(new HashMap<>(), new HashMap<>());

    /**
     * Constructor for the BloomCommand class
     */
    public BloomCommand() {
        this.bloom = new BloomMaker();
    }

    /**
     * Checks the command-line user input for the following commands: create_bf, insert_bf, query_bf, load_bf,
     * and similar_bf, and calls appropriate functions handling each.
     *
     * @param tokens - tokenized command line user input
     * @return - String indicating whether or not a command was found
     * @throws NoSuchAlgorithmException
     */
    @Override
    public String checkCommand(List<String> tokens) throws NoSuchAlgorithmException, IOException, IllegalAccessException {
        if (tokens.get(0).equals("create_bf")) { // create bf command
            if (tokens.size() == 3) {
                double posRate = Double.parseDouble(tokens.get(1));
                int size = Integer.parseInt(tokens.get(2));
                bloom.createBf(posRate, size); // creates bloom filter
                bloom.getBloom().printSet(bloom.getBloom().getSet()); // prints out bloom filter
                return "";
            }
        }
        else if (tokens.get(0).equals("insert_bf") ) { // create bf command
            if (tokens.size() == 2) {
                byte[] array = tokens.get(1).getBytes();
                bloom.insertBf(array); // inserts input value into bit set
                bloom.getBloom().printSet(bloom.getBloom().getSet()); // prints out bloom filter
                return "";
            }
        }
        else if (tokens.get(0).equals("query_bf")) { // create bf command
            if (tokens.size() == 2) {
                byte[] array = tokens.get(1).getBytes();
                bloom.queryBf(array); // queries for value
                return "";
            }
        }
        else if (tokens.get(0).equals("load_bf") || tokens.get(0).equals("similar_bf")) {
            if (tokens.get(0).equals("load_bf")) {
                // in order to generalize for any type of object
                HashMapData readerUse = new HashMapData();
                Reader studentReader = new Reader(readerUse);
                studentReader.loadData(tokens.get(1));
                // generalizing over
                students.clear();
                this.students = studentReader.getData().getData(); // fills hashmap student ID to student
                data.handleBlooms(1, this.students, tokens); // determinant used to specify which function to call
                System.out.println("Read " + this.students.size() + " students from " + tokens.get(1));
                return "";
            }
            else if (tokens.get(0).equals("similar_bf")) {
                data.handleBlooms(2, this.students, tokens); // determinant used to specify which function to call
                return "";
            }
        }
        System.out.println("ERROR: invalid arguments");
        return null;
    }
}
