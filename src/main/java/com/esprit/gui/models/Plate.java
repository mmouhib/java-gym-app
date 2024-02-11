package com.esprit.gui.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor

public class Plate {
    private int id;
    private int userId;
    private int mealId;
    private int quantity;
    private Date date;

    public Plate(int userId, int mealId, int quantity, Date date) {
        this.userId = userId;
        this.mealId = mealId;
        this.quantity = quantity;
        this.date = date;
    }

}
