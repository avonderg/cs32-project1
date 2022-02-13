package edu.brown.cs.student.main;

import java.security.NoSuchAlgorithmException;

public interface BloomFilter<T> {
//    long[] array = new long[];
//    int size;
//    List<ToIntFunction<T>> hashFunctions;

    public String createBf(int r, int n);

    public String insertBf(byte[] value) throws NoSuchAlgorithmException;

    public String queryBf(T value);
}
