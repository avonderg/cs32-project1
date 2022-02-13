package edu.brown.cs.student.main;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.List;


public class BloomFilterClass implements BloomFilter {
    private double r; // false positive rate
    private int n; // maximum size
    private BitSet set;

    public BloomFilterClass(double r, int n, BitSet set) {
        this.r = r;
        this.n = n;
        this.set = set;
    }

    public double getR() {
        return this.r;
    }

    public int getN() {
        return this.n;
    }

    public BitSet getSet() {
        return this.set;
    }

    public void handleBloomArgs(List<String> tokens) throws NoSuchAlgorithmException {
        if (tokens.get(0).equals("create_bf")) { // create bf command
             double posRate = Double.parseDouble(tokens.get(1));
             int size = Integer.parseInt(tokens.get(1));
             createBf(posRate, size);
        }
        else if (tokens.get(0).equals("insert_bf")) { // create bf command
            byte[] array = tokens.get(1).getBytes();
            insertBf(array);
        }
        else if (tokens.get(0).equals("query_bf")) { // create bf command
            byte[] array = tokens.get(1).getBytes();
            queryBf(array);
        }

    }

    @Override
    public String createBf(double r, int n) {
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
