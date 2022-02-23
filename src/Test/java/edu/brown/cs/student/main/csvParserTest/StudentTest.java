package edu.brown.cs.student.main;

import edu.brown.cs.student.main.csvReader.Student;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The class for testing the Student object.
 */
public class StudentTest {

  /**
   * Tests that the Student object parses the line of attributes
   * correctly.
   */
  @Test
  public void testStudent() {

    // initialize new object Student with line from CSV file (proj1_small.csv)
    Student testStudent = new Student(
        "2,Marika Reedie,mreedie1@cam.ac.uk,Female,senior,718-88-2492,Tunisia,and " +
            "Native Hawaiian or Other Pacific Islander,10,slack,16,in person,night,32," +
            "\"good leader, honest, quick learner, early starter\",\"unresponsive, dishonest, late\"," +
            "data structures,\"politics, theatre, music\"\n");

    // test that Student constructor parses the line correctly
    // and getter functions return the correct values
    assertEquals("2", testStudent.getID());
    assertEquals("Marika Reedie", testStudent.getName());
    assertEquals("mreedie1@cam.ac.uk", testStudent.getEmail());
    assertEquals("Female", testStudent.getGender());
    assertEquals("senior", testStudent.getClassYear());
    assertEquals("Tunisia", testStudent.getNationality());
    assertEquals("and Native Hawaiian or Other Pacific Islander", testStudent.getRace());
    assertEquals("10", testStudent.getYearsExperience());
    assertEquals("slack", testStudent.getCommunicationStyle());
    assertEquals("16", testStudent.getWeeklyAvailHours());
    assertEquals("in person", testStudent.getMeetingStyle());
    assertEquals("night", testStudent.getMeetingTime());
    assertEquals("32", testStudent.getSoftwareEngnConfidence());

    // tests that list items are stored correctly
    assertEquals("good leader", testStudent.getStrengths()[0]);
    assertEquals("honest", testStudent.getStrengths()[1]);
    assertEquals("unresponsive", testStudent.getWeaknesses()[0]);
    assertEquals("dishonest", testStudent.getWeaknesses()[1]);
    assertEquals("data structures", testStudent.getSkills()[0]);
    assertEquals("politics", testStudent.getInterests()[0]);
    assertEquals(10, testStudent.getCoords()[0], 0.01);
    assertEquals(16, testStudent.getCoords()[1], 0.01);
    assertEquals(32, testStudent.getCoords()[2], 0.01);


    // initialize new object Student with line from CSV file (proj1_small.csv)
    Student testStudent1 = new Student("5,Zelda Cosham,zcosham4@europa.eu,Female,freshman" +
        ",654-77-8995,Yemen,White,10,email,9,virtual,evening,53," +
        "\"honest, good teacher, friendly, team player\"," +
        "\"cutthroat, controlling, procrastinator, unprepared, unresponsive\"," +
        "\"testing, algorithms, OOP\",\"theatre, history, music\"\n");

    // tests that list items are stored correctly
    assertEquals("testing", testStudent1.getSkills()[0]);
    assertEquals("OOP", testStudent1.getSkills()[2]);
  }
}
