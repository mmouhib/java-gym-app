package com.esprit.gui.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Covoiturage {
    private int id;
    private String name;
    private int nbplaces;
    private String date_dep;
    private String pos_dep;
    private String statut;
    private int userId;

    public Covoiturage() {
    }

    public Covoiturage(String name, int nbplaces, String date_dep, String pos_dep, String statut, int userId) {
        this.name = name;
        this.nbplaces = nbplaces;
        this.date_dep = date_dep;
        this.pos_dep = pos_dep;
        this.statut = statut;
        this.userId = userId;
    }

    public Covoiturage(int id, String name, int nbplaces, String date_dep, String pos_dep, String statut, int userId) {
        this.id = id;
        this.name = name;
        this.nbplaces = nbplaces;
        this.date_dep = date_dep;
        this.pos_dep = pos_dep;
        this.statut = statut;
        this.userId = userId;
    }
}
