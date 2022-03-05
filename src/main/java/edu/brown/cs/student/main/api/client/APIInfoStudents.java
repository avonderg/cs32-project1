package edu.brown.cs.student.main.api.client;

/**
 * Class for storing information on student objects extracted from API aggregation calls
 */
public class APIInfoStudents {
    private final int id;
    private final String name;
    private final String email;
    private final String class_year;
    private final int years_experience;
    private final String communication_style;
    private final int weekly_avail_hours;
    private final String meeting_style;
    private final String meeting_time;

    /**
     * Constructor for the API Info students class
     * @param id
     * @param name
     * @param email
     * @param classYear
     * @param experienceYrs
     * @param communicationStyle
     * @param weeklyAvailHrs
     * @param meetingStyle
     * @param meetingTime
     */
    public APIInfoStudents(int id, String name, String email, String classYear, int experienceYrs, String communicationStyle, int weeklyAvailHrs, String meetingStyle, String meetingTime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.class_year = classYear;
        this.years_experience = experienceYrs;
        this.communication_style = communicationStyle;
        this.weekly_avail_hours = weeklyAvailHrs;
        this.meeting_style = meetingStyle;
        this.meeting_time = meetingTime;
    }

    /**
     *  Getter for a student ID
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for a student name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for a student email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for a student class year
     * @return classYear
     */
    public String getClass_year() {
        return class_year;
    }

    /**
     * Getter for student experience years
     * @return experienceYrs
     */
    public int getYears_experience() {
        return years_experience;
    }

    /**
     * Getter for a student communication style
     * @return communicationStyle
     */
    public String getCommunication_style() {
        return communication_style;
    }

    /**
     * Getter for a student weekly available hours
     * @return weeklyAvailHrs
     */
    public int getWeekly_avail_hours() {
        return weekly_avail_hours;
    }

    /**
     * Getter for a student meeting style
     * @return meetingStyle
     */
    public String getMeeting_style() {
        return meeting_style;
    }

    /**
     * Getter for a student meeting meeting time
     * @return meetingTime
     */
    public String getMeeting_time() {
        return meeting_time;
    }

    /**
     * Coverts any given APIInfoStudents object into a single string
     * @return
     */
    public String convertToString() {
        return String.join(", ", Integer.toString(this.id), this.name, this.class_year, Integer.toString(this.years_experience), this.communication_style, Integer.toString(this.weekly_avail_hours), this.meeting_style, this.meeting_time);
    }
}
