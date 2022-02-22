package edu.brown.cs.student.main;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ReaderTest {

  @Test
  public void testReader() {
    Data data = new HashMapData();
    Reader reader = new Reader(data);

    reader.loadData("data/project1/proj1_small.csv");
    HashMap<String, Student> hash = (HashMap<String, Student>) reader.getData().getData();
    assertEquals(hash.get("1").getName(), "Stanton Swalough");
    assertEquals(data.size(), 20);

    reader.loadData("data/project1/proj1_medium.csv");
    hash = (HashMap<String, Student>) reader.getData().getData();
    assertEquals(hash.get("1").getName(), "Wyatan Luce");
  }

}
