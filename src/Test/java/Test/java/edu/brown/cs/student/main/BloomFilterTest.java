package Test.java.edu.brown.cs.student.main;

import edu.brown.cs.student.main.BloomFilter;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BloomFilterTest {

    @Test
    public void testGetR() {
        BloomFilter bloom1 = new BloomFilter(0.1,0,null,0,0);
        assertEquals(bloom1.getR(),0.1, 0.1);
        BloomFilter bloom2 = new BloomFilter(100.2,0,null,0,0);
        assertEquals(bloom2.getR(),100.2, 0.1);
    }

    @Test
    public void testGetN() {
        BloomFilter bloom1 = new BloomFilter(0.1,0,null,0,0);
        assertEquals(bloom1.getN(),0);
        BloomFilter bloom2 = new BloomFilter(100.2,66,null,0,0);
        assertEquals(bloom2.getN(),66);
    }

    @Test
    public void testGetSet() {
        BloomFilter bloom1 = new BloomFilter(0.1,0,null,0,0);
        assertEquals(bloom1.getSet(),null);
        BitSet set1 = new BitSet(5);
        BloomFilter bloom2 = new BloomFilter(0.1,0,set1,0,0);
        assertEquals(bloom2.getSet(),set1);
    }

    @Test
    public void testGetBitSize() {
        BloomFilter bloom1 = new BloomFilter(0.1,0,null,0,0);
        assertEquals(bloom1.getBitSize(),0);
        BloomFilter bloom2 = new BloomFilter(0.1,0,null,39,0);
        assertEquals(bloom2.getBitSize(),39);
    }

    @Test
    public void testGetK() {
        BloomFilter bloom1 = new BloomFilter(0.1,0,null,0,0);
        assertEquals(bloom1.getK(),0);
        BloomFilter bloom2 = new BloomFilter(0.1,0,null,39,7);
        assertEquals(bloom2.getK(),7);
    }

    @Test
    public void testCalculateK() {
        BloomFilter bloom1 = new BloomFilter(0.1,0,null,0,0);
        int res1 = bloom1.calculateK(bloom1);
        assertEquals(res1,4);
        BloomFilter bloom2 = new BloomFilter(0.5,0,null,0,0);
        int res2 = bloom2.calculateK(bloom2);
        assertEquals(res2,1);
    }

    @Test
    public void testCalculateSize() {
        BloomFilter bloom1 = new BloomFilter(0.1,5,null,0,0);
        int res1 = bloom1.calculateSize(bloom1);
        assertEquals(res1,29);
    }

    @Test
    public void checkCommand() throws NoSuchAlgorithmException, IOException, IllegalAccessException {
        List<String> tokens1 = new ArrayList<String>();
        tokens1.add("create_bf");
        tokens1.add("0.1");
        tokens1.add("5");
        BloomFilter bloom1 = new BloomFilter(0,0,null,0,0);
        assertEquals(bloom1.checkCommand(tokens1), "found");
        BloomFilter bloom2 = new BloomFilter(0,0,null,0,0);
        List<String> tokens2 = new ArrayList<String>();
        tokens2.add("create_bf");
        assertEquals(bloom2.checkCommand(tokens2), null);
        BloomFilter bloom3 = new BloomFilter(0,0,null,0,0);
        List<String> tokens3 = new ArrayList<String>();
        tokens3.add("insert_bf");
        tokens3.add("alex");
        assertEquals(bloom1.checkCommand(tokens1), "found");
    }

    @Test
    public void createBf() throws NoSuchAlgorithmException, IOException, IllegalAccessException {
        BloomFilter bloom1 = new BloomFilter(0,0,null,0,0);
        List<String> tokens1 = new ArrayList<String>();
        tokens1.add("create_bf");
        tokens1.add("0.1");
        tokens1.add("5");
        bloom1.checkCommand(tokens1);
        assertEquals(bloom1.getR(), 0.1, 0.1);
        assertEquals(bloom1.getN(), 5);
    }




}
