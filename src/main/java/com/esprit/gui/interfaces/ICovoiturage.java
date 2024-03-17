package com.esprit.gui.interfaces;

import com.esprit.gui.models.Covoiturage;

import java.util.List;

public interface ICovoiturage {
    List<Covoiturage> findAll();
    Covoiturage findById(int id);
    Covoiturage save(Covoiturage covoiturage);
    void update(Covoiturage covoiturage);
    void delete(int id);
    List<Covoiturage> getCovoituragesByUser(int id);
    List<Covoiturage> getCovoituragesByDate(String date);
    List<Covoiturage> getCovoituragesByDates(String start, String end);
}
