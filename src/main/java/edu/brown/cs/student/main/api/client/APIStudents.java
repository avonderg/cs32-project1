package edu.brown.cs.student.main.api.client;

public class APIStudents {
    private String id;
    private String name;
    private String gender;
    private String classYear;
    private String nationality;
    private String race;
    private String experienceYrs;
    private String communicationStyle;
    private String weeklyAvailHrs;
    private String meetingStyle;
    private String meetingTime;
    private String softwareEngnConfidence;

    public APIStudents(String id, String name, String gender, String classYear, String nationality, String race, String experienceYrs, String communicationStyle, String weeklyAvailHrs, String meetingStyle, String meetingTime, String softwareEngnConfidence) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.classYear = classYear;
        this.nationality = nationality;
        this.race = race;
        this.experienceYrs = experienceYrs;
        this.communicationStyle = communicationStyle;
        this.weeklyAvailHrs = weeklyAvailHrs;
        this.meetingStyle = meetingStyle;
        this.meetingTime = meetingTime;
        this.softwareEngnConfidence = softwareEngnConfidence;
    }


}
