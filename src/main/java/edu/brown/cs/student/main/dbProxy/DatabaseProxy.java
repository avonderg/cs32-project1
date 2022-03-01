package edu.brown.cs.student.main.dbProxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class models a database proxy, serving as an intermediary between the programmer and a database
 * file. It executes SQL commands on the given database after checking that the command is valid
 * and that there are no permissions violations. It also implements caching to minimize calls to the
 * database.
 */
public class DatabaseProxy {

  private String filename;
  private HashMap<String,String> tableToPermissions;
  private Connection conn = null;
  private static final Map<String, String> command_permissions = new HashMap<String,String>() {
    {
      put("SELECT", "R");
      put("INSERT", "W");
      put("DROP", "RW");
      put("UPDATE", "RW");
      put("DELETE", "RW");
      put("ALTER", "RW");
      put("JOIN", "R");
      put("TRUNCATE", "RW");
    }
  };


  /**
   * Constructor the db proxy class. This should connect to the user-provided SQL database
   * @param dbFilename
   * @param tablePermissions
   */
  public DatabaseProxy(String dbFilename, HashMap<String,String> tablePermissions)
      throws SQLException, ClassNotFoundException {
    this.filename = dbFilename;
    this.tableToPermissions = tablePermissions;

    Class.forName("org.sqlite.JDBC");
    String urlToDB = "jdbc:sqlite:" + dbFilename;
    this.conn = DriverManager.getConnection(urlToDB);

  }

  /**
   * Parses a SQL statement and calls on the appropriate method to execute the command, returning the
   * ResultSet object to the caller.
   * @throws SQLException if the query is unsuccessful
   * @return
   */
  public ResultSet executeSQL(String sql) throws SQLException {
    try {
      this.validCommand(sql);
    } catch (InvalidQueryException | InvalidPermissionsException e) {
      System.out.println("ERROR: Invalid Command");
      e.printStackTrace();
      return null;
    }

    PreparedStatement prep = this.conn.prepareStatement(sql);
    if (prep.execute()) {
      return prep.getResultSet();
    }

    return null;
  }

  public Boolean validCommand(String sql) throws InvalidQueryException,
      InvalidPermissionsException {
    String[] splitSQL = sql.split("(?=SELECT)|(?=INSERT)|(?=DROP)|(?=UPDATE)|(?=DELETE)|(?=ALTER)|"
        + "(?=JOIN)|(?=TRUNCATE)");

    //for every sql instruction
    for (String query:splitSQL) {
      String[] sqlLineTokens = query.split(" ");  //split the instruction into tokens
      String sqlKeyword = sqlLineTokens[0].toUpperCase();

      if (!command_permissions.containsKey(sqlKeyword)) {
        throw new InvalidQueryException("This is not a valid SQL command.");
      }

      String table = this.getTable(sqlLineTokens);
      if (table == null) {
        throw new InvalidQueryException("SQL command has a query that does not reference a valid table" +
            "from the provided permissions.");
      }

      //check that permissions match to provided table permissions
      String requiredPermissions = command_permissions.get(sqlKeyword);
      String givenPermissions = this.tableToPermissions.get(table.toLowerCase());
//      System.out.println(givenPermissions);

      if (!this.validPermissions(requiredPermissions, givenPermissions)) {
        throw new InvalidPermissionsException(givenPermissions, requiredPermissions);
      }

    }
    return true;

  }

  /**
   * This method checks whether some the permissions for some table satisfy the permissions needed
   * for the SQL keyword to be used on that table.
   * @param requiredPermissions the permissions required by the SQL keyword
   * @param givenPermissions the permissions the proxy has for the table in question
   * @throws InvalidPermissionsException if the given permissions don't match the required permissions
   */
  private Boolean validPermissions(String requiredPermissions, String givenPermissions) {
    if (requiredPermissions.equals("RW") && !givenPermissions.equals("RW")) {
      return false;
    } else if (requiredPermissions.equals("W") && givenPermissions.equals("R")) {
      return false;
    } else if (requiredPermissions.equals("R") && givenPermissions.equals("W")) {
      return false;
    }

    return true;
  }

  /**
   * This method checks for the name of the table in the command, and returns true if the command
   * contains the table
   * @param sqlTokens an array of words from an SQL query
   * @return the String name of the table, or null if the table is not found
   */
  private String getTable(String[] sqlTokens) {
    for (String word: sqlTokens) {
      if (this.tableToPermissions.containsKey(word.toLowerCase())) {
        return word;
      }
    }
    return null;
  }


}
