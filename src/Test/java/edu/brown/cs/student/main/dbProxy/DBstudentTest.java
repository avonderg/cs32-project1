package edu.brown.cs.student.main.dbProxy;

import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class tests that the StudentFromDB object behavior is expected.
 */
public class DBstudentTest {

  /**
   * Tests a simple, valid SQL command
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @Test
  public void studentTest() throws SQLException, ClassNotFoundException {
    StudentFromDB dbStudent = new StudentFromDB(1, "Alex", "alex@brown.edu", "coding");
    ArrayList<String> strengthsList = new ArrayList<String>() {{
      add("basketball");
      add("coding");
    }};
    ArrayList<String> weakList = new ArrayList<String>() {{
      add("soccer");
      add("writing");
    }};
    ArrayList<String> interests = new ArrayList<String>() {{
      add("programming");
      add("sql");
    }};
    dbStudent.setInterests(interests);
    dbStudent.setStrengths(strengthsList);
    dbStudent.setWeaknesses(weakList);

    Assert.assertEquals(1, dbStudent.getId());
    Assert.assertEquals("Alex", dbStudent.getName());
    Assert.assertEquals("alex@brown.edu", dbStudent.getEmail());

    Assert.assertEquals("coding", dbStudent.getSkills()[0]);
    Assert.assertEquals(strengthsList, dbStudent.getStrengths());
    Assert.assertEquals(weakList, dbStudent.getWeaknesses());
    Assert.assertEquals(interests, dbStudent.getInterests());

    }

  }
