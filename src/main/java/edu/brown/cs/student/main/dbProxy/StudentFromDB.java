package edu.brown.cs.student.main.dbProxy;

import java.util.ArrayList;
import java.util.List;

public class StudentFromDB {

  private final int id;

  private final String name;
  private final String email;
  private final String skill;
  private List<String> interests;
  private List<String> strengths;
  private List<String> weaknesses;


  public StudentFromDB(int id, String name, String email, String skill) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.skill = skill;
    this.strengths = null;
    this.weaknesses = null;
  }

  public int getId() {
    return this.id;
  }
//
//  public List<String> getTraits() {
//    return this.;
//  }

  public List<String> getInterests() {
    return this.interests;
  }

  public String getEmail() {
    return this.email;
  }

  public String getName() {
    return this.name;
  }

  public String getSkill() {
    return this.skill;
  }

  public void setInterests(List<String> interests) {
    this.interests = interests;
  }

  public void setStrengths(List<String> strengths) {
    this.strengths = strengths;
  }

  public void setWeaknesses(List<String> weaknesses) {
    this.weaknesses = weaknesses;
  }

  @Override
  public String toString() {
    return "DatabaseStudent{"
        + "id=" + id
        + ", name='" + name + '\''
        + ", email='" + email + '\''
        + ", skill='" + skill + '\''
        + '}';
  }
}
