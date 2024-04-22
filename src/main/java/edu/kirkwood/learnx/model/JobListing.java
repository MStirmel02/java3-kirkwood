package edu.kirkwood.learnx.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

public class JobListing {
    /*
    job_id, int
department_id, int
department_name, String
featured, boolean
position, String
posted_at, Instant
contract, String
location, String
description, String
     */
    private int job_id;
    private int department_id;
    private String department_name;
    private boolean featured;
    private String position;
    private Timestamp posted_at;
    private String contract;
    private String location;
    private String description;

    public JobListing(int job_id, int department_id, String department_name, boolean featured, String position, Timestamp posted_at, String contract, String location, String description) {
        this.job_id = job_id;
        this.department_id = department_id;
        this.department_name = department_name;
        this.featured = featured;
        this.position = position;
        this.posted_at = posted_at;
        this.contract = contract;
        this.location = location;
        this.description = description;
    }

    @Override
    public String toString() {
        return "JobListing{" +
                "job_id=" + job_id +
                ", department_id=" + department_id +
                ", department_name='" + department_name + '\'' +
                ", featured=" + featured +
                ", position='" + position + '\'' +
                ", posted_at=" + posted_at +
                ", contract='" + contract + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Timestamp getPosted_at() {
        return posted_at;
    }

    public void setPosted_at(Timestamp posted_at) {
        this.posted_at = posted_at;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
