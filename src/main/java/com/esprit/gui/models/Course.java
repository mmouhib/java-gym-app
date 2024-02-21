package com.esprit.gui.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class Course {

    private int id;
    private String name;
    private String type;
    private String coach;
    private int userId;
    private int numPlaces;
    private java.util.Date StartDate;
    private java.util.Date EndDate;

    public Course() {
    }

    public Course(int id, String name, String type, String coach, int userId, int numPlaces, Date StartDate, Date EndDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.coach = coach;
        this.userId = userId;
        this.numPlaces = numPlaces;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }

    public Course( String name, String type, String coach, int userId, int numPlaces, Date StartDate, Date EndDate) {
        this.name = name;
        this.type = type;
        this.coach = coach;
        this.userId = userId;
        this.numPlaces = numPlaces;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }

}
