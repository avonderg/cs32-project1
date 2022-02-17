package edu.brown.cs.student.main;

public interface KDNode {

  double[] getCoords();

  KDNode getLeft();

  KDNode getRight();

  void setLeft(KDNode leftNode);

  void setRight(KDNode rightNode);




}


//havea  kd tree class, and generic with generic
//KDTree<T extends KDTreeNode>
//T might just be like students, studebt doesn't need t