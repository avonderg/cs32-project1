package edu.brown.cs.student.main;

/**
 * The Reader class will only accept objects that extend this interface.
 * Allows for storing data in various data structures.
 */
public interface Data<T> {

  /**
   * Parses the line from the csv and stores it in a data structure.
   * @param line
   */
  public void insert(String line);

  /**
   * Clears any information stored in the data structure.
   */
  public void clear();

  /**
   * Returns the current size of the data structure (how much information is stored).
   * @return
   */
  public int size();

  /**
   * Returns the data structure object.
   */
  public T getData();

}
