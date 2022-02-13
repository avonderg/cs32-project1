package edu.brown.cs.student.main;

public interface KDNode {

  public double[] getCoords();

  public KDNode getLeft();

  public KDNode getRight();

  public void setLeft(KDNode leftNode);

  public void setRight(KDNode rightNode);




}


//havea  kd tree class, and generic with generic
//KDTree<T extends KDTreeNode>
//T might just be like students, studebt doesn't need t