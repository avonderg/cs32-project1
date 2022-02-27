package edu.brown.cs.student.main.repl;

import edu.brown.cs.student.main.bloom.*;
import edu.brown.cs.student.main.csvReader.*;
import edu.brown.cs.student.main.kdTree.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Recommender {

  private int k;
  private HashMap<String, Student> students;
  private Student target;
  private List<Student> selectedStudents;
  private HashMap<String, Integer> similarities;

  public Recommender(String k, HashMap<String, Student> students, String target) {
    this.k = Integer.parseInt(k);
    this.students = students;
    this.target = students.get(target);
    similarities = new HashMap<String, Integer>();
  }

  public String[] getRecommendations() {
    // combined two lists into one
    Set<Student> set = new LinkedHashSet<>(); //getBloomRecommendations());
    set.addAll(getKDRecommendations());
    selectedStudents = new ArrayList<>(set);

    // initialize hashmaps to store vector and euclidean distances of each student
    HashMap<String, Double> vectorDistances = new HashMap<String, Double>();
    HashMap<String, Double> euclideanDistances = new HashMap<String, Double>();

    EuclidianDistance ed = new EuclidianDistance();

    // calculate euclidean distance for each student
    for (Student s : selectedStudents) {
      euclideanDistances.put(s.getID(), ed.getDist(s.getCoords(), target.getCoords(), 3));
      vectorDistances.put(s.getID(), vectorDistances.get(s.getID()));
    }

    // hashmap of normalized and averaged distance information
    HashMap<String, Double> idToDistances = normalize(vectorDistances, euclideanDistances);

    // sorted set of distances
    Set<Double> sorted = sort(idToDistances);

    // retrieve ids of top k students
    ArrayList<String> topKStudents = new ArrayList<String>();
    for (Double dist : sorted) {
      topKStudents.addAll(getKey(idToDistances, dist));
      if (topKStudents.size() >= k) {
        break;
      }
    }
    // remove extra ids (if list is longer than k)
    return (String[]) Arrays.copyOf(topKStudents.toArray(), k);
  }

  /**
   * Returns random key that matches given value.
   * @param map Hashmap where we are looking for keys that match the value.
   * @param value Value who's keys we are looking for.
   * @param <K> Datatype of the key.
   * @param <V> Datatype of the value.
   * @return random key that matches the value.
   */
  private <K, V> ArrayList<K> getKey(Map<K, V> map, V value) {
    ArrayList<K> keys = new ArrayList<K>();
    for (Map.Entry<K, V> entry : map.entrySet()) {
      if (entry.getValue().equals(value)) {
        keys.add(entry.getKey());
      }
    }
    return keys; //keys.get((int) (Math.random() * keys.size()));
  }

  /**
   * Normalize vector and euclidean distances using min-max normalization formula.
   * @param vectorDistances
   * @param euclideanDistances
   * @return HashMap mapping student ID to average of normalized vector/euclidean distances
   */
  private HashMap<String, Double> normalize
      (HashMap<String, Double> vectorDistances, HashMap<String, Double> euclideanDistances) {

    // find the minimums and maximums of vector and euclidean distnace
    double maxV = Collections.max(vectorDistances.values());
    double maxE = Collections.max(euclideanDistances.values());
    double minV = Collections.min(vectorDistances.values());
    double minE = Collections.min(euclideanDistances.values());

    // initalize hashmap that will map student IDs to normalized and averaged distance from target
    HashMap<String, Double> idToDistances = new HashMap<String, Double>();

    // do min-max normalization for both distances for every student
    for (Student s : selectedStudents) {
      double normV = (vectorDistances.get(s.getID())-minV) / (maxV-minV);
      double normE = (euclideanDistances.get(s.getID())-minE) / (maxE-minE);
      idToDistances.put(s.getID(), (normV+normE)/2);
    }

    return idToDistances;
  }

  /**
   * Sort HashMap by distances.
   * @param idToDistances
   * @return Set of sorted, ascending distances
   */
  private Set<Double> sort(HashMap<String, Double> idToDistances) {
    //create ascending list of distances without duplicates
    List<Double> distances = new ArrayList<Double>(idToDistances.values());
    Collections.sort(distances);
    return new LinkedHashSet<Double>(distances);
  }

  /**
   * Get list of recommended students from KD Tree implementation.
   * @return
   */
  private ArrayList<Student> getKDRecommendations() {
    // initialize array list to store student recommendations
    ArrayList<Student> recommendations = new ArrayList<Student>();

    // create list of student objects
    List<KDInsertable> studentList = new ArrayList<>(students.values());

    // create KDTree object
    KDTree<Student> kdTree = new KDTree<Student>(studentList, new EuclidianDistance(), 3);

    // Get top k neighbor IDs
    ArrayList<String> kNeighborsIDs = kdTree.findKNeighbors(target.getID(), k);

    // add student objects to array list using kNeighborIDs
    for (String id : kNeighborsIDs) {
      recommendations.add(students.get(id));
    }

    // return recommendations array list
    return recommendations;
  }

  /**
   * get list of recommended students from Bloom Filter implementation.
   * @return
   */
  private ArrayList<Student> getBloomRecommendations() {
    // initialize array list to store student recommendations
    ArrayList<Student> recommendations = new ArrayList<Student>();

    // initialize Bloom Data object (pass in two hashmaps)
    // first hashmaps maps student ids to their bloom filter object
    // second hashmap maps student ids to their similarities value/vector distances
    BloomData bloomData =
        new BloomData(new HashMap<String, BloomFilter>(), similarities);

    // try-catch block to catch no such algorithm and illegal access exceptions
    try {
      // create tokens list to pass into handleBlooms to
      // populate bloom filter hashmaps (first hashmap)
      List<String> tokens = new ArrayList<String>();
      tokens.add("load_bf");
      tokens.add("data/project1/proj1_big.csv");

      // populates bloom filter hashmaps
      bloomData.handleBlooms(1, students, tokens);

      // create token list to pass into handle blooms to
      // generate similar student recommendations
      tokens.clear();
      tokens.add("similar_bf"); // generate similar students command
      tokens.add(String.valueOf(k)); // k value
      tokens.add(target.getID()); // user id

      // generates similar students and stores them as
      // student objects in array list recommendations
      for (String id : bloomData.handleBlooms(2, students, tokens)) {
        recommendations.add(students.get(id));
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return recommendations;
  }
}
