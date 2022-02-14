
package edu.brown.cs.student.main;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class studentTest {

  @Test
  public void testStudent() {
    Student testStudent = new Student(
        "2,Marika Reedie,mreedie1@cam.ac.uk,Female,senior,718-88-2492,Tunisia,and " +
            "Native Hawaiian or Other Pacific Islander,10,slack,16,in person,night,32," +
            "\"good leader, honest, quick learner, early starter\",\"unresponsive, dishonest, late\"," +
            "data structures,\"politics, theatre, music\"\n");
    assertEquals("2", testStudent.getID());
    assertEquals("Marika Reedie", testStudent.getName());
    assertEquals("mreedie1@cam.ac.uk", testStudent.getEmail());
    assertEquals("Female", testStudent.getGender());
    assertEquals("senior", testStudent.getClassYear());
    assertEquals("Tunisia", testStudent.getNationality());
    //assertEquals("and Native Hawaiian or Other Pacific Islander", testStudent.getRace());
    assertEquals("10", testStudent.getYearsExperience());
    assertEquals("slack", testStudent.getCommunicationStyle());
    assertEquals("16", testStudent.getWeeklyAvailHours());
    assertEquals("in person", testStudent.getMeetingStyle());
    assertEquals("night", testStudent.getMeetingTime());
    assertEquals("32", testStudent.getSoftwareEngnConfidence());
    assertEquals("good leader", testStudent.getStrengths()[0]);
    assertEquals("honest", testStudent.getStrengths()[1]);
    assertEquals("unresponsive", testStudent.getWeaknesses()[0]);
    assertEquals("dishonest", testStudent.getWeaknesses()[1]);
    assertEquals("data structures", testStudent.getSkills()[0]);
    assertEquals("politics", testStudent.getInterests()[0]);

    Student testStudent1 = new Student("5,Zelda Cosham,zcosham4@europa.eu,Female,freshman" +
        ",654-77-8995,Yemen,White,10,email,9,virtual,evening,53," +
        "\"honest, good teacher, friendly, team player\"," +
        "\"cutthroat, controlling, procrastinator, unprepared, unresponsive\"," +
        "\"testing, algorithms, OOP\",\"theatre, history, music\"\n");
    assertEquals("testing", testStudent1.getSkills()[0]);
    assertEquals("OOP", testStudent1.getSkills()[2]);
  }
}
