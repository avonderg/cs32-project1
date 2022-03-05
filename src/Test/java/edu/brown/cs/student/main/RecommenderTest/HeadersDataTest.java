package edu.brown.cs.student.main.RecommenderTest;

import edu.brown.cs.student.main.csvReader.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeadersDataTest {

  @Test
  public void testHeaders() {
    HeadersData headersData = new HeadersData();
    headersData.insert("id,qualitative");
    assertEquals(headersData.size(), 1);
    assertEquals(headersData.getData().get("id"), "qualitative");
    headersData.clear();
    assertEquals(headersData.size(), 0);
  }
}
