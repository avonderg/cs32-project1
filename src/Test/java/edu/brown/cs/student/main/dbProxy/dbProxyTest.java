package edu.brown.cs.student.main.dbProxy;

import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    assertTrue(validCommand);
  }

  /**
   * This tests basic caching by assuring that the correct ResultSet is returned for cached results
   * and that the size of the cache increases every time a CachedRowSet should be added to the cache.
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @Test
  public void testBasicCaching() throws SQLException, ClassNotFoundException {
    HashMap<String, String> tables = new HashMap<String,String>() {
      {
        put("interests", "R");
        put("names", "R");
        put("skills", "R");
        put("traits", "R");
      }
    };

    DatabaseProxy db = new DatabaseProxy("data/recommendation_data/sql/data.sqlite3", tables);
    ResultSet rs = db.executeSQL("SELECT email FROM names WHERE ID='1'");

    rs.next();
    assertTrue(rs.getString(1).equals("pdillingstone0@nationalgeographic.com"));
    assertTrue(db.getCacheSize() == 1);
    rs = db.executeSQL("SELECT email FROM names WHERE ID='1'");

    rs.next();
    assertTrue(rs.getString(1).equals("pdillingstone0@nationalgeographic.com"));
    db.executeSQL("SELECT email FROM names WHERE ID='2'");
    assertTrue(db.getCacheSize() == 2);

  }

  /**
   * This tests makes sure that the cache is cleared after 10 entries are added (that the max size of
   * the cache never exceeds 10).
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @Test
  public void testCachingEviction() throws SQLException, ClassNotFoundException {
    HashMap<String, String> tables = new HashMap<String, String>() {
      {
        put("interests", "R");
        put("names", "R");
        put("skills", "R");
        put("traits", "R");
      }
    };

    DatabaseProxy db = new DatabaseProxy("data/recommendation_data/sql/data.sqlite3", tables);
    for (int i = 1; i < 11; i++) {
      String sqlToExecute = "SELECT email FROM names WHERE ID='" + i + "'";
      db.executeSQL(sqlToExecute);
      assertEquals(i, db.getCacheSize());
    }
    //11th select command added
    db.executeSQL("SELECT email FROM names WHERE ID='" + (db.getCacheSize() + 1) + "'");

    //assures that the size of the cache is still 10, which is the max size
    assertEquals(10, db.getCacheSize());

  }
}
