package edu.brown.cs.student.main.dbProxy;

import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class dbProxyTest {

  /**
   * Tests a simple, valid SQL command
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @Test
  public void simpleCommandTest() throws SQLException, ClassNotFoundException {
    HashMap<String, String> tables = new HashMap<String,String>() {
      {
        put("interests", "R");
        put("names", "R");
        put("skills", "R");
        put("traits", "R");
      }
    };

    DatabaseProxy db = new DatabaseProxy("data/recommendation_data/sql/data.sqlite3", tables);
    ResultSet rs = db.executeSQL("SELECT email FROM names");

    //assert that ResultSet is not null
    Assert.assertNotNull(rs);
    int size = 0;
    while (rs.next()) {
      size++;
    }
    //assert that it returned 60 emails
    Assert.assertEquals(size, 60);
  }

  /**
   * Tests that trying to update a table with "R" permissions results in an InvalidPermissionsException
   * being thrown.
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @Test(expected = InvalidPermissionsException.class)
  public void permissionsExceptionTest()
      throws SQLException, ClassNotFoundException, InvalidPermissionsException,
      InvalidQueryException {
    HashMap<String, String> tables = new HashMap<String,String>() {
      {
        put("interests", "R");
        put("names", "R");
        put("skills", "R");
        put("traits", "R");
      }
    };
    DatabaseProxy db = new DatabaseProxy("data/recommendation_data/sql/data-tester.sqlite3", tables);
    db.validCommand("UPDATE names SET id= '13' WHERE name = 'Ben'");
  }

  /**
   * This test checks that a command with invalid SQL syntax correctly has an exception thrown for it.
   * @throws SQLException
   * @throws ClassNotFoundException
   * @throws InvalidQueryException
   * @throws InvalidPermissionsException
   */
  @Test(expected = InvalidQueryException.class)
  public void testInvalidCommandThrowsQueryException() throws SQLException, ClassNotFoundException,
      InvalidQueryException, InvalidPermissionsException {
    HashMap<String, String> tables = new HashMap<String,String>() {
      {
        put("interests", "R");
        put("names", "R");
        put("skills", "R");
        put("traits", "R");
      }
    };

    DatabaseProxy db = new DatabaseProxy("data/recommendation_data/sql/data.sqlite3", tables);
    //faulty SQL (no keyword at start)
    Boolean rs = db.validCommand("email FROM names");

  }

  /**
   * This test checks that a valid command is correctly processed.
   * @throws SQLException
   * @throws ClassNotFoundException
   * @throws InvalidQueryException
   * @throws InvalidPermissionsException
   */
  @Test
  public void testValidCommand() throws SQLException, ClassNotFoundException,
      InvalidQueryException, InvalidPermissionsException {
    HashMap<String, String> tables = new HashMap<String,String>() {
      {
        put("interests", "R");
        put("names", "R");
        put("skills", "R");
        put("traits", "R");
      }
    };

    DatabaseProxy db = new DatabaseProxy("data/recommendation_data/sql/data.sqlite3", tables);
    Boolean validCommand = db.validCommand("SELECT email FROM names");

    //assert that the command is valid s
    Assert.assertTrue(validCommand);
  }



}
