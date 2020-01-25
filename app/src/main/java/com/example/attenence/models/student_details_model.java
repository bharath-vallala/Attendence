package com.example.attenence.models;

public class student_details_model {
    String name;
    int roll;
    boolean status;
    String phn;

    public student_details_model(String name, int roll, boolean status, String phn) {
        this.name = name;
        this.roll = roll;
        this.status = status;
        this.phn=phn;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public student_details_model() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
