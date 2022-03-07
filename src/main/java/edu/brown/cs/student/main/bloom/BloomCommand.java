package edu.brown.cs.student.main.bloom;

import edu.brown.cs.student.main.csvReader.Data;
import edu.brown.cs.student.main.csvReader.HashMapData;
import edu.brown.cs.student.main.csvReader.Reader;
import edu.brown.cs.student.main.Student;
import edu.brown.cs.student.main.repl.Command;

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
    public String checkCommand(List<String> tokens) {
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
                try {
                    bloom.insertBf(array); // inserts input value into bit set
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                bloom.getBloom().printSet(bloom.getBloom().getSet()); // prints out bloom filter
                return "";
            }
        }
        else if (tokens.get(0).equals("query_bf")) { // create bf command
            if (tokens.size() == 2) {
                byte[] array = tokens.get(1).getBytes();
                try {
                    bloom.queryBf(array); // queries for value
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return "";
            }
        }
        else if (tokens.get(0).equals("load_bf") || tokens.get(0).equals("similar_bf")) {
            if (tokens.get(0).equals("load_bf")) {
                if (tokens.size() != 2) {
                    System.out.println("ERROR: invalid arguments");
                    return null;
                }
                // in order to generalize for any type of object
                Data readerUse = new HashMapData();
                Reader studentReader = new Reader(readerUse);
                studentReader.loadData(new String[]{tokens.get(1)});

                // generalizing over
                students.clear();

                // fills hashmap student ID to student
                this.students = (HashMap<String, Student>) studentReader.getData().getData();

                // determinant used to specify which function to call
                try {
                    data.handleBlooms(1, this.students, tokens);
                }
                // catching exceptions
                catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (students.size() != 0) { // if there was no error reading the provided CSV file
                    System.out.println("Read " + this.students.size() + " students from " + tokens.get(1));
                    return "";
                }
                return null; // otherwise, if there was an error- return null
            }
            else if (tokens.get(0).equals("similar_bf")) {
                if (tokens.size() != 3) {
                    System.out.println("ERROR: invalid arguments");
                    return null;
                }
                try {
                    if (this.students.size() == 0) { // if load_bf has not yet been called
                        System.out.println("ERROR: no data loaded from CSV");
                        return null;
                    }
                    data.handleBlooms(2, this.students, tokens);
                    // determinant used to specify which function to call
                }
                // catching exceptions
                catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return "";
            }
        }
        return null;
    }

    private void checkCommandWithExceptions() {

    }


}
