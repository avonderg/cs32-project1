package edu.brown.cs.student.main.bloom;

import java.util.*;

/**
 * Class for calculating similarities between student bloom filters
 */
public class SimilarBlooms {
    private HashMap<String, BloomFilter> studentBlooms = new HashMap<>();
    private HashMap<String, Integer> similarities = new HashMap<>();

    /**
     * Constructor for storing bloom data
     * @param studentBlooms - hashmap mapping each student to their bloom filter
     * @param similarities - hashmap mapping each student to their bloom filter's set cardinality
     */
    public SimilarBlooms(HashMap<String, BloomFilter> studentBlooms, HashMap<String, Integer> similarities) {
        this.studentBlooms =  studentBlooms;
        this.similarities = similarities;
    }

    /**
     * Allows access to private methods within this class
     * @param k - number of similar students to find
     * @return - 1 on success, -1 on failure
     */
    public ArrayList<String> caller(int k) {
        return findKNearest(k);
    }

    /**
     * Finds the k most similar bloom filters to any given input student, based off of the bloom filter's
     * bloom set cardinalities. K must be non-negative, otherwise, an error will be thrown.
     *
     * @param k - number of similar students to find
     * @return - (-1) on failure, 1 on success
     */
    private ArrayList<String> findKNearest(int k) {
        ArrayList<String> topK = new ArrayList<String>();

        if (k < 0) {
            System.out.println("ERROR: k must be non-negative");
            return null;
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
            topK.add(this.getKey(similarities, score));
            //System.out.println(this.getKey(similarities, score));
            i++;
        }
        return topK;
    }



    /**
     *  Given a map and value to find, returns the respective key of that value
     *
     * @param map - hashmap to search
     * @param value - value of key to find
     * @param <K> - type of key
     * @param <V> - type of value
     * @return - the matching key
     */
    public <K,V> K getKey(Map<K,V> map, V value) {
        ArrayList<K> keys = new ArrayList<K>();
        for (Map.Entry<K, V> val : map.entrySet()) {
            if (val.getValue().equals(value)) { // if key match is found
                keys.add(val.getKey());
            }
        }
        return keys.get((int) (Math.random() * keys.size())); // gets randomized result
    }

}
