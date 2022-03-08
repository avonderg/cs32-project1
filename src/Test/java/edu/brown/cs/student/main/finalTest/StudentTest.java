package edu.brown.cs.student.main.finalTest;

import edu.brown.cs.student.main.api.client.APIInfoStudents;
import edu.brown.cs.student.main.api.client.APIMatchStudents;
import edu.brown.cs.student.main.Student;
import edu.brown.cs.student.main.dbProxy.StudentFromDB;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StudentTest {

  @Test
  public void testConstructor() {
    StudentFromDB db =
        new StudentFromDB(1, "Bob", "bob@gmail.com", "coding");
    ArrayList<String> interests = new ArrayList<>();
    interests.add("film");
    interests.add("art");
    db.setInterests(interests);

    ArrayList<String> strengths = new ArrayList<>();
    strengths.add("honest");
    strengths.add("knowledgeable");
    db.setStrengths(strengths);

    ArrayList<String> weaknesses = new ArrayList<>();
    weaknesses.add("controlling");
    weaknesses.add("procrastinator");
    db.setWeaknesses(weaknesses);

    APIInfoStudents info = new APIInfoStudents(1, "bob", "bob@gmail.com",
        "2024", 5, "text", 2,
        "virtual", "morning");

      APIMatchStudents match =
        new APIMatchStudents(1, "bob", "male", "11-111-111",
            "american", "alien", 30);

      Student student = new Student(db, info, match);

      assertEquals(student.getID(), "1");
      assertEquals(student.getName(), "Bob");
      assertEquals(student.getEmail(), "bob@gmail.com");
      assertEquals(student.getNationality(), "american");
      assertEquals(student.getClassYear(), "2024");
      assertEquals(student.getRace(), "alien");
      assertEquals(student.getYearsExperience(), 5, 0.1);
      assertEquals(student.getCommunicationStyle(), "text");
      assertEquals(student.getWeeklyAvailHours(), 2, 0.1);
      assertEquals(student.getMeetingStyle(), "virtual");
      assertEquals(student.getMeetingTime(), "morning");
      assertEquals(student.getSoftwareEngnConfidence(), 30, 0.1);
      assertEquals(student.getStrengths()[0], "honest");
      assertEquals(student.getStrengths()[1], "knowledgeable");
      assertEquals(student.getWeaknesses()[0], "controlling");
      assertEquals(student.getWeaknesses()[1], "procrastinator");
      assertEquals(student.getSkills()[0], "coding");
      assertEquals(student.getInterests()[0], "film");
      assertEquals(student.getInterests()[1], "art");
  }
}
