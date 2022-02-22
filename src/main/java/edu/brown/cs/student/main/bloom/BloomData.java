package edu.brown.cs.student.main.bloom;


import edu.brown.cs.student.main.Student;

import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Class for storing multiple bloom filters, each representing a student in the recommender system
 */
public class BloomData {
     private HashMap<String, BloomFilter> studentBlooms = new HashMap<>();
     private HashMap<String, Integer> similarities = new HashMap<>();


    /**
     * Constructor for storing bloom data
     * @param studentBlooms - hashmap mapping each student to their bloom filter
     * @param similarities - hashmap mapping each student to their bloom filter's cardinality
     */
    public BloomData(HashMap<String, BloomFilter> studentBlooms, HashMap<String, Integer> similarities) {
        this.studentBlooms =  studentBlooms;
        this.similarities = similarities;
    }


    /**
     * Caller function used for accessing private methods within Bloom Data class
     * @param determinant - if set to 1, loadBf() will be called, and if set to 2, similarBf() will be called
     * @param students - hashmap mapping each student to their IDs
     * @param tokens - tokenized command line user input
     * @throws NoSuchAlgorithmException
     * @throws IllegalAccessException
     */
    public int handleBlooms(int determinant, HashMap<String, Student> students, List<String> tokens) throws NoSuchAlgorithmException, IllegalAccessException {
        if (determinant == 1) { // if load_bf should be called
            loadBf(students);
            return 1;
        }
        else if (determinant == 2) { // if similar_bf should be called
            similarBf(tokens);
            return 2;
        }
        return 0;
    }

    public int attrSize(Student student) {
        Field[] fields = student.getClass().getDeclaredFields();
        int count = 0;
        for (Field field: fields) { // loops through all the fields in the input student object
            field.setAccessible(true); // sets field to accessible
            count++;
        }
        return count;
    }

    /**
     * Given a hashmap mapping students to their IDs, generates a unique bloom filter for each student based off of
     * their attributes
     * @param students - hashmap mapping each student to their IDs
     * @throws NoSuchAlgorithmException
     * @throws IllegalAccessException
     */
    private void loadBf(HashMap<String, Student> students) throws NoSuchAlgorithmException, IllegalAccessException {
        Iterator<Map.Entry<String, Student>> it = students.entrySet().iterator();
        // iterating over every set of entry in the HashMap
        while (it.hasNext()) {
            Map.Entry<String, Student> set = (Map.Entry<String, Student>) it.next();
            String key = set.getKey(); // gets current key
            Student value = set.getValue(); // gets current value
            BloomMaker bloom = new BloomMaker(); // creates an instance of the BloomMaker class
            bloom.createBf(0.1, attrSize(value)); // Creates a bloom filter for the student, where n is the number of attributes
            this.studentBlooms.put(key, fillBloom(value, bloom)); // maps bloom filter to student ID
        }
    }

    /**
     * Fills a bloom filter's bitset by looping through the attribute values of the current student object,
     * in order to generate a unique bitset.
     *
     * @param student - current student to create a bloom filter for
     * @param bloom - bloom filter whose bitset must be generate
     * @return - updated bloom filter with modified bitset
     * @throws NoSuchAlgorithmException
     * @throws IllegalAccessException
     */
    public BloomFilter fillBloom(Student student, BloomMaker bloom) throws NoSuchAlgorithmException, IllegalAccessException {
        Field[] fields = student.getClass().getDeclaredFields();
        for (Field field: fields) { // loops through all the fields in the input student object
            field.setAccessible(true); // sets field to accessible
            String val = field.get(student).toString();
            byte [] studentInfo = val.getBytes();
            // done converting
            bloom.insertBf(studentInfo); // inserts into bloom filter
        }
        return bloom.getBloom();
    }

    /**
     * Finds the k most similar bit sets (belonging to k students) to any given input student, based off of
     * their ID.
     *
     * @param tokens - tokenized command line user input
     */
    private void similarBf(List<String> tokens) {
        similarities.clear();
        SimilarBlooms similar = new SimilarBlooms(studentBlooms, similarities); // creates an instance of SimilarBlooms class
        // error checking
        if (tokens.size() != 3) {
            System.out.println("ERROR: invalid arguments");
            return;
        }
        else if (this.studentBlooms == null) {
            System.out.println("ERROR: no data has been loaded");
            return;
        }
        // done error checking
        int k = Integer.parseInt(tokens.get(1)); // parses for k
        String id = tokens.get(2); // parses for ID
        Iterator<Map.Entry<String, BloomFilter>> it = this.studentBlooms.entrySet().iterator();
        BloomFilter targetStudent = this.studentBlooms.get(id); // finds target student based off ID
        BitSet setTarget = targetStudent.getSet();
        BitwiseComparator comparator = new BitwiseComparator("xnor"); // pass in desired gate, as a String
        // iterating every set of entry in the HashMap.
        while (it.hasNext()) {
            Map.Entry<String, BloomFilter> set = (Map.Entry<String, BloomFilter>) it.next();
            String match = set.getKey(); // gets current student ID
            BloomFilter bloomCurr = set.getValue(); // gets current student bit set
            // applies selected bitwise gate (default set to XNOR)
            BitSet result = comparator.applyCalculation(comparator.comparator, setTarget, bloomCurr, targetStudent); //
            if (match != id) { // in order to prevent the input student being included in the results
                similarities.put(match, result.cardinality()); // maps cardinalities to student ID
            }
        }
        similar.caller(k); // calculates the k most similar bloom filter bit sets
        return;
    }

}
