package edu.brown.cs.student.main.api.client;

/**
 * Class for storing information on student objects extracted from API aggregation calls
 */
public class APIMatchStudents {
    private final int id;
    private final String name;
    private final String gender;
    private final String ssn;
    private final String nationality;
    private final String race;
    private final int software_engn_confidence;


    /**
     * Constructor for the API Match students class
     * @param id
     * @param name
     * @param gender
     * @param ssn
     * @param nationality
     * @param race
     * @param software_engn_confidence
     */
    public APIMatchStudents(int id, String name, String gender, String ssn, String nationality, String race, int software_engn_confidence) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.ssn = ssn;
        this.nationality = nationality;
        this.race = race;
        this.software_engn_confidence = software_engn_confidence;
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
     * Getter for a student gender
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Getter for a student ssn
     * @return ssn
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Getter for a student nationality
     * @return nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Getter for a student race
     * @return race
     */
    public String getRace() {
        return race;
    }

    /**
     * Getter for a student software engineering confidence
     * @return softwareEngnConfidence
     */
    public int getSoftware_engn_confidence() {
        return software_engn_confidence;
    }

    /**
     * Coverts any given APIMatchStudents object into a single string
     * @return
     */
    public String convertToString() {
        return String.join(", ", Integer.toString(this.id), this.name, this.gender, this.ssn, this.nationality, this.race, Integer.toString(this.software_engn_confidence));
    }
}
