package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Reader {

  private List<String> tokens;
  private HashMap<String, Student> data = new HashMap<String, Student>();

  public Reader() { //List<String> tokens, String fileName
    //this.tokens = tokens;
  }

  public void loadData(String fileName) {
//    if (tokens.size() == 1) {
//      System.out.println("ERROR: no file name found");
//      return;
//    }
//    String fileName = tokens.get(1);
    Path filePath  = Paths.get(fileName);
    try (BufferedReader reader = Files.newBufferedReader(filePath)) { // read file
      if (this.data.size() == 0) {
        data.clear(); // clears out old hashmap
      }
      String line = reader.readLine(); // read header first, don't add to hashmap
      while ((line = reader.readLine()) != null) {
        Student student = new Student(line);
        data.put(student.getID(), student);
      }
    } catch (IOException ie) {
      System.out.println("ERROR: File not found.");
    }
  }

  public HashMap<String, Student> getData() {
    return data;
  }
}
