package com.example.attenence.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dataModel {

    @SerializedName("Student_Roll")
    @Expose
    private Integer studentRoll;
    @SerializedName("Student_Name")
    @Expose
    private String studentName;
    @SerializedName("Father_Name")
    @Expose
    private String fatherName;
    @SerializedName("Mother_Name")
    @Expose
    private String motherName;
    @SerializedName("Father_PHN")
    @Expose
    private Integer fatherPHN;
    @SerializedName("Attendence_id")
    @Expose
    private Integer attendenceId;

    public Integer getStudentRoll() {
        return studentRoll;
    }

    public void setStudentRoll(Integer studentRoll) {
        this.studentRoll = studentRoll;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public Integer getFatherPHN() {
        return fatherPHN;
    }

    public void setFatherPHN(Integer fatherPHN) {
        this.fatherPHN = fatherPHN;
    }

    public Integer getAttendenceId() {
        return attendenceId;
    }

    public void setAttendenceId(Integer attendenceId) {
        this.attendenceId = attendenceId;
    }

}