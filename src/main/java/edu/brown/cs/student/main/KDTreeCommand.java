package edu.brown.cs.student.main;

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
  public boolean checkCommand(List<String> tokens) {
    if (tokens.get(0).equals("load_kd")) {
      Reader studentReader = new Reader();
      studentReader.loadData(tokens.get(1));
      HashMap<String, Student> studentHashMap = studentReader.getData();
      List<KDNode> studentList = new ArrayList<>(studentHashMap.values());
      this.tree = new KDTree<Student>(studentList, 3);
      System.out.println("Read " + studentList.size() + " students from " + tokens.get(1));
      return true;
    } else {
      System.out.println("ERROR: invalid command");
      return false;
    }
  }
}
