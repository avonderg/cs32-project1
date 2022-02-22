package edu.brown.cs.student.main.bloomTest;
import edu.brown.cs.student.main.bloom.BloomData;
import edu.brown.cs.student.main.bloom.BloomFilter;
import edu.brown.cs.student.main.bloom.BloomMaker;
import edu.brown.cs.student.main.csvReader.Student;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BloomDataTest {
    @Test
    public void testHandleBlooms() throws IllegalAccessException, NoSuchAlgorithmException {
        HashMap<String, BloomFilter> studentB = new HashMap<>();
        HashMap<String, Student> students = new HashMap<>();
        HashMap<String, Integer> similarities = new HashMap<>();
        List<String> tokens = new ArrayList<String>();
        tokens.add("load_bf");
        tokens.add("data/project1/proj1_big.csv");
        BloomData data = new BloomData(studentB, similarities);
        int res1 = data.handleBlooms(1, students, tokens);
        assertEquals(res1, 1);
        tokens.clear();
        tokens.add("similar_bf");
        tokens.add("5"); // invalid args
        int res2 = data.handleBlooms(2, students, tokens);
        assertEquals(res2, 2);
    }

    @Test
    public void testAttrSize() {
        Student stud = new Student("1,Stanton Swalough,sswalough0@ask.com,Female,junior,375-75-3870,Russia,American Indian or Alaska Native,18,email,2,in person,morning,2,\"quick learner, prepared, team player, early starter, friendly\",\"cutthroat, unfriendly, late\",OOP,\"mathematics, film/photography, politics\"\n");
        HashMap<String, BloomFilter> studentB = new HashMap<>();
        HashMap<String, Integer> similarities = new HashMap<>();
        BloomData data = new BloomData(studentB, similarities);
        int res1 = data.attrSize(stud);
        assertEquals(res1, 18);
    }


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
