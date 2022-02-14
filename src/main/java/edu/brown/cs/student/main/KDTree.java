package edu.brown.cs.student.main;

import java.util.Collections;
import java.util.List;
import java.util.*;

public class KDTree<T extends KDNode> {

  private int dims;
  private KDNode root;
//private int depth;

  public KDTree(List<KDNode> elementList, int dims) {
    // Less concise, but also in-place strategy: anonymous class
    this.dims = dims;
//    this.depth = 0;
    this.root = this.createKDTree(elementList, 0);
  }

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

  public KDNode getRoot() {
    return this.root;
  }




}
