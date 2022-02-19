package edu.brown.cs.student.main;

import edu.brown.cs.student.main.kdTree.KDInsertable;

import java.util.ArrayList;
import java.util.List;
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
//  private KDNode leftChild;
//  private KDNode rightChild;

  public Student(String line) {
    List<String> tokens = new ArrayList<>();
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

//    this.leftChild = null;
//    this.rightChild = null;
  }

  private String[] cleanList(String[] items){
    String[] cleanList = new String[items.length];
    for (int i=0; i<items.length; i++) {
      cleanList[i] = items[i].replace("\"", "").trim();
    }
    return cleanList;
  }

  @Override
  public String getID() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getGender() {
    return gender;
  }

  public String getClassYear() {
    return classYear;
  }

  public String getNationality() {
    return nationality;
  }

  public String getRace() {
    return race;
  }

  public String getYearsExperience() {
    return yearsExperience;
  }

  public String getCommunicationStyle() {
    return communicationStyle;
  }

  public String getWeeklyAvailHours() {
    return weeklyAvailHours;
  }

  public String getMeetingStyle() {
    return meetingStyle;
  }

  public String getMeetingTime() {
    return meetingTime;
  }

  public String getSoftwareEngnConfidence() {
    return softwareEngnConfidence;
  }

  public String[] getStrengths() {
    return strengths;
  }

  public String[] getWeaknesses() {
    return weaknesses;
  }

  public String[] getSkills() {
    return skills;
  }

  public String[] getInterests() {
    return interests;
  }

  /**
   * KDNode interface method.
   * @return double of coords from three attributes
   */
  @Override
  public double[] getCoords() {
    return new double[]{Double.parseDouble(this.yearsExperience),
        Double.parseDouble(this.weeklyAvailHours), Double.parseDouble(this.softwareEngnConfidence)};
  }

}
