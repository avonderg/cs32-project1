package edu.brown.cs.student.main;
import java.util.BitSet;
import java.util.HashMap;
import java.util.stream.IntStream;
import java.lang.Math;

/**
 * BloomFilter class containing all the methods needed to create a new bloom filter
 */
public class BloomFilter {
    private double r; // false positive rate
    private int n; // maximum size
    private BitSet set;
    private int bitSize;
    private int k;
    public HashMap<String, Student> students = new HashMap<String, Student>();
    BloomData data = new BloomData(new HashMap<>(), new HashMap<>());

    public void setR(double r) {
        this.r = r;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setSet(BitSet set) {
        this.set = set;
    }

    public void setBitSize(int bitSize) {
        this.bitSize = bitSize;
    }

    public void setK(int k) {
        this.k = k;
    }

    /**
     * Bloom Filter Constructor
     * @param r - desired false positive rate
     * @param n - maximum number of elements
     * @param set - corresponding bitset for filter
     * @param bitSize - size of bitset
     * @param k - number of hash functions
     */
    public BloomFilter(double r, int n, BitSet set, int bitSize, int k) {
        this.r = r;
        this.n = n;
        this.set = set;
        this.bitSize = bitSize;
        this.k = k;
    }

    /**
     * Gets the false positive rate
     * @return r
     */
    public double getR() {
        return this.r;
    }

    /**
     * Gets the maximum number of elements
     * @return n
     */
    public int getN() {
        return this.n;
    }

    /**
     * Gets the bitset for a bloom filter
     * @return set
     */
    public BitSet getSet() {
        return this.set;
    }

    /**
     * Gets the size of the bitset for a bloom filter
     *
     * @return bitSize
     */
    public int getBitSize() {
        return this.bitSize;
    }

    /**
     * Gets the k number of hash functions
     * @return k
     */
    public int getK() {
        return this.k;
    }

    /**
     * Calculates the size, m, of a bloom filter's bitset
     * @param bloom - Bloom filter
     * @return m - size of bitset
     */
    public int calculateSize(BloomFilter bloom) {
        int n = bloom.getN();
        int k = calculateK(bloom);
        int m;
        double top = k * n;
        m = (int)(Math.ceil(top / Math.log(2)));
        this.bitSize = m;
        return m;
    }

    /**
     * Calculates the number of hash functions, k
     * @param bloom - Bloom filter
     * @return k - number of hash functions
     */
    public int calculateK(BloomFilter bloom) {
        double r = bloom.getR();
        int k;
        double frac = (Math.log(r) / Math.log(2));
        double mult = -1 * frac;
        k = (int)Math.ceil(mult);
        this.k = k;
        return k;
    }

    /**
     * Prints out the bitset of any given bloom filter
     * @param set - input bitset
     * @return bitset converted to string
     */
    public String printSet(BitSet set) {
        int nbits = this.getBitSize();
        final StringBuilder buffer = new StringBuilder(nbits);
        IntStream.range(0, nbits).mapToObj(i -> this.getSet().get(i) ? '1' : '0').forEach(buffer::append);
        System.out.println(buffer.toString());
        return buffer.toString();
    }

}
