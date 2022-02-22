package edu.brown.cs.student.main;

public interface Data<T> {

  public void insert(String line);

  public void clear();

  public int size();

  public T getData();

}
