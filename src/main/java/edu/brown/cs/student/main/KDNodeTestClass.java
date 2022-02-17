package edu.brown.cs.student.main;

public class KDNodeTestClass implements KDNode{

  private KDNode leftChild;
  private KDNode rightChild;
  private double[] coords;

  public KDNodeTestClass(double[] coords) {
    this.coords = coords;
  }

  @Override
  public double[] getCoords() {
    return this.coords;
  }

  @Override
  public KDNode getLeft() {
    return this.leftChild;
  }

  @Override
  public KDNode getRight() {
    return this.rightChild;
  }

  @Override
  public void setLeft(KDNode leftNode) {
    this.leftChild = leftNode;

  }

  @Override
  public void setRight(KDNode rightNode) {
    this.rightChild = rightNode;

  }
}
