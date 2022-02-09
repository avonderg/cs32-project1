package edu.brown.cs.student.main;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class starTest {

  @Test
  public void testGetID() {
    Star testStar = new Star("1", "", "0", "0", "0");
    String id = testStar.getID();
    assertEquals("1", id);
  }

  @Test
  public void testGetName() {
    Star testStar = new Star("1", "a b", "0", "0", "0");
    String name = testStar.getName();
    assertEquals("a b", name);
  }

  @Test
  public void testGetPosition() {
    Star testStar = new Star("1", "", "0.5", "-7", "100000");
    double[] position = testStar.getPosition();
    assertEquals(3, position.length);
    assertEquals(0.5, position[0], 0.01);
    assertEquals(-7, position[1], 0.01);
    assertEquals(100000, position[2], 0.01);
  }
}
