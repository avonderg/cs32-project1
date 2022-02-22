package edu.brown.cs.student.main;

import java.util.HashMap;


/**
 * This class is used in Reader. Stores CSV data in a hashmap of Student ID's to Student objects.
 */
public class HashMapData implements Data<HashMap<String, Student>> {

  HashMap<String, Student> hash;

  /**
   * Class constructor. Sets hash to a new HashMap object.
   */
  public HashMapData () {
    hash = new HashMap<String, Student>();
  }

  @Override
  public void insert(String line) {

    // initializes student object from csv data line (Student constructor parses the line)
    Student student = new Student(line);

    // Stores the Student in the global HashMap (student ID --> student object)
    hash.put(student.getID(), student);
  }

  @Override
  public void clear() {
    hash.clear();
  }

  @Override
  public int size() {
    return hash.size();
  }

  @Override
  public HashMap<String, Student> getData() {
    return hash;
  }
}
