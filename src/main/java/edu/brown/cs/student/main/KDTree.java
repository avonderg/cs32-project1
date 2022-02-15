package edu.brown.cs.student.main;

import java.util.Collections;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class models a KDTree and handles its construction from a CSV file (insertion of KDNode
 * objects into the tree). The k-nearest neighbors algorithm is also implemented in this class.
 * @param <T>
 */
public class KDTree<T extends KDNode> {

  private int dims;
  private KDNode root;
//private int depth;

  /**
   * Constructor for an instance of KDTree: no parameters, because the tree is actually
   * constructed through the checkCommand, createKDTree, and treeBuildHelper methods.
   */
  public KDTree(List<KDNode> elementList, int dims) {
    // Less concise, but also in-place strategy: anonymous class
    if (elementList.isEmpty()) {
      System.out.println("ERROR: List of students is empty");
    } else {
      this.dims = dims;
      //    this.depth = 0;
      this.root = this.createKDTree(elementList, 0);
    }
  }

  /**
   * This method handles inserting elements into the KDTree from a given list of KDNodes.
   * @param elementList - the list of KDNodes to be inserted
   * @param depth - the initial depth of the tree
   * @return the root of the KDTree
   */
  public KDNode createKDTree(List<KDNode> elementList, int depth) {
    KDNode currNode;

    int listSize = elementList.size();
    if (listSize == 0) {
      return null;
    }
    int currDimension = depth % this.dims;
    Collections.sort(elementList, new SortByCoord(currDimension));
    int mid = elementList.size()/2;
    currNode = elementList.get(mid);
    List<KDNode> leftList = elementList.subList(0, mid);
    List<KDNode> rightList = elementList.subList(mid + 1, listSize);


    currNode.setLeft(this.createKDTree(leftList, depth + 1));
    currNode.setRight(this.createKDTree(rightList, depth + 1));
    return currNode;
  }

  /**
   * Simple getter for the root of the tree
   * @return the root of the tree
   */
  public KDNode getRoot() {
    return this.root;
  }


}
