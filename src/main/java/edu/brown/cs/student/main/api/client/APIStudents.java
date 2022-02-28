package edu.brown.cs.student.main.api.client;

public class APIStudents {
    private int id;
    private String name;
    private String classYear;
    private int experienceYrs;
    private String communicationStyle;
    private String weeklyAvailHrs;
    private String meetingStyle;
    private String meetingTime;

    public APIStudents(int id, String name, String classYear, int experienceYrs, String communicationStyle, String weeklyAvailHrs, String meetingStyle, String meetingTime) {
        this.id = id;
        this.name = name;
        this.classYear = classYear;
        this.experienceYrs = experienceYrs;
        this.communicationStyle = communicationStyle;
        this.weeklyAvailHrs = weeklyAvailHrs;
        this.meetingStyle = meetingStyle;
        this.meetingTime = meetingTime;
    }

}
