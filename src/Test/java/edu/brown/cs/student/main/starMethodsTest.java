package edu.brown.cs.student.main;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class starMethodsTest {
  @Test
  public void testFindDistance() {
    StarMethods testStarMethods = new StarMethods(new HashMap<Integer, Star>());
    double[] position1 = new double[] {0, 0, 0};
    double[] position2 = new double[] {1, 1, 1};
    assertEquals(testStarMethods.findDistance(position1, position2), Math.sqrt(3), 0.01);
  }

  @Test
  public void testGetKey() {
    StarMethods testStarMethods = new StarMethods(new HashMap<Integer, Star>());
    HashMap<String, Double> testMap1 = new HashMap<String, Double>();
    testMap1.put("a", 0.5);
    testMap1.put("b", 0.1);
    assertEquals(testStarMethods.getKey(testMap1, 0.5), "a");

    HashMap<String, Double> testMap2 = new HashMap<String, Double>();
    testMap2.put("a", 0.5);
    testMap2.put("b", 0.5);
    String key = testStarMethods.getKey(testMap2, 0.5);
    Boolean test = key.equals("a") || key.equals("b");
    assertEquals(test, true);
  }

  @Test
  public void testStoreStars() {
    HashMap<Integer, Star> starData = new HashMap<Integer, Star>();
    StarMethods testStarMethods = new StarMethods(starData);

    List<String> starsCommand = new ArrayList<>();
    starsCommand.add("stars");
    starsCommand.add("data/stars/one-star.csv");

    testStarMethods.checkArgs(starsCommand);
    assertEquals(starData.get(1).getID(), "1");
    assertEquals(starData.get(1).getName(), "Lonely Star");
  }

  @Test
  public void testCheckArgs() {
    HashMap<Integer, Star> starData = new HashMap<Integer, Star>();
    StarMethods testStarMethods = new StarMethods(starData);

    List<String> starsCommand = new ArrayList<>();
    starsCommand.add("stars");
    starsCommand.add("data/stars/ten-star.csv");
    assertEquals(testStarMethods.checkArgs(starsCommand), "stars");

    List<String> neighborsCommand1 = new ArrayList<>();
    neighborsCommand1.add("naive_neighbors");
    neighborsCommand1.add("5");
    neighborsCommand1.add("Sol");
    assertEquals(testStarMethods.checkArgs(neighborsCommand1), "neighbors");

    List<String> neighborsCommand2 = new ArrayList<>();
    neighborsCommand2.add("naive_neighbors");
    neighborsCommand2.add("-1");
    neighborsCommand2.add("Sol");
    assertEquals(testStarMethods.checkArgs(neighborsCommand2), "neighbors");

    List<String> neighborsCommand3 = new ArrayList<>();
    neighborsCommand3.add("naive_neighbors");
    neighborsCommand3.add("1");
    neighborsCommand3.add("\"Sol\"");
    assertEquals(testStarMethods.checkArgs(neighborsCommand2), "neighbors");

    List<String> errorCommand = new ArrayList<>();
    errorCommand.add("neighboars");
    errorCommand.add("5");
    errorCommand.add("Sol");
    assertEquals(testStarMethods.checkArgs(errorCommand), "error");
  }

  public static class KDTreeTest {
  }
}
