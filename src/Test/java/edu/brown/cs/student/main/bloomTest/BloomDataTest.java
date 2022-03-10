package edu.brown.cs.student.main.bloomTest;
import edu.brown.cs.student.main.bloom.BloomData;
import edu.brown.cs.student.main.bloom.BloomFilter;
import edu.brown.cs.student.main.bloom.BloomMaker;
import edu.brown.cs.student.main.Student;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class BloomDataTest {

    /**
     * tests the attrSize() method, located within the BloomData class
     */
    @Test
    public void testAttrSize() {
        Student stud = new Student("1,Stanton Swalough,sswalough0@ask.com,Female,junior,375-75-3870,Russia,American Indian or Alaska Native,18,email,2,in person,morning,2,\"quick learner, prepared, team player, early starter, friendly\",\"cutthroat, unfriendly, late\",OOP,\"mathematics, film/photography, politics\"\n");
        HashMap<String, BloomFilter> studentB = new HashMap<>();
        HashMap<String, Integer> similarities = new HashMap<>();
        BloomData data = new BloomData(studentB, similarities);
        int res1 = data.attrSize(stud);
        assertEquals(res1, 17);
    }


    /**
     * tests the fillBloom() method, located within the BloomData class
     * @throws IllegalAccessException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testFillBloom() throws IllegalAccessException, NoSuchAlgorithmException {
        Student stud = new Student("1,Stanton Swalough,sswalough0@ask.com,Female,junior,375-75-3870,Russia,American Indian or Alaska Native,18,email,2,in person,morning,2,\"quick learner, prepared, team player, early starter, friendly\",\"cutthroat, unfriendly, late\",OOP,\"mathematics, film/photography, politics\"\n");
        HashMap<String, BloomFilter> studentB = new HashMap<>();
        HashMap<String, Integer> similarities = new HashMap<>();
        BloomData data = new BloomData(studentB, similarities);
        BloomMaker maker = new BloomMaker();
        BloomFilter bloom = data.fillBloom(stud, maker);
        maker.createBf(0.1,18);
        assertEquals(maker.getBloom().getBitSize(), bloom.getBitSize());
    }
}
