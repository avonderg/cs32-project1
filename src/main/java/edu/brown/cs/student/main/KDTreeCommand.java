package edu.brown.cs.student.main;

import edu.brown.cs.student.main.kdTree.EuclidianDistance;
import edu.brown.cs.student.main.kdTree.KDInsertable;
import edu.brown.cs.student.main.kdTree.KDTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class models K-d tree commands and acts as the interface between the Main class and the
 * KDTree class.
 */
public class KDTreeCommand implements Command {

  private KDTree<Student> tree;

  /**
   * Constructor for the class that intitializes the tree to null.
   */
  public KDTreeCommand() {
    this.tree = null;

  }

  /**
   * This method is implemented from the command interface, and handles actually executing
   * the commands associated with the k-d tree.
   * @param tokens an array of tokens from the KD class.
   * @return true if the command is applicable to the KDTree, false otherwise
   */
  @Override
  public String checkCommand(List<String> tokens) {
    if (tokens.get(0).equals("load_kd")) {
      HashMapData studentDataMap = new HashMapData();
      Reader studentReader = new Reader(studentDataMap);
      studentReader.loadData(tokens.get(1));
      HashMap<String, Student> studentHashMap = studentDataMap.getData();
      List<KDInsertable> studentList = new ArrayList<>(studentHashMap.values());
      this.tree = new KDTree<Student>(studentList, new EuclidianDistance(), 3);
      return ("Read " + studentList.size() + " students from " + tokens.get(1));
    } else if (tokens.get(0).equals("similar_kd")) {
      if (this.tree == null) {
        return ("ERROR: No tree data has been loaded");
      }
      int k = Integer.parseInt(tokens.get(1));
      if (k < 0) {
        return ("ERROR: k must be greater than 0");
      }
      String targetID = tokens.get(2);
      ArrayList<String> kNeighborsIDs = this.tree.findKNeighbors(targetID, k);
      StringBuilder kNearestNeighbors = new StringBuilder();
      for (String kNeighborsID : kNeighborsIDs) {
        String idToAdd = kNeighborsID + "\n";
        kNearestNeighbors.append(idToAdd);
      }
      return kNearestNeighbors.toString();
    } else {
      return null;
    }
    }
//  @Override
//  public boolean checkCommand(List<String> tokens) {

}

