module com.example.studentregistrationapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.studentregistrationform to javafx.fxml;
    exports com.example.studentregistrationform;
}