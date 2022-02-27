package edu.brown.cs.student.main.csvReader;

import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HeadersData class that is passed into reader to store header information appropriately.
 */
public class HeadersData implements Data<HashMap<String, String>>{

  // Maps header information: attribute --> qualitative or quantitative
  HashMap<String, String> headersHash = new HashMap<String, String>();

  @Override
  public void insert(String line) {
    // initialize matcher to parse line
    Matcher m = Pattern.compile("(\".*?\")|([^\",*?\"]+)").matcher(line);

    // store first item in line as attribute
    m.find();
    String attribute = m.group();

    // store next item in line as type (qualitative or quantitative) and
    // set as lower case (inconsistent capitalization in csv file)
    m.find();
    String type = m.group().toLowerCase(Locale.ROOT).replaceAll(" ", "");

    // store attribute name and type information in global hashmap
    headersHash.put(attribute, type);
  }

  @Override
  public void clear() {
    headersHash.clear();
  }

  @Override
  public int size() {
    return headersHash.size();
  }

  @Override
  public HashMap<String, String> getData() {
    return headersHash;
  }

  @Override
  public void readHeaders(String line) {
  }
}
