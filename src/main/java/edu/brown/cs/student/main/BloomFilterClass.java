package edu.brown.cs.student.main;

public class BloomFilterClass<T> implements BloomFilter {
    public int r; // false positive rate
    public int n; // maximum size
    public BloomHashes hash;

    public BloomFilterClass(int r, int n, BloomHashes hash) {
        this.r = r;
        this.n = n;
        this.hash = hash;
    }

    public int getR() {
        return this.r;
    }

    public int getN() {
        return this.n;
    }

    public BloomHashes getHash() {
        return this.hash;
    }

    @Override
    public String createBf(Object value) {
        return null;
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
