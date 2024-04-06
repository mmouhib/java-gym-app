package com.esprit.gui.models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Meal {
    private int id;
    private String name;
    private Date date;
    private int quantity;
    private String category;
    private int userId;
    private int plateId;

    public Meal(String name, Date date, int quantity, String category, int userId, int plateId) {
        this.name = name;
        this.date = date;
        this.quantity = quantity;
        this.category = category;
        this.userId = userId;
        this.plateId = plateId;
    }
}
