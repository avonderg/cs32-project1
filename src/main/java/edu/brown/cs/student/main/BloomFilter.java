package edu.brown.cs.student.main;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class BloomFilter  {
    private double r; // false positive rate
    private int n; // maximum size
    private BitSet set;
    private int bitSize;
    private int k;

    public BloomFilter(double r, int n, BitSet set, int bitSize, int k) {
        this.r = r;
        this.n = n;
        this.set = set;
        this.bitSize = bitSize;
        this.k = k;
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
    public int getBitSize() {
        return this.bitSize;
    }
    public int getK() {
        return this.k;
    }

    public int calculateSize(BloomFilter bloom) {
        int n = bloom.getN();
        double r = bloom.getR();
        int k = calculateK(bloom);
        int m;
        int top = k * n;
        m = (int)Math.floor(top / Math.log(2));
        this.bitSize = m;
        return m;
    }

    public int calculateK(BloomFilter bloom) {
        double r = bloom.getR();
        int k;
        k = (int)Math.floor(-1 * (Math.log(r) / Math.log(2)));
        this.k = k;
        return k;
    }

    public String printSet(BitSet set) {
        String str = "";
//        System.out.println(set.length());
        for( int i = 0; i < this.getBitSize(); i++) {
            if (set.get(i)) {
                str = str + "1";
            }
            else {
                str = str + "0";
            }
        }
        System.out.println(str);
        return str;
    }

    public String createBf(double r, int n) {
        BitSet set = new BitSet(n); // initializes it to all zeros
        this.r = r;
        this.n = n;
        this.set = set;
        BloomFilter bloom = new BloomFilter(r, n, set, 0,0);
        calculateSize(bloom); // changes bloom size and k
        BloomHashes hash = new BloomHashes();
//        studentBlooms.put(id, bloom); // insert into hashmap by student ID
        String str = printSet(bloom.getSet());
        return str;
    }

    public String insertBf(byte[] value) throws NoSuchAlgorithmException {
//        BloomFilter curr =  studentBlooms.get(id);
        if (this.getSet() == null) { // error checking
            System.out.println("ERROR: cannot insert into null bloom filter");
            return "error";
        }
        BloomHashes hash = new BloomHashes();
        int size = this.getBitSize();
        int k = this.getK();
        BigInteger[] hashFunctions = hash.createHashes(value, k); // res of hash functions
        for (BigInteger func: hashFunctions) {
            int val = func.mod(BigInteger.valueOf(size)).intValue(); // index in bitset
            this.getSet().set(val, true); // changes the bit
        }
        String str = printSet(this.getSet());
        return str;
    }

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

    public void handleBloomArgs(List<String> tokens) throws NoSuchAlgorithmException {
        if (tokens.get(0).equals("create_bf")) { // create bf command
            if (tokens.size() == 3) {
                double posRate = Double.parseDouble(tokens.get(1));
                int size = Integer.parseInt(tokens.get(2));
                createBf(posRate, size);
            }
            else {
                System.out.println("ERROR: invalid arguments");
                return;
            }
        }
        else if (tokens.get(0).equals("insert_bf")) { // create bf command
            if (tokens.size() == 2) {
                byte[] array = tokens.get(1).getBytes();
                insertBf(array);
            }
            else {
                System.out.println("ERROR: invalid arguments");
                return;
            }
        }
        else if (tokens.get(0).equals("query_bf")) { // create bf command
            if (tokens.size() == 2) {
                byte[] array = tokens.get(1).getBytes();
                queryBf(array);
            }
            else {
                System.out.println("ERROR: invalid arguments");
                return;
            }
        }
    }
}
