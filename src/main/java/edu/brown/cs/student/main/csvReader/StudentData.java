package edu.brown.cs.student.main.csvReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that is passed in to Reader object to parse and store student information appropriately.
 */
public class StudentData implements Data<HashMap<String, HashMap<String, Object>>> {

  HashMap<String, HashMap<String, Object>> data;
  HashMap<String, String> headersHash;
  ArrayList<String> headers;

  /**
   * Student Data constructor. Initializes data and headers to new objects.
   * Stores passed in headers information.
   * @param headers information about header types (qualitative vs quantitative)
   */
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
      // skip the first attr (id) because we've already stored it
      if ( attr.equals("id")) {
        continue;
      }

      m.find();
      String val = m.group();

      //System.out.println(line);
      //System.out.println("attr: " + attr + ", val: " + val+ ", id :" + id);

      // headers are in different formatting, so check for all ("s" at the end or not)
      if (headersHash.containsKey(attr)){
        insertAttribute(attr, id, val);
      } else if (headersHash.containsKey(attr+"s")){
        insertAttribute(attr+"s", id, val);
      } else if (headersHash.containsKey(attr.substring(0, attr.length()-1))) {
        insertAttribute(attr.substring(0, attr.length()-1), id, val);
      } else { // if header is not contained in header arrayList, then its the 2nd csv file
        String attrVal = val.toLowerCase(Locale.ROOT).replace(" ", "");
        m.find();
        insertAttribute(attrVal, id, m.group());
        break;
      }
    }
  }

  /**
   * Inserts attribute into data global hashmap depending on header type
   * @param attr attribute name (e.g. meeting_time, weaknesses, etc)
   * @param id student id number
   * @param val attribute value (e.g. morning, testing, etc)
   */
  private void insertAttribute(String attr, String id, String val) {
    // if attribute type is quantitative
    if (headersHash.get(attr+"s") == "quantitative") {
      // insert values as an integer
      data.get(id).put(attr, Double.parseDouble(val));
    } else { // otherwise, attribute type is qualitative

      // if attribute is already stored
      if (data.get(id).containsKey(attr)){

        // check if it already has multiples values
        if (data.get(id).get(attr) instanceof ArrayList) {
          // if so, add the new value to the list
          ((ArrayList<String>) data.get(id).get(attr)).add(val);
        } else { // if it is not stored as a list/has multiple values

          // instantiate a new ArrayList
          ArrayList<String> values = new ArrayList<String>();

          // add the already stored value and the new value to the list
          values.add((String) data.get(id).get(attr));
          values.add(val);

          // replace the previous value in the hashmap with the new list
          data.get(id).replace(attr, values);
        }
      } else{ // if the attribute is not yet stored, put it in the hashmap
        data.get(id).put(attr,  val);
      }
    }
  }

  @Override
  public void clear() {
    data.clear();
  }

  @Override
  public int size() {
    return data.size();
  }

  @Override
  public HashMap<String, HashMap<String, Object>> getData() {
    return data;
  }

  @Override
  public void readHeaders(String line) {
    // clear old header information
    headers.clear();

    // initialize matcher object to parse the line
    Matcher m = Pattern.compile("(\".*?\")|([^\",*?\"]+)").matcher(line);

    // while tokens are matched by the matcher
    while(m.find()) {
      // add header string to global list (set to lower case and remove excess blank spaces)
      headers.add(m.group().toLowerCase(Locale.ROOT).replace(" ", ""));
    }
  }
}
