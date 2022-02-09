package edu.brown.cs.student.main;

public class Star {
  private String id;
  private String name;
  private double x;
  private double y;
  private double z;

  public Star(String id, String name, String x, String y, String z) {
    this.id = id;
    this.name = name;
    this.x = Double.parseDouble(x);
    this.y = Double.parseDouble(y);
    this.z = Double.parseDouble(z);
  }

  public String getID() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public double[] getPosition() {
    double[] pos = new double[3];
    pos[0] = x;
    pos[1] = y;
    pos[2] = z;
    return pos;
  }
}
