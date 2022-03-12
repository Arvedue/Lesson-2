module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires javafx.graphics;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.Controller;
    opens com.example.demo.Controller to javafx.fxml;
    exports com.example.demo.Controller.Employee;
    opens com.example.demo.Controller.Employee to javafx.fxml;
    exports com.example.demo.Controller.Department;
    opens com.example.demo.Controller.Department to javafx.fxml;
}