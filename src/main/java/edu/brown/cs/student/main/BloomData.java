package edu.brown.cs.student.main;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

/**
 * Class for storing multiple bloom filters, each representing a student in the recommender system
 */
public class BloomData {
    private int k; // number of hash functions
    private HashMap<Integer, BloomFilter> studentBlooms = new HashMap<Integer, BloomFilter>();

    /**
     * Constructor for storing bloom data
     * @param k - number of hash functions
     * @param studentBlooms - hashmap mapping each student to their bloom filter
     */
    public BloomData(int k, HashMap<Integer, BloomFilter> studentBlooms ) {
        this.k = k;
        this.studentBlooms = studentBlooms;
    }


}
