package com.esprit.gui.interfaces;

import com.esprit.gui.models.Plate;

import java.util.List;


public interface IPlate extends ICrud<Plate> {
    List<Plate> getPlatesByUser(int id);

}