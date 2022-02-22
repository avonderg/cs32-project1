package Test.java.edu.brown.cs.student.main;

import edu.brown.cs.student.main.Student;
import edu.brown.cs.student.main.bloom.BitwiseComparator;
import edu.brown.cs.student.main.bloom.BloomData;
import edu.brown.cs.student.main.bloom.BloomFilter;
import edu.brown.cs.student.main.bloom.BloomMaker;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class BitwiseComparatorTest {

    /**
     * Method to test the applyCalculation() function within the BitwiseComparator class
     * @throws IllegalAccessException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testApplyCalculation() throws IllegalAccessException, NoSuchAlgorithmException {
        Student stud = new Student("1,Stanton Swalough,sswalough0@ask.com,Female,junior,375-75-3870,Russia,American Indian or Alaska Native,18,email,2,in person,morning,2,\"quick learner, prepared, team player, early starter, friendly\",\"cutthroat, unfriendly, late\",OOP,\"mathematics, film/photography, politics\"\n");
        HashMap<String, BloomFilter> studentB = new HashMap<>();
        HashMap<String, Integer> similarities = new HashMap<>();
        BitwiseComparator comparator1 = new BitwiseComparator("xnor");
        BitSet target1 = new BitSet();
        BloomData data = new BloomData(studentB, similarities);
        BloomMaker maker = new BloomMaker();
        maker.createBf(0.1,18);
        BloomFilter bloom = data.fillBloom(stud, maker);
        BitSet res1 = comparator1.applyCalculation("xnor", target1, maker.getBloom(), bloom);
        assertEquals(res1.cardinality(), 55);
    }

    /**
     * Method to test the applyXNOR() function within the BitwiseComparator class
     * @throws IllegalAccessException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testApplyXNOR() throws IllegalAccessException, NoSuchAlgorithmException {
        Student stud = new Student("1,Stanton Swalough,sswalough0@ask.com,Female,junior,375-75-3870,Russia,American Indian or Alaska Native,18,email,2,in person,morning,2,\"quick learner, prepared, team player, early starter, friendly\",\"cutthroat, unfriendly, late\",OOP,\"mathematics, film/photography, politics\"\n");
        HashMap<String, BloomFilter> studentB = new HashMap<>();
        HashMap<String, Integer> similarities = new HashMap<>();
        BitwiseComparator comparator2 = new BitwiseComparator("xnor");
        BitSet target1 = new BitSet();
        BloomData data = new BloomData(studentB, similarities);
        BloomMaker maker = new BloomMaker();
        maker.createBf(0.1,18);
        BloomFilter bloom = data.fillBloom(stud, maker);
        BitSet res1 = comparator2.applyCalculation("xnor", target1, maker.getBloom(), bloom);
        assertEquals(res1.cardinality(), 54);
    }

    /**
     *  Method to test the applyAND() function within the BitwiseComparator class
     * @throws IllegalAccessException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testApplyAND() throws IllegalAccessException, NoSuchAlgorithmException {
        Student stud = new Student("1,Stanton Swalough,sswalough0@ask.com,Female,junior,375-75-3870,Russia,American Indian or Alaska Native,18,email,2,in person,morning,2,\"quick learner, prepared, team player, early starter, friendly\",\"cutthroat, unfriendly, late\",OOP,\"mathematics, film/photography, politics\"\n");
        HashMap<String, BloomFilter> studentB = new HashMap<>();
        HashMap<String, Integer> similarities = new HashMap<>();
        BitwiseComparator comparator3 = new BitwiseComparator("and");
        BitSet target1 = new BitSet();
        BloomData data = new BloomData(studentB, similarities);
        BloomMaker maker = new BloomMaker();
        maker.createBf(0.1,18);
        BloomFilter bloom = data.fillBloom(stud, maker);
        BitSet res1 = comparator3.applyCalculation("and", target1, maker.getBloom(), bloom);
        assertEquals(res1.cardinality(), 0);
    }


}
