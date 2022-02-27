package edu.brown.cs.student.main.csvReader;

import edu.brown.cs.student.main.kdTree.KDInsertable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student implements KDInsertable {
  private String id;
  private String name;
  private String email;
  private String gender;
  private String classYear;
  private String ssn;
  private String nationality;
  private String race;
  private String yearsExperience;
  private String communicationStyle;
  private String weeklyAvailHours;
  private String meetingStyle;
  private String meetingTime;
  private String softwareEngnConfidence;
  private String[] strengths;
  private String[] weaknesses;
  private String[] skills;
  private String[] interests;

  /**
   * Class constructor. Parses String parameter line and stores attributes.
   * @param line
   */
  public Student(String line) {
    Matcher m = Pattern.compile("(\".*?\")|([^\",*?\"]+)").matcher(line);
    m.find();
    id = m.group();
    m.find();
    name = m.group();
    m.find();
    email = m.group();
    m.find();
    gender = m.group();
    m.find();
    classYear = m.group();
    m.find();
    ssn = m.group();
    m.find();
    nationality = m.group();
    m.find();
    race = m.group();
    m.find();
    yearsExperience = m.group();
    m.find();
    communicationStyle = m.group();
    m.find();
    weeklyAvailHours = m.group();
    m.find();
    meetingStyle = m.group();
    m.find();
    meetingTime = m.group();
    m.find();
    softwareEngnConfidence = m.group();
    m.find();
    strengths = m.group().split(",");
    strengths = this.cleanList(strengths);
    m.find();
    weaknesses = m.group().split(",");
    weaknesses = this.cleanList(weaknesses);
    m.find();
    skills = m.group().split(",");
    skills = this.cleanList(skills);
    m.find();
    interests = m.group().split(",");
    interests = this.cleanList(interests);
  }

  public Student(HashMap<String, Object> attributes, String id) {
    this.id = id;
    this.name = (String) attributes.get("name");
    this.email = (String) attributes.get("email");
    this.gender = (String) attributes.get("gender");
    this.classYear = (String) attributes.get("class_year");
    this.nationality = (String) attributes.get("nationality");
    this.race = (String) attributes.get("race");
    this.yearsExperience = (String) attributes.get("years_experience");
    this.communicationStyle = (String) attributes.get("communication_style");
    this.weeklyAvailHours = (String) attributes.get("weekly_avail_hours");
    this.meetingStyle = (String) attributes.get("meeting_style");
    this.meetingTime = (String) attributes.get("meeting_time");
    this.softwareEngnConfidence = (String) attributes.get("software_engn_confidence");

    this.strengths = convertList(attributes.get("strengths"));
    this.weaknesses = convertList(attributes.get("weaknesses"));
    this.skills = convertList(attributes.get("skills"));
    this.interests = convertList(attributes.get("interests"));
  }

  private String[] convertList (Object vals) {
    if (vals instanceof ArrayList<?>) {
      ArrayList<String> list = (ArrayList<String>) ((ArrayList<?>) vals);
      return list.toArray(new String[list.size()]);
    } else {
      return new String[]{(String) vals};
    }
  }

  /**
   * Removes extra quotations and spaces.
   * @param items list of Strings to clean
   * @return cleaned list
   */
  private String[] cleanList(String[] items){
    // initializes a new array that will store the same Strings, but without extra characters
    String[] cleanList = new String[items.length];

    // iterates through each String in the list items
    for (int i=0; i<items.length; i++) {

      // removes extra quotation marks and spaces from each String
      cleanList[i] = items[i].replace("\"", "").trim();
    }

    // return the new, cleaned list
    return cleanList;
  }

  /**
   * @return id
   */
  @Override

  public String getID() {
    return id;
  }

  /**
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns String email.
   * @return
   */
  public String getEmail() {
    return email;
  }

  /**
   * Returns String gender.
   * @return
   */
  public String getGender() {
    return gender;
  }

  /**
   * Returns String class year.
   * @return
   */
  public String getClassYear() {
    return classYear;
  }

  /**
   * Returns String nationality.
   * @return
   */
  public String getNationality() {
    return nationality;
  }

  /**
   * Returns String race.
   * @return
   */
  public String getRace() {
    return race;
  }

  /**
   * Returns String years experience.
   * @return
   */
  public String getYearsExperience() {
    return yearsExperience;
  }

  /**
   * Returns String communication style.
   * @return
   */
  public String getCommunicationStyle() {
    return communicationStyle;
  }

  /**
   * Returns String weekly available hours.
   * @return
   */
  public String getWeeklyAvailHours() {
    return weeklyAvailHours;
  }

  /**
   * Returns String meeting style.
   * @return
   */
  public String getMeetingStyle() {
    return meetingStyle;
  }

  /**
   * Returns String meeting time.
   * @return
   */
  public String getMeetingTime() {
    return meetingTime;
  }

  /**
   * Returns String software engineering confidence.
   * @return
   */
  public String getSoftwareEngnConfidence() {
    return softwareEngnConfidence;
  }

  /**
   * Returns String array of strengths.
   * @return
   */
  public String[] getStrengths() {
    return strengths;
  }

  /**
   * Returns String array of weaknesses.
   * @return
   */
  public String[] getWeaknesses() {
    return weaknesses;
  }

  /**
   * Returns String array of skills.
   * @return
   */
  public String[] getSkills() {
    return skills;
  }

  /**
   * Returns String array of interests.
   * @return
   */
  public String[] getInterests() {
    return interests;
  }

  /**
   * KDNode interface method.
   * @return double of coords from three attributes
   */
  @Override
  public double[] getCoords() {
    return new double[]{
        Double.parseDouble(this.yearsExperience),
        Double.parseDouble(this.weeklyAvailHours),
        Double.parseDouble(this.softwareEngnConfidence)};
  }

}
