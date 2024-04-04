package com.esprit.gui.utils.api;

import com.esprit.gui.models.Plate;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlateResponse {
    private String name;
    private int calories;
    private int protein;
    private int carbs;
    private int fat;
    private int sugar;
    private int salt;
    private int sodium;
    private String imageUrl;
}
