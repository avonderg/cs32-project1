package edu.brown.cs.student.main.dbProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to model student object created exclusively from the sqlite3 database.
 */
public class StudentFromDB {

  private final int id;

  private final String name;
  private final String email;
  private final String[] skills;
  private List<String> interests;
  private List<String> strengths;
  private List<String> weaknesses;

  /**
   * Constructor
   * @param id student id
   * @param name name
   * @param email student email
   * @param skill skill of student from skills table
   */
  public StudentFromDB(int id, String name, String email, String skill) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.skills = new String[1];
    this.skills[0] = skill;
    this.strengths = null;
    this.weaknesses = null;
  }

  /**
   * gets studentID
   * @return the id
   */
  public int getId() {
    return this.id;
  }
//
//  public List<String> getTraits() {
//    return this.;
//  }

  /**
   * Gets the list of the student Interests
   * @return str list
   */
  public List<String> getInterests() {
    return this.interests;
  }

  /**
   * Gets student email
   * @return str email
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Gets student name
   * @return str name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns an array of skills
   * @return str array of the one skill from the skills table in data.sqlite3
   */
  public String[] getSkills() {
    return this.skills;
  }

  /**
   * Sets the student interests list
   * @param interests arr of student interests
   */
  public void setInterests(List<String> interests) {
    this.interests = interests;
  }

  /**
   * Sets the student strengths list
   * @param strengths arr of student list
   */
  public void setStrengths(List<String> strengths) {
    this.strengths = strengths;
  }

  /**
   * Sets the student weakness list
   * @param weaknesses list of weaknesses
   */
  public void setWeaknesses(List<String> weaknesses) {
    this.weaknesses = weaknesses;
  }

  /**
   * Gets strengths list
   * @return list of strengths
   */
  public List<String> getStrengths() {
    return this.strengths;
  }

  /**
   * Gets student weaknesses list
   * @return list of weaknesses
   */
  public List<String> getWeaknesses() {
    return this.weaknesses;
  }
//  @Override
//  public String toString() {
//    return "DatabaseStudent{"
//        + "id=" + id
//        + ", name='" + name + '\''
//        + ", email='" + email + '\''
//        + ", skill='" + skills + '\''
//        + '}';
//  }
}
