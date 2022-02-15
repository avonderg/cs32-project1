package edu.brown.cs.student.main;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class KDTreeTest {
  public KDTreeTest() {}

  /**
   * Tests that a KDTree with multiple elements is correctly constructed, specifically testing
   * the createKDTree() method.
   */
  @Test
  public void testCreateKDTree() {
    KDNode node1 = new KDNodeTestClass(new double[]{3.0, 4.0, 12.0});
    KDNode node2 = new KDNodeTestClass(new double[]{1.0, 2.0, 5.0});
    KDNode node3 = new KDNodeTestClass(new double[]{10.0, 4.0, 15.0});
    KDNode node4 = new KDNodeTestClass(new double[]{5.0, 11.0, 2.0});
    KDNode node5 = new KDNodeTestClass(new double[]{7.0, 4.0, 6.0});
    List<KDNode> kdNodeList = Arrays.asList(node1, node2, node3, node4, node5);

    KDTree<Student> kdTree = new KDTree<Student>();
    kdTree.treeBuildHelper(kdNodeList);

    assertEquals(kdTree.getRoot(), node4);
    assertEquals(kdTree.getRoot().getLeft(), node1);
    assertEquals(kdTree.getRoot().getRight(), node3);
    assertEquals(kdTree.getRoot().getLeft().getLeft(), node2);
    assertNull(kdTree.getRoot().getLeft().getRight());
    assertNull(kdTree.getRoot().getRight().getRight());
    assertEquals(kdTree.getRoot().getRight().getLeft(), node5);

  }


}
