package edu.brown.cs.student.main.kdTree;

import edu.brown.cs.student.main.kdTree.KDInsertable;

/**
 * Dummy class to help test the KDTree using unit tests.
 */
public class KDNodeTestClass implements KDInsertable {

  private KDInsertable leftChild;
  private KDInsertable rightChild;
  private double[] coords;
  private String ID;

  public KDNodeTestClass(double[] coords, String ID) {
    this.coords = coords;
    this.ID = ID;
  }

  /**
   * Implements from the interface.
   * @return coords that represent the values of the three relevant attributes.
   */
  @Override
  public double[] getCoords() {
    return this.coords;
  }

  /**
   * Implements from the interface -- gets the ID of the student
   * @return the String ID of the student instance
   */
  @Override
  public String getID() {
    return this.ID;
  }

}
