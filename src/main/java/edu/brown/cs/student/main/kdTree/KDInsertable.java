package edu.brown.cs.student.main.kdTree;

/**
 * This interface models anything that is to be inserted into the KDTree as a KDNode
 */
public interface KDInsertable {

  /**
   * Each insertable must have a getCoords method that returns a double array of coords.
   * @return double arr of coords
   */
  double[] getCoords();

  /**
   * Each insertable must have a getID() method that returns a String id.
   * @return id String
   */
  String getID();


}