package edu.brown.cs.student.main.kdTree;

/**
 * This interface models a distance calculator between any two coordinates of a certain dimension.
 * It allows the programmer to choose the method of calculation for the distance between any 2 coordinates.
 */
public interface Idist {

  /**
   * Calculates the distance between 2 coords.
   * @param coords1 - 1st set of coords
   * @param coords2 - 2nd set of coords
   * @param dim - # of dims of the coords
   * @return - the distance between the 2 coords using any measure
   */
  double getDist(double[] coords1, double[] coords2, int dim);

}
