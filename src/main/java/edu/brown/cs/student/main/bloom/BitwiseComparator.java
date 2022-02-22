package edu.brown.cs.student.main.bloom;

import java.util.BitSet;

/**
 * Class for comparing two bitsets, belonging to a specific bloom filter
 */
public class BitwiseComparator {
    String comparator;

    /**
     * Constructor for BitwiseComparator class
     * @param comparator - String representing which bitwise operator to use
     */
    public BitwiseComparator(String comparator) {
        this.comparator = comparator;
    }

    /**
     * Calls appropriate functions, based off of specified operator
     *
     * @param comparator - String representing which bitwise operator to use
     * @param setTarget - BitSet belonging to target student
     * @param curr - current BitSet being compared
     * @param target - Bloom filter representing target student
     * @return - modified bitset, after gate has been applied
     */
    public BitSet applyCalculation(String comparator, BitSet setTarget, BloomFilter curr, BloomFilter target) {
        if (comparator.equals("and")) { // if user wants AND gate to be applied
            return applyAND(setTarget, curr);
        }
        else if (comparator.equals("xnor")) { // if user wants XNOR gate to be applied
            return applyXNOR(setTarget, curr, target);
        }
        else {
            return null ; // insert additional operators here!
        }
    }

    /**
     * Applies the XNOR gate to two respective bitsets
     *
     * @param setTarget - BitSet belonging to target student
     * @param curr - current BitSet being compared
     * @param target - Bloom filter representing target student
     * @return - modified bitset, after XNOR gate has been applied
     */
    private BitSet applyXNOR(BitSet setTarget, BloomFilter curr, BloomFilter target) {
        setTarget.xor(curr.getSet()); // XOR gate applied
        setTarget.flip(0,target.getBitSize()); // NOT operator applied to all bits (flips the m bits)
        return setTarget;
    }

    /**
     * Applies the AND gate to two respective bitsets
     *
     * @param setTarget  - BitSet belonging to target student
     * @param curr  - current BitSet being compared
     * @return - modified bitset, after AND gate has been applied
     */
    private BitSet applyAND(BitSet setTarget, BloomFilter curr) {
        setTarget.and(curr.getSet()); // AND gate applied
        return setTarget;
    }
}
