package edu.brown.cs.student.main.csvReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentData implements Data<HashMap<String, HashMap<String, Object>>> {

  HashMap<String, HashMap<String, Object>> data;
  HashMap<String, String> headersHash;
  ArrayList<String> headers;

  public StudentData(HashMap<String, String> headers) {
    data = new HashMap<String, HashMap<String, Object>>();
    this.headers = new ArrayList<String>();
    this.headersHash = headers;
  }

  @Override
  public void insert(String line) {
    Matcher m = Pattern.compile("(\".*?\")|([^\",*?\"]+)").matcher(line);

    // first item in the csv is always the student's id
    m.find();
    String id = m.group();

    // if student is not yet stored in the hashmap, insert it
    if (!data.containsKey(id)) {
      data.put(id, new HashMap<String, Object>());
    }

    for (String attr : headers ) {
      m.find();
      String val = m.group();
      if (headersHash.get(attr) == "quantitative") {
        data.get(id).put(attr, Integer.parseInt(val));
      }
      else {
        data.get(id).put(attr, val);
      }
    }
  }

  @Override
  public void clear() {

  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public HashMap<String, HashMap<String, Object>> getData() {
    return data;
  }

  @Override
  public void readHeaders(String line) {
    Matcher m = Pattern.compile("(\".*?\")|([^\",*?\"]+)").matcher(line);
    while(m.find()) {
      headers.add(m.group());
    }
  }
}
