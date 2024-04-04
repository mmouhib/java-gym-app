package com.esprit.gym_gui.repository;

import com.esprit.gym_gui.interfaces.ICovoiturage;
import com.esprit.gym_gui.models.Covoiturage;
import com.esprit.gym_gui.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CovoiturageRepository implements ICovoiturage {

    private Connection connection;

    public CovoiturageRepository() {
        connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<Covoiturage> findAll() {
        Connection connection = DatabaseConnection.getConnection();
        List<Covoiturage> lst = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM covoiturage");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Covoiturage covoiturage = new Covoiturage();
                covoiturage.setId(rs.getInt(1));
                covoiturage.setName(rs.getString(2));
                covoiturage.setNbplaces(rs.getInt(3));
                covoiturage.setDate_dep(rs.getString(4));
                covoiturage.setPos_dep(rs.getString(5));
                covoiturage.setStatut(rs.getString(6));
                covoiturage.setUserId(rs.getInt(7));
                lst.add(covoiturage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public Covoiturage findById(int id) {
        Covoiturage covoiturage = new Covoiturage();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM covoiturage WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                covoiturage.setId(rs.getInt(1));
                covoiturage.setName(rs.getString(2));
                covoiturage.setNbplaces(rs.getInt(3));
                covoiturage.setDate_dep(rs.getString(4));
                covoiturage.setPos_dep(rs.getString(5));
                covoiturage.setStatut(rs.getString(6));
                covoiturage.setUserId(rs.getInt(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return covoiturage;
    }

    @Override
    public Covoiturage save(Covoiturage covoiturage) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO covoiturage(name, nbplaces, date_dep, pos_dep, statut, userId) VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, covoiturage.getName());
            ps.setInt(2, covoiturage.getNbplaces());
            ps.setString(3, covoiturage.getDate_dep());
            ps.setString(4, covoiturage.getPos_dep());
            ps.setString(5, covoiturage.getStatut());
            ps.setInt(6, covoiturage.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastAddedCovoiturage();
    }

    private Covoiturage lastAddedCovoiturage() {
        Covoiturage covoiturage = new Covoiturage();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM covoiturage ORDER BY id DESC LIMIT 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                covoiturage.setId(rs.getInt(1));
                covoiturage.setName(rs.getString(2));
                covoiturage.setNbplaces(rs.getInt(3));
                covoiturage.setDate_dep(rs.getString(4));
                covoiturage.setPos_dep(rs.getString(5));
                covoiturage.setStatut(rs.getString(6));
                covoiturage.setUserId(rs.getInt(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return covoiturage;
    }

    @Override
    public void update(Covoiturage covoiturage) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE covoiturage SET name=?, nbplaces=?, date_dep=?, pos_dep=?, statut=?, userId=? WHERE id=?");
            ps.setString(1, covoiturage.getName());
            ps.setInt(2, covoiturage.getNbplaces());
            ps.setString(3, covoiturage.getDate_dep());
            ps.setString(4, covoiturage.getPos_dep());
            ps.setString(5, covoiturage.getStatut());
            ps.setInt(6, covoiturage.getUserId());
            ps.setInt(7, covoiturage.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM covoiturage WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Covoiturage> getCovoituragesByUser(int id) {
        List<Covoiturage> lst = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM covoiturage WHERE userId=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Covoiturage covoiturage = new Covoiturage();
                covoiturage.setId(rs.getInt(1));
                covoiturage.setName(rs.getString(2));
                covoiturage.setNbplaces(rs.getInt(3));
                covoiturage.setDate_dep(rs.getString(4));
                covoiturage.setPos_dep(rs.getString(5));
                covoiturage.setStatut(rs.getString(6));
                covoiturage.setUserId(rs.getInt(7));
                lst.add(covoiturage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public List<Covoiturage> getCovoituragesByDate(String date) {
        List<Covoiturage> lst = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM covoiturage WHERE date_dep>=?");
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Covoiturage covoiturage = new Covoiturage();
                covoiturage.setId(rs.getInt(1));
                covoiturage.setName(rs.getString(2));
                covoiturage.setNbplaces(rs.getInt(3));
                covoiturage.setDate_dep(rs.getString(4));
                covoiturage.setPos_dep(rs.getString(5));
                covoiturage.setStatut(rs.getString(6));
                covoiturage.setUserId(rs.getInt(7));
                lst.add(covoiturage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }

    @Override
    public List<Covoiturage> getCovoituragesByDates(String start, String end) {
        List<Covoiturage> lst = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM covoiturage WHERE date_dep>=? AND date_dep<=?");
            ps.setString(1, start);
            ps.setString(2, end);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Covoiturage covoiturage = new Covoiturage();
                covoiturage.setId(rs.getInt(1));
                covoiturage.setName(rs.getString(2));
                covoiturage.setNbplaces(rs.getInt(3));
                covoiturage.setDate_dep(rs.getString(4));
                covoiturage.setPos_dep(rs.getString(5));
                covoiturage.setStatut(rs.getString(6));
                covoiturage.setUserId(rs.getInt(7));
                lst.add(covoiturage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }
}
