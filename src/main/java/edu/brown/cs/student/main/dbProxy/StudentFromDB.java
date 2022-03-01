package edu.brown.cs.student.main.dbProxy;

import java.util.ArrayList;
import java.util.List;

public class StudentFromDB {

  private final int id;

  private final String name;
  private final String email;
  private final String skill;
//  private final List<String> interests;
//  private final List<String> traits;

  public StudentFromDB(int id, String name, String email, String skill) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.skill = skill;
//    this.interests = interests;
//    this.traits = traits;
  }

  public int getId() {
    return this.id;
  }

//  public List<String> getTraits() {
//    return traits;
//  }
//
//  public List<String> getInterests() {
//    return this.interests;
//  }

  public String getEmail() {
    return this.email;
  }

  public String getName() {
    return this.name;
  }

  public String getSkill() {
    return this.skill;
  }

//  public void addInterest(String interest) {
//    this.interests.add(interest);
//  }
//
//  public void addTrait(String trait) {
//    this.traits.add(trait);
//  }

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
