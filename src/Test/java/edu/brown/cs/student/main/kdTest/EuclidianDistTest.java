package edu.brown.cs.student.main;

import edu.brown.cs.student.main.kdTree.EuclidianDistance;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * This tester class tests the EuclidianDistance class.
 */
public class EuclidianDistTest {

  /**
   * Tests that the getDist() method of the EuclidianDistance class functions correctly; I used an
   * online Euclidian distance calculator to obtain the answer and checked against the function.
   */
  @Test
  public void testGetDist() {
    double[] coords1 = new double[]{3.0, 4.0, 12.0};
    double[] coords2 = new double[]{1.0, 2.0, 5.0};
    EuclidianDistance distCalc = new EuclidianDistance();

    assertEquals(7.55, distCalc.getDist(coords1, coords2, 3), 0.01);

  }

}
