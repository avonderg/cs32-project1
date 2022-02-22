package edu.brown.cs.student.main;

import java.util.Comparator;

/**
 * This is the class that implements the Comparator interface, used to sort
 * the list of students inputted to the KDTree class
 */
public class SortByCoord implements Comparator<KDNode> {

  private int currDim;
  public SortByCoord(int dim) {
    currDim = dim;
  }

  /**
   * This method, from the Comparator interface, handles the actual sorting of KDNodes.
   * @param node1
   * @param node2
   * @return 1 if node1's currdim>, -1 if node2's currdim >, 0 if equal
   */
  public int compare(KDNode node1, KDNode node2) {
    if (node1.getCoords()[this.currDim] == node2.getCoords()[this.currDim]) {
      return 0;
//      int random = (int) (Math.random() * 2);
//      if (random == 1) {
//        return 1;
//      } else {
//        return -1;
//      }
    }
    return node1.getCoords()[this.currDim] > node2.getCoords()[this.currDim] ? 1 : -1;
  }
}
