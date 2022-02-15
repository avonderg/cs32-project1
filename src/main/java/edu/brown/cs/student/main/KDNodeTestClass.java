package edu.brown.cs.student.main;

/**
 * Dummy class to help test the KDTree using unit tests.
 */
public class KDNodeTestClass implements KDNode{

  private KDNode leftChild;
  private KDNode rightChild;
  private double[] coords;

  public KDNodeTestClass(double[] coords) {
    this.coords = coords;
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
   * Simple getter from the KDNode interface
   * @return left child in the tree
   */
  @Override
  public KDNode getLeft() {
    return this.leftChild;
  }

  /**
   * Simple getter from the KDNode interface
   * @return right child in the tree
   */
  @Override
  public KDNode getRight() {
    return this.rightChild;
  }

  /**
   * Simple setter for the left child
   * @param leftNode - the left child to be
   */
  @Override
  public void setLeft(KDNode leftNode) {
    this.leftChild = leftNode;

  }

  /**
   * Simple setter for the right child
   * @param rightNode - the right child to be
   */
  @Override
  public void setRight(KDNode rightNode) {
    this.rightChild = rightNode;

  }
}
