package edu.brown.cs.student.main;
import edu.brown.cs.student.main.kdTree.EuclidianDistance;
import edu.brown.cs.student.main.kdTree.KDInsertable;
import edu.brown.cs.student.main.kdTree.KDNodeTestClass;
import edu.brown.cs.student.main.kdTree.KDTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class KDTreeTest {
  public KDTreeTest() {}

  /**
   * Tests that a KDTree with multiple elements is correctly constructed, specifically testing
   * the createKDTree() method.
   */
  @Test
  public void testCreateKDTree() {
    KDInsertable node1 = new KDNodeTestClass(new double[]{3.0, 4.0, 12.0}, "1");
    KDInsertable node2 = new KDNodeTestClass(new double[]{1.0, 2.0, 5.0}, "2");
    KDInsertable node3 = new KDNodeTestClass(new double[]{10.0, 4.0, 15.0}, "3");
    KDInsertable node4 = new KDNodeTestClass(new double[]{5.0, 11.0, 2.0}, "4");
    KDInsertable node5 = new KDNodeTestClass(new double[]{7.0, 4.0, 6.0}, "5");
    List<KDInsertable> kdDataList = Arrays.asList(node1, node2, node3, node4, node5);

    KDTree<Student> kdTree = new KDTree<Student>(kdDataList, new EuclidianDistance(), 3);

    assertEquals(kdTree.getRoot(), node4);
    KDInsertable leftChild = kdTree.getLeft(kdTree.getRoot());
    KDInsertable rightChild = kdTree.getRight(kdTree.getRoot());

    assertEquals(kdTree.getLeft(kdTree.getRoot()), node1);
    assertEquals(kdTree.getRight(kdTree.getRoot()), node3);//    assertEquals(kdTree.getRoot().getLeft().getLeft(), node2);
    assertNull(kdTree.getRight(leftChild));
    assertNull(kdTree.getRight(rightChild));
    assertEquals(kdTree.getLeft(rightChild), node5);

  }

  /**
   * Tests that the k-nearest neighbors of a KD tree are correctly found when 1 neighbor is requested.
   */
  @Test
  public void test1NearestNeighbor() {
    KDInsertable node1 = new KDNodeTestClass(new double[]{1.0, 3.0, 6.0}, "1");
    KDInsertable node2 = new KDNodeTestClass(new double[]{1.0, 2.0, 5.0}, "2");
    KDInsertable node3 = new KDNodeTestClass(new double[]{10.0, 4.0, 15.0}, "3");
    KDInsertable node4 = new KDNodeTestClass(new double[]{5.0, 11.0, 2.0}, "4");
    KDInsertable node5 = new KDNodeTestClass(new double[]{7.0, 4.0, 6.0}, "5");
    List<KDInsertable> kdDataList = Arrays.asList(node1, node2, node3, node4, node5);

    KDTree<Student> kdTree = new KDTree<Student>(kdDataList, new EuclidianDistance(), 3);

    ArrayList<String> kNeighborsIDList = kdTree.findKNeighbors("2", 1);
    assertEquals("1", kNeighborsIDList.remove(0));
//    assertEquals("5", kNeighborsIDQueue.remove());
  }

  /**
   * Tests that the k-nearest neighbors of a KD tree are correctly found when multiple neighbors are requested.
   */
  @Test
  public void testkNearestNeighbors() {
    KDInsertable node1 = new KDNodeTestClass(new double[]{1.0, 3.0, 6.0}, "1");
    KDInsertable node2 = new KDNodeTestClass(new double[]{1.0, 2.0, 5.0}, "2");
    KDInsertable node3 = new KDNodeTestClass(new double[]{10.0, 4.0, 15.0}, "3");
    KDInsertable node4 = new KDNodeTestClass(new double[]{5.0, 11.0, 2.0}, "4");
    KDInsertable node5 = new KDNodeTestClass(new double[]{7.0, 4.0, 6.0}, "5");
    List<KDInsertable> kdDataList = Arrays.asList(node1, node2, node3, node4, node5);

    KDTree<Student> kdTree = new KDTree<Student>(kdDataList, new EuclidianDistance(), 3);

    ArrayList<String> kNeighborsIDList = kdTree.findKNeighbors("2", 3);
    assertEquals("1", kNeighborsIDList.get(0));
    assertEquals("5", kNeighborsIDList.get(1));
    assertEquals("4", kNeighborsIDList.get(2));
  }

  /**
   * Tests that no elementID's are returned when 0 nearest neighbors are requested.
   */
  @Test
  public void test0NearestNeighbor() {
    KDInsertable node1 = new KDNodeTestClass(new double[]{1.0, 3.0, 6.0}, "1");
    KDInsertable node2 = new KDNodeTestClass(new double[]{1.0, 2.0, 5.0}, "2");
    KDInsertable node3 = new KDNodeTestClass(new double[]{10.0, 4.0, 15.0}, "3");
    KDInsertable node4 = new KDNodeTestClass(new double[]{5.0, 11.0, 2.0}, "4");
    KDInsertable node5 = new KDNodeTestClass(new double[]{7.0, 4.0, 6.0}, "5");
    List<KDInsertable> kdDataList = Arrays.asList(node1, node2, node3, node4, node5);

    KDTree<Student> kdTree = new KDTree<Student>(kdDataList, new EuclidianDistance(), 3);

    ArrayList<String> kNeighborsIDList = kdTree.findKNeighbors("2", 0);
    assertTrue("1", kNeighborsIDList.size() == 0);
  }



  /**
   * Tests that an empty KDTree can be instantiated, but that the root will be null.
   */
  @Test
  public void testEmptyTree() {
    List<KDInsertable> kdNodeList = new ArrayList<KDInsertable>();
    KDTree<Student> kdTree = new KDTree<Student>(kdNodeList, new EuclidianDistance(), 3);
    assertNull(kdTree.getRoot());

  }


}
