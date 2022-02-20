package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The CSV Reader class. This where file names are passed in and their information is stored.
 */
public class Reader {

  private Data data;

  /**
   * Class constructor.
   */
  public Reader(Data data) { //List<String> tokens, String fileName
    this.data = data;
  }

  /**
   * Reads data from CSV file with given file name and stores it in Data object.
   * @param fileName -- name of the csv file to be read
   */
  public void loadData(String fileName) {
    // store the filePath object from given file name
    Path filePath  = Paths.get(fileName);

    // initialize bufferedReader to read the file
    try (BufferedReader reader = Files.newBufferedReader(filePath)) {
      // clear out old data
      data.clear();

      // read header first, so don't store header information as data
      String line = reader.readLine();

      // iterate through lines of csv until reached the end (null)
      while ((line = reader.readLine()) != null) {

        // calls data object's insert method to store information in current line of the csv file
        data.insert(line);
      }

      // prints output describing how much data was read from given file
      System.out.println("Read " + data.size() + " lines from " + fileName);
    } catch (IOException ie) {

      // if file cannot be read, print out error statement
      System.out.println("ERROR: File not found.");
    }
  }

  /**
   * Returns object storing the data.
   * @return
   */
  public Data getData() {
    return data;
  }
}
