package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Reader implements Command{

  private List<String> tokens;
  private String command;
  private HashMap<String, Student> studentData;

  public Reader(List<String> tokens, String command) {
    this.tokens = tokens;
    this.command = command;
  }

  @Override
  public String getCommand() {
    return command;
  }

  @Override
  public void loadData() {
    if (tokens.size() == 1) {
      System.out.println("ERROR: no file name found");
      return;
    }
    String fileName = tokens.get(1);
    Path filePath  = Paths.get(fileName);

    try (BufferedReader reader = Files.newBufferedReader(filePath)) { // read file
      studentData.clear(); // clears out old hashmap
      String line = reader.readLine(); // read header first, don't add to hashmap
      while ((line = reader.readLine()) != null) {
        Student student = new Student(line);
        studentData.put(student.getID(), student);
      }
    } catch (IOException ie) {
      System.out.println("ERROR: File not found.");
    }
  }

  @Override
  public void getResults() {

  }
}
