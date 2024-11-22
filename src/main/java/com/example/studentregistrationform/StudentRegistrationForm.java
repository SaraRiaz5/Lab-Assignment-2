package com.example.studentregistrationform;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class StudentRegistrationForm extends Application {

    private ArrayList<Person> personList = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        // Create a banner
        Label bannerLabel = new Label("WELCOME TO OUR CLASS");
        bannerLabel.setFont(new Font("Aileron bold", 40));
        bannerLabel.setTextFill(Color.DEEPSKYBLUE); // Color name used here
        bannerLabel.setStyle("-fx-background-color: ash green; -fx-text-alignment: center; -fx-padding: 20; -fx-border-width: 2px;");
        bannerLabel.setAlignment(Pos.CENTER);

        // Form Fields
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label fatherNameLabel = new Label("Father's Name:");
        TextField fatherNameField = new TextField();

        Label cnicLabel = new Label("CNIC:");
        TextField cnicField = new TextField();

        Label dobLabel = new Label("Date of Birth:");
        DatePicker dobPicker = new DatePicker();

        Label genderLabel = new Label("Gender:");
        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male", "Female");

        Label cityLabel = new Label("City:");
        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.getItems().addAll("Karachi", "Lahore", "Islamabad", "Peshawar", "Quetta");

        // Save Button
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: deepskyblue; -fx-text-fill: white; -fx-font-weight: bold;");

        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String fatherName = fatherNameField.getText();
            String cnic = cnicField.getText();
            String dob = dobPicker.getValue() != null ? dobPicker.getValue().toString() : "";
            String gender = genderComboBox.getValue();
            String city = cityComboBox.getValue();


            Person newPerson = new Person(name, fatherName, cnic, dob, gender, city);
            personList.add(newPerson);

            System.out.println(newPerson);

            clearFields(nameField, fatherNameField, cnicField, dobPicker, genderComboBox, cityComboBox);
        });

        // File chooser for image upload
        Button uploadButton = new Button("Upload Image");
        uploadButton.setStyle("-fx-background-color: deepskyblue; -fx-text-fill: white; -fx-font-weight: bold;");
        ImageView imageView = new ImageView();
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-border-color: white; -fx-border-width: 2px;");

        uploadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                imageView.setImage(new Image(file.toURI().toString()));
            }
        });

        // Center form layout
        GridPane formGrid = new GridPane();
        formGrid.setPadding(new Insets(30));
        formGrid.setHgap(20);
        formGrid.setVgap(20);

        formGrid.add(nameLabel, 0, 0);
        formGrid.add(nameField, 1, 0);
        formGrid.add(fatherNameLabel, 0, 1);
        formGrid.add(fatherNameField, 1, 1);
        formGrid.add(cnicLabel, 0, 2);
        formGrid.add(cnicField, 1, 2);

        formGrid.add(dobLabel, 0, 3);
        formGrid.add(dobPicker, 1, 3);

        formGrid.add(genderLabel, 0, 4);
        formGrid.add(genderComboBox, 1, 4);

        formGrid.add(cityLabel, 0, 5);
        formGrid.add(cityComboBox, 1, 5);

        formGrid.add(uploadButton, 1, 6);

        formGrid.add(saveButton, 1, 7);
        GridPane.setHalignment(saveButton, HPos.LEFT); // Center horizontally


        // Right VBox for displaying the uploaded image
        VBox rightBox = new VBox(20);
        rightBox.setPrefSize(200, 200); // Set fixed dimensions
        rightBox.setAlignment(Pos.CENTER); // Center the image
        rightBox.setPadding(new Insets(20,30,30,20));
        rightBox.setStyle("-fx-background-color:skyblue ; -fx-border-color: light white; -fx-border-width: 1px;");

        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        rightBox.getChildren().add(imageView);

        // Main BorderPane layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setTop(bannerLabel);
        BorderPane.setAlignment(bannerLabel, Pos.CENTER);
        root.setCenter(formGrid);
        root.setRight(rightBox);

        // Create and set Scene
        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Student Registration Form");
        stage.setScene(scene);
        stage.show();
    }

    // Method to clear form fields
    private void clearFields(TextField nameField, TextField fatherNameField, TextField cnicField, DatePicker dobPicker, ComboBox<String> genderComboBox, ComboBox<String> cityComboBox) {
        nameField.clear();
        fatherNameField.clear();
        cnicField.clear();
        dobPicker.setValue(null);
        genderComboBox.setValue(null);
        cityComboBox.setValue(null);
    }

    public static void main(String[] args) {
        launch();
    }
}

// Person Class
class Person {
    private String name;
    private String fatherName;
    private String cnic;
    private String dob;
    private String gender;
    private String city;

    public Person(String name, String fatherName, String cnic, String dob, String gender, String city) {
        this.name = name;
        this.fatherName = fatherName;
        this.cnic = cnic;
        this.dob = dob;
        this.gender = gender;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", cnic='" + cnic + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
