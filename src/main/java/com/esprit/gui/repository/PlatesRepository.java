package com.esprit.gui.repository;

import com.esprit.gui.interfaces.IPlate;
import com.esprit.gui.models.Plate;
import com.esprit.gui.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlatesRepository implements IPlate {

    private Connection connection;

    public PlatesRepository() {
        connection = DatabaseConnection.getConnection();

    }

    @Override
    public Plate save(Plate plate) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into plate (name, calories, protein, carbs, fat, sugar, user_id) values (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, plate.getName());
            preparedStatement.setInt(2, plate.getCalories());
            preparedStatement.setInt(3, plate.getProtein());
            preparedStatement.setInt(4, plate.getCarbs());
            preparedStatement.setInt(5, plate.getFat());
            preparedStatement.setInt(6, plate.getSugar());
            preparedStatement.setInt(7, plate.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("problem adding plate");
        }
        return plate;
    }

    @Override
    public void update(Plate plate) {
        try {
            PreparedStatement ps = connection.prepareStatement("update plate set name=?, calories=?, protein=?, carbs=?, fat=?, sugar=?, user_id=? where id=?");
            ps.setString(1, plate.getName());
            ps.setInt(2, plate.getCalories());
            ps.setInt(3, plate.getProtein());
            ps.setInt(4, plate.getCarbs());
            ps.setInt(5, plate.getFat());
            ps.setInt(6, plate.getSugar());
            ps.setInt(7, plate.getUserId());
            ps.setInt(8, plate.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem in updating plate");
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("delete from plate where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem deleting a plate");
        }
    }

    @Override
    public Plate findById(int id) {
        Plate plate = new Plate();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from plate where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                plate.setId(rs.getInt(1));
                plate.setName(rs.getString(2));
                plate.setCalories(rs.getInt(3));
                plate.setProtein(rs.getInt(4));
                plate.setCarbs(rs.getInt(5));
                plate.setFat(rs.getInt(6));
                plate.setSugar(rs.getInt(7));
                plate.setUserId(rs.getInt(8));
            }
        } catch (SQLException e) {
            System.out.println("problem in finding plate by id method");
        }
        return plate;
    }

    @Override
    public List<Plate> list() {
        List<Plate> plates = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from plate");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Plate plate = new Plate();
                plate.setId(rs.getInt(1));
                plate.setName(rs.getString(2));
                plate.setCalories(rs.getInt(3));
                plate.setProtein(rs.getInt(4));
                plate.setCarbs(rs.getInt(5));
                plate.setFat(rs.getInt(6));
                plate.setSugar(rs.getInt(7));
                plate.setUserId(rs.getInt(8));
                plates.add(plate);
            }

        } catch (SQLException e) {
            System.out.println("problem in listing plates method");
        }
        return plates;
    }

    @Override
    public List<Plate> getPlatesByUser(int id) {
        List<Plate> plates = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from plate where user_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Plate plate = new Plate();
                plate.setId(rs.getInt(1));
                plate.setName(rs.getString(2));
                plate.setCalories(rs.getInt(3));
                plate.setProtein(rs.getInt(4));
                plate.setCarbs(rs.getInt(5));
                plate.setFat(rs.getInt(6));
                plate.setSugar(rs.getInt(7));
                plate.setUserId(rs.getInt(8));
                plates.add(plate);
            }
        } catch (SQLException e) {
            System.out.println("problem in getting the list of plates by user method");
        }
        return plates;
    }

    public boolean plateExists(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from plate where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("problem in checking if plate exists");
        }
        return false;
    }

}
