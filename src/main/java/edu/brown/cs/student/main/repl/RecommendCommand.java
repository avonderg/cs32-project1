package edu.brown.cs.student.main.repl;

import edu.brown.cs.student.main.csvReader.*;

import java.util.HashMap;
import java.util.List;

/**
 * Class that checks for sprint 2 recommender commands.
 * Checks for headers_load, recsys_load, and recommend.
 * Calls appropriate methods/classes for each command.
 */
public class RecommendCommand implements Command{

  // maps attributes to type (qualitative or quantitative)
  HashMap<String, String> headers;

  // maps student IDs to hashmap of their attributes
  HashMap<String, Student> students;


  /**
   * Class constructor. Initializes global variables to new objects.
   */
  public RecommendCommand() {
    this.headers = new HashMap<String, String>();
    this.students = new HashMap<String, Student>();
  }

  @Override
  public String checkCommand(List<String> tokens) {
    // check for headers_load command (load in header type information)
    if (tokens.get(0).equals("headers_load")) {
      // initialize new data object and new reader object
      HeadersData data = new HeadersData();
      Reader reader = new Reader(data);

      // load in header data from given file name
      reader.loadData(new String[]{tokens.get(1)});

      // store headers data in global variable
      headers = data.getData();

      // return output
      return "Loaded header types";

      // check for recsys_load command (load student information)
    } else if (tokens.get(0).equals("recsys_load")) {
      // if headers are loaded
      if (headers.size() > 0) {
        // initialize new data object and new reader object
        StudentData data = new StudentData(headers);
        Reader reader = new Reader(data);

        // load in data from file names
        reader.loadData(new String[]{tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4)});

        // turn student hashmap information into student objects
        this.parseStudents(data.getData());

        // return REPL output
        return "Loaded Recommender with k student(s).";
      }

      // if size of headers is 0, headers have not been loaded yet, return appropriate output
      return "Headers not loaded";

      // check for recommend command (produce recommendations given target student)
    } else if (tokens.get(0).equals("recommend")) {
      if (students.size()<=0) {
        // if no students are stored, return appropriate message
        return "No students found";
      } else if (!students.containsKey(tokens.get(2))) { // if target student is not in hashmap,
        // return appropriate message
        return "Target student not found";
      }
      // initialize new recommender object and pass in k value, students hashmap,
      // and target student object
      Recommender recommender =
          new Recommender(tokens.get(1), students, tokens.get(2));

      // initialize output string as empty string
      String output = "";

      // add recommendation ids to the string
      Object[] recs = recommender.getRecommendations();
      for (Object r : recs) {
        String id = r.toString();
        output = output + id + "\n";
      }

      // return string with stored recommendation ids
      return output.trim();
    }

    // if none of the commands are matched, return null
    return null;
  }

  /**
   * Converts hashmap information into a student object.
   * @param data hashmap information to parse into student objects
   */
  private void parseStudents(HashMap<String, HashMap<String, Object>> data) {
    // iterate through each student (ids numbered 1-n)
    for (int i=1; i<=data.size(); i++) {

      // convert integer id to a string
      String id = Integer.toString(i);

      // store student id information and new student object with appropriate information from data
      students.put(id, new Student(data.get(id), id));
    }
  }
}
