package edu.brown.cs.student.main.repl;

import edu.brown.cs.student.main.csvReader.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Recommender {

  private int k;
  private HashMap<String, Student> students;
  private Student target;

  public Recommender(String k, HashMap<String, Student> students, String target) {
    this.k = Integer.parseInt(k);
    this.students = students;
    this.target = students.get(target);
  }

  public ArrayList<String> getRecommendations() {

    // student list from bloomFilter and kdTree
    ArrayList<Student> bloomStudents = new ArrayList<Student>();
    ArrayList<Student> kdStudents = new ArrayList<Student>();

    // combined two lists into one
    Set<Student> set = new LinkedHashSet<>(bloomStudents);
    set.addAll(kdStudents);
    List<Student> combined = new ArrayList<>(set);

    // initialize hashmaps to store vector and euclidean distances of each student
    HashMap<String, Double> vectorDistances = new HashMap<String, Double>();
    HashMap<String, Double> euclideanDistances = new HashMap<String, Double>();

    // calculate vectorDistance and euclidean distance for each student
    for (Student s : combined) {
      double vectorDistance = 0;
      double euclideanDistance = 0;
    }

    // find the minimums and maximums of vector and euclidean distnace
    double maxV = Collections.max(vectorDistances.values());
    double maxE = Collections.max(euclideanDistances.values());
    double minV = Collections.min(vectorDistances.values());
    double minE = Collections.min(euclideanDistances.values());

    // initalize hashmap that will map student IDs to normalized and averaged distance from target
    HashMap<String, Double> idToDistances = new HashMap<String, Double>();

    // do min-max normalization for both distances for every student
    for (Student s : combined) {
      double normalizedV = (vectorDistances.get(s.getID())-minV) / (maxV-minV);
      double normalizedE = (euclideanDistances.get(s.getID())-minE) / (maxE-minE);
      idToDistances.put(s.getID(), (normalizedV+normalizedE)/2);
    }

    //create ascending list of distances without duplicates
    List<Double> distances = new ArrayList<Double>(idToDistances.values());
    Collections.sort(distances);
    Set<Double> distancesSet = new LinkedHashSet<Double>(distances);

    ArrayList<String> topKStudents = new ArrayList<String>();

    // retrieve id of star at top k distances
    int count = 0;
    for (Double dist : distancesSet) {
      String id = getKey(idToDistances, dist);
      topKStudents.add(id);
      count++;
      if (count >= k) {
        break;
      }
    }
    return topKStudents;
  }

  /**
   * Returns random key that matches given value.
   * @param map Hashmap where we are looking for keys that match the value.
   * @param value Value who's keys we are looking for.
   * @param <K> Datatype of the key.
   * @param <V> Datatype of the value.
   * @return random key that matches the value.
   */
  private <K, V> K getKey(Map<K, V> map, V value) {
    ArrayList<K> keys = new ArrayList<K>();
    for (Map.Entry<K, V> entry : map.entrySet()) {
      if (entry.getValue().equals(value)) {
        keys.add(entry.getKey());
      }
    }
    return keys.get((int) (Math.random() * keys.size()));
  }
}
