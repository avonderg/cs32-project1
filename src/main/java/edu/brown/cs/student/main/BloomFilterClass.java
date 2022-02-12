package edu.brown.cs.student.main;
import java.util.Arrays;

public class BloomFilterClass<T> implements BloomFilter {
    private int r; // false positive rate
    private int n; // maximum size
    private byte[] values;
//    public BloomHashes hash;

    public BloomFilterClass(int r, int n, byte[] values) {
        this.r = r;
        this.n = n;
        this.values = values;
//        this.hash = hash;
    }

    public int getR() {
        return this.r;
    }

    public int getN() {
        return this.n;
    }

    public byte[] getValues() {
        return this.values;
    }

//    public BloomHashes getHash() {
//        return this.hash;
//    }

    @Override
    public String createBf(int r, int n) {
        byte[] arr = new byte[n]; // initializes it to all zeros
        BloomFilterClass bloom = new BloomFilterClass(r, n, arr);
        BloomHashes hash = new BloomHashes();
        return hash.bytesToHex(bloom.getValues());
    }

    @Override
    public String insertBf(Object value) {
        return null;
    }

    @Override
    public String queryBf(Object value) {
        return null;
    }

}
