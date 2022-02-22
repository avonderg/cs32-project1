package edu.brown.cs.student.main.kdTree;

import java.util.Collections;
import java.util.List;
import java.util.*;

/**
 * This class models a KDTree and handles its construction from a CSV file (insertion of KDNode
 * objects into the tree). The k-nearest neighbors algorithm is also implemented in this class.
 * @param <T>
 */
public class KDTree<T extends KDInsertable> {

  private int dims;
  private KDNode root;
  private HashMap<KDInsertable, KDNode> dataToNodeMap; //useful for testing purposes so that we can get the left adn right hcildren of nodes
  private HashMap<String, KDInsertable> userIDtoDataMap; //useful for the k-nearest neighbors algirhtm when given a student id
  private HashMap<KDNode, Double> nodeToDistMap;
  private PriorityQueue<KDNode> nearestNeighborsPQ;
  private ArrayList<KDNode> nodeList;
  private Idist distanceCalculator;
  private double maxKNearestDist;
//private int depth;

  /**
   * Constructor for an instance of KDTree: no parameters, because the tree is actually
   * constructed through the checkCommand, createKDTree, and treeBuildHelper methods.
   */
  public KDTree(List<KDInsertable> elementList, Idist distCalc, int dims) {
    if (elementList.isEmpty()) {
      System.out.println("ERROR: List of students is empty");
    } else {
      this.nodeList = new ArrayList<KDNode>();
      this.dataToNodeMap = new HashMap<KDInsertable, KDNode>();
      this.userIDtoDataMap = new HashMap<String, KDInsertable>();
      this.nodeToDistMap = new HashMap<KDNode, Double>();
      for (KDInsertable element : elementList) {
        this.userIDtoDataMap.put(element.getID(), element);
        KDNode currNode = new KDNode(element);
        nodeList.add(currNode);
        dataToNodeMap.put(element, currNode);
      }
      this.distanceCalculator = distCalc;
      this.dims = dims;
      //    this.depth = 0;
      this.root = this.createKDTree(nodeList, 0);
    }
  }

  //expose setRight and setLeft in interface? no other caller but KDTRee needs to instantiate KDNode,
  //make a private KDNode class.
  //make design: make the KDNode a wrapper class for this interface

  /**
   * This method handles inserting elements into the KDTree from a given list of KDNodes.
   * @param nodeList - the list of KDNodes to be inserted
   * @param depth - the initial depth of the tree
   * @return the root of the KDTree
   */
  public KDNode createKDTree(List<KDNode> nodeList, int depth) {
    KDNode currNode;

    int listSize = nodeList.size();
    if (listSize == 0) {
      return null;
    }
    int currDimension = depth % this.dims;
    Collections.sort(nodeList, new SortByCoord(currDimension));
    int mid = nodeList.size()/2;
    currNode = nodeList.get(mid);

//    int rightListSize = 0;
//    for (int i = 0; i < mid + 1; i++) {
//      rightListSize++;
//    }
//    int leftListSize = 0;
//    for (int i = 0; i < mid + 1; i++) {
//      leftListSize++;
//    }

    List<KDNode> leftList = new ArrayList<KDNode>();
    List<KDNode> rightList = new ArrayList<KDNode>();
    for (int i = 0; i < mid; i++) {
      leftList.add(nodeList.get(i));
    }
    for (int i = mid + 1; i < listSize; i++) {
      rightList.add(nodeList.get(i));
    }
//    List<KDNode> leftList = nodeList.subList(0, mid);
//    List<KDNode> rightList = nodeList.subList(mid + 1, listSize);

    currNode.setLeft(this.createKDTree(leftList, depth + 1));
    currNode.setRight(this.createKDTree(rightList, depth + 1));
    return currNode;
  }

  /**
   * Simple getter for the root of the tree
   * @return the root of the tree
   */
  public KDInsertable getRoot() {
    if (this.root == null) {
      return null;
    }
    return this.root.nodeData;
  }

  /**
   * This getter method gets the left child of a specified node
   * @param node - to get left child of
   * @return the left child
   */
  public KDInsertable getLeft(KDInsertable node) {
    if (this.dataToNodeMap.get(node).getLeft() == null) {
      return null;
    }
    return this.dataToNodeMap.get(node).getLeft().getDataObject();
  }

  /**
   * This getter method gets the right child of a specifed node.
   * @param node - node to get right child of
   * @return the right child
   */
  public KDInsertable getRight(KDInsertable node) {
    if (this.dataToNodeMap.get(node).getRight() == null) {
      return null;
    }
    return this.dataToNodeMap.get(node).getRight().getDataObject();
  }

  /**
   * Function to implememt the k-d tree k-nearest neighbors algorithm. To be implemented for week 2.
   * @return an arraylist with the k nearest neighbors
   * @param targetID - the ID of the node to find nearest neighbors on
   * @param k - number of nearest neighbors to find
   */
  public ArrayList<String> findKNeighbors(String targetID, int k) {
    ArrayList<String> kNeighborsIDList = new ArrayList<String>();

    if (k == 0) {
      return kNeighborsIDList;
    }    if (k < 0) {
      System.out.print("ERROR: k must be greater than 0");
      return kNeighborsIDList;
    }
    this.nodeToDistMap.clear();
    KDNode targetNode = this.dataToNodeMap.get(this.userIDtoDataMap.get(targetID));
    this.maxKNearestDist = Double.POSITIVE_INFINITY;
    this.nearestNeighborsPQ = new PriorityQueue<KDNode>(k, new SortByDist()); //with a Comparator I'll make for node distances

    if (targetNode != null) {
      this.kNNhelper(targetNode, this.root, k, 0);
    } else {
      System.out.println("ERROR: given ID is not in the k-d tree");
      //return
    }

    //implement k nearest neighbors algorithm here and return a list of the k nearest nodes
    while (!nearestNeighborsPQ.isEmpty()) {
      kNeighborsIDList.add(this.nearestNeighborsPQ.remove().getDataObject().getID());
    }
    Collections.reverse(kNeighborsIDList);
    return kNeighborsIDList;
  }


  /**
   * This recursive helper function performs the actual search of the k-d tree for its k-nearest
   * neighbors.
   * @param targetNode - node to find neighbors of
   * @param searchNode - node currently searching
   * @param k - the number of nearest neighbors to find
   * @param depth - the depth of the searchNode
   */
  private void kNNhelper(KDNode targetNode, KDNode searchNode, int k, int depth) {
    //if the node we search happens to be the target node, don't add it to the PQ, but recur on both children
    if (targetNode == searchNode) {
      if (searchNode.getRight() != null) {
        this.kNNhelper(targetNode, searchNode.getRight(), k, depth + 1);
      }
      if (searchNode.getLeft() != null) {
        this.kNNhelper(targetNode, searchNode.getLeft(), k, depth + 1);
      }
      return;
    }

    double distCurrNodeAndTarget = this.distanceCalculator.getDist(targetNode.getCoords(), searchNode.getCoords(), this.dims);

    this.nodeToDistMap.put(searchNode, distCurrNodeAndTarget);
    if (nearestNeighborsPQ.size() < k || distCurrNodeAndTarget < this.nodeToDistMap.get(this.nearestNeighborsPQ.peek())) {
      if (nearestNeighborsPQ.size() >= k) {
        nearestNeighborsPQ.remove();
      }
      nearestNeighborsPQ.add(searchNode);
//      if (distCurrNodeAndTarget > this.maxKNearestDist) {
//        this.maxKNearestDist = distCurrNodeAndTarget;
//      }
    }

    int relevantAxis = depth % this.dims;
    double targetToRelevantAxis = Math.abs(targetNode.getCoords()[relevantAxis] - searchNode.getCoords()[relevantAxis]);
    if (targetToRelevantAxis < this.maxKNearestDist || nearestNeighborsPQ.size() < k) {
      //recur on both (as long they are not null)
      if (searchNode.getLeft() != null) {
        this.kNNhelper(targetNode, searchNode.getLeft(), k, depth + 1);
      }
      if (searchNode.getRight() != null) {
        this.kNNhelper(targetNode, searchNode.getRight(), k, depth + 1);
      }
    } else if (searchNode.getCoords()[relevantAxis] < targetNode.getCoords()[relevantAxis]) {
      //recur on the right child
      if (searchNode.getRight() != null) {
        this.kNNhelper(targetNode, searchNode.getRight(), k, depth + 1);
      }
    } else if (searchNode.getCoords()[relevantAxis] >= targetNode.getCoords()[relevantAxis]) {
      //recur on the left child
      if (searchNode.getLeft() != null) {
        this.kNNhelper(targetNode, searchNode.getLeft(), k, depth + 1);
      }
    }
  }


  class KDNode {
    private KDNode leftChild;
    private KDNode rightChild;
    private KDInsertable nodeData;

    private KDNode(KDInsertable insertableData) {
      this.leftChild = null;
      this.rightChild = null;
      this.nodeData = insertableData;

    }
    /**
     * Simple getter from KDNode interface
     * @return leftChild of the student in the tree
     */
    public KDNode getLeft() {
      return this.leftChild;
    }

    /**
     * Simple getter from KDNode interface
     * @return rightChild of student in tree
     */
    public KDNode getRight() {
      return this.rightChild;
    }

    /**
     * Simple setter for the left child of a node
     * @param leftNode  -left child to be
     */
    public void setLeft(KDNode leftNode) {
      this.leftChild = leftNode;

    }

    /**
     * Simple setter for the right child of a node
     * @param rightNode - right child to be
     */
    public void setRight(KDNode rightNode) {
      this.rightChild = rightNode;

    }

    /**
     * Getter method for the data object that the node wraps.
     * @return the data object implementing the KDInsertable interface (in our use case, the Student)
     */
    public KDInsertable getDataObject() {
      return this.nodeData;
    }

    /**
     * Getter method for this node's set of coordinates.
     * @return an array of doubles with the coords
     */
    public double[] getCoords() {
      return this.nodeData.getCoords();
    }
  }

  /**
   * This class implements the Comparator interface and is used to sort
   * the list of students inputted to the KDTree class
   */
  class SortByCoord implements Comparator<KDNode> {

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

  /**
   * This class implements the Comparator interface and is used to sort the nodes by their distances
   * from a given node for a priority queue in the k-nearest neighbors algorithm.
   */
  class SortByDist implements Comparator<KDNode> {

    public SortByDist() {}
    /**
     * This method, from the Comparator interface, compares the distances of two KDNodes.
     * @param node1 - 1st node to compare distance
     * @param node2 - 2nd node to compare distance
     * @return 1 if node1's currdim>, -1 if node2's currdim >, 0 if equal
     */
    public int compare(KDNode node1, KDNode node2) {
      if (nodeToDistMap.get(node1) == nodeToDistMap.get(node2)) {
        return 0;
      }
      return nodeToDistMap.get(node1) > nodeToDistMap.get(node2) ? -1 : 1;
    }
  }
}