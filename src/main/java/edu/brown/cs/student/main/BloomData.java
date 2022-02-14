package edu.brown.cs.student.main;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

public class BloomData {
    private int k; // number of hash functions
    private HashMap<Integer, BloomFilter> studentBlooms = new HashMap<Integer, BloomFilter>();

    public BloomData(int k, HashMap<Integer, BloomFilter> studentBlooms ) {
        this.k = k;
        this.studentBlooms = studentBlooms;
    }


}
