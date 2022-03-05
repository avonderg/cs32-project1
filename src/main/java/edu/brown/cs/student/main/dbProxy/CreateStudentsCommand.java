package edu.brown.cs.student.main.dbProxy;

import edu.brown.cs.student.main.repl.Command;

import javax.xml.transform.Result;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class demos how to load a student object from data.sqlite3 using the database proxy.
 */
public class CreateStudentsCommand implements Command {

  DatabaseProxy dataDB;
  Map<Integer, StudentFromDB> idToDBstudentMap;

  /**
   * Check command method implmeneted from the command.
   * @param tokens -- list of REPL input Strings
   * @return some string on success, null on failure or non-existent command
   */
  @Override
  public String checkCommand(List<String> tokens) {
    if (tokens.get(0).equals("load_students")) {
      HashMap<String, String> tablePermissions = new HashMap<String, String>();
      tablePermissions.put("names", "R");
      tablePermissions.put("interests", "R");
      tablePermissions.put("skills", "R");
      tablePermissions.put("traits", "R");
      try {
        this.dataDB =
            new DatabaseProxy("data/recommendation_data/sql/data.sqlite3", tablePermissions);
      } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
      }
      System.out.println("Successfully loaded data_tester.sqlite3");

      this.idToDBstudentMap = new HashMap<Integer, StudentFromDB>();

      ResultSet studentFields = null;
      try {
        studentFields = this.dataDB.executeSQL("SELECT names.id,names.name "
            + ",names.email,skills.skill FROM names JOIN skills ON names.id = skills.id");
      } catch (SQLException exception) {
        System.out.println("SQLException Thrown");
        exception.printStackTrace();
      }

      if (studentFields == null) {
        return null;
      }

      try {
        this.populateStudentObjects(studentFields);
      } catch (SQLException exception) {
        exception.printStackTrace();
      }

      try {
        this.addTraits("strengths");
      } catch (SQLException exception) {
        exception.printStackTrace();
      }
      try {
        this.addTraits("weaknesses");
      } catch (SQLException exception) {
        exception.printStackTrace();
      }
      try {
        this.addInterests();
      } catch (SQLException exception) {
        exception.printStackTrace();
      }


      System.out.println("Read " + this.idToDBstudentMap.size() + " students from data.sqlite3");

      return "";
    }
    return null;
  }

  private void addInterests() throws SQLException {
    int i = 1;
    while (i <= this.idToDBstudentMap.size()) {
      StudentFromDB currStudent = this.idToDBstudentMap.get(i);
      ResultSet interestRS = null;
      try {
        interestRS = this.dataDB.executeSQL("SELECT interest FROM interests" +
            " WHERE id='" + i + "'");
      } catch (SQLException exception) {
        exception.printStackTrace();
      }
      ArrayList<String> studentInterests = new ArrayList<String>();
      while (interestRS.next()) {
        String currInterest = interestRS.getString(1);
        if (i == 1) {
          System.out.println(currInterest);
        }
        studentInterests.add(currInterest);
      }

      currStudent.setInterests(studentInterests);
      i++;
    }
  }

  /**
   * This method retreives either or weaknesses for each student from the database using a sql commands
   * @param strengthsOrWeaknesses
   * @throws SQLException
   */
  private void addTraits(String strengthsOrWeaknesses) throws SQLException {
    //parse in the interests and the traits

    if (!(strengthsOrWeaknesses.equals("strengths") || strengthsOrWeaknesses.equals("weaknesses"))) {
      System.out.println("ERROR: String must be strengths or weaknesses");
      return;
    }
    int i = 1;
    while (i <= this.idToDBstudentMap.size()) {
      StudentFromDB currStudent = this.idToDBstudentMap.get(i);
      ResultSet traitRS = null;
      try {
        traitRS = this.dataDB.executeSQL("SELECT trait FROM traits WHERE lower(type_of_attribute) = '"
            + strengthsOrWeaknesses + "' AND traits.id='" + i + "'");
      } catch (SQLException exception) {
        exception.printStackTrace();
      }
      ArrayList<String> studentTraits = new ArrayList<String>();
      while (traitRS.next()) {
        String currTrait = traitRS.getString(1);
        if (i == 1) {
          System.out.println(currTrait);
        }
        studentTraits.add(currTrait);
      }
      if (strengthsOrWeaknesses.equals("strengths")) {
        currStudent.setStrengths(studentTraits);
      } else {
        currStudent.setWeaknesses(studentTraits);
      }
      i++;
    }
  }

  /**
   * This helper creates students from the parsed input.
   * @param studentFields - the ResultSet of fields for the student from the SQL query in checkCommand().
   * @throws SQLException
   */
  private void populateStudentObjects(ResultSet studentFields) throws SQLException {
    while (studentFields.next()) {
      int id = studentFields.getInt(1);
      String name = studentFields.getString(2);
      String email = studentFields.getString(3);
      String skill = studentFields.getString(4);
      this.idToDBstudentMap.put(id, new StudentFromDB(id, name, email, skill));
    }
  }

}
