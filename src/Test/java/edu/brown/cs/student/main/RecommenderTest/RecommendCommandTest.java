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

  @Test
  public void testRecommend() {
    ArrayList<String> headersCom = new ArrayList<String>();
    headersCom.add("headers_load");
    headersCom.add("data/project1/header_types.csv");

    ArrayList<String> recsysCom = new ArrayList<String>();
    recsysCom.add("recsys_load");
    recsysCom.add("data/project1/proj1_sprint2_small_1.csv");
    recsysCom.add("data/project1/proj1_sprint2_small_2.csv");
    recsysCom.add("data/project1/proj1_sprint2_small_3.csv");
    recsysCom.add("data/project1/proj1_sprint2_small_4.csv");

    ArrayList<String> recommendCommand = new ArrayList<String>();
    recommendCommand.add("recommend");
    recommendCommand.add("3");
    recommendCommand.add("1000");

    ArrayList<String> recommendCommand2 = new ArrayList<String>();
    recommendCommand2.add("recommend");
    recommendCommand2.add("3");
    recommendCommand2.add("3");

    RecommendCommand recCom = new RecommendCommand();
    assertEquals(recCom.checkCommand(headersCom), "Loaded header types");
    assertEquals(recCom.checkCommand(recommendCommand), "No students found");
    assertEquals(recCom.checkCommand(recsysCom), "Loaded Recommender with k student(s).");
    assertEquals(recCom.checkCommand(recommendCommand), "Target student not found");
    // assertEquals(recCom.checkCommand(recommendCommand2), "19\n6\n5");

  }
}
