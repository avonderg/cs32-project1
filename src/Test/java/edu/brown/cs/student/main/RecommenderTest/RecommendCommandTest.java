package edu.brown.cs.student.main.RecommenderTest;

import edu.brown.cs.student.main.csvReader.*;
import edu.brown.cs.student.main.repl.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class RecommendCommandTest {

  @Test
  public void testHeadersLoad() {
    ArrayList<String> tokens = new ArrayList<String>();
    tokens.add("headers_load");
    tokens.add("data/project1/header_types.csv");

    RecommendCommand recCom = new RecommendCommand();

    assertEquals(recCom.checkCommand(tokens), "Loaded header types");

    HeadersData hData = new HeadersData();
    Reader reader = new Reader(hData);
    reader.loadData(new String[]{"data/project1/header_types.csv"});
    HashMap<String, String> headersHash = hData.getData();
    assertEquals(headersHash.get("id"), "qualitative");
  }

  @Test
  public void testRecsysLoad() {
    ArrayList<String> tokens = new ArrayList<String>();
    tokens.add("recsys_load");
    tokens.add("data/project1/proj1_sprint2_small_1.csv");
    tokens.add("data/project1/proj1_sprint2_small_2.csv");
    tokens.add("data/project1/proj1_sprint2_small_3.csv");
    tokens.add("data/project1/proj1_sprint2_small_4.csv");

    ArrayList<String> tokens1 = new ArrayList<String>();
    tokens1.add("headers_load");
    tokens1.add("data/project1/header_types.csv");

    RecommendCommand recCom = new RecommendCommand();
    assertEquals(recCom.checkCommand(tokens), "Headers not loaded");
    assertEquals(recCom.checkCommand(tokens1), "Loaded header types");
    assertEquals(recCom.checkCommand(tokens), "Loaded Recommender with k student(s).");
  }
}
