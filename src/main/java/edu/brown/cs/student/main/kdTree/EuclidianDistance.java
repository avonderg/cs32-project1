package edu.brown.cs.student.main.kdTree;


/**
 * This class implements the Idist interface and provides the default method of calculation for k-d
 * tree similarity between students, the Euclidian distance method.
 */
public class EuclidianDistance implements Idist {

  /**
   * Function implemented from the Idist interface that calculates the euclidian distance between two
   * nodes.
   * @param coords1 - the first node's coords
   * @param coords2 - 2nd node's coords
   * @param dims - the # of dimensions to work with
   * @return - the Euclidian distance between the two given nodes.
   */
  @Override
  public double getDist(double[] coords1, double[] coords2, int dims) {
    double distance = 0;
    for (int i = 0; i < dims; i++) {
      distance += Math.pow(coords1[i] - coords2[i], 2);
    }
    return Math.sqrt(distance);
  }
}
