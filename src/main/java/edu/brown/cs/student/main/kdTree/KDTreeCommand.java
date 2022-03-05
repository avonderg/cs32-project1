package edu.brown.cs.student.main.kdTree;

import edu.brown.cs.student.main.repl.Command;
import edu.brown.cs.student.main.csvReader.HashMapData;
import edu.brown.cs.student.main.csvReader.Reader;
import edu.brown.cs.student.main.csvReader.Student;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
      studentReader.loadData(new String[] {tokens.get(1)});
      HashMap<String, Student> studentHashMap = studentDataMap.getData();
      List<KDInsertable> studentList = new ArrayList<>(studentHashMap.values());
      this.tree = new KDTree<Student>(studentList, new EuclidianDistance(), 3);
      return ("Read " + studentList.size() + " students from " + tokens.get(1));
    } else if (tokens.get(0).equals("similar_kd")) {
      if (this.tree == null) {
        return ("ERROR: No tree data has been loaded");
      }
      int k = Integer.parseInt(tokens.get(1));

      String targetID = tokens.get(2);
      ArrayList<String> kNeighborsIDs = this.tree.findKNeighbors(targetID, k);

      for (String neighborID : kNeighborsIDs) {
        System.out.println(neighborID);
      }
      return "";
    } else {
      return null;
    }
  }
}

