package edu.brown.cs.student.main.bloom;

import edu.brown.cs.student.main.bloom.BloomFilter;
import edu.brown.cs.student.main.bloom.BloomHashes;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

/**
 * Class used for the basic user commands used to interact with a bloom filter
 */
public class BloomMaker {
    private BloomFilter bloom;

    /**
     * Constructor for the BloomMaker class
     */
    public BloomMaker() {
        this.bloom = new BloomFilter(0,0,null,0,0);
    }

    /**
     * Getter to retrieve the global bloom filter variable
     * @return bloom - bloom filter
     */
    public BloomFilter getBloom() {
        return this.bloom;
    }

    /**
     * Constructs an empty, generic Bloom filter given r, a desired false positive rate, and n,
     * the expected maximum number of elements the Bloom filter will contain
     *
     * @param r - desired false positive rate
     * @param n - expected maximum number of elements
     * @return - Bloom filter's bitset, converted to a string
     */
    public BloomFilter createBf(double r, int n) {
        BitSet set = new BitSet(n); // initializes it to all zeros
        bloom.setR(r);
        bloom.setN(n);
        bloom.setSet(set); // initializes bitset to all zeros
//        BloomFilter bloomFilter = new BloomFilter(r, n, set, 0,0);
        bloom.calculateSize(bloom); // changes bloom size and k, changes above fields
        return bloom;
    }

    /**
     * Inserts elements into a bloom filter's bitset
     *
     * @param value - element to be inserted
     * @return bitset of the bloom filter, converted to a string
     * @throws NoSuchAlgorithmException
     */
    public BloomFilter insertBf(byte[] value) throws NoSuchAlgorithmException {
        if (bloom.getSet() == null) { // error checking
            System.out.println("ERROR: cannot insert into null bloom filter");
            return null;
        }
        BloomHashes hash = new BloomHashes();
        int size = bloom.getBitSize();
        int k = bloom.getK();
        BigInteger[] hashFunctions = hash.createHashes(value, k); // res of hash functions
        for (BigInteger func: hashFunctions) {
            int val = func.mod(BigInteger.valueOf(size)).intValue(); // finds index in bitset
            bloom.getSet().set(val, true); // changes the bit appropriately
        }
        return bloom;
    }

    /**
     * Queries a Bloom filter to see whether it might contain a given element
     *
     * @param value - element to be searched for
     * @return - message indicating if element in set
     * @throws NoSuchAlgorithmException
     */
    public String queryBf(byte[] value) throws NoSuchAlgorithmException {
        BloomHashes hash = new BloomHashes();
        int size = bloom.getBitSize();
        int k = bloom.getK();
        if (bloom.getSet() == null) { // error checking
            System.out.println("ERROR: cannot query null bloom filter");
            return "error";
        }
        String ret = new String(value, StandardCharsets.UTF_8); // convert byte array to String
        BigInteger[] hashFunctions = hash.createHashes(value, k); // res of hash functions
        for (BigInteger func: hashFunctions) {
            int val = func.mod(BigInteger.valueOf(size)).intValue(); // index in bitset
            if (!bloom.getSet().get(val)) { // if value not in set
                System.out.println("\""+ret+ "\""+ " is definitely not in the set.");
                return "\""+ret+ "\"" + " is definitely not in the set.";
            }
        }
        System.out.println("\""+ret+ "\"" + " might be in the set.");
        return "\""+ret+ "\"" + " might be in the set.";
    }
}
