package edu.brown.cs.student.main;

import edu.brown.cs.student.main.csvReader.HashMapData;
import edu.brown.cs.student.main.csvReader.Reader;
import edu.brown.cs.student.main.csvReader.Student;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * The class for testing Reader object.
 */
public class ReaderTest {

  /**
   * Tests that the Reader object parses the csv files correctly and stores
   * the data in given Data object.
   *
   * Also tests HashMap getData() function.
   */
  @Test
  public void testReader() {
    // Initialize data object and reader object.
    HashMapData data = new HashMapData();
    Reader reader = new Reader(data);

    // call loadData() and pass in proj1_small.csv file for CSV Parser to read
    reader.loadData("data/project1/proj1_small.csv");

    // store data stored in the Data object in a hashmap named hash
    HashMap<String, Student> hash = data.getData();

    // check that the hashmap stores student IDs and student information (name) correctly
    assertEquals(hash.get("1").getName(), "Stanton Swalough");

    // check that reader stores the correct number of lines of data
    assertEquals(data.size(), 20);

    // call laodData() and pass in a new file for CSV Parser to read
    reader.loadData("data/project1/proj1_medium.csv");

    // store data stored in Data object in a hashmap named hash
    hash = (HashMap<String, Student>) reader.getData().getData();

    // make sure Reader object clears old data and stores new data correctly
    assertEquals(hash.get("1").getName(), "Wyatan Luce");
  }

}
