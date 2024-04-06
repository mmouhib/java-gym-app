package com.esprit.gui.controllers;

import com.esprit.gui.models.Course;
import com.esprit.gui.repository.CourseRepository;
import com.esprit.gui.utils.CourseUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;

public class CourseController {

    CourseRepository courseRepository = new CourseRepository();
    private static int idCounter = 0;

    @FXML
    public TextField id_input;
    @FXML
    public TextField name_input;
    @FXML
    public TextField Coach_input;
    @FXML
    public TextField Type_input;

    // course table view
    @FXML
    public TableView<Course> courseTableView;
    @FXML
    public TableColumn<Course, Integer> id;
    @FXML
    public TableColumn<Course, String> name;
    @FXML
    public TableColumn<Course, String> type;
    @FXML
    public TableColumn<Course, String> coach;



    ObservableList<String> roomTypeList = FXCollections.observableArrayList("Room Type", "Lesson room", "Cross Training");
    ObservableList<String> coachList = FXCollections.observableArrayList("All the coaches", "Coach Mouhib", "Coach Youssef", "Coach Salma");
    ObservableList<String> courseTypeList = FXCollections.observableArrayList("Our courses", "Abdomen Destruction", "Leg day, Death day", "DRX");

    @FXML
    private ComboBox roomTypeBox;
    @FXML
    private ComboBox coachBox;
    @FXML
    private ComboBox courseTypeBox;
    @FXML
    private void initialize() {
//        courseTableView.setEditable(true);
//        name.setCellFactory(TextFieldTableCell.forTableColumn());
//        name.setOnEditCommit(
//                (TableColumn.CellEditEvent<Course, String> t) -> {
//                    // Get the Course object from the row being edited
//                    Course course = t.getTableView().getItems().get(t.getTablePosition().getRow());
//                    // Update the name of the Course with the new value
//                    course.setName(t.getNewValue());
//                }
//        );
//        type.setCellFactory(TextFieldTableCell.forTableColumn());
//        type.setOnEditCommit(
//                (TableColumn.CellEditEvent<Course, String> t) -> {
//                    // Get the Course object from the row being edited
//                    Course course = t.getTableView().getItems().get(t.getTablePosition().getRow());
//                    // Update the name of the Course with the new value
//                    course.setName(t.getNewValue());
//                }
//        );
//        coach.setCellFactory(TextFieldTableCell.forTableColumn());
//        coach.setOnEditCommit(
//                (TableColumn.CellEditEvent<Course, String> t) -> {
//                    // Get the Course object from the row being edited
//                    Course course = t.getTableView().getItems().get(t.getTablePosition().getRow());
//                    // Update the name of the Course with the new value
//                    course.setName(t.getNewValue());
//                }
//        );

        roomTypeBox.setItems(roomTypeList);
        roomTypeBox.setValue("Room Type");

        coachBox.setItems(coachList);
        coachBox.setValue("All the coaches");

        courseTypeBox.setItems(courseTypeList);
        courseTypeBox.setValue("Our courses");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        coach.setCellValueFactory(new PropertyValueFactory<>("coach"));

        // Set the TableView's items to the ObservableList
        courseTableView.setItems(FXCollections.observableArrayList());


    }
    // Method to add a new course to the TableView
    public void addCourse() {
        // Retrieve the selected items from the ComboBoxes
        String selectedRoomType = roomTypeBox.getSelectionModel().getSelectedItem().toString();
        String selectedCoach = coachBox.getSelectionModel().getSelectedItem().toString();
        String selectedCourseType = courseTypeBox.getSelectionModel().getSelectedItem().toString();

        // Create a new Course object using the selected items
        Course newCourse = new Course();
        newCourse.setName(selectedRoomType);
        newCourse.setCoach(selectedCoach);
        newCourse.setType(selectedCourseType);

        // Assuming you have a method to generate a unique ID for each course
        newCourse.setId(generateNewId());

        // Add the new Course object to the TableView
        courseTableView.getItems().add(newCourse);

        // Insert the new Course object into the database
        courseRepository.save(newCourse);


    }

    // Implement this method to generate a unique ID for each new course
    private int generateNewId() {
        return ++idCounter;
    }

    @FXML
    private void ClearSelections() {
        // Clear all the ComboBoxes
        roomTypeBox.getSelectionModel().clearSelection();
        coachBox.getSelectionModel().clearSelection();
        courseTypeBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void ClearTableView() {
        // Clear the TableView
        courseTableView.getItems().clear();
        // Clear the database
        courseTableView.getSelectionModel().getSelectedItems().forEach(Course -> {
            new CourseRepository().delete(Course.getId());

            ClearTableView();


        });

    }







    public void goToHome(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/nutrition/home.fxml"));
            Scene scene = new Scene(p,1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goToNutrition(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/nutrition/food.fxml"));
            Scene scene = new Scene(p, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToCourse(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/nutrition/course.fxml"));
            Scene scene = new Scene(p,1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void goToBill(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/nutrition/food.fxml"));
            Scene scene = new Scene(p,1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void goToShop(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/nutrition/food.fxml"));
            Scene scene = new Scene(p,1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void goToTransport(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/nutrition/food.fxml"));
            Scene scene = new Scene(p,1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void goToTracking(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/nutrition/nutrition.fxml"));
            Scene scene = new Scene(p, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToMeals(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/meals.fxml"));
            Scene scene = new Scene(p, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToProfile(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/edit-info.fxml"));
            Scene scene = new Scene(p, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logOut(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/com/esprit/gui/sign-in.fxml"));
            Scene scene = new Scene(p, 1100, 650);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            //appStage.setUserData(3);
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
