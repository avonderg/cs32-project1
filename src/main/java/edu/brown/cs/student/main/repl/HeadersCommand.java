package edu.brown.cs.student.main.repl;

import edu.brown.cs.student.main.csvReader.*;

import java.util.HashMap;
import java.util.List;

public class HeadersCommand implements Command{

  // maps attributes to type (qualitative or quantitative)
  HashMap<String, String> headers;
  // maps student IDs to hashmap of their attributes
  HashMap<String, HashMap<String, Object>> students;

  public HeadersCommand() {
    headers = new HashMap<String, String>();
    students = new HashMap<String, HashMap<String, Object>>();
  }

  @Override
  public String checkCommand(List<String> tokens) {
    if (tokens.get(0).equals("headers_load")) {

      // initialize new data object and new reader object
      HeadersData data = new HeadersData();
      Reader reader = new Reader(data);

      // load in header data from given file name
      reader.loadData(new String[] {tokens.get(1)});

      // store headers data in global variable
      headers = data.getData();

      // return REPL output
      return "Loaded header types";
    }
    else if (tokens.get(0).equals("recsys_load")) {
      // if headers are loaded
      if (headers.size() > 0) {

        // initialize new data object and new reader object
        StudentData data = new StudentData(headers);
        Reader reader = new Reader(data);

        // load in data from file names
        reader.loadData(new String[] {tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4)});
        students = data.getData();

        // return REPL output
        return "Loaded Recommender with k student(s).";
      }
      return "Headers not loaded";
    }
    return null;
  }
}
