package edu.brown.cs.student.main;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.List;

public interface BloomFilter {

    public String createBf(double r, int n);

    public String insertBf(byte[] value) throws NoSuchAlgorithmException;

    public String queryBf(byte[] value) throws NoSuchAlgorithmException;

}