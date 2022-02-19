package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Reader {

  private List<String> tokens;

  private Data data;


  /**
   * Class constructor.
   */
  public Reader(Data data) { //List<String> tokens, String fileName
    this.data = data;
  }

  /**
   * Reads data from CSV file with given file name and stores it in data hashmap.
   * @param fileName
   */
  public void loadData(String fileName) {
    Path filePath  = Paths.get(fileName);
    try (BufferedReader reader = Files.newBufferedReader(filePath)) { // read file
      if (this.data.size() == 0) {
        data.clear(); // clears out old hashmap
      }
      String line = reader.readLine(); // read header first, don't add to hashmap
      while ((line = reader.readLine()) != null) {
        //Student student = new Student(line);
        data.insert(line);
      }
      System.out.println("Read " + data.size() + " lines from " + fileName);
    } catch (IOException ie) {
      System.out.println("ERROR: File not found.");
    }
  }

  /**
   * Returns hashmap storing the data.
   * @return
   */
  public Data getData() {
    return data;
  }
}
