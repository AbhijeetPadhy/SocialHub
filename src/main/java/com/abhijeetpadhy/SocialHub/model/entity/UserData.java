package com.abhijeetpadhy.SocialHub.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "USER_DATA")
public class UserData {
    @Id
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "DOB")
    private Date DOB;

    @Column(name = "ABOUT")
    private String about;

    @Column(name = "PROFESSION")
    private String profession;

    @Column(name = "PLACE")
    private String place;

    @Column(name = "GENDER")
    private char gender;

    @Column(name = "LAST_MODIFIED")
    private Timestamp lastModified;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }
}
