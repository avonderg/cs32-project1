package edu.brown.cs.student.main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigInteger;
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
     */
    public BloomData(HashMap<String, BloomFilter> studentBlooms, HashMap<String, Integer> similarities) {
        this.studentBlooms =  studentBlooms;
        this.similarities = similarities;
    }

    public HashMap<String, BloomFilter> getStudentBlooms() {
        return studentBlooms;
    }

    // 1 if load_bf, 2 if similar_bf
    public void handleBlooms(int determinant, HashMap<String, Student> students,  List<String> tokens) throws NoSuchAlgorithmException, IllegalAccessException {
        if (determinant == 1) {
            loadBf(students);
        }
        else if (determinant == 2) {
            similarBf(tokens);
        }
    }

    private void loadBf(HashMap<String, Student> students) throws NoSuchAlgorithmException, IllegalAccessException {
        Iterator<Map.Entry<String, Student>> it = students.entrySet().iterator();
        // iterating every set of entry in the HashMap.
        while (it.hasNext()) {
            Map.Entry<String, Student> set = (Map.Entry<String, Student>) it.next();
            String key = set.getKey();
            Student value = set.getValue();
            BloomFilter bloom = new BloomFilter(0.1,18,null,0,0);
            bloom.createBf(0.1,18); // create a bloomfilter for the student
            this.studentBlooms.put(key, fillBloom(value,bloom));
//            bloom.printSet(bloom.getSet());
        }

    }

    private BloomFilter fillBloom(Student student, BloomFilter bloom) throws NoSuchAlgorithmException, IllegalAccessException {
        Field[] fields = student.getClass().getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            String val = field.get(student).toString();
            byte [] studentInfo = val.getBytes();
            // done converting
            bloom.insertBf(studentInfo);
        }
        return bloom;
    }

    private void calculateScore(BitSet set, BloomFilter filter, String id) {
        int score = 0;
        for( int i = 0; i < filter.getBitSize(); i++) {
            if (set.get(i)) { // if current position is set to true
                score++;
            }
        }
        similarities.put(id, score);
    }

    private void findKNearest(int k) {
        if (k < 0) {
            System.out.println("ERROR: k must be non-negative");
            return;
        }
        // now, we can sort distances hashmap by using a list
        List<Integer> similaritiesSorted = new ArrayList<>(similarities.values());
        Collections.sort(similaritiesSorted); // sorts in ascending order
        // remove duplicates by creating a hashset
        Set<Integer> similaritiesNoDup = new LinkedHashSet<>(similaritiesSorted);
        // retrieve ids of the closest k stars
        int i = 0;
        for (Integer score : similaritiesNoDup) {
            if (i >= k) {
                break;
            }
            System.out.println(this.getKey(similarities, score));
            i++;
        }
    }

    public <K,V> K getKey(Map<K,V> map, V value) {
        ArrayList<K> keys = new ArrayList<K>();
        for (Map.Entry<K, V> val : map.entrySet()) {
            if (val.getValue().equals(value)) { // if key match is found
                keys.add(val.getKey());
            }
        }
        return keys.get((int) (Math.random() * keys.size())); // gets randomized result
    }

    private void similarBf(List<String> tokens) {
        if (tokens.size() != 3) {
            System.out.println("ERROR: invalid arguments");
            return;
        }
        int k = Integer.parseInt(tokens.get(1));
        String id = tokens.get(2);
        Iterator<Map.Entry<String, BloomFilter>> it = this.studentBlooms.entrySet().iterator();
        // iterating every set of entry in the HashMap.
        BloomFilter targetStudent = this.studentBlooms.get(id);
        BitSet setTarget = targetStudent.getSet();
        while (it.hasNext()) {
            Map.Entry<String, BloomFilter> set = (Map.Entry<String, BloomFilter>) it.next();
            String match = set.getKey();
            BloomFilter bloomCurr = set.getValue();
            setTarget.xor(bloomCurr.getSet()); // xor gate applied
            setTarget.flip(0,targetStudent.getBitSize()); // NOT operator applied to all bits
            if (match != id) {
                calculateScore(setTarget, targetStudent, match); // gets score for the non-target filters
            }
        }
        findKNearest(k);
        return;
    }

}
