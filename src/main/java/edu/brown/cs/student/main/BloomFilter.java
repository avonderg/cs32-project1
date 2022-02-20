package edu.brown.cs.student.main;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.lang.Math;

/**
 * BloomFilter class containing all the methods needed to create a new bloom filter
 */
public class BloomFilter implements Command {
    private double r; // false positive rate
    private int n; // maximum size
    private BitSet set;
    private int bitSize;
    private int k;
    public HashMap<String, Student> students = new HashMap<String, Student>();
    BloomData data = new BloomData(new HashMap<>(), new HashMap<>());

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
        double r = bloom.getR();
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

    /**
     * Constructs an empty, generic Bloom filter given r, a desired false positive rate, and n,
     * the expected maximum number of elements the Bloom filter will contain
     *
     * @param r - desired false positive rate
     * @param n - expected maximum number of elements
     * @return - Bloom filter's bitset, converted to a string
     */
    public void createBf(double r, int n) {
        BitSet set = new BitSet(n); // initializes it to all zeros
        this.r = r;
        this.n = n;
        this.set = set;
        BloomFilter bloom = new BloomFilter(r, n, set, 0,0);
        calculateSize(bloom); // changes bloom size and k, changes above fields
        BloomHashes hash = new BloomHashes();
//        studentBlooms.put(id, bloom); // insert into hashmap by student ID
//        String str = printSet(bloom.getSet());
//        return str;
    }

    /**
     * Inserts elements into a bloom filter's bitset
     *
     * @param value - element to be inserted
     * @return bitset of the bloom filter, converted to a string
     * @throws NoSuchAlgorithmException
     */
    public void insertBf(byte[] value) throws NoSuchAlgorithmException {
//        BloomFilter curr =  studentBlooms.get(id);
        if (this.getSet() == null) { // error checking
            System.out.println("ERROR: cannot insert into null bloom filter");
//            return "error";
            return;
        }
        BloomHashes hash = new BloomHashes();
        int size = this.getBitSize();
        int k = this.getK();
        BigInteger[] hashFunctions = hash.createHashes(value, k); // res of hash functions
        for (BigInteger func: hashFunctions) {
            int val = func.mod(BigInteger.valueOf(size)).intValue(); // index in bitset
            this.getSet().set(val, true); // changes the bit
        }
//        String str = printSet(this.getSet());
//        return str;
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
        int size = this.getBitSize();
        int k = this.getK();
        if (this.getSet() == null) { // error checking
            System.out.println("ERROR: cannot query null bloom filter");
            return "error";
        }
        String ret = new String(value, StandardCharsets.UTF_8);
        BigInteger[] hashFunctions = hash.createHashes(value, k); // res of hash functions
        for (BigInteger func: hashFunctions) {
            int val = func.mod(BigInteger.valueOf(size)).intValue(); // index in bitset
            if (!this.getSet().get(val)) { // if value not in set
                System.out.println(ret + " is definitely not in the set.");
                return ret + " is definitely not in the set.";
            }
        }
        System.out.println(ret + " might be in the set.");
        return ret + " might be in the set.";
    }

    /**
     * Checks the command-line user input for the following commands: create_bf, insert_bf, query_bf, load_bf,
     * and similar_bf, and calls appropriate functions handling each.
     *
     * @param tokens - tokenized command line user input
     * @return - String indicating whether or not a command was found
     * @throws NoSuchAlgorithmException
     */
    @Override
    public String checkCommand(List<String> tokens) throws NoSuchAlgorithmException, IOException, IllegalAccessException {
        if (tokens.get(0).equals("create_bf")) { // create bf command
            if (tokens.size() == 3) {
                double posRate = Double.parseDouble(tokens.get(1));
                int size = Integer.parseInt(tokens.get(2));
                createBf(posRate, size);
                printSet(this.getSet());
                return "found";
            }
        }
        else if (tokens.get(0).equals("insert_bf") ) { // create bf command
            if (tokens.size() == 2) {
                byte[] array = tokens.get(1).getBytes();
                insertBf(array);
                printSet(this.getSet());
                return "found";
            }
        }
        else if (tokens.get(0).equals("query_bf")) { // create bf command
            if (tokens.size() == 2) {
                byte[] array = tokens.get(1).getBytes();
                queryBf(array);
                return "found";
            }
        }
        else if (tokens.get(0).equals("load_bf") || tokens.get(0).equals("similar_bf")) {
            if (tokens.get(0).equals("load_bf")) {
//                Data readerUse = new BloomFilter(0,0,null,0,0);
                HashMapData readerUse = new HashMapData();
                Reader studentReader = new Reader(readerUse);
                studentReader.loadData(tokens.get(1));
                students.clear();
                this.students = studentReader.getData().getData();
                data.handleBlooms(1, this.students, tokens);
                System.out.println("Read " + this.students.size() + " students from " + tokens.get(1));
                return "found";
            }
            else if (tokens.get(0).equals("similar_bf")) {
                data.handleBlooms(2, this.students, tokens);
                return "found";
            }
        }
            System.out.println("ERROR: invalid arguments");
            return null;
    }
}
