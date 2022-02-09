package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StarMethods {

  private HashMap<Integer, Star> starData = new HashMap<Integer, Star>();

  /**
   * Class constructor.
   * @param starData hashmap of star ID to Star Object
   */
  public StarMethods(HashMap<Integer, Star> starData) {
    this.starData = starData;
  }

  /**
   * Checks argument commands ("stars" or "naive_neighbors").
   * @param tokens Array of tokens in the text line
   * @return return string describing the command type (for testing)
   */
  public String checkArgs(List<String> tokens) {
    if (tokens.get(0).equals("stars")) { // stars command
      storeStars(tokens);
      return "stars";
    } else if (tokens.get(0).equals("naive_neighbors")) { // find neighbors command
      checkNeighbors(tokens);
      return "neighbors";
    } else {
      System.out.println("ERROR: command not found"); // other
      return "error";
    }
  }

  /**
   * Stores the stars found in the csv file in global hashMap starData.
   * @param tokens Array of tokens in the text line
   */
  private void storeStars(List<String> tokens) {
    if (tokens.size() == 1) {
      System.out.println("ERROR: no file name found");
      return;
    }
    String fileName = tokens.get(1);
    Path filePath  = Paths.get(fileName);

    try (BufferedReader reader = Files.newBufferedReader(filePath)) { // read file
      starData.clear(); // clears out old hashmap
      String line = reader.readLine(); // read header first, don't add to hashmap
      while ((line = reader.readLine()) != null) {
        String[] attributes = line.split(","); // split up data into a list
        starData.put(Integer.parseInt(attributes[0]),
          new Star(attributes[0], attributes[1], attributes[2], attributes[3], attributes[4]));
      }
      System.out.println("Read " + starData.size() + " stars from " + fileName);
    } catch (IOException ie) {
      System.out.println("ERROR: File not found.");
    }
  }

  /**
   * K-nearest neighbors algorithm.
   * @param tokens Array of tokens in the text line
   */
  private void checkNeighbors(List<String> tokens) {
    if (this.starData.isEmpty()) { // if no star positions are stored
      System.out.println("ERROR: No stars found.");
      return;
    }
    int k = Integer.parseInt(tokens.get(1)); // number of neighbors to find
    if (tokens.size() == 3) { // if user passed in a star name
      if (!tokens.get(2).contains("\"")) {
        System.out.println("ERROR: Name must be in quotations");
        return;
      }
      String name = tokens.get(2).replace("\"", "");
      starData.forEach((id, star) -> {
        if (star.getName().equals(name)) {
          double[] position = star.getPosition();
          findNeighbors(k, position, star.getID());
        }
      });
    } else if (tokens.size() == 5) { // if user passed in star coordinates
      double[] position = new double[3];
      position[0] = Double.parseDouble(tokens.get(2));
      position[1] = Double.parseDouble(tokens.get(3));
      position[2] = Double.parseDouble(tokens.get(4));
      findNeighbors(k, position, "id");
    } else {
      System.out.println("ERROR: Star not found.");
    }
  }

  /**
   * Prints out top K neighbors of a star.
   * Star in question is the star who's neighbors we are looking for.
   * @param k number of neighbors we want to find.
   * @param position position of the star in question
   * @param flag ID of the star in question
   */
  private void findNeighbors(int k, double[] position, String flag) {
    if (k <= 0) {
      return;
    }
    // hashmap to store distances of each star to the star in question
    HashMap<String, Double> idToDistances = new HashMap<String, Double>();
    // iterate through the global hashmap of star data to store all the distances
    starData.forEach((id, star) -> {
      if (!star.getID().equals(flag)) {
        idToDistances.put(star.getID(), findDistance(position, star.getPosition()));
      }
    });
    //create ascending list of distances without duplicates
    List<Double> distances = new ArrayList<Double>(idToDistances.values());
    Collections.sort(distances);
    Set<Double> distancesSet = new LinkedHashSet<Double>(distances);

    // retrieve id of star at top k distances
    int count = 0;
    for (Double dist : distancesSet) {
      String id = getKey(idToDistances, dist);
      System.out.println(id);
      count++;
      if (count >= k) {
        break;
      }
    }
  }

  /**
   * Returns random key that matches given value.
   * @param map Hashmap where we are looking for keys that match the value.
   * @param value Value who's keys we are looking for.
   * @param <K> Datatype of the key.
   * @param <V> Datatype of the value.
   * @return random key that matches the value.
   */
  public <K, V> K getKey(Map<K, V> map, V value) {
    ArrayList<K> keys = new ArrayList<K>();
    for (Map.Entry<K, V> entry : map.entrySet()) {
      if (entry.getValue().equals(value)) {
        keys.add(entry.getKey());
      }
    }
    return keys.get((int) (Math.random() * keys.size()));
  }

  /**
   * 3D distance formula.
   * @param pos1 Arraylist of x, y, z coordinates
   * @param pos2 Arraylist of x, y, z coordinates
   * @return distance between two points
   */
  public double findDistance(double[] pos1, double[] pos2) {
    // 3D distance formula
    return Math.sqrt(Math.pow(pos1[0] - pos2[0], 2)
      + Math.pow(pos1[1] - pos2[1], 2)
      + Math.pow(pos1[2] - pos2[2], 2));
  }

}
