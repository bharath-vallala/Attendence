
package com.example.attenence.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostAttenence {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subject")
    @Expose
    private Integer subject;
    @SerializedName("date")
    @Expose
    private String date;

    public PostAttenence(Integer id, Integer subject, String date) {
        this.id = id;
        this.subject = subject;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}