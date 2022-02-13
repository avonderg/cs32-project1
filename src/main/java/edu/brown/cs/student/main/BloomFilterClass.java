package edu.brown.cs.student.main;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;


public class BloomFilterClass<T> implements BloomFilter {
    private int r; // false positive rate
    private int n; // maximum size
    private BitSet set;

    public BloomFilterClass(int r, int n, BitSet set) {
        this.r = r;
        this.n = n;
        this.set = set;
    }

    public int getR() {
        return this.r;
    }

    public int getN() {
        return this.n;
    }

    public BitSet getSet() {
        return this.set;
    }


    @Override
    public String createBf(int r, int n) {
        BitSet set = new BitSet(n); // initializes it to all zeros
        BloomFilterClass bloom = new BloomFilterClass(r, n, set);
        BloomHashes hash = new BloomHashes();
        return bloom.set.toString();
    }

    @Override
    public String insertBf(byte[] value) throws NoSuchAlgorithmException {
        BloomHashes hash = new BloomHashes();
        BigInteger[] hashFunctions = hash.createHashes(value, this.getN()); // res of hash functions
        for (BigInteger func: hashFunctions) {
            int val = func.mod(BigInteger.valueOf(this.set.length())).intValue(); // index in bitset
            this.set.set(val, true); // changes the bit
        }
        return this.set.toString();
    }

    @Override
    public String queryBf(byte[] value) throws NoSuchAlgorithmException {
        BloomHashes hash = new BloomHashes();
        BigInteger[] hashFunctions = hash.createHashes(value, this.getN()); // res of hash functions
        for (BigInteger func: hashFunctions) {
            int val = func.mod(BigInteger.valueOf(this.set.length())).intValue(); // index in bitset
            if (!this.set.get(val)) { // if value not in set
                return value.toString() + " is definitely not in the set.\n";
            }
        }
        return value.toString() + " might be in the set.";
    }

}
