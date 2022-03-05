package edu.brown.cs.student.main.dbProxy;

import edu.brown.cs.student.main.repl.Command;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * This command class demonstrates the standard db proxy functionality and handles 2 different commands,
 * "load_db_data, which connects to the data.sqlite3 database, and query_db, which takes different
 * SQL query options (1-8) as the 2nd input and queries the connected database.
 */
public class DbCommand implements Command {
  DatabaseProxy dataDB;

  //note from mentor: change REPL command so that user can input SQL syntax
  /**
   * Implemented from the command class
   * @param tokens -- list of REPL input Strings
   * @return null if command is not found, some string if command checks out.
   */
  @Override
  public String checkCommand(List<String> tokens) {
    if (tokens.get(0).equals("load_db_data")) {
      HashMap<String,String> tablePermissions = new HashMap<String,String>();
      tablePermissions.put("names", "RW");
      tablePermissions.put("interests", "RW");
      tablePermissions.put("skills", "RW");
      tablePermissions.put("traits", "R");
      try {
        this.dataDB = new DatabaseProxy("data/recommendation_data/sql/data.sqlite3", tablePermissions);
      } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
      }
      System.out.println("Successfully loaded data_tester.sqlite3");
      return "";
    } else if (tokens.get(0).equals("query_db")) {
      if (this.dataDB == null) {
        //cannot call query command before loading db
        System.out.println("ERROR: cannot call query_db before loading the database");
        return "";
      }

      String query = this.getQuery(tokens.get(1));
      ResultSet queryResult = null;
      try {
        queryResult = this.dataDB.executeSQL(query);
      } catch (SQLException e) {
        e.printStackTrace();
      }

      //for demo purposes
      this.printResultSet(queryResult);
    }
    else if (tokens.get(0).equals("query")) {
      int i = 1;
      String sqlQuery = "";
      while (i < tokens.size()) {
        sqlQuery += tokens.get(i) + " ";
        i++;
      }
      ResultSet queryResult = null;
      try {
        queryResult = this.dataDB.executeSQL(sqlQuery);
      } catch (SQLException e) {
        e.printStackTrace();
      }

      this.printResultSet(queryResult);
    }
    return null;
  }

  /**
   * Helper method that includes different SQL query options to test.
   * @param inputChoice - the user chosen query number
   * @return the string form of the query chosen
   */
  private String getQuery(String inputChoice) {
    String query = "";
    switch (inputChoice) {
      case "0":
        query = "SELECT name FROM names";
        break;
      case "1":
        query = "INSERT INTO NAMES (ID, NAME, EMAIL) VALUES (99, 'Ben', 'ben_piekarz@brown.edu')";
        break;
      case "2":
        query = "UPDATE NAMES SET ID= '13' WHERE NAME = 'Ben'";
        break;
      case "3":
        query = "SELECT id FROM names WHERE name = 'Ben'";
        break;
      case "4":
        query = "DELETE FROM names WHERE NAME = 'Ben'";
        break;
      case "5":
        query = "SELECT name FROM INTERESTS JOIN SKILLS ON SKILLS.ID=INTERESTS.ID";
        break;
      case "6":
        query = "SELECT * FROM INTERESTS LIMIT 0, 30";
        break;
      case "7":
        query = "UPDATE INTERESTS SET INTEREST='engineering' WHERE ID=40";
        break;
      case "8":
        query = "UPDATE INTERESTS SET INTEREST='coding' WHERE ID=50";
        break;
      default:
        break;
    }
    return query;
  }


  /**
   * Helper to print user-inputted ResultSet.
   * @param rs -  result set
   */
  private void printResultSet(ResultSet rs) {
    try {
      if (rs!= null) {
        while (rs.next()){
          System.out.println(rs.getString(1));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
