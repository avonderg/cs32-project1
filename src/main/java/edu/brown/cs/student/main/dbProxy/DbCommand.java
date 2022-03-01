package edu.brown.cs.student.main.dbProxy;

import edu.brown.cs.student.main.repl.Command;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class DbCommand implements Command {
  DatabaseProxy dataDB;

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
      ResultSet getNamesRS = null;
      try {
        getNamesRS = this.dataDB.executeSQL(query);
      } catch (SQLException e) {
        e.printStackTrace();
      }

      //for demo purposes
      this.printResultSet(getNamesRS);
    }
    return null;
  }

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
