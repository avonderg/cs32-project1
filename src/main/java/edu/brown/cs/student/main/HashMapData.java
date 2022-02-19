package edu.brown.cs.student.main;

import java.util.HashMap;

public class HashMapData implements Data{

  HashMap<String, Student> hash;

  public HashMapData () {
    hash = new HashMap<String, Student>();
  }

  @Override
  public void insert(String line) {
    Student student = new Student(line);
    hash.put(student.getID(), student);
  }

  @Override
  public void clear() {
    hash.clear();
  }

  @Override
  public int size() {
    return hash.size();
  }

  public HashMap<String, Student> getData() {
    return hash;
  }
}
