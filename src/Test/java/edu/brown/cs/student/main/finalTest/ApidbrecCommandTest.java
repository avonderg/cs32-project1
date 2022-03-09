package edu.brown.cs.student.main.finalTest;

import edu.brown.cs.student.main.repl.ApiDBRecCommand;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This class tests that the APIDBRecCommand properly functions, aggregating data from the dbProxy and
 * the API Aggregator.
 */
public class ApidbrecCommandTest {

  @Test
  public void testLoadDB() {
    ApiDBRecCommand command = new ApiDBRecCommand();
    List<String> tokens = new ArrayList<String>();
    tokens.add("load_db_students");
    assertEquals(command.checkCommand(tokens), "Read 60 students from data.sqlite3");
  }

  @Test
  public void testLoadAPI() {
    ApiDBRecCommand command = new ApiDBRecCommand();
    List<String> tokens = new ArrayList<String>();
    tokens.add("load_api_students");
    assertEquals(command.checkCommand(tokens), "Successfully loaded students from API.");
  }

  @Test
  public void testRecommend() {
    ApiDBRecCommand command = new ApiDBRecCommand();
    List<String> tokens = new ArrayList<String>();
    tokens.add("api_db_recommend");
    assertEquals(command.checkCommand(tokens), "No students found");

  }

}
