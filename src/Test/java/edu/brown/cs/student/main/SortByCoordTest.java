//package edu.brown.cs.student.main;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Testing class to test SortByDist, the comparator-implementing class.
// */
//public class SortByCoordTest {
//
//  public SortByCoordTest() {
//  }
//
//  /**
//   * This tests that the comparator implementing class functions correctly
//   */
//  @Test
//  public void testComparator() {
//    SortByCoord comparator = new SortByCoord(0);
//    KDNode node1 = new KDNodeTestClass(new double[]{3.0, 4.0, 12.0});
//    KDNode node2 = new KDNodeTestClass(new double[]{1.0, 2.0, 5.0});
//    assertEquals(1.0, (double) comparator.compare(node1, node2), 0.01);
//    assertEquals(-1.0, (double) comparator.compare(node2, node1), 0.01);
//  }
//
//}
