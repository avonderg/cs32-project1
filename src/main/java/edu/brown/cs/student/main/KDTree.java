package edu.brown.cs.student.main;

import java.util.Collections;
import java.util.List;
import java.util.*;

public class KDTree<T extends KDNode> {

  private int dims;
  private KDNode root;
//  private int depth;

  public KDTree(List<KDNode> elementList, int dims) {
    // Less concise, but also in-place strategy: anonymous class
    this.dims = dims;
//    this.depth = 0;
    this.root = this.createKDTree(elementList, 0, elementList.size() -1, 0);
  }

  public KDNode createKDTree(List<KDNode> elementList, int start, int end, int depth) {
    KDNode currNode;

    if (start > end) {
      return null;
    }
    int currDimension = depth % this.dims;
    Collections.sort(elementList, new SortByCoord(currDimension));
    int mid = (start + end)/2;
    currNode = elementList.get(mid);

    currNode.setLeft(this.createKDTree(elementList, start, mid - 1, depth + 1));
    currNode.setRight(this.createKDTree(elementList, mid + 1, end, depth + 1));
    return currNode;

  }




}
