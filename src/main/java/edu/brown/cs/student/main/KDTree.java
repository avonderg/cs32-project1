package edu.brown.cs.student.main;

import java.util.Collections;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class KDTree<T extends KDNode> implements Command {

  private int dims;
  private KDNode root;
//private int depth;

  public KDTree() {
    // Less concise, but also in-place strategy: anonymous class
    this.dims = 3;
    this.root = null;
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

  public KDNode treeBuildHelper(List<KDNode> elementList) {
    this.root = this.createKDTree(elementList, 0);
    return this.root;
  }


  @Override
  public boolean checkCommand(List<String> tokens) {
    if (tokens.get(0).equals("load_kd")) {
      Reader studentReader = new Reader();
      studentReader.loadData(tokens.get(1));
      HashMap<String, Student> studentHashMap = studentReader.getData();
      List<KDNode> studentList = new ArrayList<>(studentHashMap.values());
      this.treeBuildHelper(studentList);
      this.dims = 3;
      System.out.println("Read " + studentList.size() + " students from" + tokens.get(1));
      return true;
    } else {
      System.out.println("ERROR: invalid command");
      return false;
    }
  }
}
